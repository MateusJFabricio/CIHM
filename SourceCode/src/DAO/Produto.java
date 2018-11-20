package DAO;

public class Produto {
	private int Id;
	private String Nome;
	private int TempoEnvase; //tempo em que a bomba fica ligada
	private int DelayInicioProd;
	private int DelayPosEnvase;
	
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
	public int getDelayInicioProd() {
		return DelayInicioProd;
	}
	public void setDelayInicioProd(int delayInicioProd) {
		DelayInicioProd = delayInicioProd;
	}
	public int getDelayPosEnvase() {
		return DelayPosEnvase;
	}
	public void setDelayPosEnvase(int delayPosEnvase) {
		DelayPosEnvase = delayPosEnvase;
	}
	
}
