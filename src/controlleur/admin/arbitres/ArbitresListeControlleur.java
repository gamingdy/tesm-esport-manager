package controlleur.admin.arbitres;

import controlleur.ControlleurObserver;
import vue.Page;
import vue.admin.arbitres.liste.VueAdminArbitresListe;

import javax.naming.ldap.Control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArbitresListeControlleur implements ControlleurObserver, ActionListener {
	private VueAdminArbitresListe vue;

	public ArbitresListeControlleur(VueAdminArbitresListe newVue) {
		this.vue = newVue;
	}

	public void update() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vue.getBoutonAjouter()) {
			ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION);
		}
	}
}
