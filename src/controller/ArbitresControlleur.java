package controller;

import dao.*;
import vue.admin.arbitres.VueArbitres;

public class ArbitresControlleur implements ControlleurObserver {
	private VueArbitres vue;
	private DaoTournoi daoTournoi;
	private DaoSaison daoSaison;
	private DaoMatche daoMatche;
	private Connexion c = Connexion.getConnexion();

	public ArbitresControlleur(VueArbitres newVue) {
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
