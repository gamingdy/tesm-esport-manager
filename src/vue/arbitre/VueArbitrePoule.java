package vue.arbitre;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import controlleur.arbitre.ArbitreControlleur;
import vue.Vue;
import vue.common.CustomColor;
import vue.common.CustomScrollBarUI;
import vue.common.MaFont;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class VueArbitrePoule extends VueArbitre{

	private DefaultListModel<CaseMatch> model;
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
		
		model = new DefaultListModel<>();
		JList<CaseMatch> liste = new JList<CaseMatch>(model);
		liste.setFixedCellWidth(500);
		liste.setFixedCellHeight(135);
		liste.setOpaque(false);
		liste.setCellRenderer(new MatchRenderer());
		JScrollPane sp = new JScrollPane(liste);
		sp.getViewport().setBackground(CustomColor.BACKGROUND_MAIN);
		sp.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		sp.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		sp.setBorder(null);
		
		GridBagConstraints gbcListe = new GridBagConstraints();
		gbcListe.insets = new Insets(25,25,0,0);
		gbcListe.fill = GridBagConstraints.BOTH;
		gbcListe.gridy = 1;
		gbcListe.weightx = 1;
		gbcListe.weighty = 0.8;
		main.add(sp,gbcListe);
		
		this.boutonAction.setText("Clôturer la poule");
		ArbitreControlleur arbitreControlleur=new ArbitreControlleur(this);
		
	}
	public DefaultListModel<CaseMatch> getModelMatches(){
		return this.model;
	}
	
	private class MatchRenderer extends JPanel implements ListCellRenderer<CaseMatch>{

		private JLabel date;
		private JLabel logoEquipe1;
		private JLabel nomEquipe1;
		private JButton image1;
		private JLabel logoEquipe2;
		private JLabel nomEquipe2;
		private JButton image2;
		public MatchRenderer() {
			super();
			setBorder(BorderFactory.createEmptyBorder(20,0,0,20));
			setLayout(new GridBagLayout());
			setOpaque(false);
			date = new JLabel();
			date.setFont(MaFont.getFontTitre3());
			date.setForeground(CustomColor.BLANC.darker());
			GridBagConstraints gbcDate = new GridBagConstraints();
			gbcDate.gridwidth = 2;
			gbcDate.insets = new Insets(0,0,10,0);
			add(date,gbcDate);
			
			JPanel panelE = new JPanel(new GridBagLayout());
			panelE.setOpaque(false);
			GridBagConstraints gbcEquipe = new GridBagConstraints();
			gbcEquipe.fill = GridBagConstraints.BOTH;
			gbcEquipe.weightx = 1;
			gbcEquipe.weighty = 1;
			gbcEquipe.gridy = 1;
			add(panelE,gbcEquipe);
			JPanel equipe1 = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,0));
			equipe1.setOpaque(false);
			equipe1.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 1),
					BorderFactory.createEmptyBorder(10,30,10,15)));
			logoEquipe1 = new JLabel();
			equipe1.add(logoEquipe1);
			nomEquipe1 = new JLabel();
			nomEquipe1.setFont(MaFont.getFontTitre3());
			nomEquipe1.setForeground(CustomColor.BLANC);
			equipe1.add(nomEquipe1);
			image1 = new JButton();
			image1.setContentAreaFilled(false);
			image1.setBorder(null);
			image1.setFocusPainted(false);
			equipe1.add(image1);
			equipe1.setPreferredSize(new Dimension(1,1));
			GridBagConstraints gbcEquipe1 = new GridBagConstraints();
			gbcEquipe1.fill = GridBagConstraints.BOTH;
			gbcEquipe1.weightx = 0.50;
			gbcEquipe1.weighty = 1;
			panelE.add(equipe1,gbcEquipe1);
			
			JPanel equipe2 = new JPanel(new FlowLayout(FlowLayout.LEFT,20,0));
			equipe2.setOpaque(false);
			equipe2.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createMatteBorder(1,0,1,1,CustomColor.ROSE_CONTOURS),
					BorderFactory.createEmptyBorder(10,15,10,30)));		
			logoEquipe2 = new JLabel();
			equipe2.add(logoEquipe2);
			nomEquipe2 = new JLabel();
			nomEquipe2.setFont(MaFont.getFontTitre3());
			nomEquipe2.setForeground(CustomColor.BLANC);
			equipe2.add(nomEquipe2);
			image2 = new JButton();
			image2.setContentAreaFilled(false);
			image2.setBorder(null);
			image2.setFocusPainted(false);
			equipe2.add(image2);
			equipe2.setPreferredSize(new Dimension(1,1));
			GridBagConstraints gbcEquipe2 = new GridBagConstraints();
			gbcEquipe2.fill = GridBagConstraints.BOTH;
			gbcEquipe2.gridx = 1;
			gbcEquipe2.weightx = 0.5;
			gbcEquipe2.weighty = 1;
			panelE.add(equipe2,gbcEquipe2);

		}
		@Override
		public Component getListCellRendererComponent(JList<? extends CaseMatch> list, CaseMatch value, int index,
				boolean isSelected, boolean cellHasFocus) {
			date.setText(value.getDate());
			logoEquipe1.setIcon(Vue.resize(value.getLogoGauche(),50,50));
			nomEquipe1.setText(value.getNomGauche());
			image1.setIcon(Vue.resize(value.getImageBoutonGauche(),50,50));
			logoEquipe2.setIcon(Vue.resize(value.getLogoDroite(),50,50));
			nomEquipe2.setText(value.getNomDroite());
			image2.setIcon(Vue.resize(value.getImageBoutonDroite(),50,50));
			return this;
		}
		
	}
}
