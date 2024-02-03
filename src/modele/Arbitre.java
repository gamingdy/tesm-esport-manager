package modele;

import java.util.Objects;

public class Arbitre implements Comparable<Arbitre> {
	
	private String nom;
	private String prenom;
	private String numeroTelephone;
	
	public Arbitre(String nom, String prenom, String numeroTelephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.numeroTelephone=numeroTelephone;
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

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	

	@Override
	public String toString() {
		return "Arbitre [nom=" + nom + ", prenom=" + prenom + ", numeroTelephone=" + numeroTelephone + "]";
	}

	public String getNumeroTelephone() {
		return numeroTelephone;
	}

	public void setNumeroTelephone(String numeroTelephone) {
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
