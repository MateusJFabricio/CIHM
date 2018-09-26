package Controller;

import java.io.IOException;

import javax.swing.JOptionPane;

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

	public void desligar() {
		try {
			Runtime.getRuntime().exec("shutdown -h now");
			JOptionPane.showMessageDialog(null, "Desligando");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fecharApp() {
		System.exit(0);
	}
}
