package controlleur.admin.arbitres;

import dao.Connexion;
import dao.DaoArbitre;
import vue.Page;
import vue.admin.arbitres.creation.VueAdminArbitresCreation;
import vue.admin.equipes.creation.VueAdminEquipesCreation;
import vue.admin.equipes.liste.VueAdminEquipesListe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArbitresCreationControlleur implements ActionListener {
	private VueAdminArbitresCreation vue;
	private DaoArbitre daoArbitre;
	private Connexion c;

	public ArbitresCreationControlleur(VueAdminArbitresCreation newVue) {
		this.vue = newVue;
		c = Connexion.getConnexion();
		daoArbitre = new DaoArbitre(c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vue.getBoutonAnnuler()) {
			ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_LISTE);
		}
	}
}
