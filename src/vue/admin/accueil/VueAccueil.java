package vue.admin.accueil;

import controlleur.admin.accueil.AccueilControlleur;
import vue.common.CustomColor;
import vue.common.CustomScrollBarUI;
import vue.common.MaFont;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class VueAccueil extends JPanel {

	private static final long serialVersionUID = 1L;

	private JList<LigneMatche> listeMatches;

	private JList<LigneTournoi> listeTournois;

	private JList<LigneEquipe> listeEquipes;
	private transient AccueilControlleur controlleur;

	private JButton boutonImprimer;

	/**
	 * Create the panel.
	 *
	 * @param equipes  liste des équipes à afficher
	 * @param tournois liste des tournois à afficher
	 * @param matches  liste des matchs à afficher
	 */
	public VueAccueil(ListModel<LigneEquipe> equipes, ListModel<LigneTournoi> tournois, ListModel<LigneMatche> matches) {
		setOpaque(false);


		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
		setLayout(gridBagLayout);
		JPanel panelClassement = new JPanel();
		panelClassement.setPreferredSize(new Dimension(0, 0));
		panelClassement.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2));
		panelClassement.setBackground(CustomColor.BACKGROUND_MAIN);
		GridBagConstraints gbcPanelClassement = new GridBagConstraints();
		gbcPanelClassement.weighty = 0.3;
		gbcPanelClassement.insets = new Insets(0, 0, 20, 0);
		gbcPanelClassement.fill = GridBagConstraints.BOTH;
		gbcPanelClassement.gridx = 0;
		gbcPanelClassement.gridy = 0;
		add(panelClassement, gbcPanelClassement);
		GridBagLayout gblPanelClassement = new GridBagLayout();
		gblPanelClassement.columnWidths = new int[]{0, 0};
		gblPanelClassement.rowHeights = new int[]{0};
		gblPanelClassement.columnWeights = new double[]{1.0, 0.0};
		gblPanelClassement.rowWeights = new double[]{0.0, 1.0};
		panelClassement.setLayout(gblPanelClassement);

		JLabel labelTitreClassement = new JLabel("Classement des équipes de la saison actuelle");
		labelTitreClassement.setFont(MaFont.getFontTitre1());
		labelTitreClassement.setForeground(CustomColor.BLANC);
		GridBagConstraints gbcLabelTitreClassement = new GridBagConstraints();
		gbcLabelTitreClassement.insets = new Insets(0, 20, 5, 5);
		gbcLabelTitreClassement.weighty = 0.2;
		gbcLabelTitreClassement.weightx = 1.0;
		gbcLabelTitreClassement.fill = GridBagConstraints.BOTH;
		gbcLabelTitreClassement.gridx = 0;
		gbcLabelTitreClassement.gridy = 0;
		panelClassement.add(labelTitreClassement, gbcLabelTitreClassement);

		boutonImprimer = new JButton(new ImageIcon("assets/imprimante.png"));
		boutonImprimer.setFocusPainted(false);
		boutonImprimer.setContentAreaFilled(false);
		boutonImprimer.setBorder(null);
		GridBagConstraints gbcBoutonImprimert = new GridBagConstraints();
		gbcBoutonImprimert.fill = GridBagConstraints.BOTH;
		gbcBoutonImprimert.insets = new Insets(0, 0, 0, 5);
		gbcBoutonImprimert.gridx = 1;
		gbcBoutonImprimert.gridy = 0;
		panelClassement.add(boutonImprimer, gbcBoutonImprimert);

		listeEquipes = new JList<>(equipes);
		listeEquipes.setCellRenderer(new EquipeCellRenderer());
		listeEquipes.setBackground(CustomColor.BACKGROUND_MAIN);

		JScrollPane scrollPaneEquipe = new JScrollPane(listeEquipes, javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneEquipe.setWheelScrollingEnabled(true);
		scrollPaneEquipe.setBorder(BorderFactory.createMatteBorder(0, 20, 10, 20, CustomColor.BACKGROUND_MAIN));
		GridBagConstraints gbcScrollPaneEquipe = new GridBagConstraints();
		gbcScrollPaneEquipe.insets = new Insets(0, 0, 0, 5);
		gbcScrollPaneEquipe.fill = GridBagConstraints.BOTH;
		gbcScrollPaneEquipe.gridx = 0;
		gbcScrollPaneEquipe.gridy = 1;
		panelClassement.add(scrollPaneEquipe, gbcScrollPaneEquipe);

		JScrollBar scrollBarEquipe = scrollPaneEquipe.getVerticalScrollBar();

		scrollBarEquipe.setForeground(CustomColor.ROSE_CONTOURS);
		scrollBarEquipe.setUI(new CustomScrollBarUI());

		JPanel panelBas = new JPanel();
		panelBas.setOpaque(false);
		GridBagConstraints gbcPanelBas = new GridBagConstraints();
		gbcPanelBas.weighty = 0.7;
		gbcPanelBas.fill = GridBagConstraints.BOTH;
		gbcPanelBas.gridx = 0;
		gbcPanelBas.gridy = 1;
		add(panelBas, gbcPanelBas);
		GridBagLayout gblPanelBas = new GridBagLayout();
		gblPanelBas.columnWidths = new int[]{0, 0, 0};
		gblPanelBas.rowHeights = new int[]{0};
		gblPanelBas.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gblPanelBas.rowWeights = new double[]{1.0};
		panelBas.setLayout(gblPanelBas);

		JPanel panelTournois = new JPanel();
		panelTournois.setBackground(CustomColor.BACKGROUND_MAIN);
		panelTournois.setPreferredSize(new Dimension(0, 0));
		GridBagConstraints gbcPanelTournois = new GridBagConstraints();
		gbcPanelTournois.insets = new Insets(0, 0, 0, 10);
		gbcPanelTournois.weightx = 0.5;
		gbcPanelTournois.fill = GridBagConstraints.BOTH;
		gbcPanelTournois.gridx = 0;
		gbcPanelTournois.gridy = 0;
		panelBas.add(panelTournois, gbcPanelTournois);
		GridBagLayout gblPanelTournois = new GridBagLayout();
		gblPanelTournois.columnWidths = new int[]{0};
		gblPanelTournois.rowHeights = new int[]{0, 0};
		gblPanelTournois.columnWeights = new double[]{1.0};
		gblPanelTournois.rowWeights = new double[]{0.0, 1.0};
		panelTournois.setLayout(gblPanelTournois);
		panelTournois.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JLabel labelTitreTournois = new JLabel("Tournois");
		labelTitreTournois.setForeground(CustomColor.BLANC);
		labelTitreTournois.setFont(MaFont.getFontTitre1());
		GridBagConstraints gbcLabelTitreTournois = new GridBagConstraints();
		gbcLabelTitreTournois.fill = GridBagConstraints.BOTH;
		gbcLabelTitreTournois.insets = new Insets(5, 20, 0, 0);
		gbcLabelTitreTournois.gridx = 0;
		gbcLabelTitreTournois.gridy = 0;
		panelTournois.add(labelTitreTournois, gbcLabelTitreTournois);

		listeTournois = new JList<>(tournois);
		listeTournois.setCellRenderer(new TournoiCellRenderer());
		listeTournois.setBackground(CustomColor.TRANSPARENT);


		JScrollPane scrollPaneTournois = new JScrollPane(listeTournois, javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneTournois.setWheelScrollingEnabled(true);
		scrollPaneTournois.setOpaque(false);
		scrollPaneTournois.setBorder(null);
		GridBagConstraints gbcScrollPaneTournois = new GridBagConstraints();
		gbcScrollPaneTournois.fill = GridBagConstraints.BOTH;
		gbcScrollPaneTournois.insets = new Insets(0, 20, 0, 0);
		gbcScrollPaneTournois.gridx = 0;
		gbcScrollPaneTournois.gridy = 1;
		panelTournois.add(scrollPaneTournois, gbcScrollPaneTournois);

		scrollPaneTournois.getVerticalScrollBar().setUI(new CustomScrollBarUI());

		JPanel panelMatchs = new JPanel();
		panelMatchs.setBackground(CustomColor.BACKGROUND_MAIN);
		panelMatchs.setPreferredSize(new Dimension(0, 0));
		GridBagConstraints gbcPanelMatchs = new GridBagConstraints();
		gbcPanelMatchs.insets = new Insets(0, 10, 0, 0);
		gbcPanelMatchs.weightx = 0.5;
		gbcPanelMatchs.fill = GridBagConstraints.BOTH;
		gbcPanelMatchs.gridx = 1;
		gbcPanelMatchs.gridy = 0;
		panelBas.add(panelMatchs, gbcPanelMatchs);
		GridBagLayout gblPanelMatch = new GridBagLayout();
		gblPanelMatch.columnWidths = new int[]{0};
		gblPanelMatch.rowHeights = new int[]{0, 0};
		gblPanelMatch.columnWeights = new double[]{1.0};
		gblPanelMatch.rowWeights = new double[]{0.0, 1.0};
		panelMatchs.setLayout(gblPanelMatch);

		listeMatches = new JList<>(matches);
		listeMatches.setCellRenderer(new MatchCellRenderer());
		listeMatches.setBackground(CustomColor.TRANSPARENT);

		JScrollPane scrollPaneMatch = new JScrollPane(listeMatches, javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints gbcScrollPaneMatchs = new GridBagConstraints();
		gbcScrollPaneMatchs.fill = GridBagConstraints.BOTH;
		gbcScrollPaneMatchs.insets = new Insets(0, 20, 0, 0);
		gbcScrollPaneMatchs.gridx = 0;
		gbcScrollPaneMatchs.gridy = 1;
		panelMatchs.add(scrollPaneMatch, gbcScrollPaneMatchs);
		scrollPaneMatch.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPaneMatch.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		scrollPaneMatch.setOpaque(false);
		scrollPaneMatch.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

		JLabel labelTitreMatch = new JLabel("Derniers Matchs");
		labelTitreMatch.setForeground(CustomColor.BLANC);
		labelTitreMatch.setFont(MaFont.getFontTitre1());
		labelTitreMatch.setHorizontalAlignment(SwingConstants.LEADING);
		GridBagConstraints gbcLabelTitreMatch = new GridBagConstraints();
		gbcLabelTitreMatch.fill = GridBagConstraints.BOTH;
		gbcLabelTitreMatch.insets = new Insets(5, 20, 0, 0);
		gbcLabelTitreMatch.gridx = 0;
		gbcLabelTitreMatch.gridy = 0;
		panelMatchs.add(labelTitreMatch, gbcLabelTitreMatch);
		panelMatchs.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 1), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

	}

	public VueAccueil() {
		this(new DefaultListModel<>(), new DefaultListModel<>(), new DefaultListModel<>());
	}

	public void setListeEquipes(DefaultListModel<LigneEquipe> equipes) {
		this.listeEquipes.setModel(equipes);
	}

	public void setListeTournois(DefaultListModel<LigneTournoi> tournois) {

		this.listeTournois.setModel(tournois);
	}

	public void setControlleur(AccueilControlleur controlleur) {
		this.controlleur = controlleur;
		this.boutonImprimer.addActionListener(controlleur);
	}

	public void setListeMatches(DefaultListModel<LigneMatche> matches) {
		this.listeMatches.setModel(matches);
	}

	public JButton getBoutonImprimer() {
		return this.boutonImprimer;
	}


	public void updateControlleur() {
		this.controlleur.update();
	}

}
