
package views;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainView() {
		setTitle("Videogame SHOP");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/imagenes/logo.png")));
		setResizable(false);
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
