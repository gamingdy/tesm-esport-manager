package vue.login;

import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControlleurObserver;
import controller.LoginControlleur;
import vue.common.CustomColor;
import vue.common.MaFont;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Arrays;

@SuppressWarnings("serial")
public class VueLogin extends JPanel {
	private LoginControlleur controleur;
	private ChampConnexion champIdentifiant;
	private ChampConnexion champMotDePasse;
	private JButton boutonConnexion;
	public VueLogin() {
		controleur=new LoginControlleur(this);
		setOpaque(false);
		GridBagLayout gridBagLayout_1 = new GridBagLayout();
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		gridBagLayout_1.rowHeights = new int[]{0, 0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout_1);
		creerPanelVide(0,0,this);
		creerPanelVide(0,1,this);
		creerPanelVide(1,1,this);
		creerPanelVide(0,2,this);
		creerPanelVide(3,1,this);
		creerPanelVide(4,0,this);
		creerPanelVide(2,0,this);
		creerPanelVide(1,4,this);
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.fill = GridBagConstraints.BOTH;
		gbcPanel.insets = new Insets(100,0,0,0);
		gbcPanel.gridheight = 2;
		gbcPanel.gridx = 2;
		gbcPanel.gridy = 1;
		gbcPanel.weightx =  2;
		gbcPanel.weighty = 0;
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
		gbcLabelTitre.insets = new Insets(50,0,0,0);
		gbcLabelTitre.gridx = 1;
		gbcLabelTitre.gridy = 0;
		gbcLabelTitre.gridwidth = 3;
		add(labelTitre, gbcLabelTitre);

		champIdentifiant = new ChampConnexion("Identifiant",false,controleur);
		champIdentifiant.setBorder(null);
		GridBagConstraints gbcChampIdentifiant = new GridBagConstraints();
		gbcChampIdentifiant.insets = new Insets(0, 0, 50, 0);
		gbcChampIdentifiant.fill = GridBagConstraints.HORIZONTAL;
		gbcChampIdentifiant.gridx = 0;
		gbcChampIdentifiant.gridy = 0;
		gbcChampIdentifiant.weighty = 0;
		panel.add(champIdentifiant, gbcChampIdentifiant);

		champMotDePasse = new ChampConnexion("Mot de passe",true,controleur);
		champIdentifiant.setBorder(null);
		GridBagConstraints gbcChampMotDePasse = new GridBagConstraints();
		gbcChampMotDePasse.insets = new Insets(0, 0, 50, 0);
		gbcChampMotDePasse.fill = GridBagConstraints.HORIZONTAL;
		gbcChampMotDePasse.gridx = 0;
		gbcChampMotDePasse.gridy = 1;
		gbcChampMotDePasse.weighty = 0;
		panel.add(champMotDePasse, gbcChampMotDePasse);
		
		boutonConnexion = new JButton("Connexion");
		boutonConnexion.setFont(MaFont.getFontLabelConnexion());
		boutonConnexion.setForeground(CustomColor.BLANC);
		boutonConnexion.setBackground(CustomColor.TRANSPARENT);
		boutonConnexion.setOpaque(false);
		boutonConnexion.setFocusable(false);
		boutonConnexion.setEnabled(false);
		GridBagConstraints gbcBoutonConnexion = new GridBagConstraints();
		gbcBoutonConnexion.fill = GridBagConstraints.NONE;
		gbcBoutonConnexion.gridx = 0;
		gbcBoutonConnexion.gridy = 2;
		gbcBoutonConnexion.weighty = 0;
		panel.add(boutonConnexion, gbcBoutonConnexion);
		Arrays.stream(boutonConnexion.getActionListeners()).forEach(l -> boutonConnexion.removeActionListener(l));
		Arrays.stream(boutonConnexion.getMouseListeners()).forEach(l -> boutonConnexion.removeMouseListener(l));
		boutonConnexion.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.BLANC, 2), BorderFactory.createEmptyBorder(10, 25, 10, 25)));

		boutonConnexion.addMouseListener((MouseListener) controleur);
		boutonConnexion.addActionListener((ActionListener) controleur);
		creerPanelVide(0,3,panel);
	}

	public String getIdentifiant() {
		return champIdentifiant.getContenu();
	}
	public String getMotDePasse() {
		return champMotDePasse.getContenu();
	}

	public void attachObserver(ControlleurObserver obs){
		this.controleur.attach(obs);
	}

	private void creerPanelVide(int x,int y, JPanel container) {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.fill = GridBagConstraints.BOTH;
		gbcPanel.gridx = x;
		gbcPanel.gridy = y;
		gbcPanel.weightx = 2;
		gbcPanel.weighty = 2;
		container.add(panel,gbcPanel);
	}

	public void setBoutonActif(boolean value) {
		boutonConnexion.setEnabled(value);
	}
}
