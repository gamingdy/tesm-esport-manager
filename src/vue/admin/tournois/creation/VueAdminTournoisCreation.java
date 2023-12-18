package vue.admin.tournois.creation;

import controlleur.admin.equipes.EquipeCreationControlleur;
import modele.Pays;
import vue.Vue;
import vue.common.CustomColor;
import vue.common.MaFont;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class VueAdminTournoisCreation extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textfieldNom;
	private JTextField textfieldDateDebut;
	private JTextField textfieldDateFin;
	private JLabel panelEquipes[];
	private JButton boutonValider;
	private JButton boutonAnnuler;
	private JLabel labelLogo;
	private JLabel btnAjoutEquipes;
	private JPanel panelJoueurs;

	/**
	 * Create the panel.
	 */
	public VueAdminTournoisCreation() {

		setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};

		setLayout(gridBagLayout);
		setOpaque(false);


		JPanel champNom = new JPanel();
		champNom.setLayout(new GridLayout(2, 1, 10, 0));
		champNom.setOpaque(false);
		JLabel labelNom = new JLabel("Nom du tournoi");
		labelNom.setForeground(CustomColor.BLANC);
		labelNom.setFont(MaFont.getFontTitre2());
		champNom.add(labelNom);
		textfieldNom = new JTextField();
		textfieldNom.setBackground(CustomColor.BACKGROUND_MENU);
		textfieldNom.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		textfieldNom.setForeground(CustomColor.BLANC);
		textfieldNom.setCaretColor(CustomColor.BLANC);
		textfieldNom.setFont(MaFont.getFontTitre3());
		champNom.add(textfieldNom);
		GridBagConstraints gbcNom = new GridBagConstraints();
		gbcNom.gridx = 0;
		gbcNom.gridy = 0;
		gbcNom.weightx = 1F / 5F;

		add(champNom, gbcNom);


		JPanel champDateDébut = new JPanel();
		champDateDébut.setLayout(new GridLayout(2, 1, 10, 0));
		champDateDébut.setOpaque(false);
		JLabel labelDateDébut = new JLabel("Date de début");
		labelDateDébut.setForeground(CustomColor.BLANC);
		labelDateDébut.setFont(MaFont.getFontTitre2());
		champDateDébut.add(labelDateDébut);
		textfieldDateDebut = new JTextField();
		textfieldDateDebut.setBackground(CustomColor.BACKGROUND_MENU);
		textfieldDateDebut.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		textfieldDateDebut.setForeground(CustomColor.BLANC);
		textfieldDateDebut.setCaretColor(CustomColor.BLANC);
		textfieldDateDebut.setFont(MaFont.getFontTitre3());
		champDateDébut.add(textfieldDateDebut);
		GridBagConstraints gbcDateDébut = new GridBagConstraints();
		gbcDateDébut.gridx = 0;
		gbcDateDébut.gridy = 1;
		gbcDateDébut.weightx = 1F / 5F;

		add(champDateDébut, gbcDateDébut);

		JPanel panelBot = new JPanel();
		panelBot.setOpaque(false);
		panelBot.setPreferredSize(new Dimension(0, 0));
		GridBagConstraints gbcPanelBot = new GridBagConstraints();
		gbcPanelBot.fill = GridBagConstraints.BOTH;
		gbcPanelBot.weighty = 0.2;
		gbcPanelBot.gridx = 0;
		gbcPanelBot.gridy = 1;
		add(panelBot, gbcPanelBot);
		panelBot.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		((FlowLayout) panelBot.getLayout()).setHgap(150);

		boutonAnnuler = new JButton("Annuler");
		boutonAnnuler.setBackground(CustomColor.BACKGROUND_MENU.darker());
		boutonAnnuler.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS.darker(), 3), BorderFactory.createEmptyBorder(10, 40, 10, 40)));
		boutonAnnuler.setForeground(CustomColor.BLANC.darker());
		panelBot.add(boutonAnnuler);

		boutonValider = new JButton("Ajouter");
		boutonValider.setBackground(CustomColor.BACKGROUND_MENU.brighter());
		boutonValider.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3), BorderFactory.createEmptyBorder(10, 40, 10, 40)));
		boutonValider.setForeground(CustomColor.BLANC);
		panelBot.add(boutonValider);

	}

	/**
	 * setControlleur est une méthode qui permet d'ajouter les controlleurs au bouton, c'est par défaut dans Jbutton
	 *
	 * @param controleur
	 */
	public void setControleur(EquipeCreationControlleur controleur) {
		this.boutonValider.addActionListener(controleur);
		this.boutonAnnuler.addActionListener(controleur);
		this.labelLogo.addMouseListener(controleur);
	}


	public void clearField() {
		this.textfieldNom.setText("");
		this.labelLogo.setText("Insérer logo");
		this.labelLogo.setIcon(null);

	}

	public String getNomEquipe() {
		return this.textfieldNom.getText();
	}

	public JLabel getLabelLogo() {
		return this.labelLogo;
	}

	/**
	 * Set le nom d'un joueur
	 *
	 * @param nom nom du joueur
	 * @param i   indice du joueur dans le tableau ==> <strong>0 à 4</strong>
	 */
	public void setEquipe(String nom, Icon logo, int i) {
		this.panelEquipes[i] = new JLabel(nom);
		this.panelEquipes[i].setIcon(logo);
		this.panelEquipes[i].setForeground(CustomColor.BLANC);
		this.panelEquipes[i].setFont(MaFont.getFontTitre3());
		this.panelEquipes[i].setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2));
		GridBagConstraints gbcJ = new GridBagConstraints();
		gbcJ.fill = GridBagConstraints.HORIZONTAL;
		gbcJ.gridx = 0;
		gbcJ.gridy = i + 1;
		gbcJ.weighty = 1F / 9F;
		this.panelJoueurs.add(this.panelEquipes[i], gbcJ);
	}

	public void activerBoutonAjoutEquipes(boolean b) {
		if (b) {
			GridBagConstraints gbcJ = new GridBagConstraints();
			gbcJ.fill = GridBagConstraints.HORIZONTAL;
			gbcJ.gridx = 0;
			gbcJ.gridy = 5;
			gbcJ.weighty = 1F / 7F;
			panelJoueurs.add(btnAjoutEquipes, gbcJ);
		} else {
			GridBagConstraints gbcJ = new GridBagConstraints();
			gbcJ.fill = GridBagConstraints.HORIZONTAL;
			gbcJ.gridx = 0;
			gbcJ.gridy = 5;
			gbcJ.weighty = 1F / 7F;
			panelJoueurs.add(this.panelEquipes[4], gbcJ);
		}
	}

	public String[] getEquipes() {
		String[] retour = new String[5];
		for (int i = 0; i < 5; i++) {
			retour[i] = panelEquipes[i].getText();
		}
		return retour;
	}

	public JButton getBoutonValider() {
		return this.boutonValider;
	}

	public String getTextfieldNom() {
		return this.textfieldNom.getText();
	}

	public String getTextfieldDateDebut() {
		return this.textfieldDateDebut.getText();
	}

	public String getTextfieldDateFin() {
		return this.textfieldDateFin.getText();
	}

}


