package vue.accueil;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSlider;
import javax.swing.ListModel;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;

import vue.Vue;
import vue.common.MaFont;
import vue.common.CustomScrollBarUI;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class VueAccueil extends JPanel {

	private static final long serialVersionUID = 1L;

	private JList<LigneMatche> listeMatches;

	private JList<LigneTournoi> listeTournois;

	private JList<LigneEquipe> listeEquipes;

	/**
	 * Create the panel.
	 * @param equipes liste des équipes à afficher
	 * @param tournois liste des tournois à afficher
	 * @param matches liste des matchs à afficher
	 */
	public VueAccueil(ListModel<LigneEquipe> equipes, ListModel<LigneTournoi> tournois, ListModel<LigneMatche> matches) {
		setOpaque(false);
		setBorder(new EmptyBorder(20, 20, 20, 20));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
		setLayout(gridBagLayout);
		
		JPanel panelClassement = new JPanel();
		panelClassement.setBorder(BorderFactory.createLineBorder(Vue.ROSE_CONTOURS,2));
		panelClassement.setBackground(Vue.BACKGROUND_MAIN);
		GridBagConstraints gbc_panelClassement = new GridBagConstraints();
		gbc_panelClassement.weighty = 0.3;
		gbc_panelClassement.insets = new Insets(0, 0, 20, 0);
		gbc_panelClassement.fill = GridBagConstraints.BOTH;
		gbc_panelClassement.gridx = 0;
		gbc_panelClassement.gridy = 0;
		add(panelClassement, gbc_panelClassement);
		GridBagLayout gbl_panelClassement = new GridBagLayout();
		gbl_panelClassement.columnWidths = new int[]{0, 0};
		gbl_panelClassement.rowHeights = new int[] {0};
		gbl_panelClassement.columnWeights = new double[]{1.0, 0.0};
		gbl_panelClassement.rowWeights = new double[]{0.0, 1.0};
		panelClassement.setLayout(gbl_panelClassement);
		
		JLabel labelTitreClassement = new JLabel("Classement des équipes année précédente");
		labelTitreClassement.setFont(MaFont.getFontTitre1());
		labelTitreClassement.setForeground(Vue.BLANC);
		GridBagConstraints gbcLabelTitreClassement = new GridBagConstraints();
		gbcLabelTitreClassement.insets = new Insets(0, 20, 5, 5);
		gbcLabelTitreClassement.weighty = 0.2;
		gbcLabelTitreClassement.weightx = 1.0;
		gbcLabelTitreClassement.fill = GridBagConstraints.BOTH;
		gbcLabelTitreClassement.gridx = 0;
		gbcLabelTitreClassement.gridy = 0;
		panelClassement.add(labelTitreClassement, gbcLabelTitreClassement);
		
		listeEquipes = new JList<LigneEquipe>(equipes);
		listeEquipes.setCellRenderer(new EquipeCellRenderer());
		listeEquipes.setBackground(Vue.TRANSPARENT);
		
		JScrollPane scrollPaneEquipe = new JScrollPane(listeEquipes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneEquipe.setOpaque(false);
		scrollPaneEquipe.setWheelScrollingEnabled(true);
		scrollPaneEquipe.setBorder(null);
		System.out.println(scrollPaneEquipe.getViewport().getView());
		GridBagConstraints gbcScrollPaneEquipe = new GridBagConstraints();
		gbcScrollPaneEquipe.insets = new Insets(0, 0, 0, 5);
		gbcScrollPaneEquipe.fill = GridBagConstraints.BOTH;
		gbcScrollPaneEquipe.gridx = 0;
		gbcScrollPaneEquipe.gridy = 1;
		panelClassement.add(scrollPaneEquipe, gbcScrollPaneEquipe);
		
		JScrollBar scrollBarEquipe = scrollPaneEquipe.getVerticalScrollBar();
		scrollBarEquipe.setOpaque(false);
		
		scrollBarEquipe.setForeground(Vue.ROSE_CONTOURS);
		scrollBarEquipe.setUI(new CustomScrollBarUI());
		
		JPanel panelBas = new JPanel();
		panelBas.setOpaque(false);
		GridBagConstraints gbc_panelBas = new GridBagConstraints();
		gbc_panelBas.weighty = 0.7;
		gbc_panelBas.fill = GridBagConstraints.BOTH;
		gbc_panelBas.gridx = 0;
		gbc_panelBas.gridy = 1;
		add(panelBas, gbc_panelBas);
		GridBagLayout gbl_panelBas = new GridBagLayout();
		gbl_panelBas.columnWidths = new int[]{0, 0, 0};
		gbl_panelBas.rowHeights = new int[] {0};
		gbl_panelBas.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelBas.rowWeights = new double[]{1.0};
		panelBas.setLayout(gbl_panelBas);
		
		JPanel panelTournois = new JPanel();
		panelTournois.setBackground(Vue.BACKGROUND_MAIN);
		GridBagConstraints gbc_panelTournois = new GridBagConstraints();
		gbc_panelTournois.insets = new Insets(0, 0, 0, 10);
		gbc_panelTournois.weighty = 1.0;
		gbc_panelTournois.weightx = 0.5;
		gbc_panelTournois.fill = GridBagConstraints.BOTH;
		gbc_panelTournois.gridx = 0;
		gbc_panelTournois.gridy = 0;
		panelBas.add(panelTournois, gbc_panelTournois);
		GridBagLayout gbl_panelTournois = new GridBagLayout();
		gbl_panelTournois.columnWidths = new int[] {0};
		gbl_panelTournois.rowHeights = new int[] {0, 0};
		gbl_panelTournois.columnWeights = new double[]{1.0};
		gbl_panelTournois.rowWeights = new double[]{0.0,  1.0};
		panelTournois.setLayout(gbl_panelTournois);
		panelTournois.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Vue.ROSE_CONTOURS,2), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		JLabel labelTitreTournois = new JLabel("Tournois");
		labelTitreTournois.setForeground(Vue.BLANC);
		labelTitreTournois.setFont(MaFont.getFontTitre1());
		GridBagConstraints gbcLabelTitreTournois = new GridBagConstraints();
		gbcLabelTitreTournois.fill = GridBagConstraints.BOTH;
		gbcLabelTitreTournois.insets = new Insets(5,20,0,0);
		gbcLabelTitreTournois.gridx = 0;
		gbcLabelTitreTournois.gridy = 0;
		panelTournois.add(labelTitreTournois, gbcLabelTitreTournois);
		
		listeTournois = new JList<LigneTournoi>(tournois);
		listeTournois.setCellRenderer(new TournoiCellRenderer());
		listeTournois.setBackground(Vue.TRANSPARENT);
		
		
		JScrollPane scrollPaneTournois = new JScrollPane(listeTournois, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneTournois.setWheelScrollingEnabled(true);
		scrollPaneTournois.setOpaque(false);
		scrollPaneTournois.setBorder(null);
		GridBagConstraints gbcScrollPaneTournois = new GridBagConstraints();
		gbcScrollPaneTournois.fill = GridBagConstraints.BOTH;
		gbcScrollPaneTournois.insets = new Insets(0,20,0,0);
		gbcScrollPaneTournois.gridx = 0;
		gbcScrollPaneTournois.gridy = 1;
		panelTournois.add(scrollPaneTournois, gbcScrollPaneTournois);
		
		scrollPaneTournois.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		
		JPanel panelMatchs = new JPanel();
		panelMatchs.setBackground(Vue.BACKGROUND_MAIN);
		GridBagConstraints gbc_panelMatchs = new GridBagConstraints();
		gbc_panelMatchs.insets = new Insets(0, 10, 0, 0);
		gbc_panelMatchs.weighty = 1.0;
		gbc_panelMatchs.weightx = 0.5;
		gbc_panelMatchs.fill = GridBagConstraints.BOTH;
		gbc_panelMatchs.gridx = 1;
		gbc_panelMatchs.gridy = 0;
		panelBas.add(panelMatchs, gbc_panelMatchs);
		GridBagLayout gblPanelMatch= new GridBagLayout();
		gblPanelMatch.columnWidths = new int[] {0};
		gblPanelMatch.rowHeights = new int[] {0, 0};
		gblPanelMatch.columnWeights = new double[]{1.0};
		gblPanelMatch.rowWeights = new double[]{0.0,  1.0};
		panelMatchs.setLayout(gblPanelMatch);
		
		listeMatches = new JList<LigneMatche>(matches);
		listeMatches.setCellRenderer(new MatchCellRenderer());
		listeMatches.setBackground(Vue.TRANSPARENT);
		
		JScrollPane scrollPaneMatch = new JScrollPane(listeMatches,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints gbcScrollPaneMatchs = new GridBagConstraints();
		gbcScrollPaneMatchs.fill = GridBagConstraints.BOTH;
		gbcScrollPaneMatchs.insets = new Insets(0,20,0,0);
		gbcScrollPaneMatchs.gridx = 0;
		gbcScrollPaneMatchs.gridy = 1;
		panelMatchs.add(scrollPaneMatch,gbcScrollPaneMatchs);
		scrollPaneMatch.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPaneMatch.setOpaque(false);
		scrollPaneMatch.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		
		JLabel labelTitreMatch = new JLabel("Derniers Matchs");
		labelTitreMatch.setForeground(Vue.BLANC);
		labelTitreMatch.setFont(MaFont.getFontTitre1());
		labelTitreMatch.setHorizontalAlignment(SwingConstants.LEADING);
		GridBagConstraints gbcLabelTitreMatch = new GridBagConstraints();
		gbcLabelTitreMatch.fill = GridBagConstraints.BOTH;
		gbcLabelTitreMatch.insets = new Insets(5,20,0,0);
		gbcLabelTitreMatch.gridx = 0;
		gbcLabelTitreMatch.gridy = 0;
		panelMatchs.add(labelTitreMatch, gbcLabelTitreMatch);
		panelMatchs.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Vue.ROSE_CONTOURS, 2),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
	}
	
	public void setListeEquipes(DefaultListModel<LigneEquipe> equipes) {
		this.listeEquipes.setModel(equipes);
	}
	
	public void setListeTournois(DefaultListModel<LigneTournoi> tournois) {
		this.listeTournois.setModel(tournois);
	}
	
	public void setListeMatches(DefaultListModel<LigneMatche> matches) {
		this.listeMatches.setModel(matches);
	}

}
