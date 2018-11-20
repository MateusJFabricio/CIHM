package Background.Executor;

import java.io.IOException;

import javax.swing.JOptionPane;

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
			/*
			if (gpio.inEnvBateria.isLow())
			{
				aguardar(500);
				if (gpio.inEnvBateria.isLow())
				{
					try {
						aguardar(1000 * 60);
						if (gpio.inEnvBateria.isLow())
						{
							Main.Main.mannIO.goHome();
							Main.Main.mannIO.gpio.getGpio().shutdown();
							Runtime.getRuntime().exec("shutdown -h now");
							JOptionPane.showMessageDialog(null, "Desligando");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			*/
			if (gpio.inEnvBotaoEmergenciaAcionado.isLow() && !comm.isEmEmergencia())
			{
				aguardar(500);
				if (gpio.inEnvBotaoEmergenciaAcionado.isLow())
				{
					comm.setEmEmergencia(true);
				}
			}
			
			if (comm.isEmEmergencia())// && comm.isGPIOLiberada())
				iniciaCicloEmergencia();
			
			if (comm.isEmEmergencia() && gpio.inEnvBotaoEmergenciaAcionado.isHigh())
				comm.setEmEmergencia(false);
		}
	}

	protected void iniciaCicloEmergencia() {
		desabilitarBomba();
		bloquearPistaoPrincipal();
		desligarEsteiras();
		pararAcumulador();
		pararTampador();
	}
	
	private void pararTampador() {
		gpio.outTampMotor.high();
	}

	private void pararAcumulador() {
		gpio.outAcumMotor.high();
	}


	private void desligarEsteiras() {
		gpio.outEnvEsteira1.high();
		gpio.outEnvEsteira2.high();
	}


	private void bloquearPistaoPrincipal() {
		gpio.outEnvPistaoEnvaseAvanca.high();
		gpio.outEnvPistaoEnvaseRecua.high();
	}


	private void desabilitarBomba() {
		gpio.outEnvBombaEnvase.high();
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
