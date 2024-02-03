package vue.admin;

import controlleur.AdminControlleur;
import vue.Page;
import vue.admin.main.Main;
import vue.admin.main.MenuNavBar;
import vue.common.TitleBar;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class VueAdmin extends JPanel {

	private Main main;
	private MenuNavBar navbar;
	private AdminControlleur controlleurBoutons;


	public VueAdmin() {
		setLayout(new BorderLayout());
		navbar = new MenuNavBar();
		main = new Main(navbar);
		controlleurBoutons = new AdminControlleur(navbar, this);
		navbar.addButtonControlleur(controlleurBoutons);
		main.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		main.setOpaque(false);
		add(main, BorderLayout.CENTER);
		setPage(Page.ACCUEIL_ADMIN);
	}


	/**
	 * Change la page de contenue du main
	 *
	 * @param equipes l'identifiant
	 */
	public void setPage(Page equipes) {
		main.setPage(equipes);
		TitleBar.getInstance().setTitle(equipes.getNom());
	}


	/**
	 * Ajoute une page pour l'admin (par exemple VueAdminEquipes MAIS PAS VueAdminEquipesCreation qui sera ajoutée dans VueAdminEquipes)
	 *
	 * @param c       la page à ajouter (VueAdmin...)
	 * @param equipes le nom de la page
	 */
	public void addPage(JComponent c, Page equipes) {
		main.addPage(c, equipes);
	}


}
