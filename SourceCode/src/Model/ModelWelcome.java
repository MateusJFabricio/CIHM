package Model;

import DAO.Maquina;

public class ModelWelcome {

	public Maquina dadosIniciaisMaquina()
	{
		return Main.Main.mannBD.mannMaquina.selectMaquina();
	}
}
