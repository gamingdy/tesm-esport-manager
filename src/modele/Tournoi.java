package modele;

//import java.util.HashMap;
//import java.util.Map;

import exceptions.FausseDate;

public class Tournoi {

	private Saison saison;
	private String nom;
	private CustomDate debut;
	private CustomDate fin;
	private Niveau niveau;
	private boolean estEncours;
	private CompteArbitre compteArbitre;
	//private Map<Character,Poule> poules;

	public Tournoi(Saison saison, String nom, CustomDate debut, CustomDate fin, Niveau niveau, CompteArbitre compteArbitre) throws FausseDate {
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
		this.estEncours = false;
		this.compteArbitre = compteArbitre;
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



	/*public Map<Character, Poule> getPoules() {
		return poules;
	}

	public void addPoule(Poule poule) {
		this.poules.put(poule.getNom(), poule);
	}

	public void setPoules(Map<Character, Poule> poules) {
		this.poules = poules;
	}*/
}
