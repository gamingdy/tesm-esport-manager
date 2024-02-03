package controlleur.admin.equipes;

import dao.Connexion;
import dao.DaoAppartenance;
import dao.DaoEquipe;
import dao.DaoInscription;
import dao.DaoTournoi;
import modele.Equipe;
import modele.Tournoi;
import vue.Page;
import vue.admin.equipes.details.VueAdminEquipesDetails;
import vue.admin.equipes.liste.CaseEquipe;
import vue.common.JFramePopup;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EquipeCaseModificationControlleur extends MouseAdapter {
	private CaseEquipe caseEquipe;
	private boolean editing;
	private VueAdminEquipesDetails vueAdminEquipesDetails;
	private DaoTournoi daoTournoi;
	private DaoInscription daoInscription;
	private DaoEquipe daoEquipe;
	private DaoAppartenance daoAppartenance;

	public EquipeCaseModificationControlleur(CaseEquipe caseEquipe, boolean editing) {
		this.caseEquipe = caseEquipe;
		this.editing = editing;
		Connexion connexion = Connexion.getConnexion();
		this.daoTournoi = new DaoTournoi(connexion);
		this.daoInscription = new DaoInscription(connexion);
		this.daoEquipe = new DaoEquipe(connexion);
		this.daoAppartenance = new DaoAppartenance(connexion);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (this.estDansUnTournoiEnCours()) {
			new JFramePopup("Erreur","Impossible de modifier une equipe qui est dans un tournoi en cours",()->EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE));
		}

		if (editing) {
			EquipesObserver.getInstance().notifyVue(Page.EQUIPES_DETAILS, this.caseEquipe, true);
		} else {
			EquipesObserver.getInstance().notifyVue(Page.EQUIPES_DETAILS, this.caseEquipe, false);
		}
	}

	private boolean estDansUnTournoiEnCours() {
		String nom_equipe = this.caseEquipe.getNom();
		try {
			Optional<Tournoi> tournoiActuel = this.daoTournoi.getTournoiActuel();
			if (tournoiActuel.isPresent()) {
				Tournoi tournoi = tournoiActuel.get();
				List<Equipe> equipe_tournoi = this.daoAppartenance.getEquipeByTournoi(tournoi.getNom(), tournoi.getDebut().getAnnee());
				List<String> nom_equipe_tournoi = equipe_tournoi.stream().map(Equipe::getNom).collect(Collectors.toList());
				return nom_equipe_tournoi.contains(nom_equipe);
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
