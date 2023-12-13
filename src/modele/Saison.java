package modele;


import java.util.*;

public class Saison {

	private int annee;
	private Set<Tournoi> tournois;

	public Saison(int annee) {
		
		this.tournois = new HashSet<Tournoi>();
		this.annee = annee;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	@Override
	public String toString() {
		return "Saison [annee=" + annee	+ "]";
	}

	public void addTournoi(Tournoi tournoi) {
		this.tournois.add(tournoi);
	}

	public void deleteTournoi(Tournoi tournoi) {
		this.tournois.remove(tournoi);
	}

	public Set<Tournoi> getTournois() {
		return tournois;
	}

	public Tournoi getTournoi(String nom) {
		Tournoi tournoi = null;
		for (Tournoi t : this.tournois) {
			if (t.getNom() == nom) {
				tournoi = t;
			}
		}
		return tournoi;
	}

	@Override
	public int hashCode() {
		return Objects.hash(annee);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Saison other = (Saison) obj;
		return annee == other.annee;
	}

}
