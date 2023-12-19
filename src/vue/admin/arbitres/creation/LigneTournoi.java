package vue.admin.arbitres.creation;

public class LigneTournoi {

	private String nom;
	private String dateDébut;
	private String dateFin;
	/**
	 * @param nom
	 * @param dateDébut
	 * @param dateFin
	 */
	public LigneTournoi(String nom, String dateDébut, String dateFin) {
		this.nom = nom;
		this.dateDébut = dateDébut;
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
	public String getDateDébut() {
		return dateDébut;
	}
	/**
	 * @return the dateFin
	 */
	public String getDateFin() {
		return dateFin;
	}
	
}
