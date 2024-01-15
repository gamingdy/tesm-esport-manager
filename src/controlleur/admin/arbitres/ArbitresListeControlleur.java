package controlleur.admin.arbitres;

import controlleur.ControlleurObserver;
import controlleur.admin.equipes.EquipesObserver;
import dao.*;
import modele.Arbitre;
import modele.Equipe;
import modele.Saison;
import modele.Tournoi;
import vue.Page;
import vue.admin.arbitres.liste.CaseArbitre;
import vue.admin.arbitres.liste.VueAdminArbitresListe;
import vue.admin.equipes.liste.CaseEquipe;
import vue.admin.tournois.liste.CaseTournoi;
import vue.common.JFramePopup;

import javax.naming.ldap.Control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArbitresListeControlleur implements ControlleurObserver, ActionListener {
	private VueAdminArbitresListe vue;
	private DaoArbitre daoArbitre;
	private DaoArbitrage daoArbitrage;
	private DaoSaison daoSaison;
	private Connexion c;
	private List<Arbitre> arbitreList;
	private List<CaseArbitre> listeCase;

	public ArbitresListeControlleur(VueAdminArbitresListe newVue) {
		this.vue = newVue;
		c = Connexion.getConnexion();
		daoArbitre = new DaoArbitre(c);
		daoArbitrage = new DaoArbitrage(c);
		daoSaison = new DaoSaison(c);
		this.arbitreList = new ArrayList<>();
		this.update();
	}

	@Override
	public void update() {
		try {
			List<Arbitre> liste = daoArbitre.getAll();
			if (liste.size() < this.arbitreList.size()) {
				List<Arbitre> caseSupprimer = getDifference(this.arbitreList, liste);
				supprimerTournoiAffichage(caseSupprimer);
			}
			else if (this.listeCase == null) {
				this.arbitreList = liste;
				this.listeCase = convertToCaseArbitre(this.arbitreList);
				this.vue.addAll(this.listeCase);
			} else {
				List<Arbitre> differences = getDifference(liste,arbitreList);
				List<CaseArbitre> differencesCase = convertToCaseArbitre(differences);
				this.listeCase.addAll(differencesCase);
				this.arbitreList.addAll(differences);
				this.vue.addAll(differencesCase);
			}

		} catch (Exception e) {
		}

	}


	public List<CaseArbitre> convertToCaseArbitre(List<Arbitre> liste) {
		List<CaseArbitre> listeRes = new ArrayList<>();
		for (Arbitre arbitre : liste) {
			listeRes.add(new CaseArbitre(arbitre.getNom(), arbitre.getPrenom(),arbitre.getNumeroTelephone()));
		}
		return listeRes;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vue.getBoutonAjouter()) {
			ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION);
		}
	}
	private List<Arbitre> getDifference(List<Arbitre> liste1, List<Arbitre> liste2) {
		List<Arbitre> liste = new ArrayList<>();
		for (Arbitre e : liste1) {
			if (!liste2.contains(e)) {
				liste.add(e);
			}
		}
		return liste;
	}
	private void supprimerTournoiAffichage(List<Arbitre> listeTournoisSupprime) {
		List<CaseArbitre> listeCaseSupprimer = new ArrayList<>();

		for (CaseArbitre e : this.listeCase) {
			for (Arbitre eq : listeTournoisSupprime) {
				if (e.getNom().equals(eq.getNom())) {
					listeCaseSupprimer.add(e);
				}
			}
		}
		this.listeCase.removeAll(listeCaseSupprimer);
		this.arbitreList.removeAll(listeTournoisSupprime);

		this.vue.resetGrille();
		this.vue.revalidate();
		this.vue.addAll(this.listeCase);
	}
}
