package DAO;

public class LogFalhas {
	private int id;
	private String dataHora;
	private String falha;
	private String visto;
	
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	public String getFalha() {
		return falha;
	}
	public void setFalha(String falha) {
		this.falha = falha;
	}
	public String getVisto() {
		return visto;
	}
	public void setVisto(String visto) {
		this.visto = visto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
