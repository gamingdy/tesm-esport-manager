package vue.admin.arbitres.creation;

public class LigneTournoi {

	private String nom;
	private String dateDebut;
	private String dateFin;
	/**
	 * @param nom
	 * @param dateDebut
	 * @param dateFin
	 */
	public LigneTournoi(String nom, String dateDebut, String dateFin) {
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @return the dateDébut
	 */
	public String getDateDebut() {
		return dateDebut;
	}
	/**
	 * @return the dateFin
	 */
	public String getDateFin() {
		return dateFin;
	}
	
}
