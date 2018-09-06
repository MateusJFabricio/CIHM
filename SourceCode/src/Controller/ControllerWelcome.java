package Controller;

import DAO.Maquina;
import Model.ModelWelcome;

public class ControllerWelcome {
	private ModelWelcome model;
	
	public ControllerWelcome()
	{
		model = new ModelWelcome();
	}
	
	public Maquina dadosIniciaisMaquina()
	{
		return model.dadosIniciaisMaquina();
	}
}
