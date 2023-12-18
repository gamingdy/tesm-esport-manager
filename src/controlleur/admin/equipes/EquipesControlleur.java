package controlleur.admin.equipes;

import vue.Page;
import vue.admin.equipes.VueAdminEquipes;
import vue.admin.equipes.creation.VueAdminEquipesCreation;
import vue.admin.equipes.liste.VueAdminEquipesListe;

public class EquipesControlleur {
	private VueAdminEquipes vue;
	private EquipesListeControlleur equipesListeControlleur;

	public EquipesControlleur(VueAdminEquipes newVue) {
		this.vue = newVue;

		VueAdminEquipesCreation vueAdminEquipesCreation = new VueAdminEquipesCreation();
		EquipeCreationControlleur equipeCreationControlleur = new EquipeCreationControlleur(vueAdminEquipesCreation);
		vueAdminEquipesCreation.setControleur(equipeCreationControlleur);

		VueAdminEquipesListe vueAdminEquipesListe = new VueAdminEquipesListe();
		this.equipesListeControlleur = new EquipesListeControlleur(vueAdminEquipesListe);
		vueAdminEquipesListe.setControleur(equipesListeControlleur);


		vue.addPage(vueAdminEquipesCreation, Page.EQUIPES_CREATION);
		vue.addPage(vueAdminEquipesListe, Page.EQUIPES_LISTE);
		vue.setPage(Page.EQUIPES_LISTE);
		EquipesObserver.getInstance().setVue(this);
	}

	public void update(Page id) {
		if (Page.EQUIPES_LISTE.equals(id)) {
			this.equipesListeControlleur.update();
		}
		this.vue.setPage(id);
	}

}
