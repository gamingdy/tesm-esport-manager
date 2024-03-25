package modele;

import dao.Connexion;
import dao.FactoryDAO;
import exceptions.ExceptionPointsNegatifs;
import exceptions.FausseDateException;
import exceptions.MemeEquipeException;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ModeleSaison {
	private ModeleSaison() {
	}

	public static Set<Equipe> getClassement(Saison saison) throws SQLException, MemeEquipeException, FausseDateException, ExceptionPointsNegatifs {
		//On récupère toutes les équipes
		List<Equipe> allEquipe = FactoryDAO.getDaoEquipe(Connexion.getConnexion()).getAll();
		//On défini le Set d'équipes de sortie qui est trié en fonction des points
		Set<Equipe> classement = new TreeSet<>((e1, e2) -> (int) (e2.getPoint() - e1.getPoint()) == 0 ? e1.getNom().compareTo(e2.getNom()) : (int) (e1.getPoint() - e2.getPoint()));

		//Pour chaque Equipe de la liste allEquipe définie plus tôt
		for (Equipe e : allEquipe) {

			//A partir de cette Equipe et de la saison en paramètre, on crée un objet inscription
			Inscription inscription = new Inscription(saison, e);

			//A partir de cette inscription on récupère la liste de tous les matchs de l'équipe de la saison passée en paramètre 
			List<Matche> matchesEquipe = FactoryDAO.getDaoMatche(Connexion.getConnexion()).getMatchByEquipeForSaison(inscription);
			//On en extrait un itérateur
			Iterator<Matche> it = matchesEquipe.iterator();

			//Tant qu'un autre match pour l'équipe actuelle est trouvé
			while (it.hasNext()) {
				Matche m = it.next();
				//On ajoute les points à l'équipe selon la catégorie du match, la victoire ou la défaite de l'équipe et le niveau du tournoi
				ModeleSaison.setPointsEquipeMatch(m, e);
			}
			System.out.println("Equipe points" + e.getPoint());
			//En sortant de la boucle on ajoute cette Equipe au set trié
			classement.add(e);
		}

		//On renvoit le Set d'équipe triés par leur points
		return classement;
	}

	private static void setPointsEquipeMatch(Matche m, Equipe e) throws ExceptionPointsNegatifs {
		if (m.getVainqueur() != null) {
			if (m.getVainqueur().equals(e)) {
				e.setPoint(e.getPoint() + (m.getCategorie().getPtsSaisonVictoire() * m.getTournoi().getNiveau().getCoefficient()));
			} else {
				e.setPoint(e.getPoint() + (m.getCategorie().getPtsSaisonDefaite() * m.getTournoi().getNiveau().getCoefficient()));
			}
		}
	}

}
