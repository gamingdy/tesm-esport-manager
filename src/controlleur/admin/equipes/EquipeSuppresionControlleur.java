package controlleur.admin.equipes;

import dao.Connexion;
import dao.DaoAppartenance;
import dao.DaoEquipe;
import dao.DaoInscription;
import dao.DaoSaison;
import exceptions.FausseDateException;
import modele.Equipe;
import modele.Inscription;
import modele.Saison;
import modele.Tournoi;
import vue.Page;
import vue.admin.equipes.liste.CaseEquipe;
import vue.common.JFramePopup;
import vue.common.JFramePopupSuppressionEquipe;

import java.awt.event.MouseAdapter;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class EquipeSuppresionControlleur extends MouseAdapter {
	private CaseEquipe caseEquipe;
	private Saison saison;
	private DaoInscription daoInscription;
	private DaoAppartenance daoAppartenance;
	private DaoSaison daoSaison;
	private DaoEquipe daoEquipe;

	public EquipeSuppresionControlleur(CaseEquipe caseEquipe) {
		this.caseEquipe = caseEquipe;
		Connexion c = Connexion.getConnexion();
		this.daoEquipe = new DaoEquipe(c);
		this.daoInscription = new DaoInscription(c);
		this.daoSaison = new DaoSaison(c);
		this.daoAppartenance = new DaoAppartenance(c);
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		new JFramePopupSuppressionEquipe("Choisissez votre action", "Vous voulez la supprimer de la saison ou de l'equipe ?",
				this::supprimerEquipeSaison,
				() -> {
					supprimerEquipeDefinitivement();
					EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE);
				}
		);
	}

	public void supprimerEquipeDefinitivement() {
		try {
			Equipe equipe = this.daoEquipe.getById(caseEquipe.getNom()).get();
			if (isEquipeDansTournoiSaisonActuelle(equipe)) {
				afficherErreur("L'equipe est inscrite dans un tournoi");} else {
				if (isEquipeInscriteSaisonActuelle(equipe)) {
					saison = daoSaison.getLastSaison();
					daoInscription.delete(saison.getAnnee(), equipe.getNom());
				}
				daoEquipe.delete(equipe.getNom());
				File fichierLogo = new File("assets/logo-equipes/" + equipe.getNom() + ".jpg");
				fichierLogo.delete();
				new JFramePopup("Suppression effectuée", "L'equipe est supprimée", () -> EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE));

			}

		} catch (Exception e) {
			afficherErreur("Une erreur SQL s'est produite, contactez l'administrateur");}
	}

	public void supprimerEquipeSaison() {
		try {
			Equipe equipe = this.daoEquipe.getById(caseEquipe.getNom()).get();

			if (isEquipeDansTournoiSaisonActuelle(equipe)) {
				afficherErreur("L'equipe est inscrite dans un tournoi");} else {
				if (isEquipeInscriteSaisonActuelle(equipe)) {
					saison = daoSaison.getLastSaison();
					daoInscription.delete(saison.getAnnee(), equipe.getNom());
					new JFramePopup("Succès", "L'equipe a été supprimé de la saison", () -> EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE));
				} else {
					afficherErreur("L'équipe n'est pas inscrite dans la saison");}
			}

		} catch (Exception e) {
			afficherErreur("Une erreur SQL s'est produite, contactez l'administrateur");}
	}

	private boolean isEquipeInscriteSaisonActuelle(Equipe equipe) throws SQLException {
		saison = daoSaison.getLastSaison();

		List<Saison> listeSaison = daoInscription.getSaisonByEquipe(equipe.getNom());

		return listeSaison.contains(saison);
	}

	private boolean isEquipeDansTournoiSaisonActuelle(Equipe equipe) throws SQLException, FausseDateException {
		saison = daoSaison.getLastSaison();
		Inscription inscription = new Inscription(saison, equipe);
		List<Tournoi> listeTournoisJoue = daoAppartenance.getTournoiByEquipeForSaison(inscription);
		return !listeTournoisJoue.isEmpty();
	}
	private void afficherErreur(String message) {
		new JFramePopup("Erreur suppression equipe", message, () -> EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE));
	}
}
