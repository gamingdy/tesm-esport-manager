package modele;

import java.util.HashSet;
import java.util.Set;

public class Appartenance {
	
	private Equipe equipe;
	private Poule poule;
	private Set<Appartenance> appartenances;
	
	public Appartenance(Equipe equipe, Poule poule) {
		this.equipe = equipe;
		this.poule = poule;
		this.appartenances = new HashSet<>();
	}
	
	public void addAppartenance(Appartenance appartenance) {
		this.appartenances.add(appartenance);
	}
	
	public void deleteAppartenance(Appartenance appartenance) {
		this.appartenances.remove(appartenance);
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
