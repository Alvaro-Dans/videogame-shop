package views;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.User;

public class UsersView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton btnHome;
	private DefaultTableModel tblUserModel;

	// -----------
	private JPanel userPanel;// SI
	private JPanel informationPanel;// SI
	private JPanel controlPanel;// SI
	private JPanel addUserPanel;
	private JPanel editUserPanel;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JScrollPane scrpUserTable;
	private JTable userTable;// SI
	private JTextField textFieldName;
	private JLabel lblName;
	private JLabel lblUsers;
	private JTextField textFieldAge;
	private JTextField textFieldGender;
	private JTextField textFieldPoints;
	private JButton btnOk;
	private JButton btnCancel;
	private JButton btnEditOk;
	private JButton btnEditCancel;
	private JTextField textFieldEditName;
	private JTextField textFieldEditAge;
	private JTextField textFieldEditGender;
	private JTextField textFieldEditPoints;

	public UsersView() {
		setSize(1000, 700);
		setLayout(null);

		btnHome = new JButton("");
		btnHome.setIcon(new ImageIcon(HomeView.class.getResource("/images/home.png")));
		btnHome.setFocusPainted(false);
		btnHome.setBounds(20, 20, 50, 50);
		btnHome.setBorder(null);
		btnHome.setBackground(null);
		btnHome.setContentAreaFilled(false);
		btnHome.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnHome.setIcon(new ImageIcon(HomeView.class.getResource("/images/home_grande.png")));
			}

			public void mouseExited(MouseEvent evt) {
				btnHome.setIcon(new ImageIcon(HomeView.class.getResource("/images/home.png")));
			}
		});
		add(btnHome);

		userPanel = new JPanel();
		userPanel.setBounds(10, 81, 980, 580);
		add(userPanel);

		userPanel.setLayout(null);

		editUserPanel = new JPanel();
		editUserPanel.setVisible(false);
		editUserPanel.setBounds(0, 0, 980, 492);
		userPanel.add(editUserPanel);
		editUserPanel.setLayout(null);

		JLabel editUserLabelName = new JLabel("Nombre:");
		editUserLabelName.setBounds(313, 74, 59, 13);
		editUserPanel.add(editUserLabelName);

		JLabel editUserLabelAge = new JLabel("Edad:");
		editUserLabelAge.setBounds(313, 126, 45, 13);
		editUserPanel.add(editUserLabelAge);

		JLabel editUserLabelGender = new JLabel("Sexo:");
		editUserLabelGender.setBounds(313, 180, 45, 13);
		editUserPanel.add(editUserLabelGender);

		JLabel editUserLabelPoints = new JLabel("Puntos:");
		editUserLabelPoints.setBounds(313, 234, 45, 13);
		editUserPanel.add(editUserLabelPoints);

		textFieldEditName = new JTextField();
		textFieldEditName.setBounds(369, 72, 216, 20);
		editUserPanel.add(textFieldEditName);
		textFieldEditName.setColumns(10);

		textFieldEditAge = new JTextField();
		textFieldEditAge.setBounds(369, 124, 216, 20);
		editUserPanel.add(textFieldEditAge);
		textFieldEditAge.setColumns(10);

		textFieldEditGender = new JTextField();
		textFieldEditGender.setBounds(369, 178, 216, 20);
		editUserPanel.add(textFieldEditGender);
		textFieldEditGender.setColumns(10);

		textFieldEditPoints = new JTextField();
		textFieldEditPoints.setBounds(369, 232, 216, 20);
		editUserPanel.add(textFieldEditPoints);
		textFieldEditPoints.setColumns(10);

		btnEditOk = new JButton("Ok");
		btnEditOk.setBounds(313, 321, 85, 21);
		editUserPanel.add(btnEditOk);

		btnEditCancel = new JButton("Cancel");
		btnEditCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditCancel.setBounds(500, 321, 85, 21);
		editUserPanel.add(btnEditCancel);

		informationPanel = new JPanel();
		informationPanel.setBounds(0, 0, 980, 492);
		userPanel.add(informationPanel);
		informationPanel.setLayout(null);

		scrpUserTable = new JScrollPane();
		scrpUserTable.setBounds(0, 0, 980, 492);
		informationPanel.add(scrpUserTable);

		userTable = new JTable();
		userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userTable.setBounds(0, 0, 1, 1);
		scrpUserTable.setViewportView(userTable);

		controlPanel = new JPanel();
		controlPanel.setBounds(0, 532, 980, 48);
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setVgap(10);
		fl_controlPanel.setHgap(100);
		userPanel.add(controlPanel);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFocusPainted(false);
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		controlPanel.add(btnAgregar);

		btnEditar = new JButton("EDITAR");
		btnEditar.setFocusPainted(false);
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		controlPanel.add(btnEditar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setFocusPainted(false);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		controlPanel.add(btnEliminar);

		addUserPanel = new JPanel();
		addUserPanel.setVisible(false);
		addUserPanel.setBounds(0, 0, 980, 492);
		userPanel.add(addUserPanel);
		addUserPanel.setLayout(null);

		lblName = new JLabel("Nombre:");
		lblName.setBounds(313, 74, 56, 14);
		addUserPanel.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setBounds(369, 72, 216, 20);
		addUserPanel.add(textFieldName);
		textFieldName.setColumns(10);

		JLabel LabelAge = new JLabel("Edad:");
		LabelAge.setBounds(313, 126, 46, 14);
		addUserPanel.add(LabelAge);

		textFieldAge = new JTextField();
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(369, 124, 216, 20);
		addUserPanel.add(textFieldAge);

		JLabel LabelGender = new JLabel("Sexo:");
		LabelGender.setBounds(313, 180, 46, 14);
		addUserPanel.add(LabelGender);

		textFieldGender = new JTextField();
		textFieldGender.setColumns(10);
		textFieldGender.setBounds(369, 178, 216, 20);
		addUserPanel.add(textFieldGender);

		JLabel LabelPoints = new JLabel("Puntos:");
		LabelPoints.setBounds(313, 234, 46, 14);
		addUserPanel.add(LabelPoints);

		textFieldPoints = new JTextField();
		textFieldPoints.setColumns(10);
		textFieldPoints.setBounds(369, 232, 216, 20);
		addUserPanel.add(textFieldPoints);
		// textFieldPoints.setVisible(false);

		btnOk = new JButton("Ok");
		btnOk.setBounds(313, 321, 85, 21);
		addUserPanel.add(btnOk);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCancel.setBounds(500, 321, 85, 21);
		addUserPanel.add(btnCancel);

		lblUsers = new JLabel("USERS");
		lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsers.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblUsers.setBounds(0, 20, 1000, 41);
		add(lblUsers);

		configurarTablas();

	}

	public void setControllerUser(Controller controller) {
		btnHome.addActionListener(controller);
		btnAgregar.addActionListener(controller);
		btnEditar.addActionListener(controller);
		btnEliminar.addActionListener(controller);
		textFieldName.addActionListener(controller);
		btnOk.addActionListener(controller);
		btnCancel.addActionListener(controller);
		btnEditOk.addActionListener(controller);
		btnEditCancel.addActionListener(controller);
		textFieldName.addActionListener(controller);
		textFieldAge.addActionListener(controller);
		textFieldGender.addActionListener(controller);
		textFieldPoints.addActionListener(controller);

	}

	public void loadUserData(List<User> databaseUserList) {
		tblUserModel.getDataVector().clear();
		for (User user : databaseUserList) {
			DefaultTableModel model = (DefaultTableModel) userTable.getModel();
			model.addRow(new Object[] { user.getName(), user.getAge(), user.getGender(), user.getPoints() });
		}

	}

	public void addUserData(String name, int edad, String sexo, Long puntos) {
		tblUserModel.getDataVector().clear();
		File file = new File("src/model/data/UserStorage.txt");
		Scanner sc = null;
		String txtInfo = "";
		try {
			sc = new Scanner(file);
			//

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				txtInfo += line + "\n";
			}
			txtInfo += name + "," + String.valueOf(edad) + "," + sexo + "," + String.valueOf(puntos);
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write(txtInfo);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void editUserData(String name, int edad, String sexo, Long puntos, int row) {
		tblUserModel.getDataVector().clear();
		File file = new File("src/model/data/UserStorage.txt");
		List<String> usersList = new ArrayList<>();
		Scanner sc = null;
		String txtInfo = "";
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				usersList.add(sc.nextLine());
			}
			usersList.set(row, name + "," + String.valueOf(edad) + "," + sexo + "," + String.valueOf(puntos));

			for (String line : usersList) {
				txtInfo += line + "\n";
			}

			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write(txtInfo);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteUserData() {

		int selectedRow = getUserTable().getSelectedRow();
		if (selectedRow != -1) {
			tblUserModel.getDataVector().clear();
			File file = new File("src/model/data/UserStorage.txt");
			List<String> usersList = new ArrayList<>();
			Scanner sc = null;
			String txtInfo = "";
			try {
				sc = new Scanner(file);
				while (sc.hasNextLine()) {
					usersList.add(sc.nextLine());
				}
				String[] rowData = usersList.get(selectedRow).split(",");
				usersList.remove(selectedRow);
				for (String line : usersList) {
					txtInfo += line + "\n";
				}
				BufferedWriter out = new BufferedWriter(new FileWriter(file));
				out.write(txtInfo);
				out.close();
				tblUserModel.fireTableDataChanged();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void configurarTablas() {

		tblUserModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblUserModel.addColumn("NAME");
		tblUserModel.addColumn("AGE");
		tblUserModel.addColumn("GENDER");
		tblUserModel.addColumn("POINTS");

		final DefaultTableCellRenderer cellRendUser = new DefaultTableCellRenderer();
		cellRendUser.setHorizontalAlignment(SwingConstants.CENTER);

		userTable.setModel(tblUserModel);

		userTable.getColumn("NAME").setCellRenderer(cellRendUser);
		userTable.getColumn("AGE").setCellRenderer(cellRendUser);
		userTable.getColumn("GENDER").setCellRenderer(cellRendUser);
		userTable.getColumn("POINTS").setCellRenderer(cellRendUser);

	}

	public JButton getBtnHome() {
		return btnHome;
	}

	public void setBtnHome(JButton btnHome) {
		this.btnHome = btnHome;
	}

	public JPanel getInformationPanel() {
		return informationPanel;
	}

	public void setInformationPanel(JPanel informationPanel) {
		this.informationPanel = informationPanel;
	}

	public JPanel getControlPanel() {
		return controlPanel;
	}

	public void setControlPanel(JPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public JTable getUserTable() {
		return userTable;
	}

	public void setUserTable(JTable userTable) {
		this.userTable = userTable;
	}

	public JTextField getTextField() {
		return textFieldName;
	}

	public void setTextField(JTextField textField) {
		this.textFieldName = textField;
	}

	public JPanel getUserPanel() {
		return userPanel;
	}

	public void setUserPanel(JPanel userPanel) {
		this.userPanel = userPanel;
	}

	public JPanel getAddUserPanel() {
		return addUserPanel;
	}

	public void setAddUserPanel(JPanel addUserPanel) {
		this.addUserPanel = addUserPanel;
	}

	public JTextField getTextFieldName() {
		return textFieldName;
	}

	public void setTextFieldName(JTextField textFieldName) {
		this.textFieldName = textFieldName;
	}

	public JTextField getTextFieldAge() {
		return textFieldAge;
	}

	public void setTextFieldAge(JTextField textFieldAge) {
		this.textFieldAge = textFieldAge;
	}

	public JTextField getTextFieldGender() {
		return textFieldGender;
	}

	public void setTextFieldGender(JTextField textFieldGender) {
		this.textFieldGender = textFieldGender;
	}

	public JTextField getTextFieldPoints() {
		return textFieldPoints;
	}

	public void setTextFieldPoints(JTextField textFieldPoints) {
		this.textFieldPoints = textFieldPoints;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public void setBtnOk(JButton btnOk) {
		this.btnOk = btnOk;
	}

	public JPanel getEditUserPanel() {
		return editUserPanel;
	}

	public void setEditUserPanel(JPanel editUserPanel) {
		this.editUserPanel = editUserPanel;
	}

	public JButton getBtnEditOk() {
		return btnEditOk;
	}

	public void setBtnEditOk(JButton btnEditOk) {
		this.btnEditOk = btnEditOk;
	}

	public JButton getBtnEditCancel() {
		return btnEditCancel;
	}

	public void setBtnEditCancel(JButton btnEditCancel) {
		this.btnEditCancel = btnEditCancel;
	}

	public JTextField getTextFieldEditName() {
		return textFieldEditName;
	}

	public void setTextFieldEditName(JTextField textFieldEditName) {
		this.textFieldEditName = textFieldEditName;
	}

	public JTextField getTextFieldEditAge() {
		return textFieldEditAge;
	}

	public void setTextFieldEditAge(JTextField textFieldEditAge) {
		this.textFieldEditAge = textFieldEditAge;
	}

	public JTextField getTextFieldEditGender() {
		return textFieldEditGender;
	}

	public void setTextFieldEditGender(JTextField textFieldEditGender) {
		this.textFieldEditGender = textFieldEditGender;
	}

	public JTextField getTextFieldEditPoints() {
		return textFieldEditPoints;
	}

	public void setTextFieldEditPoints(JTextField textFieldEditPoints) {
		this.textFieldEditPoints = textFieldEditPoints;
	}

	public DefaultTableModel getTblUserModel() {
		return tblUserModel;
	}

	public void setTblUserModel(DefaultTableModel tblUserModel) {
		this.tblUserModel = tblUserModel;
	}

}
