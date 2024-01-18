package controlleur.arbitre;

import controlleur.VueObserver;
import dao.Connexion;
import dao.DaoMatche;
import dao.DaoPartie;
import exceptions.IdNotSetException;
import modele.Matche;
import modele.Partie;
import vue.Page;
import vue.arbitre.CaseMatch;
import vue.arbitre.VueArbitrePoule;
import vue.common.JFramePopup;

import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ArbitreControlleur implements ListSelectionListener, ActionListener {
	private VueArbitrePoule vue;
	private List<Matche> matcheList;
	private List<Partie> partiesList;
	private DaoMatche daoMatche;
	private DaoPartie daoPartie;

	public ArbitreControlleur(VueArbitrePoule vueArbitre) {
		this.vue = vueArbitre;
		Connexion c = Connexion.getConnexion();
		this.daoMatche = new DaoMatche(c);
		this.daoPartie = new DaoPartie(c);
		partiesList = new ArrayList<>();

		try {
			matcheList = daoMatche.getAll();
			List<CaseMatch> tablo = new ArrayList<>();

			for (Matche m : matcheList) {
				CaseMatch caseMatche = convertMatchToCaseMatch(m);
				tablo.add(caseMatche);
			}
			this.vue.addAllMatchs(tablo);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private CaseMatch convertMatchToCaseMatch(Matche matche) {
		String dateMatche = matche.getDateDebutMatche().toString().substring(6);
		ImageIcon imageEquipe1 = new ImageIcon("assets/logo-equipes/" + matche.getEquipe1().getNom() + ".jpg");
		ImageIcon imageEquipe2 = new ImageIcon("assets/logo-equipes/" + matche.getEquipe2().getNom() + ".jpg");
		ImageIcon tropheeGagnant = new ImageIcon("assets/trophéePerdant.png");
		ImageIcon tropheePerdant = new ImageIcon("assets/trophéePerdant.png");
		CaseMatch resultat = null;
		try {
			resultat = new CaseMatch(dateMatche, matche.getId(), imageEquipe1, matche.getEquipe1().getNom(), tropheePerdant, tropheeGagnant, matche.getEquipe2().getNom(), imageEquipe2, null, null);
		} catch (IdNotSetException e) {
			e.printStackTrace();
		}
		return resultat;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		partiesList.clear();
		/*²
		JList<CaseMatch> listeMatches = this.vue.getTableMatche();


		if (e.getValueIsAdjusting()) {
			CaseMatch caseMatch = listeMatches.getSelectedValue();
			Optional<Matche> matcheSelectionne = null;
			try {
				matcheSelectionne = daoMatche.getById(caseMatch.getIdMatche());
				if (matcheSelectionne.isPresent()) {
					System.out.println("ui");
				}
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}*/

	}

	/*
	private List<CasePartie> constructCasesParties(List<Partie> parties){
		List<CasePartie> resultat=new ArrayList<>();
		for(Partie p:parties){
			String dateMatche=p.getMatche().getDateDebutMatche().toString().substring(6);
			ImageIcon imageEquipe1 = new ImageIcon("assets/logo-equipes/" + p.getMatche().getEquipe1().getNom() + ".jpg");
			ImageIcon imageEquipe2 = new ImageIcon("assets/logo-equipes/" + p.getMatche().getEquipe2().getNom() + ".jpg");
			ImageIcon tropheeGagnant = new ImageIcon("assets/trophéePerdant.png");
			ImageIcon tropheePerdant = new ImageIcon("assets/trophéePerdant.png");
			CasePartie casePartie=null;
			try{
				casePartie=new CasePartie(imageEquipe1,p.getMatche().getEquipe1().getNom(),tropheePerdant,tropheePerdant,p.getMatche().getEquipe2().getNom(),imageEquipe2);

			}catch(Exception id){
				id.printStackTrace();
			}

			resultat.add(casePartie);
		}
		return resultat;
	}*/
	public void setVainqueurEquipe1Affichage(CaseMatch caseMatch) {
		caseMatch.setLogoEquipe2(new ImageIcon("assets/trophéeGagnant.png"));
		caseMatch.setLogoEquipe2(new ImageIcon("assets/trophéePerdant.png"));
	}

	public void setVainqueurEquipe2Affichage(CaseMatch caseMatch) {
		caseMatch.setLogoEquipe2(new ImageIcon("assets/trophéePerdant.png"));
		caseMatch.setLogoEquipe2(new ImageIcon("assets/trophéeGagnant.png"));
	}

	public void unsetVainqueurs(CaseMatch caseMatch) {
		caseMatch.setLogoEquipe2(new ImageIcon("assets/trophéePerdant.png"));
		caseMatch.setLogoEquipe2(new ImageIcon("assets/trophéePerdant.png"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vue.getBoutonAnnuler()) {
			new JFramePopup("Déconnexion", "Etes vous sur de vous déconnecter ?", () -> {
				VueObserver.getInstance().notifyVue(Page.LOGIN);
			});
		}
	}

	private boolean isAllMatcheClosed() {
		for (Matche m : matcheList) {
			if (m.getVainqueur() == null) {
				return false;
			}
		}
		return true;
	}

	private void closePoule() {
		if (isAllMatcheClosed()) {

		} else {
			new JFramePopup("Erreur de cloture", "Tout les matches n'ont pas de vainqueur", () -> {
				VueObserver.getInstance().notifyVue(Page.ARBITRE);
			});
		}
	}
}
