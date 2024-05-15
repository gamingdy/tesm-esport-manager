package vue.admin.arbitres.creation;

import controlleur.admin.arbitres.ArbitresCreationControlleur;
import modele.Niveau;
import vue.Vue;
import vue.common.CustomColor;
import vue.common.MaFont;

import javax.swing.BorderFactory;
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
import java.awt.Insets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VueAdminArbitresCreation extends JPanel {

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
	private JTextField textfieldTelephone;

	/**
	 * Create the panel.
	 */
	public VueAdminArbitresCreation() {

		setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};

		setLayout(gridBagLayout);
		setOpaque(false);

		JPanel panelTop = new JPanel();
		GridBagLayout gblPanelTop = new GridBagLayout();
		gblPanelTop.columnWeights = new double[]{0.0, 1.0};
		gblPanelTop.columnWidths = new int[]{87, 0};
		panelTop.setLayout(gblPanelTop);
		panelTop.setPreferredSize(new Dimension(0, 0));
		panelTop.setOpaque(false);
		GridBagConstraints gbcPanelTop = new GridBagConstraints();
		gbcPanelTop.weighty = 1;
		gbcPanelTop.fill = GridBagConstraints.BOTH;
		gbcPanelTop.gridx = 0;
		gbcPanelTop.gridy = 0;
		add(panelTop, gbcPanelTop);

		JLabel icone = new JLabel(Vue.resize(new ImageIcon("assets/grandUser.png"), 200, 200));
		icone.setPreferredSize(new Dimension());
		GridBagConstraints gbcIcone = new GridBagConstraints();
		gbcIcone.fill = GridBagConstraints.BOTH;
		gbcIcone.gridx = 0;
		gbcIcone.gridy = 0;
		gbcIcone.gridheight = 3;
		gbcIcone.weightx = 1F / 2F;
		gbcIcone.weighty = 1F / 3F;
		panelTop.add(icone, gbcIcone);


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
		gbcNom.gridx = 1;
		gbcNom.gridy = 0;
		gbcNom.weightx = 1F / 2F;
		gbcNom.weighty = 1F / 6F;
		panelTop.add(champNom, gbcNom);


		JPanel champPrenom = new JPanel();
		champPrenom.setLayout(new GridLayout(2, 1, 10, 0));
		champPrenom.setOpaque(false);
		JLabel labelPrenom = new JLabel("Prénom");
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
		gbcPrenom.gridx = 1;
		gbcPrenom.gridy = 1;
		gbcPrenom.weightx = 1F / 2F;
		gbcPrenom.weighty = 1F / 6F;
		panelTop.add(champPrenom, gbcPrenom);

		JPanel champTelephone = new JPanel();
		champTelephone.setLayout(new GridLayout(2, 1, 10, 0));
		champTelephone.setOpaque(false);
		JLabel labelTelephone = new JLabel("Numéro de téléphone");
		labelTelephone.setForeground(CustomColor.BLANC);
		labelTelephone.setFont(MaFont.getFontTitre2());
		champTelephone.add(labelTelephone);
		textfieldTelephone = new JTextField();
		textfieldTelephone.setBackground(CustomColor.BACKGROUND_MENU);
		textfieldTelephone.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		textfieldTelephone.setForeground(CustomColor.BLANC);
		textfieldTelephone.setCaretColor(CustomColor.BLANC);
		textfieldTelephone.setFont(MaFont.getFontTitre3());
		champTelephone.add(textfieldTelephone);
		GridBagConstraints gbcTelephone = new GridBagConstraints();
		gbcTelephone.fill = GridBagConstraints.BOTH;
		gbcTelephone.gridx = 1;
		gbcTelephone.gridy = 2;
		gbcTelephone.weightx = 1F / 2F;
		gbcTelephone.weighty = 1F / 6F;
		panelTop.add(champTelephone, gbcTelephone);

		panelTournois = new JPanel();
		panelTournois.setBackground(CustomColor.BACKGROUND_MAIN);
		panelTournois.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2));
		GridBagLayout gblPanelTournois = new GridBagLayout();
		panelTournois.setLayout(gblPanelTournois);
		GridBagConstraints gbcPanelTournois = new GridBagConstraints();
		gbcPanelTournois.gridwidth = 2;
		gbcPanelTournois.fill = GridBagConstraints.BOTH;
		gbcPanelTournois.insets = new Insets(50, 0, 0, 0);
		gbcPanelTournois.gridx = 0;
		gbcPanelTournois.gridy = 3;
		gbcPanelTournois.gridheight = 4;
		gbcPanelTournois.weightx = 1F;
		gbcPanelTournois.weighty = 2F / 3F;

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
		btnAjoutTournois.setHorizontalTextPosition(javax.swing.SwingConstants.TRAILING);
		btnAjoutTournois.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
		btnAjoutTournois.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		GridBagConstraints gbcAjout = new GridBagConstraints();
		gbcAjout.fill = GridBagConstraints.HORIZONTAL;
		gbcAjout.gridx = 1;
		gbcAjout.gridy = 0;
		panelTournois.add(btnAjoutTournois, gbcAjout);

		model = new DefaultListModel<>();
		JList<LigneTournoi> l = new JList<>(model);
		l.setPreferredSize(new Dimension());
		l.setLayout(new GridLayout(0, 1));
		l.setBackground(CustomColor.BACKGROUND_MAIN);
		l.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, CustomColor.ROSE_CONTOURS));
		l.setCellRenderer((JList<? extends LigneTournoi> list, LigneTournoi value, int index,
														  boolean isSelected, boolean cellHasFocus) ->{
				JPanel panelItem = new JPanel();
				panelItem.setOpaque(false);
				JLabel nom = new JLabel(value.getNom());
				nom.setForeground(CustomColor.BLANC);
				nom.setFont(MaFont.getFontTitre3());
				panelItem.add(nom);
				JLabel date = new JLabel(value.getDateDebut() + "  " + value.getDateFin());
				date.setForeground(CustomColor.BLANC.darker());
				date.setFont(MaFont.getFontTitre4());
				panelItem.add(date);
				return panelItem;
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
	public void setControleur(ArbitresCreationControlleur controleur) {
		this.boutonValider.addActionListener(controleur);
		this.boutonAnnuler.addActionListener(controleur);
		this.btnAjoutTournois.addMouseListener(controleur);
	}


	public void clearField() {
		this.textfieldNom.setText("");
		this.textfieldPrenom.setText("");
		this.textfieldTelephone.setText("");
		resetTournois();

	}

	private void resetTournois() {
		model.removeAllElements();
	}

	public String getNomEquipe() {
		return this.textfieldNom.getText();
	}

	public JLabel getLabelLogo() {
		return this.labelLogo;
	}

	public void addTournoi(String nom, String dateDebut, String dateFin, int i) {
		this.model.add(i, new LigneTournoi(nom, dateDebut, dateFin));
	}

	public String getTextTelephone() {
		return this.textfieldTelephone.getText();
	}

	public JButton getBoutonValider() {
		return this.boutonValider;
	}

	public String getTextfieldNom() {
		return this.textfieldNom.getText();
	}

	public String getTextfieldPrenom() {
		return this.textfieldPrenom.getText();
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

	public JLabel getBoutonAjoutTournois() {
		return this.btnAjoutTournois;
	}

	public JButton getBoutonAnnuler() {
		return this.boutonAnnuler;
	}
}


