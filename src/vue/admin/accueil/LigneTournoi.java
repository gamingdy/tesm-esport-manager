package vue.admin.accueil;

public class LigneTournoi {
	
	private String nom;
	private boolean enCours;
	
	/**
	 * @param le nom du tournoi
	 * @param si le tournoi est en cours
	 * @return la ligne du tournoi
	 */
	public LigneTournoi(String nom, boolean enCours) {
		this.nom = nom;
		this.enCours = enCours;
	}

	/**
	 * @return le nom du tournoi
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return si le tournoi est en cours
	 */
	public boolean isEnCours() {
		return enCours;
	}

}
