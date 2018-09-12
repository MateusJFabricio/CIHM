package Views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import Controller.ControllerSenha;

public class ViewSenha extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField password;
	private ControllerSenha control;
	@SuppressWarnings("unused")
	private ViewFrame frame;
	 
	public ViewSenha(ViewFrame frame) {
		Controller.Controller.janelaPrincipal.setAlwaysOnTop(false);
		this.frame = frame;
		control = new ControllerSenha();
		setType(Type.UTILITY);
		setTitle("Terminal de senha");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(frame.getHeight() / 2, 150, 433,69);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Entre com a senha:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 171, 22);
		getContentPane().add(lblNewLabel);
		
		password = new JPasswordField(10);
		password.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Main.Main.teclado.setNumberOnly(true);
				Main.Main.teclado.showKeyboard();
			}
		});
		password.setEchoChar('*'); 
		password.setBounds(186, 11, 144, 25);
		getContentPane().add(password);
		
		JButton btnConfirmar = new JButton("Ok");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actBtnConfirmar();
			}
		});
		btnConfirmar.setBounds(340, 11, 80, 26);
		getContentPane().add(btnConfirmar);
		
	}
	
	@SuppressWarnings("deprecation")
	private void actBtnConfirmar()
	{
		if (password.getText().isEmpty())
			this.dispose();
			
		if (!control.confirmarSenha(password.getText()))
		{
			JOptionPane.showMessageDialog(null, "Senha Inválida");
			password.setText("");
			this.dispose();
		}
	}
}
