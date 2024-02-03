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
import dao.FactoryDAO;
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
		Saison saison = new Saison(2024);
		Saison saison2 = new Saison(2023);
		try {
			daoSaison.add(saison);
			daoSaison.add(saison2);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		CustomDate debutTournoi = new CustomDate(2024, 12, 1);
		CustomDate fin = new CustomDate(2024, 12, 30);
		Tournoi tournoi = new Tournoi(saison, "RLCS", debutTournoi, fin, Niveau.LOCAL, new CompteArbitre("arbitre", "rlcs"));

		CustomDate debutTournoi2 = new CustomDate(2024, 1, 9);
		CustomDate fin2 = new CustomDate(2024, 1, 30);
		Tournoi tournoi2 = new Tournoi(saison, "RLRS", debutTournoi2, fin2, Niveau.LOCAL, new CompteArbitre("arbitre", "rlrs"));
		try {
			daoTournoi.add(tournoi);
			daoTournoi.add(tournoi2);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		/*
		Optional<Tournoi> tournoiOptional = daoTournoi.getTournoiActuel();

		if (tournoiOptional.isPresent()) {
			tournoi = tournoiOptional.get();
		}*/

		Poule poule = new Poule(tournoi2, 'A');

		CustomDate debut = new CustomDate(2024, 12, 5);
		CustomDate debut1 = new CustomDate(2024, 12, 7);
		List<Equipe> equipeList = new ArrayList<>();
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
		Inscription inscription = new Inscription(saison, equipe);
		Inscription inscription1 = new Inscription(saison, equipe1);
		Inscription inscription2 = new Inscription(saison, equipe2);
		Inscription inscription3 = new Inscription(saison, equipe3);
		Inscription inscription4 = new Inscription(saison, equipe4);
		Inscription inscription5 = new Inscription(saison, equipe5);
		Inscription inscription6 = new Inscription(saison2, equipe);
		Inscription inscription7 = new Inscription(saison2, equipe1);
		Inscription inscription8 = new Inscription(saison2, equipe2);
		Inscription inscription9 = new Inscription(saison2, equipe3);
		Inscription inscription10 = new Inscription(saison2, equipe4);
		Inscription inscription11 = new Inscription(saison2, equipe5);

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
			daoInscription.add(inscription);
			daoInscription.add(inscription1);
			daoInscription.add(inscription2);
			daoInscription.add(inscription3);
			daoInscription.add(inscription4);
			daoInscription.add(inscription5);
			daoInscription.add(inscription6);
			daoInscription.add(inscription7);
			daoInscription.add(inscription8);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

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

		Appartenance appartenance = new Appartenance(equipe, poule);
		Appartenance appartenance1 = new Appartenance(equipe1, poule);
		Appartenance appartenance2 = new Appartenance(equipe2, poule);
		Appartenance appartenance3 = new Appartenance(equipe3, poule);
		Appartenance appartenance4 = new Appartenance(equipe4, poule);
		Appartenance appartenance5 = new Appartenance(equipe5, poule);

		equipeList.add(equipe);
		equipeList.add(equipe1);
		equipeList.add(equipe2);
		equipeList.add(equipe3);
		equipeList.add(equipe4);
		equipeList.add(equipe5);
		try {
			daoPoule.add(poule);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		try {
			daoAppartenance.add(appartenance);
			daoAppartenance.add(appartenance1);
			daoAppartenance.add(appartenance2);
			daoAppartenance.add(appartenance3);
			daoAppartenance.add(appartenance4);
			daoAppartenance.add(appartenance5);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		Creator.creationAutomatiqueMatches(equipeList, tournoi2);
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
			System.out.println(partieRecup);
			partieRecup.get(0).setVainqueur(equipe);
			daoPartie.update(partieRecup.get(0));
			matche.setVainqueur(equipe);
			daoMatche.update(matche);

			System.out.println(FactoryDAO.getDaoMatche(c).visualizeTable());
			System.out.println(FactoryDAO.getDaoPartie(c).visualizeTable());

			List<Partie> partieRecup1 = daoPartie.getPartieByMatche(matche1);
			partieRecup1.get(0).setVainqueur(equipe2);
			daoPartie.update(partieRecup.get(0));
			matche1.setVainqueur(equipe2);
			daoMatche.update(matche);

			System.out.println(FactoryDAO.getDaoMatche(c).visualizeTable());
			System.out.println(FactoryDAO.getDaoPartie(c).visualizeTable());
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		Arbitre arbitre = new Arbitre("arbitre", "arbitre", "1234567890");
		Arbitre arbitre1 = new Arbitre("arbitre1", "arbitre1", "1234567891");
		Arbitre arbitre2 = new Arbitre("arbitre2", "arbitre2", "1234567892");
		try {
			daoArbitre.add(arbitre);
			daoArbitre.add(arbitre1);
			daoArbitre.add(arbitre2);

			List<Arbitre> arbitres = daoArbitre.getAll();
			System.out.println(arbitres);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

	private static String randomUsername(String name) {
		List<String> characters = Arrays.asList(name.split(""));
		Collections.shuffle(characters);
		StringBuilder afterShuffle = new StringBuilder();
		for (String character : characters) {
			afterShuffle.append(character);
		}
		return afterShuffle.toString();
	}

	private static void initEquipe(Equipe equipe) {
		Connexion c = Connexion.getConnexion();
		DaoJoueur daoJoueur = new DaoJoueur(c);
		String default_username = "patata";
		for (int i = 0; i < 5; i++) {
			default_username = randomUsername(default_username);
			try {
				Joueur j = new Joueur(default_username, equipe);
				daoJoueur.add(j);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
