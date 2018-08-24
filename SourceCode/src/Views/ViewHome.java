package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Controller.Controller;
import Controller.ControllerHome;
import DAO.Maquina;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ViewHome extends JPanel {

	private static final long serialVersionUID = 1L;
	private JToggleButton tglbtnIniciarCiclo;
	private JToggleButton tglbtnOff;
	private JLabel lblNomeProduto;
	private JLabel lblInicioProducao;
	private JLabel lblMetaProducao;
	private JLabel lblProduzido;
	private JLabel lblFaltando;
	private JLabel lblPrevisaoTermino;
	private JLabel lblTempoCiclo;
	private JLabel lblProximaManutencao;
	private ControllerHome control;
	private JFrame frame;

	public ViewHome(JFrame frame) {
		control = new ControllerHome();
		this.frame = frame;
		setLayout(null);
		setBackground(SystemColor.textHighlight);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 800, 329);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(65, 220, 271, 90);
		panel.add(panel_2);
		
		tglbtnIniciarCiclo = new JToggleButton("ON");
		tglbtnIniciarCiclo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Abrir a tela de inicio de ciclo, com os parametros configurados
				frame.setAlwaysOnTop(false);
				ViewIniciarCicloDialog viewIniciarCiclo = new ViewIniciarCicloDialog(control);
				viewIniciarCiclo.setModal(true);
				viewIniciarCiclo.setVisible(true);
				
				if(viewIniciarCiclo.clickOk)
				{
					lblNomeProduto.setText(viewIniciarCiclo.getProduto());
					//lblInicioProducao.setText(viewIniciarCiclo.);
					lblMetaProducao.setText(viewIniciarCiclo.getMetaProducao());
				}
				viewIniciarCiclo.dispose();
				frame.setAlwaysOnTop(true);
				
			}
		});
		tglbtnIniciarCiclo.setBackground(Color.GRAY);
		tglbtnIniciarCiclo.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		tglbtnOff = new JToggleButton("OFF");
		tglbtnOff.setSelected(true);
		tglbtnOff.setFont(new Font("Tahoma", Font.PLAIN, 28));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(tglbtnIniciarCiclo, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnOff, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(tglbtnOff, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnIniciarCiclo, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.WHITE));
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBounds(402, 11, 10, 301);
		panel.add(panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(null);
		panel_3.setBounds(26, 11, 341, 31);
		panel.add(panel_3);
		
		JLabel lblStatusDaProducao = new JLabel("Status da Produ\u00E7\u00E3o");
		lblStatusDaProducao.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatusDaProducao.setForeground(Color.RED);
		lblStatusDaProducao.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(73)
					.addComponent(lblStatusDaProducao)
					.addContainerGap(72, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(lblStatusDaProducao)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(null);
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(439, 11, 330, 31);
		panel.add(panel_4);
		
		JLabel lblStatusDaMaquina = new JLabel("Status da M\u00E1quina");
		lblStatusDaMaquina.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatusDaMaquina.setForeground(Color.RED);
		lblStatusDaMaquina.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGap(0, 330, Short.MAX_VALUE)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(73)
					.addComponent(lblStatusDaMaquina)
					.addContainerGap(72, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGap(0, 31, Short.MAX_VALUE)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(lblStatusDaMaquina)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProduto.setBounds(26, 53, 81, 22);
		panel.add(lblProduto);
		
		lblNomeProduto = new JLabel("");
		lblNomeProduto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeProduto.setBounds(116, 53, 253, 22);
		panel.add(lblNomeProduto);
		
		JLabel lblInicio = new JLabel("Inicio:");
		lblInicio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInicio.setBounds(26, 101, 81, 22);
		panel.add(lblInicio);
		
		JLabel lblMeta = new JLabel("Meta:");
		lblMeta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMeta.setBounds(26, 123, 81, 22);
		panel.add(lblMeta);
		
		JLabel lblDescProduzido = new JLabel("Produzido:");
		lblDescProduzido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescProduzido.setBounds(26, 144, 96, 22);
		panel.add(lblDescProduzido);
		
		JLabel lblDescFaltando = new JLabel("Faltando:");
		lblDescFaltando.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescFaltando.setBounds(26, 166, 81, 22);
		panel.add(lblDescFaltando);
		
		JLabel lblPrevisoDeTrmino = new JLabel("Previs\u00E3o de T\u00E9rmino: ");
		lblPrevisoDeTrmino.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrevisoDeTrmino.setBounds(26, 187, 173, 22);
		panel.add(lblPrevisoDeTrmino);
		
		lblInicioProducao = new JLabel("");
		lblInicioProducao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInicioProducao.setBounds(130, 101, 81, 22);
		panel.add(lblInicioProducao);
		
		lblMetaProducao = new JLabel("");
		lblMetaProducao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMetaProducao.setBounds(132, 123, 81, 22);
		panel.add(lblMetaProducao);
		
		lblProduzido = new JLabel("");
		lblProduzido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProduzido.setBounds(132, 144, 96, 22);
		panel.add(lblProduzido);
		
		lblFaltando = new JLabel("");
		lblFaltando.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFaltando.setBounds(130, 166, 81, 22);
		panel.add(lblFaltando);
		
		lblPrevisaoTermino = new JLabel("");
		lblPrevisaoTermino.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrevisaoTermino.setBounds(196, 187, 173, 22);
		panel.add(lblPrevisaoTermino);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEstado.setBounds(439, 53, 81, 22);
		panel.add(lblEstado);
		
		JLabel lblTempoDeCiclo = new JLabel("Tempo de Ciclo:");
		lblTempoDeCiclo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTempoDeCiclo.setBounds(439, 89, 135, 22);
		panel.add(lblTempoDeCiclo);
		
		JLabel lblPrximaManuteno = new JLabel("Pr\u00F3xima Manuten\u00E7\u00E3o:");
		lblPrximaManuteno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrximaManuteno.setBounds(439, 123, 182, 22);
		panel.add(lblPrximaManuteno);
		
		JLabel lblEstadoMaquina = new JLabel("Aguardando Ciclo");
		lblEstadoMaquina.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEstadoMaquina.setBounds(581, 53, 188, 22);
		panel.add(lblEstadoMaquina);
		
		lblTempoCiclo = new JLabel("");
		lblTempoCiclo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTempoCiclo.setBounds(584, 89, 81, 22);
		panel.add(lblTempoCiclo);
		
		lblProximaManutencao = new JLabel("13/09/2018");
		lblProximaManutencao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProximaManutencao.setBounds(610, 123, 106, 22);
		panel.add(lblProximaManutencao);
		
		JLabel lblUltimaFalha = new JLabel("Ultimas falhas:");
		lblUltimaFalha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUltimaFalha.setBounds(439, 156, 182, 22);
		panel.add(lblUltimaFalha);
		
		JTextPane txtpnLAs = new JTextPane();
		txtpnLAs.setEditable(false);
		txtpnLAs.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtpnLAs.setBounds(439, 187, 330, 106);
		panel.add(txtpnLAs);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLimpar.setBounds(680, 295, 89, 23);
		panel.add(btnLimpar);

		CarregarEstadoInicial();
	}

	private void CarregarEstadoInicial()
	{
		/*
		Maquina maq = new Maquina(); 
		maq = control.dadosIniciaisMaquina();
		lblNomeProduto.setText(maq.getModelo());
		*/
	}
}
