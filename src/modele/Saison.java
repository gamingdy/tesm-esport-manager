package modele;

import exceptions.EquipeInexistante;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;

public class Saison {

	private int annee;
	private Map<Equipe, Integer> equipes;
	private Set<Arbitre> arbitres;
	private Set<Tournoi> tournois;

	public Saison(int annee) {
		this.equipes = new HashMap<Equipe, Integer>();
		this.arbitres = new TreeSet<Arbitre>();
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

	public void addEquipe(Equipe equipe) {
		//TODO Ajouter l'automatisation de la value (world rank)
		//Si l'équipe existait à la saison d'avant Alors
		//world rank = rank de la saison précédente
		//Sinon
		//world rank = 1000
		this.equipes.put(equipe, 1000);
	}

	public void deleteEquipe(Equipe equipe) throws EquipeInexistante {
		if (!equipes.containsKey(equipe)) {
			throw new EquipeInexistante("Cette équipe n'est pas dans la saison");
		}
		this.equipes.remove(equipe);
	}

	public Set<Equipe> getEquipes() {
		return this.equipes.keySet();
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
}
