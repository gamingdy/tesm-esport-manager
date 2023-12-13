package vue.admin.equipes.creation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import modele.Pays;
import vue.common.CustomColor;
import vue.common.MaFont;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.DefaultComboBoxModel;

public class VueAdminEquipesCreation extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelDrapeau;

	/**
	 * Create the panel.
	 */
	public VueAdminEquipesCreation() {

		setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};

		setLayout(gridBagLayout);
		setOpaque(false);
		JPanel panelTop = new JPanel();
		panelTop.setPreferredSize(new Dimension(0, 0));
		panelTop.setOpaque(false);
		GridBagConstraints gbcPanelTop = new GridBagConstraints();
		gbcPanelTop.weighty = 1;
		gbcPanelTop.fill = GridBagConstraints.BOTH;
		gbcPanelTop.gridx = 0;
		gbcPanelTop.gridy = 0;
		add(panelTop, gbcPanelTop);
		GridLayout gl = new GridLayout();
		gl.setColumns(2);
		gl.setRows(2);
		gl.setHgap(100);
		gl.setVgap(100);
		panelTop.setLayout(gl);

		JPanel panelChamps = new JPanel();
		panelChamps.setLayout(new GridLayout(2, 1, 40, 0));
		panelChamps.setOpaque(false);

		JPanel champNom = new JPanel();
		champNom.setLayout(new GridLayout(2, 1, 10, 0));
		champNom.setOpaque(false);
		JLabel labelNom = new JLabel("Nom de l'équipe");
		labelNom.setForeground(CustomColor.BLANC);
		labelNom.setFont(MaFont.getFontTitre2());
		champNom.add(labelNom);
		JTextField textfieldNom = new JTextField();
		textfieldNom.setBackground(CustomColor.BACKGROUND_MENU);
		textfieldNom.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2));
		textfieldNom.setForeground(CustomColor.BLANC);
		textfieldNom.setFont(MaFont.getFontTitre3());
		champNom.add(textfieldNom);

		JPanel champPays = new JPanel();
		champPays.setLayout(new GridLayout(2, 1, 10, 0));
		champPays.setOpaque(false);
		JLabel labelPays = new JLabel("Pays");
		labelPays.setForeground(CustomColor.BLANC);
		labelPays.setFont(MaFont.getFontTitre2());
		champPays.add(labelPays);
		DefaultComboBoxModel<Pays> model = new DefaultComboBoxModel<Pays>(Pays.values());
		JComboBox<Pays> comboboxPays = new JComboBox<Pays>(model);
		//Pour render les cells mais pas nécessaire si juste le texte
//		comboboxPays.setRenderer(new ListCellRenderer<Country>() {
//		@Override
//		public Component getListCellRendererComponent(JList<? extends Country> list, Country value, int index,
//				boolean isSelected, boolean cellHasFocus) {
//			JLabel panel = new JLabel(value.getNom());
//			return panel;
//		}
//		});
		champPays.add(comboboxPays);

		panelChamps.add(champNom);
		panelChamps.add(champPays);
		panelTop.add(panelChamps);

		//panel pour centrer le drapeau
		JPanel panelDrapeau = new JPanel();
		panelDrapeau.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3));
		panelDrapeau.setBackground(CustomColor.BACKGROUND_MAIN);
		labelDrapeau = new JLabel("");
		labelDrapeau.setOpaque(true);
		labelDrapeau.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		labelDrapeau.setBackground(CustomColor.BACKGROUND_TEST);
		labelDrapeau.setIcon(new ImageIcon("assets/country-flags/fr.png"));
		labelDrapeau.setHorizontalAlignment(JLabel.CENTER);
		labelDrapeau.setVerticalAlignment(JLabel.CENTER);
		panelDrapeau.add(labelDrapeau);
		panelTop.add(panelDrapeau);

		JPanel panelJoueurs = new JPanel();
		panelJoueurs.setBackground(CustomColor.BACKGROUND_MAIN);
		panelJoueurs.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2));
		GridBagLayout gblPanelJoueurs = new GridBagLayout();
		panelJoueurs.setLayout(gblPanelJoueurs);
		panelTop.add(panelJoueurs);

		JLabel labelJoueurs = new JLabel("Joueurs");
		labelJoueurs.setPreferredSize(new Dimension());
		labelJoueurs.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, CustomColor.ROSE_CONTOURS));
		labelJoueurs.setForeground(CustomColor.BLANC);
		labelJoueurs.setFont(MaFont.getFontTitre3());
		GridBagConstraints gbcLabelJoueurs = new GridBagConstraints();
		gbcLabelJoueurs.fill = GridBagConstraints.BOTH;
		gbcLabelJoueurs.gridx = 0;
		gbcLabelJoueurs.gridy = 0;
		gbcLabelJoueurs.weightx = 1;
		gbcLabelJoueurs.weighty = 1F / 7F;
		panelJoueurs.add(labelJoueurs, gbcLabelJoueurs);

		DefaultListModel<String> lmJoueurs = new DefaultListModel<String>();
		JList<String> listeJoueurs = new JList<String>(lmJoueurs);
		listeJoueurs.setCellRenderer(new ListCellRenderer<String>() {
			@Override
			public Component getListCellRendererComponent(JList list, String value, int index, boolean isSelected, boolean cellHasFocus) {
				JLabel panel = new JLabel(value);
				panel.setOpaque(false);
				panel.setForeground(CustomColor.BLANC);
				panel.setFont(MaFont.getFontTitre4());
				return panel;
			}

		});
		listeJoueurs.setOpaque(false);
		listeJoueurs.setPreferredSize(new Dimension());
		lmJoueurs.addElement("Joueur1");
		lmJoueurs.addElement("Joueur2");
		lmJoueurs.addElement("Joueur3");
		lmJoueurs.addElement("Joueur4");
		lmJoueurs.addElement("Joueur5");
		GridBagConstraints gbcListeJoueurs = new GridBagConstraints();
		gbcListeJoueurs.fill = GridBagConstraints.BOTH;
		gbcListeJoueurs.gridx = 0;
		gbcListeJoueurs.gridy = 1;
		gbcListeJoueurs.weightx = 1;
		gbcListeJoueurs.weighty = 6F / 7F;
		panelJoueurs.add(listeJoueurs, gbcListeJoueurs);

		JLabel labelLogo = new JLabel("Insérer logo");
		labelLogo.setOpaque(true);
		labelLogo.setBackground(CustomColor.BACKGROUND_MAIN);
		labelLogo.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2));
		labelLogo.setForeground(CustomColor.BLANC);
		labelLogo.setHorizontalAlignment(JLabel.CENTER);
		labelLogo.setVerticalAlignment(JLabel.CENTER);
		panelTop.add(labelLogo);

		JPanel panelBot = new JPanel();
		panelBot.setOpaque(false);
		panelBot.setPreferredSize(new Dimension(0, 0));
		GridBagConstraints gbcPanelBot = new GridBagConstraints();
		gbcPanelBot.fill = GridBagConstraints.BOTH;
		gbcPanelBot.weighty = 0.2;
		gbcPanelBot.gridx = 0;
		gbcPanelBot.gridy = 1;
		add(panelBot, gbcPanelBot);

		panelBot.add(new JButton("Ajout"));
	}

}
