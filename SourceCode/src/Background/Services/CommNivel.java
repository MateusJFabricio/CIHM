package Background.Services;

public class CommNivel {
	private boolean nivelBaixo = false;
	private boolean nivelAlto = false;
	private boolean monitorar = false;
	public boolean isNivelBaixo() {
		return nivelBaixo;
	}
	public void setNivelBaixo(boolean nivelBaixo) {
		this.nivelBaixo = nivelBaixo;
	}
	public boolean isNivelAlto() {
		return nivelAlto;
	}
	public void setNivelAlto(boolean nivelAlto) {
		this.nivelAlto = nivelAlto;
	}
	public boolean isMonitorar() {
		return monitorar;
	}
	public void setMonitorar(boolean monitorar) {
		this.monitorar = monitorar;
	}
}
