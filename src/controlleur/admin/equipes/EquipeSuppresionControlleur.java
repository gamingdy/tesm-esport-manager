package controlleur.admin.equipes;

import dao.Connexion;
import dao.DaoAppartenance;
import dao.DaoEquipe;
import dao.DaoInscription;
import dao.DaoSaison;
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
	private Equipe equipe;

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
				() -> {
					supprimerEquipeSaison();
					this.update();
				},
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
				new JFramePopup("Erreur", "L'equipe est inscrite dans un tournoi", () -> EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE));
			} else {
				if (isEquipeInscriteSaisonActuelle(equipe)) {
					saison = daoSaison.getLastSaison();
					daoInscription.delete(saison.getAnnee(), equipe.getNom());
				}
				daoEquipe.delete(equipe.getNom());
				File fichierLogo = new File("assets/logo-equipes/" + equipe.getNom() + ".jpg");
				fichierLogo.delete();
				new JFramePopup("Suppression effectuée", "L'equipe est supprimée", () -> EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE));
				this.update();

			}

		} catch (Exception e) {
			e.printStackTrace();
			new JFramePopup("Suppression echoué", "L'equipe  ne peut pas etre supprimée", () -> EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE));
		}
	}

	public void supprimerEquipeSaison() {
		try {
			Equipe equipe = this.daoEquipe.getById(caseEquipe.getNom()).get();

			if (isEquipeDansTournoiSaisonActuelle(equipe)) {
				new JFramePopup("Erreur", "L'equipe est inscrite dans un tournoi", () -> EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE));
			} else {
				if (isEquipeInscriteSaisonActuelle(equipe)) {
					saison = daoSaison.getLastSaison();
					daoInscription.delete(saison.getAnnee(), equipe.getNom());
					new JFramePopup("Erreur", "L'equipe a été supprimé de la saison", () -> EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE));
				} else {
					new JFramePopup("Erreur", "L'equipe n'est pas inscrite dans la saison", () -> EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE));
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private boolean isEquipeInscriteSaisonActuelle(Equipe equipe) throws Exception {
		saison = daoSaison.getLastSaison();
		System.out.println("Last saison : " + saison.getAnnee());
		List<Saison> listeSaison = daoInscription.getSaisonByEquipe(equipe.getNom());
		System.out.println("NB saison : " + listeSaison.size());
		return listeSaison.contains(saison);
	}

	private boolean isEquipeDansTournoiSaisonActuelle(Equipe equipe) throws SQLException, Exception {
		saison = daoSaison.getLastSaison();
		System.out.println("Last saison tournoi : " + saison.getAnnee());
		Inscription inscription = new Inscription(saison, equipe);
		List<Tournoi> listeTournoisJoue = daoAppartenance.getTournoiByEquipeForSaison(inscription);
		System.out.println("NB tournois joue : " + listeTournoisJoue.size());
		return !listeTournoisJoue.isEmpty();
	}


	public void update() {

	}

}
