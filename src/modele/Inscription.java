package modele;

import java.util.Objects;

public class Inscription {
	
	private String nom;
	private short annee;
	
	public Inscription(short annee, String nom) {
		this.nom = nom;
		this.annee = annee;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public short getAnnee() {
		return annee;
	}

	public void setAnnee(short annee) {
		this.annee = annee;
	}

}
