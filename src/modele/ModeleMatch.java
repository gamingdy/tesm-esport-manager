package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import dao.Connexion;
import dao.FactoryDAO;

public class ModeleMatch {
	 
	public static Optional<Equipe> getWinner(Matche matche) throws Exception {
		
		List<Partie> partie = FactoryDAO.getDaoPartie(Connexion.getConnexion()).getPartieByMatche(matche);
		Iterator<Partie> iterator = partie.iterator();
		int frequenceEquipe1 = 0;
		int frequenceEquipe2 = 0;
		
		while(iterator.hasNext()) {
			if (iterator.next().getVainqueur().equals(matche.getEquipe1())) {
				frequenceEquipe1++;
			}
			if (iterator.next().getVainqueur().equals(matche.getEquipe2())) {
				frequenceEquipe2++;
			}
		}
		
		float moitie = matche.getNombreMaxParties()/2;
		Equipe vainqueur = null;
		if (frequenceEquipe1>moitie) {
			vainqueur = matche.getEquipe1();
		}
		if (frequenceEquipe2>moitie) {
			vainqueur = matche.getEquipe2();
		}
		return Optional.ofNullable(vainqueur);

	}
	
	public static Optional<Equipe> getLooser(Matche matche) throws Exception {
		Equipe perdant = null;
		if(ModeleMatch.getWinner(matche).isPresent()) {
			if(ModeleMatch.getWinner(matche).get().equals(matche.getEquipe1())) {
				perdant = matche.getEquipe2();
			}
			if(ModeleMatch.getWinner(matche).get().equals(matche.getEquipe2())) {
				perdant = matche.getEquipe1();
			}
		}
		return Optional.ofNullable(perdant);
	}
	

}
