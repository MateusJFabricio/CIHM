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
		try {
			comm.setAlive(true);
			ciclo();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			comm.setAlive(false);
		}
	}

	private void ciclo() throws Exception {
		if (comm.isGoHome())
		{
			comm.setGoGome(false);
			comm.setFinalizado(false);
			
			pararEsteira1();
			pararEsteira2();
			recuarTampador();
			desligarAcumulador();
			desligarBomba();
			subirBicos();
			recuarEnforcador();
			abrirTrava1();
			abrirTrava2();
			
			comm.setFinalizado(true);
		}
		aguardar(1000);
	}
	
	private void desligarAcumulador() {
		gpio.outAcumMotor.high();
		aguardar(100);
	}

	private void recuarTampador() {
		gpio.outTampTampador.high();
		aguardar(100);
	}

	private void pararEsteira2() {
		gpio.outEnvEsteira2.high();
		aguardar(100);
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
		gpio.outEnvTrava2.high();
		aguardar(100);
	}

	private void abrirTrava1() {
		gpio.outEnvTrava1.high();
		aguardar(100);
	}
	
	private void recuarEnforcador() {
		gpio.outEnvPistaoEnforcador.high();
		aguardar(100);
	}

	private void desligarBomba() throws Exception {
		gpio.outEnvBombaEnvase.high();
	}
	

	private void subirBicos() throws Exception {
		int tempo = 5000, i = 0;
		if (!gpio.inEnvFimDeCursoEnvasadoraEmCima.isHigh())
		{
			gpio.outEnvPistaoEnvaseAvanca.high();
			gpio.outEnvPistaoEnvaseRecua.low();
			
			while(gpio.inEnvFimDeCursoEnvasadoraEmCima.isLow())
			{
				i += 100;
				if (i >= tempo) sair();
				aguardar(100);
			}
		}
	}
	
	
	private void sair() throws Exception {
		throw new Exception("Sair da rotina por emergencia acionado");
	}

	private void pararEsteira1() {
		gpio.outEnvEsteira1.high();
		aguardar(100);
	}
}
