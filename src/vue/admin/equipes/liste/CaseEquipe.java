package vue.admin.equipes.liste;

import javax.swing.Icon;

public class CaseEquipe {
	
	private String nom;
	private String[] joueurs;
	private Icon logo;
	private Icon pays;
	/**
	 * @param nom
	 * @param joueurs
	 * @param logo le logo de l'équipe
	 * @param pays l'icone du drapeau du pays
	 */
	public CaseEquipe(String nom, String[] joueurs, Icon logo, Icon pays) {
		this.nom = nom;
		this.joueurs = joueurs;
		this.logo = logo;
		this.pays = pays;
	}
	/**
	 * @return le nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return les joueurs
	 */
	public String[] getJoueurs() {
		return joueurs;
	}
	/**
	 * @param joueurs the joueurs to set
	 */
	public void setJoueurs(String[] joueurs) {
		this.joueurs = joueurs;
	}
	/**
	 * @return le logo
	 */
	public Icon getLogo() {
		return logo;
	}
	/**
	 * @param logo the logo to set
	 */
	public void setLogo(Icon logo) {
		this.logo = logo;
	}
	/**
	 * @return l'icone du drapeau du pays
	 */
	public Icon getPays() {
		return pays;
	}
	/**
	 * @param pays the pays to set
	 */
	public void setPays(Icon pays) {
		this.pays = pays;
	}
}
