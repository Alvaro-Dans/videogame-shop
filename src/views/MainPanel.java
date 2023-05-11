
package views;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.DatabaseController;

public class MainPanel extends JFrame {

	private static final long serialVersionUID = 1L;

	private LoginView loginView;
	private HomeView homeView;
	private UsersView usersView;
	private StockView stockView;
	private FinanceView financeView;
	private RankingView rankingView;

	public MainPanel() throws HeadlessException {
		super();

	}

	public MainPanel(LoginView loginView, HomeView homeView, UsersView usersView, StockView stockView,
			FinanceView financeView, RankingView rankingView) {
		this.loginView = loginView;
		this.homeView = homeView;
		this.usersView = usersView;
		this.stockView = stockView;
		this.financeView = financeView;
		this.rankingView = rankingView;

		setIconImage(Toolkit.getDefaultToolkit().getImage(MainPanel.class.getResource("/images/logo.png")));
		setTitle("Videogame SHOP");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		loadPanels(loginView, homeView, usersView, stockView, financeView, rankingView);
	}

	private void loadPanels(LoginView loginView, HomeView homeView, UsersView usersView, StockView stockView,
			FinanceView financeView, RankingView rankingView) {

		getContentPane().add(loginView);
		this.loginView.setVisible(false);

		getContentPane().add(homeView);
		this.homeView.setVisible(false);

		getContentPane().add(usersView);
		this.usersView.setVisible(false);

		getContentPane().add(stockView);
		this.stockView.setVisible(false);

		getContentPane().add(financeView);
		this.financeView.setVisible(false);

		getContentPane().add(rankingView);
		this.rankingView.setVisible(false);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				DatabaseController.closeDatabaseConnection();
				System.out.println("Database connection closed.");
			}
		});

	}

	public void loadPanel(JPanel panel) {
		panel.setVisible(true);
		this.repaint();
		this.validate();
	}

	public void removePanel(JPanel panel) {
		panel.setVisible(false);
	}

	public void makeVisible() {
		setVisible(true);
	}

}
