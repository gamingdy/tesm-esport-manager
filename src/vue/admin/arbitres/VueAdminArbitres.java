package vue.admin.arbitres;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controlleur.VueObserver;
import controlleur.admin.arbitres.ArbitresControlleur;
import controlleur.admin.arbitres.ArbitresListeControlleur;
import controlleur.admin.tournois.TournoisControlleur;
import vue.Page;
import vue.common.CustomColor;
import vue.common.MaFont;

import java.awt.*;

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
