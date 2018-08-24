package Model;

import DAO.ManagerSenha;
import DAO.Senha;

public class ModelFrame {
	private ManagerSenha manager;
	
	public ModelFrame()
	{
		manager = new ManagerSenha();
	}
	
	public int confirmarSenha(String senha)
	{
		Senha objSenha = manager.selectSenha();
		if (objSenha.getSenhaGeral().equals(senha))
		{
			return 1;
		}else if (objSenha.getSenhaMaster().equals(senha))
		{
			return 2;
		}else return 0;
	}
	
}
