package vue.arbitre;

import controlleur.arbitre.ArbitreControlleur;
import vue.common.CustomColor;
import vue.common.CustomScrollBarUI;
import vue.common.MaFont;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

public class VueArbitrePoule extends VueArbitre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelTitreParties;
	private JScrollPane spParties;
	private JPanel liste;

	@Override
	public void initMain() {
		super.initMain();
		main.setLayout(new GridBagLayout());

		JLabel labelTitre = new JLabel("Liste des matchs", SwingConstants.LEADING);
		labelTitre.setForeground(CustomColor.BLANC);
		labelTitre.setFont(MaFont.getFontTitre1());
		GridBagConstraints gbcTitre = new GridBagConstraints();
		gbcTitre.insets = new Insets(25, 25, 0, 0);
		gbcTitre.fill = GridBagConstraints.HORIZONTAL;
		gbcTitre.weightx = 0;
		main.add(labelTitre, gbcTitre);

		liste = new JPanel();
		liste.setLayout(new BoxLayout(liste, BoxLayout.Y_AXIS));
		liste.setBackground(CustomColor.BACKGROUND_MAIN);
		JScrollPane sp = new JScrollPane(liste);
		sp.getViewport().setBackground(CustomColor.BACKGROUND_MAIN);
		sp.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		sp.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		sp.setBorder(null);
		sp.getVerticalScrollBar().setUnitIncrement(10);

		GridBagConstraints gbcListe = new GridBagConstraints();
		gbcListe.insets = new Insets(25, 25, 25, 25);
		gbcListe.fill = GridBagConstraints.BOTH;
		gbcListe.gridy = 1;
		gbcListe.weightx = 0.6;
		gbcListe.weighty = 0.8;
		main.add(sp, gbcListe);

		this.boutonAction.setText("Clôturer la poule");
		setControleur(new ArbitreControlleur(this));

	}

	public void afficherParties(boolean b) {
		spParties.setVisible(b);
		labelTitreParties.setVisible(b);
	}

	public void setControleur(ArbitreControlleur controlleur) {
		boutonDeconnexion.addActionListener(controlleur);
		boutonAction.addActionListener(controlleur);
	}

	public JButton getBoutonAnnuler() {
		return boutonDeconnexion;
	}

	public void addMatch(CaseMatch c) {
		liste.add(c);
	}

	public void setMatchs(List<CaseMatch> c) {
		resetListeMatchs();
		this.addAllMatchs(c);
	}

	public void addAllMatchs(List<CaseMatch> c) {
		c.stream().forEach(this::addMatch);
	}

	public void resetListeMatchs() {
		liste.removeAll();
	}

	public void supprimerCaseMatch(int i) {
		liste.remove(i);
	}

	public JButton getBoutonClosePoule() {
		return this.boutonAction;
	}
}
