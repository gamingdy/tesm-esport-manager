package modele;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

import dao.Connexion;
import dao.DaoArbitre;
import exceptions.IdNotSetException;

public class Arbitre implements Comparable<Arbitre> {
	private Integer id;
	private String nom;
	private String prenom;
	
	public Arbitre (String nom,String prenom) throws SQLException {
		this(nom,prenom,new DaoArbitre(Connexion.getConnexion()).getLastId());
	}

	private Arbitre(String nom, String prenom, Integer id) {
		this.nom = nom;
		this.prenom = prenom;
		if (id==null) {
			this.id=0;
		} else {
			this.id = id+1;
		}
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

	@Override
	public int hashCode() {
		return Objects.hash(id, nom, prenom);
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
		return Objects.equals(id, other.id) && Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom);
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
