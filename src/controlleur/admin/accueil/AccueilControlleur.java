package controlleur.admin.accueil;

import controlleur.ControlleurObserver;
import controlleur.VueObserver;
import dao.Connexion;
import dao.DaoMatche;
import dao.DaoPartie;
import dao.DaoSaison;
import dao.DaoTournoi;
import exceptions.FausseDateException;
import modele.CustomDate;
import modele.Equipe;
import modele.Matche;
import modele.ModeleTournoi;
import modele.Partie;
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
import java.util.Set;

public class AccueilControlleur implements ControlleurObserver, ActionListener {
	private VueAccueil vue;
	private List<Equipe> equipes;
	private DaoTournoi daoTournoi;
	private DaoSaison daoSaison;
	private DaoMatche daoMatche;
	private DaoPartie daoPartie;
	private Connexion c = Connexion.getConnexion();

	public AccueilControlleur(VueAccueil newVue) {
		vue = newVue;
		daoTournoi = new DaoTournoi(c);
		daoSaison = new DaoSaison(c);
		daoMatche = new DaoMatche(c);
		daoPartie = new DaoPartie(c);
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
		DefaultListModel<LigneTournoi> listeTournoi = new DefaultListModel<>();
		try {
			Saison saisonActuelle = daoSaison.getLastSaison();
			CustomDate debutSaison = new CustomDate(saisonActuelle.getAnnee(), 01, 01);
			List<Tournoi> liste = new ArrayList<>(daoTournoi.getTournoiBetweenDate(debutSaison, CustomDate.now()));
			try {
				Optional<Tournoi> tournoiOptional=daoTournoi.getTournoiActuel();
				if(tournoiOptional.isPresent()){
					Tournoi tournoiActuel = tournoiOptional.get();
					LigneTournoi ligne = new LigneTournoi(tournoiActuel.getNom(), tournoiActuel.isEstEncours());
					listeTournoi.addElement(ligne);
					liste.remove(tournoiActuel);
				}


			} catch (Exception e) {
				afficherErreur("Erreur sql s'est produite lors de la mise a jour");
			}

			for (Tournoi tournoi : liste) {
				LigneTournoi ligne1 = new LigneTournoi(tournoi.getNom(), tournoi.isEstEncours());
				listeTournoi.addElement(ligne1);
			}
			vue.setListeTournois(listeTournoi);
		} catch (Exception e) {
			afficherErreur("Erreur sql s'est produite");
			}
	}

	public void mettreAjourListeClassement() {
		DefaultListModel<LigneEquipe> listeClassement = new DefaultListModel<>();
		try {
			Optional<Tournoi> tournoiActuel = daoTournoi.getTournoiActuel();

			if (tournoiActuel.isPresent()) {
				Set<Equipe> liste = ModeleTournoi.getClassement(tournoiActuel.get());
				int i = 0;
				for (Equipe e : liste) {
					String nomEquipe = e.getNom();
					ImageIcon icone = new ImageIcon("assets/logo-equipes/" + nomEquipe + ".jpg");
					LigneEquipe ligneEquipe = new LigneEquipe(i + 1, icone, nomEquipe, e.getPoint());
					listeClassement.addElement(ligneEquipe);
					equipes.add(e);
					i++;
				}
				vue.setListeEquipes(listeClassement);
			}
		} catch (Exception e) {
			afficherErreur("Erreur sql s'est produite");
		}
	}

	public void mettreAjourListeMatches() {
		DefaultListModel<LigneMatche> listeMatchesR = new DefaultListModel<>();
		try {
			List<Matche> liste = new ArrayList<>(daoMatche.getTenLastMatch());
			for (Matche m : liste) {
				List<Partie> partieList = daoPartie.getPartieByMatche(m);
				m.setVainqueur(partieList.get(0).getVainqueur());
				ImageIcon tropheeGagnant = new ImageIcon("assets/trophéeGagnant.png");
				ImageIcon tropheePerdant = new ImageIcon("assets/trophéePerdant.png");
				String nomEquipe1 = m.getEquipe1().getNom();
				String nomEquipe2 = m.getEquipe2().getNom();
				ImageIcon imageEquipe1 = new ImageIcon("assets/logo-equipes/" + nomEquipe1 + ".jpg");
				ImageIcon imageEquipe2 = new ImageIcon("assets/logo-equipes/" + nomEquipe2 + ".jpg");
				String dateHeure = m.getDateDebutMatche().toString().substring(6);
				ImageIcon trophee1;
				ImageIcon trophee2;

				if (m.getVainqueur() == null) {
					trophee1 = tropheePerdant;
					trophee2 = tropheePerdant;
				} else if (m.getVainqueur().equals(m.getEquipe1())) {
					trophee1 = tropheeGagnant;
					trophee2 = tropheePerdant;
				} else {
					trophee1 = tropheePerdant;
					trophee2 = tropheeGagnant;
				}
				LigneMatche ligneMatche = new LigneMatche(dateHeure, imageEquipe1, nomEquipe1, trophee1, imageEquipe2, nomEquipe2, trophee2);
				listeMatchesR.addElement(ligneMatche);
			}
			vue.setListeMatches(listeMatchesR);
		} catch (Exception e) {
			afficherErreur("Erreur sql s'est produite");
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
					afficherErreur("Il n'y a pas de tournoi actuellement");
					}
			} catch (SQLException | FausseDateException ex) {
				afficherErreur("Erreur sql s'est produite");
				}

		}
	}

	private void impression(List<Equipe> equipes, String nomTournoi) {
		PrinterJob job = PrinterJob.getPrinterJob();
		List<Float> point = new ArrayList<>();
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
				afficherErreur("Erreur d'impression s'est produite");
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
	private void afficherErreur(String message) {
		new JFramePopup("Erreur Accueil", message, () -> VueObserver.getInstance().notifyVue(Page.ACCUEIL_ADMIN));
	}
}
