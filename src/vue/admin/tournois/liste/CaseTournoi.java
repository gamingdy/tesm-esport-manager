package vue.admin.tournois.liste;

import controlleur.admin.tournois.TournoiSuppressionControlleur;
import vue.Vue;
import vue.common.CustomColor;
import vue.common.MaFont;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class CaseTournoi {

	private String nom;
	private String dateDebut;
	private String dateFin;

	/**
	 * @param nom
	 * @param dateDebut
	 * @param dateFin   le logo de l'équipe
	 * @param pays      l'icone du drapeau du pays
	 */
	public CaseTournoi(String nom, String dateDebut, String dateFin) {
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	/**
	 * Permet de render la case de l'équipe
	 *
	 * @return un JPanel contentant les infos de l'équipe
	 */
	public JPanel getPanel() {
		JPanel panelItem = new JPanel(new GridBagLayout());
		panelItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelItem.setBackground(CustomColor.BACKGROUND_MAIN);
		panelItem.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2),
				BorderFactory.createEmptyBorder(20, 20, 20, 20)));

		JLabel nom = new JLabel(getNom());
		nom.setIconTextGap(15);
		nom.setForeground(CustomColor.BLANC);
		nom.setFont(MaFont.getFontTitre2());
		GridBagConstraints gbcNom = new GridBagConstraints();
		gbcNom.fill = GridBagConstraints.HORIZONTAL;
		gbcNom.gridx = 0;
		gbcNom.gridy = 0;
		gbcNom.weighty = 0.2F;
		gbcNom.gridwidth = GridBagConstraints.REMAINDER;
		panelItem.add(nom, gbcNom);

		JLabel labelDate = new JLabel(dateDebut + " - " + dateFin);
		labelDate.setFont(MaFont.getFontTitre3());
		labelDate.setForeground(CustomColor.BLANC.darker());
		labelDate.setHorizontalTextPosition(JLabel.CENTER);
		labelDate.setVerticalTextPosition(JLabel.CENTER);
		GridBagConstraints gbcDate = new GridBagConstraints();
		gbcDate.fill = GridBagConstraints.HORIZONTAL;
		gbcDate.gridx = 0;
		gbcDate.gridy = 1;
		gbcDate.weighty = 0.5F;
		gbcDate.gridwidth = GridBagConstraints.REMAINDER;
		panelItem.add(labelDate, gbcDate);

		JLabel labelDelete = new JLabel(Vue.resize(new ImageIcon("assets/delete.png"), 30, 30));
		GridBagConstraints gbcDelete = new GridBagConstraints();
		gbcDelete.fill = GridBagConstraints.HORIZONTAL;
		gbcDelete.gridx = 2;
		gbcDelete.gridy = 2;
		gbcDelete.weightx = 0.2F;
		gbcDelete.weighty = 0.2F;
		panelItem.add(labelDelete, gbcDelete);
		labelDelete.addMouseListener(new TournoiSuppressionControlleur(this));
		return panelItem;
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
	 * @return la date de début
	 */
	public String getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut la date de début
	 */
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return la date de fin
	 */
	public String getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin la date de fin
	 */
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
}
