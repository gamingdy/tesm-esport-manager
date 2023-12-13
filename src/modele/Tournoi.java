package modele;

import java.util.Objects;

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
	//TODO à supprimer ?
	//private Map<Character,Poule> poules;

	public Tournoi(Saison saison, String nom, CustomDate debut, CustomDate fin, Niveau niveau, CompteArbitre compteArbitre) throws FausseDateException {
		if (debut.getAnnee() < saison.getAnnee()) {
			throw new FausseDateException("La date de début du tournoi est avant la date de début de la saison");
		}
		if (fin.getAnnee() > saison.getAnnee()) {
			throw new FausseDateException("La date de fin du tournoi est après la date de fin de la saison");
		}
		if (debut.estApres(fin)) {
			throw new FausseDateException("La date de fin du tournoi est avant la date de début du tournoi");
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


	/* TODO à supprimer ?
	public Map<Character, Poule> getPoules() {
		return poules;
	}

	public void addPoule(Poule poule) {
		this.poules.put(poule.getNom(), poule);
	}

	public void setPoules(Map<Character, Poule> poules) {
		this.poules = poules;
	}*/
}
