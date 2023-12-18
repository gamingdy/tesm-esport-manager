package vue.admin.accueil;

import javax.swing.ImageIcon;

/**
 * @author Titouan
 */
public class LigneMatche {

	private String dateHeure;
	private ImageIcon imageEquipe1;
	private String nomEquipe1;
	private ImageIcon trophéeEquipe1;
	private ImageIcon imageEquipe2;
	private String nomEquipe2;
	private ImageIcon trophéeEquipe2;

	/**
	 * @param la      date et l'heure du tournoi (en String)
	 * @param l'image de la première équipe
	 * @param le      nom de la première équipe
	 * @param l'image du trophée (ou du logo) de la première équipe
	 * @param l'image de la deuxième équipe
	 * @param le      nom de la deuxième équipe
	 * @param l'image du trophée (ou du logo) de la deuxième équipe
	 */
	public LigneMatche(String dateHeure, ImageIcon imageEquipe1, String nomEquipe1, ImageIcon trophéeEquipe1,
					   ImageIcon imageEquipe2, String nomEquipe2, ImageIcon trophéeEquipe2) {
		this.dateHeure = dateHeure;
		this.imageEquipe1 = imageEquipe1;
		this.nomEquipe1 = nomEquipe1;
		this.trophéeEquipe1 = trophéeEquipe1;
		this.imageEquipe2 = imageEquipe2;
		this.nomEquipe2 = nomEquipe2;
		this.trophéeEquipe2 = trophéeEquipe2;
	}

	/**
	 * @return la date et l'heure
	 */
	public String getDateHeure() {
		return dateHeure;
	}

	/**
	 * @return l'image de la première équipe
	 */
	public ImageIcon getImageEquipe1() {
		return imageEquipe1;
	}

	/**
	 * @return le nom de la première équipe
	 */
	public String getNomEquipe1() {
		return nomEquipe1;
	}

	/**
	 * @return le trophée ou l'icone de la première équipe
	 */
	public ImageIcon getTropheeEquipe1() {
		return trophéeEquipe1;
	}

	/**
	 * @return l'image de la deuxième équipe
	 */
	public ImageIcon getImageEquipe2() {
		return imageEquipe2;
	}

	/**
	 * @return le nom de la deuxième équipe
	 */
	public String getNomEquipe2() {
		return nomEquipe2;
	}

	/**
	 * @return le trophée ou l'icone de la deuxième équipe
	 */
	public ImageIcon getTropheeEquipe2() {
		return trophéeEquipe2;
	}

}
