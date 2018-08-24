package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

public class ViewManuaisFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnZoomMais;
	private JButton btnZoomMenos;
	private JLabel lblImagem;
	
	public ViewManuaisFrame() {
		setType(Type.UTILITY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewManuaisFrame.class.getResource("/Assets/LogoClaraMaq - 800 x 480.PNG")));
		setResizable(false);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 480);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
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
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setViewportView(lblImagem);
		panel.setLayout(null);
		
		JButton btnSalvarPDF = new JButton("Salvar");
		btnSalvarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btnSalvarPDF.setIcon(new ImageIcon(ViewManuaisFrame.class.getResource("/Assets/pdf (32).png")));
		btnSalvarPDF.setBounds(10, 11, 97, 61);
		btnSalvarPDF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalvarPDF.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalvarPDF.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel.add(btnSalvarPDF);
		
		btnAnterior = new JButton("Anterior");
		btnAnterior.setIcon(new ImageIcon(ViewManuaisFrame.class.getResource("/Assets/left-arrow (32).png")));
		btnAnterior.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAnterior.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAnterior.setBounds(116, 11, 97, 61);
		panel.add(btnAnterior);
		
		btnProximo = new JButton("Pr\u00F3ximo");
		btnProximo.setIcon(new ImageIcon(ViewManuaisFrame.class.getResource("/Assets/right-arrow (32).png")));
		btnProximo.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnProximo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProximo.setBounds(223, 11, 97, 61);
		panel.add(btnProximo);
		
		btnZoomMais = new JButton("Zoom +");
		btnZoomMais.setIcon(new ImageIcon(ViewManuaisFrame.class.getResource("/Assets/zoom-in (32).png")));
		btnZoomMais.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnZoomMais.setHorizontalTextPosition(SwingConstants.CENTER);
		btnZoomMais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnZoomMais.setBounds(330, 11, 97, 61);
		panel.add(btnZoomMais);
		
		btnZoomMenos = new JButton("Zoom -");
		btnZoomMenos.setIcon(new ImageIcon(ViewManuaisFrame.class.getResource("/Assets/zoom-out (32).png")));
		btnZoomMenos.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnZoomMenos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnZoomMenos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnZoomMenos.setBounds(441, 11, 97, 61);
		panel.add(btnZoomMenos);
		
		
		contentPane.setLayout(gl_contentPane);	
		
	}
	
	private void carregaImagem()
	{
	      File file = new File("C://Users//Mateus//Downloads//CaixoteBase.pdf");
	      try {
	    	  FileInputStream filea = new FileInputStream(file);
			PDDocument doc = PDDocument.load(filea);
			PDFRenderer pdfRenderer = new PDFRenderer(doc);
			//for (int page = 0; page < doc.getNumberOfPages(); ++page)
			//{ 
			    BufferedImage bim = pdfRenderer.renderImageWithDPI(1, 300, ImageType.RGB);
			    Image img = new ImageIcon(bim).getImage().getScaledInstance(bim.getWidth(), bim.getHeight(),bim.SCALE_DEFAULT );
			       lblImagem.setText(null);
			       lblImagem.setIcon(new ImageIcon(img));
			//}

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	      
	}
}
