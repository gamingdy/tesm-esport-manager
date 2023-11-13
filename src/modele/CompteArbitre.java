package modele;

public class CompteArbitre extends CompteUtilisateur{
	
	private Arbitre arbitre;
	private Tournoi tournoi;

	public CompteArbitre(String username, String mdp, Arbitre arbitre, Tournoi tournoi) {
		super(username, mdp);
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

}
