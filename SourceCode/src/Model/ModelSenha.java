package Model;

import java.io.IOException;

import javax.swing.JOptionPane;

import DAO.Senha;

public class ModelSenha {

	public int tipoSenha(String senha)
	{
		Senha objSenha = new Senha();
		objSenha = Main.Main.mannBD.mannSenha.selectSenha();
		
		if (objSenha.getSenhaGeral().equals(senha))
		{
			return 1;
		}else if (objSenha.getSenhaMaster().equals(senha))
		{
			return 2;
		}else
			return 0;
		
	}
}
