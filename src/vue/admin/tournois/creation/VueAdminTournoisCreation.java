package vue.admin.tournois.creation;

import controlleur.admin.tournois.TournoiCréationControlleur;
import modele.Niveau;
import vue.Vue;
import vue.common.CustomColor;
import vue.common.MaFont;

import java.util.List;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.util.Arrays;
import java.util.stream.Collectors;

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

public class VueAdminTournoisCreation extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textfieldNom;
	private JTextField textfieldDateDebut;
	private JTextField textfieldDateFin;
	private JLabel labelEquipes[];
	private JButton boutonValider;
	private JButton boutonAnnuler;
	private JLabel labelLogo;
	private JLabel btnAjoutEquipes;
	private JPanel panelEquipes;
	private DefaultListModel<JLabel> model;
	private JComboBox<Niveau> comboboxNiveaux;

	/**
	 * Create the panel.
	 */
	public VueAdminTournoisCreation() {

		setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};

		setLayout(gridBagLayout);
		setOpaque(false);

		JPanel panelTop = new JPanel();
		panelTop.setLayout(new GridBagLayout());
		panelTop.setPreferredSize(new Dimension(0, 0));
		panelTop.setOpaque(false);
		GridBagConstraints gbcPanelTop = new GridBagConstraints();
		gbcPanelTop.weighty = 1;
		gbcPanelTop.fill = GridBagConstraints.BOTH;
		gbcPanelTop.gridx = 0;
		gbcPanelTop.gridy = 0;
		add(panelTop, gbcPanelTop);

		JPanel champNom = new JPanel();
		champNom.setLayout(new GridLayout(2, 1, 10, 0));
		champNom.setOpaque(false);
		JLabel labelNom = new JLabel("Nom du tournoi");
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
		GridBagConstraints gbcNom = new GridBagConstraints();
		gbcNom.fill = GridBagConstraints.BOTH;
		gbcNom.insets = new Insets(0, 0, 0, 50);
		gbcNom.gridx = 0;
		gbcNom.gridy = 0;
		gbcNom.weightx = 1F / 2F;
		gbcNom.weighty = 1F / 5F;
		panelTop.add(champNom, gbcNom);


		JPanel champNiveau = new JPanel();
		champNiveau.setLayout(new GridLayout(2, 1, 10, 0));
		champNiveau.setOpaque(false);
		JLabel labelNiveau = new JLabel("Niveau");
		labelNiveau.setForeground(CustomColor.BLANC);
		labelNiveau.setFont(MaFont.getFontTitre2());
		champNiveau.add(labelNiveau);
		DefaultComboBoxModel<Niveau> modelNiveaux = new DefaultComboBoxModel<Niveau>();
		modelNiveaux.addElement(null);
		Arrays.stream(Niveau.values()).forEach(p -> modelNiveaux.addElement(p));
		comboboxNiveaux = new JComboBox<Niveau>(modelNiveaux);
		comboboxNiveaux.setRenderer(new ListCellRenderer<Niveau>() {
			@Override
			public Component getListCellRendererComponent(JList<? extends Niveau> list, Niveau value, int index,
														  boolean isSelected, boolean cellHasFocus) {
				JLabel panel = new JLabel();
				if (value != null) {
					panel.setText(value.getNom());
				} else {
					panel.setText("Choissez le niveau du tournoi");
				}
				return panel;
			}
		});
		champNiveau.add(comboboxNiveaux);
		GridBagConstraints gbcNiveau = new GridBagConstraints();
		gbcNiveau.fill = GridBagConstraints.BOTH;
		gbcNiveau.insets = new Insets(0, 0, 0, 50);
		gbcNiveau.gridx = 0;
		gbcNiveau.gridy = 1;
		gbcNiveau.weightx = 1F / 2F;
		gbcNiveau.weighty = 1F / 5F;
		panelTop.add(champNiveau, gbcNiveau);


		JPanel champDateDebut = new JPanel();
		champDateDebut.setLayout(new GridLayout(2, 1, 10, 0));
		champDateDebut.setOpaque(false);
		JLabel labelDateDebut = new JLabel("Date de début");
		labelDateDebut.setForeground(CustomColor.BLANC);
		labelDateDebut.setFont(MaFont.getFontTitre2());
		champDateDebut.add(labelDateDebut);
		textfieldDateDebut = new JTextField();
		textfieldDateDebut.setBackground(CustomColor.BACKGROUND_MENU);
		textfieldDateDebut.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		textfieldDateDebut.setForeground(CustomColor.BLANC);
		textfieldDateDebut.setCaretColor(CustomColor.BLANC);
		textfieldDateDebut.setFont(MaFont.getFontTitre3());
		champDateDebut.add(textfieldDateDebut);
		GridBagConstraints gbcDateDebut = new GridBagConstraints();
		gbcDateDebut.fill = GridBagConstraints.BOTH;
		gbcDateDebut.insets = new Insets(0, 0, 0, 50);
		gbcDateDebut.gridx = 0;
		gbcDateDebut.gridy = 2;
		gbcDateDebut.weightx = 1F / 2F;
		gbcDateDebut.weighty = 1F / 5F;
		panelTop.add(champDateDebut, gbcDateDebut);


		JPanel champDateFin = new JPanel();
		champDateFin.setLayout(new GridLayout(2, 1, 10, 0));
		champDateFin.setOpaque(false);
		JLabel labelDateFin = new JLabel("Date de fin");
		labelDateFin.setForeground(CustomColor.BLANC);
		labelDateFin.setFont(MaFont.getFontTitre2());
		champDateFin.add(labelDateFin);
		textfieldDateFin = new JTextField();
		textfieldDateFin.setBackground(CustomColor.BACKGROUND_MENU);
		textfieldDateFin.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		textfieldDateFin.setForeground(CustomColor.BLANC);
		textfieldDateFin.setCaretColor(CustomColor.BLANC);
		textfieldDateFin.setFont(MaFont.getFontTitre3());
		champDateFin.add(textfieldDateFin);
		GridBagConstraints gbcDateFin = new GridBagConstraints();
		gbcDateFin.fill = GridBagConstraints.BOTH;
		gbcDateFin.insets = new Insets(0, 0, 0, 50);
		gbcDateFin.gridx = 0;
		gbcDateFin.gridy = 3;
		gbcDateFin.weightx = 1F / 2F;
		gbcDateFin.weighty = 1F / 5F;
		panelTop.add(champDateFin, gbcDateFin);

		panelEquipes = new JPanel();
		panelEquipes.setBackground(CustomColor.BACKGROUND_MAIN);
		panelEquipes.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2));
		GridBagLayout gblPanelEquipes = new GridBagLayout();
		panelEquipes.setLayout(gblPanelEquipes);
		GridBagConstraints gbcPanelEquipes = new GridBagConstraints();
		gbcPanelEquipes.fill = GridBagConstraints.BOTH;
		gbcPanelEquipes.insets = new java.awt.Insets(0, 50, 0, 0);
		gbcPanelEquipes.gridx = 1;
		gbcPanelEquipes.gridy = 0;
		gbcPanelEquipes.gridheight = 4;
		gbcPanelEquipes.weightx = 1 / 2F;
		gbcPanelEquipes.weighty = 5F / 7F;

		panelTop.add(panelEquipes, gbcPanelEquipes);

		JLabel labelEquipe = new JLabel("Équipes");
		labelEquipe.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
		labelEquipe.setForeground(CustomColor.BLANC);
		labelEquipe.setFont(MaFont.getFontTitre2());
		GridBagConstraints gbcLabelEquipe = new GridBagConstraints();
		gbcLabelEquipe.fill = GridBagConstraints.BOTH;
		gbcLabelEquipe.gridx = 0;
		gbcLabelEquipe.gridy = 0;
		gbcLabelEquipe.weightx = 1;
		gbcLabelEquipe.weighty = 1F / 9F;
		panelEquipes.add(labelEquipe, gbcLabelEquipe);

		btnAjoutEquipes = new JLabel(Vue.resize(new ImageIcon("assets/plus.png"), 20, 20));
		btnAjoutEquipes.setHorizontalTextPosition(JLabel.TRAILING);
		btnAjoutEquipes.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));

		GridBagConstraints gbcAjout = new GridBagConstraints();
		gbcAjout.fill = GridBagConstraints.HORIZONTAL;
		gbcAjout.gridx = 1;
		gbcAjout.gridy = 0;
		panelEquipes.add(btnAjoutEquipes, gbcAjout);

		model = new DefaultListModel<JLabel>();
		model.addElement(new JLabel());
		model.addElement(new JLabel());
		model.addElement(new JLabel());
		model.addElement(new JLabel());
		model.addElement(new JLabel());
		model.addElement(new JLabel());
		model.addElement(new JLabel());
		JList<JLabel> l = new JList<JLabel>(model);
		l.setLayout(new GridLayout(0, 1));
		l.setBackground(CustomColor.BACKGROUND_MAIN);
		l.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, CustomColor.ROSE_CONTOURS));
		l.setCellRenderer(new ListCellRenderer<JLabel>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends JLabel> list, JLabel value, int index,
														  boolean isSelected, boolean cellHasFocus) {
				return value;
			}

		});

		GridBagConstraints gbcJ = new GridBagConstraints();
		gbcJ.fill = GridBagConstraints.BOTH;
		gbcJ.gridwidth = 2;
		gbcJ.gridx = 0;
		gbcJ.gridy = 1;
		gbcJ.weighty = 1F;
		panelEquipes.add(l, gbcJ);


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

	}

	/**
	 * setControlleur est une méthode qui permet d'ajouter les controlleurs au bouton, c'est par défaut dans Jbutton
	 *
	 * @param controleur
	 */
	public void setControleur(TournoiCréationControlleur controleur) {
		this.boutonValider.addActionListener(controleur);
		this.boutonAnnuler.addActionListener(controleur);
		this.btnAjoutEquipes.addMouseListener(controleur);
	}


	public void clearField() {
		this.textfieldNom.setText("");
		this.labelLogo.setText("Insérer logo");
		this.labelLogo.setIcon(null);

	}

	public String getNomEquipe() {
		return this.textfieldNom.getText();
	}

	public JLabel getLabelLogo() {
		return this.labelLogo;
	}

	/**
	 * Set le nom d'un joueur
	 *
	 * @param nom nom du joueur
	 * @param i   indice du joueur dans le tableau ==> <strong>0 à 4</strong>
	 */
	public void setEquipe(String nom, ImageIcon logo, int i) {
		JLabel equipe = new JLabel(nom);
		equipe.setIcon(Vue.resize(logo, 30, 30));
		equipe.setForeground(CustomColor.BLANC);
		equipe.setFont(MaFont.getFontTitre3());
		equipe.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, CustomColor.ROSE_CONTOURS));
		this.model.add(i, equipe);
	}

	public JButton getBoutonValider() {
		return this.boutonValider;
	}

	public String getTextfieldNom() {
		return this.textfieldNom.getText();
	}

	public String getTextfieldDateDebut() {
		return this.textfieldDateDebut.getText();
	}

	public String getTextfieldDateFin() {
		return this.textfieldDateFin.getText();
	}

	public Niveau getNiveau() {
		if (this.comboboxNiveaux.getSelectedItem() == null) {
			return null;
		}
		return (Niveau) this.comboboxNiveaux.getSelectedItem();
	}

	public List<String> getEquipes() {
		return Arrays.stream(model.toArray()).map(x -> ((JLabel) x).getText()).collect(Collectors.toList());
	}

	public JLabel getBtnAjoutEquipes() {
		return this.btnAjoutEquipes;
	}

	public JButton getBoutonAnnuler() {
		return this.boutonAnnuler;
	}
}


