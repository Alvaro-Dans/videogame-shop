package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Finance;
import model.Game;

public class StockView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblStock;
	private JButton btnHome;
	private JPanel marketPanel;
	private JPanel menuPanel;
	private JButton btnStockPanel;
	private JButton btnMarketPanel;
	private JPanel informationPanel;
	private JPanel controlPanel;
	private JButton btnBuy;
	private JButton btnRent;
	private JTable stockTable;
	private JScrollPane scrpStockTable;
	private JScrollPane scrpMarketTable;
	private JTable marketTable;
	private DefaultTableModel tblStockModel, tblMarketModel;

	public StockView() {
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

		marketPanel = new JPanel();
		marketPanel.setBounds(10, 81, 980, 580);
		add(marketPanel);
		marketPanel.setLayout(new BorderLayout(0, 0));

		menuPanel = new JPanel();
		FlowLayout fl_menuPanel = (FlowLayout) menuPanel.getLayout();
		fl_menuPanel.setVgap(15);
		fl_menuPanel.setHgap(100);
		marketPanel.add(menuPanel, BorderLayout.NORTH);

		btnStockPanel = new JButton("Stock");
		btnStockPanel.setEnabled(false);
		btnStockPanel.setFocusPainted(false);
		btnStockPanel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuPanel.add(btnStockPanel);

		btnMarketPanel = new JButton("Market");
		btnMarketPanel.setFocusPainted(false);
		btnMarketPanel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuPanel.add(btnMarketPanel);

		informationPanel = new JPanel();
		marketPanel.add(informationPanel, BorderLayout.CENTER);
		informationPanel.setLayout(null);

		scrpStockTable = new JScrollPane();
		scrpStockTable.setBounds(0, 0, 980, 463);
		informationPanel.add(scrpStockTable);

		stockTable = new JTable();
		stockTable.setRowHeight(25);
		stockTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		stockTable.setBounds(0, 0, 1, 1);
		ListSelectionModel cellSelectionModel = stockTable.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrpStockTable.setViewportView(stockTable);
		scrpStockTable.setVisible(true);

		scrpMarketTable = new JScrollPane();
		scrpMarketTable.setBounds(0, 0, 980, 463);
		scrpMarketTable.setVisible(false);
		informationPanel.add(scrpMarketTable);

		marketTable = new JTable();
		marketTable.setRowHeight(25);
		marketTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		configurarTablas();
		marketTable.setBounds(0, 0, 1, 1);
		scrpMarketTable.setViewportView(marketTable);

		controlPanel = new JPanel();
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setVgap(10);
		fl_controlPanel.setHgap(100);
		marketPanel.add(controlPanel, BorderLayout.SOUTH);

		btnBuy = new JButton("Buy");
		btnBuy.setFocusPainted(false);
		btnBuy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		controlPanel.add(btnBuy);

		btnRent = new JButton("Rent");
		btnRent.setFocusPainted(false);
		btnRent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		controlPanel.add(btnRent);

		lblStock = new JLabel("STOCK");
		lblStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblStock.setBounds(0, 20, 1000, 41);
		add(lblStock);
	}

	public void setController(Controller controller) {
		btnHome.addActionListener(controller);
		btnBuy.addActionListener(controller);
		btnMarketPanel.addActionListener(controller);
		btnRent.addActionListener(controller);
		btnStockPanel.addActionListener(controller);
	}

	public void loadMarketData(List<Finance> financeList) {
		tblMarketModel.getDataVector().clear();
		for (Finance finance : financeList) {
			DefaultTableModel model = (DefaultTableModel) marketTable.getModel();
			model.addRow(new Object[] { finance.getGameName(), finance.getSellPrice(), finance.getRentPrice() });
		}
	}

	public void loadStockData(List<Game> gameList) {
		tblStockModel.getDataVector().clear();
		for (Game game : gameList) {
			DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
			model.addRow(new Object[] { game.getName(), game.getSellStock(), game.getRentStock() });
		}
		stockTable.clearSelection();
	}

	private void configurarTablas() {

		tblStockModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblStockModel.addColumn("GAME");
		tblStockModel.addColumn("SELL STOCK");
		tblStockModel.addColumn("RENT STOCK");

		final DefaultTableCellRenderer cellRendStock = new DefaultTableCellRenderer();
		cellRendStock.setHorizontalAlignment(SwingConstants.CENTER);

		stockTable.setModel(tblStockModel);

		stockTable.getColumn("GAME").setCellRenderer(cellRendStock);
		stockTable.getColumn("SELL STOCK").setCellRenderer(cellRendStock);
		stockTable.getColumn("RENT STOCK").setCellRenderer(cellRendStock);

		tblMarketModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblMarketModel.addColumn("GAME");
		tblMarketModel.addColumn("SELL PRICE");
		tblMarketModel.addColumn("RENT PRICE");

		final DefaultTableCellRenderer cellRendMarket = new DefaultTableCellRenderer();
		cellRendMarket.setHorizontalAlignment(SwingConstants.CENTER);

		marketTable.setModel(tblMarketModel);

		marketTable.getColumn("GAME").setCellRenderer(cellRendMarket);
		marketTable.getColumn("SELL PRICE").setCellRenderer(cellRendMarket);
		marketTable.getColumn("RENT PRICE").setCellRenderer(cellRendMarket);
	}

	public JLabel getLblStock() {
		return lblStock;
	}

	public void setLblStock(JLabel lblStock) {
		this.lblStock = lblStock;
	}

	public JButton getBtnHome() {
		return btnHome;
	}

	public void setBtnHome(JButton btnHome) {
		this.btnHome = btnHome;
	}

	public JPanel getMarketPanel() {
		return marketPanel;
	}

	public void setMarketPanel(JPanel marketPanel) {
		this.marketPanel = marketPanel;
	}

	public JPanel getMenuPanel() {
		return menuPanel;
	}

	public void setMenuPanel(JPanel menuPanel) {
		this.menuPanel = menuPanel;
	}

	public JButton getBtnStockPanel() {
		return btnStockPanel;
	}

	public void setBtnStockPanel(JButton btnStockPanel) {
		this.btnStockPanel = btnStockPanel;
	}

	public JButton getBtnMarketPanel() {
		return btnMarketPanel;
	}

	public void setBtnMarketPanel(JButton btnMarketPanel) {
		this.btnMarketPanel = btnMarketPanel;
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

	public JButton getBtnBuy() {
		return btnBuy;
	}

	public void setBtnBuy(JButton btnBuy) {
		this.btnBuy = btnBuy;
	}

	public JButton getBtnRent() {
		return btnRent;
	}

	public void setBtnRent(JButton btnRent) {
		this.btnRent = btnRent;
	}

	public JTable getStockTable() {
		return stockTable;
	}

	public void setStockTable(JTable stockTable) {
		this.stockTable = stockTable;
	}

	public JScrollPane getScrpStockTable() {
		return scrpStockTable;
	}

	public void setScrpStockTable(JScrollPane scrpStockTable) {
		this.scrpStockTable = scrpStockTable;
	}

	public JScrollPane getScrpMarketTable() {
		return scrpMarketTable;
	}

	public void setScrpMarketTable(JScrollPane scrpMarketTable) {
		this.scrpMarketTable = scrpMarketTable;
	}

	public JTable getMarketTable() {
		return marketTable;
	}

	public void setMarketTable(JTable marketTable) {
		this.marketTable = marketTable;
	}

	public DefaultTableModel getTblStockModel() {
		return tblStockModel;
	}

	public void setTblStockModel(DefaultTableModel tblStockModel) {
		this.tblStockModel = tblStockModel;
	}

	public DefaultTableModel getTblMarketModel() {
		return tblMarketModel;
	}

	public void setTblMarketModel(DefaultTableModel tblMarketModel) {
		this.tblMarketModel = tblMarketModel;
	}

}
