package vue;

import vue.common.JPanelWithBackground;
import vue.common.WindowResizer;
import vue.main.Main;
import vue.main.TitleBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Vue extends JFrame{

	private final int HEIGHT = 800;
	private final int WIDTH = 1300;
	
	
	private TitleBar titleBar;
	private JPanel panelContenu;
	private Main main;
	
	public Vue() {
		setBounds(100, 100, WIDTH, HEIGHT);
		setBackground();

		titleBar = new TitleBar(this);
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelContenu.add(titleBar, BorderLayout.NORTH);
		
		new WindowResizer(this, HEIGHT, WIDTH);
		ImageIcon logo = new ImageIcon("assets/logo.png");
		Image newimg = logo.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		setIconImage(newimg);
		
		main = new Main();
		main.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		main.setOpaque(false);
		panelContenu.add(main, BorderLayout.CENTER);
		setPage("Accueil");
	}

	private void setBackground() {
		panelContenu = null;
		try {
			panelContenu = new JPanelWithBackground("assets/background.jpg", 1300, 800);
		} catch (IOException e) {
			panelContenu = new JPanel();
			panelContenu.setBackground(Color.red.darker());
		}
		setContentPane(panelContenu);
	}

	public void updateBackgroundSize() {
		if (panelContenu instanceof JPanelWithBackground) {
			((JPanelWithBackground) panelContenu).updateBackgroundSize(this.getWidth(), this.getHeight());
		}
	}

	/**
	 * Change la page de contenue du main
	 *
	 * @param identifiant l'identifiant
	 */
	public void setPage(String identifiant) {
		main.setPage(identifiant);
		titleBar.setTitle(identifiant);
	}
	
	
}
