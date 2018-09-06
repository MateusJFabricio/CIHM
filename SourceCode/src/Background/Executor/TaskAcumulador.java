package Background.Executor;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import Background.Services.CommAcumulador;
import Background.Services.GPIO;

public class TaskAcumulador implements Runnable {
	private CommAcumulador comm;
	private GPIO gpio;
	protected boolean frascoSemGirar;
	
	public TaskAcumulador(CommAcumulador comm, GPIO gpio)
	{
		this.comm = comm;
		this.gpio = gpio;
	}

	@Override
	public void run() {
		adicionarListenerFrasco();
	}

	private void adicionarListenerFrasco() {
		gpio.inAcumFrascoEmPosicao.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            	if (event.getState().isHigh())
                {
            		comm.setFrascosEmTransito(comm.getFrascosEmTransito() - 1);
                	girarAcumulador();
                }
            	
            	if (event.getState().isLow())
            	{
            		if (comm.getFrascosEmTransito() == 0)
            		{
            			gpio.outEnvEsteira2.low();
            		}
            	}
            }

    	});
	}
	
	private void girarAcumulador()
	{
		gpio.outAcumMotor.low();
		gpio.outAcumMotor.pulse(4000, true);
	}
	
}
