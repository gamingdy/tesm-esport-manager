package controlleur.admin.arbitres;

import controlleur.ControlleurObserver;
import controlleur.admin.tournois.TournoiCréationControlleur;
import controlleur.admin.tournois.TournoisListeControlleur;
import controlleur.admin.tournois.TournoisObserver;
import dao.Connexion;
import dao.DaoMatche;
import dao.DaoSaison;
import dao.DaoTournoi;
import vue.Page;
import vue.admin.arbitres.VueAdminArbitres;
import vue.admin.arbitres.creation.VueAdminArbitresCreation;
import vue.admin.arbitres.liste.VueAdminArbitresListe;
import vue.admin.tournois.creation.VueAdminTournoisCreation;
import vue.admin.tournois.liste.VueAdminTournoisListe;

public class ArbitresControlleur implements ControlleurObserver {
	private VueAdminArbitres vue;
	private ArbitresListeControlleur controlleur;
	private DaoTournoi daoTournoi;
	private DaoSaison daoSaison;
	private DaoMatche daoMatche;
	private Connexion c = Connexion.getConnexion();

	public ArbitresControlleur(VueAdminArbitres newVue) {
		this.vue = newVue;
		daoTournoi = new DaoTournoi(c);
		daoSaison = new DaoSaison(c);
		daoMatche = new DaoMatche(c);
		VueAdminArbitresListe vueAdminArbitresListe = new VueAdminArbitresListe();
		ArbitresListeControlleur arbitresListeControlleur = new ArbitresListeControlleur(vueAdminArbitresListe);
		vueAdminArbitresListe.setControleur(arbitresListeControlleur);

		VueAdminArbitresCreation vueAdminArbitresCreation = new VueAdminArbitresCreation();
		ArbitresCreationControlleur arbitresCreationControlleur = new ArbitresCreationControlleur(vueAdminArbitresCreation);
		vueAdminArbitresCreation.setControleur(arbitresCreationControlleur);

		vue.addPage(vueAdminArbitresCreation, Page.ARBITRES_CREATION);
		vue.addPage(vueAdminArbitresListe, Page.ARBITRES_LISTE);
		vue.setPage(Page.ARBITRES_LISTE);
		ArbitresObserver.getInstance().setVue(this);
	}

	public void update(Page id) {
		if (Page.ARBITRES_LISTE.equals(id)) {
			this.controlleur.update();
		}

		this.vue.setPage(id);
	}

	@Override
	public void update() {

	}
}
