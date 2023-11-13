package modele;

import java.time.LocalDate;

//import java.util.HashMap;
//import java.util.Map;


import exceptions.FausseDate;

public class Tournoi {

	private Saison saison;
	private String nom;
	private Custom_Date debut;
	private Custom_Date fin;
	private Niveau niveau;
	//private Map<Character,Poule> poules;

	public Tournoi(Saison saison, String nom, Custom_Date debut, Custom_Date fin, Niveau niveau) throws FausseDate {
		if (debut.getAnnee() < saison.getAnnee()) {
			throw new FausseDate("La date de début du tournoi est avant la date de début de la saison");
		}
		if (fin.getAnnee() > saison.getAnnee()) {
			throw new FausseDate("La date de fin du tournoi est après la date de fin de la saison");
		}
		this.saison = saison;
		this.nom = nom;
		this.debut = debut;
		this.fin = fin;
		this.niveau = niveau;
		//this.poules = new HashMap<Character,Poule>();
	}

	public Saison getSaison() {
		return saison;
	}

	public void setSaison(Saison saison) {
		this.saison = saison;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Custom_Date getDebut() {
		return debut;
	}

	public void setDebut(Custom_Date debut) {
		this.debut = debut;
	}

	public Custom_Date getFin() {
		return fin;
	}

	public void setFin(Custom_Date fin) {
		this.fin = fin;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}


}
