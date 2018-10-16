package Model;

import java.util.ArrayList;

import DAO.Produto;

public class ModelHome {
	
	public ArrayList<Produto> Produtos()
	{
		return Main.Main.mannBD.mannProduto.getAllProduto();
	}
	
	public void iniciarCiclo(Produto produto, boolean frascosPosicionados, int meta)
	{
		Main.Main.mannIO.iniciarCiclo(produto, frascosPosicionados, meta);
		Main.Main.mannBD.mannProducao.insereNovaProducao(produto, meta);
	}

	public void interromperCiclo(int produzido) {
		Main.Main.mannIO.interromperCiclo();
		Main.Main.mannBD.mannProducao.finalizaProducao(produzido);
	}
	
	
}
