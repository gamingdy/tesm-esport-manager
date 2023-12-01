package modele;


import exceptions.EquipeComplete;
import exceptions.ErreurJoueur;
import exceptions.IdNotSet;

public class Joueur implements Comparable<Joueur> {
	
	private Integer id;
	private String pseudo;
	private Equipe equipe;

	public Joueur(String pseudo, Equipe equipe) throws EquipeComplete, ErreurJoueur{
		this.pseudo = pseudo;
		this.equipe = equipe;
		this.equipe.addJoueur(this);
	}

	public void setId(Integer newId) {
		this.id = newId;
	}

	public Integer getId() throws IdNotSet {
		if (this.id == null) {
			throw new IdNotSet("le id de ce Joueur n'est pas set");
		}
		return this.id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNomEquipe() {
		return this.equipe.getNom();
	}

	public Equipe getEquipe() {
		return this.equipe;
	}


	public int compareTo(Joueur j) {
		return j.getPseudo().compareTo(this.getPseudo());
	}

	@Override
	public int hashCode() {
		return this.pseudo.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Joueur) {
			Joueur j = (Joueur) o;
			return this.pseudo.equals(j.getPseudo());
		}
		return false;
	}

	@Override
	public String toString() {
		return "Joueur [id=" + id + ", pseudo=" + pseudo + ", equipe=" + equipe + "]";
	}
}
