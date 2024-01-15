package vue.arbitre;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CasePartie {

	private ImageIcon logoGauche;
	private String nomGauche;
	private ImageIcon imageBoutonGauche;
	private ImageIcon logoDroite;
	private String nomDroite;
	private ImageIcon imageBoutonDroite;
	
	/**
	 * @param date
	 * @param logoGauche
	 * @param nomGauche
	 * @param logoDroite
	 * @param nomDroite
	 */
	public CasePartie(ImageIcon logoGauche, String nomGauche, ImageIcon imageGauche, ImageIcon logoDroite, String nomDroite, ImageIcon imageDroite) {
		this.logoGauche = logoGauche;
		this.nomGauche = nomGauche;
		this.logoDroite = logoDroite;
		this.nomDroite = nomDroite;
		this.imageBoutonGauche = imageGauche;
		this.imageBoutonDroite = imageDroite;
	}
	public ImageIcon getLogoGauche() {
		return logoGauche;
	}
	public void setLogoGauche(ImageIcon logoGauche) {
		this.logoGauche = logoGauche;
	}
	public String getNomGauche() {
		return nomGauche;
	}
	public void setNomGauche(String nomGauche) {
		this.nomGauche = nomGauche;
	}
	public ImageIcon getImageBoutonGauche() {
		return imageBoutonGauche;
	}
	public void setImageBoutonGauche(ImageIcon imageBoutonGauche) {
		this.imageBoutonGauche = imageBoutonGauche;
	}
	public ImageIcon getLogoDroite() {
		return logoDroite;
	}
	public void setLogoDroite(ImageIcon logoDroite) {
		this.logoDroite = logoDroite;
	}
	public String getNomDroite() {
		return nomDroite;
	}
	public void setNomDroite(String nomDroite) {
		this.nomDroite = nomDroite;
	}
	public ImageIcon getImageBoutonDroite() {
		return imageBoutonDroite;
	}
	public void setImageBoutonDroite(ImageIcon imageBoutonDroite) {
		this.imageBoutonDroite = imageBoutonDroite;
	}
}
