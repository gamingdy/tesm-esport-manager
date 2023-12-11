package vue.admin.equipes.creation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import vue.common.CustomColor;
import vue.common.MaFont;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class VueAdminEquipesCreation extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelDrapeau;

	/**
	 * Create the panel.
	 */
	public VueAdminEquipesCreation() {
		
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		setOpaque(false);
		JPanel panelTop = new JPanel();
		panelTop.setPreferredSize(new Dimension(0,0));
		panelTop.setOpaque(false);
		GridBagConstraints gbcPanelTop = new GridBagConstraints();
		gbcPanelTop.weighty = 1;
		gbcPanelTop.fill = GridBagConstraints.BOTH;
		gbcPanelTop.gridx = 0;
		gbcPanelTop.gridy = 0;
		add(panelTop, gbcPanelTop);
		GridBagLayout gbl_panelTop = new GridBagLayout();
		gbl_panelTop.columnWidths = new int[]{0, 0, 0};
		gbl_panelTop.rowHeights = new int[]{0, 0, 0};
		gbl_panelTop.columnWeights = new double[]{0.5, 0.5, Double.MIN_VALUE};
		gbl_panelTop.rowWeights = new double[]{0.5, 0.5, Double.MIN_VALUE};
		panelTop.setLayout(gbl_panelTop);
		
		JPanel panelChamps = new JPanel();
		panelChamps.setOpaque(false);
		GridBagConstraints gbcPanelChamps = new GridBagConstraints();
		gbcPanelChamps.fill = GridBagConstraints.BOTH;
		gbcPanelChamps.gridx = 0;
		gbcPanelChamps.gridy = 0;
		panelTop.add(panelChamps, gbcPanelChamps);
		
		//panel pour centrer le drapeau (marche pas)
		JPanel panelDrapeau = new JPanel();
		panelDrapeau.setOpaque(false);
		panelDrapeau.setPreferredSize(new Dimension(0,0));
		labelDrapeau = new JLabel("");
		labelDrapeau.setOpaque(true);
		labelDrapeau.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 4),BorderFactory.createEmptyBorder(67,100,67,100)));
		labelDrapeau.setBackground(CustomColor.BACKGROUND_TEST);
		labelDrapeau.setIcon(new ImageIcon("assets/country-flags/fr.png"));
		labelDrapeau.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints gbcLabelDrapeau = new GridBagConstraints();
		gbcLabelDrapeau.fill = GridBagConstraints.BOTH;
		gbcLabelDrapeau.gridx = 1;
		gbcLabelDrapeau.gridy = 0;
		panelTop.add(panelDrapeau, gbcLabelDrapeau);
		panelDrapeau.add(labelDrapeau,BorderLayout.SOUTH);
		
		JPanel panelJoueurs = new JPanel();
		panelJoueurs.setBackground(CustomColor.BACKGROUND_MAIN);
		panelJoueurs.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2));
		GridBagLayout gblPanelJoueurs = new GridBagLayout();
		gblPanelJoueurs.columnWeights = new double[] {1,Double.MIN_VALUE};
		gblPanelJoueurs.rowWeights = new double[] {1,Double.MIN_VALUE};
		gblPanelJoueurs.columnWidths = new int[] {0};
		panelJoueurs.setLayout(gblPanelJoueurs);
		GridBagConstraints gbcPanelJoueurs = new GridBagConstraints();
		gbcPanelJoueurs.fill = GridBagConstraints.BOTH;
		gbcPanelJoueurs.gridx = 0;
		gbcPanelJoueurs.gridy = 1;
		panelTop.add(panelJoueurs, gbcPanelJoueurs);
		JLabel labelJoueurs = new JLabel("Joueurs");
		labelJoueurs.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, CustomColor.ROSE_CONTOURS));
		labelJoueurs.setForeground(CustomColor.BLANC);
		labelJoueurs.setFont(MaFont.getFontTitre3());
		GridBagConstraints gbcLabelJoueurs = new GridBagConstraints();
		gbcPanelJoueurs.fill = GridBagConstraints.HORIZONTAL;
		gbcPanelJoueurs.gridx=0;
		gbcPanelJoueurs.gridy=0;
		gbcPanelJoueurs.weightx = 1;
		gbcPanelJoueurs.weighty = 0.125;
		panelJoueurs.add(labelJoueurs,gbcLabelJoueurs);
		
		JLabel labelLogo = new JLabel("Insérer logo");
		GridBagConstraints gbcLabelLogo = new GridBagConstraints();
		gbcLabelLogo.gridx = 1;
		gbcLabelLogo.gridy = 1;
		panelTop.add(labelLogo, gbcLabelLogo);
		
		JPanel panelBot = new JPanel();
		panelBot.setOpaque(false);
		panelBot.setPreferredSize(new Dimension(0,0));
		GridBagConstraints gbcPanelBot = new GridBagConstraints();
		gbcPanelBot.fill = GridBagConstraints.BOTH;
		gbcPanelBot.weighty = 0.2;
		gbcPanelBot.gridx = 0;
		gbcPanelBot.gridy = 1;
		add(panelBot, gbcPanelBot);
	}

}
