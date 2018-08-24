package Model;

import java.util.ArrayList;

import Controller.ControllerHome;
import DAO.ManagerProduto;
import DAO.Produto;

public class ModelHome {
	private ManagerProduto produto;
	
	public ModelHome()
	{
		produto = new ManagerProduto();
	}
	
	public ArrayList<Produto> Produtos()
	{
		return produto.getAll();
	}
}
