package controlleur;

import vue.Page;
import vue.admin.equipes.creation.VueAdminEquipesCreation;
import vue.admin.equipes.liste.VueAdminEquipesListe;
import vue.admin.tournois.VueAdminTournois;
import vue.admin.tournois.liste.VueAdminTournoisListe;

public class TournoisControlleur {
	private VueAdminTournois vueAdminTournois;

	public TournoisControlleur(VueAdminTournois newVue) {
		this.vueAdminTournois = newVue;
		vueAdminTournois.addPage(new VueAdminTournois(), Page.TOURNOIS_CREATION);
		vueAdminTournois.addPage(new VueAdminTournoisListe(), Page.TOURNOIS_LISTE);
		vueAdminTournois.setPage(Page.TOURNOIS_LISTE);
		TournoisObserver.getInstance().setVue(this);
	}

	public void update(Page id) {
		this.vueAdminTournois.setPage(id);
	}
}
