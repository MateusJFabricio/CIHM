package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ManagerProduto {
	private java.sql.Statement query;
	
	public ManagerProduto(java.sql.Statement query)
	{
		this.query = query;
	}
	
	public void insertProduto(Produto produto) {		  
		try {		  
			query.executeUpdate("INSERT INTO Produto("
					+ "	Nome, "
					+ "TempoEnvase,"
					+ "DelayInicioProd) "
					+ "VALUES ('" 
					+ produto.getNome() + "',"
					+ produto.getTempoEnvase()+ ","
					+ produto.getDelayInicioProd() + " )");
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void removeProduto(Produto produto) {		  
		try {		  
			query.executeUpdate("DELETE FROM Produto WHERE id = " + produto.getId());
			
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());  
		}  
	}

	public ArrayList<Produto> getAllProduto() {
		ArrayList<Produto> lista = new ArrayList<Produto>();	  
		ResultSet rs;

		try {
			rs = query.executeQuery("SELECT * FROM Produto ORDER BY Nome");

			while (rs.next()) {
				Produto prod = new Produto();
				prod.setId(rs.getInt("Id"));
				prod.setNome(rs.getString("Nome"));
				prod.setTempoEnvase(rs.getInt("TempoEnvase"));
				prod.setDelayInicioProd(rs.getInt("DelayInicioProd"));
				
				lista.add(prod);
			}

			rs.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
}
