package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class ViewManuaisFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnZoomMais;
	private JButton btnZoomMenos;
	private JLabel lblImagem;
	private String diretorioManual;
	private int paginaAtual = 0;
	private int totalPaginas = 0;
	private File file;
	private PDFRenderer pdfRenderer;
	private int escala = 0;
	private JLabel lblTZomm;
	private JLabel lblZoom;
	private PDDocument doc;
	private FileInputStream fileInputStream;
	private FileOutputStream fileOutputStream;
	
	public ViewManuaisFrame(String pathManual) {
		Controller.Controller.janelaPrincipal.setAlwaysOnTop(false);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setType(Type.UTILITY);
		diretorioManual = pathManual;
		//setIconImage(Toolkit.getDefaultToolkit().getImage(ViewManuaisFrame.class.getResource("/Assets/LogoClaraMaq - 800 x 480.PNG")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 800, 480);
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
		
		lblImagem = new JLabel("Nenhum PDF a ser exibido");
		lblImagem.setIcon(new ImageIcon(ViewManuaisFrame.class.getResource("/img/EsquemaEletricoFinal.jpg")));
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setViewportView(lblImagem);
		panel.setLayout(null);
		
		JButton btnSalvarPDF = new JButton("Salvar");
		btnSalvarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actBtnSalvar();
			}
		});
		btnSalvarPDF.setIcon(new ImageIcon(ViewManuaisFrame.class.getResource("/Assets/pdf (32).png")));
		btnSalvarPDF.setBounds(10, 11, 97, 61);
		btnSalvarPDF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalvarPDF.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalvarPDF.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel.add(btnSalvarPDF);
		
		btnAnterior = new JButton("Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//paginaAtual--;
				voltaScroll(10);
				//carregaPagina();
			}
		});
		btnAnterior.setIcon(new ImageIcon(ViewManuaisFrame.class.getResource("/Assets/left-arrow (32).png")));
		btnAnterior.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAnterior.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAnterior.setBounds(116, 11, 97, 61);
		panel.add(btnAnterior);
		
		btnProximo = new JButton("Pr\u00F3ximo");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//paginaAtual++;
				avancaScroll(10);
			}
		});
		btnProximo.setIcon(new ImageIcon(ViewManuaisFrame.class.getResource("/Assets/right-arrow (32).png")));
		btnProximo.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnProximo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProximo.setBounds(223, 11, 97, 61);
		panel.add(btnProximo);
		
		btnZoomMais = new JButton("Zoom +");
		btnZoomMais.setEnabled(false);
		btnZoomMais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if (escala <= 1000)
				{
					escala+=100;
					carregaPagina();
					lblZoom.setText(String.valueOf(Integer.parseInt(lblZoom.getText())+1));
				}*/
			}
		});
		btnZoomMais.setIcon(new ImageIcon(ViewManuaisFrame.class.getResource("/Assets/zoom-in (32).png")));
		btnZoomMais.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnZoomMais.setHorizontalTextPosition(SwingConstants.CENTER);
		btnZoomMais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnZoomMais.setBounds(330, 11, 97, 61);
		panel.add(btnZoomMais);
		
		btnZoomMenos = new JButton("Zoom -");
		btnZoomMenos.setEnabled(false);
		btnZoomMenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if (escala >= -300)
				{
					escala-=100;
					carregaPagina();
					lblZoom.setText(String.valueOf(Integer.parseInt(lblZoom.getText())-1));
				}*/
			}
		});
		btnZoomMenos.setIcon(new ImageIcon(ViewManuaisFrame.class.getResource("/Assets/zoom-out (32).png")));
		btnZoomMenos.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnZoomMenos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnZoomMenos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnZoomMenos.setBounds(441, 11, 97, 61);
		panel.add(btnZoomMenos);
		
		JLabel lblDe = new JLabel("Total de Paginas: 18");
		lblDe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDe.setBounds(574, 21, 154, 22);
		panel.add(lblDe);
		
		lblTZomm = new JLabel("Zomm:");
		lblTZomm.setEnabled(false);
		lblTZomm.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTZomm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTZomm.setBounds(574, 48, 72, 22);
		panel.add(lblTZomm);
		
		lblZoom = new JLabel("0");
		lblZoom.setEnabled(false);
		lblZoom.setHorizontalAlignment(SwingConstants.LEFT);
		lblZoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblZoom.setBounds(656, 51, 72, 17);
		panel.add(lblZoom);
		
		
		contentPane.setLayout(gl_contentPane);	
		/*carregarPDF();
		carregaPagina();
		*/
		
	}
	
	protected void avancaScroll(int i) {
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue() + i);
	}

	protected void voltaScroll(int i) {
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue() - i);
	}

	protected void actBtnSalvar() {
		ViewUnidades viewUnidade = new ViewUnidades();
		if (viewUnidade.path != null)
		{
			String p = viewUnidade.path;
			file = new File(diretorioManual);
			String a = file.getName();
			File fileDestino = new File (viewUnidade.path + file.getName());	
			copyFile(file, fileDestino);
		}
	}
	
	private void copyFile(File source, File destination) 
	{
        if (destination.exists())
        	return;
        
        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;
        try {
            fileInputStream = new FileInputStream(source);
			sourceChannel = fileInputStream.getChannel();
            fileOutputStream = new FileOutputStream(destination);
			destinationChannel = fileOutputStream.getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                    destinationChannel);
            JOptionPane.showMessageDialog(null, "Salvo como: " + file.getName());
        }
         catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao salvar aquivo: " + e.getMessage());
        } finally { 
			try {
				if (sourceChannel != null && sourceChannel.isOpen())
					sourceChannel.close();
				if (destinationChannel != null && destinationChannel.isOpen())
	                destinationChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
       }
   }
	private void carregarPDF()
	{
		file = new File(diretorioManual);
	      try {
	    	 
			doc = PDDocument.load(new FileInputStream(file));
			pdfRenderer = new PDFRenderer(doc);
			totalPaginas = doc.getNumberOfPages();
			
		} catch (IOException e) {
			e.printStackTrace();
			this.dispose();
		}
	}
	
	private void carregaPagina()
	{
		/*
		if (paginaAtual > totalPaginas)
			paginaAtual = totalPaginas;
		
		if (paginaAtual < 0)
			paginaAtual = 0;
		  try {
			Image img = new ImageIcon(bim).getImage().getScaledInstance(bim.getWidth() + escala - 1700, bim.getHeight() + escala - 1700,Image.SCALE_DEFAULT );
			ViewManuaisFrame.class.getResource("/img/EsquemaEletricoFinal.jpg")).getIconImage().
			lblImagem.setIcon(new ImageIcon(ViewManuaisFrame.class.getResource("/img/EsquemaEletricoFinal.jpg")).getImage().getScaledInstance(bim.getWidth() + escala - 1700, bim.getHeight() + escala - 1700,Image.SCALE_DEFAULT);
			lblImagem.setText(null);
		
			} catch (IOException e) {
				e.printStackTrace();
			}
	      */
	}
}
