package vue.admin.equipes;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JPanel;

import controlleur.EquipesControlleur;

import vue.Page;

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
