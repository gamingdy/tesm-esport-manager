package modele;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import exceptions.PouleComplete;

public class Poule {

	private Tournoi tournoi;

	private Character libelle;
	private Map<Equipe,Integer> equipes;
	
	public Poule(Tournoi tournoi, Character libelle) {
		this.tournoi = tournoi;
		this.libelle = libelle;
		this.equipes = new HashMap<Equipe,Integer>();
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

	public Integer getPoint(Equipe equipe){
		return this.equipes.get(equipe);
	}

	public void ajouterPoint(Equipe equipe, Integer point){
		this.equipes.put(equipe, this.equipes.get(equipe)+point);
	}

	public void enleverPoint(Equipe equipe, Integer point){
		this.equipes.put(equipe, this.equipes.get(equipe) - point);
	}
	
	public void addEquipe(Equipe equipe) throws PouleComplete {
		if (this.equipes.size() == 8) {
			throw new PouleComplete("La poule est complète");
		}
		this.equipes.put(equipe,0);
	}

	public void deleteEquipe(Equipe equipe) {
		this.equipes.remove(equipe);
	}

	public Set<Equipe> getEquipes() {
		return this.equipes.keySet();
	}

	@Override
	public String toString(){
		String str = "";
		for (Equipe e : this.getEquipes()){
			str += e.getNom() + " : " + this.equipes.get(e);
			str += System.lineSeparator();
		}
		return str;
	}
}
