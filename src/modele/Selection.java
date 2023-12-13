package modele;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Selection {
	
	private Arbitre arbitre;
	private Saison saison;
	//TODO à retirer
	private Set<Selection> selections;
	
	public Selection(Arbitre arbitre, Saison saison) {
		this.arbitre = arbitre;
		this.saison = saison;
		this.selections = new HashSet<>();
	}
	
	public void addSelection(Selection selection) {
		this.selections.add(selection);
	}
	
	public void deleteSelection(Selection selection) {
		this.selections.remove(selection);
	}
	
	public Arbitre getArbitre() {
		return arbitre;
	}

	public Saison getSaison() {
		return saison;
	}

	public Set<Selection> getSelections() {
		return selections;
	}

	@Override
	public int hashCode() {
		return Objects.hash(arbitre, saison);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Selection other = (Selection) obj;
		return Objects.equals(arbitre, other.arbitre) && Objects.equals(saison, other.saison);
	}
	

	

}
