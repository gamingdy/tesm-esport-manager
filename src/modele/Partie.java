package modele;

public class Partie {

	private int numeroPartie;
	private int vainqueur;
	private Matche matche;


	public Partie(byte numeroPartie, String nom, Matche matche) {
		this.numeroPartie = numeroPartie;
		this.vainqueur = 0;
		this.matche = matche;
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

	public Integer getIdMatche() {
		return idMatch;
	}

	public void setIdMatche(Integer idMatche) {
		this.idMatch = idMatche;
	}


}