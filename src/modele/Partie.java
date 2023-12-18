package modele;

import exceptions.GagnantNonChoisiException;

public class Partie {

	private int numeroPartie;
	private int vainqueur;
	private Matche matche;

	
	public Partie(Matche matche, int numeroPartie) throws IllegalArgumentException {
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

	public void setNumeroPartie(int numeroPartie) throws IllegalArgumentException {
		if (numeroPartie <= 0) {
			throw new IllegalArgumentException("Le numéro de partie ne peut pas être négatif");
		}
		this.numeroPartie = numeroPartie;
	}

	public Equipe getVainqueur() throws GagnantNonChoisiException {
		switch (this.vainqueur) {
			case 1:
				return this.matche.getEquipe1();
			case 2:
				return this.matche.getEquipe2();
			default:
				throw new GagnantNonChoisiException("Le gagnant n'a pas été choisi");
		}
	}

	public void setVainqueur(Equipe vainqueur) {
		if (vainqueur.getNom().equals(this.matche.getEquipe1().getNom())) {
			this.vainqueur = 1;
		} else {
			this.vainqueur = 2;
		}
	}

	public Matche getMatche() {
		return matche;
	}

	@Override
	public String toString() {
		return "Partie [numeroPartie=" + numeroPartie + ", vainqueur=" + vainqueur + ", matche=" + matche + "]";
	}

}