package controlleur.admin.tournois;

import controlleur.ControlleurObserver;
import dao.Connexion;
import dao.DaoTournoi;
import modele.Equipe;
import modele.Tournoi;
import vue.Page;
import vue.admin.equipes.liste.CaseEquipe;
import vue.admin.tournois.liste.CaseTournoi;
import vue.admin.tournois.liste.VueAdminTournoisListe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TournoisListeControlleur implements ActionListener, ControlleurObserver {
	private VueAdminTournoisListe vueAdminTournoisListe;
	private DaoTournoi daoTournoi;
	private Connexion c;

	public TournoisListeControlleur(VueAdminTournoisListe newVue) {
		this.vueAdminTournoisListe = newVue;
		this.c = Connexion.getConnexion();
		daoTournoi = new DaoTournoi(c);
		this.update();
	}

	@Override
	public void update() {
		try {
			List<Tournoi> liste = daoTournoi.getAll();
			List<CaseTournoi> listeCase = convertListToCase(liste);
			this.vueAdminTournoisListe.setListeTournois(listeCase);
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
		if (e.getSource() == this.vueAdminTournoisListe.getBoutonAjouter()) {
			TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_CREATION);
		}
	}
}
