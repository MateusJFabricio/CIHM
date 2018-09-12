package Controller;

import java.util.ArrayList;

import DAO.Produto;
import Model.ModelConfiguracao;

public class ControllerConfiguracoes {
	private ModelConfiguracao model;
	
	public ControllerConfiguracoes()
	{
		model = new ModelConfiguracao();
	}
	
	public ArrayList<Produto> buscarProdutosCadastrados() {
		return model.getAllProdutos();
	}

	public void inserirNovoProduto(Produto prod) {
		model.inserirProduto(prod);
	}

	public void excluirProduto(Produto produto) {
		model.excluirProduto(produto);
	}

	public void inserirProduto(Produto produto) {
		// TODO Auto-generated method stub
		
	}

}
