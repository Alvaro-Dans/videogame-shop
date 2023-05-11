package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Finance;

public class FinanceView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton btnHome;
	private JLabel lblFinance;
	private JTable financeTable;
	private DefaultTableModel tblFinanceModel;
	private JLabel lblTotalPrice;

	public FinanceView() {
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

		financeTable = new JTable();
		financeTable.setRowHeight(20);
		financeTable.setBorder(null);
		financeTable.setBackground(new Color(240, 240, 240));
		financeTable.setShowHorizontalLines(false);
		financeTable.setShowVerticalLines(false);
		financeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		financeTable.setBounds(79, 134, 837, 400);
		add(financeTable);

		lblFinance = new JLabel("FINANCE");
		lblFinance.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinance.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblFinance.setBounds(0, 20, 1000, 41);
		add(lblFinance);

		JLabel lblQuantity = new JLabel("QUANTITY");
		lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantity.setBorder(new MatteBorder(3, 0, 3, 0, (Color) new Color(0, 0, 0)));
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuantity.setBounds(79, 100, 219, 35);
		add(lblQuantity);

		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescription.setBorder(new MatteBorder(3, 0, 3, 0, (Color) new Color(0, 0, 0)));
		lblDescription.setBounds(294, 100, 188, 35);
		add(lblDescription);

		JLabel lblPrice = new JLabel("PRICE PER UNIT");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrice.setBorder(new MatteBorder(3, 0, 3, 0, (Color) new Color(0, 0, 0)));
		lblPrice.setBounds(459, 100, 273, 35);
		add(lblPrice);

		JLabel lblFinalPrice = new JLabel("FINAL PRICE");
		lblFinalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinalPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFinalPrice.setBorder(new MatteBorder(3, 0, 3, 0, (Color) new Color(0, 0, 0)));
		lblFinalPrice.setBounds(710, 100, 206, 35);
		add(lblFinalPrice);

		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotal.setBounds(575, 545, 64, 25);
		add(lblTotal);

		lblTotalPrice = new JLabel("");
		lblTotalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotalPrice.setBounds(767, 545, 100, 25);
		add(lblTotalPrice);

		configurarTabla();
	}

	public void loadFinanceData(List<Finance> financeList) {
		tblFinanceModel.getDataVector().clear();
		Double totalPrice = 0.0;

		for (Finance finance : financeList) {
			String name = finance.getName();
			int sold = finance.getSoldNumber();
			int rented = finance.getRentedNumber();
			Double sellPrice = finance.getSellPrice();
			Double rentPrice = finance.getRentPrice();
			Double totalSale = (double) sold * sellPrice;
			Double totalRent = (double) rented * rentPrice;
			totalPrice += totalSale + totalRent;
			
			DefaultTableModel model = (DefaultTableModel) financeTable.getModel();
			model.addRow(new Object[] { sold, name + " (Sold)", sellPrice + "€", totalSale + "€" });
			model.addRow(new Object[] { rented, name + " (Rented)", rentPrice + "€", totalRent + "€" });
		}
		lblTotalPrice.setText(totalPrice.toString() + "€");
	}

	private void configurarTabla() {

		tblFinanceModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblFinanceModel.addColumn("QUANTITY");
		tblFinanceModel.addColumn("DESCRIPTION");
		tblFinanceModel.addColumn("PRICE PER UNIT");
		tblFinanceModel.addColumn("FINAL PRICE");

		final DefaultTableCellRenderer cellRendFinance = new DefaultTableCellRenderer();
		cellRendFinance.setHorizontalAlignment(SwingConstants.CENTER);

		financeTable.setModel(tblFinanceModel);

		financeTable.getColumn("QUANTITY").setCellRenderer(cellRendFinance);
		financeTable.getColumn("DESCRIPTION").setCellRenderer(cellRendFinance);
		financeTable.getColumn("PRICE PER UNIT").setCellRenderer(cellRendFinance);
		financeTable.getColumn("FINAL PRICE").setCellRenderer(cellRendFinance);

	}

	public void setController(Controller controller) {
		btnHome.addActionListener(controller);
	}

	public JButton getBtnHome() {
		return btnHome;
	}

	public void setBtnHome(JButton btnHome) {
		this.btnHome = btnHome;
	}
}
