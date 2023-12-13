package modele;

import exceptions.FausseDateException;
import exceptions.IdNotSetException;
import exceptions.MemeEquipeException;

import java.sql.SQLException;
import java.util.Objects;

import dao.Connexion;
import dao.DaoMatche;

public class Matche {

	private Integer id;
	private CustomDate dateDebutMatche;
	private int nombreMaxParties;
	private Categorie categorie;
	private Equipe equipe1;
	private Equipe equipe2;
	private Tournoi tournoi;
	private Equipe vainqueur;
	private Saison saison;


	private Matche(int nombreMaxParties, CustomDate dateDebutMatche, Categorie categorie,
				  Equipe equipe1, Equipe equipe2, Tournoi tournoi, Integer id) throws FausseDateException, MemeEquipeException {

		if (dateDebutMatche == null || categorie == null || equipe1 == null || equipe2 == null || tournoi == null) {
			throw new IllegalArgumentException("Un des paramètres est null");
		}

		if (Objects.equals(equipe1.getNom(), equipe2.getNom())) {
			throw new MemeEquipeException("Les 2 équipes sont identiques");
		}

		if (dateDebutMatche.estAvant(tournoi.getDebut())) {
			throw new FausseDateException("La date de début du matche est avant la date de début du tournoi");
		}

		if (nombreMaxParties < 1) {
			throw new IllegalArgumentException("Le nombre de parties doit être supérieur à 0");
		}

		this.nombreMaxParties = nombreMaxParties;
		this.dateDebutMatche = dateDebutMatche;
		this.categorie = categorie;
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
		this.tournoi = tournoi;
		this.vainqueur = null;
		this.saison = tournoi.getSaison();
		
		if (id.equals(null)) {
			this.id=0;
		} else {
			this.id = id+1;
		}
	}
	
	public static Matche createMatche(int nombreMaxParties, CustomDate dateDebutMatche, Categorie categorie,
			Equipe equipe1, Equipe equipe2, Tournoi tournoi, Integer id) throws FausseDateException, MemeEquipeException, SQLException {
		
		DaoMatche daomatche = new DaoMatche(Connexion.getConnexion());
		return new Matche(nombreMaxParties, dateDebutMatche, categorie,
				equipe1, equipe2, tournoi, daomatche.getLastId());
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

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
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
		return this.vainqueur;
	}

	public void setVainqueur(Equipe vainqueur) {
		this.vainqueur = vainqueur;
	}

	public Saison getSaison() {
		return saison;
	}

	public Integer getId() throws IdNotSetException {
		if (this.id == null) {
			throw new IdNotSetException("le id du Matche n'est pas set");
		}

		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tournoi getTournoi() {
		return tournoi;
	}

	@Override
	public String toString() {
		return "Matche [id=" + id + ", dateDebutMatche=" + dateDebutMatche + ", nombreMaxParties=" + nombreMaxParties
				+ ", libelle=" + categorie + ", equipe1=" + equipe1 + ", equipe2=" + equipe2 + ", tournoi=" + tournoi
				+ ", vainqueur=" + vainqueur + ", saison=" + saison + "]";
	}

}
