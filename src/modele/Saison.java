package modele;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;

public class Saison {

	private short annee;
	private Map<Equipe,Integer> equipes;
	private Set<Arbitre> arbitres;
	
	public Saison(short annee) {
		this.equipes=new HashMap<Equipe,Integer>();
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
		//TODO Ajouter l'automatisation de la value (world rank)
		//Si l'équipe existait à la saison d'avant Alors
		//world rank = rank de la saison précédente
		//Sinon
		//world rank = 1000
		this.equipes.put(equipe,1000);
	}
	
	public void deleteEquipe(Equipe equipe) {
		this.equipes.remove(equipe);
	}

	public Set<Equipe> getEquipes() {
		return this.equipes.keySet();
	}

	@Override
	public String toString(){
		String str = "";
		for (Equipe e : this.getEquipes()){
			str += e.getNom() + " : " + this.equipes.get(e);
			str += System.lineSeparator();
		}
		return str;
	}
	
}
