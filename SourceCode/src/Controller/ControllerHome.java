package Controller;

import java.util.ArrayList;

import DAO.Produto;
import Model.ModelHome;

public class ControllerHome {
	private ModelHome model;
	
	public ControllerHome()
	{
		model = new ModelHome();
	}
	
	public ArrayList<Produto> getProdutos()
	{
		return model.Produtos();
	}
	
	public void iniciarCiclo(Produto produto, boolean frascosPosicionados)
	{
		model.iniciarCiclo(produto, frascosPosicionados);
	}
	
	public void interromperCiclo()
	{
		
	}
}
