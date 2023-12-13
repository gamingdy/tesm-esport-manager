package vue.admin;

import controlleur.BoutonMenuControlleur;
import controlleur.VueObserver;
import vue.Page;
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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controlleur.AdminControlleur;
import controlleur.BoutonMenuControlleur;
import controlleur.VueObserver;

@SuppressWarnings("serial")
public class VueAdmin extends JPanel {

	private Main main;
	private MenuNavBar navbar;
	private BoutonMenuControlleur controlleurBoutons;
	private AdminControlleur controlleur;

	public VueAdmin() {
		setLayout(new BorderLayout());

		controlleur = new AdminControlleur(this);
		navbar = new MenuNavBar();
		main = new Main(navbar);
		controlleurBoutons = new BoutonMenuControlleur(navbar, this);
		navbar.addButtonControlleur(controlleurBoutons);
		main.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		main.setOpaque(false);
		add(main, BorderLayout.CENTER);
		setPage(Page.ACCUEIL_ADMIN.getNom());
	}


	/**
	 * Change la page de contenue du main
	 *
	 * @param identifiant l'identifiant
	 */
	public void setPage(String identifiant) {
		main.setPage(identifiant);
		TitleBar.getInstance().setTitle(identifiant);
	}

	public void addPage(JComponent c, String id) {
		main.addPage(c, id);
	}


	public void attachObserver(VueObserver obs) {
		this.controlleurBoutons.attach(obs);
	}


}
