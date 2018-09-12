package Model;

import DAO.Senha;

public class ModelFrame {

	public int confirmarSenha(String senha)
	{
		Senha objSenha = Main.Main.mannBD.mannSenha.selectSenha();
		if (objSenha.getSenhaGeral().equals(senha))
		{
			return 1;
		}else if (objSenha.getSenhaMaster().equals(senha))
		{
			return 2;
		}else return 0;
	}
	
}
