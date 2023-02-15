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

public class UsersView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton btnHome;
	private DefaultTableModel tblUserModel;

	// -----------
	private JPanel userPanel;// SI
	private JPanel informationPanel;// SI
	private JPanel controlPanel;// SI
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JScrollPane scrpUserTable;
	private JTable userTable;// SI
	private JTextField textField;
	private JLabel NM;
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

		// btnStockPanel = new JButton("Stock");
		// btnStockPanel.setEnabled(false);
		// btnStockPanel.setFocusPainted(false);
		// btnStockPanel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		// menuPanel.add(btnStockPanel);

		/*
		 * btnMarketPanel = new JButton("Market");
		 * btnMarketPanel.setFocusPainted(false); btnMarketPanel.setFont(new
		 * Font("Tahoma", Font.PLAIN, 16)); menuPanel.add(btnMarketPanel);
		 */
		userPanel.setLayout(null);

		JPanel addUserPanel = new JPanel();
		addUserPanel.setBounds(0, 0, 980, 532);
		userPanel.add(addUserPanel);
		addUserPanel.setLayout(null);

		NM = new JLabel("Nombre:");
		NM.setBounds(216, 74, 46, 14);
		addUserPanel.add(NM);

		textField = new JTextField();
		textField.setBounds(272, 71, 86, 20);
		addUserPanel.add(textField);
		textField.setColumns(10);

		informationPanel = new JPanel();
		informationPanel.setBounds(0, 0, 980, 532);
		userPanel.add(informationPanel);
		informationPanel.setLayout(null);

		scrpUserTable = new JScrollPane();
		scrpUserTable.setBounds(0, 0, 980, 491);
		informationPanel.add(scrpUserTable);

		userTable = new JTable();
		userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userTable.setBounds(0, 0, 1, 1);
		scrpUserTable.setViewportView(userTable);

		/*
		 * stockTable = new JTable(); stockTable.setBounds(0, 0, 1, 1);
		 * scrpStockTable.setViewportView(stockTable); scrpStockTable.setVisible(true);
		 */
		configurarTablas();

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

	}

	// ------------------------------------BOTON
	// HOME-------------------------------------------

	public void setControllerUser(Controller controller) {
		btnHome.addActionListener(controller);
		btnAgregar.addActionListener(controller);
		// btnUserPanel.addActionListener(controller);
		btnEditar.addActionListener(controller);
		btnEliminar.addActionListener(controller);
		textField.addActionListener(controller);
		// btnStockPanel.addActionListener(controller);
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
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

}
