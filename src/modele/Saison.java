package modele;

import java.util.Set;
import java.util.TreeSet;

public class Saison {

	private short annee;
	private Set<Equipe> equipes;
	private Set<Arbitre> arbitres;
	
	public Saison(short annee) {
		this.equipes=new TreeSet<Equipe>();
		this.arbitres=new TreeSet<Arbitre>();
		this.annee = annee;
		
	}

	public short getAnnee() {
		return annee;
	}

	public void setAnnee(short annee) {
		this.annee = annee;
	}
	
	public void addArbitre(Arbitre arbitre) {
		this.arbitres.add(arbitre);
	}
	
	public void deleteArbitre(Arbitre arbitre) {
		this.arbitres.remove(arbitre);
	}

	public Set<Arbitre> getArbitre() {
		return arbitres;
	}
	
	public void addEquipe(Equipe equipe) {
		this.equipes.add(equipe);
	}
	
	public void deleteEquipe(Equipe equipe) {
		this.equipes.remove(equipe);
	}

	public Set<Equipe> getEquipes() {
		return equipes;
	}

	
	
}
