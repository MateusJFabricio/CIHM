package Background.Executor;

import Background.ControllerIO;
import Background.Services.CommEmergencia;
import Background.Services.GPIO;

public class TaskEmergencia implements Runnable {
	private CommEmergencia comm;
	private ControllerIO controlIO;
	private GPIO gpio;
	
	
	public TaskEmergencia(CommEmergencia comm, ControllerIO controlIO)
	{
		this.controlIO = controlIO;
		this.gpio = controlIO.getGpio();
		this.comm = comm;
	}


	@Override
	public void run() {
		while(true)
		{
			if (gpio.inEnvBotaoEmergenciaAcionado.isHigh())
			{
				gpio = controlIO.getGpioEmergencia();
				comm.setEmEmergencia(true);
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
			aguardar(20);
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
