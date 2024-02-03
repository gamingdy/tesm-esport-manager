package vue.admin.tournois;

import controlleur.admin.tournois.TournoisControlleur;
import vue.Page;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Component;

public class VueAdminTournois extends JPanel {

	private CardLayout cl;

	/**
	 * Create the panel.
	 */
	public VueAdminTournois() {
		cl = new CardLayout();
		setLayout(cl);
		setOpaque(false);
		TournoisControlleur controlleur = new TournoisControlleur(this);
	}

	public void addPage(Component c, Page id) {
		this.add(c, id.name());
	}

	public void setPage(Page id) {
		cl.show(this, id.name());
	}


}
