package controlleur;

import vue.admin.VueAdmin;
import vue.admin.equipes.VueAdminEquipes;
import vue.login.VueLogin;

public class AdminControlleur {
	private VueAdmin vue;

	public AdminControlleur(VueAdmin vue) {
		this.vue = vue;
	}
	
	/**
	 * Initialise les pages que contient la vue afin de pouvoir changer
	 */
	public void initialiserVue() {
		vue.addPage(new VueAdminEquipes(), "E");
		vue.setPage("E");
	}
	
}
