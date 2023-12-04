package vue.admin;

import controller.BoutonMenuControlleur;
import controller.VueObserver;
import vue.admin.main.Main;
import vue.admin.main.MenuNavBar;
import vue.common.JPanelWithBackground;
import vue.common.TitleBar;
import vue.common.WindowResizer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VueAdmin extends JPanel {

	private Main main;
	private MenuNavBar navbar;
	private BoutonMenuControlleur controlleurBoutons;

	public VueAdmin() {
		setLayout(new BorderLayout());

		navbar = new MenuNavBar();
		main = new Main(navbar);
		controlleurBoutons = new BoutonMenuControlleur(navbar, this);
		navbar.addButtonControlleur(controlleurBoutons);
		main.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		main.setOpaque(false);
		add(main, BorderLayout.CENTER);
		setPage("Accueil");
	}


	/**
	 * Change la page de contenue du main
	 *
	 * @param identifiant l'identifiant
	 */
	public void setPage(String identifiant) {
		main.setPage(identifiant);
	}
	
	
}
