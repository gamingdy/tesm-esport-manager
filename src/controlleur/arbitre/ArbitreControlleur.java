package controlleur.arbitre;

import controlleur.VueObserver;
import controlleur.admin.arbitres.ArbitresCreationControlleur;
import dao.Connexion;
import dao.Dao;
import dao.DaoMatche;
import modele.CustomDate;
import modele.Matche;
import vue.Page;
import vue.Vue;
import vue.admin.VueAdmin;
import vue.admin.historique.VueAdminHistorique;
import vue.arbitre.CaseMatch;
import vue.arbitre.VueArbitre;
import vue.arbitre.VueArbitrePoule;
import vue.common.FileChooser;
import vue.common.JFramePopup;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Observer;

public class ArbitreControlleur implements ListSelectionListener, ActionListener {
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

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	private CaseMatch convertMatchToCaseMatch(Matche matche){
		String dateMatche=matche.getDateDebutMatche().toString().substring(6);
		ImageIcon imageEquipe1 = new ImageIcon("assets/logo-equipes/" + matche.getEquipe1().getNom() + ".jpg");
		ImageIcon imageEquipe2 = new ImageIcon("assets/logo-equipes/" + matche.getEquipe2().getNom() + ".jpg");
		ImageIcon tropheeGagnant = new ImageIcon("assets/trophéePerdant.png");
		ImageIcon tropheePerdant = new ImageIcon("assets/trophéePerdant.png");
		CaseMatch resultat=new CaseMatch(dateMatche,imageEquipe1,matche.getEquipe1().getNom(),tropheePerdant,tropheeGagnant,matche.getEquipe2().getNom(),imageEquipe2);
		return resultat;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList<CaseMatch> listeMatches=this.vue.getTableMatche();
		if (e.getValueIsAdjusting()){
				CaseMatch caseMatch=listeMatches.getSelectedValue();
		}

	}
	public void setVainqueurEquipe1Affichage(CaseMatch caseMatch){
		caseMatch.setImageBoutonDroite(new ImageIcon("assets/trophéeGagnant.png"));
		caseMatch.setImageBoutonDroite(new ImageIcon("assets/trophéePerdant.png"));
	}
	public void setVainqueurEquipe2Affichage(CaseMatch caseMatch){
		caseMatch.setImageBoutonDroite(new ImageIcon("assets/trophéePerdant.png"));
		caseMatch.setImageBoutonDroite(new ImageIcon("assets/trophéeGagnant.png"));
	}
	public void unsetVainqueurs(CaseMatch caseMatch){
		caseMatch.setImageBoutonDroite(new ImageIcon("assets/trophéePerdant.png"));
		caseMatch.setImageBoutonDroite(new ImageIcon("assets/trophéePerdant.png"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.vue.getBoutonAnnuler()){
			new JFramePopup("Déconnexion", "Etes vous sur de vous déconnecter ?", () -> {
				VueObserver.getInstance().notifyVue(Page.LOGIN);
			});
		}
	}
}
