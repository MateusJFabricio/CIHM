package Main;

import Background.ManagerIO;
import Controller.Controller;
import DAO.ManagerBD;
import Views.Frame.MtsKeyboard;

public class Main {
	public static MtsKeyboard teclado;
	public static ManagerBD mannBD;
	public static ManagerIO mannIO;
	
	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.initWelcomeScreen();
		teclado = new MtsKeyboard();
		mannBD = new  ManagerBD();
		mannIO = new ManagerIO();
		controller.abrirTelaHome();
	}
}
