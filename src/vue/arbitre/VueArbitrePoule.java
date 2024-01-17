package vue.arbitre;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;

import javax.swing.*;

import controlleur.arbitre.ArbitreControlleur;
import vue.Vue;
import vue.admin.equipes.liste.CaseEquipe;
import vue.common.CustomColor;
import vue.common.CustomScrollBarUI;
import vue.common.MaFont;

public class VueArbitrePoule extends VueArbitre{

	private JLabel labelTitreParties;
	private JScrollPane spParties;
	private JPanel liste;
	private JPanel listeParties;
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

		liste = new JPanel();
		liste.setOpaque(false);
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

		this.boutonAction.setText("Clôturer la poule");
		setControleur(new ArbitreControlleur(this));

	}
	public void afficherParties(boolean b) {
		spParties.setVisible(b);
		labelTitreParties.setVisible(b);
	}

	public void setControleur(ArbitreControlleur controlleur){
		boutonDeconnexion.addActionListener(controlleur);
	}
	public JButton getBoutonAnnuler(){
		return boutonDeconnexion;
	}

	public void addMatch(CaseMatch c) {
		liste.add(c);
	}

	public void setMatchs(List<CaseMatch> c) {
		liste.removeAll();
		this.addAllMatchs(c);
	}

	public void addAllMatchs(List<CaseMatch> c) {
		c.stream().forEach(this::addMatch);
	}

	public void resetListeMatchs() {
		liste.removeAll();
		JPanel j;
		for (int i = 0; i < 4; i++) {
			j = new JPanel();
			j.setOpaque(false);
			liste.add(j);
		}
	}

	public void supprimerCaseMatch(int i) {
		liste.remove(i);
	}

}
