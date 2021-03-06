package Views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewDocumentacao extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public ViewDocumentacao() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 800, 329);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(10, 11, 780, 48);
		panel.add(panel_3);
		
		JLabel lblNewLabel = new JLabel("Sobre");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 34));
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addContainerGap(691, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 68, 780, 181);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Interface Homem M\u00E1quina - Envasadora CMEV12B");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 549, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblClaramaqEnvasadora = new JLabel("ClaraMaq - Envasadora e Automa\u00E7\u00E3o Industrial");
		lblClaramaqEnvasadora.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblClaramaqEnvasadora.setBounds(10, 24, 549, 14);
		panel_1.add(lblClaramaqEnvasadora);
		
		JTextPane txtpnCnpjEndereo = new JTextPane();
		txtpnCnpjEndereo.setEditable(false);
		txtpnCnpjEndereo.setText("CNPJ: 30.762.475/0001-86\r\nEndere\u00E7o: Aviador Max Fontoura, 1011, Distrito Industrial Mau\u00E1, Colombo, PR\r\nDireitos Reservados");
		txtpnCnpjEndereo.setBounds(10, 36, 639, 55);
		panel_1.add(txtpnCnpjEndereo);
		
		JLabel lblSoftware = new JLabel("Software");
		lblSoftware.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSoftware.setBounds(10, 94, 549, 14);
		panel_1.add(lblSoftware);
		
		JTextPane txtpnVersoDoSoftware = new JTextPane();
		txtpnVersoDoSoftware.setEditable(false);
		txtpnVersoDoSoftware.setText("Vers\u00E3o do Software: 0 - Build: 0 - Credits for Icons www.flaticon.com\r\nN\u00FAmero de s\u00E9rie: ASKDB3R4OISBD\r\nC\u00F3pia Licenciada para: Sany do Brasil - 2018");
		txtpnVersoDoSoftware.setBounds(10, 107, 639, 63);
		panel_1.add(txtpnVersoDoSoftware);
		
		JButton btnManualMaquina = new JButton("Manual da M\u00E1quina");
		btnManualMaquina.setEnabled(false);
		btnManualMaquina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actBtnManualMaquina();
			}
		});
		btnManualMaquina.setIcon(new ImageIcon(ViewDocumentacao.class.getResource("/Assets/guide (32).png")));
		btnManualMaquina.setBounds(201, 252, 146, 66);
		btnManualMaquina.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnManualMaquina.setHorizontalTextPosition(SwingConstants.CENTER);
		btnManualMaquina.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel.add(btnManualMaquina);
		
		JButton btnManuaisPerifericos = new JButton("Manuais dos Perif\u00E9ricos");
		btnManuaisPerifericos.setEnabled(false);
		btnManuaisPerifericos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actBtnManuaisPerifericos();
			}
		});
		btnManuaisPerifericos.setIcon(new ImageIcon(ViewDocumentacao.class.getResource("/Assets/guide_two (32).png")));
		btnManuaisPerifericos.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnManuaisPerifericos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnManuaisPerifericos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnManuaisPerifericos.setBounds(357, 252, 159, 66);
		panel.add(btnManuaisPerifericos);
		
		JButton btnEsquemaEltrico = new JButton("Esquema El\u00E9trico");
		btnEsquemaEltrico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actBtnEsquemaEletrico();
			}
		});
		btnEsquemaEltrico.setIcon(new ImageIcon(ViewDocumentacao.class.getResource("/Assets/manual (32).png")));
		btnEsquemaEltrico.setBounds(526, 252, 146, 66);
		panel.add(btnEsquemaEltrico);
		btnEsquemaEltrico.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEsquemaEltrico.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEsquemaEltrico.setFont(new Font("Tahoma", Font.PLAIN, 11));

	}

	protected void actBtnEsquemaEletrico() {
			ViewManuaisFrame manualFrame = new ViewManuaisFrame("/home/pi/EsquemaEletrico.pdf");
			manualFrame.setVisible(true);
	}

	protected void actBtnManuaisPerifericos() {
		ViewManuaisFrame manualFrame = new ViewManuaisFrame("/home/pi/ManuaisPerifericos.pdf");
		manualFrame.setVisible(true);
	}

	protected void actBtnManualMaquina() {
		ViewManuaisFrame manualFrame = new ViewManuaisFrame("/home/pi/ManualMaquina.pdf");
		manualFrame.setVisible(true);
	}
}
