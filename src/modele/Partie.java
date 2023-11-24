package modele;

public class Partie {

	private int numeroPartie;
	private int vainqueur;
	private Matche matche;


	public Partie(String nom, Matche matche) {
		this.vainqueur = 0;
		this.matche = matche;
	}

	public Partie(String nom, Matche matche, int numeroPartie) throws IllegalArgumentException{
		if (numeroPartie <= 0) {
			throw new IllegalArgumentException("Le numéro de partie ne peut pas être négatif");
		}
		this.numeroPartie = numeroPartie;
		this.vainqueur = 0;
		this.matche = matche;
	}

	public int getNumeroPartie() {
		return numeroPartie;
	}

	public void setNumeroPartie(int numeroPartie) throws IllegalArgumentException{
		if (numeroPartie <= 0) {
			throw new IllegalArgumentException("Le numéro de partie ne peut pas être négatif");
		}
		this.numeroPartie = numeroPartie;
	}

	public Equipe getVainqueur() throws GagnantNonChoisi {
		switch(this.vainqueur) {
			case 1 :
				return this.matche.getEquipe1();
			case 2 : 
				return this.matche.getEquipe2();
			default:
				throw new GagnantNonChoisi("Le gagnant n'a pas été choisi");
		}
	}

	public void setVainqueur(Equipe vainqueur) {
		if(vainqueur.getNom().equals(this.matche.getEquipe1().getNom())) {
			this.vainqueur = 1;
		} else {
			this.vainqueur = 2;
		}
	}

	public Matche getMatche() {
		return matche;
	}

	public void setMatche(Matche matche) {
		this.matche = matche;
	}


}