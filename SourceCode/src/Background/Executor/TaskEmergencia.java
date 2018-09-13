package Background.Executor;

import Background.Services.CommEmergencia;
import Background.Services.GPIO;

public class TaskEmergencia implements Runnable {
	private CommEmergencia comm;
	private GPIO gpio;
	
	
	public TaskEmergencia(CommEmergencia comm, GPIO gpio)
	{
		this.gpio = gpio;
		this.comm = comm;
	}


	@Override
	public void run() {
		while(true)
		{
			if (gpio.inEnvBotaoEmergenciaAcionado.isHigh())
			{
				comm.setEmEmergencia(true);
				while(!comm.isGPIOLiberada())
					aguardar(10);
				
				iniciaCicloEmergencia();	
			}
		}
	}

	protected void iniciaCicloEmergencia() {
		desabilitarBomba();
		bloquearPistaoPrincipal();
		desligarEsteiras();
		pararAcumulador();
		
		while(gpio.inEnvBotaoEmergenciaAcionado.isLow())
			aguardar(200);
	}
	
	private void pararAcumulador() {
		gpio.outAcumMotor.low();
	}


	private void desligarEsteiras() {
		gpio.outEnvEsteira1.low();
		gpio.outEnvEsteira2.low();
	}


	private void bloquearPistaoPrincipal() {
		gpio.outEnvPistaoEnvaseAvanca.low();
		gpio.outEnvPistaoEnvaseRecua.low();
	}


	private void desabilitarBomba() {
		gpio.outEnvBombaEnvase.low();
	}


	private void aguardar(int i)
	{
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
