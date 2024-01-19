package vue.arbitre;

import controlleur.arbitre.CaseMatcheControlleur;
import vue.Vue;
import vue.common.CustomColor;
import vue.common.MaFont;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

public class CaseMatch extends JPanel {

	private static ImageIcon tropheeGagnant = Vue.resize(new ImageIcon("assets/trophéeGagnant.png"), 50, 50);
	private static ImageIcon tropheePerdant = Vue.resize(new ImageIcon("assets/trophéePerdant.png"), 50, 50);
	private String date;
	private int idMatche;
	private ImageIcon logoEquipe1;
	private String nomEquipe1;
	private ImageIcon tropheGauche;
	private JButton tropheeGaucheBTN;
	private ImageIcon tropheDroite;
	private JButton tropheeDroiteBTN;
	private String nomEquipe2;
	private ImageIcon logoEquipe2;
	private ActionListener alGauche;
	private ActionListener alDroite;

	private int numEquipeGagnante;

	/**
	 * @param date
	 * @param idMatche
	 * @param logoGauche
	 * @param nomGauche
	 * @param nomDroite
	 * @param logoDroite
	 */
	public CaseMatch(String date, int idMatche, ImageIcon logoGauche, String nomGauche, String nomDroite, ImageIcon logoDroite) {
		super();
		this.date = date;
		this.idMatche = idMatche;
		this.logoEquipe1 = logoGauche;
		this.nomEquipe1 = nomGauche;
		this.tropheGauche = this.tropheePerdant;
		this.tropheDroite = this.tropheePerdant;
		this.nomEquipe2 = nomDroite;
		this.logoEquipe2 = logoDroite;
		this.numEquipeGagnante = 0;

		this.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 20));
		this.setLayout(new GridBagLayout());
		this.setBackground(CustomColor.BACKGROUND_MAIN);

		JLabel thisDate = new JLabel();
		thisDate.setFont(MaFont.getFontTitre3());
		thisDate.setForeground(CustomColor.BLANC.darker());
		thisDate.setText(getDate());
		GridBagConstraints gbcDate = new GridBagConstraints();
		gbcDate.gridwidth = 2;
		gbcDate.insets = new Insets(0, 0, 10, 0);
		this.add(thisDate, gbcDate);

		JPanel thisE = new JPanel(new GridBagLayout());
		thisE.setOpaque(false);
		GridBagConstraints gbcEquipe = new GridBagConstraints();
		gbcEquipe.fill = GridBagConstraints.BOTH;
		gbcEquipe.weightx = 1;
		gbcEquipe.weighty = 1;
		gbcEquipe.gridy = 1;
		this.add(thisE, gbcEquipe);

		JPanel equipe1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		equipe1.setOpaque(false);
		equipe1.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 1),
				BorderFactory.createEmptyBorder(10, 30, 10, 15)));

		JLabel logoEquipe1Label = new JLabel();
		logoEquipe1Label.setIcon(Vue.resize(getLogoEquipe1(), 50, 50));
		equipe1.add(logoEquipe1Label);

		JLabel nomEquipe1Label = new JLabel();
		nomEquipe1Label.setFont(MaFont.getFontTitre3());
		nomEquipe1Label.setForeground(CustomColor.BLANC);
		nomEquipe1Label.setText(getNomEquipe1());
		equipe1.add(nomEquipe1Label);

		this.tropheeGaucheBTN = new JButton();
		tropheeGaucheBTN.setContentAreaFilled(false);
		tropheeGaucheBTN.setBorder(null);
		tropheeGaucheBTN.setFocusPainted(false);
		tropheeGaucheBTN.setIcon(Vue.resize(getTropheGauche(), 50, 50));
		tropheeGaucheBTN.addMouseListener(new CaseMatcheControlleur(this, true));
		equipe1.add(tropheeGaucheBTN);
		equipe1.setPreferredSize(new Dimension(0, 80));

		GridBagConstraints gbcEquipe1 = new GridBagConstraints();
		gbcEquipe1.fill = GridBagConstraints.BOTH;
		gbcEquipe1.weightx = 0.50;
		gbcEquipe1.weighty = 1;
		thisE.add(equipe1, gbcEquipe1);

		JPanel equipe2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
		equipe2.setOpaque(false);
		equipe2.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(1, 0, 1, 1, CustomColor.ROSE_CONTOURS),
				BorderFactory.createEmptyBorder(10, 15, 10, 30)));

		tropheeDroiteBTN = new JButton();
		tropheeDroiteBTN.setContentAreaFilled(false);
		tropheeDroiteBTN.setBorder(null);
		tropheeDroiteBTN.setFocusPainted(false);
		tropheeDroiteBTN.setIcon(Vue.resize(getTropheDroite(), 50, 50));
		tropheeDroiteBTN.addMouseListener(new CaseMatcheControlleur(this, false));
		equipe2.add(tropheeDroiteBTN);

		JLabel nomEquipe2Label = new JLabel();
		nomEquipe2Label.setFont(MaFont.getFontTitre3());
		nomEquipe2Label.setForeground(CustomColor.BLANC);
		nomEquipe2Label.setText(getNomEquipe2());
		equipe2.add(nomEquipe2Label);

		JLabel logoEquipe2 = new JLabel();
		logoEquipe2.setIcon(Vue.resize(getLogoEquipe2(), 50, 50));
		equipe2.add(logoEquipe2);

		equipe2.setPreferredSize(new Dimension(0, 80));
		GridBagConstraints gbcEquipe2 = new GridBagConstraints();
		gbcEquipe2.fill = GridBagConstraints.BOTH;
		gbcEquipe2.gridx = 1;
		gbcEquipe2.weightx = 0.5;
		gbcEquipe2.weighty = 1;
		thisE.add(equipe2, gbcEquipe2);
	}

	public ImageIcon getLogoEquipe1() {
		return logoEquipe1;
	}

	public void setLogoEquipe1(ImageIcon logoEquipe1) {
		this.logoEquipe1 = logoEquipe1;
	}

	public String getNomEquipe1() {
		return nomEquipe1;
	}

	public void setNomEquipe1(String nomEquipe1) {
		this.nomEquipe1 = nomEquipe1;
	}

	public ImageIcon getTropheGauche() {
		return tropheGauche;
	}


	public void setGagnant(int numEquipe) {
		this.numEquipeGagnante = numEquipe;
		if (numEquipe == 1) {
			this.tropheeGaucheBTN.setIcon(tropheeGagnant);
			this.tropheeDroiteBTN.setIcon(tropheePerdant);
		} else if (numEquipe == 2) {
			this.tropheeGaucheBTN.setIcon(tropheePerdant);
			this.tropheeDroiteBTN.setIcon(tropheeGagnant);
		}
	}

	public int getGagnant() {
		return this.numEquipeGagnante;
	}

	public ImageIcon getTropheDroite() {
		return tropheDroite;
	}


	public String getNomEquipe2() {
		return nomEquipe2;
	}

	public void setNomEquipe2(String nomEquipe2) {
		this.nomEquipe2 = nomEquipe2;
	}

	public ImageIcon getLogoEquipe2() {
		return logoEquipe2;
	}

	public void setLogoEquipe2(ImageIcon logoEquipe2) {
		this.logoEquipe2 = logoEquipe2;
	}

	public ActionListener getAlDroite() {
		return alDroite;
	}

	public void setAlDroite(ActionListener alDroite) {
		this.alDroite = alDroite;
	}

	public ActionListener getAlGauche() {
		return alGauche;
	}

	public void setAlGauche(ActionListener alGauche) {
		this.alGauche = alGauche;
	}

	public int getIdMatche() {
		return this.idMatche;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
