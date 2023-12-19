package controlleur.admin.accueil;

import controlleur.ControlleurObserver;
import dao.*;
import modele.Equipe;
import modele.Matche;
import modele.Tournoi;
import vue.admin.accueil.LigneEquipe;
import vue.admin.accueil.LigneMatche;
import vue.admin.accueil.LigneTournoi;
import vue.admin.accueil.VueAccueil;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class AccueilControlleur implements ControlleurObserver {
	private VueAccueil vue;
	private DefaultListModel<LigneTournoi> listeTournoi;
	private DefaultListModel<LigneEquipe> listeClassement;
	private DefaultListModel<LigneMatche> listeMatchesR;
	private DaoTournoi daoTournoi;
	private DaoEquipe daoEquipe;
	private DaoSaison daoSaison;
	private DaoMatche daoMatche;
	private Connexion c = Connexion.getConnexion();

	public AccueilControlleur(VueAccueil newVue) throws Exception {
		vue = newVue;
		daoTournoi = new DaoTournoi(c);
		daoSaison = new DaoSaison(c);
		daoMatche = new DaoMatche(c);
		daoEquipe = new DaoEquipe(c);
		this.update();
	}

	@Override
	public void update() {

		mettreAjourListeTournoi();
		mettreAjourListeClassement();
		mettreAjourListeMatches();
	}

	public void mettreAjourListeTournoi() {
		listeTournoi = new DefaultListModel<LigneTournoi>();
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mettreAjourListeClassement() {
		listeClassement = new DefaultListModel<LigneEquipe>();
		try {
			List<Equipe> liste = new ArrayList<>(daoEquipe.getAll());
			for (int i = 0; i < liste.size(); i++) {
				String nomEquipe = liste.get(i).getNom();
				System.out.println(liste.get(i));
				ImageIcon icone = new ImageIcon("assets/logo-equipes/" + nomEquipe + ".jpg");
				LigneEquipe ligneEquipe = new LigneEquipe(i+1, icone, nomEquipe, liste.get(i).getPoint());
				listeClassement.addElement(ligneEquipe);
			}
			vue.setListeEquipes(listeClassement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mettreAjourListeMatches() {
		listeMatchesR = new DefaultListModel<LigneMatche>();
		try {
			List<Matche> liste = new ArrayList<>(daoMatche.getAll());
			for (int i = 0; i < liste.size(); i++) {
				ImageIcon tropheeGagnant = new ImageIcon("assets/trophéeGagnant.png");
				ImageIcon tropheePerdant = new ImageIcon("assets/trophéePerdant.png");
				String nomEquipe1 = liste.get(i).getEquipe1().getNom();
				String nomEquipe2 = liste.get(i).getEquipe2().getNom();
				ImageIcon imageEquipe1 = new ImageIcon("assets/logo-equipes/" + nomEquipe1 + ".jpg");
				ImageIcon imageEquipe2 = new ImageIcon("assets/logo-equipes/" + nomEquipe2 + ".jpg");
				String dateHeure = liste.get(i).getDateDebutMatche().toString();
				LigneMatche ligneMatche = new LigneMatche(dateHeure, imageEquipe1, nomEquipe1, tropheeGagnant, imageEquipe2, nomEquipe2, tropheePerdant);
				listeMatchesR.addElement(ligneMatche);
			}
			vue.setListeMatches(listeMatchesR);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
