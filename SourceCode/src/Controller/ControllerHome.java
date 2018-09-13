package Controller;

import java.util.ArrayList;

import DAO.Maquina;
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
	
	public void iniciarCiclo(Produto produto, boolean frascosPosicionados, int meta)
	{
		model.iniciarCiclo(produto, frascosPosicionados, meta);
	}
	
	public void interromperCiclo()
	{
		model.interromperCiclo();
	}

	public Maquina dadosMaquina() {
		return Main.Main.mannBD.mannMaquina.selectMaquina();
	}
}
