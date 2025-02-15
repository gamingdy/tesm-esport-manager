package modele;

import dao.Connexion;
import dao.FactoryDAO;
import exceptions.ExceptionPointsNegatifs;
import exceptions.FausseDateException;
import exceptions.GagnantNonChoisiException;
import exceptions.IdNotSetException;
import exceptions.MemeEquipeException;

import java.sql.SQLException;
import java.util.ArrayList;
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
		TreeSet<Equipe> classement = new TreeSet<>((e1, e2) -> (int) (e2.getPoint() - e1.getPoint()) == 0 ? e1.getNom().compareTo(e2.getNom()) : (int) (e2.getPoint() - e1.getPoint()));

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
				try {
					List<Partie> partieList = FactoryDAO.getDaoPartie(Connexion.getConnexion()).getPartieByMatche(m);
					m.setVainqueur(partieList.get(0).getVainqueur());
				} catch (GagnantNonChoisiException e3) {
					e3.printStackTrace();
				} catch (IllegalArgumentException e3) {
					e3.printStackTrace();
				} catch (IdNotSetException e3) {
					e3.printStackTrace();
				}
				//On ajoute les points à l'équipe selon la catégorie du match, la victoire ou la défaite de l'équipe et le niveau du tournoi
				ModeleSaison.setPointsEquipeMatch(m, e);
			}
			
			System.out.println("Equipe points" + e.getPoint());
			//En sortant de la boucle on ajoute cette Equipe au set trié
			classement.add(e);
		}
		ArrayList<Equipe> classmentList = new ArrayList<>(classement);
		for (int i = 0; i< classmentList.size(); i++) {
			Inscription ins = new Inscription(saison, classmentList.get(i));
			ins.setWorldRank(i+1);
			FactoryDAO.getDaoInscription(Connexion.getConnexion()).update(ins);
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
