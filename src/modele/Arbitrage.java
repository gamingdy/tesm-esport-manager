package modele;

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

	public void setArbitre(Arbitre arbitre) {
		this.arbitre = arbitre;
	}

	public Tournoi getTournoi() {
		return tournoi;
	}

	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
	}

	@Override
	public String toString() {
		return "Arbitrage [arbitre=" + arbitre + ", tournoi=" + tournoi + "]";
	}
}
