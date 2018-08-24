package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ManagerProduto {
	private java.sql.Statement query;
	private CommBD commDb;
	
	public ManagerProduto()
	{
		commDb = new CommBD();
	}
	
	@Override
	public void finalize() {
		commDb.desconectar();
	}
	
	public ArrayList<Produto> getAll()
	{
		ArrayList<Produto> listProd = new ArrayList<>();
		try {
			if (query == null) 
				query = commDb.getStatement();
			
			ResultSet resultado = query.executeQuery("SELECT * FROM Produto ORDER BY Nome DESC");
			while (resultado.next())
			{
				Produto prod = new Produto();
				prod.setNome(resultado.getString("Nome"));
				prod.setDelayInicioCiclo(resultado.getInt("DelayInicioCiclo"));
				prod.setPassoTampador(resultado.getInt("PassoTampador"));
				prod.setTempoEnvase(resultado.getInt("tempoEnvase"));
				prod.setTempoRetardoRetorno(resultado.getInt("tempoRetardoRetorno"));
				prod.setVelocidadeEnvase(resultado.getInt("VelocidadeEnvase"));
				prod.setVelocidadeEsteira(resultado.getInt("VelocidadeEsteira"));
				prod.setId(resultado.getInt("Id"));
				listProd.add(prod);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Houve um problema com o banco de dados. Problema: " +  e.getMessage());
			System.exit(0);
		}
		finally {
			commDb.desconectar();
			query = null;
		}
		
		return listProd;
	}
}
