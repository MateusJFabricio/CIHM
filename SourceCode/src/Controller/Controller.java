package Controller;


import Views.ViewFrame;
import Views.ViewWelcome;

public class Controller {
	public static ViewFrame janelaPrincipal;
	private ViewWelcome welcome;
	
	public void initWelcomeScreen()
	{
		welcome = new ViewWelcome();
		welcome.setVisible(true);
	}
	
	public void abrirTelaHome()
	{
		janelaPrincipal = new ViewFrame();
		janelaPrincipal.setVisible(true);
		welcome.dispose();
	}
	
	
	
}
