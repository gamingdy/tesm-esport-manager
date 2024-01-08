package controlleur.admin.tournois;

import controlleur.ControlleurObserver;
import dao.Connexion;
import dao.DaoTournoi;
import modele.Arbitre;
import modele.Tournoi;
import vue.Page;
import vue.admin.arbitres.liste.CaseArbitre;
import vue.admin.tournois.liste.CaseTournoi;
import vue.admin.tournois.liste.VueAdminTournoisListe;
import vue.common.JFramePopup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		this.update();
	}

	@Override
	public void update() {
		try {
			List<Tournoi> liste = daoTournoi.getAll();

			if (this.listeCase == null) {
				this.listeTournois = liste;
				this.listeCase = convertListToCase(this.listeTournois);
				this.vue.addAll(this.listeCase);
			} else {
				List<Tournoi> differences = liste.stream()
						.filter(element -> !this.listeTournois.contains(element))
						.collect(Collectors.toList());
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

	public void supprimerTournoi(Tournoi tournoi) {
		if (tournoi.isEstEncours()) {
			new JFramePopup("Erreur de suppression", "Le tournoi est en cours", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_LISTE));
		} else {
			try {
				daoTournoi.delete(tournoi.getNom());
				new JFramePopup("Tournoi supprimé", "Le tournoi a été bien supprimé", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_LISTE));
				this.update();
			} catch (Exception e) {
				new JFramePopup("Erreur de suppression", "Le tournoi n'a pas pu être supprimé", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_LISTE));
			}

		}
	}
}
