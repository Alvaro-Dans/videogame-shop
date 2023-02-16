package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import views.FinanceView;
import views.HomeView;
import views.LoginView;
import views.MainPanel;
import views.RankingView;
import views.StockView;
import views.UsersView;

public class Controller implements ActionListener {

	private LoginView loginView;
	private HomeView homeView;
	private UsersView usersView;
	private StockView stockView;
	private FinanceView financeView;
	private RankingView rankingView;
	private MainPanel mainPanel = new MainPanel();

	public Controller(LoginView loginView, HomeView homeView, UsersView usersView, StockView stockView,
			FinanceView financeView, RankingView rankingView) {
		this.loginView = loginView;
		this.homeView = homeView;
		this.usersView = usersView;
		this.stockView = stockView;
		this.financeView = financeView;
		this.rankingView = rankingView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		loginControl(e);
		homeControl(e);

		userControl(e);
		stockControl(e);
		financeControl(e);
		rankingControl(e);

	}

	private void loginControl(ActionEvent e) {
		if (e.getSource().equals(loginView.getBtnLogin())) {
			String password = "";
			char[] passwordChar = loginView.getTextFieldPassword().getPassword();
			for (char c : passwordChar) {
				password += c;
			}
			if (loginView.successLogin(loginView.getTextFieldUser().getText(), password)) {
				mainPanel.loadPanel(homeView);
				mainPanel.removePanel(loginView);
			} else {
				JOptionPane.showMessageDialog(loginView, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

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

		if (e.getSource().equals(stockView.getBtnBuy())) {

		}

		if (e.getSource().equals(stockView.getBtnRent())) {

		}

		if (e.getSource().equals(stockView.getBtnSell())) {

		}

	}

	private void userControl(ActionEvent e) {
		if (e.getSource().equals(usersView.getBtnHome())) {
			mainPanel.loadPanel(homeView);
			mainPanel.removePanel(usersView);

			usersView.getInformationPanel().setVisible(true);
			usersView.getAddUserPanel().setVisible(false);
			usersView.getEditUserPanel().setVisible(false);
			usersView.getBtnAgregar().setEnabled(true);
			usersView.getBtnEliminar().setEnabled(true);
			usersView.getBtnEditar().setEnabled(true);
		}

		if (e.getSource().equals(usersView.getBtnAgregar())) {

			usersView.getInformationPanel().setVisible(false);
			usersView.getAddUserPanel().setVisible(true);
			usersView.getBtnEliminar().setEnabled(false);
			usersView.getBtnEditar().setEnabled(false);

		}

		if (e.getSource().equals(usersView.getBtnOk())) {

			if (usersView.getTextFieldName().getText().isEmpty() || usersView.getTextFieldAge().getText().isEmpty()
					|| usersView.getTextFieldGender().getText().isEmpty()
					|| usersView.getTextFieldPoints().getText().isEmpty()) {

				JOptionPane.showMessageDialog(usersView, "Please fill all the fields", "Error",
						JOptionPane.ERROR_MESSAGE);

			} else {
				try {
					String name = usersView.getTextFieldName().getText();
					int age = Integer.parseInt(usersView.getTextFieldAge().getText());
					String gender = usersView.getTextFieldGender().getText();
					Long points = Long.parseLong(usersView.getTextFieldPoints().getText());

					usersView.getInformationPanel().setVisible(true);
					usersView.getAddUserPanel().setVisible(false);

					usersView.addUserData(name, age, gender, points);
					usersView.loadUserData();
					usersView.getBtnEditar().setEnabled(true);
					usersView.getBtnEliminar().setEnabled(true);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(usersView, "Errors found in fields", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		}

		if (e.getSource().equals(usersView.getBtnCancel())) {

			usersView.getInformationPanel().setVisible(true);
			usersView.getAddUserPanel().setVisible(false);
			usersView.getBtnEliminar().setEnabled(true);
			usersView.getBtnEditar().setEnabled(true);

		}

		if (e.getSource().equals(usersView.getBtnEditar())) {

			int selectedRowEdit = usersView.getUserTable().getSelectedRow();

			if (selectedRowEdit >= 0) {

				usersView.getInformationPanel().setVisible(false);
				usersView.getEditUserPanel().setVisible(true);
				usersView.getBtnEliminar().setEnabled(false);
				usersView.getBtnAgregar().setEnabled(false);

				int selectedRow = usersView.getUserTable().getSelectedRow();

				String name = usersView.getTblUserModel().getValueAt(selectedRow, 0).toString();
				Long edad = Long.parseLong(usersView.getTblUserModel().getValueAt(selectedRow, 1).toString());
				String sexo = usersView.getTblUserModel().getValueAt(selectedRow, 2).toString();
				Long puntos = Long.parseLong(usersView.getTblUserModel().getValueAt(selectedRow, 3).toString());

				usersView.getTextFieldEditName().setText(name);
				usersView.getTextFieldEditAge().setText(edad.toString());
				usersView.getTextFieldEditGender().setText(sexo);
				usersView.getTextFieldEditPoints().setText(puntos.toString());
			} else {
				JOptionPane.showMessageDialog(usersView, "Select an user please", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}

		if (e.getSource().equals(usersView.getBtnEditOk())) {

			String name = usersView.getTextFieldEditName().getText();
			int age = Integer.parseInt(usersView.getTextFieldEditAge().getText());
			String gender = usersView.getTextFieldEditGender().getText();
			Long points = Long.parseLong(usersView.getTextFieldEditPoints().getText());
			int selectedRow = usersView.getUserTable().getSelectedRow();

			usersView.editUserData(name, age, gender, points, selectedRow);

			usersView.loadUserData();

			usersView.getInformationPanel().setVisible(true);
			usersView.getEditUserPanel().setVisible(false);
			usersView.getBtnEliminar().setEnabled(true);
			usersView.getBtnAgregar().setEnabled(true);

		}

		if (e.getSource().equals(usersView.getBtnEditCancel())) {

			usersView.getInformationPanel().setVisible(true);
			usersView.getEditUserPanel().setVisible(false);
			usersView.getBtnEliminar().setEnabled(true);
			usersView.getBtnAgregar().setEnabled(true);

		}

		if (e.getSource().equals(usersView.getBtnEliminar())) {

			int selectedRow = usersView.getUserTable().getSelectedRow();

			if (selectedRow >= 0) {
				usersView.deleteUserData();
				usersView.loadUserData();
			} else {
				JOptionPane.showMessageDialog(usersView, "Select an user please", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	private void homeControl(ActionEvent e) {
		if (e.getSource().equals(homeView.getBtnUsersView())) {
			mainPanel.loadPanel(usersView);
			mainPanel.removePanel(homeView);
			usersView.loadUserData();
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
			rankingView.loadRankingData();
		}

	}

}
