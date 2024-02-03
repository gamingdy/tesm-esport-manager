package modele;

import dao.Connexion;
import dao.FactoryDAO;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ModeleTournoi {

	public static Set<Equipe> getClassement(Tournoi tournoi) throws Exception {
		List<Equipe> allEquipeTournoi = FactoryDAO.getDaoAppartenance(Connexion.getConnexion()).getEquipeByTournoi(tournoi.getNom(), tournoi.getSaison().getAnnee());
		Set<Equipe> classement = new TreeSet<>((e1, e2) -> {
			return (int) (e2.getPoint() - e1.getPoint()) == 0 ? e1.getNom().compareTo(e2.getNom()) : (int) (e1.getPoint() - e2.getPoint());
		});
		for (Equipe e : allEquipeTournoi) {
			List<Matche> matchesEquipe = FactoryDAO.getDaoMatche(Connexion.getConnexion()).getMatchByEquipeForTournoi(e, tournoi);
			Iterator<Matche> it = matchesEquipe.iterator();
			while (it.hasNext()) {
				Matche m = it.next();
				List<Partie> partieList = FactoryDAO.getDaoPartie(Connexion.getConnexion()).getPartieByMatche(m);
				m.setVainqueur(partieList.get(0).getVainqueur());
				if (m.getVainqueur() != null) {
					if (m.getVainqueur().equals(e)) {
						e.setPoint(e.getPoint() + 3);
					} else {
						e.setPoint(e.getPoint() + 1);
					}
				}

			}
			classement.add(e);
		}
		return classement;
	}


}
