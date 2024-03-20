package vue.admin.equipes.liste;

import controlleur.admin.equipes.EquipeCaseModificationControlleur;
import controlleur.admin.equipes.EquipeSuppresionControlleur;
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
import java.awt.GridLayout;

public class CaseEquipe extends JPanel {

	private String nom;
	private String[] joueurs;
	private ImageIcon logo;
	private ImageIcon pays;

	/**
	 * @param nom
	 * @param joueurs
	 * @param logo    le logo de l'équipe
	 * @param pays    l'icone du drapeau du pays
	 */
	public CaseEquipe(String nom, String[] joueurs, ImageIcon logo, ImageIcon pays) {
		super(new GridBagLayout());
		this.nom = nom;
		this.joueurs = joueurs;
		this.logo = logo;
		this.pays = pays;
		addMouseListener(new EquipeCaseModificationControlleur(this, false));
		this.updatePanel();
		setBackground(CustomColor.BACKGROUND_MAIN);
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2),
				BorderFactory.createEmptyBorder(20, 20, 20, 20)));
		setCursor(new Cursor(Cursor.HAND_CURSOR));

	}

	/**
	 * Permet de render la case de l'équipe
	 *
	 * @return un JPanel contentant les infos de l'équipe
	 */
	public void updatePanel() {
		removeAll();
		JLabel nomLabel = new JLabel(getNom());
		nomLabel.setIconTextGap(15);
		nomLabel.setIcon(Vue.resize(getLogo(), 45, 45));
		nomLabel.setForeground(CustomColor.BLANC);
		nomLabel.setFont(MaFont.getFontTitre3());
		GridBagConstraints gbcNom = new GridBagConstraints();
		gbcNom.fill = GridBagConstraints.HORIZONTAL;
		gbcNom.gridx = 0;
		gbcNom.gridy = 0;
		gbcNom.weighty = 0.2F;
		gbcNom.gridwidth = GridBagConstraints.REMAINDER;
		add(nomLabel, gbcNom);

		JPanel panelJoueurs = new JPanel(new GridLayout(0, 1));
		((GridLayout) panelJoueurs.getLayout()).setVgap(3);
		JLabel labelJ;
		for (String j : getJoueurs()) {
			labelJ = new JLabel(j);
			labelJ.setForeground(CustomColor.BLANC);
			labelJ.setFont(MaFont.getFontTitre4());
			panelJoueurs.add(labelJ);
		}
		panelJoueurs.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
		GridBagConstraints gbcJoueurs = new GridBagConstraints();
		gbcJoueurs.gridx = 0;
		gbcJoueurs.gridy = 1;
		gbcJoueurs.weighty = 0.6F;
		gbcJoueurs.gridwidth = GridBagConstraints.REMAINDER;
		panelJoueurs.setBackground(CustomColor.BACKGROUND_MAIN);
		add(panelJoueurs, gbcJoueurs);

		JLabel labelDrapeau = new JLabel(Vue.resize(getPays(), 45, 30));
		labelDrapeau.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		GridBagConstraints gbcDrapeau = new GridBagConstraints();
		gbcDrapeau.fill = GridBagConstraints.HORIZONTAL;
		gbcDrapeau.gridx = 0;
		gbcDrapeau.gridy = 2;
		gbcDrapeau.weightx = 0.2F;
		gbcDrapeau.weighty = 0.2F;
		add(labelDrapeau, gbcDrapeau);

		JLabel labelModif = new JLabel(Vue.resize(new ImageIcon("assets/modif.png"), 30, 30));
		labelModif.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		GridBagConstraints gbcModif = new GridBagConstraints();
		gbcModif.fill = GridBagConstraints.HORIZONTAL;
		gbcModif.gridx = 1;
		gbcModif.gridy = 2;
		gbcModif.weightx = 0.6F;
		gbcModif.weighty = 0.2F;
		labelModif.addMouseListener(new EquipeCaseModificationControlleur(this, true));
		add(labelModif, gbcModif);

		JLabel labelDelete = new JLabel(Vue.resize(new ImageIcon("assets/delete.png"), 30, 30));
		GridBagConstraints gbcDelete = new GridBagConstraints();
		gbcDelete.fill = GridBagConstraints.HORIZONTAL;
		gbcDelete.gridx = 2;
		gbcDelete.gridy = 2;
		gbcDelete.weightx = 0.2F;
		gbcDelete.weighty = 0.2F;
		labelDelete.addMouseListener(new EquipeSuppresionControlleur(this));
		add(labelDelete, gbcDelete);

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

	@Override
	public boolean equals(Object o) {
		if (o instanceof CaseEquipe) {
			CaseEquipe c = (CaseEquipe) o;
			return c.getNom().equals(this.getNom());
		}
		return false;
	}
}
