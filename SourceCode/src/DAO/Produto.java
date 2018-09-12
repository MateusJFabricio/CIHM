package DAO;

public class Produto {
	private int Id;
	private String Nome;
	private int TempoEnvase; //tempo em que a bomba fica ligada
	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public int getTempoEnvase() {
		return TempoEnvase;
	}
	public void setTempoEnvase(int tempoEnvase) {
		TempoEnvase = tempoEnvase;
	}
	public int getId() {
		return Id;
	}
	public void setId(int Id) {
		this.Id = Id;
	}
	
}
