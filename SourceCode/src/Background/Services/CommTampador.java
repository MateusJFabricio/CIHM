package Background.Services;

public class CommTampador {
	private int frascosParaTampar = 0;
	private int frascosNoCarrossel = 0;
	private int frascosTampados = 0;
	private int tempoGiroCarrossel = 0;
	
	public int getFrascosParaTampar() {
		return frascosParaTampar;
	}

	public void setFrascosParaTampar(int frascosParaTampar) {
		this.frascosParaTampar = frascosParaTampar;
	}

	public int getFrascosNoCarrossel() {
		return frascosNoCarrossel;
	}

	public void setFrascosNoCarrossel(int frascosNoCarrossel) {
		this.frascosNoCarrossel = frascosNoCarrossel;
	}

	public int getFrascosTampados() {
		return frascosTampados;
	}
	
	/**
	 * @param frascosTampados
	 * @category tempo em  segundos
	 */
	public void setFrascosTampados(int frascosTampados) {
		this.frascosTampados = frascosTampados;
	}

	public int getTempoGiroCarrossel() {
		return tempoGiroCarrossel;
	}

	public void setTempoGiroCarrossel(int tempoGiroCarrossel) {
		this.tempoGiroCarrossel = tempoGiroCarrossel;
	}
}
