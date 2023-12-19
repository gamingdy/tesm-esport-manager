package controlleur.admin.arbitres;

import controlleur.ControlleurObserver;
import dao.Connexion;
import dao.DaoMatche;
import dao.DaoSaison;
import dao.DaoTournoi;
import vue.admin.arbitres.VueAdminArbitres;

public class ArbitresControlleur implements ControlleurObserver {
	private VueAdminArbitres vue;
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

	@Override
	public void update() {


	}
}