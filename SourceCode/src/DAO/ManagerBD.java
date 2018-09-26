package DAO;

import java.io.File;
import java.io.IOException;
import java.sql.*;

import javax.swing.JOptionPane;

public class ManagerBD {
	private String URLConexao;
	
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
		String caminhoAbsoluto = new File("").getAbsolutePath();
		URLConexao = "jdbc:sqlite:" + caminhoAbsoluto + "/ClaraMaq_PRD.db";
		try {
			Class.forName("org.sqlite.JDBC");
			this.conn = DriverManager.getConnection(URLConexao);
			this.stm = this.conn.createStatement();
		} catch (SQLException | ClassNotFoundException e) {
			try {
				URLConexao = "jdbc:sqlite:" + caminhoAbsoluto + "\\ClaraMaq_PRD.db";
				Class.forName("org.sqlite.JDBC");
				this.conn = DriverManager.getConnection(URLConexao);
				this.stm = this.conn.createStatement();
			} catch (SQLException | ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Houve um problema com o banco de dados.");
				try {
					Runtime.getRuntime().exec("pkill x");
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				e1.printStackTrace();
			}
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
