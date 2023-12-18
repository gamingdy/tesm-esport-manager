package controlleur.admin.tournois;

import controlleur.VueObserver;
import dao.Connexion;
import dao.Dao;
import dao.DaoSaison;
import dao.DaoTournoi;
import modele.CustomDate;
import modele.Saison;
import modele.Tournoi;
import vue.Page;
import vue.admin.tournois.creation.VueAdminTournoisCreation;
import vue.admin.tournois.liste.VueAdminTournoisListe;
import vue.common.JFramePopup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;

public class TournoiCréationControlleur implements ActionListener {
	private VueAdminTournoisCreation vue;
	private DaoTournoi daoTournoi;
	private DaoSaison daoSaison;
	private Saison saison;
	private Connexion c;

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
			String[] tabloEquipes = vue.getEquipes();
			//Gestion des Champs vides
			if (tabloEquipes == null) {
				new JFramePopup("Erreur", "Il faut au moins deux equipes", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			} else if (nom == "") {
				new JFramePopup("Erreur", "Le tournoi doit avoir un nom", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			} else if (dateDebutString == "") {
				new JFramePopup("Erreur", "Le tournoi doit avoir une date de debut", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			} else if (dateFinString == "") {
				new JFramePopup("Erreur", "Le tournoi doit avoir une date de fin", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			}
			//Gestion des dates entrées
			CustomDate dateDebut;
			CustomDate dateFin;
			try {
				dateDebut = CustomDate.fromString(dateDebutString);
				dateFin = CustomDate.fromString(dateFinString);
				if (!dateDebut.estAvant(dateFin)) {
					new JFramePopup("Erreur", "La date debut doit etre avant la date fin", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
				} else if (saison.getAnnee() != dateDebut.getAnnee() || saison.getAnnee() != dateFin.getAnnee()) {
					new JFramePopup("Erreur", "Les dates doivent correspondre a la saison actuelle : " + saison.getAnnee(), () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
				}
			} catch (DateTimeException dateTimeException) {
				new JFramePopup("Erreur", "Veuillez entrer le bon format", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			}
			//Gestion de verification si le tournoi n'existe pas deja aves ses dates
			//Tournoi tournoiInserer=new Tournoi(saison,nom,dateDebut,dateFin,)
			Tournoi tournoiRecherche;
			try {
				//Recherche par nom
				tournoiRecherche = daoTournoi.getById(saison.getAnnee(), nom).get();
				if (tournoiRecherche != null) {
					new JFramePopup("Erreur", "Le tournoi existe deja avec ce nom", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
				}
				//Recherche par date
				//tournoiRecherche=daoTournoi.
			} catch (Exception exceptionTournoi) {

			}
			;


		}
	}
}
