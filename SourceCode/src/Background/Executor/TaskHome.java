package Background.Executor;

import Background.Services.CommHome;
import Background.Services.GPIO;

public class TaskHome implements Runnable {
	public GPIO gpio;
	private CommHome comm;
	
	public TaskHome(CommHome comm, GPIO gpio)
	{
		this.comm = comm;
		this.gpio = gpio;
	}

	@Override
	public void run() {
		ciclo();
	}

	private void ciclo() {
		while(true)
		{
			if (comm.isGoHome())
			{
				comm.setGoGome(false);
				comm.setFinalizado(false);
				
				pararEsteira1();
				desligarBomba();
				subirBicos();
				recuarEnforcador();
				abrirTrava1();
				abrirTrava2();
				
				comm.setFinalizado(true);
			}
			aguardar(1000);
		}
	}
	
	private void aguardar(int i)
	{
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void abrirTrava2() {
		if (gpio.outEnvTrava2.isHigh())
		{
			gpio.outEnvTrava2.low();
			aguardar(100);
		}
	}

	private void abrirTrava1() {
		if (gpio.outEnvTrava1.isHigh())
		{
			gpio.outEnvTrava1.low();
			aguardar(100);
		}
	}
	
	private void recuarEnforcador() {
		if (gpio.outEnvPistaoEnforcador.isHigh())
		{
			gpio.outEnvPistaoEnforcador.low();
			aguardar(100);
		}
	}

	private void desligarBomba() {
		if (gpio.inEnvBombaLigada.isHigh())
		{
			gpio.outEnvBombaEnvase.low();
			while(gpio.inEnvBombaLigada.isHigh())
			{
				aguardar(100);
			}
		}
	}

	private void subirBicos() {
		if (gpio.inEnvFimDeCursoEnvasadoraEmbaixo.isHigh())
		{
			gpio.outEnvPistaoEnvaseRecua.low();
			gpio.outEnvPistaoEnvaseAvanca.high();
			
			while(gpio.inEnvFimDeCursoEnvasadoraEmCima.isLow())
			{
				aguardar(100);
			}
		}
	}

	private void pararEsteira1() {
		if (!gpio.outEnvEsteira1.isHigh())
		{
			gpio.outEnvEsteira1.low();
			aguardar(200);
		}
	}
}
