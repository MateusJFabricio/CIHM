package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class CommBD {
	private Connection conexaoBD;
	
	public java.sql.Statement getStatement() {
		String URL = "jdbc:sqlite:ClaraMaq_PRD.db";
		
		java.sql.Statement statement = null;
		conexaoBD = null;
        try {
        	conexaoBD = DriverManager.getConnection(URL);
        	statement = conexaoBD.createStatement();
        } catch (SQLException e) {
        	 JOptionPane.showMessageDialog(null,"Nao foi possivel estabelecer conexao com o banco de dados. A aplicacao sera encerrada. Motivo: " + e.getMessage());
        	 System.exit(0);
        }
		return statement;
	}
	
	public void desconectar()
	{
		try
        {
			conexaoBD.close();
        }
        catch(SQLException fecha)
        {
       	 JOptionPane.showMessageDialog(null, "Houve um problema com o banco de dados. Problema: " +  fecha.getMessage());
       	 System.exit(0);
        }
	}
}
