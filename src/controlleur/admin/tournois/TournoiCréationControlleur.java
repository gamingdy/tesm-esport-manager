package controlleur.admin.tournois;

import dao.Connexion;
import dao.DaoSaison;
import dao.DaoTournoi;
import exceptions.FausseDateException;
import modele.*;
import vue.Page;
import vue.admin.tournois.creation.VueAdminTournoisCreation;
import vue.common.JFramePopup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.DateTimeException;
import java.util.List;
import java.util.Objects;

public class TournoiCréationControlleur implements ActionListener, MouseListener {
	private VueAdminTournoisCreation vue;
	private DaoTournoi daoTournoi;
	private DaoSaison daoSaison;
	private Saison saison;
	private Connexion c;
	private int nbEquipes;

	public TournoiCréationControlleur(VueAdminTournoisCreation newVue) {
		this.vue = newVue;
		this.c = Connexion.getConnexion();
		daoTournoi = new DaoTournoi(c);
		daoSaison = new DaoSaison(c);
		try {
			saison = daoSaison.getLastSaison();
		} catch (Exception e) {

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vue.getBoutonValider()) {
			//Recuperation des champs
			String nom = vue.getTextfieldNom();
			String dateDebutString = vue.getTextfieldDateDebut();
			String dateFinString = vue.getTextfieldDateFin();
			List<String> tabloEquipes = vue.getEquipes();
			Niveau niveau = vue.getNiveau();

			//Gestion des Champs vides
			if (Objects.equals(nom, "")) {
				new JFramePopup("Erreur", "Le tournoi doit avoir un nom", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			} else if (Objects.equals(niveau, null)) {
				new JFramePopup("Erreur", "Veuillez choisir un niveau de tournoi", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			} else if (tabloEquipes == null) {
				new JFramePopup("Erreur", "Il faut au moins deux equipes", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			} else if (Objects.equals(dateDebutString, "")) {
				new JFramePopup("Erreur", "Le tournoi doit avoir une date de debut", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			} else if (Objects.equals(dateFinString, "")) {
				new JFramePopup("Erreur", "Le tournoi doit avoir une date de fin", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			} else {
				//Gestion des dates entrées
				CustomDate dateDebut;
				CustomDate dateFin;
				try {
					dateDebut = CustomDate.fromString(dateDebutString);
					dateFin = CustomDate.fromString(dateFinString);
					if (!dateDebut.estAvant(dateFin)) {
						new JFramePopup("Erreur", "La date debut doit etre avant la date fin", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
					} else if (saison.getAnnee() != dateDebut.getAnnee() || saison.getAnnee() != dateFin.getAnnee()) {
						new JFramePopup("Erreur", "L'année doit etre : " + saison.getAnnee(), () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
					}
					//TEST POUR TOURNOI DEJA EXISTANT
					Tournoi tournoiRecherche;
					try {
						//Recherche par nom
						tournoiRecherche = daoTournoi.getById(saison.getAnnee(), nom).get();
						if (tournoiRecherche != null) {
							new JFramePopup("Erreur", "Le tournoi existe deja avec ce nom", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
						}
						//Recherche par date
						//Gestion de verification si le tournoi n'existe pas deja aves ses dates
						tournoiRecherche = daoTournoi.getTournoiBetweenDate(dateDebut, dateFin).get();
						if (tournoiRecherche != null) {
							new JFramePopup("Erreur", "Il y a deja un tournoi pendant ses dates", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
						}
					} catch (Exception exceptionTournoi) {

					}
					//CREATION TOURNOI
					Tournoi tournoiInserer = new Tournoi(saison, nom, dateDebut, dateFin, niveau, new CompteArbitre(nom, niveau.getNom()));
					daoTournoi.add(tournoiInserer);
				} catch (DateTimeException dateTimeException) {
					new JFramePopup("Erreur", "Veuillez entrer le bon format", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
				} catch (FausseDateException ex) {
					throw new RuntimeException(ex);
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}

			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this.vue.getBtnAjoutEquipes()) {

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public void initEquipes(Tournoi tournoi) {

	}

	public void ajoutEquipe() {
		nbEquipes++;
	}
}
