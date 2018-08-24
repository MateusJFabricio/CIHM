package DAO;

import java.sql.*;
import java.util.Vector;

public class ManagerBD {
	private String URLConexao = "jdbc:sqlite:C:/Users/Mateus/Desktop/ClaraMaq/2 - Projetos/8 - Envasadora Sany do Brasil/6 - Software/Banco de dados/ClaraMaq_PRD.db";
	
	private Connection conn;
	private Statement stm;
	
	public ManagerBD() throws SQLException, ClassNotFoundException
	{
		Class.forName("org.sqlite.JDBC");
		this.conn = DriverManager.getConnection(URLConexao);
		this.stm = this.conn.createStatement();
	}

	public void insertProduto(Produto produto) {		  
		try {		  
			this.stm = this.conn.createStatement();

			this.stm.executeUpdate("INSERT INTO Produto("
					+ "	Nome, "
					+ "TempoEnvase, "
					+ "TempoRetardoRetorno, "
					+ "VelocidadeEsteira, "
					+ "VelocidadeEnvase, "
					+ "PassoTampador, "
					+ "DelayInicioCiclo) "
					+ "VALUES ("
					+ produto.getNome() + ","
					+ produto.getTempoEnvase() + ","
					+ produto.getTempoRetardoRetorno() + ","
					+ produto.getVelocidadeEsteira() + ","
					+ produto.getVelocidadeEnvase() + ","
					+ produto.getPassoTampador() + ","
					+ produto.getDelayInicioCiclo() + ")");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeProduto(Produto produto) {		  
		try {		  
			this.stm = this.conn.createStatement();

			this.stm.executeUpdate("DELETE FROM Produto WHERE id = " + produto.getId());
		}
		catch (SQLException e) {
			e.printStackTrace();  
		}  
	}

	public Vector<Produto> getAllProduto() {
		Vector<Produto> lista = new Vector<Produto>();	  
		ResultSet rs;

		try {
			rs = this.stm.executeQuery("SELECT * FROM Produto ORDER BY Nome");

			while (rs.next()) {
				Produto prod = new Produto();
				prod.setNome(rs.getString("Nome"));
				prod.setTempoEnvase(rs.getInt("TempoEnvase"));
				prod.setTempoRetardoRetorno(rs.getInt("TempoRetardoRetorno"));
				prod.setVelocidadeEsteira(rs.getInt("VelocidadeEsteira"));
				prod.setVelocidadeEnvase(rs.getInt("VelocidadeEnvase"));
				prod.setPassoTampador(rs.getInt("PassoTampador"));
				prod.setDelayInicioCiclo(rs.getInt("DelayInicioCiclo"));
				
				lista.add(prod);
			}

			rs.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	public Maquina selectMaquina()
	{
		Maquina maq = new Maquina();  
		ResultSet rs;

		try {
			rs = this.stm.executeQuery("SELECT * FROM Maquina LIMIT 1");

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
