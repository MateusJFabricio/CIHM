package Background.Services;

import DAO.Produto;

public class CommEnvase {
	private boolean inicioRapido;
	private boolean iniciaProducao;
	private boolean continuaProducaoAtual;
	private boolean pararProducao;
	private Produto produto;
	private String statusAtual;
	private boolean cicloContinuo;
	private int tempoEnvase;
	private int frascosEnvasado = 0;
	private int frascosParaEnvasar = 0;
	private int metaProducao = 0;
	private boolean isAlive = false;
	
	public int getTempoEnvase()
	{
		return tempoEnvase;
	}
	
	public boolean isInicioRapido() {
		return inicioRapido;
	}

	public void setInicioRapido(boolean inicioRapido) {
		this.inicioRapido = inicioRapido;
	}

	public boolean isIniciaProducao() {
		return iniciaProducao;
	}

	public void setIniciaProducao(boolean iniciaProducao) {
		this.iniciaProducao = iniciaProducao;
	}

	public boolean isContinuaProducaoAtual() {
		return continuaProducaoAtual;
	}

	public void setContinuaProducaoAtual(boolean continuaProducaoAtual) {
		this.continuaProducaoAtual = continuaProducaoAtual;
	}

	public boolean isPararProducao() {
		return pararProducao;
	}

	public void setPararProducao(boolean pararProducao) {
		this.pararProducao = pararProducao;
	}

	public Produto getProduto() {
		return produto;
	}

	/**
	 * @param produto
	 * @category valor em segundos
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
		this.tempoEnvase = produto.getTempoEnvase();
	}

	public String getStatusAtual() {
		return statusAtual;
	}

	public void setStatusAtual(String statusAtual) {
		this.statusAtual = statusAtual;
	}

	public boolean isCicloContinuo() {
		return cicloContinuo;
	}

	public void setCicloContinuo(boolean cicloContinuo) {
		this.cicloContinuo = cicloContinuo;
	}

	public int getFrascosEnvasado() {
		return frascosEnvasado;
	}

	public void setFrascosEnvasado(int frascosEnvasado) {
		this.frascosEnvasado = frascosEnvasado;
	}

	public int getFrascosParaEnvasar() {
		return frascosParaEnvasar;
	}

	public void setFrascosParaEnvasar(int frascosParaEnvasar) {
		this.frascosParaEnvasar = frascosParaEnvasar;
	}

	public int getMetaProducao() {
		return metaProducao;
	}

	public void setMetaProducao(int metaProducao) {
		this.metaProducao = metaProducao;
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
