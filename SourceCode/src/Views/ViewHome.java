package Views;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import Background.Services.CommFrame;
import Controller.ControllerHome;
import DAO.Maquina;

public class ViewHome extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblNomeProduto;
	private JLabel lblInicioProducao;
	private JLabel lblMetaProducao;
	private JLabel lblProduzido;
	private JLabel lblFaltando;
	private JLabel lblTempoCiclo;
	public ControllerHome control;
	ViewIniciarCicloDialog viewIniciarCiclo;
	private JButton btnLiga;
	private JButton btnDesliga;
	private ActionListener actMonitorEstado;
	private Timer timerMonitorEstado;
	private CommFrame comunicador;
	private JLabel lblEstadoMaquina;
	private JTextPane txtFalhas;
	private int initProd_Hora = 0, initProd_Min = 0, initProd_Seg = 0; 

	public ViewHome(JFrame frame) {
		control = new ControllerHome();
		comunicador = new CommFrame();
		setLayout(null);
		setBackground(SystemColor.textHighlight);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 800, 329);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(65, 220, 271, 90);
		panel.add(panel_2);
		
		btnLiga = new JButton("LIGA");
		btnDesliga = new JButton("DESLIGA");
		btnDesliga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actBtnDesliga();
			}
		});
		
		btnLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actBtnLiga();
			}
		});
		
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLiga, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addComponent(btnDesliga, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDesliga, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLiga, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.WHITE));
		panel_1.setBackground(new Color(0, 0, 205));
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
		
		lblInicioProducao = new JLabel("");
		lblInicioProducao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInicioProducao.setBounds(163, 101, 214, 22);
		panel.add(lblInicioProducao);
		
		lblMetaProducao = new JLabel("");
		lblMetaProducao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMetaProducao.setBounds(163, 123, 214, 22);
		panel.add(lblMetaProducao);
		
		lblProduzido = new JLabel("");
		lblProduzido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProduzido.setBounds(163, 144, 214, 22);
		panel.add(lblProduzido);
		
		lblFaltando = new JLabel("");
		lblFaltando.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFaltando.setBounds(163, 166, 214, 22);
		panel.add(lblFaltando);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEstado.setBounds(439, 53, 158, 22);
		panel.add(lblEstado);
		
		JLabel lblTempoDeCiclo = new JLabel("Tempo de Ciclo:");
		lblTempoDeCiclo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTempoDeCiclo.setBounds(439, 89, 158, 22);
		panel.add(lblTempoDeCiclo);
		
		lblEstadoMaquina = new JLabel("Aguardando Ciclo");
		lblEstadoMaquina.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEstadoMaquina.setBounds(599, 53, 173, 22);
		panel.add(lblEstadoMaquina);
		
		lblTempoCiclo = new JLabel("");
		lblTempoCiclo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTempoCiclo.setBounds(599, 89, 170, 22);
		panel.add(lblTempoCiclo);
		
		JLabel lblUltimaFalha = new JLabel("Ultimas falhas:");
		lblUltimaFalha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUltimaFalha.setBounds(439, 156, 158, 22);
		panel.add(lblUltimaFalha);
		
		txtFalhas = new JTextPane();
		txtFalhas.setEditable(false);
		txtFalhas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtFalhas.setBounds(439, 187, 330, 106);
		panel.add(txtFalhas);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtFalhas.setText("");
			}
		});
		btnLimpar.setBounds(680, 295, 89, 23);
		panel.add(btnLimpar);

		CarregarEstadoInicial();
		btnLiga.setEnabled(true);
		btnDesliga.setEnabled(false);
		iniciaMonitorEstado();
	}

	private void CarregarEstadoInicial()
	{
		
		Maquina maq = new Maquina(); 
		maq = control.dadosMaquina();
	}
	
	public void carregarDadosViewIniciaCiclo()
	{
		lblNomeProduto.setText(viewIniciarCiclo.getProdutoSelecionado().getNome());
		lblInicioProducao.setText(gravarDataHoraInicioProd());
		if (String.valueOf(viewIniciarCiclo.getMetaProducao()).equals(""))
			lblMetaProducao.setText("Modo Cont�nuo");
		else
			lblMetaProducao.setText(String.valueOf(viewIniciarCiclo.getMetaProducao()));
		
		lblProduzido.setText("0");
		lblFaltando.setText(String.valueOf(viewIniciarCiclo.getMetaProducao()));
		try {
			lblTempoCiclo.setText(String.valueOf(viewIniciarCiclo.getProdutoSelecionado().getTempoEnvase() / 12));
		} catch (Exception e) {
			lblTempoCiclo.setText("Indeterminado");
		}
		lblEstadoMaquina.setText("Em ciclo");
		
	}
	
	private String gravarDataHoraInicioProd() {
        Calendar data = Calendar.getInstance();
        initProd_Hora = data.get(Calendar.HOUR_OF_DAY);
        initProd_Min = data.get(Calendar.MINUTE);
        initProd_Seg = data.get(Calendar.SECOND);
        return String.valueOf(initProd_Hora) + ":" + String.valueOf(initProd_Min) + ":" + String.valueOf(initProd_Seg);
	}

	private void actBtnLiga()
	{
		control.atualizarDados(comunicador);
		if (comunicador.isEmergencia())
		{
			JOptionPane.showMessageDialog(this, "Emergencia acionado. Desabilite para prosseguir");
			return;
		}
			
		viewIniciarCiclo = new ViewIniciarCicloDialog(this);
		viewIniciarCiclo.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		viewIniciarCiclo.setModal(true);
		viewIniciarCiclo.setVisible(true);
		
		if (viewIniciarCiclo.comando == 1)
		{
			btnLiga.setEnabled(false);
			btnDesliga.setEnabled(true);
			carregarDadosViewIniciaCiclo();
			control.iniciarCiclo(viewIniciarCiclo.getProdutoSelecionado(), viewIniciarCiclo.isFrascosPosicionados(), viewIniciarCiclo.getMetaProducao());
		}
		
		viewIniciarCiclo.comando = 0;
		viewIniciarCiclo.dispose();
		timerMonitorEstado.start();
	}
	
	private void actBtnDesliga()
	{
		control.atualizarDados(comunicador);
		int meta = Integer.parseInt(lblMetaProducao.getText());
		
		btnLiga.setEnabled(true);
		btnDesliga.setEnabled(false);
		control.interromperCiclo(comunicador.getProduzido());
		timerMonitorEstado.stop();
		
		lblEstadoMaquina.setText("Aguardando ciclo");
		lblNomeProduto.setText("");
		lblMetaProducao.setText("");
		lblFaltando.setText("");
		lblTempoCiclo.setText("");
		lblInicioProducao.setText("");
		lblProduzido.setText("");

	}
	
	private void iniciaMonitorEstado() {
		actMonitorEstado = new ActionListener() {
			private boolean msgEmergenciaRegistrada = false;
			private boolean msgNivelBaixoRegistrada;
			private boolean msgNivelAltoRegistrada;
			public void actionPerformed(java.awt.event.ActionEvent e) {
				control.atualizarDados(comunicador);
				
				if (comunicador.isEmergencia())
				{
					lblEstadoMaquina.setText("Em Emerg�ncia");
					actBtnDesliga();
				}
				
				int meta = Integer.parseInt(lblMetaProducao.getText());
				if (meta > 0)
				{
					lblProduzido.setText(String.valueOf(comunicador.getProduzido()));
					lblFaltando.setText(String.valueOf(meta - comunicador.getProduzido()));
				}else
				{
					lblProduzido.setText(String.valueOf(comunicador.getProduzido()));
				}
				
				lblTempoCiclo.setText(buscarTempoCiclo(comunicador.getProduzido()));
				
				//Mensagens
				if (comunicador.isEmergencia() && !msgEmergenciaRegistrada )
				{
					txtFalhas. setText(txtFalhas.getText() + "Emergencia acionado - Hora: "+ retornarDataHora() +"\n");
					msgEmergenciaRegistrada = true;
				}else if (!comunicador.isEmergencia() && msgEmergenciaRegistrada)
					msgEmergenciaRegistrada = false;
				
				if (comunicador.isNivelBaixo() && !msgNivelBaixoRegistrada)
				{
					txtFalhas. setText(txtFalhas.getText() + "Nivel baixo de produto - Hora: "+ retornarDataHora() +"\n");
					msgNivelBaixoRegistrada = true;
				}else if (!comunicador.isNivelBaixo() && msgNivelBaixoRegistrada)
				{
					msgNivelBaixoRegistrada = false;
				}
				
				if (comunicador.isNivelAlto() && !msgNivelAltoRegistrada)
				{
					txtFalhas.setText(txtFalhas.getText() + "Nivel alto de produto - Hora: "+ retornarDataHora() +" \n");
					msgNivelAltoRegistrada = true;
				}else if (!comunicador.isNivelAlto() && msgNivelAltoRegistrada)
				{
					msgNivelAltoRegistrada = false;
				}
				
		}};
		
		timerMonitorEstado = new Timer(1000, actMonitorEstado);
	}
	
	protected String buscarTempoCiclo(int produzido) {
		Calendar data = Calendar.getInstance();
		int tempoProducao;
		int hora = (data.get(Calendar.HOUR) - initProd_Hora) * 3600;
		if (hora < 0 )
			hora = (24 - initProd_Hora + data.get(Calendar.HOUR)) * 3600;
		
		int min = ((data.get(Calendar.MINUTE) - initProd_Min) * 60);
		if (min < 0)
			min = (60 - initProd_Min + data.get(Calendar.MINUTE)) * 60;
		
		int seg =  (data.get(Calendar.SECOND) - initProd_Seg); 
		if (seg < 0)
			seg =  60 - initProd_Seg + data.get(Calendar.SECOND);
		
		tempoProducao = hora + min + seg;
		try {
			return String.valueOf(tempoProducao / produzido);
		} catch (Exception e) {
			return "N�o foi possivel contabilizar";
		}
	}

	private String retornarDataHora()
	{
        Calendar data = Calendar.getInstance();
        return String.valueOf(data.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(data.get(Calendar.MINUTE)) + ":" + String.valueOf(data.get(Calendar.SECOND));
	}
}
