package controlleur;

import controlleur.ControlleurObserver;
import dao.*;
import modele.Equipe;
import modele.Saison;
import vue.admin.arbitres.VueArbitres;

import java.sql.SQLException;
import java.util.List;

public class EquipesControlleur implements ControlleurObserver {
	private VueArbitres vue;
	private DaoInscription daoInscription;
	private DaoEquipe daoEquipe;
	private DaoSaison daoSaison;
	private List<Equipe> listeEquipes;
	private Saison saison;

	public EquipesControlleur(VueArbitres newVue) {
		this.vue = newVue;
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
	}

	@Override
	public void update() {
		try {
			listeEquipes = daoEquipe.getAll();
		} catch (Exception e) {

		}
	}
}
