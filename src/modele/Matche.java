package modele;

import java.sql.Date;

import exceptions.FausseDate;
import exceptions.MemeEquipe;

public class Matche {

	private int nombreMaxParties;

	private final int id;

	private Date dateDebutMatche;
	private Categorie libelle;
	private Equipe equipe1;
	private Equipe equipe2;
	private Tournoi tournoi;
	private int vainqueur;


	public Matche(int id, int nombreMaxParties, Date dateDebutMatche, Categorie libelle,
				  Equipe equipe1, Equipe equipe2, Tournoi tournoi) throws FausseDate, MemeEquipe {
		if (dateDebutMatche.before(tournoi.getDebut())) {
			throw new FausseDate("La date de début du matche est avant la date de début du tournoi");
		}
		if (equipe1.getNom() == equipe2.getNom()) {
			throw new MemeEquipe("Les 2 équipes sont identiques");
		}

		this.id = id;
		this.nombreMaxParties = nombreMaxParties;
		this.dateDebutMatche = dateDebutMatche;
		this.libelle = libelle;
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
		this.tournoi = tournoi;
		this.vainqueur = 0;
	}

	public int getId() {
		return id;
	}


	public int getNombreMaxParties() {
		return nombreMaxParties;
	}

	public void setNombreMaxParties(int nombreMaxParties) {
		this.nombreMaxParties = nombreMaxParties;
	}

	public Date getDateDebutMatche() {
		return dateDebutMatche;
	}

	public void setDateDebutMatche(Date dateDebutMatche) {
		this.dateDebutMatche = dateDebutMatche;
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

	public void setVainqueur(int vainqueur) {
		this.vainqueur = vainqueur;
	}
}
