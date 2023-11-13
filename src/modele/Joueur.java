package modele;

import java.util.Objects;

public class Joueur {
	
	private int id;
	private String pseudo;
	private String nom;
	
	public Joueur(int id, String pseudo, String nom) {
		this.id = id;
		this.pseudo = pseudo;
		this.nom = nom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, nom, pseudo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		return id == other.id && Objects.equals(nom, other.nom) && Objects.equals(pseudo, other.pseudo);
	}

}
