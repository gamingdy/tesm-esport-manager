package controlleur;

import controlleur.ControlleurObserver;
import dao.*;
import modele.*;
import vue.admin.accueil.LigneTournoi;
import vue.admin.accueil.VueAccueil;

import javax.swing.DefaultListModel;
import java.util.ArrayList;
import java.util.List;

public class AccueilControlleur implements ControlleurObserver {
	private VueAccueil vue;
	DefaultListModel<LigneTournoi> listeTournoi;
	private DaoTournoi daoTournoi;
	private DaoSaison daoSaison;
	private DaoMatche daoMatche;
	private Connexion c = Connexion.getConnexion();

	public AccueilControlleur(VueAccueil newVue) throws Exception {
		vue = newVue;
		daoTournoi = new DaoTournoi(c);
		daoSaison = new DaoSaison(c);
		daoMatche = new DaoMatche(c);
		this.update();
	}

	@Override
	public void update() {
		listeTournoi = new DefaultListModel<LigneTournoi>();
		try {
			List<Tournoi> liste = new ArrayList<>(daoTournoi.getAll());
			Tournoi tournoiActuel = daoTournoi.getTournoiActuel().get();
			LigneTournoi ligne = new LigneTournoi(tournoiActuel.getNom(), tournoiActuel.isEstEncours());
			listeTournoi.addElement(ligne);
			liste.remove(tournoiActuel);
			for (Tournoi tournoi : liste) {
				LigneTournoi ligne1 = new LigneTournoi(tournoi.getNom(), tournoi.isEstEncours());
				listeTournoi.addElement(ligne1);
			}
			vue.setListeTournois(listeTournoi);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
