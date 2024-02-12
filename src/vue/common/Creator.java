package vue.common;

import dao.Connexion;
import dao.DaoMatche;
import dao.DaoPartie;
import exceptions.FausseDateException;
import exceptions.MemeEquipeException;
import modele.Categorie;
import modele.CustomDate;
import modele.Equipe;
import modele.Matche;
import modele.Partie;
import modele.Tournoi;

import java.util.ArrayList;
import java.util.List;

public class Creator {

	public static void creationAutomatiqueMatches(List<Equipe> listeEquipe, Tournoi tournoi) {
		Connexion connexion = Connexion.getConnexion();
		DaoMatche daoMatche = new DaoMatche(connexion);
		DaoPartie daoPartie = new DaoPartie(connexion);
		CustomDate dateDebut = tournoi.getDebut();
		CustomDate dateFin = tournoi.getFin();

		//Calcule la durée du tournoi, en enlevant le dernier jour dédié à la finale
		int nbDay = CustomDate.dureeEnJour(dateDebut, dateFin);
		if (nbDay > 1) {
			nbDay -= 1;
		}

		//Calcule le nombre de matchs à jouer
		int nbMatches = 0;
		for (int i = 0; i < listeEquipe.size(); i++) {
			nbMatches += i;
		}
		
		int matchParJour = nbMatches / nbDay;
		int reste = nbMatches % nbDay;

		CustomDate dateActuel = dateDebut;
		int currentDay = 1;
		List<Matche> all_match = new ArrayList<>();
		List<Matche> matcheOfDay = new ArrayList<>();
		for (int i = 0; i < listeEquipe.size() - 1; i++) {
			for (int j = i + 1; j < listeEquipe.size(); j++) {
				Equipe equipe1 = listeEquipe.get(i);
				Equipe equipe2 = listeEquipe.get(j);
				try {
					Matche matche = new Matche(1, dateActuel, Categorie.POULE, equipe1, equipe2, tournoi);
					matcheOfDay.add(matche);
					if (matcheOfDay.size() >= matchParJour) {
						if (currentDay == nbDay) {
							if (matcheOfDay.size() == matchParJour + reste) {
								all_match.addAll(matcheOfDay);
								matcheOfDay.clear();
								dateActuel = dateActuel.plusOne();
								currentDay += 1;
							}
						} else {
							all_match.addAll(matcheOfDay);
							matcheOfDay.clear();
							dateActuel = dateActuel.plusOne();
							currentDay += 1;
						}
					}
				} catch (FausseDateException | MemeEquipeException e) {
					e.printStackTrace();
				}
			}
		}

		for (Matche matche : all_match) {
			try {
				daoMatche.add(matche);
				Partie partie = new Partie(matche, 1);
				daoPartie.add(partie);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
