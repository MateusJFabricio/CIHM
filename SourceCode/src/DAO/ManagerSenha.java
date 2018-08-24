package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ManagerSenha {
	private CommBD commBd;
	private Statement query;
	
	
	public ManagerSenha()
	{
		commBd = new CommBD();
		query = commBd.getStatement();
	}
	
	public Senha selectSenha()
	{
		Senha senha = new Senha();
		try {
			ResultSet resultado = query.executeQuery("SELECT SenhaMaster, SenhaSair FROM Maquina LIMIT 1");
			
			senha.setSenhaGeral(resultado.getString("SenhaSair"));
			senha.setSenhaMaster(resultado.getString("SenhaMaster"));
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Houve um problema com o banco de dados. Problema: " +  e.getMessage());
			System.exit(0);
		}
		return senha;
	}
	
	public void desconectar()
    {
		commBd.desconectar();
    }
}
