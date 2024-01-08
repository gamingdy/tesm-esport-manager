package modele;

import java.util.Objects;
import exceptions.IdNotSetException;

public class Arbitre implements Comparable<Arbitre> {
	private Integer id;
	private String nom;
	private String prenom;
	private Integer numeroTelephone;
	
	public Arbitre(String nom, String prenom, Integer numeroTelephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.numeroTelephone = numeroTelephone;
	}

	public String getNom() {
		return nom;
	}


	public String getPrenom() {
		return prenom;
	}
	
	@Override
	public int compareTo(Arbitre autreArbitre) {
		if (this == autreArbitre) {
			return 0;
		} else {
			return this.toString().compareTo(autreArbitre.toString());
		}
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

	@Override
	public String toString() {
		return "Arbitre [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Integer getNumeroTelephone() {
		return numeroTelephone;
	}

	public void setNumeroTelephone(Integer numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom, numeroTelephone, prenom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arbitre other = (Arbitre) obj;
		return Objects.equals(nom, other.nom) && Objects.equals(numeroTelephone, other.numeroTelephone)
				&& Objects.equals(prenom, other.prenom);
	}
}
