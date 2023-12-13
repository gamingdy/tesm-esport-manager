package modele;

import exceptions.IdNotSetException;

public class Arbitre implements Comparable<Arbitre> {
	private Integer id;
	private String nom;
	private String prenom;

	public Arbitre(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	@Override
	public String toString() {
		return this.getNom() + " " + this.getPrenom();
	}

	@Override
	public int compareTo(Arbitre autreArbitre) {
		if (this == autreArbitre) {
			return 0;
		} else {
			return this.toString().compareTo(autreArbitre.toString());
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Arbitre) {
			return this.toString().equals(o.toString());
		}
		return false;
	}

	public Integer getId() throws IdNotSetException {
		if (this.id == null) {
			throw new IdNotSetException("le id de l'objet n'est pas set");
		}
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
