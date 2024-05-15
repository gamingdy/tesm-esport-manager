package dao;

import modele.Appartenance;
import modele.Arbitrage;
import modele.Arbitre;
import modele.Equipe;
import modele.Inscription;
import modele.Joueur;
import modele.Matche;
import modele.Niveau;
import modele.Partie;
import modele.Poule;
import modele.Saison;
import modele.Selection;
import modele.Tournoi;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VisualizeDB {
	private static final Logger LOGGER = Logger.getLogger(VisualizeDB.class.getName());

	public static void main(String[] args) throws Exception {

		Connexion c = Connexion.getConnexion();
		DaoAppartenance daoappartenance = new DaoAppartenance(c);
		DaoArbitrage daoarbitrage = new DaoArbitrage(c);
		DaoArbitre daoarbitre = new DaoArbitre(c);
		DaoEquipe daoequipe = new DaoEquipe(c);
		DaoInscription daoinscription = new DaoInscription(c);
		DaoJoueur daojoueur = new DaoJoueur(c);
		DaoMatche daomatche = new DaoMatche(c);
		DaoNiveau daoniveau = new DaoNiveau(c);
		DaoPartie daopartie = new DaoPartie(c);
		DaoPoule daopoule = new DaoPoule(c);
		DaoSaison daosaison = new DaoSaison(c);
		DaoSelection daoselection = new DaoSelection(c);
		DaoTournoi daotournoi = new DaoTournoi(c);
		LOGGER.info("VisualizeDB started");
		LOGGER.info("_______________Appartenance_______________________");
		List<Appartenance> l = daoappartenance.getAll();
		l.forEach(x -> LOGGER.info(x.toString()));


		LOGGER.info("_______________Arbitrage__________________________");
		List<Arbitrage> l1 = daoarbitrage.getAll();
		l1.forEach(x -> LOGGER.info(x.toString()));

		LOGGER.info("_______________Arbitre____________________________");
		List<Arbitre> l2 = daoarbitre.getAll();
		l2.forEach(x -> LOGGER.info(x.toString()));

		LOGGER.info("_______________Equipe_____________________________");
		List<Equipe> l3 = daoequipe.getAll();
		l3.forEach(x ->LOGGER.info(x.toString()));

		LOGGER.info("_______________Inscription________________________");
		List<Inscription> l4 = daoinscription.getAll();
		l4.forEach(x -> LOGGER.info(x.toString()));
		System.out.println("\n\n\n");

		LOGGER.info("_______________Joueur_____________________________");
		List<Joueur> l5 = daojoueur.getAll();
		l5.forEach(x -> LOGGER.info(x.toString()));

		LOGGER.info("_______________Matche_____________________________");
		List<Matche> l6 = daomatche.getAll();
		l6.forEach(x -> LOGGER.info(x.toString()));
		System.out.println("\n\n\n");

		LOGGER.info("_______________Niveau_____________________________");
		List<Niveau> l7 = daoniveau.getAll();
		l7.forEach(x -> LOGGER.info(x.toString()));

		LOGGER.info("_______________Partie_____________________________");
		List<Partie> l8 = daopartie.getAll();
		l8.forEach(x -> LOGGER.info(x.toString()));

		LOGGER.info("_______________Poule______________________________");
		List<Poule> l9 = daopoule.getAll();
		l9.forEach(x -> LOGGER.info(x.toString()));

		LOGGER.info("_______________Saison_____________________________");
		List<Saison> l10 = daosaison.getAll();
		l10.forEach(x -> LOGGER.info(x.toString()));

		LOGGER.info("_______________Selection__________________________");
		List<Selection> l11 = daoselection.getAll();
		l11.forEach(x -> LOGGER.info(x.toString()));

		LOGGER.info("_______________Tournoi____________________________");
		List<Tournoi> l12 = daotournoi.getAll();
		l12.forEach(x ->LOGGER.info(x.toString()));

	}

}
