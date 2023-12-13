package vue.admin.main;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListModel;

import controlleur.AccueilControlleur;
import controlleur.ArbitresControlleur;
import controlleur.ControlleurObserver;
import vue.Page;
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
		vueAccueil = new VueAccueil(new DefaultListModel<LigneEquipe>(),new DefaultListModel<LigneTournoi>(),new DefaultListModel<LigneMatche>());
		add(vueAccueil, "Accueil");
		show("Accueil");
	}
	
	/**
	 * Choisit la page à afficher
	 * @param identifiant de la page à afficher
	 */
	public void show(String identifiant) {
		cardLayout.show(this,identifiant);
	}
}
