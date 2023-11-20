package modele;

//import java.util.HashMap;
//import java.util.Map;
import java.util.Set;
import java.util.HashSet;


import exceptions.FausseDate;
import exceptions.PointsNegatifs;

public class Tournoi {

	private Saison saison;
	private String nom;
	private CustomDate debut;
	private CustomDate fin;
	private Niveau niveau;
	private Set<Equipe> equipes;
	private Set<Matche> matches;
	private boolean estEncours;
	//private Map<Character,Poule> poules;

	public Tournoi(Saison saison, String nom, CustomDate debut, CustomDate fin, Niveau niveau) throws FausseDate {
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
		this.equipes = new HashSet<Equipe>();
		this.estEncours = false;
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

	public Set<Equipe> getEquipes() {
		return equipes;
	}

	public void addEquipe(Equipe equipe) {
		this.equipes.add(equipe);
	}

	public void removeEquipe(Equipe equipe) {
		this.equipes.remove(equipe);
	}

	public void setEquipes(Set<Equipe> equipes) {
		this.equipes = equipes;
	}

	public Set<Matche> getMatches() {
		return matches;
	}

	public void addMatche(Matche matche) {
		this.matches.add(matche);
	}

	public void removeMatche(Matche matche) {
		this.matches.remove(matche);
	}

	public void setMatches(Set<Matche> matches) {
		this.matches = matches;
	}

	public void assignerPointFinTournoi() throws PointsNegatifs {
		for (Equipe equipe : this.equipes) {
			equipe.setPoints(equipe.getPoints() + this.getPointsFinTournoi(equipe));
		}
	}

	public int getPointsFinTournoi(Equipe equipe){
		
		return 0;
	}

	public boolean isEstEncours() {
		return estEncours;
	}

	public void setEstEncours(boolean estEncours) {
		this.estEncours = estEncours;
	}
}
