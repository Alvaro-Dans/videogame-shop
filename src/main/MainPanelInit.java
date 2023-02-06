package main;

import java.awt.EventQueue;

import views.MainView;

public class MainPanelInit {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				MainView mainView = new MainView();
				mainView.hacerVisible();
			}
		});

	}

}
