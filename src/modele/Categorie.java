package modele;

public enum Categorie {
	POULE("POULE",25,15),
	FINALE("FINALE",200,100),
	DEMI_FINALE("DEMI_FINALE",0,0),
	QUART_FINALE("QUART_FINALE",0,0),
	PETITE_FINALE("PETIT_FINALE",50,25),;

	private String designation;
	private int ptsSaisonVictoire;
	private int ptsSaisonDefaite;
	
	private Categorie(String designation, int ptsSaisonVictoire, int ptsSaisonDefaite) {
		this.designation=designation;
		this.ptsSaisonVictoire=ptsSaisonVictoire;
		this.ptsSaisonDefaite=ptsSaisonDefaite;
	}

	public String getDesignation() {
		return designation;
	}

	public int getPtsSaisonVictoire() {
		return ptsSaisonVictoire;
	}

	public int getPtsSaisonDefaite() {
		return ptsSaisonDefaite;
	}
}
