package vue;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JSlider;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.SpringLayout;

public class PageAccueil extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PageAccueil() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panelClassement = new JPanel();
		GridBagConstraints gbc_panelClassement = new GridBagConstraints();
		gbc_panelClassement.weighty = 0.3;
		gbc_panelClassement.insets = new Insets(0, 0, 5, 0);
		gbc_panelClassement.fill = GridBagConstraints.BOTH;
		gbc_panelClassement.gridx = 0;
		gbc_panelClassement.gridy = 0;
		add(panelClassement, gbc_panelClassement);
		GridBagLayout gbl_panelClassement = new GridBagLayout();
		gbl_panelClassement.columnWidths = new int[]{0, 0};
		gbl_panelClassement.rowHeights = new int[]{0, 0, 0};
		gbl_panelClassement.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelClassement.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panelClassement.setLayout(gbl_panelClassement);
		
		JLabel lblNewLabel = new JLabel("Classement des équipes année précédente");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.weighty = 0.2;
		gbc_lblNewLabel.weightx = 1.0;
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelClassement.add(lblNewLabel, gbc_lblNewLabel);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.weighty = 0.8;
		gbc_list.weightx = 1.0;
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panelClassement.add(list, gbc_list);
		
		JPanel panelBas = new JPanel();
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
		GridBagConstraints gbc_panelTournois = new GridBagConstraints();
		gbc_panelTournois.insets = new Insets(0, 0, 0, 3);
		gbc_panelTournois.weighty = 1.0;
		gbc_panelTournois.weightx = 0.5;
		gbc_panelTournois.fill = GridBagConstraints.BOTH;
		gbc_panelTournois.gridx = 0;
		gbc_panelTournois.gridy = 0;
		panelBas.add(panelTournois, gbc_panelTournois);
		GridBagLayout gbl_panelTournois = new GridBagLayout();
		gbl_panelTournois.columnWidths = new int[] {0};
		gbl_panelTournois.rowHeights = new int[] {0, 0, 0, 0, 0};
		gbl_panelTournois.columnWeights = new double[]{1.0};
		gbl_panelTournois.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0};
		panelTournois.setLayout(gbl_panelTournois);
		panelTournois.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red,1), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		JLabel lblNewLabel_4 = new JLabel("Tournois");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.weighty = 0.15;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 0;
		panelTournois.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel("En cours");
		lblNewLabel_3.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.weighty = 0.1;
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 1;
		panelTournois.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Précédents");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.weighty = 0.1;
		gbc_lblNewLabel_2.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		panelTournois.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JPanel panelTournoiEnCours = new PanelNomTournoi("Pas de tournoi en cours");
		GridBagLayout gridBagLayout_1 = (GridBagLayout) panelTournoiEnCours.getLayout();
		gridBagLayout_1.rowHeights = new int[] {0};
		GridBagConstraints gbc_list_3 = new GridBagConstraints();
		gbc_list_3.insets = new Insets(0, 0, 5, 0);
		gbc_list_3.weighty = 0.1;
		gbc_list_3.fill = GridBagConstraints.NONE;
		gbc_list_3.gridx = 0;
		gbc_list_3.gridy = 2;
		panelTournois.add(panelTournoiEnCours, gbc_list_3);
		panelTournoiEnCours.setSize((int) (lblNewLabel_2.getWidth()*0.8), panelTournoiEnCours.getHeight());
		
		JPanel panelTournoisFinis = new JPanel();
		GridBagConstraints gbc_panelTournoisFinis = new GridBagConstraints();
		gbc_panelTournoisFinis.fill = GridBagConstraints.BOTH;
		gbc_panelTournoisFinis.gridx = 0;
		gbc_panelTournoisFinis.gridy = 4;
		panelTournois.add(panelTournoisFinis, gbc_panelTournoisFinis);
		GridBagLayout gbl_panelTournoisFinis = new GridBagLayout();
		gbl_panelTournoisFinis.columnWidths = new int[] {0};
		gbl_panelTournoisFinis.rowHeights = new int[] {0};
		gbl_panelTournoisFinis.columnWeights = new double[]{0.0};
		gbl_panelTournoisFinis.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelTournoisFinis.setLayout(gbl_panelTournoisFinis);
		
		JPanel panelTournoiFini1 = new PanelNomTournoi("Tournoi fini numéro 1 (plus récent)");
		
		GridBagConstraints gbc_panelTournoiFini1 = new GridBagConstraints();
		gbc_panelTournoiFini1.fill = GridBagConstraints.BOTH;
		gbc_panelTournoiFini1.insets = new Insets(0, 0, 5, 0);
		gbc_panelTournoiFini1.gridx = 0;
		gbc_panelTournoiFini1.gridy = 0;
		panelTournoisFinis.add(panelTournoiFini1, gbc_panelTournoiFini1);
				
				
		JPanel panelTournoiFini2 = new PanelNomTournoi("Tournoi fini numéro 2 ");
		GridBagConstraints gbc_panelTournoiFini2 = new GridBagConstraints();
		gbc_panelTournoiFini2.fill = GridBagConstraints.BOTH;
		gbc_panelTournoiFini2.insets = new Insets(0, 0, 5, 0);
		gbc_panelTournoiFini2.gridx = 0;
		gbc_panelTournoiFini2.gridy = 1;
		panelTournoisFinis.add(panelTournoiFini2, gbc_panelTournoiFini2);
		JPanel panelTournoiFini3 = new PanelNomTournoi("Tournoi fini numéro 3 (plus ancien)");
		GridBagConstraints gbc_panelTournoiFini3 = new GridBagConstraints();
		gbc_panelTournoiFini3.fill = GridBagConstraints.BOTH;
		gbc_panelTournoiFini3.gridx = 0;
		gbc_panelTournoiFini3.gridy = 2;
		panelTournoisFinis.add(panelTournoiFini3, gbc_panelTournoiFini3);
		
		JPanel panelMatchs = new JPanel();
		GridBagConstraints gbc_panelMatchs = new GridBagConstraints();
		gbc_panelMatchs.insets = new Insets(0, 3, 0, 0);
		gbc_panelMatchs.weighty = 1.0;
		gbc_panelMatchs.weightx = 0.5;
		gbc_panelMatchs.fill = GridBagConstraints.BOTH;
		gbc_panelMatchs.gridx = 1;
		gbc_panelMatchs.gridy = 0;
		panelBas.add(panelMatchs, gbc_panelMatchs);
		panelMatchs.setLayout(new BorderLayout(0, 0));
		
		JList list_1 = new JList();
		panelMatchs.add(list_1);
		
		JLabel lblNewLabel_1 = new JLabel("Derniers Matchs");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelMatchs.add(lblNewLabel_1, BorderLayout.NORTH);

	}

}
