package controlleur.admin.tournois;

import controlleur.admin.arbitres.ArbitresObserver;
import dao.*;
import exceptions.FausseDateException;
import exceptions.MemeEquipeException;
import modele.*;
import vue.Page;
import vue.admin.tournois.creation.PopupArbitres;
import vue.admin.tournois.creation.PopupCompteArbitre;
import vue.admin.tournois.creation.PopupEquipe;
import vue.admin.tournois.creation.VueAdminTournoisCreation;
import vue.common.JFramePopup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class TournoiCréationControlleur implements ActionListener, MouseListener {
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
	private DaoMatche daoMatche;
	private PopupEquipe popupAjoutEquipe;
	private PopupCompteArbitre popupCompteArbitre;
	private PopupArbitres popupArbitres;
	private String motdePasse;

	public TournoiCréationControlleur(VueAdminTournoisCreation newVue) {
		this.vue = newVue;
		this.c = Connexion.getConnexion();
		daoTournoi = new DaoTournoi(c);
		daoSaison = new DaoSaison(c);
		daoEquipe = new DaoEquipe(c);
		daoPoule = new DaoPoule(c);
		daoInscription = new DaoInscription(c);
		daoAppartenance = new DaoAppartenance(c);
		daoInscription = new DaoInscription(c);
		daoArbitrage = new DaoArbitrage(c);
		daoArbitre = new DaoArbitre(c);
		daoMatche=new DaoMatche(c);
		motdePasse = "";
		arbitreListChoisi = new ArrayList<>();
		listeEquipeChoisi=new ArrayList<>();
		try {
			saison = daoSaison.getLastSaison();
			listeEquipe = daoInscription.getEquipeBySaison(saison.getAnnee());
			arbitreList = daoArbitre.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vue.getBoutonValider()) {
			//Recuperation des champs
			String nom = vue.getTextfieldNom().trim();
			String dateDebutString = vue.getTextfieldDateDebut().trim();
			String dateFinString = vue.getTextfieldDateFin().trim();
			Niveau niveau = vue.getNiveau();
			//Gestion des Champs vides
			if (nom.isEmpty()) {
				new JFramePopup("Erreur", "Le tournoi doit avoir un nom", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			} else if (Objects.equals(niveau, null)) {
				new JFramePopup("Erreur", "Veuillez choisir un niveau de tournoi", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			} else if (nbEquipes < 4) {
				new JFramePopup("Erreur", "Il faut au moins 4 equipes", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
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
					} else {
						popupCompteArbitre = new PopupCompteArbitre("Compte Arbitre", () ->
						{
							TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION);
							addMotDePasse();
							try {
								Tournoi tournoiInserer = new Tournoi(saison, nom, dateDebut, dateFin, niveau, new CompteArbitre(nom, motdePasse));
								tentativeAjoutTournoiBDD(tournoiInserer);
							}catch(FausseDateException fd){
								fd.printStackTrace();
							} 
						}, nom);
					}
				} catch (DateTimeException dateTimeException) {
					new JFramePopup("Erreur", "Le bon format est dd/mm/yyyy", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
				} catch (Exception ext) {
					ext.printStackTrace();
					throw new RuntimeException(ext);
				}

			}

		} else if (e.getSource() == this.vue.getBoutonAnnuler()) {
			TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_LISTE);
			resetChamps();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == this.vue.getBtnAjoutEquipes()) {
			if (listeEquipe.isEmpty()) {
				new JFramePopup("Erreur", "Il n'y a plus de equipes disponibles", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			} else {
				this.popupAjoutEquipe = new PopupEquipe("Veuillez choisir le nom de l'equipe", listeEquipe, () -> {
					TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION);
					this.addEquipe();
				});
			}
		} else if (e.getSource() == this.vue.getBoutonArbitres()) {
			if (arbitreList.isEmpty()) {
				new JFramePopup("Erreur", "Il n'y a plus de arbitres disponibles", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
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
			new JFramePopup("Erreur", "Arbitre est deja dans la liste", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
		}

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
		creationAutomatiqueMatches(listeEquipe,tournoi);


	}
	private void tentativeAjoutTournoiBDD(Tournoi tournoi){
		try{
		if (isTournoiMemeNomExistant(tournoi)) {
			new JFramePopup("Erreur", "Le tournoi existe deja avec ce nom", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
		} else if (isTournoiMemeDateExistant(tournoi)) {
			new JFramePopup("Erreur", "Le tournoi existe à cette date", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
		} else {
			daoTournoi.add(tournoi);
			initEquipes(tournoi,listeEquipeChoisi);
			if (!arbitreListChoisi.isEmpty()) {
				for (Arbitre arbitre : arbitreListChoisi) {
					Arbitrage arbitrage = new Arbitrage(arbitre, tournoi);
					daoArbitrage.add(arbitrage);
				}
			}
			new JFramePopup("Succès", "Tournoi est crée", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION));
			resetChamps();
		}}
		catch(Exception e){
			e.printStackTrace();
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
		}
		this.vue.clearField();
		this.nbEquipes = 0;
		motdePasse = "";

	}

	private void resetcomboBoxArbitres() {

	}

	public void addMotDePasse() {
		motdePasse = popupCompteArbitre.getSaisie().trim();
	}

	public void addEquipe() {
		if (this.nbEquipes < 8) {
			Equipe equipe = this.popupAjoutEquipe.getSaisie();
			String nomEquipe = equipe.getNom();
			ImageIcon icon = new ImageIcon("assets/logo-equipes/" + nomEquipe + ".jpg");
			List<String> lst_equipes = this.vue.getEquipes();
			if (!lst_equipes.contains(nomEquipe)) {
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
		return tournoiRecherche2.size() != 0;
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
	private void creationAutomatiqueMatches(List<Equipe> listeEquipe,Tournoi tournoi) {
		List<Matche> matcheList=new ArrayList<>();
		CustomDate dateDebut=tournoi.getDebut();
		CustomDate dateFin = tournoi.getFin();
		int nbDay = CustomDate.dureeEnJour(dateDebut,dateFin);
		if (nbDay > 1) {
			nbDay -=1;
		}
		System.out.println("Total equipe "+listeEquipe.size());
		int nbMatches=0;
		for(int i=0;i<listeEquipe.size();i++){
			nbMatches+=i;
		}
		int matchParJour=nbMatches/nbDay;
		int reste=nbMatches%nbDay;

		CustomDate dateActuel = dateDebut;
		int currentDay = 1;
		List<Matche> all_match = new ArrayList<>();
		List<Matche> matcheOfDay = new ArrayList<>();
		for (int i = 0; i < listeEquipe.size() - 1; i++) {
			for (int j = i + 1; j < listeEquipe.size(); j++) {
				Equipe equipe1 = listeEquipe.get(i);
				Equipe equipe2 = listeEquipe.get(j);
				try{
					Matche matche = new Matche(1,dateActuel,Categorie.POULE,equipe1,equipe2,tournoi);
					matcheOfDay.add(matche);
					if (matcheOfDay.size() >= matchParJour){
						if (currentDay == nbDay){
							if (matcheOfDay.size()== matchParJour+reste){
								all_match.addAll(matcheOfDay);
								matcheOfDay.clear();
								dateActuel.plusOne();
								currentDay += 1;
							}
						}else{
							all_match.addAll(matcheOfDay);
							matcheOfDay.clear();
							dateActuel.plusOne();
							currentDay += 1;
						}
					}
				}catch (FausseDateException| MemeEquipeException e){
					e.printStackTrace();
				}

			}

		}
		System.out.println("Nb days "+ nbDay);

		System.out.println("Nb match  "+nbMatches);
		System.out.println("Nb match day"+matchParJour);
		System.out.println("Reste " + reste);
		System.out.println("nb match " + all_match.size());

		for(Matche matche:all_match){
			try {
				daoMatche.add(matche);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
