package vue;

import controller.ControleurTest;
import vue.common.JPanelWithBackground;
import vue.common.WindowResizer;
import vue.main.Main;
import vue.main.TitleBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import vue.admin.main.Main;
import vue.common.JPanelWithBackground;
import vue.common.TitleBar;
import vue.common.WindowResizer;

@SuppressWarnings("serial")
public class Vue extends JFrame{

	private final int HEIGHT = 800;
	private final int WIDTH = 1300;
	private ControleurTest controleur;
	
	private TitleBar titleBar;
	private JPanel panelContenu;
	private CardLayout cl;
	
	public Vue() {
		setBounds(100, 100, WIDTH, HEIGHT);
		setBackground();
		this.controleur=new ControleurTest(this);
		titleBar = new TitleBar(this);
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(titleBar, BorderLayout.NORTH);
		
		new WindowResizer(this, HEIGHT, WIDTH);
		ImageIcon logo = new ImageIcon("assets/logo.png");
		Image newimg = logo.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		setIconImage(newimg);
		
		main = new Main(this.controleur);
		main.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		main.setOpaque(false);
		panelContenu.add(main, BorderLayout.CENTER);
		setPage("Accueil");
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
	}
	
	public void addPage(JComponent page, String identifiant) {
		page.setOpaque(false);
		panelContenu.add(page, identifiant);
	}
}