package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerProducao {

	private java.sql.Statement query;
	
	public ManagerProducao(java.sql.Statement query)
	{
		this.query = query;
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
				prod.setId(rs.getInt("Id"));
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
				prod.setId(rs.getInt("Id"));
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
				prod.setId(rs.getInt("Id"));
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

}
