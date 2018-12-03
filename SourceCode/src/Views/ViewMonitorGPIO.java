package Views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Controller.ControllerMonitorGPIO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewMonitorGPIO extends JFrame {

	private static final long serialVersionUID = 1L;
	private ControllerMonitorGPIO control;
	private JPanel contentPane;
	private JTextField txtNumOut;
	private JLabel lblInput11;
	private JLabel lblInput12;
	private JLabel lblInput13;
	private JLabel lblInput16;
	private JLabel lblInput15;
	private JLabel lblInput14;
	private JLabel lblInput24;
	private JLabel lblInput27;
	private JLabel lblInput25;
	private JLabel lblOutput3;
	private JLabel lblOutput2;
	private JLabel lblOutput1;
	private JLabel lblOutput7;
	private JLabel lblOutput5;
	private JLabel lblOutput4;
	private JLabel lblOutput26;
	private JLabel lblOutput10;
	private JLabel lblOutput8;
	private JLabel lblInput23;
	private JLabel lblInput21;
	private JLabel lblOutput28;
	private JLabel lblOutput29;
	private JLabel lblOutput22;
	private ActionListener actMonitorGPIO;
	private Timer timerMonitorGPIO;
	private JLabel lblInput;
	private int numDots = 0;
	private JLabel lblOutput0;
	

	public ViewMonitorGPIO() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				actClosing();
			}
		});
		control = new ControllerMonitorGPIO();
		actMonitorGPIO = new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				atualizarEstadosInput();
				
				lblInput.setText(lblInput.getText() + " .");
				
				if (numDots >= 4)
				{
					numDots = numDots * -1;
					lblInput.setText("Input");
				}
				
				numDots++;
		}};
		
		timerMonitorGPIO = new Timer(400, actMonitorGPIO);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 795, 562);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPainelDeTeste = new JLabel("Painel de Teste da GPIO - ClaraMaq");
		lblPainelDeTeste.setForeground(new Color(0, 0, 0));
		lblPainelDeTeste.setBackground(new Color(255, 255, 255));
		lblPainelDeTeste.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPainelDeTeste.setHorizontalAlignment(SwingConstants.CENTER);
		lblPainelDeTeste.setBounds(10, 0, 669, 36);
		contentPane.add(lblPainelDeTeste);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(10, 31, 354, 214);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtNumOut = new JTextField();
		txtNumOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Main.Main.teclado.setNumberOnly(true);
				Main.Main.teclado.showKeyboard();
			}
		});
		txtNumOut.setBounds(183, 48, 161, 54);
		panel.add(txtNumOut);
		txtNumOut.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNumOut.setText("1");
		txtNumOut.setColumns(10);
		
		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				up();
			}
		});
		btnUp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUp.setBounds(10, 119, 151, 84);
		panel.add(btnUp);
		
		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				down();
			}
		});
		btnDown.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDown.setBounds(181, 119, 151, 84);
		panel.add(btnDown);
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOutput.setBounds(117, 11, 122, 26);
		panel.add(lblOutput);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNmero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNmero.setBounds(10, 48, 141, 54);
		panel.add(lblNmero);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_1.setBounds(10, 264, 354, 148);
		contentPane.add(panel_1);
		
		JButton btnIniciarMonitor = new JButton("Iniciar Monitor");
		JButton btnPararMonitor = new JButton("Parar monitor");
		
		btnIniciarMonitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timerMonitorGPIO.start();
				btnIniciarMonitor.setEnabled(false);
				btnPararMonitor.setEnabled(true);
			}
		});
		btnIniciarMonitor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnIniciarMonitor.setBounds(10, 48, 151, 84);
		panel_1.add(btnIniciarMonitor);
		
		
		btnPararMonitor.setEnabled(false);
		btnPararMonitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timerMonitorGPIO.stop();
				btnIniciarMonitor.setEnabled(true);
				btnPararMonitor.setEnabled(false);
			}
		});
		btnPararMonitor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPararMonitor.setBounds(171, 48, 151, 84);
		panel_1.add(btnPararMonitor);
		
		lblInput = new JLabel("Input");
		lblInput.setHorizontalAlignment(SwingConstants.LEFT);
		lblInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInput.setBounds(56, 11, 246, 26);
		panel_1.add(lblInput);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_2.setBounds(366, 31, 396, 414);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblInput_1 = new JLabel("### Entradas ###");
		lblInput_1.setBounds(10, 3, 275, 14);
		lblInput_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblInput_1);
		
		JLabel lblOutput_1 = new JLabel("####  Sa\u00EDdas ###");
		lblOutput_1.setBounds(10, 188, 275, 14);
		lblOutput_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblOutput_1);
		
		lblInput11 = new JLabel("Input 11: Bomba Ligada - ");
		lblInput11.setBounds(10, 19, 334, 14);
		panel_2.add(lblInput11);
		
		lblInput12 = new JLabel("Input 12: Bot\u00E3o de Emergencia - ");
		lblInput12.setBounds(10, 34, 334, 14);
		panel_2.add(lblInput12);
		
		lblInput13 = new JLabel("Input 13: Frasco saindo envase - ");
		lblInput13.setBounds(10, 49, 334, 14);
		panel_2.add(lblInput13);
		
		lblInput16 = new JLabel("Input 16: Frasco entrando envase - ");
		lblInput16.setBounds(10, 94, 334, 14);
		panel_2.add(lblInput16);
		
		lblInput15 = new JLabel("Input 15: Fim de curso em cima - ");
		lblInput15.setBounds(10, 79, 334, 14);
		panel_2.add(lblInput15);
		
		lblInput14 = new JLabel("Input 14: Fim de curso embaixo - ");
		lblInput14.setBounds(10, 64, 334, 14);
		panel_2.add(lblInput14);
		
		lblInput24 = new JLabel("Input 24: Sensor Frasco Pos. Tampador - ");
		lblInput24.setBounds(10, 139, 376, 14);
		panel_2.add(lblInput24);
		
		lblInput27 = new JLabel("Input 27: Sensor Nivel alto - ");
		lblInput27.setBounds(10, 124, 334, 14);
		panel_2.add(lblInput27);
		
		lblInput25 = new JLabel("Input 25: Sensor Nivel baixo - ");
		lblInput25.setBounds(10, 109, 334, 14);
		panel_2.add(lblInput25);
		
		lblOutput3 = new JLabel("Output 3: Esteira 2 - ");
		lblOutput3.setBounds(10, 235, 334, 14);
		panel_2.add(lblOutput3);
		
		lblOutput2 = new JLabel("Output 2: Esteira 1 - ");
		lblOutput2.setBounds(10, 219, 334, 14);
		panel_2.add(lblOutput2);
		
		lblOutput1 = new JLabel("Output 1: Bomba Envase - ");
		lblOutput1.setBounds(10, 203, 334, 14);
		panel_2.add(lblOutput1);
		
		lblOutput7 = new JLabel("Output 7: Valvula Pistao Env. Recua - ");
		lblOutput7.setBounds(10, 284, 334, 14);
		panel_2.add(lblOutput7);
		
		lblOutput5 = new JLabel("Output 5: Valvula Pistao Env. Avanca - ");
		lblOutput5.setBounds(10, 268, 334, 14);
		panel_2.add(lblOutput5);
		
		lblOutput4 = new JLabel("Output 4: Valvula Enforcador - ");
		lblOutput4.setBounds(10, 252, 334, 14);
		panel_2.add(lblOutput4);
		
		lblOutput26 = new JLabel("Output 26: Sinal sonoro - ");
		lblOutput26.setBounds(10, 332, 334, 14);
		panel_2.add(lblOutput26);
		
		lblOutput10 = new JLabel("Output 10: Valvula Trava 2 - ");
		lblOutput10.setBounds(10, 316, 334, 14);
		panel_2.add(lblOutput10);
		
		lblOutput8 = new JLabel("Output 8: Valvula Trava 1 - ");
		lblOutput8.setBounds(10, 300, 334, 14);
		panel_2.add(lblOutput8);
		
		lblInput23 = new JLabel("Input 23: Sensor Frasco Pos. Acumulador - ");
		lblInput23.setBounds(10, 169, 376, 14);
		panel_2.add(lblInput23);
		
		lblInput21 = new JLabel("Input 21: Sensor Frasco Entrando Carrossel - ");
		lblInput21.setBounds(10, 154, 376, 14);
		panel_2.add(lblInput21);
		
		lblOutput28 = new JLabel("Output 28: Motor Carrossel - ");
		lblOutput28.setBounds(10, 347, 334, 14);
		panel_2.add(lblOutput28);
		
		lblOutput29 = new JLabel("Output 29: Valvula Tampador - ");
		lblOutput29.setBounds(10, 363, 334, 14);
		panel_2.add(lblOutput29);
		
		lblOutput22 = new JLabel("Output 22: Motor acumulador - ");
		lblOutput22.setBounds(10, 379, 334, 14);
		panel_2.add(lblOutput22);
		
		lblOutput0 = new JLabel("Output 0: - Pistao Posicionador - ");
		lblOutput0.setBounds(11, 393, 334, 14);
		panel_2.add(lblOutput0);
		
		adicionarEstadosOutput();
		adicionarEstadosInput();
	}
	
	
	protected void actClosing() {
		timerMonitorGPIO.stop();
	}


	private void adicionarEstadosOutput() {
		lblOutput1.setText(lblOutput1.getText() + " ( " + control.buscarEstadoOutput(1) + " )");
		lblOutput2.setText(lblOutput2.getText() + " ( " + control.buscarEstadoOutput(2) + " )");
		lblOutput3.setText(lblOutput3.getText() + " ( " + control.buscarEstadoOutput(3) + " )");
		lblOutput4.setText(lblOutput4.getText() + " ( " + control.buscarEstadoOutput(4) + " )");
		lblOutput5.setText(lblOutput5.getText() + " ( " + control.buscarEstadoOutput(5) + " )");
		lblOutput7.setText(lblOutput7.getText() + " ( " + control.buscarEstadoOutput(7) + " )");
		lblOutput8.setText(lblOutput8.getText() + " ( " + control.buscarEstadoOutput(8) + " )");
		lblOutput10.setText(lblOutput10.getText() + " ( " + control.buscarEstadoOutput(10) + " )");
		lblOutput26.setText(lblOutput26.getText() + " ( " + control.buscarEstadoOutput(26) + " )");
		lblOutput28.setText(lblOutput28.getText() + " ( " + control.buscarEstadoOutput(28) + " )");
		lblOutput29.setText(lblOutput29.getText() + " ( " + control.buscarEstadoOutput(29) + " )");
		lblOutput22.setText(lblOutput22.getText() + " ( " + control.buscarEstadoOutput(22) + " )");
		lblOutput0.setText(lblOutput0.getText() + " ( " + control.buscarEstadoOutput(0) + " )");
	}
	
	
	private void adicionarEstadosInput() {
		lblInput11.setText(lblInput11.getText() + " ( " + control.buscarEstadoInput(11) + " )");
		lblInput12.setText(lblInput12.getText() + " ( " + control.buscarEstadoInput(12) + " )");
		lblInput13.setText(lblInput13.getText() + " ( " + control.buscarEstadoInput(13) + " )");
		lblInput14.setText(lblInput14.getText() + " ( " + control.buscarEstadoInput(14) + " )");
		lblInput15.setText(lblInput15.getText() + " ( " + control.buscarEstadoInput(15) + " )");
		lblInput16.setText(lblInput16.getText() + " ( " + control.buscarEstadoInput(16) + " )");
		lblInput25.setText(lblInput25.getText() + " ( " + control.buscarEstadoInput(25) + " )");
		lblInput27.setText(lblInput27.getText() + " ( " + control.buscarEstadoInput(27) + " )");
		lblInput24.setText(lblInput24.getText() + " ( " + control.buscarEstadoInput(24) + " )");
		lblInput21.setText(lblInput21.getText() + " ( " + control.buscarEstadoInput(21) + " )");
		lblInput23.setText(lblInput23.getText() + " ( " + control.buscarEstadoInput(23) + " )");
	}
	
