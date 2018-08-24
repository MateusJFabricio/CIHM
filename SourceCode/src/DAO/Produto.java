package DAO;

public class Produto {
	private int Id;
	private String Nome;
	private int TempoEnvase; //tempo em que a bomba fica ligada
	private int TempoRetardoRetorno; //Tempo em que se espera para a maquina subir os bicos
	private int VelocidadeEsteira;
	private int VelocidadeEnvase; //Velocidade da bomba
	private int PassoTampador; //Quantidade de passos do motor de passos do tampador
	private int DelayInicioCiclo; //Tempo antes de iniciar o ciclo (pode ser zerado)
	
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
	public int getTempoRetardoRetorno() {
		return TempoRetardoRetorno;
	}
	public void setTempoRetardoRetorno(int tempoRetardoRetorno) {
		TempoRetardoRetorno = tempoRetardoRetorno;
	}
	public int getVelocidadeEsteira() {
		return VelocidadeEsteira;
	}
	public void setVelocidadeEsteira(int velocidadeEsteira) {
		VelocidadeEsteira = velocidadeEsteira;
	}
	public int getVelocidadeEnvase() {
		return VelocidadeEnvase;
	}
	public void setVelocidadeEnvase(int velocidadeEnvase) {
		VelocidadeEnvase = velocidadeEnvase;
	}
	public int getPassoTampador() {
		return PassoTampador;
	}
	public void setPassoTampador(int passoTampador) {
		PassoTampador = passoTampador;
	}
	public int getDelayInicioCiclo() {
		return DelayInicioCiclo;
	}
	public void setDelayInicioCiclo(int delayInicioCiclo) {
		DelayInicioCiclo = delayInicioCiclo;
	}
	public int getId() {
		return Id;
	}
	public void setId(int Id) {
		this.Id = Id;
	}
	
}
