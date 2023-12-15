package vue.admin.main;

import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import controlleur.*;
import vue.Page;
import vue.admin.accueil.LigneEquipe;
import vue.admin.accueil.LigneMatche;
import vue.admin.accueil.LigneTournoi;
import vue.admin.accueil.VueAccueil;
import vue.admin.arbitres.VueArbitres;
import vue.admin.equipes.VueAdminEquipes;
import vue.admin.equipes.creation.VueAdminEquipesCreation;

@SuppressWarnings("serial")
public class ConteneurMain extends JPanel {

	private CardLayout cardLayout;
	private VueAccueil vueAccueil;
	private VueArbitres vueArbitres;
	private VueAdminEquipes vueAdminEquipes;
	private VueAdminEquipesCreation vueAdminEquipesCreation;
	Map<String, ControlleurObserver> lst_controlleurs;

	public ConteneurMain() {
		this.setOpaque(false);
		this.lst_controlleurs = new HashMap<>();

		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		vueAccueil = new VueAccueil(new DefaultListModel<LigneEquipe>(), new DefaultListModel<LigneTournoi>(), new DefaultListModel<LigneMatche>());
		vueArbitres = new VueArbitres();
		vueAdminEquipesCreation = new VueAdminEquipesCreation();
		vueAdminEquipes = new VueAdminEquipes();
		try {
			this.lst_controlleurs.put(Page.ACCUEIL_ADMIN.getNom(), new AccueilControlleur(vueAccueil));
			this.lst_controlleurs.put(Page.ARBITRES.getNom(), new ArbitresControlleur(vueArbitres));
			this.lst_controlleurs.put(Page.EQUIPES.getNom(), new CreationEquipeControlleur(vueAdminEquipesCreation));
			//this.lst_controlleurs.put(Page.EQUIPES.getNom(),new EquipesControlleur(vueAdminEquipes));
		} catch (Exception e) {
			e.printStackTrace();
		}


		add(vueAccueil, Page.ACCUEIL_ADMIN.getNom());
		add(vueArbitres, Page.ARBITRES.getNom());
		add(vueAdminEquipesCreation, Page.EQUIPES.getNom());
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
