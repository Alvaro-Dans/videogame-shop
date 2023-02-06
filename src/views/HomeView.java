package views;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Controller;

public class HomeView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblInicio;
	private JButton btnStockView;
	private JButton btnFinanceView;
	private JButton btnRankingView;
	private JButton btnUsersView;
	private JButton btnHome;

	public HomeView() {
		setSize(1000, 700);
		setLayout(null);

		lblInicio = new JLabel("Inicio");
		lblInicio.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setBounds(0, 0, 984, 92);
		add(lblInicio);

		JPanel panel = new JPanel();
		panel.setBounds(150, 100, 700, 486);
		add(panel);
		panel.setLayout(new GridLayout(2, 1, 0, 0));

		btnUsersView = new JButton("");
		btnUsersView.setFocusPainted(false);
		btnUsersView.setBorderPainted(false);
		btnUsersView.setBorder(null);
		btnUsersView.setBackground(null);
		btnUsersView.setContentAreaFilled(false);
		btnUsersView.setIcon(new ImageIcon(HomeView.class.getResource("/images/clientes.png")));
		panel.add(btnUsersView);

		btnStockView = new JButton("");
		btnStockView.setFocusPainted(false);
		btnStockView.setIcon(new ImageIcon(HomeView.class.getResource("/images/warehouse.png")));
		btnStockView.setBorderPainted(false);
		btnStockView.setBorder(null);
		btnStockView.setBackground(null);
		btnStockView.setContentAreaFilled(false);
		panel.add(btnStockView);

		btnFinanceView = new JButton("");
		btnFinanceView.setFocusPainted(false);
		btnFinanceView.setBorderPainted(false);
		btnFinanceView.setBorder(null);
		btnFinanceView.setBackground(null);
		btnFinanceView.setContentAreaFilled(false);
		btnFinanceView.setIcon(new ImageIcon(HomeView.class.getResource("/images/facturas.png")));
		panel.add(btnFinanceView);

		btnRankingView = new JButton("");
		btnRankingView.setFocusPainted(false);
		btnRankingView.setBorderPainted(false);
		btnRankingView.setBorder(null);
		btnRankingView.setBackground(null);
		btnRankingView.setContentAreaFilled(false);
		btnRankingView.setIcon(new ImageIcon(HomeView.class.getResource("/images/badge.png")));
		panel.add(btnRankingView);

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
	}

	public void setController(Controller controller) {
		btnHome.addActionListener(controller);
		btnUsersView.addActionListener(controller);
		btnRankingView.addActionListener(controller);
		btnStockView.addActionListener(controller);
		btnFinanceView.addActionListener(controller);
	}

	public JButton getBtnHome() {
		return btnHome;
	}

	public void setBtnHome(JButton btnHome) {
		this.btnHome = btnHome;
	}

	public JButton getBtnStockView() {
		return btnStockView;
	}

	public void setBtnStockView(JButton btnStockView) {
		this.btnStockView = btnStockView;
	}

	public JButton getBtnFinanceView() {
		return btnFinanceView;
	}

	public void setBtnFinanceView(JButton btnFinanceView) {
		this.btnFinanceView = btnFinanceView;
	}

	public JButton getBtnRankingView() {
		return btnRankingView;
	}

	public void setBtnRankingView(JButton btnRankingView) {
		this.btnRankingView = btnRankingView;
	}

	public JButton getBtnUsersView() {
		return btnUsersView;
	}

	public void setBtnUsersView(JButton btnUsersView) {
		this.btnUsersView = btnUsersView;
	}

}
