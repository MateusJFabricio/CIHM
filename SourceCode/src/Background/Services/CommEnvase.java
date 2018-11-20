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
	private int frascoEnviadoTampamento;
	private boolean nivelBaixo = false;
	private boolean nivelAlto = false;
	private int delayInicioProd = 3;
	private int delayPosEnvase = 0;
	
	
	public int getTempoEnvase()
	{
		return tempoEnvase;
	}
	
	public void setTempoEnvase(int tempo)
	{
		tempoEnvase = tempo;
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

	public int getFrascoEnviadoTampamento() {
		return frascoEnviadoTampamento;
	}

	public void setFrascoEnviadoTampamento(int frascoEnviadoTampamento) {
		this.frascoEnviadoTampamento = frascoEnviadoTampamento;
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

	public int getDelayInicioProd() {
		return delayInicioProd;
	}

	public void setDelayInicioProd(int delayInicioProd) {
		this.delayInicioProd = delayInicioProd;
	}

	public int getDelayPosEnvase() {
		return delayPosEnvase;
	}

	public void setDelayPosEnvase(int delayPosEnvase) {
		this.delayPosEnvase = delayPosEnvase;
	}
}
