package Views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

public class ViewUnidades extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JComboBox<String> comboBox;
	public boolean btnOk = false;
	private boolean soLinux;
	public String path;

	public ViewUnidades() {
		this.setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Unidade");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 345, 142);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actBtnOk();
			}
		});
		contentPane.add(btnSelecionar, BorderLayout.SOUTH);
		
		JLabel lblSalvarPdf = new JLabel("Salvar PDF");
		lblSalvarPdf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSalvarPdf.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSalvarPdf, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(151, 11, 146, 36);
		panel.add(comboBox);
		
		JLabel label = new JLabel("  Selecione a Unidade:  ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 11, 287, 36);
		panel.add(label);
		if (carregarDadosIniciais())
		{
			setModal(true);
			setVisible(true);
		}
	}

	private boolean carregarDadosIniciais() {
		File[] roots = null;
		soLinux = System.getProperties().getProperty("os.name").toLowerCase().contains("linux");
		 
		FileSystemView fs = FileSystemView.getFileSystemView();
		 
		
		if (soLinux) {
		     
		    roots = fs.getFiles(new File("/media/pi/"), true);
		     
		    for (File file : roots) {
		    	comboBox.addItem(fs.getSystemDisplayName(file));
		    }
		}else
		{
		    roots = File.listRoots();
		     
		    for (File file : roots) {
		        String descricao = fs.getSystemTypeDescription(file);
		        if (descricao != null) {
		            if (descricao.endsWith("removível")) {
		            	comboBox.addItem(file.getAbsolutePath() + fs.getSystemDisplayName(file));
		            }
		        }
		    }  
		}
		
		if (comboBox.getItemCount() <= 0)
		{
			JOptionPane.showMessageDialog(null, "Insira um Pendrive na porta USB");
			return false;
		}
		
		return true;
	}

	protected void actBtnOk() {
		path = comboBox.getSelectedItem().toString();
		
		if (soLinux)
			path = "/media/pi/" + path + "/";
		else
			path = path.substring(0, path.lastIndexOf("\\"));
		
		btnOk  = true;
		this.dispose();
	}
}
