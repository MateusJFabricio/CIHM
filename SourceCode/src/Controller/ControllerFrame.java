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
			Main.Main.mannIO.goHome();
			Main.Main.mannIO.gpio.getGpio().shutdown();
			Runtime.getRuntime().exec("shutdown -h now");
			JOptionPane.showMessageDialog(null, "Desligando");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fecharApp() {
		Main.Main.mannIO.gpio.getGpio().shutdown();
		System.exit(0);
	}
}
