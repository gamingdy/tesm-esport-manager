package modele;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(libelle, tournoi);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poule other = (Poule) obj;
		return Objects.equals(libelle, other.libelle) && Objects.equals(tournoi, other.tournoi);
	}

}