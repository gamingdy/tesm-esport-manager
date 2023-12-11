package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.VueControleur;
import vue.common.JPanelWithBackground;
import vue.common.TitleBar;
import vue.common.WindowResizer;

@SuppressWarnings("serial")
public class Vue extends JFrame {

	private static final int HEIGHT = 800;
	private static final int WIDTH = 1300;


	private TitleBar titleBar;
	private JPanel panelContenu;
	private CardLayout cl;

	public Vue() throws Exception {
		setBounds(100, 100, WIDTH, HEIGHT);
		setBackground();

		titleBar = TitleBar.getInstance(this);

		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(titleBar, BorderLayout.NORTH);

		new WindowResizer(this, HEIGHT, WIDTH);
		ImageIcon logo = new ImageIcon("assets/logo.png");
		Image newimg = logo.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		setIconImage(newimg);

		panelContenu = new JPanel();
		cl = new CardLayout();
		panelContenu.setLayout(cl);
		add(panelContenu, BorderLayout.CENTER);
		panelContenu.setOpaque(false);
		VueControleur mainController = new VueControleur(this);
	}

	private void setBackground() {
		JPanel panel = null;
		try {
			panel = new JPanelWithBackground("assets/background.jpg", HEIGHT, WIDTH);
		} catch (IOException e) {
			panel = new JPanel();
			panel.setBackground(Color.red.darker());
		}
		setContentPane(panel);
	}

	public void updateBackgroundSize() {
		if (getContentPane() instanceof JPanelWithBackground) {
			((JPanelWithBackground) getContentPane()).updateBackgroundSize(this.getWidth(), this.getHeight());
		}
	}

	public void setPage(String identifiant) {
		cl.show(panelContenu, identifiant);
		titleBar.setTitle(identifiant);
	}

	public void addPage(JComponent page, String identifiant) {
		page.setOpaque(false);
		panelContenu.add(page, identifiant);
	}
}