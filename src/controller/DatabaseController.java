package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Finance;

public class DatabaseController {

	private static Connection con = null;

	public DatabaseController() {
		initializeDataBase();
	}

	public void insertUser(int id, String name, int age, String sex, Long points, String role) throws SQLException {
		String insertSQL = "INSERT INTO USER (id, UserName, Age, Sex, Points, Role) VALUES (?, ?, ?, ?, ?, ?);";

		PreparedStatement pstmt = con.prepareStatement(insertSQL);
		pstmt.setInt(1, id);
		pstmt.setString(2, name);
		pstmt.setInt(3, age);
		pstmt.setString(4, sex);
		pstmt.setLong(5, points);
		pstmt.setString(6, role);
		pstmt.executeUpdate();
	}

	public void insertFinanceOperation(int id, String game, int soldNumber, int rentedNumber, double sellPrice,
			double rentPrice) throws SQLException {
		String insertSQL = "INSERT INTO FINANCE (id, Game, SoldNumber, RentedNumber, SellPrice, RentPrice) VALUES (?, ?, ?, ?, ?, ?);";

		PreparedStatement pstmt = con.prepareStatement(insertSQL);
		pstmt.setInt(1, id);
		pstmt.setString(2, game);
		pstmt.setInt(3, soldNumber);
		pstmt.setInt(4, rentedNumber);
		pstmt.setDouble(5, sellPrice);
		pstmt.setDouble(6, rentPrice);
		pstmt.executeUpdate();
	}

	public void insertGame(int id, String name, int sellStock, int rentStock) throws SQLException {
		String insertSQL = "INSERT INTO GAME (id, GameName, SellStock, RentStock) VALUES (?, ?, ?, ?);";

		PreparedStatement pstmt = con.prepareStatement(insertSQL);
		pstmt.setInt(1, id);
		pstmt.setString(2, name);
		pstmt.setInt(3, sellStock);
		pstmt.setInt(4, rentStock);
		pstmt.executeUpdate();
	}

	public void insertUserCredentials(String userName, String password) throws SQLException {
		String insertSQL = "INSERT INTO USER_ACCESS (User, Password) VALUES (?, ?);";

		PreparedStatement pstmt = con.prepareStatement(insertSQL);
		pstmt.setString(1, userName);
		pstmt.setString(2, password);
		pstmt.executeUpdate();
	}

	public List<Finance> selectAllFinance() throws SQLException {
		List<Finance> financeList = new ArrayList<>();
		String selectSQL = "SELECT * FROM FINANCE;";

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(selectSQL);

		while (rs.next()) {
			financeList.add(new Finance(rs.getString("Game"), rs.getInt("SoldNumber"), rs.getInt("RentedNumber"),
					rs.getDouble("SellPrice"), rs.getDouble("RentPrice")));
		}
		return financeList;
	}

	/**
	 * Method to initialize database and create the tables if there haven't been
	 * created yet.
	 */
	private void initializeDataBase() {
		try {
			con = DriverManager.getConnection("jdbc:sqlite:src/model/data/database.db");
			Statement stmt = con.createStatement();
			stmt.execute("SELECT Name FROM sqlite_schema WHERE type='table' AND name='USER_ACCESS';");
			System.out.println("Table USER_ACCESS loaded.");
			stmt.execute("SELECT Name FROM sqlite_schema WHERE type='table' AND name='USER';");
			System.out.println("Table USER loaded.");
			stmt.execute("SELECT Name FROM sqlite_schema WHERE type='table' AND name='GAME';");
			System.out.println("Table GAME loaded.");
			stmt.execute("SELECT Name FROM sqlite_schema WHERE type='table' AND name='FINANCE';");
			System.out.println("Table FINANCE loaded.");
		} catch (Exception ex1) {
			System.out.println("Unable to connect to Database");
		}

	}

	public static void closeDatabaseConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
