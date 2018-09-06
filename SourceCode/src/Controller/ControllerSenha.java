package Controller;

import Model.ModelSenha;

public class ControllerSenha {
	private ModelSenha model;
	
	public ControllerSenha()
	{
		model = new ModelSenha();
	}
	
	public boolean confirmarSenha(String senha)
	{
		return model.confirmarSenha(senha);
	}
	
}
