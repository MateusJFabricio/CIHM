package Controller;

import Model.ModelFrame;

public class ControllerFrame {
	private ModelFrame model;
	
	public ControllerFrame()
	{
		model = new ModelFrame();
	}
	
	public int confirmarSenha(String senha)
	{
		return model.confirmarSenha(senha);
	}
}
