
package views;

import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPanel extends JFrame {

	private static final long serialVersionUID = 1L;

	private HomeView homeView;
	private UsersView usersView;
	private StockView stockView;
	private FinanceView financeView;
	private RankingView rankingView;

	public MainPanel() throws HeadlessException {
		super();

	}

	public MainPanel(HomeView homeView, UsersView usersView, StockView stockView, FinanceView financeView,
			RankingView rankingView) {
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

		loadPanels(homeView, usersView, stockView, financeView, rankingView);
	}

	private void loadPanels(HomeView homeView, UsersView usersView, StockView stockView, FinanceView financeView,
			RankingView rankingView) {

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
