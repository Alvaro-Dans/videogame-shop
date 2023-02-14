package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import javax.swing.ListSelectionModel;

public class UsersView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton btnHome;
	private DefaultTableModel tblUserModel;
	
	//-----------
	private JPanel userPanel;//SI
	private JPanel menuPanel;//SI
	private JButton btnStockPanel;//NO
	private JButton btnMarketPanel;//NO
	private JPanel informationPanel;//SI
	private JPanel controlPanel;//SI
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JTable stockTable;//NO
	private JScrollPane scrpUserTable;//NO
	private JScrollPane scrpUserTable_1;
	private JScrollPane scrpMarketTable;//SI
	private JTable userTable;//SI
	//--------------

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
		userPanel.setLayout(new BorderLayout(0, 0));
		
		menuPanel = new JPanel();
		FlowLayout fl_menuPanel = (FlowLayout) menuPanel.getLayout();
		fl_menuPanel.setVgap(15);
		fl_menuPanel.setHgap(100);
		userPanel.add(menuPanel, BorderLayout.NORTH);
		
		//btnStockPanel = new JButton("Stock");
		//btnStockPanel.setEnabled(false);
		//btnStockPanel.setFocusPainted(false);
		//btnStockPanel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//menuPanel.add(btnStockPanel);
		
		/*
		btnMarketPanel = new JButton("Market");
		btnMarketPanel.setFocusPainted(false);
		btnMarketPanel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuPanel.add(btnMarketPanel);
		*/
		
		informationPanel = new JPanel();
		userPanel.add(informationPanel, BorderLayout.CENTER);
		informationPanel.setLayout(null);
		
		/*
		stockTable = new JTable();
		stockTable.setBounds(0, 0, 1, 1);
		scrpStockTable.setViewportView(stockTable);
		scrpStockTable.setVisible(true);
		*/
		
		
		scrpUserTable = new JScrollPane();
		scrpUserTable.setBounds(0, 0, 980, 463);
		scrpUserTable.setVisible(true);
		
		scrpUserTable_1 = new JScrollPane();
		scrpUserTable_1.setBounds(0, 0, 980, 463);
		informationPanel.add(scrpUserTable_1);

		
		userTable = new JTable();
		userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userTable.setColumnSelectionAllowed(true);
		userTable.setCellSelectionEnabled(true);
		configurarTablas();
		userTable.setBounds(0, 0, 1, 1);
		scrpUserTable_1.setViewportView(userTable);
		
		controlPanel = new JPanel();
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setVgap(10);
		fl_controlPanel.setHgap(100);
		userPanel.add(controlPanel, BorderLayout.SOUTH);
		
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
	
	//------------------------------------BOTON HOME-------------------------------------------	
	
	public void setControllerUser(Controller controller) {
		btnHome.addActionListener(controller);
		btnAgregar.addActionListener(controller);
		//btnUserPanel.addActionListener(controller);
		btnEditar.addActionListener(controller);
		btnEliminar.addActionListener(controller);
		//btnStockPanel.addActionListener(controller);
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
	
	
	//-configurar tablas
	
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
	
	//-------------------

	public void setController(Controller controller) {
		btnHome.addActionListener(controller);
	}
	
	public JButton getBtnStockPanel() {
		return btnStockPanel;
	}

	public void setBtnStockPanel(JButton btnStockPanel) {
		this.btnStockPanel = btnStockPanel;
	}
	

	public JButton getBtnHome() {
		return btnHome;
	}

	public void setBtnHome(JButton btnHome) {
		this.btnHome = btnHome;
	}
	
	public JTable getUserTable() {
		return userTable;
	}
	
	public void setUserTable(JTable userTable) {
		this.userTable = userTable;
	}
	
	public JScrollPane getScrpUserTable() {
		return scrpUserTable_1;
	}
	
	public void setScrpUserTable(JScrollPane scrpUserTable) {
		this.scrpUserTable_1 = scrpUserTable;
	}

}
