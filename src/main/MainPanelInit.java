package main;

import java.awt.EventQueue;

import controller.Controller;
import views.FinanceView;
import views.HomeView;
import views.LoginView;
import views.MainPanel;
import views.RankingView;
import views.StockView;
import views.UsersView;

public class MainPanelInit {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

				LoginView loginView = new LoginView();              
				HomeView homeView = new HomeView();
				UsersView usersView = new UsersView();
				StockView stockView = new StockView();
				FinanceView financeView = new FinanceView();
				RankingView rankingView = new RankingView();

				MainPanel mainPanel = new MainPanel(loginView, homeView, usersView, stockView, financeView,
						rankingView);

				Controller controller = new Controller(loginView, homeView, usersView, stockView, financeView,
						rankingView);

				loginView.setController(controller);
				homeView.setController(controller);
				usersView.setControllerUser(controller);
				stockView.setController(controller);
				financeView.setController(controller);
				rankingView.setController(controller);

				mainPanel.add(loginView);
				mainPanel.loadPanel(loginView);
				mainPanel.setVisible(true);

			}
		});

	}

}
