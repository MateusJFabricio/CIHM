package Background.Executor;

import java.awt.event.ActionListener;
import javax.swing.Timer;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import Background.Services.CommEnvase;
import Background.Services.GPIO;

public class TaskEnvase implements Runnable {
	public CommEnvase comm;
	public GPIO gpio;
	public boolean inicioRapido;
	private int tempoAguardoInicioLinha, tempoAguardoFimLinha;
	private Timer timerInicioLinha, timerFimLinha;
	private ActionListener actionInicioLinha, actionFimLinha;
	private boolean contagemInicioLinhaFinalizada = false, contagemFimLinhaFinalizada = false;
	
	public TaskEnvase(CommEnvase comm, GPIO gpio)
	{
		this.comm = comm;
		this.gpio = gpio;
		
		inicializaTimers();
	}
	
	private void inicializaTimers() {
		actionInicioLinha = new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int tempoEspera = 5000;
				try{
					if (tempoAguardoInicioLinha <= tempoEspera)
					{
						tempoAguardoInicioLinha = tempoAguardoInicioLinha + 10;  
					}
					
					if ((tempoAguardoInicioLinha >=tempoEspera) && (comm.getFrascosParaEnvasar() > 0))
					{
						finalizaContagemFrascosInicioLinha();
					}else if (comm.getFrascosParaEnvasar() >=12)
						finalizaContagemFrascosInicioLinha();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}};
		
		timerInicioLinha = new Timer(10, actionInicioLinha);
		
		actionFimLinha = new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int tempo = 200;
				if (gpio.inEnvFrascoSaindoDaAreaEnvase.isLow())
					tempoAguardoFimLinha = tempoAguardoFimLinha + 10;
				else
					tempoAguardoFimLinha = 0;
				
