package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Controller.Controller;

public class ViewConfiguracoes extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNomeProduto;
	private JTextField txtTempoEnvase;
	private JTextField txtRetardoEnvase;
	private JTextField txtVelocidadeEnvase;
	private JTextField txtVelocidadeEsteira;
	private JTextField txtPassosTampador;
	private JTextField txtDelayInicioCiclo;

	public ViewConfiguracoes() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 800, 329);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(10, 11, 780, 59);
		panel.add(panel_3);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon(ViewConfiguracoes.class.getResource("/Assets/new-file (32).png")));
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNovo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNovo.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(ViewConfiguracoes.class.getResource("/Assets/floppy-disk (32).png")));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSalvar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalvar.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(ViewConfiguracoes.class.getResource("/Assets/file (32).png")));
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnExcluir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExcluir.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		JButton btnAnteiror = new JButton("Anterior");
		btnAnteiror.setIcon(new ImageIcon(ViewConfiguracoes.class.getResource("/Assets/left-arrow (32).png")));
		btnAnteiror.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAnteiror.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnteiror.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		JButton btnProximo = new JButton("Pr\u00F3ximo");
		btnProximo.setIcon(new ImageIcon(ViewConfiguracoes.class.getResource("/Assets/right-arrow (32).png")));
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnProximo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProximo.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAnteiror, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnProximo, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(254, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnAnteiror, GroupLayout.PREFERRED_SIZE, 57, Short.MAX_VALUE)
						.addComponent(btnProximo, GroupLayout.PREFERRED_SIZE, 57, Short.MAX_VALUE)
						.addComponent(btnSalvar, 0, 0, Short.MAX_VALUE)
						.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 57, Short.MAX_VALUE)
						.addComponent(btnNovo, 0, 0, Short.MAX_VALUE))
					.addGap(2))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 68, 780, 175);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblData_1 = new JLabel("Nome do Produto:");
		lblData_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblData_1.setForeground(Color.BLACK);
		lblData_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblData_1.setBounds(10, 11, 247, 22);
		panel_1.add(lblData_1);
		
		txtNomeProduto = new JTextField();
		txtNomeProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNomeProduto.setText("LavaMax Sany Concentrado - Flor do Campo");
		txtNomeProduto.setBounds(267, 11, 503, 23);
		panel_1.add(txtNomeProduto);
		txtNomeProduto.setColumns(10);
		
		JLabel lblTempoDeEnvase = new JLabel("Tempo de Envase (s):");
		lblTempoDeEnvase.setHorizontalAlignment(SwingConstants.LEFT);
		lblTempoDeEnvase.setForeground(Color.BLACK);
		lblTempoDeEnvase.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTempoDeEnvase.setBounds(10, 54, 247, 22);
		panel_1.add(lblTempoDeEnvase);
		
		JLabel lblTempoDeRetardo = new JLabel("Tempo de Retardo do envase (s):");
		lblTempoDeRetardo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTempoDeRetardo.setForeground(Color.BLACK);
		lblTempoDeRetardo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTempoDeRetardo.setBounds(10, 98, 247, 22);
		panel_1.add(lblTempoDeRetardo);
		
		JLabel lblVelocidadeDeEnvase = new JLabel("Velocidade de Envase (%):");
		lblVelocidadeDeEnvase.setHorizontalAlignment(SwingConstants.LEFT);
		lblVelocidadeDeEnvase.setForeground(Color.BLACK);
		lblVelocidadeDeEnvase.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblVelocidadeDeEnvase.setBounds(10, 142, 229, 22);
		panel_1.add(lblVelocidadeDeEnvase);
		
		txtTempoEnvase = new JTextField();
		txtTempoEnvase.setHorizontalAlignment(SwingConstants.CENTER);
		txtTempoEnvase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTempoEnvase.setText("12");
		txtTempoEnvase.setColumns(10);
		txtTempoEnvase.setBounds(267, 54, 139, 23);
		panel_1.add(txtTempoEnvase);
		
		txtRetardoEnvase = new JTextField();
		txtRetardoEnvase.setText("2");
		txtRetardoEnvase.setHorizontalAlignment(SwingConstants.CENTER);
		txtRetardoEnvase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtRetardoEnvase.setColumns(10);
		txtRetardoEnvase.setBounds(267, 98, 139, 23);
		panel_1.add(txtRetardoEnvase);
		
		txtVelocidadeEnvase = new JTextField();
		txtVelocidadeEnvase.setText("100");
		txtVelocidadeEnvase.setHorizontalAlignment(SwingConstants.CENTER);
		txtVelocidadeEnvase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtVelocidadeEnvase.setColumns(10);
		txtVelocidadeEnvase.setBounds(267, 142, 139, 23);
		panel_1.add(txtVelocidadeEnvase);
		
		JLabel lblVelocidadeDaEsteira = new JLabel("Velocidade da Esteira (%):");
		lblVelocidadeDaEsteira.setHorizontalAlignment(SwingConstants.LEFT);
		lblVelocidadeDaEsteira.setForeground(Color.BLACK);
		lblVelocidadeDaEsteira.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblVelocidadeDaEsteira.setBounds(422, 54, 217, 22);
		panel_1.add(lblVelocidadeDaEsteira);
		
		JLabel lblPassosDoTampador = new JLabel("Passos do Tampador (un):");
		lblPassosDoTampador.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassosDoTampador.setForeground(Color.BLACK);
		lblPassosDoTampador.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPassosDoTampador.setBounds(422, 98, 217, 22);
		panel_1.add(lblPassosDoTampador);
		
		JLabel lblDelayDeInicio = new JLabel("Delay de Inicio do Ciclo (s):");
		lblDelayDeInicio.setHorizontalAlignment(SwingConstants.LEFT);
		lblDelayDeInicio.setForeground(Color.BLACK);
		lblDelayDeInicio.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDelayDeInicio.setBounds(422, 142, 217, 22);
		panel_1.add(lblDelayDeInicio);
		
		txtVelocidadeEsteira = new JTextField();
		txtVelocidadeEsteira.setText("80");
		txtVelocidadeEsteira.setHorizontalAlignment(SwingConstants.CENTER);
		txtVelocidadeEsteira.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtVelocidadeEsteira.setColumns(10);
		txtVelocidadeEsteira.setBounds(631, 53, 139, 23);
		panel_1.add(txtVelocidadeEsteira);
		
		txtPassosTampador = new JTextField();
		txtPassosTampador.setText("457");
		txtPassosTampador.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassosTampador.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassosTampador.setColumns(10);
		txtPassosTampador.setBounds(631, 97, 139, 23);
		panel_1.add(txtPassosTampador);
		
		txtDelayInicioCiclo = new JTextField();
		txtDelayInicioCiclo.setText("2");
		txtDelayInicioCiclo.setHorizontalAlignment(SwingConstants.CENTER);
		txtDelayInicioCiclo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDelayInicioCiclo.setColumns(10);
		txtDelayInicioCiclo.setBounds(631, 141, 139, 23);
		panel_1.add(txtDelayInicioCiclo);
		
		JButton btnRelatorioErros = new JButton("Relat\u00F3rio de Erros");
		btnRelatorioErros.setIcon(new ImageIcon(ViewConfiguracoes.class.getResource("/Assets/computer (32).png")));
		btnRelatorioErros.setBounds(10, 252, 124, 66);
		btnRelatorioErros.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRelatorioErros.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRelatorioErros.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel.add(btnRelatorioErros);

	}
}
