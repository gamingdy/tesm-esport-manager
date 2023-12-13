package modele;

import exceptions.GagnantNonChoisiException;

public class Partie {

	private int numeroPartie;
	private Equipe vainqueur;
	private Matche matche;


	public Partie(Matche matche) {
		this.vainqueur = null;
		this.matche = matche;
	}

	public Partie(Matche matche, int numeroPartie) throws IllegalArgumentException {
		if (numeroPartie <= 0) {
			throw new IllegalArgumentException("Le numéro de partie ne peut pas être négatif");
		}
		this.numeroPartie = numeroPartie;
		this.vainqueur = null;
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
		if (this.vainqueur == null) {
			throw new GagnantNonChoisiException("Le gagnant n'a pas été choisi");
		}
		return this.vainqueur;
	}

	public void setVainqueur(Equipe vainqueur) {
		this.vainqueur = vainqueur;
	}

	public Matche getMatche() {
		return matche;
	}

	@Override
	public String toString() {
		return "Partie [numeroPartie=" + numeroPartie + ", vainqueur=" + vainqueur + ", matche=" + matche + "]";
	}

}