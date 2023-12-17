package controlleur;

import dao.*;
import modele.Equipe;
import modele.Saison;
import vue.Page;
import vue.admin.equipes.VueAdminEquipes;
import vue.admin.equipes.creation.VueAdminEquipesCreation;
import vue.admin.equipes.liste.VueAdminEquipesListe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class EquipesControlleur {
	private VueAdminEquipes vue;
	private VueAdminEquipesCreation vueAdminEquipesCreation;
	private VueAdminEquipesListe vueAdminEquipesListe;
	private DaoInscription daoInscription;
	private DaoEquipe daoEquipe;
	private DaoSaison daoSaison;
	private List<Equipe> listeEquipes;
	private Saison saison;

	public EquipesControlleur(VueAdminEquipes newVue)  {
		this.vue = newVue;
		vue.addPage(new VueAdminEquipesCreation(), Page.EQUIPES_CREATION);
		vue.addPage(new VueAdminEquipesListe(), Page.EQUIPES_LISTE);
		vue.setPage(Page.EQUIPES_LISTE);
		EquipesObserver.getInstance().setVue(this);
	}

	public void update(Page id) {
		this.vue.setPage(id);
	}

}
