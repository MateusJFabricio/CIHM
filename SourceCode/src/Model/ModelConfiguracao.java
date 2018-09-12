package Model;

import java.util.ArrayList;

import DAO.Produto;
import Main.Main;

public class ModelConfiguracao {
	
	public ArrayList<Produto> getAllProdutos() {
		return Main.mannBD.mannProduto.getAllProduto();
	}

	public void inserirProduto(Produto prod) {
		Main.mannBD.mannProduto.insertProduto(prod);
	}

	public void excluirProduto(Produto produto) {
		Main.mannBD.mannProduto.removeProduto(produto);
	}

}
