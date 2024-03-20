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

public class VisualizeDB {

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

		System.out.println("_______________Appartenance_______________________");
		System.out.println("\n");
		List<Appartenance> l = daoappartenance.getAll();
		l.forEach(x -> System.out.println(x.toString()));
		System.out.println("\n\n\n");

		System.out.println("_______________Arbitrage__________________________");
		System.out.println("\n");
		List<Arbitrage> l1 = daoarbitrage.getAll();
		l1.forEach(x -> System.out.println(x.toString()));
		System.out.println("\n\n\n");

		System.out.println("_______________Arbitre____________________________");
		System.out.println("\n");
		List<Arbitre> l2 = daoarbitre.getAll();
		l2.forEach(x -> System.out.println(x.toString()));
		System.out.println("\n\n\n");

		System.out.println("_______________Equipe_____________________________");
		System.out.println("\n");
		List<Equipe> l3 = daoequipe.getAll();
		l3.forEach(x -> System.out.println(x.toString()));
		System.out.println("\n\n\n");

		System.out.println("_______________Inscription________________________");
		System.out.println("\n");
		List<Inscription> l4 = daoinscription.getAll();
		l4.forEach(x -> System.out.println(x.toString()));
		System.out.println("\n\n\n");

		System.out.println("_______________Joueur_____________________________");
		System.out.println("\n");
		List<Joueur> l5 = daojoueur.getAll();
		l5.forEach(x -> System.out.println(x.toString()));
		System.out.println("\n\n\n");

		System.out.println("_______________Matche_____________________________");
		System.out.println("\n");
		List<Matche> l6 = daomatche.getAll();
		l6.forEach(x -> System.out.println(x.toString()));
		System.out.println("\n\n\n");

		System.out.println("_______________Niveau_____________________________");
		System.out.println("\n");
		List<Niveau> l7 = daoniveau.getAll();
		l7.forEach(x -> System.out.println(x.toString()));
		System.out.println("\n\n\n");

		System.out.println("_______________Partie_____________________________");
		System.out.println("\n");
		List<Partie> l8 = daopartie.getAll();
		l8.forEach(x -> System.out.println(x.toString()));
		System.out.println("\n\n\n");

		System.out.println("_______________Poule______________________________");
		System.out.println("\n");
		List<Poule> l9 = daopoule.getAll();
		l9.forEach(x -> System.out.println(x.toString()));
		System.out.println("\n\n\n");

		System.out.println("_______________Saison_____________________________");
		System.out.println("\n");
		List<Saison> l10 = daosaison.getAll();
		l10.forEach(x -> System.out.println(x.toString()));
		System.out.println("\n\n\n");

		System.out.println("_______________Selection__________________________");
		System.out.println("\n");
		List<Selection> l11 = daoselection.getAll();
		l11.forEach(x -> System.out.println(x.toString()));
		System.out.println("\n\n\n");

		System.out.println("_______________Tournoi____________________________");
		System.out.println("\n");
		List<Tournoi> l12 = daotournoi.getAll();
		l12.forEach(x -> System.out.println(x.toString()));

	}

}
