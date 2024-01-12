package vue.arbitre;

import javax.swing.Icon;
import javax.swing.JButton;

public class CaseMatch {

	private String date;
	private Icon logoGauche;
	private String nomGauche;
	private Icon imageBoutonGauche;
	private Icon logoDroite;
	private String nomDroite;
	private Icon imageBoutonDroite;
	
	/**
	 * @param date
	 * @param logoGauche
	 * @param nomGauche
	 * @param logoDroite
	 * @param nomDroite
	 */
	public CaseMatch(String date, Icon logoGauche, String nomGauche, Icon imageGauche, Icon logoDroite, String nomDroite, Icon imageDroite) {
		this.date = date;
		this.logoGauche = logoGauche;
		this.nomGauche = nomGauche;
		this.logoDroite = logoDroite;
		this.nomDroite = nomDroite;
		this.imageBoutonGauche = imageGauche;
		this.imageBoutonDroite = imageDroite;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Icon getLogoGauche() {
		return logoGauche;
	}
	public void setLogoGauche(Icon logoGauche) {
		this.logoGauche = logoGauche;
	}
	public String getNomGauche() {
		return nomGauche;
	}
	public void setNomGauche(String nomGauche) {
		this.nomGauche = nomGauche;
	}
	public Icon getImageBoutonGauche() {
		return imageBoutonGauche;
	}
	public void setImageBoutonGauche(Icon imageBoutonGauche) {
		this.imageBoutonGauche = imageBoutonGauche;
	}
	public Icon getLogoDroite() {
		return logoDroite;
	}
	public void setLogoDroite(Icon logoDroite) {
		this.logoDroite = logoDroite;
	}
	public String getNomDroite() {
		return nomDroite;
	}
	public void setNomDroite(String nomDroite) {
		this.nomDroite = nomDroite;
	}
	public Icon getImageBoutonDroite() {
		return imageBoutonDroite;
	}
	public void setImageBoutonDroite(Icon imageBoutonDroite) {
		this.imageBoutonDroite = imageBoutonDroite;
	}
}
