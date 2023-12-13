package controller;

import dao.Connexion;
import dao.DaoEquipe;
import dao.DaoJoueur;
import dao.DaoSaison;
import modele.Country;
import vue.admin.arbitres.VueArbitres;

public class EquipeControlleur {
	private VueArbitres vue;
	private DaoEquipe daoEquipe;
	private DaoSaison daoSaison;
	private DaoJoueur daoJoueur;
	private String champNomEquipe;
	private Country champPaysEquipe;
	private String codeImage;

	public EquipeControlleur(VueArbitres newVue) {
		this.vue = newVue;
		Connexion c = Connexion.getConnexion();
		daoEquipe = new DaoEquipe(c);
		daoSaison = new DaoSaison(c);
		daoJoueur = new DaoJoueur(c);
	}

	private boolean EquipeDejaExistante(String nomEquipe) {
		EquipedaoEquipe.getById(nomEquipe);
	}
}
