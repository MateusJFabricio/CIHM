package Controller;

import Model.ModelSenha;

public class ControllerSenha {
	private ModelSenha model;
	
	public ControllerSenha()
	{
		model = new ModelSenha();
	}
	
	public int confirmarSenha(String senha)
	{
		return model.tipoSenha(senha);
	}

}
