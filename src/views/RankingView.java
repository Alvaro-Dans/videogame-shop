package views;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.User;

public class RankingView extends JPanel {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel tblRankingModel;

	private JButton btnHome;
	private JScrollPane scrpRankingTable;
	private JTable rankingTable;
	private JLabel lblRanking;

	public RankingView() {
		setSize(1000, 700);
		setLayout(null);

		btnHome = new JButton("");
		btnHome.setIcon(new ImageIcon(HomeView.class.getResource("/images/home.png")));
		btnHome.setFocusPainted(false);
		btnHome.setBounds(20, 20, 50, 50);
		btnHome.setBorder(null);
		btnHome.setBackground(null);
		btnHome.setContentAreaFilled(false);
		btnHome.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnHome.setIcon(new ImageIcon(HomeView.class.getResource("/images/home_grande.png")));
			}

			public void mouseExited(MouseEvent evt) {
				btnHome.setIcon(new ImageIcon(HomeView.class.getResource("/images/home.png")));
			}
		});
		add(btnHome);

		scrpRankingTable = new JScrollPane();
		scrpRankingTable.setBounds(30, 81, 932, 583);
		add(scrpRankingTable);

		rankingTable = new JTable();
		rankingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rankingTable.setBounds(0, 0, 1, 1);
		// rankingTable.setAutoCreateRowSorter(true);
		scrpRankingTable.setViewportView(rankingTable);

		lblRanking = new JLabel("RANKING");
		lblRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblRanking.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblRanking.setBounds(0, 20, 1000, 41);
		add(lblRanking);

		configurarTabla();
	}

	public void loadRankingData() {
		tblRankingModel.getDataVector().clear();
		List<User> userList = getUserList();
		userList.sort((u1, u2) -> (int) u2.getPoints() - (int) u1.getPoints());
		for (User user : userList) {
			DefaultTableModel model = (DefaultTableModel) rankingTable.getModel();
			model.addRow(new Object[] { user.getName(), user.getPoints() });
		}

	}

	public List<User> getUserList() {

		File file = new File("src/model/data/UserStorage.txt");
		List<User> userList = new ArrayList<>();
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (sc.hasNextLine()) {
			String userLine = sc.nextLine();
			String name = userLine.split(",")[0];
			Long points = Long.parseLong(userLine.split(",")[3]);
			User user = new User();
			user.setName(name);
			user.setPoints(points);
			userList.add(user);
		}

		return userList;

	}

	private void configurarTabla() {

		tblRankingModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblRankingModel.addColumn("USER");
		tblRankingModel.addColumn("POINTS");

		final DefaultTableCellRenderer cellRendUser = new DefaultTableCellRenderer();
		cellRendUser.setHorizontalAlignment(SwingConstants.CENTER);

		rankingTable.setModel(tblRankingModel);

		rankingTable.getColumn("USER").setCellRenderer(cellRendUser);
		rankingTable.getColumn("POINTS").setCellRenderer(cellRendUser);

	}

	public void setController(Controller controller) {
		btnHome.addActionListener(controller);
	}

	public JButton getBtnHome() {
		return btnHome;
	}

	public void setBtnHome(JButton btnHome) {
		this.btnHome = btnHome;
	}

	public JTable getRankingTable() {
		return rankingTable;
	}

	public void setRankingTable(JTable rankingTable) {
		this.rankingTable = rankingTable;
	}

}
