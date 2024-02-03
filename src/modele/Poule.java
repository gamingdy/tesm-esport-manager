package modele;

public class Poule {

	private final Tournoi tournoi;
	private final Character libelle;

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

	@Override
	public String toString() {
		return "Poule [tournoi=" + tournoi + ", libelle=" + libelle + "]";
	}

}