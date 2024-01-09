package modele;

import exceptions.EquipeCompleteException;
import exceptions.EquipeVideException;
import exceptions.ExceptionPointsNegatifs;
import exceptions.JoueurNonPresentException;

import java.util.LinkedHashSet;
import java.util.Set;

public class Equipe {

	private String nom;
	private Set<Joueur> equipe; //à voir si on laisse ou créer une classe association
	private int point; //calculable donc pas dans le MCDi
	private Pays pays;


	public Equipe(String nom, Pays pays) {
		this.equipe = new LinkedHashSet<Joueur>();
		this.nom = nom;
		this.point = 0;
		this.pays = pays;
	}

	public Pays getPays() {
		return this.pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public Set<Joueur> getEquipe() {
		return equipe;
	}

	public String getNom() {
		return this.nom;
	}

	public void setPoint(int point) throws ExceptionPointsNegatifs {
		if (point < 0) {
			throw new ExceptionPointsNegatifs("On ne peut pas mettre des points negatifs");
		}
		this.point = point;
	}

	public void addJoueur(Joueur joueur) throws EquipeCompleteException {
		if (this.equipe.size() == 5) {
			throw new EquipeCompleteException("L'équipe est pleine");
		}
		this.equipe.add(joueur);
	}

	public Joueur getJoueur(Joueur joueur) throws JoueurNonPresentException, EquipeVideException {
		if (this.equipe.isEmpty()) {
			throw new EquipeVideException("L'équipe est vide");
		}
		for (Joueur j : this.equipe) {
			if (j.equals(joueur)) {
				return j;
			}
		}
		throw new JoueurNonPresentException("Le joueur ne fait pas partie de l'équipe");
	}

	public void deleteJoueur(Joueur joueur) throws JoueurNonPresentException, EquipeVideException {
		if (this.equipe.isEmpty()) {
			throw new EquipeVideException("L'équipe est vide");
		}
		Joueur joueur_res = null;
		for (Joueur j : this.equipe) {
			if (j.equals(joueur)) {
				joueur_res = j;
			}
		}
		if (joueur_res == null) {
			throw new JoueurNonPresentException("Le joueur ne fait pas partie de l'équipe");
		}
		this.equipe.remove(joueur);
	}

	public int getNombreJoueurs() {
		return this.equipe.size();
	}

	public int getPoint() {
		return point;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Equipe) {
			Equipe e = (Equipe) o;
			return this.getNom().equals(e.getNom());
		}
		return false;
	}

	@Override
	public String toString() {
		return "Equipe [nom=" + nom + ", point=" + point + ", pays=" + pays + "]";
	}
}

