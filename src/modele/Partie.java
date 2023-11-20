package modele;

public class Partie {

	private int id;
	private int numeroPartie;
	private int vainqueur;
	private Matche matche;


	public Partie(int id, byte numeroPartie, String nom, Matche matche) {
		this.id = id;
		this.numeroPartie = numeroPartie;
		this.vainqueur = 0;
		this.matche = matche;
	}

	public int getId() {
		return id;
	}

	public int getNumeroPartie() {
		return numeroPartie;
	}

	public void setNumeroPartie(int numeroPartie) {
		this.numeroPartie = numeroPartie;
	}

	public Equipe getVainqueur() {
		Equipe equipe = null;
		if (this.vainqueur == 1) {
			equipe = this.matche.getEquipe1();
		}
		if (this.vainqueur == 2) {
			equipe = this.matche.getEquipe2();
		}
		return equipe;
	}

	public void setVainqueur(Equipe vainqueur) {
		if (vainqueur.getNom() == this.matche.getEquipe1().getNom()) {
			this.vainqueur = 1;
		}
		if (vainqueur.getNom() == this.matche.getEquipe2().getNom()) {
			this.vainqueur = 2;
		}
	}

	public Matche getmatche() {
		return matche;
	}

	public void setmatche(Matche matche) {
		this.matche = matche;
	}


}
