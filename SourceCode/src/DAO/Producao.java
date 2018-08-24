package DAO;

public class Producao {
	private int Id;
	private int Fk_Produto;
	private String DataProducao; //Grava quando inicia a producao
	private int Meta; //Grava a meta quando inicia a producao, se for producao planejada
	private String TipoProducao; //Planejada ou continua
	private int Produzido; //Quantas unidades foram produzidas
	private int TempoProducao; //Tempo gasto para produzir o total
	private int MediaProducao; //Tempo Médio de produção de cada unidade
	
	public int getFk_Produto() {
		return Fk_Produto;
	}
	public void setFk_Produto(int fk_Produto) {
		Fk_Produto = fk_Produto;
	}
	public String getDataProducao() {
		return DataProducao;
	}
	public void setDataProducao(String dataProducao) {
		DataProducao = dataProducao;
	}
	public int getMeta() {
		return Meta;
	}
	public void setMeta(int meta) {
		Meta = meta;
	}
	public String getTipoProducao() {
		return TipoProducao;
	}
	public void setTipoProducao(String tipoProducao) {
		TipoProducao = tipoProducao;
	}
	public int getProduzido() {
		return Produzido;
	}
	public void setProduzido(int produzido) {
		Produzido = produzido;
	}
	public int getTempoProducao() {
		return TempoProducao;
	}
	public void setTempoProducao(int tempoProducao) {
		TempoProducao = tempoProducao;
	}
	public int getMediaProducao() {
		return MediaProducao;
	}
	public void setMediaProducao(int mediaProducao) {
		MediaProducao = mediaProducao;
	}
	public int getId() {
		return Id;
	}
}
