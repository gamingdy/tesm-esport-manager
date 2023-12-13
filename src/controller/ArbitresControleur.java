package controller;

import dao.*;
import modele.*;
import vue.Vue;
import vue.admin.accueil.LigneTournoi;
import vue.admin.accueil.VueAccueil;
import vue.admin.arbitres.VueArbitres;

import javax.swing.DefaultListModel;
import java.util.ArrayList;
import java.util.List;
public class ArbitresControleur implements ControlleurObserver{
	private VueArbitres vue;
	private DaoTournoi daoTournoi;
	private DaoSaison daoSaison;
	private DaoMatche daoMatche;
	private Connexion c = Connexion.getConnexion();
	public ArbitresControleur(VueArbitres newVue){
		this.vue=newVue;
		daoTournoi = new DaoTournoi(c);
		daoSaison = new DaoSaison(c);
		daoMatche = new DaoMatche(c);

		this.update();
	}
	@Override
	public void update() {
		try {
			Saison saison=daoSaison
			Tournoi tournoi=new Tournoi();

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
