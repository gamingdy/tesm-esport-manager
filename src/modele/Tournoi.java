package modele;

import java.sql.Date;

public class Tournoi {
	
	private Saison annee;
	private String nom;
	private Date debut;
	private Date fin;
	private Niveau niveau;
	private String usernameArbitre;
	private String mdpArbitre;
	
	public Tournoi(Saison annee, String nom, Date debut, Date fin, Niveau niveau, String usernameArbitre, String mdpArbitre) {
		this.annee = annee;
		this.nom = nom;
		this.debut = debut;
		this.fin = fin;
		this.niveau = niveau;
		this.usernameArbitre = usernameArbitre;
		this.mdpArbitre = mdpArbitre;
	}
	
	public Saison getAnnee() {
		return annee;
	}
	public void setAnnee(Saison annee) {
		this.annee = annee;
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

	public String getUsernameArbitre() {
		return usernameArbitre;
	}

	public void setUsernameArbitre(String usernameArbitre) {
		this.usernameArbitre = usernameArbitre;
	}

	public String getMdpArbitre() {
		return mdpArbitre;
	}

	public void setMdpArbitre(String mdpArbitre) {
		this.mdpArbitre = mdpArbitre;
	}
	
	
	

}
