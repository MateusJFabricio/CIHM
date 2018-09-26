package Views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import Controller.ControllerSenha;
import java.awt.Rectangle;

public class ViewSenha extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPasswordField password;
	private ControllerSenha control;
	public int tipoSenha;
	 
	public ViewSenha() {
		setBounds(new Rectangle(100, 100, 449, 67));
		Controller.Controller.janelaPrincipal.setAlwaysOnTop(false);
		control = new ControllerSenha();
		setType(Type.UTILITY);
		setTitle("Terminal de senha");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Entre com a senha:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 171, 22);
		getContentPane().add(lblNewLabel);
		
		password = new JPasswordField(10);
		password.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Main.Main.teclado.setModalExclusionType(getModalExclusionType());
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
		tipoSenha = 0;
		
		if (password.getText().isEmpty())
		{
			this.dispose();
			return;
		}
		
		tipoSenha = control.confirmarSenha(password.getText());
		password.setText("");
		this.dispose();
	}
}