				if (tempoAguardoFimLinha >= tempo)
					finalizaContagemFrascosFimLinha();
		}};
		
		timerFimLinha = new Timer(10, actionFimLinha);
	}

	@Override
	public void run() {
		try{
			comm.setAlive(true);
			goHome();
			
			if (comm.isIniciaProducao())
			{
				if (!emPosicaoHome())
					posicaoEnvase();
				
				if (!comm.isInicioRapido())
				{
					comm.setInicioRapido(true);
					buscarFrascos();
				}
			}
				
			while(comm.isIniciaProducao())
			{
				
				if (comm.isCicloContinuo())
				{
					if (!emPosicaoHome())
						posicaoEnvase();
					ciclo();
					enviarParaTampador();
				}
				else if (comm.getMetaProducao() > 0)
				{
					if (!emPosicaoHome())
						posicaoEnvase();
					ciclo();
					enviarParaTampador();
				}
				else
					comm.setIniciaProducao(false);
			}
			
			desligaBomba();
			subirBicosEnvase();
			recuarEnforcador();
			
		}catch (Exception e) {
			System.out.println("Ciclo Envase finalizado");
			comm.setCicloContinuo(false);
			comm.setIniciaProducao(false);
			comm.setAlive(false);
			try {
				goHome();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}finally {
			comm.setAlive(false);
		}
	}
	
	private void posicaoEnvase() throws Exception {
		desligaBomba();
		pararEsteira1();
		subirBicosEnvase();
		recuarEnforcador();
	}

	private void ciclo() throws Exception
	{
		
		envase();
		
		aguarda(1000);
	}


	private void envase() throws Exception
	{
		pararEsteira1();
		avancarTrava1();
		avancarTrava2();
		avancarEnforcador();
		baixarBicosEnvase();
		ligaBomba();
		aguardaTempoEnvase();
		desligaBomba();
		subirBicosEnvase();
		recuarEnforcador();
		recuarTrava1();
		recuarTrava2();
	}
	
	private void buscarFrascos() throws Exception
	{
		recuarTrava2();
		avancarTrava1();
		ligarEsteira1();
		contarFrascosEntradaLinha();
		while(!contagemInicioLinhaFinalizada)
		{
			if (!comm.isIniciaProducao()) exit();
			aguarda(100);
		}
		posicionarFrascos();
	}
	
	private void exit() throws Exception {
		throw new Exception("interrompido por emergencia");
	}

	private void posicionarFrascos() throws Exception {
		while(gpio.inEnvFrascoSaindoDaAreaEnvase.isLow())
		{
			if (!comm.isIniciaProducao()) exit();
			aguarda(100);
		}
	}

	private void finalizaContagemFrascosInicioLinha() throws Exception
	{
		retiraListenerFrascoInicioLinha();
		timerInicioLinha.stop();
		tempoAguardoInicioLinha = 0;
		avancarTrava2();
		posicionarFrascos();
		contagemInicioLinhaFinalizada = true;
	}
	
	private void finalizaContagemFrascosFimLinha()
	{
		avancarTrava1();
		retiraListenerFrascoFimLinha();
		timerFimLinha.stop();
		contagemFimLinhaFinalizada = true;
	}
	
	private void retiraListenerFrascoFimLinha() {
		gpio.inEnvFrascoSaindoDaAreaEnvase.removeAllListeners();
	}

	private void retiraListenerFrascoInicioLinha() {
		gpio.inEnvFrascoEntrandoNaAreaEnvase.removeAllListeners();
	}
	
	private void enviarParaTampador() throws Exception
	{
		//Ajusta a meta de producao
		comm.setFrascosEnvasado(comm.getFrascosParaEnvasar());
		comm.setMetaProducao(comm.getMetaProducao() - comm.getFrascosEnvasado());
		
		if (comm.getMetaProducao() <= 0)
			comm.setMetaProducao(0);
		
		contagemFimLinhaFinalizada = false;
		contagemInicioLinhaFinalizada = false;
		contarFrascosEntradaLinha();
		contarFrascosSaidaLinha();
		ligarEsteira1();
		
		//Sempre aguarda o fim e o inicio de producao
		if (comm.isCicloContinuo())
		{
			while((!(contagemFimLinhaFinalizada)) || (!(contagemInicioLinhaFinalizada)))
			{
				if (!comm.isIniciaProducao() && !(contagemInicioLinhaFinalizada)) break;
				aguarda(100);
			}
		}else
		{
			//Com meta, caso a meta seja atingida, deve aguardar somente o fim da producao
			if (comm.getMetaProducao() <= 0)
			{
				while(!contagemFimLinhaFinalizada && comm.isIniciaProducao())
				{
					aguarda(100);
				}
				//Aguarda este tempo para desligar a esteira 1
				aguarda(4000);
			}else
				while((!(contagemFimLinhaFinalizada)) || (!(contagemInicioLinhaFinalizada)))
				{
					if (!comm.isIniciaProducao() && !(contagemInicioLinhaFinalizada)) break;
					aguarda(100);
				}
		}
			
	}
	
	private void contarFrascosSaidaLinha() {
		contagemFimLinhaFinalizada = false;
		
		gpio.inEnvFrascoSaindoDaAreaEnvase.addListener(new GpioPinListenerDigital() {
			@Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (gpio.outEnvEsteira1.isLow())
            	{
					if (event.getState().isLow())
	            	{
	            		comm.setFrascoEnviadoTampamento(comm.getFrascoEnviadoTampamento() + 1);
	            	}
            	}
            }

    	});
		
		timerFimLinha.start();
	}

	private void contarFrascosEntradaLinha() {
		comm.setFrascosParaEnvasar(0);
		contagemInicioLinhaFinalizada = false;
		
		gpio.inEnvFrascoEntrandoNaAreaEnvase.addListener(new GpioPinListenerDigital() {
            private boolean contado = false;

			@Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState().isHigh() && !contado )
                {
                	contado = false;
                	comm.setFrascosParaEnvasar(comm.getFrascosParaEnvasar() + 1);
                	tempoAguardoInicioLinha = 0;
                }else if (event.getState().isLow() && contado )
                	contado = false;
            }

    	});
		
		timerInicioLinha.start();
		
	}

	private void avancarEnforcador() {
		if (gpio.outEnvPistaoEnforcador.isHigh())
		{
			gpio.outEnvPistaoEnforcador.low();
		}
	}


	private void aguardaTempoEnvase() {
		for (int i = 0; i < comm.getTempoEnvase() * 10; i++) {
			gpio.inAcumFrascoEmPosicao.isHigh();
			aguarda(100);
		}
	}


	private void ligaBomba() {
		if (gpio.inEnvBombaLigada.isLow())
		{
			gpio.outEnvBombaEnvase.low();
		}
	}

	private void baixarBicosEnvase() throws Exception {
		if (gpio.inEnvFimDeCursoEnvasadoraEmbaixo.isLow())
		{
			gpio.outEnvPistaoEnvaseRecua.high();
			gpio.outEnvPistaoEnvaseAvanca.low();
			while(!gpio.inEnvFimDeCursoEnvasadoraEmbaixo.isHigh())
			{
				if (!comm.isIniciaProducao()) exit();
				aguarda(500);
			}
			
		}
	}

	private void subirBicosEnvase() throws Exception {
		
		if (gpio.inEnvFimDeCursoEnvasadoraEmCima.isLow())
		{
			gpio.outEnvPistaoEnvaseAvanca.high();
			
			gpio.outEnvPistaoEnvaseRecua.low();
			
			while(gpio.inEnvFimDeCursoEnvasadoraEmCima.isLow())
			{
				if (!comm.isIniciaProducao()) exit();
				aguarda(100);
			}
		}
	}

	private void avancarTrava2() {
		if (gpio.outEnvTrava2.isHigh())
		{
			gpio.outEnvTrava2.low();
			aguarda(200);
		}
	}


	private void avancarTrava1() {
		if (gpio.outEnvTrava1.isHigh())
		{
			gpio.outEnvTrava1.low();
			aguarda(200);
		}
	}
	
	private void goHome() throws Exception
	{
		while (!emPosicaoHome())
		{
			desligaBomba();
			pararEsteira1();
			pararEsteira2();
			subirBicosEnvase();
			recuarEnforcador();
			recuarTrava1();
			recuarTrava2();
		}
	}
	
	private void pararEsteira2() {
		gpio.outEnvEsteira2.high();
	}

	private void recuarTrava2() {
		gpio.outEnvTrava2.high();
		aguarda(100);
	}


	private void recuarTrava1() {
		gpio.outEnvTrava1.high();
		aguarda(100);
	}


	private void recuarEnforcador() {
		gpio.outEnvPistaoEnforcador.high();
		aguarda(200);
	}


	private void pararEsteira1() {
		if (!gpio.outEnvEsteira1.isHigh())
		{
			gpio.outEnvEsteira1.high();
			aguarda(200);
		}
	}
	
	private void ligarEsteira1() {
		if (gpio.outEnvEsteira1.isHigh())
		{
			gpio.outEnvEsteira1.low();
			aguarda(200);
		}
	}

	private boolean emPosicaoHome()
	{
		return (gpio.inEnvFimDeCursoEnvasadoraEmCima.isHigh() &&
				gpio.inEnvFimDeCursoEnvasadoraEmbaixo.isLow() &&
				gpio.inEnvBombaLigada.isLow() &&
				gpio.outEnvBombaEnvase.isHigh() &&
				gpio.outEnvEsteira1.isHigh() &&
				gpio.outEnvTrava2.isHigh() &&
				gpio.outEnvTrava1.isHigh()
				);
	}
	
	private void desligaBomba()
	{
		//Deliga a bomba
		gpio.outEnvBombaEnvase.high();

	}
	
	/**
	 * @param tempo
	 * @category valor em milissegundos
	 */
	private void aguarda(int tempo)
	{
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
