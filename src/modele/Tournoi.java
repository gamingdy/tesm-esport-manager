package modele;

//import java.util.HashMap;
//import java.util.Map;
import java.sql.Date;

import exceptions.FausseDate;

public class Tournoi {
	
	private Saison saison;
	private String nom;
	private Date debut;
	private Date fin;
	private Niveau niveau;
	//private Map<Character,Poule> poules;
	
	public Tournoi(Saison saison, String nom, Date debut, Date fin, Niveau niveau)  throws FausseDate {
		if (debut.before(new Date(saison.getAnnee()-1900,1,1))) {
			throw new FausseDate("La date de début du tournoi est avant la date de début de la saison");
		}
		if (fin.after(new Date(saison.getAnnee() - 1900,1,1))) {
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
	public Date getDebut() {
		return debut;
	}
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public Niveau getNiveau() {
		return niveau;
	}
	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}
	
	
	

}
