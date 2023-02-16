package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextField;

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
			
			usersView.getInformationPanel().setVisible(true);
			usersView.getAddUserPanel().setVisible(false);
			usersView.getEditUserPanel().setVisible(false);	
			usersView.getBtnAgregar().setEnabled(true);
			usersView.getBtnEliminar().setEnabled(true);
			usersView.getBtnEditar().setEnabled(true);
		}
		
		if (e.getSource().equals(usersView.getBtnAgregar())) {
			
			int selectedRowEdit = usersView.getUserTable().getSelectedRow();
						
				usersView.getInformationPanel().setVisible(false);
				usersView.getAddUserPanel().setVisible(true);
				usersView.getBtnEliminar().setEnabled(false);
				usersView.getBtnEditar().setEnabled(false);
				
		}
		
		if (e.getSource().equals(usersView.getBtnOk())) {
			
			String name = usersView.getTextFieldName().getText();
			Double edad = Double.parseDouble(usersView.getTextFieldAge().getText());
			String sexo = usersView.getTextFieldGender().getText();
			Double puntos = Double.parseDouble(usersView.getTextFieldPoints().getText());

			
			usersView.getInformationPanel().setVisible(true);
			usersView.getAddUserPanel().setVisible(false);
			
			usersView.addUserData(name, edad, sexo, puntos);
			usersView.loadUserData();
			usersView.getBtnEditar().setEnabled(true);
			usersView.getBtnEliminar().setEnabled(true);
			
		}
		
		if (e.getSource().equals(usersView.getBtnCancel())) {
			
			usersView.getInformationPanel().setVisible(true);
			usersView.getAddUserPanel().setVisible(false);
			usersView.getBtnEliminar().setEnabled(true);
			usersView.getBtnEditar().setEnabled(true);
			
		}
		
		if (e.getSource().equals(usersView.getBtnEditar())) {
			
			
			int selectedRowEdit = usersView.getUserTable().getSelectedRow();
			
			if(selectedRowEdit >= 0) {
				
				usersView.getInformationPanel().setVisible(false);
				usersView.getEditUserPanel().setVisible(true);
				usersView.getBtnEliminar().setEnabled(false);
				usersView.getBtnAgregar().setEnabled(false);
				
				int selectedRow = usersView.getUserTable().getSelectedRow();
				
				//String name = usersView.getUserTable().getSelectedRow();
				String name = usersView.getTblUserModel().getValueAt(selectedRow, 0).toString();
				Double edad = Double.parseDouble(usersView.getTblUserModel().getValueAt(selectedRow, 1).toString());
				String sexo = usersView.getTblUserModel().getValueAt(selectedRow, 2).toString();
				Double puntos = Double.parseDouble(usersView.getTblUserModel().getValueAt(selectedRow, 3).toString());
				
				usersView.getTextFieldEditName().setText(name);
			    usersView.getTextFieldEditAge().setText(edad.toString());
			    usersView.getTextFieldEditGender().setText(sexo);
			    usersView.getTextFieldEditPoints().setText(puntos.toString());
			}
			

		}
		
		if (e.getSource().equals(usersView.getBtnEditOk())) {
			
			String name = usersView.getTextFieldEditName().getText();
		    Double edad = Double.parseDouble(usersView.getTextFieldEditAge().getText());
		    String sexo = usersView.getTextFieldEditGender().getText();
		    Double puntos = Double.parseDouble(usersView.getTextFieldEditPoints().getText());
		    int selectedRow = usersView.getUserTable().getSelectedRow();
		    
		    usersView.editUserData(name, edad, sexo, puntos, selectedRow);
			
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
			
		        usersView.deleteUserData();
		        usersView.loadUserData();
  
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
		}

	}

}
