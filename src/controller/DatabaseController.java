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
import model.User;

public class DatabaseController {

	private static Connection con = null;

	public DatabaseController() {
		initializeDataBase();
	}

	public void insertUser(String name, int age, String gender, Long points, String role) throws SQLException {
		String insertSQL = "INSERT INTO USER (UserName, Age, Gender, Points, Role) VALUES (?, ?, ?, ?, ?);";

		PreparedStatement pstmt = con.prepareStatement(insertSQL);
		pstmt.setString(1, name);
		pstmt.setInt(2, age);
		pstmt.setString(3, gender);
		pstmt.setLong(4, points);
		pstmt.setString(5, role);
		pstmt.executeUpdate();
	}

	public void insertFinanceOperation(String game, int soldNumber, int rentedNumber, double sellPrice,
			double rentPrice) throws SQLException {
		String insertSQL = "INSERT INTO FINANCE (Game, SoldNumber, RentedNumber, SellPrice, RentPrice) VALUES (?, ?, ?, ?, ?);";

		PreparedStatement pstmt = con.prepareStatement(insertSQL);
		pstmt.setString(1, game);
		pstmt.setInt(2, soldNumber);
		pstmt.setInt(3, rentedNumber);
		pstmt.setDouble(4, sellPrice);
		pstmt.setDouble(5, rentPrice);
		pstmt.executeUpdate();
	}

	public void insertGame(String name, int sellStock, int rentStock) throws SQLException {
		String insertSQL = "INSERT INTO GAME (GameName, SellStock, RentStock) VALUES (?, ?, ?);";

		PreparedStatement pstmt = con.prepareStatement(insertSQL);
		pstmt.setString(1, name);
		pstmt.setInt(2, sellStock);
		pstmt.setInt(3, rentStock);
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
	
	
	//LISTA USER
	public List<User> selectAllUser() throws SQLException {
		List<User> databaseUserList = new ArrayList<>();
		String selectSQL = "SELECT * FROM USER;";

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(selectSQL);

		while (rs.next()) {
			databaseUserList.add(new User(rs.getString("UserName"), rs.getInt("Age"), rs.getString("Gender"), rs.getLong("Points"), rs.getString("Role")));
		}
		return databaseUserList;
	}
	
	
	public void deleteUser(String user) throws SQLException {
		
		String insertSQL = "DELETE FROM USER WHERE UserName='"+ user +"';";

		PreparedStatement pstmt = con.prepareStatement(insertSQL);
	
		pstmt.executeUpdate();
	}
	
	
	
	public void editUser(String user, String name, int age, String gender, Long points) throws SQLException {
		
		String insertSQL = "UPDATE USER"
				+ "\nSET UserName=" + "'" + name + "'" + ", "
				+ "Age=" + String.valueOf(age)+ ", "
				+ "Gender=" + "'" + gender + "'" + ", "
				+ "Points=" +String.valueOf(points)
				+ "\nWHERE UserName=" + "'" + user + "'" + ";";

		PreparedStatement pstmt = con.prepareStatement(insertSQL);
		pstmt.executeUpdate();
		
		
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
