package controlleur.arbitre;

import controlleur.admin.arbitres.ArbitresCreationControlleur;
import dao.Connexion;
import dao.Dao;
import dao.DaoMatche;
import modele.Matche;
import vue.arbitre.VueArbitre;
import vue.arbitre.VueArbitrePoule;

import java.util.List;

public class ArbitreControlleur {
	private VueArbitrePoule vue;
	private List<Matche> matcheList;
	private DaoMatche daoMatche;
	public ArbitreControlleur(VueArbitrePoule vueArbitre){
		this.vue=vueArbitre;
		Connexion c = Connexion.getConnexion();
		this.daoMatche=new DaoMatche(c);
		try{
			matcheList=daoMatche.getAll();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
