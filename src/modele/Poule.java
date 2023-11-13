package modele;

import java.util.Set;
import java.util.TreeSet;

import exceptions.PouleComplete;

public class Poule {

	private Tournoi tournoi;
	private char libelle;
	private Set<Equipe> equipes;

	public Poule(Tournoi tournoi, char libelle) {
		this.tournoi = tournoi;
		this.libelle = libelle;
		this.equipes = new TreeSet<Equipe>();
	}

	public Tournoi getTournoi() {
		return tournoi;
	}
    
	public char getLibelle() {
		return libelle;
	}

	public void setLibelle(char libelle) {
		this.libelle = libelle;
	}

	public void addEquipe(Equipe equipe) throws PouleComplete {
		if (this.equipes.size() == 8) {
			throw new PouleComplete("La poule est complète");
		}
		this.equipes.add(equipe);
	}

	public void deleteEquipe(Equipe equipe) {
		this.equipes.remove(equipe);
	}

	public Set<Equipe> getEquipes() {
		return equipes;
	}
}
