package controlleur.admin.arbitres;

import vue.Page;
import vue.admin.arbitres.VueAdminArbitres;
import vue.admin.arbitres.creation.VueAdminArbitresCreation;
import vue.admin.arbitres.liste.VueAdminArbitresListe;

public class ArbitresControlleur {
	private VueAdminArbitres vue;
	private ArbitresListeControlleur arbitresListeControlleur;
	private ArbitresCreationControlleur arbitresCreationControlleur;


	public ArbitresControlleur(VueAdminArbitres newVue) {
		this.vue = newVue;
		VueAdminArbitresListe vueAdminArbitresListe = new VueAdminArbitresListe();
		arbitresListeControlleur = new ArbitresListeControlleur(vueAdminArbitresListe);
		vueAdminArbitresListe.setControleur(arbitresListeControlleur);

		VueAdminArbitresCreation vueAdminArbitresCreation = new VueAdminArbitresCreation();
		arbitresCreationControlleur = new ArbitresCreationControlleur(vueAdminArbitresCreation);
		vueAdminArbitresCreation.setControleur(arbitresCreationControlleur);

		vue.addPage(vueAdminArbitresCreation, Page.ARBITRES_CREATION);
		vue.addPage(vueAdminArbitresListe, Page.ARBITRES_LISTE);
		vue.setPage(Page.ARBITRES_LISTE);
		ArbitresObserver.getInstance().setVue(this);
	}

	public void update(Page id) {
		if (Page.ARBITRES_LISTE.equals(id)) {
			this.arbitresListeControlleur.update();
		}
		this.vue.setPage(id);
	}
}
