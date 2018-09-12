package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ManagerMaquina {
	private java.sql.Statement query;
	
	public ManagerMaquina(java.sql.Statement query)
	{
		this.query = query;
	}
	
	public Maquina selectMaquina()
	{
		Maquina maq = new Maquina();
		try {
			ResultSet resultado = query.executeQuery("SELECT Maquina.id, Maquina.Modelo, Maquina.EstadoAtual, "
													+ "EstadoMaquina.Estado, Maquina.TempoMedioCiclo, "
													+"Maquina.PeriodicidadeManutencao, Maquina.ProximaManutencao, "
													+"Maquina.DataUltimaManutencao FROM Maquina INNER JOIN EstadoMaquina "
													+ "ON Maquina.EstadoAtual = EstadoMaquina.id LIMIT 1");
			
			maq.setId(resultado.getInt("id"));
			maq.setModelo(resultado.getString("Modelo"));
			maq.setFK_EstadoAtual(resultado.getInt("EstadoAtual"));
			maq.setEstadoAtual(resultado.getString("Estado"));
			maq.setTempoMedioCiclo(resultado.getInt("TempoMedioCiclo"));
			maq.setPeriodicidadeManutencao(resultado.getInt("PeriodicidadeManutencao"));
			maq.setProximaManutencao(resultado.getString("ProximaManutencao"));
			maq.setDataUltimaManutencao(resultado.getString("DataUltimaManutencao"));
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Houve um problema com o banco de dados. Problema: " +  e.getMessage());
			System.exit(0);
		}
		return maq;
	}
	
}
