package controlleur.arbitre;

import controlleur.admin.arbitres.ArbitresCreationControlleur;
import dao.Connexion;
import dao.Dao;
import dao.DaoMatche;
import modele.CustomDate;
import modele.Matche;
import vue.arbitre.CaseMatch;
import vue.arbitre.VueArbitre;
import vue.arbitre.VueArbitrePoule;

import javax.swing.*;
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
			DefaultListModel<CaseMatch> tablo=this.vue.getModelMatches();
			for(Matche m:matcheList){
				CaseMatch caseMatche=convertMatchToCaseMatch(m);
				tablo.addElement(caseMatche);
			}
			System.out.println("Case matche ajoutés nb de cases :"+tablo.size());
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	private CaseMatch convertMatchToCaseMatch(Matche matche){
		String dateMatche=matche.getDateDebutMatche().toString().substring(6);
		ImageIcon imageEquipe1 = new ImageIcon("assets/logo-equipes/" + matche.getEquipe1().getNom() + ".jpg");
		ImageIcon imageEquipe2 = new ImageIcon("assets/logo-equipes/" + matche.getEquipe2().getNom() + ".jpg");
		ImageIcon tropheeGagnant = new ImageIcon("assets/trophéeGagnant.png");
		ImageIcon tropheePerdant = new ImageIcon("assets/trophéePerdant.png");
		CaseMatch resultat=new CaseMatch(dateMatche,imageEquipe1,matche.getEquipe1().getNom(),tropheePerdant,tropheeGagnant,matche.getEquipe2().getNom(),imageEquipe2);
		return resultat;
	}
}
