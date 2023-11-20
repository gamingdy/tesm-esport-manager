package modele;

import exceptions.EquipeInexistante;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;

public class Saison {

	private int annee;

	public Saison(int annee) {
		this.annee = annee;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}
}
