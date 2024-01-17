package vue.arbitre;

import java.awt.event.ActionListener;

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
	public CaseMatch(String date, ImageIcon logoGauche, String nomGauche, ImageIcon imageGauche, ImageIcon imageDroite, String nomDroite, ImageIcon logoDroite, int idMatche, ActionListener alGauche, ActionListener alDroite) {
		super(logoGauche,nomGauche,imageGauche,imageDroite,nomDroite,logoDroite, alGauche, alDroite);
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
