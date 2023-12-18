package controlleur.admin.tournois;

import controlleur.admin.equipes.EquipesObserver;
import dao.Connexion;
import dao.DaoEquipe;
import dao.DaoSaison;
import dao.DaoTournoi;
import exceptions.FausseDateException;
import modele.CompteArbitre;
import modele.CustomDate;
import modele.Niveau;
import modele.Saison;
import modele.Tournoi;
import vue.Page;
import vue.admin.equipes.creation.PopupPseudo;
import vue.admin.tournois.creation.PopupEquipe;
import vue.admin.tournois.creation.VueAdminTournoisCreation;
import vue.common.JFramePopup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.DateTimeException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TournoiCréationControlleur implements ActionListener, MouseListener {
	private VueAdminTournoisCreation vue;
	private DaoTournoi daoTournoi;
	private DaoSaison daoSaison;
	private DaoEquipe daoEquipe;
	private Saison saison;
	private Connexion c;
	private int nbEquipes = 0;
	private List<Equipe> listeEquipe;
	private PopupEquipe popupAjoutEquipe;

	public TournoiCréationControlleur(VueAdminTournoisCreation newVue) {
		this.vue = newVue;
		this.c = Connexion.getConnexion();
		daoTournoi = new DaoTournoi(c);
		daoSaison = new DaoSaison(c);
		daoEquipe = new DaoEquipe(c);
		try {
			saison = daoSaison.getLastSaison();
			listeEquipe = daoEquipe.getAll();
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
			if (nom.isEmpty()) {
				new JFramePopup("Erreur", "Le tournoi doit avoir un nom", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			} else if (Objects.equals(niveau, null)) {
				new JFramePopup("Erreur", "Veuillez choisir un niveau de tournoi", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			} else if (nbEquipes > 4) {
				new JFramePopup("Erreur", "Il faut au moins deux equipes", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			} else if (dateDebutString.isEmpty()) {
				new JFramePopup("Erreur", "Le tournoi doit avoir une date de debut", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			} else if (dateFinString.isEmpty()) {
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
					Tournoi tournoiInserer = new Tournoi(saison, nom, dateDebut, dateFin, niveau, new CompteArbitre(nom, niveau.getNom()));
					if (isTournoiMemeNomExistant(tournoiInserer)) {
						new JFramePopup("Erreur", "Le tournoi existe deja avec ce nom", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
					}
					if (isTournoiMemeDateExistant(tournoiInserer)) {
						new JFramePopup("Erreur", "Le tournoi existe à cette date", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
					}

					//CREATION TOURNOI

					daoTournoi.add(tournoiInserer);
				} catch (DateTimeException dateTimeException) {
					new JFramePopup("Erreur", "Veuillez entrer le bon format", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
				} catch (FausseDateException ex) {
					throw new RuntimeException(ex);
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}

			}

		} else if (e.getSource() == this.vue.getBoutonAnnuler()) {
			TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_LISTE);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this.vue.getBtnAjoutEquipes()) {
			this.popupAjoutEquipe = new PopupEquipe("Veuillez choisir le nom de l'equipe", listeEquipe, () -> {
				TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION);
				this.addEquipe();
			});
		}
	}


	public void initEquipes(Tournoi tournoi) {

	}

	public void addEquipe() {
		if (this.nbEquipes < 8) {
			String nomEquipe = this.popupAjoutEquipe.getSaisie().getNom();
			Icon icon = new ImageIcon("assets/logo-equipes/" + nomEquipe + ".jpg");
			this.vue.setEquipe(nomEquipe, icon, this.nbEquipes);
			this.nbEquipes++;
		}

	}

	public boolean isTournoiMemeNomExistant(Tournoi tournoi) throws Exception {
		Optional<Tournoi> tournoiRecherche;
		tournoiRecherche = daoTournoi.getById(tournoi.getNom(), saison.getAnnee());
		return tournoiRecherche.isPresent();

	}

	public boolean isTournoiMemeDateExistant(Tournoi tournoi) throws Exception {
		Optional<Tournoi> tournoiRecherche2;
		tournoiRecherche2 = daoTournoi.getTournoiBetweenDate(tournoi.getDebut(), tournoi.getFin());
		return tournoiRecherche2.isPresent();
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
}
