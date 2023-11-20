package modele;


import exceptions.idNotSet;

public class Joueur implements Comparable<Joueur> {
	private String pseudo;
	private Integer id;

	public Joueur(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int compareTo(Joueur j) {
		return j.getPseudo().compareTo(this.getPseudo());
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

	@Override
	public int hashCode() {
		return this.pseudo.hashCode();
	}


}
