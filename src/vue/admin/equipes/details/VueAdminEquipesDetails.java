package vue.admin.equipes.details;

import controlleur.admin.equipes.EquipeCreationControlleur;
import modele.Pays;
import vue.Vue;
import vue.common.CustomColor;
import vue.common.MaFont;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;

public class VueAdminEquipesDetails extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<Pays> comboboxPays;
	private JTextField textfieldNom;
	private JButton boutonValider;
	private JButton boutonAnnuler;
	private JLabel labelLogo;
	private JLabel btnAjoutJoueurs;
	private JPanel panelJoueurs;
	private DefaultListModel<String> modelJoueurs;
	private DefaultListModel<String> modelSaisons;
	private JTextField textfieldWR;
	private JPanel panelSaisons;
	private JLabel btnAjoutSaisons;

	/**
	 * Create the panel.
	 */
	public VueAdminEquipesDetails() {

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
		GridLayout gl = new GridLayout(2, 2, 100, 50);
		panelTop.setLayout(gl);

		labelLogo = new JLabel("");
		labelLogo.setOpaque(true);
		labelLogo.setBackground(CustomColor.BACKGROUND_MAIN);
		labelLogo.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2));
		labelLogo.setForeground(CustomColor.BLANC);
		labelLogo.setHorizontalAlignment(JLabel.CENTER);
		labelLogo.setVerticalAlignment(JLabel.CENTER);
		labelLogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelTop.add(labelLogo);

		JPanel panelChamps = new JPanel();
		panelChamps.setLayout(new GridLayout(3, 1, 20, 0));
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
		DefaultComboBoxModel<Pays> modelPays = new DefaultComboBoxModel<Pays>();
		modelPays.addElement(null);
		Arrays.stream(Pays.values()).forEach(p -> modelPays.addElement(p));
		comboboxPays = new JComboBox<Pays>(modelPays);
		comboboxPays.setRenderer(new javax.swing.ListCellRenderer<Pays>() {
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

		JPanel champWR = new JPanel();
		champWR.setLayout(new GridLayout(2, 1, 10, 0));
		champWR.setOpaque(false);
		JLabel labelWR = new JLabel("World rank");
		labelWR.setForeground(CustomColor.BLANC);
		labelWR.setFont(MaFont.getFontTitre2());
		champWR.add(labelWR);
		textfieldWR = new JTextField();
		textfieldWR.setBackground(CustomColor.BACKGROUND_MENU);
		textfieldWR.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		textfieldWR.setForeground(CustomColor.BLANC);
		textfieldWR.setCaretColor(CustomColor.BLANC);
		textfieldWR.setFont(MaFont.getFontTitre3());
		champWR.add(textfieldWR);

		panelChamps.add(champNom);
		panelChamps.add(champPays);
		panelChamps.add(champWR);
		panelTop.add(panelChamps);

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
		labelJoueurs.setVerticalTextPosition(JLabel.CENTER);
		GridBagConstraints gbcLabelJoueurs = new GridBagConstraints();
		gbcLabelJoueurs.fill = GridBagConstraints.BOTH;
		gbcLabelJoueurs.gridx = 0;
		gbcLabelJoueurs.gridy = 0;
		gbcLabelJoueurs.weightx = 1;
		gbcLabelJoueurs.weighty = 2F / 7F;
		panelJoueurs.add(labelJoueurs, gbcLabelJoueurs);

		btnAjoutJoueurs = new JLabel(Vue.resize(new ImageIcon("assets/plus.png"), 20, 20));
		btnAjoutJoueurs.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 20));
		btnAjoutJoueurs.setHorizontalTextPosition(JLabel.TRAILING);
		btnAjoutJoueurs.setCursor(new Cursor(Cursor.HAND_CURSOR));

		GridBagConstraints gbcAjout = new GridBagConstraints();
		gbcAjout.fill = GridBagConstraints.NONE;
		gbcAjout.gridx = 1;
		gbcAjout.gridy = 0;

		panelJoueurs.add(btnAjoutJoueurs, gbcAjout);

		modelJoueurs = new DefaultListModel<String>();
		JList<String> l = new JList<String>(modelJoueurs);
		l.setLayout(new GridLayout(0, 1));
		l.setBackground(CustomColor.BACKGROUND_MAIN);
		l.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, CustomColor.ROSE_CONTOURS));
		l.setCellRenderer(new ListCellRenderer<String>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
														  boolean isSelected, boolean cellHasFocus) {
				JLabel l = new JLabel(value);
				l.setForeground(CustomColor.BLANC);
				l.setFont(MaFont.getFontTitre3());
				return l;
			}

		});

		GridBagConstraints gbcL = new GridBagConstraints();
		gbcL.fill = GridBagConstraints.HORIZONTAL;
		gbcL.gridx = 0;
		gbcL.gridwidth = 2;
		gbcL.gridy = 1;
		gbcL.weighty = 5F / 7F;
		panelJoueurs.add(l, gbcL);

		panelSaisons = new JPanel();
		panelSaisons.setBackground(CustomColor.BACKGROUND_MAIN);
		panelSaisons.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2));
		GridBagLayout gblPanelSaisons = new GridBagLayout();
		panelSaisons.setLayout(gblPanelSaisons);
		panelTop.add(panelSaisons);

		JLabel labelSaisons = new JLabel("Saisons");
		labelSaisons.setPreferredSize(new Dimension());
		labelSaisons.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
		labelSaisons.setForeground(CustomColor.BLANC);
		labelSaisons.setFont(MaFont.getFontTitre2());
		labelSaisons.setVerticalTextPosition(JLabel.CENTER);
		GridBagConstraints gbcLabelSaisons = new GridBagConstraints();
		gbcLabelSaisons.fill = GridBagConstraints.BOTH;
		gbcLabelSaisons.gridx = 0;
		gbcLabelSaisons.gridy = 0;
		gbcLabelSaisons.weightx = 1;
		gbcLabelSaisons.weighty = 2F / 7F;
		panelSaisons.add(labelSaisons, gbcLabelSaisons);

		btnAjoutSaisons = new JLabel("Ajouter à la saison actuelle");
		btnAjoutSaisons.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(20, 0, 0, 20),
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS,2)));
		btnAjoutSaisons.setHorizontalTextPosition(JLabel.TRAILING);
		btnAjoutSaisons.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAjoutSaisons.setFont(MaFont.getFontTitre3());
		btnAjoutSaisons.setForeground(CustomColor.BLANC);

		panelSaisons.add(btnAjoutSaisons, gbcAjout);

		modelSaisons = new DefaultListModel<String>();

		JList<String> ls = new JList<String>(modelSaisons);
		ls.setLayout(new GridLayout(0, 1));
		ls.setBackground(CustomColor.BACKGROUND_MAIN);
		ls.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, CustomColor.ROSE_CONTOURS));
		ls.setCellRenderer(new ListCellRenderer<String>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
														  boolean isSelected, boolean cellHasFocus) {
				JLabel l = new JLabel(value);
				l.setForeground(CustomColor.BLANC);
				l.setFont(MaFont.getFontTitre3());
				return l;
			}

		});

		panelSaisons.add(ls, gbcL);


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
		panelBot.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 0));

		boutonAnnuler = new JButton("Retour");
		boutonAnnuler.setBackground(CustomColor.BACKGROUND_MENU.darker());
		boutonAnnuler.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS.darker(), 3), BorderFactory.createEmptyBorder(10, 40, 10, 40)));
		boutonAnnuler.setForeground(CustomColor.BLANC.darker());
		boutonAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBot.add(boutonAnnuler);

		boutonValider = new JButton("Modifier");
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
		this.labelLogo.setText("Logo ici");
		this.labelLogo.setIcon(null);
		this.comboboxPays.setSelectedItem(null);
		this.modelJoueurs.clear();
		this.modelSaisons.clear();
	}

	public void setNom(String nom) {
		this.textfieldNom.setText(nom);
	}

	public void setPays(Pays pays) {
		this.comboboxPays.setSelectedItem(pays);
	}

	public void setWorldRank(int wr) {
		this.textfieldWR.setText("" + wr);
	}

	public void setLogo(Icon logo) {
		this.labelLogo.setIcon(logo);
	}

	public void setJoueurs(List<String> joueurs) {
		this.modelJoueurs.removeAllElements();
		joueurs.forEach(s -> modelJoueurs.addElement(s));
	}

	public void setSaisons(List<Integer> saisons) {
		this.modelSaisons.removeAllElements();
		saisons.forEach(s -> modelSaisons.addElement("" + s));
	}

	public JComboBox<Pays> getComboboxPays() {
		return this.comboboxPays;
	}

	public JLabel getLabelLogo() {
		return this.labelLogo;
	}

	public void addSaison(int annee) {
		modelSaisons.addElement("" + annee);
	}

	public JLabel getbtnAjoutJoueurs() {
		return this.btnAjoutJoueurs;
	}

	public JLabel getbtnAjoutSaisons() {
		return this.btnAjoutSaisons;
	}
}


