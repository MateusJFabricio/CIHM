package Controller;

import java.awt.event.ActionListener;
import javax.swing.Timer;

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
