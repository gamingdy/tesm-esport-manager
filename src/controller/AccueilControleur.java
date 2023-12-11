package controller;

import dao.*;
import modele.*;
import vue.admin.accueil.LigneTournoi;
import vue.admin.accueil.VueAccueil;

import javax.swing.DefaultListModel;
import java.util.ArrayList;
import java.util.List;

public class AccueilControleur {
	private VueAccueil vue;
	DefaultListModel<LigneTournoi> listeTournoi;
	private DaoTournoi daoTournoi;
	private DaoSaison daoSaison;
	private DaoMatche daoMatche;
	private Connexion c = Connexion.getConnexion();

	public AccueilControleur(VueAccueil newVue) throws Exception {
		vue = newVue;
		daoTournoi = new DaoTournoi(c);
		daoSaison = new DaoSaison(c);
		daoMatche = new DaoMatche(c);
		listeTournoi = new DefaultListModel<LigneTournoi>();
		List<Tournoi> liste = new ArrayList<>(daoTournoi.getAll());
		Tournoi tournoiActuel = daoTournoi.getTournoiActuel().get();
		LigneTournoi ligne = new LigneTournoi(tournoiActuel.getNom(), tournoiActuel.isEstEncours());
		listeTournoi.addElement(ligne);
		liste.remove(tournoiActuel);
		for (Tournoi tournoi : liste) {
			LigneTournoi ligne1 = new LigneTournoi(tournoi.getNom(), tournoi.isEstEncours());
			listeTournoi.addElement(ligne1);
		}
		vue.setListeTournois(listeTournoi);
	}
}
