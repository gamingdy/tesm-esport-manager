package modele;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Arbitrage {

	private Arbitre arbitre;
	private Tournoi tournoi;
	
	public Arbitrage(Arbitre arbitre, Tournoi tournoi) {
		this.arbitre=arbitre;
		this.tournoi=tournoi;
	}

	public Arbitre getArbitre() {
		return arbitre;
	}
	public Tournoi getTournoi() {
		return tournoi;
	}

	@Override
	public String toString() {
		return "Arbitrage [arbitre=" + arbitre + ", tournoi=" + tournoi + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(arbitre, tournoi);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arbitrage other = (Arbitrage) obj;
		return Objects.equals(arbitre, other.arbitre) && Objects.equals(tournoi, other.tournoi);
	}
}
