package vue.admin.arbitres.liste;

import controlleur.admin.arbitres.ArbitreSuppressionControlleur;
import vue.Vue;
import vue.common.CustomColor;
import vue.common.MaFont;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class CaseArbitre {

	private String nom;
	private String prenom;
	private String numero;

	public String getNumero() {
		return numero;
	}

	/**
	 * @param nom
	 * @param joueurs
	 * @param logo    le logo de l'équipe
	 * @param pays    l'icone du drapeau du pays
	 */
	public CaseArbitre(String nom, String prenom, String numero) {
		this.nom = nom;
		this.prenom = prenom;
		this.numero = numero;
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

		JLabel icone = new JLabel(new ImageIcon("assets/petitUser.png"));
		GridBagConstraints gbcIcone = new GridBagConstraints();
		gbcIcone.fill = GridBagConstraints.HORIZONTAL;
		gbcIcone.gridx = 0;
		gbcIcone.gridy = 0;
		gbcIcone.weighty = 0.8F;
		panelItem.add(icone, gbcIcone);


		JTextArea nomTextArea = new JTextArea(getNom() + " " + getPrenom() + System.lineSeparator() + getNumero());
		nomTextArea.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		nomTextArea.setLineWrap(true);
		nomTextArea.setEditable(false);
		nomTextArea.setWrapStyleWord(true);
		nomTextArea.setOpaque(false);
		nomTextArea.setForeground(CustomColor.BLANC);
		nomTextArea.setFont(MaFont.getFontTitre4());
		GridBagConstraints gbcNom = new GridBagConstraints();
		gbcNom.fill = GridBagConstraints.HORIZONTAL;
		gbcNom.gridx = 1;
		gbcNom.gridy = 0;
		gbcNom.gridwidth = 2;
		panelItem.add(nomTextArea, gbcNom);


		JLabel labelDelete = new JLabel(Vue.resize(new ImageIcon("assets/delete.png"), 30, 30));
		GridBagConstraints gbcDelete = new GridBagConstraints();
		gbcDelete.fill = GridBagConstraints.HORIZONTAL;
		gbcDelete.gridx = 2;
		gbcDelete.gridy = 1;
		panelItem.add(labelDelete, gbcDelete);
		labelDelete.addMouseListener(new ArbitreSuppressionControlleur(this));
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
