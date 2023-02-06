
package views;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainView() {
		setTitle("Videogame SHOP");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/imagenes/logo.png")));
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setBounds(0, 0, 984, 92);
		getContentPane().add(lblInicio);

		JPanel panel = new JPanel();
		panel.setBounds(50, 110, 884, 510);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(2, 1, 45, 45));

		JButton btnUsersView = new JButton("New button");
		panel.add(btnUsersView);

		JButton btnStockView = new JButton("New button");
		panel.add(btnStockView);

		JButton btnFinanceView = new JButton("New button");
		panel.add(btnFinanceView);

		JButton btnRankingView = new JButton("New button");
		panel.add(btnRankingView);
	}

	public void cargarPanel(JPanel panel) {
		panel.setVisible(true);
		this.repaint();
		this.validate();
	}

	public void quitarPanel(JPanel panel) {
		panel.setVisible(false);
	}

	public void hacerVisible() {
		setVisible(true);
	}
}
