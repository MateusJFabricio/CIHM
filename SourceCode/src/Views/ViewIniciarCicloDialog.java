package Views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import DAO.Produto;

public class ViewIniciarCicloDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtMeta;
	private JPanel buttonPane;
	private JLabel lblMetaDeProducao;
	
	private JRadioButton rbProdContinua;
	private JRadioButton rbProdProgramada;
	private JComboBox<String> cbProdutos;
	private ViewHome frame;
	private JButton btnOk;
	private ArrayList<Produto> listProd;
	
	
	public String getProduto() {
		return cbProdutos.getSelectedItem().toString();
	}

	public String getTipoProducao() {
		if (rbProdContinua.isSelected())
		{
			return "Contínua";
		}else 
			return "Programada";
	}

	public String getMetaProducao() {
		return txtMeta.getText();
	}

	public ViewIniciarCicloDialog(ViewHome frame) {
		setType(Type.UTILITY);
		this.frame = frame;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		getContentPane().setBackground(new Color(30, 144, 255));
		setBounds(100, 100, 468, 285);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		cbProdutos = new JComboBox<String>();
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.PLAIN, 24));
		JLabel lblTipoDeProduo = new JLabel("Tipo de Produ\u00E7\u00E3o:");
		lblTipoDeProduo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMetaDeProducao = new JLabel("Meta de Produ\u00E7\u00E3o (un):");
		lblMetaDeProducao.setEnabled(false);
		lblMetaDeProducao.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtMeta = new JTextField();
		txtMeta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (txtMeta.isEnabled())
				{
					Main.Main.teclado.setNumberOnly(true);
					Main.Main.teclado.showKeyboard();
				}
			}
		});
		txtMeta.setEnabled(false);
		txtMeta.setColumns(10);
		
		rbProdContinua = new JRadioButton("Produ\u00E7\u00E3o Cont\u00EDnua");
		rbProdContinua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMetaDeProducao.setEnabled(false);
				txtMeta.setText(null);
				txtMeta.setEnabled(false);
			}
		});
		rbProdContinua.setSelected(true);
		rbProdContinua.setBackground(Color.WHITE);
		rbProdContinua.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonGroup.add(rbProdContinua);
		rbProdProgramada = new JRadioButton("Produ\u00E7\u00E3o Programada");
		rbProdProgramada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMetaDeProducao.setEnabled(true);
				txtMeta.setEnabled(true);
			}
		});
		rbProdProgramada.setBackground(Color.WHITE);
		rbProdProgramada.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonGroup.add(rbProdProgramada);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblMetaDeProducao)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtMeta, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblProduto)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(cbProdutos, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblTipoDeProduo, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(rbProdProgramada)
									.addComponent(rbProdContinua)))))
					.addGap(39))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblProduto)
						.addComponent(cbProdutos, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTipoDeProduo, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(rbProdContinua)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rbProdProgramada)))
					.addGap(8)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtMeta)
						.addComponent(lblMetaDeProducao, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
					.addGap(25))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						acaoBotaoOk();
					}
				});
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						acaoBotaoCancelar();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 462, Short.MAX_VALUE)
				.addComponent(buttonPane, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		
		JLabel lblSelecioneOsParmetro = new JLabel("Selecione os Par\u00E2metro de Produ\u00E7\u00E3o");
		lblSelecioneOsParmetro.setForeground(Color.RED);
		lblSelecioneOsParmetro.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(lblSelecioneOsParmetro);
		getContentPane().setLayout(groupLayout);
		
		inicializarDados();
		verificaProducaoNaoTerminada();
	}
	
	private void verificaProducaoNaoTerminada()
	{
		
	}
	private void inicializarDados(){
		//Solicita para a Controller todos os produtos
		listProd = frame.control.getProdutos();
		
		if (listProd.size()==0)
		{
			JOptionPane.showMessageDialog(null, "Você não tem nenhum produto cadastrado. Acesse a tela de Configuração.");
			rbProdContinua.setEnabled(false);
			rbProdProgramada.setEnabled(false);
			txtMeta.setEnabled(false);
			lblMetaDeProducao.setEnabled(false);
			btnOk.setEnabled(false);
			
		}else{
			for(Produto produto: listProd)
			{
				cbProdutos.addItem(produto.getNome());
			}
			rbProdContinua.setSelected(true);
			rbProdProgramada.setSelected(false);
			txtMeta.setText(null);
			txtMeta.setEnabled(false);
			lblMetaDeProducao.setEnabled(false);
		}
	}
	
	private void acaoBotaoOk()
	{
		int meta = 0;
		//Valida meta
		if (rbProdProgramada.isSelected())
		{
			if (!validarMeta())
			{
				JOptionPane.showMessageDialog(null, "Meta invalida!");
				return;
			}
			meta = Integer.parseInt(txtMeta.getText());
		}
		
		Object[] options = { "Sim", "Não" };
		int opcao = JOptionPane.showOptionDialog(null, "Há frascos já em posição de envase?", "Pergunta", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		boolean frascosPosicionados = (opcao == 0);
		
		frame.carregarDadosViewIniciaCiclo();
		
		Produto produto = new Produto();
		for(Produto produtoTemp: listProd)
		{
			if (produtoTemp.getNome().equals(cbProdutos.getSelectedItem().toString()))
			{
				produto = produtoTemp;
				break;
			}
		}
		
		frame.viewIniciaCiclo(produto, frascosPosicionados, meta);
		this.dispose();
	}
	
	private boolean validarMeta() {
		try {
	        if (Integer.parseInt(txtMeta.getText()) > 0)
	        	return true;
	        else
	        	return false;
	    } catch (NumberFormatException ex) {
	        return false;
	    }
	}

	private void acaoBotaoCancelar()
	{
		this.dispose();
	}

}
