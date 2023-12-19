package controlleur.admin.arbitres;

import controlleur.ControlleurObserver;
import dao.Connexion;
import dao.DaoMatche;
import dao.DaoSaison;
import dao.DaoTournoi;
import vue.Page;
import vue.admin.arbitres.VueAdminArbitres;

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

		this.update();
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
