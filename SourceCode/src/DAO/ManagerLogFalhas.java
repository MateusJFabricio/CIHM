package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerLogFalhas {
	private java.sql.Statement query;
	
	public ManagerLogFalhas(java.sql.Statement query)
	{
		this.query = query;
	}
	
	public ArrayList<LogFalhas> buscarFalhas() {
		ArrayList<LogFalhas> lista = new ArrayList<LogFalhas>();	  
		ResultSet rs;

		try {
			rs = query.executeQuery("select * from LogFalhas ORDER BY DataHora DESC");

			while (rs.next()) {
				LogFalhas fail = new LogFalhas();
				fail.setId(rs.getInt("Id"));
				fail.setDataHora(rs.getString("DataHora"));
				fail.setFalha(rs.getString("Falha"));
				fail.setVisto(rs.getString("Visto"));
				
				lista.add(fail);
			}

			rs.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	
	}

}
