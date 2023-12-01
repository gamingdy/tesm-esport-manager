package vue.admin.main;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListModel;

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
		DefaultListModel<LigneEquipe> dl = new DefaultListModel<LigneEquipe>();
		dl.addElement(new LigneEquipe(1, new ImageIcon("assets/logo.png"), "La première équipe", 14525));
		dl.addElement(new LigneEquipe(2, new ImageIcon("assets/logo.png"), "La deuxième équipe", 1616));
		dl.addElement(new LigneEquipe(3, new ImageIcon("assets/logo.png"), "La troisème équipe", 15));
		DefaultListModel<LigneTournoi> lm = new DefaultListModel<LigneTournoi>();
		lm.addElement(new LigneTournoi("Tournoi en cours actuellement",true));
		lm.addElement(new LigneTournoi("Tournoi terminé récent",false));
		lm.addElement(new LigneTournoi("Tournoi terminé moins récent",false));
		lm.addElement(new LigneTournoi("Tournoi terminé encore moins récent",false));
		lm.addElement(new LigneTournoi("Tournoi terminé vieux",false));
		lm.addElement(new LigneTournoi("Tournoi terminé très vieux ",false));
		lm.addElement(new LigneTournoi("Tournoi terminé vriament très vieux",false));
		lm.addElement(new LigneTournoi("Premier tournoi de la saison",false));
		DefaultListModel<LigneMatche> lt = new DefaultListModel<LigneMatche>();
		lt.addElement(new LigneMatche("18/11/2023 18:30",new ImageIcon("assets/logo.png"),"Equipe1",new ImageIcon("assets/trophéePerdant.png"),new ImageIcon("assets/logo.png"),"Equipe2",new ImageIcon("assets/trophéeGagnant.png")));
		lt.addElement(new LigneMatche("18/11/2023 18:30",new ImageIcon("assets/logo.png"),"Equipe1",new ImageIcon("assets/trophéePerdant.png"),new ImageIcon("assets/logo.png"),"Equipe2",new ImageIcon("assets/trophéeGagnant.png")));
		lt.addElement(new LigneMatche("18/11/2023 18:30",new ImageIcon("assets/logo.png"),"Equipe1",new ImageIcon("assets/trophéePerdant.png"),new ImageIcon("assets/logo.png"),"Equipe2",new ImageIcon("assets/trophéeGagnant.png")));
		lt.addElement(new LigneMatche("18/11/2023 18:30",new ImageIcon("assets/logo.png"),"Equipe1",new ImageIcon("assets/trophéePerdant.png"),new ImageIcon("assets/logo.png"),"Equipe2",new ImageIcon("assets/trophéeGagnant.png")));
		lt.addElement(new LigneMatche("18/11/2023 18:30",new ImageIcon("assets/logo.png"),"Equipe1",new ImageIcon("assets/trophéePerdant.png"),new ImageIcon("assets/logo.png"),"Equipe2",new ImageIcon("assets/trophéeGagnant.png")));
		lt.addElement(new LigneMatche("18/11/2023 18:30",new ImageIcon("assets/logo.png"),"Equipe1",new ImageIcon("assets/trophéePerdant.png"),new ImageIcon("assets/logo.png"),"Equipe2",new ImageIcon("assets/trophéeGagnant.png")));
		lt.addElement(new LigneMatche("18/11/2023 18:30",new ImageIcon("assets/logo.png"),"Equipe1",new ImageIcon("assets/trophéePerdant.png"),new ImageIcon("assets/logo.png"),"Equipe2",new ImageIcon("assets/trophéeGagnant.png")));
		lt.addElement(new LigneMatche("18/11/2023 18:30",new ImageIcon("assets/logo.png"),"Equipe1",new ImageIcon("assets/trophéePerdant.png"),new ImageIcon("assets/logo.png"),"Equipe2",new ImageIcon("assets/trophéeGagnant.png")));
		lt.addElement(new LigneMatche("18/11/2023 18:30",new ImageIcon("assets/logo.png"),"Equipe1",new ImageIcon("assets/trophéePerdant.png"),new ImageIcon("assets/logo.png"),"Equipe2",new ImageIcon("assets/trophéeGagnant.png")));
		lt.addElement(new LigneMatche("18/11/2023 18:30",new ImageIcon("assets/logo.png"),"Equipe1",new ImageIcon("assets/trophéePerdant.png"),new ImageIcon("assets/logo.png"),"Equipe2",new ImageIcon("assets/trophéeGagnant.png")));
		lt.addElement(new LigneMatche("18/11/2023 18:30",new ImageIcon("assets/logo.png"),"Equipe1",new ImageIcon("assets/trophéePerdant.png"),new ImageIcon("assets/logo.png"),"Equipe2",new ImageIcon("assets/trophéeGagnant.png")));
		vueAccueil = new VueAccueil(dl,lm,lt);
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
