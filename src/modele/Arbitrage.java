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
	public Tournoi getTournoi() {
		return tournoi;
	}

}
