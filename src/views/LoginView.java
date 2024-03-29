package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Controller;

public class LoginView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblTitulo;
	private JButton btnSignUpOk;
	private JButton btnSignUpCancel;
	private JPanel SignUpPanel;
	private JPanel panel;
	private JPanel LogInPanel;
	private JLabel lblSignUpName;
	private JTextField textFieldSignUpName;
	private JTextField textFieldSignUpAge;
	private JComboBox<String> comboBoxSignUpGender;
	private JTextField textFieldUser;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JButton btnLogin;
	private JPasswordField textFieldPassword;
	private JButton btnSignUp;
	private JLabel lblSignUpPassword;
	private JPasswordField textFieldSignUpPassword;

	public LoginView() {
		setSize(1000, 700);
		setLayout(null);

		lblTitulo = new JLabel("VIDEOGAME - SHOP");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 0, 984, 92);
		add(lblTitulo);

		panel = new JPanel();
		panel.setBounds(150, 100, 700, 486);
		add(panel);
		panel.setLayout(null);

		SignUpPanel = new JPanel();
		SignUpPanel.setVisible(false);
		SignUpPanel.setBounds(0, 0, 700, 486);
		panel.add(SignUpPanel);
		SignUpPanel.setLayout(null);

		textFieldSignUpName = new JTextField();
		textFieldSignUpName.setBounds(325, 113, 96, 19);
		SignUpPanel.add(textFieldSignUpName);
		textFieldSignUpName.setColumns(10);

		lblSignUpName = new JLabel("Nombre:");
		lblSignUpName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSignUpName.setBounds(263, 117, 60, 13);
		SignUpPanel.add(lblSignUpName);

		JLabel LabelSignUpAge = new JLabel("Edad:");
		LabelSignUpAge.setFont(new Font("Tahoma", Font.PLAIN, 11));
		LabelSignUpAge.setBounds(263, 190, 35, 13);
		SignUpPanel.add(LabelSignUpAge);

		textFieldSignUpAge = new JTextField();
		textFieldSignUpAge.setColumns(10);
		textFieldSignUpAge.setBounds(325, 186, 96, 19);
		SignUpPanel.add(textFieldSignUpAge);

		JLabel LabelSignUpGender = new JLabel("Sexo:");
		LabelSignUpGender.setFont(new Font("Tahoma", Font.PLAIN, 11));
		LabelSignUpGender.setBounds(263, 220, 60, 13);
		SignUpPanel.add(LabelSignUpGender);

		btnSignUpOk = new JButton("Ok");
		btnSignUpOk.setBounds(254, 261, 80, 21);
		SignUpPanel.add(btnSignUpOk);

		btnSignUpCancel = new JButton("Cancel");
		btnSignUpCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSignUpCancel.setBounds(366, 261, 80, 21);
		SignUpPanel.add(btnSignUpCancel);

		comboBoxSignUpGender = new JComboBox<String>();
		comboBoxSignUpGender.setBounds(325, 217, 96, 19);
		SignUpPanel.add(comboBoxSignUpGender);

		lblSignUpPassword = new JLabel("Password:");
		lblSignUpPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblSignUpPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSignUpPassword.setBounds(263, 152, 60, 16);
		SignUpPanel.add(lblSignUpPassword);

		textFieldSignUpPassword = new JPasswordField();
		textFieldSignUpPassword.setBounds(325, 150, 96, 19);
		SignUpPanel.add(textFieldSignUpPassword);

		comboBoxSignUpGender.addItem("MASCULINO");
		comboBoxSignUpGender.addItem("FEMENINO");

		LogInPanel = new JPanel();
		LogInPanel.setBounds(0, 0, 700, 486);
		panel.add(LogInPanel);
		LogInPanel.setLayout(null);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(315, 130, 96, 19);
		textFieldUser.setColumns(10);
		LogInPanel.add(textFieldUser);

		lblUser = new JLabel("User");
		lblUser.setBounds(263, 130, 42, 16);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LogInPanel.add(lblUser);

		lblPassword = new JLabel("Password");
		lblPassword.setBounds(240, 173, 65, 16);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LogInPanel.add(lblPassword);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(315, 239, 80, 21);
		LogInPanel.add(btnLogin);

		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(315, 173, 96, 19);
		LogInPanel.add(textFieldPassword);

		btnSignUp = new JButton("SignUp");
		btnSignUp.setBounds(315, 284, 80, 21);
		LogInPanel.add(btnSignUp);

	}

	public boolean successLogin(String passwordInput, String userPassword) {
		if (null == userPassword) {
			return false;
		}
		return userPassword.equals(passwordInput);
	}

	public void setController(Controller controller) {
		btnLogin.addActionListener(controller);
		btnSignUp.addActionListener(controller);
		textFieldUser.addActionListener(controller);
		textFieldPassword.addActionListener(controller);
		comboBoxSignUpGender.addActionListener(controller);
		textFieldSignUpAge.addActionListener(controller);
		textFieldSignUpName.addActionListener(controller);
		btnSignUpCancel.addActionListener(controller);
		btnSignUpOk.addActionListener(controller);
		textFieldSignUpPassword.addActionListener(controller);
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

	public JButton getBtnSignUp() {
		return btnSignUp;
	}

	public void setBtnSignUp(JButton btnSignUp) {
		this.btnSignUp = btnSignUp;
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public JButton getBtnSignUpOk() {
		return btnSignUpOk;
	}

	public void setBtnSignUpOk(JButton btnSignUpOk) {
		this.btnSignUpOk = btnSignUpOk;
	}

	public JButton getBtnSignUpCancel() {
		return btnSignUpCancel;
	}

	public void setBtnSignUpCancel(JButton btnSignUpCancel) {
		this.btnSignUpCancel = btnSignUpCancel;
	}

	public JPanel getSignUpPanel() {
		return SignUpPanel;
	}

	public void setSignUpPanel(JPanel signUpPanel) {
		SignUpPanel = signUpPanel;
	}

	public JLabel getLblSignUpName() {
		return lblSignUpName;
	}

	public void setLblSignUpName(JLabel lblSignUpName) {
		this.lblSignUpName = lblSignUpName;
	}

	public JTextField getTextFieldSignUpName() {
		return textFieldSignUpName;
	}

	public void setTextFieldSignUpName(JTextField textFieldSignUpName) {
		this.textFieldSignUpName = textFieldSignUpName;
	}

	public JTextField getTextFieldSignUpAge() {
		return textFieldSignUpAge;
	}

	public void setTextFieldSignUpAge(JTextField textFieldSignUpAge) {
		this.textFieldSignUpAge = textFieldSignUpAge;
	}

	public JComboBox<String> getComboBoxSignUpGender() {
		return comboBoxSignUpGender;
	}

	public void setComboBoxSignUpGender(JComboBox<String> comboBoxSignUpGender) {
		this.comboBoxSignUpGender = comboBoxSignUpGender;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JPanel getLogInPanel() {
		return LogInPanel;
	}

	public void setLogInPanel(JPanel logInPanel) {
		LogInPanel = logInPanel;
	}

	public JPasswordField getTextFieldSignUpPassword() {
		return textFieldSignUpPassword;
	}

	public void setTextFieldSignUpPassword(JPasswordField textFieldSignUpPassword) {
		this.textFieldSignUpPassword = textFieldSignUpPassword;
	}

}