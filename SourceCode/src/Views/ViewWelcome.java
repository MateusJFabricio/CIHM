package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
	
	public ViewWelcome(Controller controlMain) {
		this.controlMain = controlMain;
		controlLocal = new ControllerWelcome();
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
			}
		});
		
		setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		setBounds(100, 100, 800, 480);
		this.setUndecorated(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JLabel lblNewLabel = new JLabel("Envasadoras e Automa\u00E7\u00E3o Industrial");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewLabel.setBounds(201, 257, 388, 28);
		getContentPane().add(lblNewLabel);
		
		JLabel lblSejaBemVindo = new JLabel("Seja bem vindo");
		lblSejaBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSejaBemVindo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSejaBemVindo.setBounds(295, 312, 199, 14);
		getContentPane().add(lblSejaBemVindo);
		
		JLabel lblNewLabel_1 = new JLabel("Inicializando Sistema...");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(295, 337, 199, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblModeloDaMaquina = new JLabel("Envasadora " + controlLocal.dadosIniciaisMaquina().getModelo());
		lblModeloDaMaquina.setHorizontalAlignment(SwingConstants.CENTER);
		lblModeloDaMaquina.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblModeloDaMaquina.setBounds(275, 383, 246, 28);
		getContentPane().add(lblModeloDaMaquina);
		
		JLabel lblNewLabel_2 = new JLabel("Vers\u00E3o: 0.0");
		lblNewLabel_2.setBounds(731, 466, 69, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ViewWelcome.class.getResource("/Assets/LogoClaraMaq - 800 x 480.PNG")));
		label.setBounds(0, 0, 800, 480);
		getContentPane().add(label);
		
		inicializaTimer();
		
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
