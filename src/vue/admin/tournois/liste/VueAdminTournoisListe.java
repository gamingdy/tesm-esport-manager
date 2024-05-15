package vue.admin.tournois.liste;


import controlleur.admin.tournois.TournoisListeControlleur;
import vue.common.CustomColor;
import vue.common.CustomScrollBarUI;
import vue.common.MaFont;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;

public class VueAdminTournoisListe extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel list;
	private JButton boutonAjouter;
	private int nbCases = 0;

	public VueAdminTournoisListe() {

		setOpaque(false);
		setLayout(new GridBagLayout());

		setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));

		list = new JPanel(new GridLayout(0, 3, 15, 15));
		list.setBackground(CustomColor.BACKGROUND_MAIN);
		list.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JPanel j;
		for (int i = 0; i < 6; i++) {
			j = new JPanel();
			j.setOpaque(false);
			list.add(j);
		}
		JScrollPane sp = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setBackground(CustomColor.BACKGROUND_MAIN);
		sp.getVerticalScrollBar().setUnitIncrement(15);
		sp.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3));

		GridBagConstraints gbcSp = new GridBagConstraints();
		gbcSp.fill = GridBagConstraints.BOTH;
		gbcSp.gridx = 0;
		gbcSp.gridy = 1;
		gbcSp.weighty = 0.8F;
		gbcSp.gridwidth = 3;
		add(sp, gbcSp);

		sp.getVerticalScrollBar().setUI(new CustomScrollBarUI());


		JLabel recherche = new JLabel("Liste des Tournois", javax.swing.SwingConstants.CENTER);
		recherche.setBackground(CustomColor.BACKGROUND_MAIN);
		recherche.setOpaque(true);
		recherche.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2), BorderFactory.createEmptyBorder(10, 5, 10, 0)));
		recherche.setForeground(CustomColor.BLANC);
		recherche.setFont(MaFont.getFontLabelConnexion());
		GridBagConstraints gbcRecherche = new GridBagConstraints();
		gbcRecherche.fill = GridBagConstraints.HORIZONTAL;
		gbcRecherche.gridx = 0;
		gbcRecherche.gridy = 0;
		gbcRecherche.weightx = 0.6F;
		gbcRecherche.weighty = 0.2F;

		add(recherche, gbcRecherche);

		boutonAjouter = new JButton("Ajouter");
		boutonAjouter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		ImageIcon ajt = new ImageIcon("assets/BoutonAjouter.png");
		ajt = new ImageIcon(ajt.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		boutonAjouter.setIcon(ajt);
		boutonAjouter.setIconTextGap(10);
		boutonAjouter.setBackground(CustomColor.BACKGROUND_MENU.brighter());
		boutonAjouter.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3), BorderFactory.createEmptyBorder(10, 40, 10, 40)));
		boutonAjouter.setForeground(CustomColor.BLANC);
		boutonAjouter.setFont(MaFont.getFontTitre4());
		GridBagConstraints gbcBtnAjt = new GridBagConstraints();
		gbcBtnAjt.fill = GridBagConstraints.NONE;
		gbcBtnAjt.gridx = 1;
		gbcBtnAjt.gridy = 0;
		gbcBtnAjt.weightx = 0.4F;
		gbcBtnAjt.weighty = 0.2F;
		add(boutonAjouter, gbcBtnAjt);
	}

	public void add(CaseTournoi c) {
		if (nbCases < 6) {
			list.remove(nbCases);
		}
		list.add(c.getPanel(), nbCases);
		nbCases += 1;
	}


	public void addAll(List<CaseTournoi> c) {
		c.stream().forEach(this::add);
	}

	public JButton getBoutonAjouter() {
		return this.boutonAjouter;
	}

	public void setControleur(TournoisListeControlleur controleur) {
		this.boutonAjouter.addActionListener(controleur);

	}

	public void resetGrille() {
		list.removeAll();
		JPanel j;
		for (int i = 0; i < 4; i++) {
			j = new JPanel();
			j.setOpaque(false);
			list.add(j);
		}
		nbCases = 0;
	}
}
