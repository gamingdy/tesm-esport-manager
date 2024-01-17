package vue.arbitre;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;

import javax.swing.*;

import controlleur.arbitre.ArbitreControlleur;
import vue.Vue;
import vue.common.CustomColor;
import vue.common.CustomScrollBarUI;
import vue.common.MaFont;

public class VueArbitrePoule extends VueArbitre{

	private DefaultListModel<CaseMatch> modelMatch;
	private DefaultListModel<CasePartie> modelPartie;
	private JLabel labelTitreParties;
	private JScrollPane spParties;
	private JList<CaseMatch> liste;
	private JList<CasePartie> listeParties;
	@Override
	public void initMain() {
		super.initMain();
		main.setLayout(new GridBagLayout());
		
		JLabel labelTitre = new JLabel("Liste des matchs",SwingConstants.LEADING);
		labelTitre.setForeground(CustomColor.BLANC);
		labelTitre.setFont(MaFont.getFontTitre1());
		GridBagConstraints gbcTitre = new GridBagConstraints();
		gbcTitre.insets = new Insets(25,25,0,0);
		gbcTitre.fill = GridBagConstraints.HORIZONTAL;
		gbcTitre.weightx = 0;
		main.add(labelTitre,gbcTitre);
		
		modelMatch = new DefaultListModel<>();
		liste = new JList<CaseMatch>(modelMatch);
		liste.setFixedCellWidth(500);
		liste.setFixedCellHeight(135);
		liste.setOpaque(false);
		liste.setCellRenderer(new ListCellRenderer<CaseMatch>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends CaseMatch> list, CaseMatch value, int index,
					boolean isSelected, boolean cellHasFocus) {
				return value.getPanel();
			}
			
		});
		JScrollPane sp = new JScrollPane(liste);
		sp.getViewport().setBackground(CustomColor.BACKGROUND_MAIN);
		sp.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		sp.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		sp.setBorder(null);
		
		GridBagConstraints gbcListe = new GridBagConstraints();
		gbcListe.insets = new Insets(25,25,25,25);
		gbcListe.fill = GridBagConstraints.BOTH;
		gbcListe.gridy = 1;
		gbcListe.weightx = 0.6;
		gbcListe.weighty = 0.8;
		main.add(sp,gbcListe);
		
		labelTitreParties = new JLabel("Parties",SwingConstants.LEADING);
		labelTitreParties.setVisible(false);
		labelTitreParties.setForeground(CustomColor.BLANC);
		labelTitreParties.setFont(MaFont.getFontTitre1());
		GridBagConstraints gbcTitrePartie = new GridBagConstraints();
		gbcTitrePartie.insets = new Insets(25,25,0,0);
		gbcTitrePartie.fill = GridBagConstraints.HORIZONTAL;
		gbcTitrePartie.weightx = 0;
		main.add(labelTitreParties,gbcTitrePartie);
		
		modelPartie = new DefaultListModel<>();
		listeParties = new JList<CasePartie>(modelPartie);
		listeParties.setFixedCellHeight(70);
		listeParties.setOpaque(false);
		listeParties.setCellRenderer(new ListCellRenderer<CasePartie>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends CasePartie> list, CasePartie value, int index,
					boolean isSelected, boolean cellHasFocus) {
				return value.getPanel();
			}
			
		});
		spParties = new JScrollPane(listeParties);
		spParties.setVisible(false);
		spParties.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS,1));
		spParties.getViewport().setBackground(CustomColor.BACKGROUND_MAIN);
		spParties.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		spParties.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		spParties.setBorder(null);
		
		GridBagConstraints gbcListeParties = new GridBagConstraints();
		gbcListeParties.insets = new Insets(25,25,25,25);
		gbcListeParties.fill = GridBagConstraints.BOTH;
		gbcListeParties.gridx = 1;
		gbcListeParties.gridy = 1;
		gbcListeParties.weightx = 0.4;
		gbcListeParties.weighty = 0.8;
		main.add(spParties,gbcListeParties);

		this.boutonAction.setText("Clôturer la poule");
		setControleur(new ArbitreControlleur(this));
		
	}
	public void afficherParties(boolean b) {
		spParties.setVisible(b);
		labelTitreParties.setVisible(b);
	}
	
	public DefaultListModel<CaseMatch> getModelMatches(){
		return this.modelMatch;
	}
	public DefaultListModel<CasePartie> getModelPartie(){
		return this.modelPartie;
	}
	public void setControleur(ArbitreControlleur controlleur){
		this.liste.addListSelectionListener(controlleur);
		boutonDeconnexion.addActionListener(controlleur);
	}
	public JButton getBoutonAnnuler(){
		return boutonDeconnexion;
	}

	public JList<CaseMatch> getTableMatche() {
		return this.liste;
	}

	public JList<CasePartie> getTableParties() {
		return this.listeParties;
	}
}
