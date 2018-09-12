package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Controller.Controller;
import Controller.ControllerWelcome;

public class ViewWelcome extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Controller controlMain;
	private ControllerWelcome controlLocal;
	private Timer timerWelcomePage;
	private JLabel lblModeloDaMaquina;
	
	public ViewWelcome(Controller controlMain) {
		this.controlMain = controlMain;
		controlLocal = new ControllerWelcome();
		
		setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		setBounds(100, 100, 800, 480);
		this.setUndecorated(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JLabel lblNewLabel = new JLabel("Envasadoras e Automa\u00E7\u00E3o Industrial");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewLabel.setBounds(0, 257, 800, 28);
		getContentPane().add(lblNewLabel);
		
		JLabel lblSejaBemVindo = new JLabel("Seja bem vindo");
		lblSejaBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSejaBemVindo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSejaBemVindo.setBounds(0, 304, 800, 22);
		getContentPane().add(lblSejaBemVindo);
		
		JLabel lblNewLabel_1 = new JLabel("Inicializando Sistema...");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(0, 337, 800, 28);
		getContentPane().add(lblNewLabel_1);
		
		lblModeloDaMaquina = new JLabel("Envasadora ClaraMaq init");
		lblModeloDaMaquina.setHorizontalAlignment(SwingConstants.CENTER);
		lblModeloDaMaquina.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblModeloDaMaquina.setBounds(0, 383, 800, 39);
		getContentPane().add(lblModeloDaMaquina);
		
		JLabel lblNewLabel_2 = new JLabel("Vers\u00E3o: 0.0");
		lblNewLabel_2.setBounds(697, 466, 103, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ViewWelcome.class.getResource("/Assets/LogoClaraMaq - 800 x 480.PNG")));
		label.setBounds(0, 0, 800, 480);
		getContentPane().add(label);
		
		inicializaTimer();
		
		buscarModeloMaquina();
	}
	
	private void buscarModeloMaquina() {
		lblModeloDaMaquina.setText("Envasadora " + controlLocal.dadosIniciaisMaquina().getModelo());
	}

	private void inicializaTimer() {
		ActionListener action = new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				controlMain.abrirTelaHome();
				timerWelcomePage.stop();
			}
		};
		timerWelcomePage = new Timer(2000, action);
		timerWelcomePage.start();
	}
	
}
