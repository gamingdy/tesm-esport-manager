package modele;

import java.util.HashSet;
import java.util.Set;

public class Appartenance {
	
	private Equipe equipe;
	private Poule poule;
	
	public Appartenance(Equipe equipe, Poule poule) {
		this.equipe = equipe;
		this.poule = poule;
		
	}
	
	public Equipe getEquipe() {
		return equipe;
	}

	public Poule getPoule() {
		return poule;
	}

	@Override
	public String toString() {
		return "Appartenance [equipe=" + equipe + ", poule=" + poule + "]";
	}


}
