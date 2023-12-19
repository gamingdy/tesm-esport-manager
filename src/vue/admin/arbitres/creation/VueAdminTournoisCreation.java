package vue.admin.arbitres.creation;

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
	private JTextField textfieldPrenom;
	private JButton boutonValider;
	private JButton boutonAnnuler;
	private JLabel labelLogo;
	private JLabel btnAjoutTournois;
	private JPanel panelTournois;
	private DefaultListModel<LigneTournoi> model;
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
		
		JLabel icone = new JLabel("assets/userGrand.png");
		GridBagConstraints gbcIcone = new GridBagConstraints();
		gbcIcone.fill = GridBagConstraints.BOTH;
		gbcIcone.insets = new Insets(0, 0, 0, 50);
		gbcIcone.gridx = 0;
		gbcIcone.gridy = 0;
		gbcIcone.gridheight = 2;
		gbcIcone.weightx = 1F / 4F;
		gbcIcone.weighty = 1F / 5F;
		panelTop.add(icone,gbcIcone);
		

		JPanel champNom = new JPanel();
		champNom.setLayout(new GridLayout(2, 1, 10, 0));
		champNom.setOpaque(false);
		JLabel labelNom = new JLabel("Nom");
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
		gbcNom.gridx = 1;
		gbcNom.gridy = 0;
		gbcNom.weightx = 3F / 4F;
		gbcNom.weighty = 1F / 4F;
		panelTop.add(champNom, gbcNom);


		JPanel champPrenom = new JPanel();
		champPrenom.setLayout(new GridLayout(2, 1, 10, 0));
		champPrenom.setOpaque(false);
		JLabel labelPrenom = new JLabel("Nom");
		labelPrenom.setForeground(CustomColor.BLANC);
		labelPrenom.setFont(MaFont.getFontTitre2());
		champPrenom.add(labelPrenom);
		textfieldPrenom = new JTextField();
		textfieldPrenom.setBackground(CustomColor.BACKGROUND_MENU);
		textfieldPrenom.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		textfieldPrenom.setForeground(CustomColor.BLANC);
		textfieldPrenom.setCaretColor(CustomColor.BLANC);
		textfieldPrenom.setFont(MaFont.getFontTitre3());
		champPrenom.add(textfieldPrenom);
		GridBagConstraints gbcPrenom = new GridBagConstraints();
		gbcPrenom.fill = GridBagConstraints.BOTH;
		gbcPrenom.insets = new Insets(0, 0, 0, 50);
		gbcPrenom.gridx = 1;
		gbcPrenom.gridy = 1;
		panelTop.add(champPrenom, gbcPrenom);

		panelTournois = new JPanel();
		panelTournois.setBackground(CustomColor.BACKGROUND_MAIN);
		panelTournois.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2));
		GridBagLayout gblPanelTournois = new GridBagLayout();
		panelTournois.setLayout(gblPanelTournois);
		GridBagConstraints gbcPanelTournois = new GridBagConstraints();
		gbcPanelTournois.fill = GridBagConstraints.BOTH;
		gbcPanelTournois.insets = new java.awt.Insets(0, 50, 0, 0);
		gbcPanelTournois.gridx = 1;
		gbcPanelTournois.gridy = 0;
		gbcPanelTournois.gridheight = 4;
		gbcPanelTournois.weightx = 1 / 2F;
		gbcPanelTournois.weighty = 5F / 7F;

		panelTop.add(panelTournois, gbcPanelTournois);

		JLabel labelTournoi = new JLabel("Tournois");
		labelTournoi.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
		labelTournoi.setForeground(CustomColor.BLANC);
		labelTournoi.setFont(MaFont.getFontTitre2());
		GridBagConstraints gbcLabelTournois = new GridBagConstraints();
		gbcLabelTournois.fill = GridBagConstraints.BOTH;
		gbcLabelTournois.gridx = 0;
		gbcLabelTournois.gridy = 0;
		gbcLabelTournois.weightx = 1;
		gbcLabelTournois.weighty = 1F / 9F;
		panelTournois.add(labelTournoi, gbcLabelTournois);

		btnAjoutTournois = new JLabel(Vue.resize(new ImageIcon("assets/plus.png"), 20, 20));
		btnAjoutTournois.setHorizontalTextPosition(JLabel.TRAILING);
		btnAjoutTournois.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));

		GridBagConstraints gbcAjout = new GridBagConstraints();
		gbcAjout.fill = GridBagConstraints.HORIZONTAL;
		gbcAjout.gridx = 1;
		gbcAjout.gridy = 0;
		panelTournois.add(btnAjoutTournois, gbcAjout);

		model = new DefaultListModel<LigneTournoi>();
		JList<LigneTournoi> l = new JList<LigneTournoi>(model);
		l.setPreferredSize(new Dimension());
		l.setLayout(new GridLayout(0, 1));
		l.setBackground(CustomColor.BACKGROUND_MAIN);
		l.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, CustomColor.ROSE_CONTOURS));
		l.setCellRenderer(new ListCellRenderer<LigneTournoi>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends LigneTournoi> list, LigneTournoi value, int index,
														  boolean isSelected, boolean cellHasFocus) {
				JPanel panelItem = new JPanel();
				JLabel nom = new JLabel(value.getNom());
				nom.setForeground(CustomColor.BLANC);
				nom.setFont(MaFont.getFontTitre3());
				panelItem.add(nom);
				JLabel date = new JLabel(value.getNom());
				date.setForeground(CustomColor.BLANC.darker());
				date.setFont(MaFont.getFontTitre4());
				panelItem.add(date);
				return panelItem;
			}

		});

		GridBagConstraints gbcJ = new GridBagConstraints();
		gbcJ.fill = GridBagConstraints.BOTH;
		gbcJ.gridwidth = 2;
		gbcJ.gridx = 0;
		gbcJ.gridy = 1;
		gbcJ.weighty = 1F;
		panelTournois.add(l, gbcJ);


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
		this.btnAjoutTournois.addMouseListener(controleur);
	}


	public void clearField() {
		this.textfieldNom.setText("");
		this.textfieldPrenom.setText("");
		this.comboboxNiveaux.setSelectedItem(null);
		resetTournois();
		
	}

	private void resetTournois() {
		model = new DefaultListModel<LigneTournoi>();
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
	public void setEquipe(String nom, String dateDébut, String dateFin, int i) {
		
		this.model.set(i, new LigneTournoi(nom,dateDébut,dateFin));
	}

	public JButton getBoutonValider() {
		return this.boutonValider;
	}

	public String getTextfieldNom() {
		return this.textfieldNom.getText();
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
		return this.btnAjoutTournois;
	}

	public JButton getBoutonAnnuler() {
		return this.boutonAnnuler;
	}
}


