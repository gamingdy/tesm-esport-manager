package modele;

import java.util.Objects;

public class Inscription {

	private Equipe equipe;
	private Saison saison;
	private Integer worldRank;

	public Inscription(Saison saison, Equipe equipe) {
		this(saison, 1000, equipe);
	}

	public Inscription(Saison saison, Integer worldRank, Equipe equipe) {
		this.equipe = equipe;
		this.worldRank = worldRank;
		this.saison = saison;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Saison getSaison() {
		return saison;
	}

	public void setSaison(Saison saison) {
		this.saison = saison;
	}

	public Integer getWorldRank() {
		return worldRank;
	}

	public void setWorldRank(Integer worldRank) {
		this.worldRank = worldRank;
	}

	@Override
	public int hashCode() {
		return Objects.hash(equipe, saison);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inscription other = (Inscription) obj;
		return this.toString().equals(other.toString());
	}

	@Override
	public String toString() {
		return "Inscription [equipe=" + equipe + ", saison=" + saison + ", worldRank=" + worldRank + "]";
	}


}
