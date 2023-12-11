package vue.admin.main;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.*;

import controller.AccueilControleur;
import vue.admin.accueil.LigneEquipe;
import vue.admin.accueil.LigneMatche;
import vue.admin.accueil.LigneTournoi;
import vue.admin.accueil.VueAccueil;

@SuppressWarnings("serial")
public class ConteneurMain extends JPanel {

	private CardLayout cardLayout;
	private VueAccueil vueAccueil;

	public ConteneurMain() {
		this.setOpaque(false);

		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		vueAccueil = new VueAccueil(new DefaultListModel<LigneEquipe>(), new DefaultListModel<LigneTournoi>(), new DefaultListModel<LigneMatche>());
		try {
			new AccueilControleur(vueAccueil);
		} catch (Exception e) {
			e.printStackTrace();
		}

		add(vueAccueil, "Accueil");
		show("Accueil");
	}

	/**
	 * Choisit la page à afficher
	 *
	 * @param identifiant de la page à afficher
	 */
	public void show(String identifiant) {
		cardLayout.show(this, identifiant);
	}
}
