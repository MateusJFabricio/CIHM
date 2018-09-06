package Background.Executor;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

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
		adicionarListener();
	}


	private void adicionarListener() {
		gpio.inEnvBotaoEmergenciaAcionado.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            	if (event.getState().isHigh())
                {
            		comm.setEmEmergencia(true);
                	iniciaCicloEmergencia();
                }
            }

    	});
	}


	protected void iniciaCicloEmergencia() {
		while(!comm.isGPIOLiberada())
		{
			aguardar(20);
		}
		
		desabilitarBomba();
		bloquearPistaoPrincipal();
		desligarEsteiras();
		pararAcumulador();
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
