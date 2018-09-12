package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerEstadoMaquina {
	private java.sql.Statement query;
	
	
	public ManagerEstadoMaquina (java.sql.Statement query)
	{
		this.query = query;
	}
	
	public Maquina selectMaquina()
	{
		Maquina maq = new Maquina();  
		ResultSet rs;

		try {
			rs = query.executeQuery("SELECT * FROM Maquina LIMIT 1");

			while (rs.next()) {
				maq.setId(rs.getInt("id"));
				maq.setModelo(rs.getString("Modelo"));
				maq.setFK_EstadoAtual(rs.getInt("EstadoAtual"));
				//maq.setEstadoAtual(rs.getString(("DescEstadoAtual"))); //------
				maq.setTempoMedioCiclo(rs.getInt("TempoMedioCiclo"));
				maq.setPeriodicidadeManutencao(rs.getInt("PeriodicidadeManutencao"));
				maq.setProximaManutencao(rs.getString("ProximaManutencao"));
				maq.setDataUltimaManutencao(rs.getString("DataUltimaManutencao"));
				maq.setSenhaSair(rs.getString("SenhaSair"));
				maq.setSenhaMaster(rs.getString("SenhaMaster"));
				
			}

			rs.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return maq;
	}
	
}
