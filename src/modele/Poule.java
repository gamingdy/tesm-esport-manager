package modele;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import exceptions.PouleComplete;

public class Poule {

	private Short anneeTournoi;
	private String nomTournoi;
	private String libelle;
	private Set<Equipe> equipes;
	
	public Poule(Object[] idTournoi, String libelle) {
		this.anneeTournoi = (Short) idTournoi[0];
		this.nomTournoi = (String) idTournoi[1];
		this.libelle = libelle;
		this.equipes = new HashSet<Equipe>();
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public void addEquipe(Equipe equipe) throws PouleComplete {
		if (this.equipes.size()==8) {
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

	public Short getAnneeTournoi() {
		return anneeTournoi;
	}

	public String getNomTournoi() {
		return nomTournoi;
	}
}
