package Background.Executor;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
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
		
		actionInicioLinha = new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				tempoAguardoInicioLinha = tempoAguardoInicioLinha + 10;
				if ((tempoAguardoInicioLinha >=3000) && (comm.getFrascosParaEnvasar() > 0))
				{
					finalizaContagemFrascosInicioLinha();
				}
		}};
		
		actionFimLinha = new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				tempoAguardoFimLinha = tempoAguardoFimLinha + 10;
				if (tempoAguardoFimLinha >=3000)
				{
					finalizaContagemFrascosFimLinha();
				}
		}};
		
		timerInicioLinha = new Timer(10, actionInicioLinha);
		timerFimLinha = new Timer(10, actionFimLinha);
	}
	
	
	
	@Override
	public void run() {
		try{
			comm.setAlive(true);
			while(comm.isIniciaProducao())
			{
				
				if (comm.isCicloContinuo())
					ciclo();
				else if (comm.getMetaProducao() > 0)
					ciclo();
				else
					comm.setIniciaProducao(false);
			}
			
		}catch (Exception e) {
			System.out.println("Thread Envase finalizada");
			comm.setCicloContinuo(false);
			comm.setIniciaProducao(false);
		}finally {
			comm.setAlive(false);
		}
	}
	
	private void ciclo()
	{
		goHome();
		
		if (!comm.isInicioRapido())
		{
			comm.setInicioRapido(true);
			buscarFrascos();
		}
		
		envase();
		enviarParaTampador();
		aguarda(1000);
	}


	private void envase()
	{
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
		goHome();
	}
	
	private void buscarFrascos()
	{
		recuarTrava2();
		avancarTrava1();
		ligarEsteira1();
		contarFrascosEntradaLinha();
		while(!contagemInicioLinhaFinalizada)
			aguarda(100);
		avancarTrava2();
		posicionarFrascos();
	}
	
	private void posicionarFrascos() {
		while(gpio.inEnvFrascoSaindoDaAreaEnvase.isLow())
			aguarda(100);
		
		aguarda(1000);
	}

	private void finalizaContagemFrascosInicioLinha()
	{
		avancarTrava2();
		retiraListenerFrascoInicioLinha();
		timerInicioLinha.stop();
		tempoAguardoInicioLinha = 0;
		contagemInicioLinhaFinalizada = true;
	}
	
	private void finalizaContagemFrascosFimLinha()
	{
		avancarTrava1();
		retiraListenerFrascoFimLinha();
		timerFimLinha.stop();
		tempoAguardoFimLinha = 0;
		contagemFimLinhaFinalizada = true;
	}
	
	private void retiraListenerFrascoFimLinha() {
		gpio.inEnvFrascoSaindoDaAreaEnvase.removeAllListeners();
	}

	private void retiraListenerFrascoInicioLinha() {
		gpio.inEnvFrascoEntrandoNaAreaEnvase.removeAllListeners();
	}
	
	private void enviarParaTampador()
	{
		ligarEsteira1();
		ligarEsteira2();
		contarFrascosEntradaLinha();
		contarFrascosSaidaLinha();
		
		while(!contagemFimLinhaFinalizada && !contagemInicioLinhaFinalizada)
		{
			aguarda(100);
		}
		comm.setMetaProducao(comm.getMetaProducao() - comm.getFrascosEnvasado());
		if (comm.getMetaProducao() <= 0)
			comm.setMetaProducao(0);
	}
	
	private void contarFrascosSaidaLinha() {
		comm.setFrascosEnvasado(0);
		contagemFimLinhaFinalizada = false;
		
		gpio.inEnvFrascoSaindoDaAreaEnvase.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            	if (event.getState().isHigh())
                {
                	comm.setFrascosEnvasado(comm.getFrascosEnvasado() + 1);
                	if(comm.getFrascosEnvasado() >= 12)
                	{
                		finalizaContagemFrascosFimLinha();
                	}else
                		tempoAguardoFimLinha = 0;
                }
            }

    	});
		
		timerFimLinha.start();
	}

	private void contarFrascosEntradaLinha() {
		comm.setFrascosParaEnvasar(0);
		contagemInicioLinhaFinalizada = false;
		
		gpio.inEnvFrascoEntrandoNaAreaEnvase.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState().isHigh())
                {
                	comm.setFrascosParaEnvasar(comm.getFrascosParaEnvasar() + 1);
                	if (comm.getFrascosParaEnvasar() >= 12)
                	{
                		finalizaContagemFrascosInicioLinha();
                	}else
                		tempoAguardoInicioLinha = 0;
                	
                }
            }

    	});
		
		timerInicioLinha.start();
		
	}

	private void avancarEnforcador() {
		if (!gpio.outEnvPistaoEnforcador.isHigh())
		{
			gpio.outEnvPistaoEnforcador.high();
			aguarda(500);
		}
	}


	private void aguardaTempoEnvase() {
		aguarda(comm.getTempoEnvase() * 1000);
	}


	private void ligaBomba() {
		if (!gpio.inEnvBombaLigada.isHigh())
		{
			gpio.outEnvBombaEnvase.high();
			aguarda(100);
		}
	}

	private void baixarBicosEnvase() {
		if (!gpio.inEnvFimDeCursoEnvasadoraEmbaixo.isHigh())
		{
			gpio.outEnvPistaoEnvaseAvanca.low();
			aguarda(100);
			gpio.outEnvPistaoEnvaseRecua.high();
			aguarda(100);
			
			while(!gpio.inEnvFimDeCursoEnvasadoraEmbaixo.isHigh())
			{
				aguarda(500);
			}
			
			gpio.outEnvPistaoEnvaseRecua.low();
		}
	}

	private void subirBicosEnvase() {
		
		if (!gpio.inEnvFimDeCursoEnvasadoraEmCima.isHigh())
		{
			gpio.outEnvPistaoEnvaseRecua.low();
			
			aguarda(100);
			gpio.outEnvPistaoEnvaseAvanca.high();
			
			while(!gpio.inEnvFimDeCursoEnvasadoraEmCima.isHigh())
			{
				aguarda(100);
			}
			gpio.outEnvPistaoEnvaseAvanca.low();
		}
	}

	private void avancarTrava2() {
		if (gpio.outEnvTrava2.isLow())
		{
			gpio.outEnvTrava2.high();
			aguarda(200);
		}
	}


	private void avancarTrava1() {
		if (gpio.outEnvTrava1.isLow())
		{
			gpio.outEnvTrava1.high();
			aguarda(200);
		}
	}
	
	private void goHome()
	{
		while (!emPosicaoHome())
		{
			desligaBomba();
			pararEsteira1();
			subirBicosEnvase();
			recuarEnforcador();
			recuarTrava1();
			recuarTrava2();
		}
	}
	
	private void recuarTrava2() {
		gpio.outEnvTrava2.low();
		aguarda(100);
	}


	private void recuarTrava1() {
		gpio.outEnvTrava1.low();
		aguarda(100);
	}


	private void recuarEnforcador() {
		gpio.outEnvPistaoEnforcador.low();
		aguarda(200);
	}


	private void pararEsteira1() {
		if (!gpio.outEnvEsteira1.isLow())
		{
			gpio.outEnvEsteira1.low();
			aguarda(200);
		}
	}
	
	private void ligarEsteira1() {
		
		if (gpio.outEnvTrava1.isHigh())
			JOptionPane.showMessageDialog(null, "A trava 1 ainda esta acionada");
		
		if (gpio.outEnvTrava2.isHigh())
			JOptionPane.showMessageDialog(null, "A trava 2 ainda esta acionada");
		
		
		if (gpio.outEnvEsteira1.isLow())
		{
			gpio.outEnvEsteira1.high();
			aguarda(200);
		}
	}

	private void ligarEsteira2() {
		
		if (gpio.outEnvEsteira2.isLow())
		{
			gpio.outEnvEsteira2.high();
			aguarda(200);
		}
	}




	private boolean emPosicaoHome()
	{
		return (gpio.inEnvFimDeCursoEnvasadoraEmCima.isHigh() &&
				gpio.inEnvFimDeCursoEnvasadoraEmbaixo.isLow() &&
				gpio.inEnvFimDeCursoEnforcadorAvancado.isLow() &&
				gpio.inEnvBombaLigada.isLow() &&
				gpio.inEnvFrascoSaindoDaAreaEnvase.isHigh() &&
				gpio.inEnvProdutoNivelCritico.isLow() &&
				gpio.outEnvBombaEnvase.isLow() &&
				gpio.outEnvEsteira1.isLow() &&
				gpio.outEnvTrava2.isLow() &&
				gpio.outEnvTrava1.isLow()
				);
	}
	
	private void desligaBomba()
	{
		//Deliga a bomba
		gpio.outEnvBombaEnvase.low();
		//Aguarda desligar a bomba
		while(!gpio.inEnvBombaLigada.isLow())
		{
			aguarda(100);
		}
		
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
