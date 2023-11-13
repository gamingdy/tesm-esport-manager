package modele;

import java.util.Set;
import java.util.TreeSet;

import exceptions.EquipeComplete;
import exceptions.EquipeVide;
import exceptions.JoueurNonPresent;

public class Equipe {

	private String nom;
	private Set<Joueur> equipe;
	private Integer point;
	
	public Equipe(String nom) {
		this.equipe = new TreeSet<Joueur>();
		this.nom = nom;
		this.point = 0;
	}

	public Set<Joueur> getEquipe() {
		return equipe;
	}

	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getPoint() {
		return this.point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}

	public void addJoueur(Joueur joueur) throws EquipeComplete{
		if (this.equipe.size()==5) {
			throw new EquipeComplete("L'équipe est pleine");
		}
		this.equipe.add(joueur);
	}
	
	public Joueur getJoueur(Joueur joueur) throws JoueurNonPresent, EquipeVide {
		if(this.equipe.size()==0) {
			throw new EquipeVide("L'équipe est vide");
		}
		for(Joueur j : this.equipe) {
			if(j.getId()==joueur.getId()) {
				return j;
			}
		}
		throw new JoueurNonPresent("Le joueur ne fait pas partie de l'équipe");
	}
	
	public void deleteJoueur(Joueur joueur) {
		this.equipe.remove(joueur);
	}
}
