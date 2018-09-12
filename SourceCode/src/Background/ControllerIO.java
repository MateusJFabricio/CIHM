package Background;

import Background.Services.GPIO;

public class ControllerIO {
	private GPIO gpio;
	private GPIO gpioEmergencia;
	private boolean emergencia;
	
	public ControllerIO()
	{
		gpio = new GPIO();
	}

	public boolean isEmergencia() {
		return emergencia;
	}

	public void setEmergencia(boolean emergencia) {
		this.emergencia = emergencia;
	}

	public GPIO getGpioEmergencia() {
		if (gpioEmergencia == null)
		{
			gpio.finalize();
			gpioEmergencia = new GPIO();
		}
		
		return gpioEmergencia;
	}
	
	public GPIO getGpio() {
		if (gpio == null)
		{
			gpioEmergencia.finalize();
			gpio = new GPIO();
		}
			
		return gpio;
	}
}
