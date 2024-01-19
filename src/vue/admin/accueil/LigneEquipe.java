package vue.admin.accueil;

import javax.swing.ImageIcon;

public class LigneEquipe {

	private int place;
	private ImageIcon logo;
	private String nom;
	private Float points;

	/**
	 * @param place  la place de l'équipe dans la saison précédente
	 * @param logo   le logo de l'équipe
	 * @param nom    le nom de l'équipe
	 * @param points le nombre de points de l'équipe dans la saison précédente
	 * @return le paramètre LigneEquipe nécessaire pour une ligne d'équipe de la page d'accueil
	 */
	public LigneEquipe(int place, ImageIcon logo, String nom, Float points) {
		this.place = place;
		this.logo = logo;
		this.nom = nom;
		this.points = points;
	}

	/**
	 * @return la place
	 */
	public int getPlace() {
		return place;
	}

	/**
	 * @return le logo
	 */
	public ImageIcon getLogo() {
		return logo;
	}

	/**
	 * @return le nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return les points
	 */
	public Float getPoints() {
		return points;
	}


}
