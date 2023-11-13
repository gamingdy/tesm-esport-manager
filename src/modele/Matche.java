package modele;

import java.sql.Date;

public class Matche {

	private int id;
	private byte nombreMaxParties;
	private Date dateDebutMatche;
	private Date dateFinMatche;
	private Categorie libelle;
	private Equipe equipe1;
	private Equipe equipe2;
	private Tournoi tournoi;
	private byte vainqueur;

	public Matche(int id, byte nombreMaxParties, Date dateDebutMatche, Date dateFinMatche, Categorie libelle,
				  Equipe equipe1, Equipe equipe2, Tournoi tournoi) {
		this.id = id;
		this.nombreMaxParties = nombreMaxParties;
		this.dateDebutMatche = dateDebutMatche;
		this.dateFinMatche = dateFinMatche;
		this.libelle = libelle;
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
		this.tournoi = tournoi;
		this.vainqueur = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getNombreMaxParties() {
		return nombreMaxParties;
	}

	public void setNombreMaxParties(byte nombreMaxParties) {
		this.nombreMaxParties = nombreMaxParties;
	}

	public Date getDateDebutMatche() {
		return dateDebutMatche;
	}

	public void setDateDebutMatche(Date dateDebutMatche) {
		this.dateDebutMatche = dateDebutMatche;
	}

	public Date getDateFinMatche() {
		return dateFinMatche;
	}

	public void setDateFinMatche(Date dateFinMatche) {
		this.dateFinMatche = dateFinMatche;
	}

	public Categorie getLibelle() {
		return libelle;
	}

	public void setLibelle(Categorie libelle) {
		this.libelle = libelle;
	}

	public Equipe getEquipe1() {
		return equipe1;
	}

	public void setEquipe1(Equipe equipe1) {
		this.equipe1 = equipe1;
	}

	public Equipe getEquipe2() {
		return equipe2;
	}

	public void setEquipe2(Equipe equipe2) {
		this.equipe2 = equipe2;
	}

	public Tournoi getTournoi() {
		return tournoi;
	}

	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
	}

	public Equipe getVainqueur() {
		Equipe equipe = null;
		if (this.vainqueur == 1) {
			equipe = this.equipe1;
		}
		if (this.vainqueur == 2) {
			equipe = this.equipe2;
		}
		return equipe;
	}

	public void setVainqueur(byte vainqueur) {
		this.vainqueur = vainqueur;
	}
	
}
