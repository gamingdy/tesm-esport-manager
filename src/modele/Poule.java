package modele;

import java.util.List;

public class Poule {

	private final Tournoi tournoi;
	private final Character libelle;
	private List<Equipe> equipes;

	public Poule(Tournoi tournoi, Character libelle) {
		this.tournoi = tournoi;
		this.libelle = libelle;
	}

	public Tournoi getTournoi() {
		return tournoi;
	}

	public Character getLibelle() {
		return libelle;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void addEquipe(Equipe equipe) {
		this.equipes.add(equipe);
	}

	@Override
	public String toString() {
		return "Poule [tournoi=" + tournoi + ", libelle=" + libelle + "]";
	}

}