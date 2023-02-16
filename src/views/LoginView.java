package views;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Controller;

public class LoginView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblTitulo;
	private JTextField textFieldUser;
	private JButton btnLogin;
	private JPasswordField textFieldPassword;

	public LoginView() {
		setSize(1000, 700);
		setLayout(null);

		lblTitulo = new JLabel("VIDEOGAME - SHOP");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 0, 984, 92);
		add(lblTitulo);

		JPanel panel = new JPanel();
		panel.setBounds(150, 100, 700, 486);
		add(panel);
		panel.setLayout(null);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(259, 170, 194, 20);
		panel.add(textFieldUser);
		textFieldUser.setColumns(10);

		JLabel lblUser = new JLabel("User");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUser.setBounds(0, 143, 700, 16);
		panel.add(lblUser);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(0, 244, 700, 16);
		panel.add(lblPassword);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(308, 344, 89, 23);
		panel.add(btnLogin);

		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(259, 271, 194, 20);
		panel.add(textFieldPassword);

	}

	public boolean successLogin(String username, String password) {
		String realUsername = "admin";
		String realPassword = "pass";
		return realUsername.equals(username) && realPassword.equals(password);
	}

	public void setController(Controller controller) {
		btnLogin.addActionListener(controller);
		textFieldUser.addActionListener(controller);
		textFieldPassword.addActionListener(controller);
	}

	public JPasswordField getTextFieldPassword() {
		return textFieldPassword;
	}

	public void setTextFieldPassword(JPasswordField textFieldPassword) {
		this.textFieldPassword = textFieldPassword;
	}

	public JTextField getTextFieldUser() {
		return textFieldUser;
	}

	public void setTextFieldUser(JTextField textFieldUser) {
		this.textFieldUser = textFieldUser;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}
}
