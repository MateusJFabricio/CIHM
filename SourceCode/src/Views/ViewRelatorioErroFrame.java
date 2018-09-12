package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerRelErros;
import DAO.LogFalhas;

public class ViewRelatorioErroFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblRelatrioDeFalhas;
	private JLabel lblOsDadosSo;
	private JLabel lblSoArmazenadoSomente;
	private ControllerRelErros control;
	private ArrayList<LogFalhas> listProducao;
	
	public ViewRelatorioErroFrame() {
		Controller.Controller.janelaPrincipal.setAlwaysOnTop(false);
		setType(Type.UTILITY);
		control = new ControllerRelErros();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(Color.WHITE);
		
		scrollPane = new JScrollPane();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"C\u00F3digo da Falha", "Hora", "Data", "Falha"
			}
		) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(131);
		table.getColumnModel().getColumn(3).setPreferredWidth(343);
		scrollPane.setViewportView(table);
		panel.setLayout(null);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actBtnSalvar();
			}
		});
		btnSalvar.setIcon(new ImageIcon(ViewRelatorioErroFrame.class.getResource("/Assets/csv (32).png")));
		btnSalvar.setBounds(677, 11, 97, 61);
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalvar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalvar.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel.add(btnSalvar);
		
		lblRelatrioDeFalhas = new JLabel("Relat\u00F3rio de Falhas da M\u00E1quina");
		lblRelatrioDeFalhas.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRelatrioDeFalhas.setBounds(10, 11, 351, 34);
		panel.add(lblRelatrioDeFalhas);
		
		lblOsDadosSo = new JLabel("Os dados s\u00E3o apresentados em ordem decrescente da data da falha");
		lblOsDadosSo.setBounds(10, 54, 537, 14);
		panel.add(lblOsDadosSo);
		
		lblSoArmazenadoSomente = new JLabel("S\u00E3o armazenado somente as falhas dos \u00FAltimos 6 meses");
		lblSoArmazenadoSomente.setBounds(10, 65, 537, 14);
		panel.add(lblSoArmazenadoSomente);
		
		
		contentPane.setLayout(gl_contentPane);	
		carregarDadosIniciais();
	}
	
	protected void actBtnSalvar() {
		if (!listProducao.isEmpty())
		{
			control.salvarCSV(listProducao);
		}
	}

	private void carregarDadosIniciais()
	{
		listProducao = new ArrayList<LogFalhas>();
		listProducao = control.buscarDados();
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		int count = modelo.getRowCount();
		for (int i = 0; i < count; i++) {
			modelo.removeRow(0);
		}

		for (LogFalhas logFalhas : listProducao) {
			modelo.addRow(new Object[] {
					logFalhas.getId(),
					logFalhas.getDataHora(),
					logFalhas.getDataHora(),
					logFalhas.getFalha()
					});
		}
	}
}
