package modele;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import dao.Connexion;
import dao.FactoryDAO;

public class ModeleTournoi {
	
	public static Set<Equipe> getClassement(Tournoi tournoi) throws Exception {
		List<Equipe> allEquipeTournoi = FactoryDAO.getDaoAppartenance(Connexion.getConnexion()).getEquipeByTournoi(tournoi.getNom(),tournoi.getSaison().getAnnee());
		Set<Equipe> classement = new TreeSet<>((e1,e2) -> {
			return (int)(e1.getPoint()-e2.getPoint());
		});
		for(Equipe e : allEquipeTournoi) {
			List<Matche> matchesEquipe = FactoryDAO.getDaoMatche(Connexion.getConnexion()).getMatchByEquipeForTournoi(e, tournoi);
			Iterator<Matche> it = matchesEquipe.iterator();
			while(it.hasNext()) {
				Matche m = it.next();
				if(m.getVainqueur().equals(e)) {
					e.setPoint(e.getPoint()+3);
				} else {
					e.setPoint(e.getPoint()+1);
				}
			}
			classement.add(e);
		}
		return classement;
	}
	

}
