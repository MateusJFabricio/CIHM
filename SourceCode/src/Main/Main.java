package Main;

import Controller.Controller;
import Views.Frame.MtsKeyboard;

public class Main {
	public static MtsKeyboard teclado;
	
	public static void main(String[] args) {

		teclado = new MtsKeyboard();
		Controller controller = new Controller();
		controller.initTelas();
	}
}
