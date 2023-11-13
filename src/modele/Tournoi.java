package modele;

//import java.util.HashMap;
//import java.util.Map;

import java.time.LocalDate;
import java.util.Date;

import exceptions.FausseDate;

public class Tournoi {

	private Saison saison;
	private String nom;
	private LocalDate debut;
	private LocalDate fin;
	private Niveau niveau;
	//private Map<Character,Poule> poules;

	public Tournoi(Saison saison, String nom, LocalDate debut, LocalDate fin, Niveau niveau) throws FausseDate {
		if (debut.getYear() < saison.getAnnee()) {
			throw new FausseDate("La date de début du tournoi est avant la date de début de la saison");
		}
		if (fin.getYear() > saison.getAnnee()) {
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

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}


}
