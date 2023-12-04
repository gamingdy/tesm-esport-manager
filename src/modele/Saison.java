package modele;

import exceptions.EquipeInexistante;
import exceptions.FausseDate;
import exceptions.PointsNegatifs;

import java.util.*;

public class Saison {

	private int annee;
	private Map<Equipe, Integer> equipes;
	private Set<Arbitre> arbitres;
	private Set<Tournoi> tournois;

	public Saison(int annee) {
		this.equipes = new HashMap<Equipe, Integer>();
		this.arbitres = new TreeSet<Arbitre>();
		this.tournois = new HashSet<Tournoi>();
		this.annee = annee;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public void addArbitre(Arbitre arbitre) {
		this.arbitres.add(arbitre);
	}

	public void deleteArbitre(Arbitre arbitre) {
		this.arbitres.remove(arbitre);
	}

	public Set<Arbitre> getArbitres() {
		return arbitres;
	}

	public void addEquipe(Equipe equipe) {
		//TODO Ajouter l'automatisation de la value (world rank)
		//Si l'équipe existait à la saison d'avant Alors
		//world rank = rank de la saison précédente
		//Sinon
		//world rank = 1000
		this.equipes.put(equipe, 1000);
	}

	public void deleteEquipe(Equipe equipe) throws EquipeInexistante {
		if (!equipes.containsKey(equipe)) {
			throw new EquipeInexistante("Cette équipe n'est pas dans la saison");
		}
		this.equipes.remove(equipe);
	}

	public Set<Equipe> getEquipes() {
		return this.equipes.keySet();
	}

	@Override
	public String toString() {
		return "Saison [annee=" + annee	+ "]";
	}

	public void addTournoi(Tournoi tournoi) {
		this.tournois.add(tournoi);
	}

	public void deleteTournoi(Tournoi tournoi) {
		this.tournois.remove(tournoi);
	}

	public Set<Tournoi> getTournois() {
		return tournois;
	}

	public Tournoi getTournoi(String nom) {
		Tournoi tournoi = null;
		for (Tournoi t : this.tournois) {
			if (t.getNom() == nom) {
				tournoi = t;
			}
		}
		return tournoi;
	}

	public void SaisonFinie() throws FausseDate{
		//TODO Supprimer toutes les informations à ne pas garder de la saison (this)
	}

	public Saison NouvelleSaison() throws PointsNegatifs{
		Saison NouvelleSaison = new Saison(this.annee+1);
		for (Equipe e : this.equipes.keySet()) {
			Equipe equipe = new Equipe(e.getNom(), e.getPays());
			NouvelleSaison.addEquipe(equipe, e.getPoint());
		}
		for (Tournoi t : this.tournois) {
			//TODO Faire l'ajout des tournois avec seulement équipe, niveau et points pour chaque équipe
			//Pas besoin des dates ou du reste :
			// - Seuls le nombre de points remportés par chaque équipe par tournoi et son classement annuel sont conservés d’une année sur l’autre.
		}
		return NouvelleSaison;
	}
}
