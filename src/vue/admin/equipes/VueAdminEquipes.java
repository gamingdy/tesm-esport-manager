package vue.admin.equipes;

import controlleur.admin.equipes.EquipesControlleur;
import vue.Page;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JPanel;

public class VueAdminEquipes extends JPanel {

	private static final long serialVersionUID = 1L;

	private CardLayout cl;

	/**
	 * Create the panel.
	 */
	public VueAdminEquipes() {
		cl = new CardLayout();
		setLayout(cl);
		setOpaque(false);
		EquipesControlleur controlleur = new EquipesControlleur(this);
	}

	public void addPage(Component c, Page id) {
		this.add(c, id.name());
	}

	public void setPage(Page id) {
		cl.show(this, id.name());
	}


}
