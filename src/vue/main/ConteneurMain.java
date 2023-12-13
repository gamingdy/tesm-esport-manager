package vue.main;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListModel;

import vue.Page;
import vue.admin.accueil.VueAccueil;
import vue.arbitres.PageTestArbitre;
import vue.admin.accueil.LigneEquipe;
import vue.admin.accueil.LigneTournoi;
import vue.admin.accueil.LigneMatche;

@SuppressWarnings("serial")
public class ConteneurMain extends JPanel {

	private CardLayout cardLayout;
	private VueAccueil vueAccueil;
	private PageTestArbitre vueArbitres;


	public ConteneurMain() {
		this.setOpaque(false);
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		vueAccueil = new VueAccueil(new DefaultListModel<LigneEquipe>(), new DefaultListModel<LigneTournoi>(), new DefaultListModel<LigneMatche>());
		vueArbitres = new PageTestArbitre();
		add(vueAccueil, Page.ACCUEIL_ADMIN.getNom());
		add(vueArbitres, Page.ARBITRES.getNom());
		show(Page.ACCUEIL_ADMIN.getNom());
	}

	public VueAccueil getVueAccueil() {
		return vueAccueil;
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
