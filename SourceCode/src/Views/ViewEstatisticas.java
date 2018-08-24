package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;

public class ViewEstatisticas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tblHistoricoProd;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public ViewEstatisticas() {
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
		panel_3.setBounds(10, 11, 780, 46);
		panel.add(panel_3);
		
		JLabel lblStatusDaProduo = new JLabel("Tempo M\u00E9dio de Ciclo:");
		lblStatusDaProduo.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatusDaProduo.setForeground(Color.BLACK);
		lblStatusDaProduo.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		JLabel lblSeg = new JLabel("36 seg");
		lblSeg.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeg.setForeground(Color.BLUE);
		lblSeg.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		JLabel lblProduoMdiaDiria = new JLabel("Produ\u00E7\u00E3o M\u00E9dia Di\u00E1ria:");
		lblProduoMdiaDiria.setHorizontalAlignment(SwingConstants.CENTER);
		lblProduoMdiaDiria.setForeground(Color.BLACK);
		lblProduoMdiaDiria.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		JLabel lblUn = new JLabel("4300 un");
		lblUn.setHorizontalAlignment(SwingConstants.CENTER);
		lblUn.setForeground(Color.BLUE);
		lblUn.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblStatusDaProduo, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSeg, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblProduoMdiaDiria, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUn, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUn, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProduoMdiaDiria, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSeg, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStatusDaProduo))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 68, 780, 250);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.ORANGE);
		panel_2.setBounds(0, 0, 780, 34);
		panel_1.add(panel_2);
		
		JLabel lblData = new JLabel("Hist\u00F3rico de Produ\u00E7\u00E3o");
		panel_2.add(lblData);
		lblData.setBackground(SystemColor.activeCaption);
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setForeground(Color.BLACK);
		lblData.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		JRadioButton rdbtnHg = new JRadioButton("Maior que");
		rdbtnHg.setBackground(Color.WHITE);
		buttonGroup.add(rdbtnHg);
		rdbtnHg.setBounds(412, 58, 109, 14);
		panel_1.add(rdbtnHg);
		
		JLabel lblData_1 = new JLabel("Data (dia/mes/ano):");
		lblData_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblData_1.setForeground(Color.BLACK);
		lblData_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblData_1.setBounds(10, 38, 159, 22);
		panel_1.add(lblData_1);
		
		textField = new JTextField();
		textField.setText("28/05/2018");
		textField.setBounds(168, 41, 100, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnIgual = new JRadioButton("Igual");
		rdbtnIgual.setBackground(Color.WHITE);
		buttonGroup.add(rdbtnIgual);
		rdbtnIgual.setBounds(412, 41, 109, 14);
		panel_1.add(rdbtnIgual);
		
		JRadioButton rdbtnMenorQue = new JRadioButton("Menor que");
		buttonGroup.add(rdbtnMenorQue);
		rdbtnMenorQue.setBackground(Color.WHITE);
		rdbtnMenorQue.setBounds(412, 73, 109, 14);
		panel_1.add(rdbtnMenorQue);
		
		JLabel lblTipoDeFiltro = new JLabel("Tipo de Filtro:");
		lblTipoDeFiltro.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoDeFiltro.setForeground(Color.BLACK);
		lblTipoDeFiltro.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTipoDeFiltro.setBounds(278, 38, 128, 22);
		panel_1.add(lblTipoDeFiltro);
		
		JButton btnNewButton = new JButton("Exportar para CSV");
		btnNewButton.setBounds(590, 45, 180, 37);
		panel_1.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 94, 760, 145);
		panel_1.add(scrollPane);
		
		tblHistoricoProd = new JTable();
		scrollPane.setViewportView(tblHistoricoProd);
		tblHistoricoProd.setFillsViewportHeight(true);
		tblHistoricoProd.setColumnSelectionAllowed(true);
		tblHistoricoProd.setCellSelectionEnabled(true);
		tblHistoricoProd.setAutoResizeMode(JTable. AUTO_RESIZE_ALL_COLUMNS);
		tblHistoricoProd.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Data", "Produto", "Meta (un)", "Produzido (un)", "Tempo de Produ\u00E7\u00E3o (h:m:ss)"
			}
		) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblHistoricoProd.getColumnModel().getColumn(1).setPreferredWidth(151);
		tblHistoricoProd.getColumnModel().getColumn(3).setPreferredWidth(117);
		tblHistoricoProd.getColumnModel().getColumn(4).setPreferredWidth(159);
		tblHistoricoProd.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JButton btnNewButton_1 = new JButton("Aplicar Filtro");
		btnNewButton_1.setBounds(20, 69, 115, 23);
		panel_1.add(btnNewButton_1);

	}
}
