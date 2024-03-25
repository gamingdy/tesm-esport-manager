package controlleur.admin.tournois;

import dao.Connexion;
import dao.DaoAppartenance;
import dao.DaoArbitrage;
import dao.DaoArbitre;
import dao.DaoEquipe;
import dao.DaoInscription;
import dao.DaoPoule;
import dao.DaoSaison;
import dao.DaoTournoi;
import exceptions.FausseDateException;
import modele.Appartenance;
import modele.Arbitrage;
import modele.Arbitre;
import modele.CompteArbitre;
import modele.CustomDate;
import modele.Equipe;
import modele.Niveau;
import modele.Poule;
import modele.Saison;
import modele.Tournoi;
import vue.Page;
import vue.admin.tournois.creation.PopupArbitres;
import vue.admin.tournois.creation.PopupCompteArbitre;
import vue.admin.tournois.creation.PopupEquipe;
import vue.admin.tournois.creation.VueAdminTournoisCreation;
import vue.common.Creator;
import vue.common.JFramePopup;

import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TournoiCreationControlleur implements ActionListener, MouseListener {
	private VueAdminTournoisCreation vue;
	private DaoTournoi daoTournoi;
	private DaoSaison daoSaison;
	private DaoPoule daoPoule;
	private DaoAppartenance daoAppartenance;
	private DaoArbitrage daoArbitrage;
	private DaoEquipe daoEquipe;
	private DaoInscription daoInscription;
	private Saison saison;
	private Connexion c;
	private int nbEquipes = 0;
	private List<Equipe> listeEquipe;
	private List<Equipe> listeEquipeChoisi;
	private List<Arbitre> arbitreList;
	private List<Arbitre> arbitreListChoisi;
	private DaoArbitre daoArbitre;
	private PopupEquipe popupAjoutEquipe;
	private PopupCompteArbitre popupCompteArbitre;
	private PopupArbitres popupArbitres;
	private String motdePasse;

	public TournoiCreationControlleur(VueAdminTournoisCreation newVue) {
		// Initialisation de la vue
		this.vue = newVue;

		// Initialisation de la connexion et des DAOs
		this.c = Connexion.getConnexion();
		daoTournoi = new DaoTournoi(c);
		daoSaison = new DaoSaison(c);
		daoEquipe = new DaoEquipe(c);
		daoPoule = new DaoPoule(c);
		daoInscription = new DaoInscription(c);
		daoAppartenance = new DaoAppartenance(c);
		daoArbitrage = new DaoArbitrage(c);
		daoArbitre = new DaoArbitre(c);

		// Initialisation du mot de passe de compte arbitre
		motdePasse = "";

		// Initialisation des listes d'arbitres et d'équipes choisies
		arbitreListChoisi = new ArrayList<>();
		listeEquipeChoisi = new ArrayList<>();

		// Récupération de la dernière saison, des équipes et des arbitres disponibles
		try {
			saison = daoSaison.getLastSaison();
			listeEquipe = daoInscription.getEquipeBySaison(saison.getAnnee());
			arbitreList = daoArbitre.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			afficherErreur("Erreur sql s'est produite, contactez l'administrateur");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vue.getBoutonValider()) {
			validerTournoi();
		} else if (e.getSource() == this.vue.getBoutonAnnuler()) {
			annulerCreationTournoi();
		}
	}

	private void validerTournoi() {
		//recuperation des champs de texte
		String nom = vue.getTextfieldNom().trim();
		String dateDebutString = vue.getTextfieldDateDebut().trim();
		String dateFinString = vue.getTextfieldDateFin().trim();
		Niveau niveau = vue.getNiveau();

		if (champsTournoiVides(nom, niveau, dateDebutString, dateFinString)) {
			return;
		}

		if (!isNombreEquipesValide()) {
			afficherErreur("Il faut au moins 4 équipes");
			return;
		}

		CustomDate dateDebut;
		CustomDate dateFin;

		try {
			dateDebut = CustomDate.fromString(dateDebutString);
			dateFin = CustomDate.fromString(dateFinString);
			if (!datesValides(dateDebut, dateFin)) {
				return;
			}

			if (!anneeSaisonValide(dateDebut, dateFin)) {
				return;
			}

			ouvrirPopupCompteArbitre(nom, dateDebut, dateFin, niveau);
		} catch (DateTimeException dateTimeException) {
			afficherErreur("Le bon format de date est dd/mm/yyyy");
		} catch (Exception ext) {
			ext.printStackTrace();
			afficherErreur("Erreur sql s'est produite, contactez l'administrateur");
		}
	}

	private void annulerCreationTournoi() {
		TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_LISTE);
		resetChamps();
	}

	private boolean champsTournoiVides(String nom, Niveau niveau, String dateDebutString, String dateFinString) {
		if (nom.isEmpty()) {
			afficherErreur("Le tournoi doit avoir un nom");
			return true;
		}
		if (Objects.equals(niveau, null)) {
			afficherErreur("Veuillez choisir un niveau de tournoi");
			return true;
		}
		if (dateDebutString.isEmpty()) {
			afficherErreur("Le tournoi doit avoir une date de début");
			return true;
		}
		if (dateFinString.isEmpty()) {
			afficherErreur("Le tournoi doit avoir une date de fin");
			return true;
		}
		if (!isAtLeastOneArbitre()) {
			afficherErreur("Le tournoi doit avoir au moins un arbitre");
			return true;
		}
		return false;
	}

	private boolean isNombreEquipesValide() {
		return nbEquipes >= 4;
	}

	private boolean datesValides(CustomDate dateDebut, CustomDate dateFin) {
		
		if (Boolean.FALSE.equals(dateDebut.estAvant(dateFin))) {
			afficherErreur("La date début doit être avant la date fin");
			return false;
		}
		return true;
	}

	private boolean anneeSaisonValide(CustomDate dateDebut, CustomDate dateFin) {
		if (saison.getAnnee() != dateDebut.getAnnee() || saison.getAnnee() != dateFin.getAnnee()) {
			afficherErreur("L'année doit être : " + saison.getAnnee());
			return false;
		}
		return true;
	}

	private void ouvrirPopupCompteArbitre(String nomTournoi, CustomDate dateDebut, CustomDate dateFin, Niveau niveau) {
		popupCompteArbitre = new PopupCompteArbitre("Compte Arbitre", () -> {
			TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION);
			addMotDePasse();
			try {
				Tournoi tournoiInserer = new Tournoi(saison, nomTournoi, dateDebut, dateFin, niveau, new CompteArbitre(nomTournoi, motdePasse));
				tentativeAjoutTournoiBDD(tournoiInserer);
			} catch (FausseDateException fd) {
				afficherErreur("La date du tournoi n'est pas valide");
			}
		}, nomTournoi);
	}

	private void afficherErreur(String message) {
		new JFramePopup("Erreur", message, () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
	}


	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == this.vue.getBtnAjoutEquipes()) {
			if (listeEquipe.isEmpty()) {
				afficherErreur("Il n'y a plus de equipes disponibles");
			} else {
				this.popupAjoutEquipe = new PopupEquipe("Veuillez choisir le nom de l'equipe", listeEquipe, () -> {
					TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION);
					this.addEquipe();
				});
			}
		} else if (e.getSource() == this.vue.getBoutonArbitres()) {
			if (arbitreList.isEmpty()) {
				afficherErreur("Il n'y a plus de arbitres disponibles");
			} else {
				this.popupArbitres = new PopupArbitres("Veuillez choisir au moins un Arbitre", arbitreList, () -> {
					TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION);
					this.addArbitre();
				});
			}
		}
	}

	public void addArbitre() {
		Arbitre arbitreChoisi = popupArbitres.getSaisie();
		if (!arbitreListChoisi.contains(arbitreChoisi)) {
			arbitreListChoisi.add(arbitreChoisi);
			this.vue.addArbitre(arbitreChoisi.getNom());
			this.arbitreList.remove(arbitreChoisi);
		} else {
			afficherErreur("Arbitre est deja dans la liste");
		}

	}

	public boolean isAtLeastOneArbitre() {
		return !this.arbitreListChoisi.isEmpty();
	}

	public void initEquipes(Tournoi tournoi, List<Equipe> listeEquipe) throws Exception {
		//creation de la poule
		Poule poule = new Poule(tournoi, 'A');
		daoPoule.add(poule);
		//ajout des equipes dans la poule
		for (Equipe e : listeEquipe) {
			Appartenance appartenance = new Appartenance(e, poule);
			daoAppartenance.add(appartenance);
		}
		Creator.creationAutomatiqueMatches(listeEquipe, tournoi);


	}

	private void tentativeAjoutTournoiBDD(Tournoi tournoi) {
		try {
			if (isTournoiMemeNomExistant(tournoi)) {
				afficherErreur("Le tournoi existe deja avec ce nom");
			} else if (isTournoiMemeDateExistant(tournoi)) {
				afficherErreur("Le tournoi existe à cette date");
			} else {
				daoTournoi.add(tournoi);
				initEquipes(tournoi, listeEquipeChoisi);
				if (!arbitreListChoisi.isEmpty()) {
					for (Arbitre arbitre : arbitreListChoisi) {
						Arbitrage arbitrage = new Arbitrage(arbitre, tournoi);
						daoArbitrage.add(arbitrage);
					}
				}
				new JFramePopup("Succès", "Tournoi est crée", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
				resetChamps();
			}
		} catch (Exception e) {
			e.printStackTrace();
			afficherErreur("Erreur sql s'est produite, contactez l'administrateur");
		}
	}

	public void resetChamps() {
		this.arbitreListChoisi.clear();
		try {
			this.listeEquipe = daoEquipe.getAll();
			this.listeEquipeChoisi.clear();
			this.arbitreList = daoArbitre.getAll();
			this.vue.getBtnAjoutArbitres().setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
			afficherErreur("Erreur sql s'est produite, contactez l'administrateur");
		}
		this.vue.clearField();
		this.nbEquipes = 0;
		motdePasse = "";

	}

	public void addMotDePasse() {
		motdePasse = popupCompteArbitre.getSaisie().trim();
	}

	public void addEquipe() {
		if (this.nbEquipes < 8) {
			Equipe equipe = this.popupAjoutEquipe.getSaisie();
			String nomEquipe = equipe.getNom();
			ImageIcon icon = new ImageIcon("assets/logo-equipes/" + nomEquipe + ".jpg");
			List<String> listEquipes = this.vue.getEquipes();
			if (!listEquipes.contains(nomEquipe)) {
				this.vue.setEquipe(nomEquipe, icon, this.nbEquipes);
				this.listeEquipeChoisi.add(equipe);
				this.nbEquipes++;

			}
			this.listeEquipe.remove(equipe);
		}

	}

	public boolean isTournoiMemeNomExistant(Tournoi tournoi) throws Exception {
		Optional<Tournoi> tournoiRecherche;
		tournoiRecherche = daoTournoi.getById(saison.getAnnee(), tournoi.getNom());
		return tournoiRecherche.isPresent();

	}

	public boolean isTournoiMemeDateExistant(Tournoi tournoi) throws Exception {
		List<Tournoi> tournoiRecherche2;
		tournoiRecherche2 = daoTournoi.getTournoiBetweenDate(tournoi.getDebut(), tournoi.getFin());
		return !tournoiRecherche2.isEmpty();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// default implementation ignored
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// default implementation ignored
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// default implementation ignored
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// default implementation ignored
	}
}
