package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class ManagerProducao {

	private java.sql.Statement query;
	private int horaInicioProducao, minInicioProducao, segundoInicioProducao;
	
	
	public ManagerProducao(java.sql.Statement query)
	{
		this.query = query;
	}
	
	public void insereNovaProducao(Produto produto, int meta)
	{
		String tipoProducao = "Programada";
		if (meta <= 0)
			tipoProducao = "Continua";
		try {		  
			query.executeUpdate("INSERT INTO Producao("
					+ "	Fk_Produto, "
					+ "DataProducao,"
					+ "Meta,"
					+ "TipoProducao,"
					+ "Produzido,"
					+ "TempoProducao,"
					+ "MediaProducao) "
					+ "VALUES (" 
					+ produto.getId() + ","
					+ quote(retornarDataHora()) + ","
					+ meta + ","
					+ quote(tipoProducao) + ","
					+ "0" + ","
					+ "0" + ","
					+ "0" + ")");
			
			atualizaInicioProducao();
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private void atualizaInicioProducao() {
	  Calendar data = Calendar.getInstance();
	  horaInicioProducao = data.get(Calendar.HOUR);
	  minInicioProducao = data.get(Calendar.MINUTE);
	  segundoInicioProducao = data.get(Calendar.SECOND);
	}

	private String quote(String w)
	{
		return "'" + w + "'";
	}
	public ArrayList<Producao> getRegistrosDataIgual(String data) {
		ArrayList<Producao> lista = new ArrayList<Producao>();	  
		ResultSet rs;
		try {
			rs = query.executeQuery("SELECT Producao.*, Produto.Nome FROM Producao " +
						"INNER JOIN Produto ON Producao.Fk_Produto = Produto.Id " +
						"where DataProducao = '"+ data + "'");
			
			while (rs.next()) {
				Producao prod = new Producao();
				prod.setId(rs.getInt("id"));
				prod.setDataProducao(formatData(rs.getString("DataProducao")));
				prod.setFk_Produto(rs.getInt("Fk_Produto"));
				prod.setNomeProduto(rs.getString("Produto.Nome"));
				prod.setMediaProducao(rs.getInt("MediaProducao"));
				prod.setMeta(rs.getInt("Meta"));
				prod.setProduzido(rs.getInt("Produzido"));
				prod.setTempoProducao(rs.getInt("TempoProducao"));
				prod.setTipoProducao(rs.getString("TipoProducao"));
								
				lista.add(prod);
			}

			rs.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	private String retornarDataHora()
	{
        Calendar data = Calendar.getInstance();
        String ano = String.valueOf(data.get(Calendar.YEAR));
        String mes = String.valueOf(data.get(Calendar.MONTH));
        if (data.get(Calendar.MONTH) < 10)
        	mes = "0" + String.valueOf(data.get(Calendar.MONTH));
        
        String dia = String.valueOf(data.get(Calendar.DAY_OF_MONTH));
        if (data.get(Calendar.DAY_OF_MONTH) < 10)
        	dia = "0" + String.valueOf(data.get(Calendar.DAY_OF_MONTH));
        return ano + mes + dia;
	}
	
	private String formatData(String data)
	{
		String dataFormat = data;
		return dataFormat.substring(6, 8) + "/" + dataFormat.substring(4, 6) + "/" + dataFormat.substring(0, 4);
	}
	public ArrayList<Producao> getRegistrosDataMaiorQue(String data) {
		ArrayList<Producao> lista = new ArrayList<Producao>();	  
		ResultSet rs;

		try {
			rs = query.executeQuery("SELECT Producao.*, Produto.Nome FROM Producao " +
					"INNER JOIN Produto ON Producao.Fk_Produto = Produto.Id " +
					"where DataProducao > '"+ data + "'");
			
			while (rs.next()) {
				Producao prod = new Producao();
				prod.setId(rs.getInt("id"));
				prod.setDataProducao(formatData(rs.getString("DataProducao")));
				prod.setFk_Produto(rs.getInt("Fk_Produto"));
				prod.setNomeProduto(rs.getString("Nome"));
				prod.setMediaProducao(rs.getInt("MediaProducao"));
				prod.setMeta(rs.getInt("Meta"));
				prod.setProduzido(rs.getInt("Produzido"));
				prod.setTempoProducao(rs.getInt("TempoProducao"));
				prod.setTipoProducao(rs.getString("TipoProducao"));
								
				lista.add(prod);
			}

			rs.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public ArrayList<Producao> getRegistrosDataMenorQue(String data) {
		ArrayList<Producao> lista = new ArrayList<Producao>();	  
		ResultSet rs;

		try {
			rs =query.executeQuery("SELECT Producao.*, Produto.Nome FROM Producao " +
					"INNER JOIN Produto ON Producao.Fk_Produto = Produto.Id " +
					"where DataProducao < '"+ data + "'");
			
			while (rs.next()) {
				Producao prod = new Producao();
				prod.setId(rs.getInt("id"));
				prod.setDataProducao(formatData(rs.getString("DataProducao")));
				prod.setFk_Produto(rs.getInt("Fk_Produto"));
				prod.setNomeProduto(rs.getString("Produto.Nome"));
				prod.setMediaProducao(rs.getInt("MediaProducao"));
				prod.setMeta(rs.getInt("Meta"));
				prod.setProduzido(rs.getInt("Produzido"));
				prod.setTempoProducao(rs.getInt("TempoProducao"));
				prod.setTipoProducao(rs.getString("TipoProducao"));
								
				lista.add(prod);
			}

			rs.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public void finalizaProducao(int produzido) {
		Calendar data = Calendar.getInstance();
		int idProducao = 0;
		int tempoProducao;
		int hora = (data.get(Calendar.HOUR) - horaInicioProducao) * 3600;
		if (hora < 0 )
			hora = (24 - horaInicioProducao + data.get(Calendar.HOUR)) * 3600;
		
		int min = ((data.get(Calendar.MINUTE) - minInicioProducao) * 60);
		if (min < 0)
			min = (60 - minInicioProducao + data.get(Calendar.MINUTE)) * 60;
		
		int seg =  (data.get(Calendar.SECOND) - segundoInicioProducao); 
		if (seg < 0)
			seg =  60 - segundoInicioProducao + data.get(Calendar.SECOND);
		
		tempoProducao = hora + min + seg;
		
		int mediaProducao = 0;
		try{
			 mediaProducao = produzido / tempoProducao;
		}catch (Exception e) {
			mediaProducao = 0;
		}
		ResultSet rs;
		Producao prod = new Producao();

		try {
			rs =query.executeQuery("SELECT MAX(id) AS id FROM Producao");
			
			idProducao =  rs.getInt("id");

			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {		  
			query.executeUpdate("UPDATE Producao SET Produzido = " + produzido + ", TempoProducao = " + tempoProducao + ", MediaProducao = " + mediaProducao + " WHERE id = " + idProducao);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}

}
