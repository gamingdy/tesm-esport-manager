package modele;

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

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Poule getPoule() {
		return poule;
	}

	public void setPoule(Poule poule) {
		this.poule = poule;
	}


}
