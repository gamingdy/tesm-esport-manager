package dao;

import java.util.List;
import java.util.stream.Collectors;

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
		
		/*System.out.println("_________________________________________");
		List<Appartenance> l = daoappartenance.getAll();
		l.stream().forEach(x -> System.out.println(x.toString()));
		System.out.println("_________________________________________");
		List<Arbitrage> l1 = daoarbitrage.getAll();
		l1.stream().forEach(x -> System.out.println(x.toString()));
		System.out.println("_________________________________________");
		List<Arbitre> l2 = daoarbitre.getAll();
		l2.stream().forEach(x -> System.out.println(x.toString()));
		System.out.println("_________________________________________");
		List<Equipe> l3 = daoequipe.getAll();
		l3.stream().forEach(x -> System.out.println(x.toString()));
		System.out.println("_________________________________________");
		List<Inscription> l4 = daoinscription.getAll();
		l4.stream().forEach(x -> System.out.println(x.toString()));*/
		System.out.println("_________________________________________");
		List<Joueur> l5 = daojoueur.getAll();
		l5.stream().forEach(x -> System.out.println(x.toString()));
		System.out.println("_________________________________________");
		List<Matche> l6 = daomatche.getAll();
		l6.stream().forEach(x -> System.out.println(x.toString()));
		System.out.println("_________________________________________");
		List<Niveau> l7 = daoniveau.getAll();
		l7.stream().forEach(x -> System.out.println(x));
		System.out.println("_________________________________________");
		List<Partie> l8 = daopartie.getAll();
		l8.stream().forEach(x -> System.out.println(x.toString()));
		System.out.println("_________________________________________");
		List<Poule> l9 = daopoule.getAll();
		l9.stream().forEach(x -> System.out.println(x.toString()));
		System.out.println("_________________________________________");
		List<Saison> l10 = daosaison.getAll();
		l10.stream().forEach(x -> System.out.println(x.toString()));
		System.out.println("_________________________________________");
		List<Selection> l11 = daoselection.getAll();
		l11.stream().forEach(x -> System.out.println(x.toString()));
		System.out.println("_________________________________________");
		List<Tournoi> l12 = daotournoi.getAll();
		l12.stream().forEach(x -> System.out.println(x.toString()));
		
	}

}
