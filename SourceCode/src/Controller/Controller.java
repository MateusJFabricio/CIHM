package Controller;


import Views.ViewFrame;
import Views.ViewWelcome;

public class Controller {
	public static ViewFrame janelaPrincipal;
	private ViewWelcome welcome;
	
	public void initTelas()
	{
		welcome = new ViewWelcome(this);

		//Habilita tela Bem Vindo
		welcome.setVisible(true);
		
	}
	
	public void abrirTelaHome()
	{
		welcome.dispose();
		janelaPrincipal = new ViewFrame();
		janelaPrincipal.setVisible(true);
	}
	
	
	
}
