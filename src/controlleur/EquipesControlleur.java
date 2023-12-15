package controlleur;

import dao.*;
import modele.Equipe;
import modele.Saison;
import vue.Page;
import vue.admin.equipes.VueAdminEquipes;
import vue.admin.equipes.creation.VueAdminEquipesCreation;
import vue.admin.equipes.liste.VueAdminEquipesListe;

import java.sql.SQLException;
import java.util.List;

public class EquipesControlleur implements ControlleurObserver {
	private VueAdminEquipes vue;
	private DaoInscription daoInscription;
	private DaoEquipe daoEquipe;
	private DaoSaison daoSaison;
	private List<Equipe> listeEquipes;
	private Saison saison;

	public EquipesControlleur(VueAdminEquipes vue) {
		this.vue = vue;
		Connexion c = Connexion.getConnexion();
		daoInscription = new DaoInscription(c);
		daoSaison = new DaoSaison(c);
		daoEquipe = new DaoEquipe(c);
		//pour filtrer par la saison actuelle
		try {
			saison = daoSaison.getLastSaison();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.update();
		
		vue.addPage(new VueAdminEquipesCreation(), Page.EQUIPES_CREATION);
		vue.addPage(new VueAdminEquipesListe(), Page.EQUIPES_LISTE);
		vue.setPage(Page.EQUIPES_CREATION);
	}

	@Override
	public void update() {
		try {
			listeEquipes = daoEquipe.getAll();
		} catch (Exception e) {

		}
	}
}
