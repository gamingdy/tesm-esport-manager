package vue.admin.equipes.creation;

import controlleur.admin.equipes.EquipeCreationControlleur;
import modele.Pays;
import vue.Vue;
import vue.common.CustomColor;
import vue.common.CustomComboBox;
import vue.common.MaFont;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Arrays;

public class VueAdminEquipesCreation extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelDrapeau;
	private CustomComboBox<Pays> comboboxPays;
	private JTextField textfieldNom;
	private JButton boutonValider;
	private JButton boutonAnnuler;
	private JLabel labelLogo;
	private JLabel btnAjoutJoueurs;
	private JPanel panelJoueurs;
	private DefaultListModel<String> model;

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
		GridLayout gl = new GridLayout(2, 2, 100, 100);
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
		textfieldNom.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		textfieldNom.setForeground(CustomColor.BLANC);
		textfieldNom.setCaretColor(CustomColor.BLANC);
		textfieldNom.setFont(MaFont.getFontTitre3());
		champNom.add(textfieldNom);

		JPanel champPays = new JPanel();
		champPays.setLayout(new GridLayout(2, 1, 10, 0));
		champPays.setOpaque(false);
		JLabel labelPays = new JLabel("Pays");
		labelPays.setForeground(CustomColor.BLANC);
		labelPays.setFont(MaFont.getFontTitre2());
		champPays.add(labelPays);
		DefaultComboBoxModel<Pays> modelPays = new DefaultComboBoxModel<>();
		modelPays.addElement(null);
		Arrays.stream(Pays.values()).forEach(modelPays::addElement);
		comboboxPays = new CustomComboBox<>(modelPays);
		comboboxPays.style();
		comboboxPays.setFont(MaFont.getFontTitre4());
		comboboxPays.setRenderer((JList<? extends Pays> list, Pays value, int index, boolean isSelected, boolean cellHasFocus) -> {
				JLabel panel = new JLabel();
				panel.setOpaque(true);
				if (value == null) {
					if (index == -1) {
						panel.setText("Choissez le pays de l'équipe");
					} else {
						return new JLabel();
					}
				} else {
					panel.setText(value.getNom());
				}
				panel.setBackground(CustomColor.BACKGROUND_MAIN);
				panel.setFocusable(false);
				panel.setFont(MaFont.getFontTitre4());
				panel.setBorder(BorderFactory.createEmptyBorder(3, 5, 0, 0));
				if (isSelected) {
					panel.setForeground(CustomColor.ROSE_CONTOURS.darker());
				} else {
					panel.setForeground(CustomColor.BLANC);
				}
				return panel;
			});
		champPays.add(comboboxPays);

		panelChamps.add(champNom);
		panelChamps.add(champPays);
		panelTop.add(panelChamps);

		labelDrapeau = new JLabel();
		this.setDrapeau("earth");
		labelDrapeau.setMaximumSize(new Dimension(labelDrapeau.getIcon().getIconHeight(), labelDrapeau.getIcon().getIconWidth()));
		labelDrapeau.setBackground(CustomColor.BACKGROUND_MAIN);
		labelDrapeau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelDrapeau.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
		panelTop.add(labelDrapeau);

		panelJoueurs = new JPanel();
		panelJoueurs.setBackground(CustomColor.BACKGROUND_MAIN);
		panelJoueurs.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2));
		GridBagLayout gblPanelJoueurs = new GridBagLayout();
		panelJoueurs.setLayout(gblPanelJoueurs);
		panelTop.add(panelJoueurs);

		JLabel labelJoueurs = new JLabel("Joueurs");
		labelJoueurs.setPreferredSize(new Dimension());
		labelJoueurs.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
		labelJoueurs.setForeground(CustomColor.BLANC);
		labelJoueurs.setFont(MaFont.getFontTitre2());
		labelJoueurs.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
		GridBagConstraints gbcLabelJoueurs = new GridBagConstraints();
		gbcLabelJoueurs.fill = GridBagConstraints.BOTH;
		gbcLabelJoueurs.gridx = 0;
		gbcLabelJoueurs.gridy = 0;
		gbcLabelJoueurs.weightx = 1;
		gbcLabelJoueurs.weighty = 2F / 7F;
		panelJoueurs.add(labelJoueurs, gbcLabelJoueurs);

		btnAjoutJoueurs = new JLabel(Vue.resize(new ImageIcon("assets/plus.png"), 20, 20));
		btnAjoutJoueurs.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 20));
		btnAjoutJoueurs.setHorizontalTextPosition(javax.swing.SwingConstants.TRAILING);
		btnAjoutJoueurs.setCursor(new Cursor(Cursor.HAND_CURSOR));

		GridBagConstraints gbcAjout = new GridBagConstraints();
		gbcAjout.fill = GridBagConstraints.BOTH;
		gbcAjout.gridx = 1;
		gbcAjout.gridy = 0;
		panelJoueurs.add(btnAjoutJoueurs, gbcAjout);

		model = new DefaultListModel<>();

		JList<String> l = new JList<>(model);
		l.setLayout(new GridLayout(0, 1));
		l.setBackground(CustomColor.BACKGROUND_MAIN);
		l.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, CustomColor.ROSE_CONTOURS));
		l.setCellRenderer((JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) -> {
				JLabel lItem = new JLabel(value);
				lItem.setForeground(CustomColor.BLANC);
				lItem.setFont(MaFont.getFontTitre3());
				return lItem;
			});

		GridBagConstraints gbcJ = new GridBagConstraints();
		gbcJ.fill = GridBagConstraints.HORIZONTAL;
		gbcJ.gridx = 0;
		gbcJ.gridwidth = 2;
		gbcJ.gridy = 1;
		gbcJ.weighty = 5F / 7F;
		panelJoueurs.add(l, gbcJ);

		labelLogo = new JLabel("Insérer logo");
		labelLogo.setOpaque(true);
		labelLogo.setBackground(CustomColor.BACKGROUND_MAIN);
		labelLogo.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2));
		labelLogo.setForeground(CustomColor.BLANC);
		labelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelLogo.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
		labelLogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelTop.add(labelLogo);

		JPanel panelBot = new JPanel();
		panelBot.setOpaque(false);
		panelBot.setPreferredSize(new Dimension(0, 0));
		GridBagConstraints gbcPanelBot = new GridBagConstraints();
		gbcPanelBot.fill = GridBagConstraints.BOTH;
		// ...

		gbcPanelBot.weighty = 0.2;
		gbcPanelBot.gridx = 0;
		gbcPanelBot.gridy = 1;
		add(panelBot, gbcPanelBot);

		panelBot.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		panelBot.setLayout(new FlowLayout(javax.swing.SwingConstants.CENTER, 150, 0));

		boutonAnnuler = new JButton("Annuler");
		boutonAnnuler.setBackground(CustomColor.BACKGROUND_MENU.darker());
		boutonAnnuler.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS.darker(), 3), BorderFactory.createEmptyBorder(10, 40, 10, 40)));
		boutonAnnuler.setForeground(CustomColor.BLANC.darker());
		boutonAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBot.add(boutonAnnuler);

		boutonValider = new JButton("Ajouter");
		boutonValider.setBackground(CustomColor.BACKGROUND_MENU.brighter());
		boutonValider.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3), BorderFactory.createEmptyBorder(10, 40, 10, 40)));
		boutonValider.setForeground(CustomColor.BLANC);
		boutonValider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBot.add(boutonValider);

	}

	/**
	 * setControlleur est une méthode qui permet d'ajouter les controlleurs au bouton, c'est par défaut dans Jbutton
	 *
	 * @param controleur
	 */
	public void setControleur(EquipeCreationControlleur controleur) {
		this.comboboxPays.addItemListener(controleur);
		this.boutonValider.addActionListener(controleur);
		this.boutonAnnuler.addActionListener(controleur);
		this.labelLogo.addMouseListener(controleur);
		this.btnAjoutJoueurs.addMouseListener(controleur);
	}


	public void clearField() {
		this.textfieldNom.setText("");
		this.labelLogo.setText("Insérer logo");
		this.labelLogo.setIcon(null);
		this.setDrapeau("earth");
		this.comboboxPays.setSelectedItem(null);
		this.model.clear();

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
		ImageIcon img = new ImageIcon("assets/country-flags/png200px/" + code + ".png");

		labelDrapeau.setIcon(img);
	}

	/**
	 * Set le nom d'un joueur
	 *
	 * @param nom nom du joueur
	 * @param i   indice du joueur dans le tableau ==> <strong>0 à 4</strong>
	 */
	public void setJoueur(String nom, int i) {
		model.addElement(nom);
	}

	public Object[] getJoueurs() {
		return model.toArray();
	}

	public JLabel getbtnAjoutJoueurs() {
		return this.btnAjoutJoueurs;
	}

	public void activerBoutonAjoutJoueur(boolean b) {
		btnAjoutJoueurs.setEnabled(b);
	}
}


