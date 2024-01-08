package vue.admin.equipes;

import controlleur.admin.equipes.EquipesControlleur;
import vue.Page;

import javax.swing.*;
import java.awt.*;

public class VueAdminEquipes extends JPanel {

	private static final long serialVersionUID = 1L;

	private CardLayout cl;
	private EquipesControlleur controlleur;

	/**
	 * Create the panel.
	 */
	public VueAdminEquipes() {
		cl = new CardLayout();
		setLayout(cl);
		setOpaque(false);
		controlleur = new EquipesControlleur(this);
	}

	public void updateControlleur(Page id) {
		controlleur.update(id);
	} //

	public void addPage(Component c, Page id) {
		this.add(c, id.name());
	}

	public void setPage(Page id) {
		cl.show(this, id.name());
	}


}
