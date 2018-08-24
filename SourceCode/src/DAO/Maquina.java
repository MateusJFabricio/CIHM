package DAO;

public class Maquina {
	private int Id;
	private String Modelo;
	private int FK_EstadoAtual; //fk para tabela EstadoMaquina
	private String EstadoAtual;
	private int TempoMedioCiclo; //Media em segundos do tempo de ciclo dos ultimos 10 ciclos
	private int PeriodicidadeManutencao; //De quantos em quantos dias deve ser dado manutenção na máquina
	private String ProximaManutencao; //Data da próxima manutenção, baseado na periodicidade, e na data da ultima manutenção
	private String DataUltimaManutencao; //Data em que foi feita a ultima manutenção
	private String SenhaSair; //Senha para sair do SO
	private String SenhaMaster; //Senha para o usuario master
	public String getModelo() {
		return Modelo;
	}
	public void setModelo(String modelo) {
		Modelo = modelo;
	}
	public int getFK_EstadoAtual() {
		return FK_EstadoAtual;
	}
	public void setFK_EstadoAtual(int fK_EstadoAtual) {
		FK_EstadoAtual = fK_EstadoAtual;
	}
	public int getTempoMedioCiclo() {
		return TempoMedioCiclo;
	}
	public void setTempoMedioCiclo(int tempoMedioCiclo) {
		TempoMedioCiclo = tempoMedioCiclo;
	}
	public int getPeriodicidadeManutencao() {
		return PeriodicidadeManutencao;
	}
	public void setPeriodicidadeManutencao(int periodicidadeManutencao) {
		PeriodicidadeManutencao = periodicidadeManutencao;
	}
	public String getProximaManutencao() {
		return ProximaManutencao;
	}
	public void setProximaManutencao(String proximaManutencao) {
		ProximaManutencao = proximaManutencao;
	}
	public String getDataUltimaManutencao() {
		return DataUltimaManutencao;
	}
	public void setDataUltimaManutencao(String dataUltimaManutencao) {
		DataUltimaManutencao = dataUltimaManutencao;
	}
	public String getSenhaSair() {
		return SenhaSair;
	}
	public void setSenhaSair(String senhaSair) {
		SenhaSair = senhaSair;
	}
	public String getSenhaMaster() {
		return SenhaMaster;
	}
	public void setSenhaMaster(String senhaMaster) {
		SenhaMaster = senhaMaster;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id= id;
	}
	public String getEstadoAtual() {
		return EstadoAtual;
	}
	public void setEstadoAtual(String estadoAtual) {
		EstadoAtual = estadoAtual;
	}
}
