package controlleur.admin.tournois;

import controlleur.admin.equipes.EquipesObserver;
import dao.*;
import modele.*;
import vue.Page;
import vue.admin.equipes.liste.CaseEquipe;
import vue.admin.tournois.liste.CaseTournoi;
import vue.common.JFramePopup;
import vue.common.JFramePopupSuppressionEquipe;

import java.awt.event.MouseAdapter;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TournoiSuppressionControlleur extends MouseAdapter {
	private CaseTournoi caseTournoi;
	private DaoTournoi daoTournoi;

	public TournoiSuppressionControlleur(CaseTournoi newCaseTournoi) {
		this.caseTournoi = newCaseTournoi;
		Connexion c = Connexion.getConnexion();
		this.daoTournoi=new DaoTournoi(c);
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		new JFramePopup("Suppression de Tournois","Etes vous sûr de supprimer ce tournoi ?",()->
		supprimerTournoi()
		);
	}
	public void supprimerTournoi() {
		CustomDate date=CustomDate.fromString(caseTournoi.getDateDebut());
		try {
			Optional<Tournoi> tournoi = daoTournoi.getById( date.getAnnee(),caseTournoi.getNom());
			if(tournoi.isPresent()){
			if (tournoi.get().isEstEncours()) {
				new JFramePopup("Erreur de suppression", "Le tournoi est en cours", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_LISTE));
			} else {
				try {
					daoTournoi.delete(date.getAnnee(),tournoi.get().getNom());
					new JFramePopup("Tournoi supprimé", "Le tournoi a été bien supprimé", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_LISTE));
				} catch (Exception e) {
					e.printStackTrace();
					new JFramePopup("Erreur de suppression", "Le tournoi n'a pas pu être supprimé", () -> TournoisObserver.getInstance().notifyVue(Page.TOURNOIS_LISTE));
				}

			}}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}