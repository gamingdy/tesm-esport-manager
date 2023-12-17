package vue.admin.equipes.liste;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vue.Vue;
import vue.common.CustomColor;
import vue.common.MaFont;

public class CaseEquipe {
	
	private String nom;
	private String[] joueurs;
	private ImageIcon logo;
	private ImageIcon pays;
	/**
	 * @param nom
	 * @param joueurs
	 * @param logo le logo de l'équipe
	 * @param pays l'icone du drapeau du pays
	 */
	public CaseEquipe(String nom, String[] joueurs, ImageIcon logo, ImageIcon pays) {
		this.nom = nom;
		this.joueurs = joueurs;
		this.logo = logo;
		this.pays = pays;
	}
	/**
	 * Permet de render la case de l'équipe
	 * @return un JPanel contentant les infos de l'équipe
	 */
	public JPanel getPanel() {
		JPanel panelItem = new JPanel(new GridBagLayout());
		panelItem.setBackground(CustomColor.BACKGROUND_MAIN);
        panelItem.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS,2),
        		BorderFactory.createEmptyBorder(20, 20, 20, 20)));
		
		JLabel nom = new JLabel(getNom());
		nom.setIconTextGap(15);
		nom.setIcon(Vue.resize(getLogo(),45,45));
		nom.setForeground(CustomColor.BLANC);
		nom.setFont(MaFont.getFontTitre3());
		GridBagConstraints gbcNom = new GridBagConstraints();
		gbcNom.fill = GridBagConstraints.HORIZONTAL;
		gbcNom.gridx = 0;
		gbcNom.gridy = 0;
		gbcNom.weighty = 0.2F;
		gbcNom.gridwidth = GridBagConstraints.REMAINDER;
		panelItem.add(nom,gbcNom);
		
		JPanel panelJoueurs = new JPanel(new GridLayout(0,1));
		((GridLayout) panelJoueurs.getLayout()).setVgap(3);
		JLabel labelJ;
		for (String j : getJoueurs() ) {
			labelJ = new JLabel(j);
			labelJ.setForeground(CustomColor.BLANC);
			labelJ.setFont(MaFont.getFontTitre4());
			panelJoueurs.add(labelJ);
		}
		panelJoueurs.setBorder(BorderFactory.createEmptyBorder(10,0,15,0));
		GridBagConstraints gbcJoueurs = new GridBagConstraints();
		gbcJoueurs.gridx = 0;
		gbcJoueurs.gridy = 1;
		gbcJoueurs.weighty = 0.6F;
		gbcJoueurs.gridwidth = GridBagConstraints.REMAINDER;
		panelJoueurs.setBackground(CustomColor.BACKGROUND_MAIN);
		panelItem.add(panelJoueurs,gbcJoueurs);
		
		JLabel labelDrapeau = new JLabel(Vue.resize(getPays(),45,30));
		labelDrapeau.setHorizontalAlignment(JLabel.LEFT);
		GridBagConstraints gbcDrapeau = new GridBagConstraints();
		gbcDrapeau.fill = GridBagConstraints.HORIZONTAL;
		gbcDrapeau.gridx = 0;
		gbcDrapeau.gridy = 2;
		gbcDrapeau.weightx = 0.2F;
		gbcDrapeau.weighty = 0.2F;
		panelItem.add(labelDrapeau,gbcDrapeau);
		
		JLabel labelModif = new JLabel(Vue.resize(new ImageIcon("assets/modif.png"),30,30));
		labelModif.setHorizontalAlignment(JLabel.RIGHT);
		GridBagConstraints gbcModif = new GridBagConstraints();
		gbcModif.fill = GridBagConstraints.HORIZONTAL;
		gbcModif.gridx = 1;
		gbcModif.gridy = 2;
		gbcModif.weightx = 0.6F;
		gbcModif.weighty = 0.2F;
		panelItem.add(labelModif,gbcModif);
		
		JLabel labelDelete = new JLabel(Vue.resize(new ImageIcon("assets/delete.png"),30,30));
		GridBagConstraints gbcDelete = new GridBagConstraints();
		gbcDelete.fill = GridBagConstraints.HORIZONTAL;
		gbcDelete.gridx = 2;
		gbcDelete.gridy = 2;
		gbcDelete.weightx = 0.2F;
		gbcDelete.weighty = 0.2F;
		panelItem.add(labelDelete,gbcDelete);
		
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
	public ImageIcon getLogo() {
		return logo;
	}
	/**
	 * @param logo the logo to set
	 */
	public void setLogo(ImageIcon logo) {
		this.logo = logo;
	}
	/**
	 * @return l'icone du drapeau du pays
	 */
	public ImageIcon getPays() {
		return pays;
	}
	/**
	 * @param pays the pays to set
	 */
	public void setPays(ImageIcon pays) {
		this.pays = pays;
	}
}
