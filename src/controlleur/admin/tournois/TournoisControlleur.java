package controlleur.admin.tournois;

import vue.Page;
import vue.admin.tournois.VueAdminTournois;
import vue.admin.tournois.creation.VueAdminTournoisCreation;
import vue.admin.tournois.liste.VueAdminTournoisListe;

public class TournoisControlleur {
	private VueAdminTournois vueAdminTournois;

	public TournoisControlleur(VueAdminTournois newVue) {
		this.vueAdminTournois = newVue;
		VueAdminTournoisCreation vueAdminTournoisCreation = new VueAdminTournoisCreation();
		TournoiCréationControlleur tournoiCréationControlleur = new TournoiCréationControlleur(vueAdminTournoisCreation);
		vueAdminTournoisCreation.setControleur(tournoiCréationControlleur);

		VueAdminTournoisListe vueAdminTournoisListe = new VueAdminTournoisListe();
		TournoisListeControlleur tournoisListeControlleur = new TournoisListeControlleur(vueAdminTournoisListe);
		vueAdminTournoisListe.setControleur(tournoisListeControlleur);

		vueAdminTournois.addPage(vueAdminTournoisCreation, Page.TOURNOIS_CREATION);
		vueAdminTournois.addPage(vueAdminTournoisListe, Page.TOURNOIS_LISTE);
		vueAdminTournois.setPage(Page.TOURNOIS_LISTE);
		TournoisObserver.getInstance().setVue(this);
	}

	public void update(Page id) {

		this.vueAdminTournois.setPage(id);
	}
}