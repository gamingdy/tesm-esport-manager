package vue.admin.arbitres;

import controlleur.admin.arbitres.ArbitresControlleur;
import vue.Page;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Component;

public class VueAdminArbitres extends JPanel {
	private CardLayout cl;

	public VueAdminArbitres() {
		cl = new CardLayout();
		setLayout(cl);
		setOpaque(false);
		ArbitresControlleur controlleur = new ArbitresControlleur(this);
	}

	public void addPage(Component c, Page id) {
		this.add(c, id.name());
	}

	public void setPage(Page id) {
		cl.show(this, id.name());
	}
}
