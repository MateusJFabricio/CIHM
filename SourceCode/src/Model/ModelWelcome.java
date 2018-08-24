package Model;

import DAO.ManagerMaquina;
import DAO.Maquina;

public class ModelWelcome {

	public Maquina dadosIniciaisMaquina()
	{
		ManagerMaquina manMaquina =  new ManagerMaquina();
		return manMaquina.selectMaquina();
	}
}
