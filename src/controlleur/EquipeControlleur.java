package controlleur;

import dao.Connexion;
import dao.DaoEquipe;
import dao.DaoJoueur;
import dao.DaoSaison;
import modele.Pays;
import modele.Equipe;
import vue.admin.arbitres.VueArbitres;

public class EquipeControlleur {
	private VueArbitres vue;
	private DaoEquipe daoEquipe;
	private DaoSaison daoSaison;
	private DaoJoueur daoJoueur;
	private String champNomEquipe;
	private Pays champPaysEquipe;
	private String codeImage;

	public EquipeControlleur(VueArbitres newVue) {
		this.vue = newVue;
		Connexion c = Connexion.getConnexion();
		daoEquipe = new DaoEquipe(c);
		daoSaison = new DaoSaison(c);
		daoJoueur = new DaoJoueur(c);
	}

	private boolean EquipeDejaExistante(String nomEquipe) {
		try {
			Equipe equipe = daoEquipe.getById(nomEquipe);
		} catch (Exception e) {

		}
		return true;
	}
}
