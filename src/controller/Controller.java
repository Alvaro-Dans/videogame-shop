package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.FinanceView;
import views.HomeView;
import views.MainPanel;
import views.RankingView;
import views.StockView;
import views.UsersView;

public class Controller implements ActionListener {

	private HomeView homeView;
	private UsersView usersView;
	private StockView stockView;
	private FinanceView financeView;
	private RankingView rankingView;
	private MainPanel mainPanel = new MainPanel();

	public Controller(HomeView homeView, UsersView usersView, StockView stockView, FinanceView financeView,
			RankingView rankingView) {
		this.homeView = homeView;
		this.usersView = usersView;
		this.stockView = stockView;
		this.financeView = financeView;
		this.rankingView = rankingView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		homeControl(e);

		userControl(e);
		stockControl(e);
		financeControl(e);
		rankingControl(e);

	}

	private void rankingControl(ActionEvent e) {
		if (e.getSource().equals(rankingView.getBtnHome())) {
			mainPanel.loadPanel(homeView);
			mainPanel.removePanel(rankingView);
		}

	}

	private void financeControl(ActionEvent e) {
		if (e.getSource().equals(financeView.getBtnHome())) {
			mainPanel.loadPanel(homeView);
			mainPanel.removePanel(financeView);
		}

	}

	private void stockControl(ActionEvent e) {
		if (e.getSource().equals(stockView.getBtnHome())) {
			mainPanel.loadPanel(homeView);
			mainPanel.removePanel(stockView);
		}

		if (e.getSource().equals(stockView.getBtnMarketPanel())) {
			stockView.getBtnMarketPanel().setEnabled(false);
			stockView.getBtnStockPanel().setEnabled(true);
			stockView.loadMarketData();
			stockView.getScrpStockTable().setVisible(false);
			stockView.getScrpMarketTable().setVisible(true);
		}

		if (e.getSource().equals(stockView.getBtnStockPanel())) {
			stockView.getBtnMarketPanel().setEnabled(true);
			stockView.getBtnStockPanel().setEnabled(false);
			stockView.loadStockData();
			stockView.getScrpStockTable().setVisible(true);
			stockView.getScrpMarketTable().setVisible(false);
		}

	}

	private void userControl(ActionEvent e) {
		if (e.getSource().equals(usersView.getBtnHome())) {
			mainPanel.loadPanel(homeView);
			mainPanel.removePanel(usersView);
		}

	}

	private void homeControl(ActionEvent e) {
		if (e.getSource().equals(homeView.getBtnUsersView())) {
			mainPanel.loadPanel(usersView);
			mainPanel.removePanel(homeView);
		}

		if (e.getSource().equals(homeView.getBtnStockView())) {
			mainPanel.loadPanel(stockView);
			mainPanel.removePanel(homeView);
			stockView.loadStockData();
		}

		if (e.getSource().equals(homeView.getBtnFinanceView())) {
			mainPanel.loadPanel(financeView);
			mainPanel.removePanel(homeView);
		}

		if (e.getSource().equals(homeView.getBtnRankingView())) {
			mainPanel.loadPanel(rankingView);
			mainPanel.removePanel(homeView);
		}

	}

}
