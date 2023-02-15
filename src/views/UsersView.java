package views;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsersView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton btnHome;
	private DefaultTableModel tblUserModel;

	// -----------
	private JPanel userPanel;// SI
	private JPanel informationPanel;// SI
	private JPanel controlPanel;// SI
	private JPanel addUserPanel;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JScrollPane scrpUserTable;
	private JTable userTable;// SI
	private JTextField textFieldName;
	private JLabel LabelName;
	private JTextField textFieldAge;
	private JTextField textFieldGender;
	private JTextField textFieldPoints;
	private JButton btnOk;
	private JButton btnCancel;
	// --------------

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
						
		informationPanel = new JPanel();
		informationPanel.setBounds(0, 0, 980, 492);
		userPanel.add(informationPanel);
		informationPanel.setLayout(null);
								
		scrpUserTable = new JScrollPane();
		scrpUserTable.setBounds(0, 0, 980, 533);
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
		
				JPanel addUserPanel = new JPanel();
				addUserPanel.setBounds(0, 0, 980, 492);
				userPanel.add(addUserPanel);
				addUserPanel.setLayout(null);
					

					LabelName = new JLabel("Nombre:");
					LabelName.setBounds(313, 74, 46, 14);
					addUserPanel.add(LabelName);
					
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
							//textFieldPoints.setVisible(false);
							
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
		textFieldName.addActionListener(controller);
		textFieldAge.addActionListener(controller);
		textFieldGender.addActionListener(controller);
		textFieldPoints.addActionListener(controller);
	}

	public void loadUserData() {
		tblUserModel.getDataVector().clear();
		File file = new File("src/model/data/UserStorage.txt");
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (sc.hasNextLine()) {
			String userData = sc.nextLine();
			String name = userData.split(",")[0];
			Double edad = Double.parseDouble(userData.split(",")[1]);
			String sexo = userData.split(",")[2];
			Double puntos = Double.parseDouble(userData.split(",")[3]);
			DefaultTableModel model = (DefaultTableModel) userTable.getModel();
			model.addRow(new Object[] { name, edad, sexo, puntos });
		}

		sc.close();
	}

	public void addUserData(String name, Double edad, String sexo, Double puntos) {
		tblUserModel.getDataVector().clear();
		File file = new File("src/model/data/UserStorage.txt");
		Scanner sc = null;
		String txtInfo = "";
		try {
			sc = new Scanner(file);
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				txtInfo += line + "\n";
			}
			txtInfo += name + "," + String.valueOf(edad) + "," + sexo + "," + String.valueOf(puntos);
			out.write(txtInfo);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void editUserData(String name, Double edad, String sexo, Double puntos, int row) {
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

	// -configurar tablas

	private void configurarTablas() {

		tblUserModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblUserModel.addColumn("NOMBRE");
		tblUserModel.addColumn("EDAD");
		tblUserModel.addColumn("SEXO");
		tblUserModel.addColumn("PUNTOS");

		final DefaultTableCellRenderer cellRendUser = new DefaultTableCellRenderer();
		cellRendUser.setHorizontalAlignment(SwingConstants.CENTER);

		userTable.setModel(tblUserModel);

		userTable.getColumn("NOMBRE").setCellRenderer(cellRendUser);
		userTable.getColumn("EDAD").setCellRenderer(cellRendUser);
		userTable.getColumn("SEXO").setCellRenderer(cellRendUser);
		userTable.getColumn("PUNTOS").setCellRenderer(cellRendUser);

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

	
	
	
}
