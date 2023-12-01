package vue.login;

import javax.swing.JPanel;
import javax.swing.JTextField;

import vue.common.CustomColor;
import vue.common.MaFont;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class VueLogin extends JPanel {
	
	public VueLogin() {
		setOpaque(false);
		GridBagLayout gridBagLayout_1 = new GridBagLayout();
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		gridBagLayout_1.rowHeights = new int[]{0, 0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout_1);
		creerPanelVide(0,0);
		creerPanelVide(1,0);
		creerPanelVide(0,1);
		creerPanelVide(0,2);
		creerPanelVide(2,0);
		creerPanelVide(3,0);
		creerPanelVide(3,1);
		creerPanelVide(3,2);
		creerPanelVide(3,3);
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(410,0));
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.fill = GridBagConstraints.BOTH;
		gbcPanel.gridheight = 2;
		gbcPanel.gridwidth = 2;
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 1;
		add(panel, gbcPanel);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		JLabel labelTitre = new JLabel("Bienvenue sur Esporter Manager");
		labelTitre.setFont(MaFont.getFontTitreConnexion());
		labelTitre.setForeground(CustomColor.BLANC);
		GridBagConstraints gbcLabelTitre = new GridBagConstraints();
		gbcLabelTitre.insets = new Insets(0, 0, 5, 0);
		gbcLabelTitre.gridx = 0;
		gbcLabelTitre.gridy = 0;
		gbcLabelTitre.weightx = 0;
		gbcLabelTitre.weighty = 0;
		panel.add(labelTitre, gbcLabelTitre);
		
		JPanel champIdentifiant = new ChampConnexion("Identifiant");
		GridBagConstraints gbcChampIdentifiant = new GridBagConstraints();
		gbcChampIdentifiant.insets = new Insets(0, 0, 5, 0);
		gbcChampIdentifiant.fill = GridBagConstraints.HORIZONTAL;
		gbcChampIdentifiant.gridx = 0;
		gbcChampIdentifiant.gridy = 1;
		panel.add(champIdentifiant, gbcChampIdentifiant);
		
		JPanel champMotDePasse = new ChampConnexion("Mot de passe");
		GridBagConstraints gbcChampMotDePasse = new GridBagConstraints();
		gbcChampMotDePasse.insets = new Insets(0, 0, 5, 0);
		gbcChampMotDePasse.fill = GridBagConstraints.HORIZONTAL;
		gbcChampMotDePasse.gridx = 0;
		gbcChampMotDePasse.gridy = 2;
		panel.add(champMotDePasse, gbcChampMotDePasse);
		
		JButton boutonConnexion = new JButton("Connexion");
		boutonConnexion.setFont(MaFont.getFontLabelConnexion());
		boutonConnexion.setForeground(CustomColor.BLANC);
		boutonConnexion.setBackground(CustomColor.TRANSPARENT);
		GridBagConstraints gbcBoutonConnexion = new GridBagConstraints();
		gbcBoutonConnexion.gridx = 0;
		gbcBoutonConnexion.gridy = 3;
		panel.add(boutonConnexion, gbcBoutonConnexion);
	}
	
	private void creerPanelVide(int x,int y) {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.fill = GridBagConstraints.BOTH;
		gbcPanel.gridx = x;
		gbcPanel.gridy = y;
		gbcPanel.weightx = 2;
		gbcPanel.weighty = 2;
		add(panel,gbcPanel);
	}
}
