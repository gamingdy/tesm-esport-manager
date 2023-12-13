package vue.admin.main;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import controller.AccueilControleur;
import controller.ArbitresControleur;
import controller.ControlleurObserver;
import vue.Page;
import vue.admin.accueil.LigneEquipe;
import vue.admin.accueil.LigneMatche;
import vue.admin.accueil.LigneTournoi;
import vue.admin.accueil.VueAccueil;
import vue.admin.arbitres.VueArbitres;

@SuppressWarnings("serial")
public class ConteneurMain extends JPanel {

	private CardLayout cardLayout;
	private VueAccueil vueAccueil;
	private VueArbitres vueArbitres;
	Map<String, ControlleurObserver> lst_controlleurs;

	public ConteneurMain() {
		this.setOpaque(false);
		this.lst_controlleurs = new HashMap<>();

		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		vueAccueil = new VueAccueil(new DefaultListModel<LigneEquipe>(), new DefaultListModel<LigneTournoi>(), new DefaultListModel<LigneMatche>());
		vueArbitres = new VueArbitres();
		try {
			this.lst_controlleurs.put(Page.ACCUEIL_ADMIN.getNom(), new AccueilControleur(vueAccueil));
			this.lst_controlleurs.put(Page.ARBITRES.getNom(), new ArbitresControleur(vueArbitres));

		} catch (Exception e) {
			e.printStackTrace();
		}


		add(vueAccueil, Page.ACCUEIL_ADMIN.getNom());
		add(vueArbitres, Page.ARBITRES.getNom());
		show(Page.ACCUEIL_ADMIN.getNom());
	}

	/**
	 * Choisit la page à afficher
	 *
	 * @param identifiant de la page à afficher
	 */
	public void show(String identifiant) {
		cardLayout.show(this, identifiant);
	}

	public void refreshVue(String identifiant) {
		this.lst_controlleurs.get(identifiant).update();
	}
}
