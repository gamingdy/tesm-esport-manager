package controlleur;

import vue.admin.VueAdmin;
import vue.admin.equipes.VueAdminEquipes;
import vue.admin.equipes.creation.VueAdminEquipesCreation;

public class AdminEquipesControlleurs {

	private VueAdminEquipes vue;

	public AdminEquipesControlleurs(VueAdminEquipes vue) {
		this.vue = vue;
		initialiserVue();
	}
	
	/**
	 * Initialise les pages que contient la vue afin de pouvoir changer
	 */
	public void initialiserVue() {
		vue.addPage(new VueAdminEquipesCreation(), "E");
		vue.setPage("E");
	}
}
