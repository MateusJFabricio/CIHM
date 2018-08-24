package DAO;

public class LogAlteracao {
	private int id;
	private String DataHora;
	private String Alteracao;
	
	public String getDataHora() {
		return DataHora;
	}
	public void setDataHora(String dataHora) {
		DataHora = dataHora;
	}
	public String getAlteracao() {
		return Alteracao;
	}
	public void setAlteracao(String alteracao) {
		Alteracao = alteracao;
	}
	public int getId() {
		return id;
	}
}
