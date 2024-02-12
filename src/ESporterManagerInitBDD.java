import dao.Connexion;
import dao.DBGeneration;
import dao.DBSuppression;
import dao.DaoAppartenance;
import dao.DaoArbitre;
import dao.DaoEquipe;
import dao.DaoInscription;
import dao.DaoJoueur;
import dao.DaoMatche;
import dao.DaoNiveau;
import dao.DaoPartie;
import dao.DaoPoule;
import dao.DaoSaison;
import dao.DaoTournoi;
import modele.Appartenance;
import modele.Arbitre;
import modele.Categorie;
import modele.CompteArbitre;
import modele.CustomDate;
import modele.Equipe;
import modele.Inscription;
import modele.Joueur;
import modele.Matche;
import modele.Niveau;
import modele.Partie;
import modele.Pays;
import modele.Poule;
import modele.Saison;
import modele.Tournoi;
import vue.common.Creator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ESporterManagerInitBDD {

	public static void main(String[] args) throws Exception {

		//Connexion à la base de données
		Connexion c = Connexion.getConnexion();
		try {
			DBSuppression.main(c);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		try {
			DBGeneration.main(c);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		DaoInscription daoInscription = new DaoInscription(c);
		DaoTournoi daoTournoi = new DaoTournoi(c);
		DaoNiveau daoNiveau = new DaoNiveau(c);
		DaoPoule daoPoule = new DaoPoule(c);
		DaoEquipe daoEquipe = new DaoEquipe(c);
		DaoAppartenance daoAppartenance = new DaoAppartenance(c);
		DaoMatche daoMatche = new DaoMatche(c);
		DaoPartie daoPartie = new DaoPartie(c);
		DaoSaison daoSaison = new DaoSaison(c);
		DaoArbitre daoArbitre = new DaoArbitre(c);
		try {
			daoNiveau.add(Niveau.LOCAL);
			daoNiveau.add(Niveau.INTERNATIONAL);
			daoNiveau.add(Niveau.REGIONAL);
			daoNiveau.add(Niveau.INTERNATIONAL_CLASSE);
			daoNiveau.add(Niveau.NATIONAL);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		//Ajout saisons
		Saison saison = new Saison(2024);
		Saison saison2 = new Saison(2023);
		try {
			daoSaison.add(saison);
			daoSaison.add(saison2);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		//Ajout tournois
		CustomDate debutTournoi = new CustomDate(2024, 12, 1);
		CustomDate fin = new CustomDate(2024, 12, 30);
		Tournoi tournoi = new Tournoi(saison, "RLCS", debutTournoi, fin, Niveau.LOCAL, new CompteArbitre("arbitre", "rlcs"));

		CustomDate debutTournoi2 = new CustomDate(2024, 1, 9);
		CustomDate fin2 = new CustomDate(2024, 1, 30);
		Tournoi tournoi2 = new Tournoi(saison, "RLRS", debutTournoi2, fin2, Niveau.LOCAL, new CompteArbitre("arbitre", "rlrs"));

		CustomDate debutTournoi3 = new CustomDate(2023, 2, 1);
		CustomDate fin3 = new CustomDate(2023, 2, 28);
		Tournoi tournoi3 = new Tournoi(saison2, "Worlds", debutTournoi3, fin3, Niveau.LOCAL, new CompteArbitre("arbitre", "worlds"));

		CustomDate debutTournoi4 = new CustomDate(2023, 3, 9);
		CustomDate fin4 = new CustomDate(2023, 3, 30);
		Tournoi tournoi4 = new Tournoi(saison2, "MeilleurTournoi", debutTournoi4, fin4, Niveau.LOCAL, new CompteArbitre("arbitre", "meilleurtournoi"));

		try {
			daoTournoi.add(tournoi);
			daoTournoi.add(tournoi2);
			daoTournoi.add(tournoi3);
			daoTournoi.add(tournoi4);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		//Ajout équipes
		Equipe equipe = new Equipe("terros", Pays.FRANCE);
		Equipe equipe1 = new Equipe("lion-rouge", Pays.FRANCE);
		Equipe equipe2 = new Equipe("shark", Pays.FRANCE);
		Equipe equipe3 = new Equipe("goule", Pays.FRANCE);
		Equipe equipe4 = new Equipe("dragon", Pays.FRANCE);
		Equipe equipe5 = new Equipe("aigle", Pays.FRANCE);
		Equipe equipe6 = new Equipe("bear", Pays.FRANCE);
		Equipe equipe7 = new Equipe("chevarcher", Pays.FRANCE);
		Equipe equipe8 = new Equipe("loup", Pays.FRANCE);
		Equipe equipe9 = new Equipe("mort", Pays.FRANCE);
		try {
			daoEquipe.add(equipe);
			daoEquipe.add(equipe1);
			daoEquipe.add(equipe2);
			daoEquipe.add(equipe3);
			daoEquipe.add(equipe4);
			daoEquipe.add(equipe5);
			daoEquipe.add(equipe6);
			daoEquipe.add(equipe7);
			daoEquipe.add(equipe8);
			daoEquipe.add(equipe9);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		//Ajout joueurs dans équipes
		initEquipe(equipe);
		initEquipe(equipe1);
		initEquipe(equipe2);
		initEquipe(equipe3);
		initEquipe(equipe4);
		initEquipe(equipe5);
		initEquipe(equipe6);
		initEquipe(equipe7);
		initEquipe(equipe8);
		initEquipe(equipe9);

		//Ajout équipes dans saisons
		Inscription inscription = new Inscription(saison, equipe);
		Inscription inscription1 = new Inscription(saison, equipe1);
		Inscription inscription2 = new Inscription(saison, equipe2);
		Inscription inscription3 = new Inscription(saison, equipe3);
		Inscription inscription4 = new Inscription(saison, equipe4);
		Inscription inscription5 = new Inscription(saison, equipe5);
		Inscription inscription6 = new Inscription(saison, equipe6);
		Inscription inscription7 = new Inscription(saison, equipe7);
		Inscription inscription8 = new Inscription(saison, equipe8);
		Inscription inscription9 = new Inscription(saison, equipe9);
		Inscription inscription10 = new Inscription(saison2, equipe);
		Inscription inscription11 = new Inscription(saison2, equipe1);
		Inscription inscription12 = new Inscription(saison2, equipe2);
		Inscription inscription13 = new Inscription(saison2, equipe3);
		Inscription inscription14 = new Inscription(saison2, equipe4);
		Inscription inscription15 = new Inscription(saison2, equipe5);
		try {
			daoInscription.add(inscription);
			daoInscription.add(inscription1);
			daoInscription.add(inscription2);
			daoInscription.add(inscription3);
			daoInscription.add(inscription4);
			daoInscription.add(inscription5);
			daoInscription.add(inscription6);
			daoInscription.add(inscription7);
			daoInscription.add(inscription8);
			daoInscription.add(inscription9);
			daoInscription.add(inscription10);
			daoInscription.add(inscription11);
			daoInscription.add(inscription12);
			daoInscription.add(inscription13);
			daoInscription.add(inscription14);
			daoInscription.add(inscription15);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		//Ajout poules
		Poule poule = new Poule(tournoi, 'A');
		Poule poule1 = new Poule(tournoi2, 'B');
		Poule poule2 = new Poule(tournoi3, 'C');
		Poule poule3 = new Poule(tournoi4, 'D');
		try {
			daoPoule.add(poule);
			daoPoule.add(poule1);
			daoPoule.add(poule2);
			daoPoule.add(poule3);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		//Ajout équipes dans poules
		Appartenance appartenance = new Appartenance(equipe, poule);
		Appartenance appartenance1 = new Appartenance(equipe1, poule);
		Appartenance appartenance2 = new Appartenance(equipe2, poule);
		Appartenance appartenance3 = new Appartenance(equipe3, poule);
		Appartenance appartenance4 = new Appartenance(equipe4, poule);
		Appartenance appartenance5 = new Appartenance(equipe5, poule);
		Appartenance appartenance6 = new Appartenance(equipe6, poule);
		Appartenance appartenance7 = new Appartenance(equipe7, poule);
		Appartenance appartenance8 = new Appartenance(equipe8, poule);
		Appartenance appartenance9 = new Appartenance(equipe9, poule);
		Appartenance appartenance10 = new Appartenance(equipe, poule1);
		Appartenance appartenance11 = new Appartenance(equipe1, poule1);
		Appartenance appartenance12 = new Appartenance(equipe2, poule1);
		Appartenance appartenance13 = new Appartenance(equipe3, poule1);
		Appartenance appartenance14 = new Appartenance(equipe4, poule1);
		Appartenance appartenance15 = new Appartenance(equipe5, poule1);
		Appartenance appartenance16 = new Appartenance(equipe1, poule2);
		Appartenance appartenance17 = new Appartenance(equipe2, poule2);
		Appartenance appartenance18 = new Appartenance(equipe3, poule2);
		Appartenance appartenance19 = new Appartenance(equipe4, poule2);
		Appartenance appartenance20 = new Appartenance(equipe, poule3);
		Appartenance appartenance21 = new Appartenance(equipe1, poule3);
		Appartenance appartenance22 = new Appartenance(equipe2, poule3);
		Appartenance appartenance23 = new Appartenance(equipe3, poule3);
		Appartenance appartenance24 = new Appartenance(equipe4, poule3);
		Appartenance appartenance25 = new Appartenance(equipe5, poule3);
		try {
			daoAppartenance.add(appartenance);
			daoAppartenance.add(appartenance1);
			daoAppartenance.add(appartenance2);
			daoAppartenance.add(appartenance3);
			daoAppartenance.add(appartenance4);
			daoAppartenance.add(appartenance5);
			daoAppartenance.add(appartenance6);
			daoAppartenance.add(appartenance7);
			daoAppartenance.add(appartenance8);
			daoAppartenance.add(appartenance9);
			daoAppartenance.add(appartenance10);
			daoAppartenance.add(appartenance11);
			daoAppartenance.add(appartenance12);
			daoAppartenance.add(appartenance13);
			daoAppartenance.add(appartenance14);
			daoAppartenance.add(appartenance15);
			daoAppartenance.add(appartenance16);
			daoAppartenance.add(appartenance17);
			daoAppartenance.add(appartenance18);
			daoAppartenance.add(appartenance19);
			daoAppartenance.add(appartenance20);
			daoAppartenance.add(appartenance21);
			daoAppartenance.add(appartenance22);
			daoAppartenance.add(appartenance23);
			daoAppartenance.add(appartenance24);
			daoAppartenance.add(appartenance25);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		//Création Liste des équipes de chaque tournoi pour la création automatique des matches

		List<Equipe> equipeListTournoi3 = new ArrayList<>();
		equipeListTournoi3.add(equipe);
		equipeListTournoi3.add(equipe1);
		equipeListTournoi3.add(equipe2);
		equipeListTournoi3.add(equipe3);
		equipeListTournoi3.add(equipe4);

		List<Equipe> equipeListTournoi2 = equipeListTournoi3;
		equipeListTournoi2.add(equipe5);

		List<Equipe> equipeListTournoi4 = equipeListTournoi2;

		List<Equipe> equipeListTournoi = equipeListTournoi2;
		equipeListTournoi.add(equipe6);
		equipeListTournoi.add(equipe7);
		equipeListTournoi.add(equipe8);
		equipeListTournoi.add(equipe9);

		//Création automatique des matches
		Creator.creationAutomatiqueMatches(equipeListTournoi, tournoi);
		Creator.creationAutomatiqueMatches(equipeListTournoi2, tournoi2);
		Creator.creationAutomatiqueMatches(equipeListTournoi3, tournoi3);
		Creator.creationAutomatiqueMatches(equipeListTournoi4, tournoi4);

		CustomDate debut = new CustomDate(2024, 12, 5);
		CustomDate debut1 = new CustomDate(2024, 12, 7);
		Matche matche = new Matche(1, debut, Categorie.POULE, equipe, equipe1, tournoi);
		Matche matche1 = new Matche(1, debut1, Categorie.POULE, equipe2, equipe3, tournoi);
		Partie partie1 = new Partie(matche, 1);
		Partie partie2 = new Partie(matche1, 1);
		try {
			daoMatche.add(matche);
			daoPartie.add(partie1);
			daoMatche.add(matche1);
			daoPartie.add(partie2);
			List<Partie> partieRecup = daoPartie.getPartieByMatche(matche);
			partieRecup.get(0).setVainqueur(equipe);
			daoPartie.update(partieRecup.get(0));
			matche.setVainqueur(equipe);
			daoMatche.update(matche);
			List<Partie> partieRecup1 = daoPartie.getPartieByMatche(matche1);
			partieRecup1.get(0).setVainqueur(equipe2);
			daoPartie.update(partieRecup.get(0));
			matche1.setVainqueur(equipe2);
			daoMatche.update(matche);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		//Ajout arbitres
		Arbitre arbitre = new Arbitre("arbitre", "arbitre", "1234567890");
		Arbitre arbitre1 = new Arbitre("arbitre1", "arbitre1", "1234567891");
		Arbitre arbitre2 = new Arbitre("arbitre2", "arbitre2", "1234567892");
		try {
			daoArbitre.add(arbitre);
			daoArbitre.add(arbitre1);
			daoArbitre.add(arbitre2);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

	//Fonction pour pseudo aléatoire
	private static String randomUsername(String name, int length) {
		List<String> characters = Arrays.asList(name.split(""));
		Collections.shuffle(characters);
		StringBuilder afterShuffle = new StringBuilder();
		for (int i = 0; i < length; i++) {
			afterShuffle.append(characters.get(i));
		}
		return afterShuffle.toString();
	}

	//Fonction ajout joueurs dans équipe
	private static void initEquipe(Equipe equipe) {
		Connexion c = Connexion.getConnexion();
		DaoJoueur daoJoueur = new DaoJoueur(c);
		String default_username = "abcdefghijkmnopqrstuvwxyzABCDEFGHIJKLMOPQRSTUVWXYZ0123456789";
		for (int i = 0; i < 5; i++) {
			default_username = randomUsername(default_username, 6);
			try {
				Joueur j = new Joueur(default_username, equipe);
				daoJoueur.add(j);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
