package modele;


import java.util.*;

public class Saison {

	private int annee;
	private Map<String,Tournoi> tournois;

	public Saison(int annee) {
		
		this.tournois = new HashMap<String,Tournoi>();
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
		this.tournois.put(tournoi.getNom(),tournoi);
	}

	public void deleteTournoi(Tournoi tournoi) {
		this.tournois.remove(tournoi.getNom());
	}

	public Set<Tournoi> getTournois() {
		return new HashSet<Tournoi>(tournois.values());
	}

	public Tournoi getTournoi(String nom) {
		return tournois.get(nom);
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
