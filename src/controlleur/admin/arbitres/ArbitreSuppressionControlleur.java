package controlleur.admin.arbitres;

import dao.Connexion;
import dao.DaoArbitrage;
import dao.DaoArbitre;
import dao.DaoTournoi;
import modele.Arbitre;
import modele.Tournoi;
import vue.Page;
import vue.admin.arbitres.liste.CaseArbitre;
import vue.common.JFramePopup;

import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.Optional;

public class ArbitreSuppressionControlleur extends MouseAdapter {
	private CaseArbitre caseArbitre;
	private DaoArbitre daoArbitre;
	private DaoTournoi daoTournoi;
	private DaoArbitrage daoArbitrage;

	public ArbitreSuppressionControlleur(CaseArbitre newCase) {
		this.caseArbitre = newCase;
		Connexion c = Connexion.getConnexion();
		this.daoArbitre = new DaoArbitre(c);
		this.daoArbitrage = new DaoArbitrage(c);
		this.daoTournoi = new DaoTournoi(c);
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		new JFramePopup("Suppression d'arbitre", "Etes vous sûr de supprimer cet arbitre?", this::supprimerArbitre
		);
	}

	public void supprimerArbitre() {
		try {
			Optional<Arbitre> arbitre = daoArbitre.getById(caseArbitre.getNom(), caseArbitre.getPrenom(), caseArbitre.getNumero());
			if (arbitre.isPresent()) {
				if (isArbitreDansTournoiActuel(arbitre.get())) {
					new JFramePopup("Erreur de suppression", "Arbitre est deja dans un tournoi actuel", () -> ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_LISTE));
				} else {
					daoArbitre.delete(arbitre.get().getNom(), arbitre.get().getPrenom(), arbitre.get().getNumeroTelephone());
					new JFramePopup("Suppression effectuée", "L'arbitre est supprimé", () -> ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_LISTE));
				}
			}
		} catch (Exception e) {
			new JFramePopup("Erreur", "Une erreur SQL s'est produite, contactez l'administrateur", () -> ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_LISTE));
		}
	}

	public boolean isArbitreDansTournoiActuel(Arbitre arbitre) {
		try {
			Optional<Tournoi> tournoi = daoTournoi.getTournoiActuel();
			if (tournoi.isPresent()) {
				List<Arbitre> listeArbitre = daoArbitrage.getArbitreByTournoi(tournoi.get().getNom(), tournoi.get().getDebut().getAnnee());
				return listeArbitre.contains(arbitre);
			}
		} catch (Exception e) {
			new JFramePopup("Erreur", "Une erreur SQL s'est produite, contactez l'administrateur", () -> ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_LISTE));
		}
		return false;
	}
}
