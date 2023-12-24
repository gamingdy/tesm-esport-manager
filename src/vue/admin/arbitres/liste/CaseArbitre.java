package vue.admin.arbitres.liste;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vue.Vue;
import vue.common.CustomColor;
import vue.common.MaFont;

public class CaseArbitre {

	private String nom;
	private String prenom;
	/**
	 * @param nom
	 * @param joueurs
	 * @param logo    le logo de l'équipe
	 * @param pays    l'icone du drapeau du pays
	 */
	public CaseArbitre(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	/**
	 * Permet de render la case de l'équipe
	 *
	 * @return un JPanel contentant les infos de l'équipe
	 */
	public JPanel getPanel() {
		JPanel panelItem = new JPanel(new GridBagLayout());
		panelItem.setBackground(CustomColor.BACKGROUND_MAIN);
		panelItem.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2),
				BorderFactory.createEmptyBorder(20, 20, 20, 20)));

		JLabel icone = new JLabel(new ImageIcon("assets/petitUser.png"));
		GridBagConstraints gbcIcone = new GridBagConstraints();
		gbcIcone.fill = GridBagConstraints.HORIZONTAL;
		gbcIcone.gridx = 0;
		gbcIcone.gridy = 0;
		gbcIcone.weighty = 0.8F;
		panelItem.add(icone, gbcIcone);
		
		
		JLabel nom = new JLabel(getNom()+" "+getPrenom());
		nom.setIconTextGap(15);
		nom.setForeground(CustomColor.BLANC);
		nom.setFont(MaFont.getFontTitre3());
		GridBagConstraints gbcNom = new GridBagConstraints();
		gbcNom.fill = GridBagConstraints.HORIZONTAL;
		gbcNom.gridx = 1;
		gbcNom.gridy = 0;
		gbcNom.gridwidth = 2;
		panelItem.add(nom, gbcNom);


		JLabel labelModif = new JLabel(Vue.resize(new ImageIcon("assets/modif.png"), 30, 30));
		labelModif.setHorizontalAlignment(JLabel.RIGHT);
		GridBagConstraints gbcModif = new GridBagConstraints();
		gbcModif.fill = GridBagConstraints.HORIZONTAL;
		gbcModif.gridx = 1;
		gbcModif.gridy = 1;
		gbcModif.weightx = 1F;
		gbcModif.weighty = 0.2F;
		panelItem.add(labelModif, gbcModif);

		JLabel labelDelete = new JLabel(Vue.resize(new ImageIcon("assets/delete.png"), 30, 30));
		GridBagConstraints gbcDelete = new GridBagConstraints();
		gbcDelete.fill = GridBagConstraints.HORIZONTAL;
		gbcDelete.gridx = 2;
		gbcDelete.gridy = 2;
		panelItem.add(labelDelete, gbcDelete);

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
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


}
