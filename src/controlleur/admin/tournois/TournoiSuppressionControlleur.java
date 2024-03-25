package controlleur.admin.tournois;

import dao.Connexion;
import dao.DaoTournoi;
import modele.CustomDate;
import modele.Tournoi;
import vue.Page;
import vue.admin.tournois.liste.CaseTournoi;
import vue.common.JFramePopup;

import java.awt.event.MouseAdapter;
import java.util.Optional;

public class TournoiSuppressionControlleur extends MouseAdapter {
	private final CaseTournoi caseTournoi;
	private final DaoTournoi daoTournoi;

	public TournoiSuppressionControlleur(CaseTournoi newCaseTournoi) {
		this.caseTournoi = newCaseTournoi;
		Connexion c = Connexion.getConnexion();
		this.daoTournoi = new DaoTournoi(c);
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		new JFramePopup("Suppression de Tournois", "Etes vous sûr de supprimer ce tournoi ?", this::supprimerTournoi);
	}

	public void supprimerTournoi() {
		CustomDate date = CustomDate.fromString(caseTournoi.getDateDebut());
		try {
			Optional<Tournoi> tournoi = daoTournoi.getById(date.getAnnee(), caseTournoi.getNom());
			if (tournoi.isPresent()) {
				if (tournoi.get().isEstEncours()) {
					afficherErreur("Le tournoi est en cours");
				} else {
					try {
						daoTournoi.delete(date.getAnnee(), tournoi.get().getNom());
						new JFramePopup("Tournoi supprimé", "Le tournoi a été bien supprimé", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_LISTE));
					} catch (Exception e) {
						afficherErreur("Une erreur s'est produite lors de la suppression");}

				}
			}
		} catch (Exception e) {
			afficherErreur("Une erreur SQL s'est produite, contactez l'administrateur");}
	}
	private void afficherErreur(String message) {
		new JFramePopup("Erreur de suppression", message, () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_LISTE));
	}
}