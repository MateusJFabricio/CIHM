package Model;

import java.io.IOException;

import javax.swing.JOptionPane;

import DAO.Senha;

public class ModelSenha {

	public boolean confirmarSenha(String senha)
	{
		int tipo = tipoSenha(senha);
				
		switch (tipo) {
		case 0:
			return false;
		case 1:
			try {
				Runtime.getRuntime().exec("shutdown -h now");
				JOptionPane.showMessageDialog(null, "Desligando");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case 2:
			System.exit(0);
			break;
		default:
			break;
		}
		
		return false;
	}
	
	private int tipoSenha(String senha)
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
