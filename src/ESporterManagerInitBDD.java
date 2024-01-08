import dao.*;
import modele.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class ESporterManagerInitBDD {
	public static void main(String[] args) throws Exception {
		Connexion c = Connexion.getConnexion();

		DaoTournoi daoTournoi = new DaoTournoi(c);
		DaoNiveau daoNiveau = new DaoNiveau(c);
		DaoPoule daoPoule = new DaoPoule(c);
		DaoEquipe daoEquipe = new DaoEquipe(c);
		DaoAppartenance daoAppartenance = new DaoAppartenance(c);
		DaoMatche daoMatche = new DaoMatche(c);
		DaoPartie daoPartie = new DaoPartie(c);
		try {
			daoNiveau.add(Niveau.LOCAL);
			daoNiveau.add(Niveau.INTERNATIONAL);
			daoNiveau.add(Niveau.REGIONAL);
			daoNiveau.add(Niveau.INTERNATIONAL_CLASSE);
			daoNiveau.add(Niveau.NATIONAL);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		Tournoi tournoi = daoTournoi.getTournoiActuel().get();
		Poule poule = new Poule(tournoi, 'A');

		CustomDate debut = new CustomDate(2023, 12, 5);
		CustomDate debut1 = new CustomDate(2023, 12, 7);

		Equipe equipe = new Equipe("FAZE", Pays.FRANCE);
		Equipe equipe1 = new Equipe("TSM", Pays.FRANCE);
		Equipe equipe2 = new Equipe("FOX", Pays.FRANCE);
		Equipe equipe3 = new Equipe("KC", Pays.FRANCE);
		Equipe equipe4 = new Equipe("F-Society", Pays.FRANCE);
		initEquipe(equipe);
		initEquipe(equipe1);
		initEquipe(equipe2);
		initEquipe(equipe3);
		initEquipe(equipe4);
		try {
			daoEquipe.add(equipe);
			daoEquipe.add(equipe1);
			daoEquipe.add(equipe2);
			daoEquipe.add(equipe3);
			daoEquipe.add(equipe4);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}


		Appartenance appartenance = new Appartenance(equipe, poule);
		Appartenance appartenance1 = new Appartenance(equipe1, poule);
		Appartenance appartenance2 = new Appartenance(equipe2, poule);
		Appartenance appartenance3 = new Appartenance(equipe3, poule);
		Appartenance appartenance4 = new Appartenance(equipe4, poule);
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
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		Matche matche = new Matche(1, debut, Categorie.POULE, equipe, equipe1, tournoi);

		
		Matche matche1 = new Matche(1, debut1, Categorie.POULE, equipe2, equipe3, tournoi);
		Partie partie1 = new Partie(matche, 1);
		Partie partie2 = new Partie(matche1, 1);
		try {
			daoMatche.add(matche);
			daoPartie.add(partie1);
			daoMatche.add(matche1);
			daoPartie.add(partie2);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

	private static String randomUsername(String name) {
		String str = name;
		List<String> characters = Arrays.asList(str.split(""));
		Collections.shuffle(characters);
		String afterShuffle = "";
		for (String character : characters) {
			afterShuffle += character;
		}
		return afterShuffle;
	}

	private static void initEquipe(Equipe equipe) {
		String default_username = "patata";
		for (int i = 0; i < 5; i++) {
			default_username = randomUsername(default_username);
			try {
				new Joueur(default_username, equipe);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
