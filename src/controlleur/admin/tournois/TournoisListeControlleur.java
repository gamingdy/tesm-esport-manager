package controlleur.admin.tournois;

import controlleur.ControlleurObserver;
import dao.Connexion;
import dao.DaoTournoi;
import modele.Tournoi;
import vue.Page;
import vue.admin.tournois.liste.CaseTournoi;
import vue.admin.tournois.liste.VueAdminTournoisListe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TournoisListeControlleur implements ActionListener, ControlleurObserver {
	private VueAdminTournoisListe vue;
	private DaoTournoi daoTournoi;
	private Connexion c;
	private List<Tournoi> listeTournois;
	private List<CaseTournoi> listeCase;

	public TournoisListeControlleur(VueAdminTournoisListe newVue) {
		this.vue = newVue;
		this.c = Connexion.getConnexion();
		daoTournoi = new DaoTournoi(c);
		this.listeTournois = new ArrayList<>();
		this.update();
	}

	@Override
	public void update() {
		try {
			List<Tournoi> liste = daoTournoi.getAll();
			if (liste.size() < this.listeTournois.size()) {
				List<Tournoi> caseSupprimer = getDifference(this.listeTournois, liste);
				supprimerTournoiAffichage(caseSupprimer);
			} else if (this.listeCase == null) {
				this.listeTournois = liste;
				this.listeCase = convertListToCase(this.listeTournois);
				this.vue.addAll(this.listeCase);
			} else {
				List<Tournoi> differences = getDifference(liste, listeTournois);
				List<CaseTournoi> differencesCase = convertListToCase(differences);
				this.listeCase.addAll(differencesCase);
				this.listeTournois.addAll(differences);
				this.vue.addAll(differencesCase);
			}

		} catch (Exception e) {
		}

	}

	private List<CaseTournoi> convertListToCase(List<Tournoi> liste) {
		List<CaseTournoi> caseTournoiList = new ArrayList<>();
		for (Tournoi t : liste) {
			String debut = t.getDebut().toString().substring(6);
			String fin = t.getFin().toString().substring(6);
			caseTournoiList.add(new CaseTournoi(t.getNom(), debut, fin));
		}
		return caseTournoiList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vue.getBoutonAjouter()) {
			TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION);
		}
	}


	private void supprimerTournoiAffichage(List<Tournoi> listeTournoisSupprime) {
		List<CaseTournoi> listeCaseSupprimer = new ArrayList<>();

		for (CaseTournoi e : this.listeCase) {
			for (Tournoi eq : listeTournoisSupprime) {
				if (e.getNom().equals(eq.getNom())) {
					listeCaseSupprimer.add(e);
				}
			}
		}
		this.listeCase.removeAll(listeCaseSupprimer);
		this.listeTournois.removeAll(listeTournoisSupprime);

		this.vue.resetGrille();
		this.vue.revalidate();
		this.vue.addAll(this.listeCase);
	}

	private List<Tournoi> getDifference(List<Tournoi> liste1, List<Tournoi> liste2) {
		List<Tournoi> liste = new ArrayList<>();
		for (Tournoi e : liste1) {
			if (!liste2.contains(e)) {
				liste.add(e);
			}
		}
		return liste;
	}
}
