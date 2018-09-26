package Views;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Controller.Controller;
import Controller.ControllerConfiguracoes;
import DAO.Produto;


public class ViewConfiguracoes extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private JTextField txtNomeProduto;
	private JTextField txtTempoEnvase;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnNovo;
	private JButton btnSalvar;
	private JButton btnExcluir;
	
	private ControllerConfiguracoes control;
	private ArrayList<Produto> listProduto;
	private Produto produto;
	private int indexProduto = 0;
	
	public ViewConfiguracoes() {
		setLayout(null);
		
		control = new ControllerConfiguracoes();
		listProduto = new ArrayList<Produto>();
		produto = new Produto();
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 800, 329);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(10, 8, 764, 66);
		panel.add(panel_3);
		
		btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 4, 90, 57);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actBtnNovo();
			}
		});
		btnNovo.setIcon(new ImageIcon(ViewConfiguracoes.class.getResource("/Assets/new-file (32).png")));
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNovo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNovo.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actBtnSalvar();
			}
		});
		btnSalvar.setBounds(107, 4, 100, 57);
		btnSalvar.setIcon(new ImageIcon(ViewConfiguracoes.class.getResource("/Assets/floppy-disk (32).png")));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSalvar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalvar.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actBtnExcluir();
			}
		});
		btnExcluir.setBounds(213, 4, 100, 57);
		btnExcluir.setIcon(new ImageIcon(ViewConfiguracoes.class.getResource("/Assets/file (32).png")));
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnExcluir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExcluir.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		btnAnterior = new JButton("Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				anteriorProduto();
			}
		});
		btnAnterior.setBounds(319, 4, 100, 57);
		btnAnterior.setIcon(new ImageIcon(ViewConfiguracoes.class.getResource("/Assets/left-arrow (32).png")));
		btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAnterior.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnterior.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		btnProximo = new JButton("Pr\u00F3ximo");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				proximoProduto();
			}
		});
		btnProximo.setBounds(425, 4, 100, 57);
		btnProximo.setIcon(new ImageIcon(ViewConfiguracoes.class.getResource("/Assets/right-arrow (32).png")));
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnProximo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProximo.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel_3.setLayout(null);
		panel_3.add(btnNovo);
		panel_3.add(btnSalvar);
		panel_3.add(btnExcluir);
		panel_3.add(btnAnterior);
		panel_3.add(btnProximo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 74, 764, 169);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblData_1 = new JLabel("Nome do Produto:");
		lblData_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblData_1.setForeground(Color.BLACK);
		lblData_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblData_1.setBounds(10, 11, 247, 22);
		panel_1.add(lblData_1);
		
		txtNomeProduto = new JTextField();
		txtNomeProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Main.Main.teclado.showKeyboard();
			}
		});
		txtNomeProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNomeProduto.setBounds(267, 11, 487, 23);
		panel_1.add(txtNomeProduto);
		txtNomeProduto.setColumns(10);
		
		JLabel lblTempoDeEnvase = new JLabel("Tempo de Envase (s):");
		lblTempoDeEnvase.setHorizontalAlignment(SwingConstants.LEFT);
		lblTempoDeEnvase.setForeground(Color.BLACK);
		lblTempoDeEnvase.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTempoDeEnvase.setBounds(10, 54, 247, 22);
		panel_1.add(lblTempoDeEnvase);
		
		txtTempoEnvase = new JTextField();
		txtTempoEnvase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.Main.teclado.setNumberOnly(true);
				Main.Main.teclado.showKeyboard();
			}
		});
		txtTempoEnvase.setHorizontalAlignment(SwingConstants.CENTER);
		txtTempoEnvase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTempoEnvase.setColumns(10);
		txtTempoEnvase.setBounds(267, 54, 139, 23);
		panel_1.add(txtTempoEnvase);
		
		JButton btnRelatorioErros = new JButton("Relat\u00F3rio de Erros");
		btnRelatorioErros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actBtnRelatoriosErros();
			}
		});
		btnRelatorioErros.setIcon(new ImageIcon(ViewConfiguracoes.class.getResource("/Assets/computer (32).png")));
		btnRelatorioErros.setBounds(10, 252, 203, 66);
		btnRelatorioErros.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRelatorioErros.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRelatorioErros.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel.add(btnRelatorioErros);
		
		JButton btnTesteParametros = new JButton("Testar Par\u00E2metros");
		btnTesteParametros.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnTesteParametros.setHorizontalTextPosition(SwingConstants.CENTER);
		btnTesteParametros.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnTesteParametros.setBounds(571, 254, 203, 66);
		panel.add(btnTesteParametros);
		
		carregarDadosIniciais();
		txtNomeProduto.setEditable(false);
		txtTempoEnvase.setEditable(false);
		btnSalvar.setEnabled(false);
		
		JButton btnMonitorGPIO = new JButton("Monitor Input/Output");
		btnMonitorGPIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actMonitorpInOut();
			}
		});
		btnMonitorGPIO.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnMonitorGPIO.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMonitorGPIO.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMonitorGPIO.setBounds(294, 252, 203, 66);
		panel.add(btnMonitorGPIO);
	}
	
	protected void actMonitorpInOut() {
		
		ViewSenha vSenha = new ViewSenha();
		vSenha.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		vSenha.setModal(true);
		vSenha.setVisible(true);
		int tipoSenha = vSenha.tipoSenha;
		vSenha.dispose();
		if (tipoSenha == 1)
		{
			ViewMonitorGPIO view = new ViewMonitorGPIO();
			Controller.janelaPrincipal.setAlwaysOnTop(false);
			view.setVisible(true);
		}else
		{
			JOptionPane.showMessageDialog(null, "Senha invalida");
		}
		
		
	}

	protected void actBtnRelatoriosErros() {
		ViewRelatorioErroFrame relErros = new ViewRelatorioErroFrame();
		relErros.setVisible(true);
	}

	protected void actBtnExcluir() {
		if (listProduto.isEmpty())
			return;
		
		control.excluirProduto(produto);
		listProduto.remove(produto);
		indexProduto--;
		if (indexProduto < 0)
		{
			indexProduto = 0;
			
			if (!listProduto.isEmpty())
				produto = listProduto.get(indexProduto);
			else
				produto = null;
		}else
			produto = listProduto.get(indexProduto);
		
		loadProduto();
	}

	protected void actBtnNovo() {
		
		txtNomeProduto.setEditable(true);
		txtNomeProduto.setText("");
		
		txtTempoEnvase.setEditable(true);
		txtTempoEnvase.setText("");
		
		btnProximo.setEnabled(false);
		btnAnterior.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnNovo.setEnabled(false);
		btnSalvar.setEnabled(true);
		
	}
	
	protected void actBtnSalvar() {
				
		if (txtNomeProduto.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Você não preencheu o nome do produto");
			return;
		}
		
		for (Produto temp_produto : listProduto) {
			if (txtNomeProduto.getText().equals(temp_produto.getNome()))
			{
				JOptionPane.showMessageDialog(null, "Este nome de produto já esta cadastrado");;
				return;
			}
		}
		
		if (txtTempoEnvase.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Você não preencheu o tempo de ciclo");
			return;
		}
		
		int tempoEnvase = 0;
		try
		{
			tempoEnvase = Integer.parseInt(txtTempoEnvase.getText());
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Há um problema com o tempo de envase. Deve conter apenas números.");
			return;
		}
		
		if (!btnNovo.isEnabled())
		{
			txtNomeProduto.setEditable(false);
			txtTempoEnvase.setEditable(false);
			
			btnProximo.setEnabled(true);
			btnAnterior.setEnabled(true);
			btnExcluir.setEnabled(true);
			btnNovo.setEnabled(true);
		}
		
		if (produto == null)
			produto = new Produto();
		
		produto.setNome(txtNomeProduto.getText());
		produto.setTempoEnvase(tempoEnvase);
		control.inserirNovoProduto(produto);
		
		
		listProduto = control.buscarProdutosCadastrados();
	
		for (Produto tempProduto : listProduto) {
			if (tempProduto.getNome().equals(produto.getNome()))
			{
				indexProduto = listProduto.indexOf(tempProduto);
				break;
			}
		}
		produto = listProduto.get(indexProduto);
		loadProduto();
		btnSalvar.setEnabled(false);
	
	}

	private void carregarDadosIniciais()
	{
		listProduto = control.buscarProdutosCadastrados();
		if (!listProduto.isEmpty())
		{
			produto = listProduto.get(0);
			indexProduto = 0;
			loadProduto();
		}
				
	}

	private void anteriorProduto() {
		if (!listProduto.isEmpty())
		{
			if (indexProduto > 0)
			{
				indexProduto--;
				produto = listProduto.get(indexProduto);
			}
			loadProduto();
		}
	}

	private void proximoProduto() {
		if (!listProduto.isEmpty())
		{
			if (indexProduto < listProduto.size() - 1)
			{
				indexProduto++;
				produto = listProduto.get(indexProduto);
			}

			loadProduto();
		}
		
	}
	
	private void loadProduto()
	{
		if (produto == null)
		{
			txtNomeProduto.setText("");
			txtTempoEnvase.setText("");
		}else
		{
			txtNomeProduto.setText(produto.getNome());
			txtTempoEnvase.setText(String.valueOf(produto.getTempoEnvase()));
		}
	}
}
