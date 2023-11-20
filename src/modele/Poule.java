package modele;

public class Poule {

	private Tournoi tournoi;
	private Character libelle;
	
	public Poule(Tournoi tournoi, Character libelle) {
		this.tournoi = tournoi;
		this.libelle = libelle;
	}

	public Tournoi getTournoi() {
		return tournoi;
	}
	public Character getLibelle() {
		return libelle;
	}

	public void setLibelle(Character libelle) {
		this.libelle = libelle;
	}
}
