package controlleur.admin.equipes;

import vue.Page;
import vue.admin.equipes.VueAdminEquipes;
import vue.admin.equipes.creation.VueAdminEquipesCreation;
import vue.admin.equipes.details.VueAdminEquipesDetails;
import vue.admin.equipes.liste.CaseEquipe;
import vue.admin.equipes.liste.VueAdminEquipesListe;

public class EquipesControlleur {
	private VueAdminEquipes vue;
	private EquipesListeControlleur equipesListeControlleur;
	private EquipeModificationControlleur equipeModificationControlleur;

	public EquipesControlleur(VueAdminEquipes newVue) {
		this.vue = newVue;

		VueAdminEquipesCreation vueAdminEquipesCreation = new VueAdminEquipesCreation();
		EquipeCreationControlleur equipeCreationControlleur = new EquipeCreationControlleur(vueAdminEquipesCreation);
		vueAdminEquipesCreation.setControleur(equipeCreationControlleur);

		VueAdminEquipesListe vueAdminEquipesListe = new VueAdminEquipesListe();
		this.equipesListeControlleur = new EquipesListeControlleur(vueAdminEquipesListe);
		vueAdminEquipesListe.setControleur(equipesListeControlleur);
		VueAdminEquipesDetails vueAdminEquipesDetails = new VueAdminEquipesDetails();
		this.equipeModificationControlleur = new EquipeModificationControlleur(vueAdminEquipesDetails);
		vueAdminEquipesDetails.setControleur(equipeModificationControlleur);

		vue.addPage(vueAdminEquipesCreation, Page.EQUIPES_CREATION);
		vue.addPage(vueAdminEquipesListe, Page.EQUIPES_LISTE);
		vue.addPage(vueAdminEquipesDetails, Page.EQUIPES_DETAILS);
		vue.setPage(Page.EQUIPES_LISTE);
		EquipesObserver.getInstance().setVue(this);
	}

	public void update(Page id) {
		if (Page.EQUIPES_LISTE.equals(id)) {
			this.equipesListeControlleur.update();
		}
		this.vue.setPage(id);
	}

	public void update(Page id, CaseEquipe caseEquipe, boolean editing) {
		this.equipeModificationControlleur.init(caseEquipe, editing);
		this.vue.setPage(id);
	}

}