private void atualizarEstadosInput() {
		lblInput11.setText(formatarTextoLabel(control.buscarEstadoInput(11), lblInput11.getText()));
		lblInput12.setText(formatarTextoLabel(control.buscarEstadoInput(12), lblInput12.getText()));
		lblInput13.setText(formatarTextoLabel(control.buscarEstadoInput(13), lblInput13.getText()));
		lblInput14.setText(formatarTextoLabel(control.buscarEstadoInput(14), lblInput14.getText()));
		lblInput15.setText(formatarTextoLabel(control.buscarEstadoInput(15), lblInput15.getText()));
		lblInput16.setText(formatarTextoLabel(control.buscarEstadoInput(16), lblInput16.getText()));
		lblInput25.setText(formatarTextoLabel(control.buscarEstadoInput(25), lblInput25.getText()));
		lblInput27.setText(formatarTextoLabel(control.buscarEstadoInput(27), lblInput27.getText()));
		lblInput24.setText(formatarTextoLabel(control.buscarEstadoInput(24), lblInput24.getText()));
		lblInput21.setText(formatarTextoLabel(control.buscarEstadoInput(21), lblInput21.getText()));
		lblInput23.setText(formatarTextoLabel(control.buscarEstadoInput(23), lblInput23.getText()));
	}


	private void up()
	{
		if (txtNumOut.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Digite um numero");
			return;
		}
		
		control.mudarEstadoIO(true, Integer.parseInt(txtNumOut.getText()));
		
		switch (Integer.parseInt(txtNumOut.getText())) {
		case 1:
			lblOutput1.setText(formatarTextoLabel("ON", lblOutput1.getText()));
			break;
		case 2:
			lblOutput2.setText(formatarTextoLabel("ON", lblOutput2.getText()));
			break;
		case 3:
			lblOutput3.setText(formatarTextoLabel("ON", lblOutput3.getText()));
			break;
		case 4:
			lblOutput4.setText(formatarTextoLabel("ON", lblOutput4.getText()));
			break;
		case 5:
			lblOutput5.setText(formatarTextoLabel("ON", lblOutput5.getText()));
			break;
		case 7:
			lblOutput7.setText(formatarTextoLabel("ON", lblOutput7.getText()));
			break;
		case 8:
			lblOutput8.setText(formatarTextoLabel("ON", lblOutput8.getText()));
			break;
		case 10:
			lblOutput10.setText(formatarTextoLabel("ON", lblOutput10.getText()));
			break;
		case 26:
			lblOutput26.setText(formatarTextoLabel("ON", lblOutput26.getText()));
			break;
		case 28:
			lblOutput28.setText(formatarTextoLabel("ON", lblOutput28.getText()));
			break;
		case 29:
			lblOutput29.setText(formatarTextoLabel("ON", lblOutput29.getText()));
			break;
		case 22:
			lblOutput22.setText(formatarTextoLabel("ON", lblOutput22.getText()));
			break;
		case 0:
			lblOutput22.setText(formatarTextoLabel("ON", lblOutput0.getText()));
			break;
		default:
			break;
		}
	}
	
	private void down()
	{
		if (txtNumOut.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Digite um numero");
			return;
		}
		
		control.mudarEstadoIO(false, Integer.parseInt(txtNumOut.getText()));
		
		switch (Integer.parseInt(txtNumOut.getText())) {
		case 1:
			lblOutput1.setText(formatarTextoLabel("OFF", lblOutput1.getText()));
			break;
		case 2:
			lblOutput2.setText(formatarTextoLabel("OFF", lblOutput2.getText()));
			break;
		case 3:
			lblOutput3.setText(formatarTextoLabel("OFF", lblOutput3.getText()));
			break;
		case 4:
			lblOutput4.setText(formatarTextoLabel("OFF", lblOutput4.getText()));
			break;
		case 5:
			lblOutput5.setText(formatarTextoLabel("OFF", lblOutput5.getText()));
			break;
		case 7:
			lblOutput7.setText(formatarTextoLabel("OFF", lblOutput7.getText()));
			break;
		case 8:
			lblOutput8.setText(formatarTextoLabel("OFF", lblOutput8.getText()));
			break;
		case 10:
			lblOutput10.setText(formatarTextoLabel("OFF", lblOutput10.getText()));
			break;
		case 26:
			lblOutput26.setText(formatarTextoLabel("OFF", lblOutput26.getText()));
			break;
		case 28:
			lblOutput28.setText(formatarTextoLabel("OFF", lblOutput28.getText()));
			break;
		case 29:
			lblOutput29.setText(formatarTextoLabel("OFF", lblOutput29.getText()));
			break;
		case 22:
			lblOutput22.setText(formatarTextoLabel("OFF", lblOutput22.getText()));
			break;
		case 0:
			lblOutput0.setText(formatarTextoLabel("OFF", lblOutput0.getText()));
			break;
		default:
			break;
		}
	}


	private String formatarTextoLabel(String estado, String text) {
		return text.substring(0, text.indexOf("(")) + "( " + estado + " )";
	}
}
