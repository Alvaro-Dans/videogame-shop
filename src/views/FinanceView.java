package views;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Controller;

public class FinanceView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton btnHome;
	private JLabel lblFinance;

	public FinanceView() {
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

		lblFinance = new JLabel("FINANCE");
		lblFinance.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinance.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblFinance.setBounds(0, 20, 1000, 41);
		add(lblFinance);
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

}
