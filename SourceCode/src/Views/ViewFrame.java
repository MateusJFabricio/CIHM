package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import Controller.ControllerFrame;

public class ViewFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ViewHome telaHome;
	private ViewConfiguracoes telaConfiguracoes;
	private ViewEstatisticas telaEstatisticas;
	private ViewDocumentacao telaDocumentacao;
	private ControllerFrame control;
	private JPanel panelTelas_1;
	final int HOME = 1;
	final int ESTATISTICAS = 2;
	final int CONFIGURACOES = 3;
	final int DOCUMENTOS = 4;
	final int DESLIGAR = 5;
	
	public ViewFrame() {
		control = new ControllerFrame();
		//setResizable(false);
		setAutoRequestFocus(false);
		setAlwaysOnTop(true);
		this.setUndecorated(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 480);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(SystemColor.menu);
		
		panelTelas_1 = new JPanel();
		panelTelas_1.setLayout(new BorderLayout(0, 0));
		switchScreen(HOME);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panelTelas_1, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelTelas_1, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(null);
		
		JButton btnSair = new JButton("Desligar");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchScreen(DESLIGAR);
			}
		});
		btnSair.setIcon(new ImageIcon(ViewFrame.class.getResource("/Assets/power (64).png")));
		btnSair.setBounds(624, 11, 140, 114);
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSair.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSair.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel.add(btnSair);
		
		JButton btnConfiguracao = new JButton("Configura\u00E7\u00F5es");
		btnConfiguracao.setIcon(new ImageIcon(ViewFrame.class.getResource("/Assets/maintenance (64).png")));
		btnConfiguracao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchScreen(CONFIGURACOES);
			}
		});
		btnConfiguracao.setBounds(324, 11, 140, 114);
		btnConfiguracao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfiguracao.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConfiguracao.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel.add(btnConfiguracao);
		
		JButton btnEstatistica = new JButton("Estat\u00EDsticas");
		btnEstatistica.setIcon(new ImageIcon(ViewFrame.class.getResource("/Assets/bars-chart (64).png")));
		btnEstatistica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchScreen(ESTATISTICAS);
			}
		});
		btnEstatistica.setBounds(174, 11, 140, 114);
		btnEstatistica.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEstatistica.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEstatistica.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel.add(btnEstatistica);
		
		JButton btnDocumentos = new JButton("Documentos");
		btnDocumentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchScreen(DOCUMENTOS);
			}
		});
		btnDocumentos.setIcon(new ImageIcon(ViewFrame.class.getResource("/Assets/notepad (64).png")));
		btnDocumentos.setBounds(474, 11, 140, 114);
		btnDocumentos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDocumentos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDocumentos.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel.add(btnDocumentos);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchScreen(HOME);
			}
		});
		btnHome.setIcon(new ImageIcon(ViewFrame.class.getResource("/Assets/home (64).png")));
		btnHome.setBounds(24, 11, 140, 114);
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnHome.setHorizontalTextPosition(SwingConstants.CENTER);
		btnHome.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel.add(btnHome);
		
		
		contentPane.setLayout(gl_contentPane);	
		
	}
	
	private void switchScreen(int numTela)
	{
		if (!confirmarMudancaTela()) return;

		if (numTela == HOME)
		{
			if (telaHome == null) 
			{
				telaHome = new ViewHome(this);
			};
			panelTelas_1.removeAll();
			panelTelas_1.add(telaHome);
		}else if (numTela == ESTATISTICAS){
			if (telaEstatisticas == null)
			{
				telaEstatisticas = new ViewEstatisticas();
			}
			panelTelas_1.removeAll();
			panelTelas_1.add(telaEstatisticas);
		}else if(numTela == CONFIGURACOES){
			if (telaConfiguracoes == null)
			{
				telaConfiguracoes = new ViewConfiguracoes();
			}
			panelTelas_1.removeAll();
			panelTelas_1.add(telaConfiguracoes);
		}else if(numTela == DOCUMENTOS){
			if (telaDocumentacao == null)
			{
				telaDocumentacao = new ViewDocumentacao();
			}
			panelTelas_1.removeAll();
			panelTelas_1.add(telaDocumentacao);
		}else if(numTela == DESLIGAR){
			this.setAlwaysOnTop(false);
			
			JPasswordField password = new JPasswordField(10);
			password.setEchoChar('*'); 
			JLabel rotulo = new JLabel("Entre com a senha:");
			JPanel entUsuario = new JPanel();
			entUsuario.add(rotulo);
			entUsuario.add(password);
			JOptionPane.showMessageDialog(null, entUsuario, "Acesso restrito", JOptionPane.PLAIN_MESSAGE);
			String senha = password.getText();
			switch (control.confirmarSenha(senha)) {
			case 2:
				try {
					Runtime.getRuntime().exec("shutdown -h now");
				} catch (IOException e) {
					e.printStackTrace();
					this.setAlwaysOnTop(true);
				}
				break;
			case 1:
				System.exit(0);
				break;

			default:
				JOptionPane.showMessageDialog(null, "Senha inválida");
				break;
			}
			this.setAlwaysOnTop(true);
		}
		panelTelas_1.repaint();
		panelTelas_1.revalidate();
	}
	
	private boolean confirmarMudancaTela()
	{
		return true;
	}
}
