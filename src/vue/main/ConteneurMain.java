package vue.main;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListModel;

import vue.accueil.VueAccueil;
import vue.arbitres.PageTestArbitre;
import vue.accueil.LigneEquipe;
import vue.accueil.LigneTournoi;
import vue.accueil.LigneMatche;

@SuppressWarnings("serial")
public class ConteneurMain extends JPanel {

	private CardLayout cardLayout;
	private VueAccueil vueAccueil;
	private PageTestArbitre vueArbitres;

	
	public ConteneurMain() {
		this.setOpaque(false);
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		vueAccueil = new VueAccueil(new DefaultListModel<LigneEquipe>(),new DefaultListModel<LigneTournoi>(),new DefaultListModel<LigneMatche>());
		 vueArbitres = new PageTestArbitre();
		add(vueAccueil, "Accueil");
		add(vueArbitres,"Arbitres");
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
