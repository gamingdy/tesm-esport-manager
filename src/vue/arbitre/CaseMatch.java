package vue.arbitre;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CaseMatch extends CasePartie{

	private String date;
	private int idMatche;
	
	/**
	 * @param date
	 * @param logoGauche
	 * @param nomGauche
	 * @param logoDroite
	 * @param nomDroite
	 */
	public CaseMatch(String date, ImageIcon logoGauche, String nomGauche, ImageIcon imageGauche, ImageIcon logoDroite, String nomDroite, ImageIcon imageDroite,int idMatche) {
		super(logoGauche,nomGauche,imageGauche,logoDroite,nomDroite,imageDroite);
		this.date = date;
		this.idMatche=idMatche;
	}
	public int getIdMatche(){
		return this.idMatche;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
