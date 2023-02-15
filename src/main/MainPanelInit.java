package main;

import java.awt.EventQueue;

import controller.Controller;
import views.FinanceView;
import views.HomeView;
import views.MainPanel;
import views.RankingView;
import views.StockView;
import views.UsersView;

public class MainPanelInit {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

				HomeView homeView = new HomeView();
				UsersView usersView = new UsersView();
				StockView stockView = new StockView();
				FinanceView financeView = new FinanceView();
				RankingView rankingView = new RankingView();

				MainPanel mainPanel = new MainPanel(homeView, usersView, stockView, financeView, rankingView);

				Controller controller = new Controller(homeView, usersView, stockView, financeView, rankingView);

				homeView.setController(controller);
				usersView.setControllerUser(controller);
				stockView.setController(controller);
				financeView.setController(controller);
				rankingView.setController(controller);

				mainPanel.add(homeView);
				mainPanel.loadPanel(homeView);
				mainPanel.setVisible(true);

			}
		});

	}

}
