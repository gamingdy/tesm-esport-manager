package modele;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//import java.util.HashMap;
//import java.util.Map;

import exceptions.FausseDateException;

public class Tournoi {

	private Saison saison;
	private String nom;
	private CustomDate debut;
	private CustomDate fin;
	private Niveau niveau;
	private boolean estEncours;
	private CompteArbitre compteArbitre;
	
	private Set<Poule> poules;
	private Set<Matche> matches;

	public Tournoi(Saison saison, String nom, CustomDate debut, CustomDate fin, Niveau niveau, CompteArbitre compteArbitre) throws FausseDateException {
		if (debut.getAnnee() < saison.getAnnee()) {
			throw new FausseDateException("La date de début du tournoi est avant la date de début de la saison");
		}
		if (fin.getAnnee() > saison.getAnnee()) {
			throw new FausseDateException("La date de fin du tournoi est après la date de fin de la saison");
		}
		this.saison = saison;
		this.nom = nom;
		this.debut = debut;
		this.fin = fin;
		this.niveau = niveau;

		this.compteArbitre = compteArbitre;
		if (debut.estAvant(CustomDate.now()) && fin.estApres(CustomDate.now())) {
			this.estEncours = true;
		} else {
			this.estEncours = false;
		}
		
		this.matches = new HashSet<>();
		this.poules = new HashSet<>();
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

	public CustomDate getDebut() {
		return debut;
	}

	public void setDebut(CustomDate debut) {
		this.debut = debut;
	}

	public CustomDate getFin() {
		return fin;
	}

	public void setFin(CustomDate fin) {
		this.fin = fin;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public boolean isEstEncours() {
		return estEncours;
	}

	public void setEstEncours(boolean estEncours) {
		this.estEncours = estEncours;
	}

	public CompteArbitre getCompteArbitre() {
		return compteArbitre;
	}

	@Override
	public String toString() {
		return "Tournoi [saison=" + saison + ", nom=" + nom + ", debut=" + debut + ", fin=" + fin + ", niveau=" + niveau
				+ ", estEncours=" + estEncours + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom, saison);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tournoi other = (Tournoi) obj;
		return Objects.equals(nom, other.nom) && Objects.equals(saison, other.saison);
	}

	public void addPoules(Poule poule) {
		this.poules.add(poule);
	}
	
	public void deletePoules(Poule poule) {
		this.poules.remove(poule);
	}
	
	public void addMatche(Matche matche) {
		this.matches.add(matche);
	}
	
	public void deleteMatche(Matche matche) {
		this.matches.remove(matche);
	}
}
