package DAO;

import java.sql.*;

import javax.swing.JOptionPane;

public class ManagerBD {
	private String URLConexao = "jdbc:sqlite:ClaraMaq_PRD.db";
	
	private Connection conn;
	private Statement stm;
	
	public ManagerProduto mannProduto;
	public ManagerMaquina mannMaquina;
	public ManagerEstadoMaquina mannEstadoMaquina;
	public ManagerLogAlteracao mannLogAlteracoes;
	public ManagerLogFalhas mannLogFalhas;
	public ManagerProducao mannProducao;
	public ManagerSenha mannSenha;
	
	
	public ManagerBD()
	{
		
		try {
			Class.forName("org.sqlite.JDBC");
			this.conn = DriverManager.getConnection(URLConexao);
			this.stm = this.conn.createStatement();
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Houve um problema com o banco de dados.");
			e.printStackTrace();
		}
		
		inicializaManagers();
	}

	private void inicializaManagers() {
		mannProduto = new ManagerProduto(stm);
		mannMaquina = new ManagerMaquina(stm);
		mannEstadoMaquina = new ManagerEstadoMaquina(stm);
		mannLogAlteracoes = new ManagerLogAlteracao();
		mannLogFalhas = new ManagerLogFalhas(stm);
		mannProducao = new ManagerProducao(stm);
		mannSenha = new ManagerSenha(stm);
	}
	
}
