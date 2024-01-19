package controlleur.admin.accueil;

import controlleur.ControlleurObserver;
import controlleur.VueObserver;
import dao.*;
import exceptions.FausseDateException;
import modele.CustomDate;
import modele.Equipe;
import modele.Matche;
import modele.Saison;
import modele.Tournoi;
import vue.Impression;
import vue.Page;
import vue.admin.accueil.LigneEquipe;
import vue.admin.accueil.LigneMatche;
import vue.admin.accueil.LigneTournoi;
import vue.admin.accueil.VueAccueil;
import vue.common.JFramePopup;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccueilControlleur implements ControlleurObserver, ActionListener {
	private VueAccueil vue;
	private DefaultListModel<LigneTournoi> listeTournoi;
	private List<Equipe> equipes;
	private DefaultListModel<LigneEquipe> listeClassement;
	private DefaultListModel<LigneMatche> listeMatchesR;
	private DaoTournoi daoTournoi;
	private DaoEquipe daoEquipe;
	private DaoAppartenance daoAppartenance;
	private DaoSaison daoSaison;
	private DaoMatche daoMatche;
	private Connexion c = Connexion.getConnexion();

	public AccueilControlleur(VueAccueil newVue) {
		vue = newVue;
		daoTournoi = new DaoTournoi(c);
		daoSaison = new DaoSaison(c);
		daoMatche = new DaoMatche(c);
		daoEquipe = new DaoEquipe(c);
		daoAppartenance=new DaoAppartenance(c);
		equipes = new ArrayList<>();
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
			Saison saisonActuelle = daoSaison.getLastSaison();
			CustomDate debutSaison = new CustomDate(saisonActuelle.getAnnee(), 01, 01);
			List<Tournoi> liste = new ArrayList<>(daoTournoi.getTournoiBetweenDate(debutSaison, CustomDate.now()));
			try {
				Tournoi tournoiActuel = daoTournoi.getTournoiActuel().get();
				LigneTournoi ligne = new LigneTournoi(tournoiActuel.getNom(), tournoiActuel.isEstEncours());
				listeTournoi.addElement(ligne);
				liste.remove(tournoiActuel);
			} catch (Exception e) {

			}

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
			Optional<Tournoi> tournoiActuel=daoTournoi.getTournoiActuel();
			if(tournoiActuel.isPresent()) {
				List<Equipe> liste = new ArrayList<>(daoAppartenance.getEquipeByTournoi(tournoiActuel.get()));
				for (int i = 0; i < liste.size(); i++) {
					String nomEquipe = liste.get(i).getNom();
					ImageIcon icone = new ImageIcon("assets/logo-equipes/" + nomEquipe + ".jpg");
					LigneEquipe ligneEquipe = new LigneEquipe(i + 1, icone, nomEquipe, liste.get(i).getPoint());
					listeClassement.addElement(ligneEquipe);
					equipes.add(liste.get(i));
				}
				vue.setListeEquipes(listeClassement);
			}
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
				String dateHeure = liste.get(i).getDateDebutMatche().toString().substring(6);
				LigneMatche ligneMatche = new LigneMatche(dateHeure, imageEquipe1, nomEquipe1, tropheeGagnant, imageEquipe2, nomEquipe2, tropheePerdant);
				listeMatchesR.addElement(ligneMatche);
			}
			vue.setListeMatches(listeMatchesR);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vue.getBoutonImprimer()) {
			try {
				Optional<Tournoi> tournoi = daoTournoi.getTournoiActuel();
				if (tournoi.isPresent()) {
					String nom = tournoi.get().getNom();
					impression(equipes, nom);
				} else {
					new JFramePopup("Erreur", "Il n'y a pas de tournoi actuellement", () -> VueObserver.getInstance().notifyVue(Page.ACCUEIL_ADMIN));
				}
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			} catch (FausseDateException ex) {
				throw new RuntimeException(ex);
			}

		}
	}

	private void impression(List<Equipe> equipes, String nomTournoi) {
		PrinterJob job = PrinterJob.getPrinterJob();
		List<Integer> point = new ArrayList<>();
		bubbleSort(equipes);
		for (Equipe e : equipes) {
			point.add(e.getPoint());
		}
		// Définit son contenu à imprimer
		job.setPrintable(new Impression(equipes, point, nomTournoi, CustomDate.now().toString()));
		// Affiche une boîte de choix d'imprimante
		if (job.printDialog()) {
			try {
				// Effectue l'impression
				job.print();
			} catch (PrinterException ex) {
				ex.printStackTrace();
			}
		}
	}

	// Bubble sort algorithm
	private void bubbleSort(List<Equipe> equipes) {
		Equipe temp;
		int n = equipes.size();
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (equipes.get(j).getPoint() > equipes.get(j + 1).getPoint()) {
					// Swap elements
					temp = equipes.get(j);
					equipes.set(j, equipes.get(j + 1));
					equipes.set(j + 1, temp);
				}
			}
		}
	}
}
