package vue.admin.equipes.creation;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import controlleur.EquipeCreationControlleur;
import modele.Pays;
import vue.common.CustomColor;
import vue.common.MaFont;

import java.awt.GridLayout;
import java.awt.Image;
import java.util.Arrays;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.DefaultComboBoxModel;

public class VueAdminEquipesCreation extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelDrapeau;
	private JComboBox<Pays> comboboxPays;
	private JTextField textfieldNom;
	private JButton boutonValider;
	private JButton boutonAnnuler;
	private JLabel labelLogo;

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
		textfieldNom = new JTextField();
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
		DefaultComboBoxModel<Pays> model = new DefaultComboBoxModel<Pays>();
		model.addElement(null);
		Arrays.stream(Pays.values()).forEach(p -> model.addElement(p));
		comboboxPays = new JComboBox<Pays>(model);
		comboboxPays.setRenderer(new ListCellRenderer<Pays>() {
			@Override
			public Component getListCellRendererComponent(JList<? extends Pays> list, Pays value, int index,
														  boolean isSelected, boolean cellHasFocus) {
				JLabel panel = new JLabel();
				if (value != null) {
					panel.setText(value.getNom());
				} else {
					panel.setText("Choissez le pays de l'équipe");
				}
				return panel;
			}
		});
		champPays.add(comboboxPays);

		panelChamps.add(champNom);
		panelChamps.add(champPays);
		panelTop.add(panelChamps);

		labelDrapeau = new JLabel();
		this.setDrapeau("earth");
		labelDrapeau.setOpaque(true);
		labelDrapeau.setBackground(CustomColor.BACKGROUND_MAIN);
		labelDrapeau.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2));
		labelDrapeau.setForeground(CustomColor.BLANC);
		labelDrapeau.setHorizontalAlignment(JLabel.CENTER);
		labelDrapeau.setVerticalAlignment(JLabel.CENTER);
		panelTop.add(labelDrapeau);

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

		labelLogo = new JLabel("Insérer logo");
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


		panelBot.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		((FlowLayout) panelBot.getLayout()).setHgap(150);

		boutonAnnuler = new JButton("Annuler");
		boutonAnnuler.setBackground(CustomColor.BACKGROUND_MENU.darker());
		boutonAnnuler.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS.darker(), 3), BorderFactory.createEmptyBorder(10, 40, 10, 40)));
		boutonAnnuler.setForeground(CustomColor.BLANC.darker());
		panelBot.add(boutonAnnuler);

		boutonValider = new JButton("Ajouter");
		boutonValider.setBackground(CustomColor.BACKGROUND_MENU.brighter());
		boutonValider.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3), BorderFactory.createEmptyBorder(10, 40, 10, 40)));
		boutonValider.setForeground(CustomColor.BLANC);
		panelBot.add(boutonValider);

		setControleur(new EquipeCreationControlleur(this));
	}

	// setControlleur est une méthode qui permet d'ajouter les controlleurs au bouton, c'est par défaut dans Jbutton
	public void setControleur(EquipeCreationControlleur controleur) {
		this.comboboxPays.addItemListener(controleur);
		this.boutonValider.addActionListener(controleur);
		this.boutonAnnuler.addActionListener(controleur);
		this.labelLogo.addMouseListener(controleur);
	}


	public void clearField() {
		this.textfieldNom.setText("");
		this.labelLogo.setText("Insérer logo");
		this.labelLogo.setIcon(null);
		this.setDrapeau("earth");
		this.comboboxPays.setSelectedItem(null);

	}

	public JComboBox<Pays> getComboboxPays() {
		return this.comboboxPays;
	}

	public String getChampPaysEquipe() {
		if (this.comboboxPays.getSelectedItem() != null) {
			return this.comboboxPays.getSelectedItem().toString();
		}
		return null;
	}

	public String getNomEquipe() {
		return this.textfieldNom.getText();
	}

	public JLabel getLabelLogo() {
		return this.labelLogo;
	}

	public JLabel getLabelDrapeau() {
		return this.labelDrapeau;
	}

	public void setDrapeau(String code) {
		ImageIcon img = new ImageIcon("assets/country-flags/png1000px/" + code + ".png");
		Image newimg = img.getImage().getScaledInstance(350, 200, java.awt.Image.SCALE_SMOOTH);
		labelDrapeau.setIcon(new ImageIcon(newimg));
	}
}
