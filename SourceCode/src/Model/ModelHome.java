package Model;

import java.util.ArrayList;

import Background.ManagerIO;
import DAO.ManagerProduto;
import DAO.Produto;

public class ModelHome {
	private ManagerProduto produto;
	private ManagerIO controladorIo;
	
	public ModelHome()
	{
		produto = new ManagerProduto();
		controladorIo = new ManagerIO();
	}
	
	public ArrayList<Produto> Produtos()
	{
		return produto.getAll();
	}
	
	public void iniciarCiclo(Produto produto, boolean frascosPosicionados)
	{
		controladorIo.iniciarCiclo(produto, frascosPosicionados);
	}
	
	
}
