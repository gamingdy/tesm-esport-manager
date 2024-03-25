package modele;

import exceptions.EquipeCompleteException;
import exceptions.EquipeVideException;
import exceptions.ExceptionPointsNegatifs;
import exceptions.JoueurNonPresentException;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Equipe {

	private String nom;
	private Set<Joueur> listJoueur; //à voir si on laisse ou créer une classe association
	private Float point; //calculable donc pas dans le MCDi
	private Pays pays;


	public Equipe(String nom, Pays pays) {
		this.listJoueur = new LinkedHashSet<>();
		this.nom = nom;
		this.point = 0F;
		this.pays = pays;
	}

	public Pays getPays() {
		return this.pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public Set<Joueur> getEquipe() {
		return listJoueur;
	}

	public String getNom() {
		return this.nom;
	}

	public void setPoint(Float point) throws ExceptionPointsNegatifs {
		if (point < 0) {
			throw new ExceptionPointsNegatifs("On ne peut pas mettre des points negatifs");
		}
		this.point = point;
	}

	public void addJoueur(Joueur joueur) throws EquipeCompleteException {
		if (this.listJoueur.size() == 5) {
			throw new EquipeCompleteException("L'équipe est pleine");
		}
		this.listJoueur.add(joueur);
	}

	public Joueur getJoueur(Joueur joueur) throws JoueurNonPresentException, EquipeVideException {
		if (this.listJoueur.isEmpty()) {
			throw new EquipeVideException("L'équipe est vide");
		}
		for (Joueur j : this.listJoueur) {
			if (j.equals(joueur)) {
				return j;
			}
		}
		throw new JoueurNonPresentException("Le joueur ne fait pas partie de l'équipe");
	}

	public void deleteJoueur(Joueur joueur) throws JoueurNonPresentException, EquipeVideException {
		if (this.listJoueur.isEmpty()) {
			throw new EquipeVideException("L'équipe est vide");
		}
		Joueur joueurRes = null;
		for (Joueur j : this.listJoueur) {
			if (j.equals(joueur)) {
				joueurRes = j;
			}
		}
		if (joueurRes == null) {
			throw new JoueurNonPresentException("Le joueur ne fait pas partie de l'équipe");
		}
		this.listJoueur.remove(joueur);
	}

	public int getNombreJoueurs() {
		return this.listJoueur.size();
	}

	public Float getPoint() {
		return point;
	}


	@Override
	public String toString() {
		return "Equipe [nom=" + nom + ", point=" + point + ", pays=" + pays + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipe other = (Equipe) obj;
		return Objects.equals(nom, other.nom);
	}
}

