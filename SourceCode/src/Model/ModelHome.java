package Model;

import java.util.ArrayList;

import DAO.Produto;

public class ModelHome {
	
	public ArrayList<Produto> Produtos()
	{
		return Main.Main.mannBD.mannProduto.getAllProduto();
	}
	
	public void iniciarCiclo(Produto produto, boolean frascosPosicionados)
	{
		Main.Main.mannIO.iniciarCiclo(produto, frascosPosicionados);
	}
	
	
}
