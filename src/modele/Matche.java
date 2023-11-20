package modele;

import exceptions.FausseDate;
import exceptions.MemeEquipe;

import java.util.Objects;

public class Matche {

	private CustomDate dateDebutMatche;
	private int nombreMaxParties;
	private Categorie libelle;
	private Equipe equipe1;
	private Equipe equipe2;
	private Tournoi tournoi;
	private int vainqueur;
	private Saison saison;


	public Matche(int nombreMaxParties, CustomDate dateDebutMatche, Categorie libelle,
				  Equipe equipe1, Equipe equipe2, Tournoi tournoi) throws FausseDate, MemeEquipe {
		if (dateDebutMatche.estAvant(tournoi.getDebut())) {
			throw new FausseDate("La date de début du matche est avant la date de début du tournoi");
		}
		if (Objects.equals(equipe1.getNom(), equipe2.getNom())) {
			throw new MemeEquipe("Les 2 équipes sont identiques");
		}
		this.nombreMaxParties = nombreMaxParties;
		this.dateDebutMatche = dateDebutMatche;
		this.libelle = libelle;
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
		this.tournoi = tournoi;
		this.vainqueur = 0;
		this.saison = tournoi.getSaison();
	}


	public int getNombreMaxParties() {
		return nombreMaxParties;
	}

	public void setNombreMaxParties(int nombreMaxParties) {
		this.nombreMaxParties = nombreMaxParties;
	}

	public CustomDate getDateDebutMatche() {
		return dateDebutMatche;
	}

	public void setDateDebutMatche(CustomDate dateDebutMatche) {
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

	public void setVainqueur(Equipe vainqueur) {
		if (vainqueur.getNom() == this.equipe1.getNom()) {
			this.vainqueur = 1;
		}
		if (vainqueur.getNom() == this.equipe2.getNom()) {
			this.vainqueur = 2;
		}
	}

	public int getVainqueurInt() {
		return vainqueur;
	}

	public void setVainqueurInt(int vainqueur) {
		this.vainqueur = vainqueur;
	}
}
