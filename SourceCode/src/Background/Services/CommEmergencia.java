package Background.Services;

public class CommEmergencia {
	private boolean emEmergencia;
	private boolean GPIOLiberada = false;

	public boolean isEmEmergencia() {
		return emEmergencia;
	}

	public void setEmEmergencia(boolean emEmergencia) {
		this.emEmergencia = emEmergencia;
	}

	public void setGPIOLiberada(boolean v) {
		GPIOLiberada = v;
	}
	
	public boolean isGPIOLiberada(){
		return GPIOLiberada;
	}
}
