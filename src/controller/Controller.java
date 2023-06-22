package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import model.Finance;
import model.User;
import utils.Constants;
import utils.Utils;
import views.FinanceView;
import views.HomeView;
import views.LoginView;
import views.MainPanel;
import views.RankingView;
import views.StockView;
import views.UsersView;

public class Controller implements ActionListener {

	private StringBuffer userType = new StringBuffer();
	private LoginView loginView;
	private HomeView homeView;
	private UsersView usersView;
	private StockView stockView;
	private FinanceView financeView;
	private RankingView rankingView;
	private DatabaseController dbController = new DatabaseController();
	private MainPanel mainPanel = new MainPanel();

	public Controller(LoginView loginView, HomeView homeView, UsersView usersView, StockView stockView,
			FinanceView financeView, RankingView rankingView) {
		this.loginView = loginView;
		this.homeView = homeView;
		this.usersView = usersView;
		this.stockView = stockView;
		this.financeView = financeView;
		this.rankingView = rankingView;
		this.userType.append("admin");
		Class<?> clazz = Controller.class;
		try {
			Object cc = clazz.newInstance();
			Field f1 = cc.getClass().getSuperclass().getDeclaredField("userType");
			f1.setAccessible(true);
		} catch (Exception e) {

		}

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
		if (e.getSource().equals(loginView.getBtnLogin()) || e.getSource().equals(loginView.getTextFieldPassword())) {
			String userInput = loginView.getTextFieldUser().getText();
			String userPassword = "";
			try {
				userPassword = dbController.selectUserAccessPasswsord(userInput);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(loginView, "User could not be retrieved", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			String passwordInput = Utils.convertCharArrayToString(loginView.getTextFieldPassword().getPassword());
			if (loginView.successLogin(passwordInput, userPassword)) {
				try {
					this.userType = new StringBuffer(dbController.selectUserType(userInput));
				} catch (SQLException e1) {

				}
				if (this.userType.toString().equalsIgnoreCase(Constants.USER_TYPE_USER)) {
					homeView.getBtnFinanceView().setVisible(false);
					homeView.getbtnUsersView().setVisible(false);
					homeView.getBtnStockView().setBounds(homeView.getbtnUsersView().getBounds());
					mainPanel.loadPanel(homeView);
					mainPanel.removePanel(loginView);
				} else {
					homeView.getBtnFinanceView().setVisible(true);
					homeView.getbtnUsersView().setVisible(true);
					mainPanel.loadPanel(homeView);
					mainPanel.removePanel(loginView);
				}
			} else {
				JOptionPane.showMessageDialog(loginView, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (e.getSource().equals(loginView.getBtnSignUp())) {
			loginView.getLogInPanel().setVisible(false);
			loginView.getSignUpPanel().setVisible(true);
			loginView.getTextFieldUser().setText("");
			loginView.getTextFieldPassword().setText("");

		}

		if (e.getSource().equals(loginView.getBtnSignUpCancel())) {
			loginView.getLogInPanel().setVisible(true);
			loginView.getSignUpPanel().setVisible(false);
			loginView.getTextFieldSignUpAge().setText("");
			loginView.getTextFieldSignUpName().setText("");
			loginView.getTextFieldSignUpPassword().setText("");
		}

		if (e.getSource().equals(loginView.getBtnSignUpOk())) {
			loginView.getLogInPanel().setVisible(true);
			loginView.getSignUpPanel().setVisible(false);
			try {
				String password = Utils.convertCharArrayToString(loginView.getTextFieldSignUpPassword().getPassword());
				dbController.insertUser(loginView.getTextFieldSignUpName().getText(),
						Integer.parseInt(loginView.getTextFieldSignUpAge().getText()),
						loginView.getComboBoxSignUpGender().getSelectedItem().toString(), 0L);
				dbController.insertUserCredentials(loginView.getTextFieldSignUpName().getText(), password.toString());
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(loginView, "Unable to insert user", "Error", JOptionPane.ERROR_MESSAGE);
			}
			loginView.getTextFieldSignUpAge().setText("");
			loginView.getTextFieldSignUpName().setText("");
			loginView.getTextFieldSignUpPassword().setText("");
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
			stockView.getLblStock().setText("MARKET");
			stockView.getBtnMarketPanel().setEnabled(false);
			stockView.getBtnStockPanel().setEnabled(true);
			try {
				stockView.loadMarketData(dbController.selectAllFinance());
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(usersView, "Unable to load finance data", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			stockView.getScrpStockTable().setVisible(false);
			stockView.getScrpMarketTable().setVisible(true);
		}

		if (e.getSource().equals(stockView.getBtnStockPanel())) {
			stockView.getLblStock().setText("STOCK");
			stockView.getBtnMarketPanel().setEnabled(true);
			stockView.getBtnStockPanel().setEnabled(false);
			try {
				stockView.loadStockData(dbController.selectAllGames());
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(usersView, "Unable to load stock data", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			stockView.getScrpStockTable().setVisible(true);
			stockView.getScrpMarketTable().setVisible(false);
		}

		if (e.getSource().equals(stockView.getBtnBuy())) {
			if (stockView.getStockTable().getSelectedRow() == -1 && stockView.getMarketTable().getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(stockView, "Select game to buy", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				int selectedRow = -1 == stockView.getStockTable().getSelectedRow()
						? stockView.getMarketTable().getSelectedRow()
						: stockView.getStockTable().getSelectedRow();
				try {

					// Si es admin, al comprar un juego subira el stock en 1, si es usuario, bajara
					// stock porque lo compra
					String stockOperation = this.userType.toString().equalsIgnoreCase(Constants.USER_TYPE_ADMIN) ? "+ 1"
							: "- 1";
					// Siempre + 1 porque se cuenta un juego vendido mas
					String financeSoldOperation = "+ 1";
					if (this.userType.toString().equalsIgnoreCase(Constants.USER_TYPE_ADMIN)) {
						String[] buttons = { "Sell", "Rent" };

						int rc = JOptionPane.showOptionDialog(null, "Do you want to buy for sale or for rent?",
								"Confirmation", JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[0]);

						if (rc == 0) {
							// Sell
							dbController.updateSellStockById(
									stockView.getStockTable().getValueAt(selectedRow, 0).toString(), stockOperation);
						} else {
							// Rent
							dbController.updateRentStockById(
									stockView.getStockTable().getValueAt(selectedRow, 0).toString(), stockOperation);
						}
					} else {
						dbController.updateSellStockById(
								stockView.getMarketTable().getValueAt(selectedRow, 0).toString(), stockOperation);
						dbController.updateSoldFinanceById(
								stockView.getMarketTable().getValueAt(selectedRow, 0).toString(), financeSoldOperation);
					}

					stockView.loadStockData(dbController.selectAllGames());

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(stockView, "Unable to update Finance detail", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		if (e.getSource().equals(stockView.getBtnRent())) {
			if (stockView.getMarketTable().getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(stockView, "Select game to rent", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				int row = stockView.getMarketTable().getSelectedRow();
				// Siempre -1 porque es un juego menos en stock para alquiler
				String rentOperation = "- 1";

				// Siempre + 1 porque se cuenta un juego vendido mas
				String financeRentOperation = "+ 1";
				try {
					dbController.updateRentStockById(stockView.getMarketTable().getValueAt(row, 0).toString(),
							rentOperation);
					dbController.updateRentFinanceById(stockView.getMarketTable().getValueAt(row, 0).toString(),
							financeRentOperation);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(stockView, "Unable to update Finance detail", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	private void userControl(ActionEvent e) {
		if (e.getSource().equals(usersView.getBtnHome())) {
			mainPanel.loadPanel(homeView);
			mainPanel.removePanel(usersView);

			usersView.getInformationPanel().setVisible(true);
			usersView.getAddUserPanel().setVisible(false);
			usersView.getEditUserPanel().setVisible(false);
			usersView.getBtnEliminar().setEnabled(true);
			usersView.getBtnEditar().setEnabled(true);
		}

		if (e.getSource().equals(usersView.getBtnOk())) {

			if (usersView.getTextFieldName().getText().isEmpty() || usersView.getTextFieldAge().getText().isEmpty()
					|| usersView.getComboBoxGender().getSelectedIndex() == -1
					|| usersView.getTextFieldPoints().getText().isEmpty()) {

				JOptionPane.showMessageDialog(usersView, "Please fill all the fields", "Error",
						JOptionPane.ERROR_MESSAGE);

			} else {
				try {
					String name = usersView.getTextFieldName().getText();
					int age = Integer.parseInt(usersView.getTextFieldAge().getText());
					String gender = usersView.getComboBoxGender().getSelectedItem().toString();
					Long points = Long.parseLong(usersView.getTextFieldPoints().getText());

					usersView.getInformationPanel().setVisible(true);
					usersView.getAddUserPanel().setVisible(false);

					dbController.insertUser(name, age, gender, points);

					List<User> userList = dbController.selectAllUser();
					usersView.loadUserData(userList);

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

				int selectedRow = usersView.getUserTable().getSelectedRow();

				String name = usersView.getTblUserModel().getValueAt(selectedRow, 0).toString();
				Long edad = Long.parseLong(usersView.getTblUserModel().getValueAt(selectedRow, 1).toString());
				String sexo = usersView.getTblUserModel().getValueAt(selectedRow, 2).toString();
				Long puntos = Long.parseLong(usersView.getTblUserModel().getValueAt(selectedRow, 3).toString());

				usersView.getTextFieldEditName().setText(name);
				usersView.getTextFieldEditAge().setText(edad.toString());
				usersView.getComboBoxEditGender().setToolTipText(sexo);
				usersView.getTextFieldEditPoints().setText(puntos.toString());
			} else {
				JOptionPane.showMessageDialog(usersView, "Select an user please", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}

		if (e.getSource().equals(usersView.getBtnEditOk())) {

			String name = usersView.getTextFieldEditName().getText();
			int age = Integer.parseInt(usersView.getTextFieldEditAge().getText());
			String gender = usersView.getComboBoxEditGender().getSelectedItem().toString();
			Long points = Long.parseLong(usersView.getTextFieldEditPoints().getText());
			int selectedRow = usersView.getUserTable().getSelectedRow();

			try {
				dbController.updateUser(usersView.getUserTable().getValueAt(selectedRow, 0).toString(), name, age,
						gender, points);
				List<User> userList = dbController.selectAllUser();
				usersView.loadUserData(userList);

			} catch (SQLException e1) {

				JOptionPane.showMessageDialog(usersView, "Unable to edit user", "Error", JOptionPane.ERROR_MESSAGE);
			}

			usersView.getInformationPanel().setVisible(true);
			usersView.getEditUserPanel().setVisible(false);
			usersView.getBtnEliminar().setEnabled(true);

		}

		if (e.getSource().equals(usersView.getBtnEditCancel())) {

			usersView.getInformationPanel().setVisible(true);
			usersView.getEditUserPanel().setVisible(false);
			usersView.getBtnEliminar().setEnabled(true);

		}

		if (e.getSource().equals(usersView.getBtnEliminar())) {

			int selectedRow = usersView.getUserTable().getSelectedRow();

			if (selectedRow >= 0) {
				try {
					dbController.deleteUser((String) usersView.getUserTable().getValueAt(selectedRow, 0));
					dbController.deleteUserAccess((String) usersView.getUserTable().getValueAt(selectedRow, 0));
					List<User> userList = dbController.selectAllUser();
					usersView.loadUserData(userList);
				} catch (SQLException e1) {

					JOptionPane.showMessageDialog(usersView, "Unable to delete user", "Error",
							JOptionPane.ERROR_MESSAGE);

				}

			} else {
				JOptionPane.showMessageDialog(usersView, "Select an user please", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	private void homeControl(ActionEvent e) {

		if (e.getSource().equals(homeView.getBtnLogout())) {
			mainPanel.loadPanel(loginView);
			loginView.getTextFieldUser().setText("");
			loginView.getTextFieldPassword().setText("");
			mainPanel.removePanel(homeView);
		}

		if (e.getSource().equals(homeView.getbtnUsersView())) {
			mainPanel.loadPanel(usersView);
			mainPanel.removePanel(homeView);

			List<User> userList;
			try {
				userList = dbController.selectAllUser();
				usersView.loadUserData(userList);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(usersView, "Unable to load user data", "Error",
						JOptionPane.ERROR_MESSAGE);

			}

		}

		if (e.getSource().equals(homeView.getBtnStockView())) {
			if (this.userType.toString().equalsIgnoreCase(Constants.USER_TYPE_USER)) {
				stockView.getBtnStockPanel().setVisible(false);
				stockView.getScrpStockTable().setVisible(false);
				stockView.getBtnMarketPanel().setVisible(false);
				stockView.getScrpMarketTable().setVisible(true);
				stockView.getLblStock().setText("MARKET");
				try {
					stockView.loadMarketData(dbController.selectAllFinance());
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(stockView, "Unable to load game data", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				mainPanel.loadPanel(stockView);
				mainPanel.removePanel(homeView);
			} else {
				stockView.getBtnStockPanel().setVisible(true);
				stockView.getScrpStockTable().setVisible(true);
				stockView.getBtnMarketPanel().setVisible(true);
				stockView.getBtnRent().setVisible(false);
				stockView.getScrpMarketTable().setVisible(false);
				stockView.getBtnMarketPanel().setVisible(false);
				stockView.getBtnStockPanel().setVisible(false);
				mainPanel.loadPanel(stockView);
				mainPanel.removePanel(homeView);
				try {
					stockView.loadStockData(dbController.selectAllGames());
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(stockView, "Unable to load game data", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		if (e.getSource().equals(homeView.getBtnFinanceView())) {
			mainPanel.loadPanel(financeView);
			mainPanel.removePanel(homeView);
			List<Finance> financeList;
			try {
				financeList = dbController.selectAllFinance();
				financeView.loadFinanceData(financeList);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(financeView, "Unable to select finance", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}

		if (e.getSource().equals(homeView.getBtnRankingView())) {
			mainPanel.loadPanel(rankingView);
			List<User> userList;
			try {
				userList = dbController.selectAllUser();
				rankingView.loadRankingData(userList);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(rankingView, "Unable to load user data", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			mainPanel.removePanel(homeView);
		}

	}

}
