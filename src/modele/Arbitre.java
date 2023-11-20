package modele;

import exceptions.idNotSet;

public class Arbitre implements Comparable<Arbitre> {

	private int id;
	private String nom;
	private String prenom;

	public Arbitre(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		this.id = -1;
	}

	public void setId(int newid) {
		this.id = newid;
	}

	public int getId() throws idNotSet {
		if (id == -1) {
			throw new idNotSet("Le id de l'objet n'est pas set");
		}
		return id;
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
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public String toString() {
		try {
			return "(" + this.getId() + ")" + this.getNom() + " " + this.getPrenom();
		} catch (idNotSet e) {
			throw new RuntimeException(e);
		}
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
		if (o == null) {
			return false;
		}
		if (!(o instanceof Arbitre)) {
			return false;
		}
		Arbitre autreArbitre = (Arbitre) o;
		return this.id == autreArbitre.id;
	}
}
