package modele;


import exceptions.EquipeInexistanteException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Saison {

	private int annee;
	private Map<Equipe, Integer> equipes;
	private Set<Arbitre> arbitres;
	private Set<Tournoi> tournois;

	public Saison(int annee) {
		this.equipes = new HashMap<Equipe, Integer>();
		this.arbitres = new TreeSet<Arbitre>();
		this.tournois = new HashSet<Tournoi>();
		this.annee = annee;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public void addArbitre(Arbitre arbitre) {
		this.arbitres.add(arbitre);
	}

	public void deleteArbitre(Arbitre arbitre) {
		this.arbitres.remove(arbitre);
	}

	public Set<Arbitre> getArbitres() {
		return arbitres;
	}

	public void addEquipe(Equipe equipe, Integer rank) {
		if (rank.equals(null)) {
			this.equipes.put(equipe, rank);
		} else {
			this.equipes.put(equipe, 1000);
		}
	}

	public void addEquipe(Equipe equipe) {
		this.equipes.put(equipe, 1000);
	}

	public void deleteEquipe(Equipe equipe) throws EquipeInexistanteException {
		if (!equipes.containsKey(equipe)) {
			throw new EquipeInexistanteException("Cette équipe n'est pas dans la saison");
		}
		this.equipes.remove(equipe);
	}

	public Set<Equipe> getEquipes() {
		return this.equipes.keySet();
	}

	@Override
	public String toString() {
		return "Saison [annee=" + annee + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Saison) {
			Saison saison = (Saison) obj;
			return this.annee == saison.getAnnee();
		}
		return false;
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
}
