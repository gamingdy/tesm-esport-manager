package modele;


import exceptions.EquipeComplete;
import exceptions.ErreurJoueur;
import exceptions.idNotSet;

public class Joueur implements Comparable<Joueur> {
	
	private Integer id;
	private String pseudo;
	private Equipe equipe;

	public Joueur(String pseudo, Equipe equipe) throws EquipeComplete, ErreurJoueur{
		this.pseudo = pseudo;
		this.equipe = equipe;
		this.equipe.addJoueur(this);
	}

	public Joueur(String pseudo) {
		this.pseudo = pseudo;
		this.equipe = null;
	}

	public void setId(Integer newId) {
		this.id = newId;
	}

	public Integer getId() throws idNotSet {
		if (this.id == null) {
			throw new idNotSet("le id de ce Joueur n'est pas set");
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

	public void setEquipe(Equipe equipe) throws EquipeComplete, ErreurJoueur {
		if (this.equipe != null) {
			throw new ErreurJoueur("Ce joueur est déjà dans une équipe");
		}
		this.equipe = equipe;
		this.equipe.addJoueur(this);
	}

	public int compareTo(Joueur j) {
		return j.getPseudo().compareTo(this.getPseudo());
	}

	@Override
	public int hashCode() {
		return this.pseudo.hashCode();
	}
}
