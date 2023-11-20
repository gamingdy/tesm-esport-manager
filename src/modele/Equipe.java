package modele;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import exceptions.EquipeComplete;
import exceptions.EquipeVide;
import exceptions.JoueurNonPresent;
import exceptions.PointsNegatifs;

public class Equipe {

	private String nom;
	private Set<Joueur> equipe;
	private int point;
	private Country pays;

	public Equipe(String nom, Country pays) {
		this.equipe = new TreeSet<Joueur>();
		this.nom = nom;
		this.point = 0;
		this.pays = pays;
	}

	public Country getPays() {
		return this.pays;
	}

	public void setPays(Country pays) {
		this.pays = pays;
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


	public void setPoint(int point) throws PointsNegatifs {
		if (point < 0) {
			throw new PointsNegatifs("On ne peut pas mettre des points négatifs");
		}
		this.point = point;
	}

	public void addJoueur(Joueur joueur) throws EquipeComplete {
		if (this.equipe.size() == 5) {
			throw new EquipeComplete("L'équipe est pleine");
		}
		this.equipe.add(joueur);
	}

	public Joueur getJoueur(Joueur joueur) throws JoueurNonPresent, EquipeVide {
		if (this.equipe.isEmpty()) {
			throw new EquipeVide("L'équipe est vide");
		}
		for (Joueur j : this.equipe) {
			if (j.equals(joueur)) {
				return j;
			}
		}
		throw new JoueurNonPresent("Le joueur ne fait pas partie de l'équipe");
	}

	public void deleteJoueur(Joueur joueur) throws JoueurNonPresent, EquipeVide {
		if (this.equipe.isEmpty()) {
			throw new EquipeVide("L'équipe est vide");
		}
		Joueur joueur_res = null;
		for (Joueur j : this.equipe) {
			if (j.equals(joueur)) {
				joueur_res = j;
			}
		}
		if (joueur_res == null) {
			throw new JoueurNonPresent("Le joueur ne fait pas partie de l'équipe");
		}
		this.equipe.remove(joueur);
	}

	public int getNombreJoueurs() {
		return this.equipe.size();
	}

	public Integer getPoint() {
		return point;
	}
}

