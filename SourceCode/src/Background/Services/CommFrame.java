package Background.Services;

public class CommFrame {
	private int Produzido;
	private boolean emergencia;
	private boolean nivelBaixo;
	private boolean nivelAlto;
	public int getProduzido() {
		return Produzido;
	}
	public void setProduzido(int produzido) {
		Produzido = produzido;
	}
	public boolean isEmergencia() {
		return emergencia;
	}
	public void setEmergencia(boolean emergencia) {
		this.emergencia = emergencia;
	}
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
}
