package modele;

<<<<<<< HEAD
import exceptions.EquipeInexistanteException;
import exceptions.FausseDateException;
import exceptions.ExceptionPointsNegatifs;
=======
import exceptions.EquipeInexistante;
>>>>>>> parent of 8dd3f58 (Début Nouvelle Saison)

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

	public void addEquipe(Equipe equipe, Integer rank) {
		if (rank.equals(null)) {
			this.equipes.put(equipe, rank);
		} else {
			this.equipes.put(equipe, 1000);
		}
	}

	public void addEquipe(Equipe equipe) {
		this.equipes.put(equipe, 1000);
	}

	public void deleteEquipe(Equipe equipe) throws EquipeInexistanteException {
		if (!equipes.containsKey(equipe)) {
			throw new EquipeInexistanteException("Cette équipe n'est pas dans la saison");
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
<<<<<<< HEAD

	public void SaisonFinie() throws FausseDateException{
		//TODO Supprimer toutes les informations à ne pas garder de la saison (this)
	}

	public Saison NouvelleSaison() throws ExceptionPointsNegatifs, FausseDateException{
		Saison NouvelleSaison = new Saison(this.annee+1);
		for (Equipe e : this.equipes.keySet()) {
			Equipe equipe = new Equipe(e.getNom(), e.getPays());
			NouvelleSaison.addEquipe(equipe, e.getPoint());
		}
		for (Tournoi t : this.tournois) {
			Tournoi tournoi = new Tournoi(this, t.getNom(), t.getDebut(), t.getFin(), t.getNiveau(), t.getCompteArbitre());
			//TODO Faire l'ajout des tournois avec seulement équipes, niveau et points pour chaque équipe
			//Pas besoin des dates ou du reste :
			// - Seuls le nombre de points remportés par chaque équipe par tournoi et son classement annuel sont conservés d’une année sur l’autre.
		}
		return NouvelleSaison;
	}
=======
>>>>>>> parent of 8dd3f58 (Début Nouvelle Saison)
}
