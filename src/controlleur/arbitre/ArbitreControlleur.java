package controlleur.arbitre;

import controlleur.VueObserver;
import dao.Connexion;
import dao.DaoMatche;
import dao.DaoPartie;
import dao.DaoTournoi;
import exceptions.FausseDateException;
import exceptions.GagnantNonChoisiException;
import exceptions.IdNotSetException;
import modele.Categorie;
import modele.Equipe;
import modele.Matche;
import modele.ModeleTournoi;
import modele.Partie;
import modele.Tournoi;
import vue.Page;
import vue.arbitre.CaseMatch;
import vue.arbitre.VueArbitrePoule;
import vue.common.JFramePopup;

import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ArbitreControlleur implements ActionListener {
	private VueArbitrePoule vue;
	private List<CaseMatch> caseMatchList;
	private DaoTournoi daoTournoi;
	private Tournoi tournoi;
	private DaoMatche daoMatche;
	private DaoPartie daoPartie;

	public ArbitreControlleur(VueArbitrePoule vueArbitre) {
		this.vue = vueArbitre;
		Connexion c = Connexion.getConnexion();
		this.daoMatche = new DaoMatche(c);
		this.daoPartie = new DaoPartie(c);
		this.daoTournoi = new DaoTournoi(c);

		try {
			Optional<Tournoi> tournoiActuel;
			tournoiActuel = daoTournoi.getTournoiActuel();
			if(tournoiActuel.isPresent()){
				tournoi=tournoiActuel.get();
				List<Matche> matcheList = daoMatche.getMatchByTournoi(tournoi.getDebut().getAnnee(), tournoi.getNom());
				this.vue.setTitre("Tournoi " + tournoi.getNom() + " " + tournoi.getDebut().getAnnee());
				if (matcheList.stream().anyMatch(m -> m.getCategorie() != Categorie.POULE)) {
					vue.setTexteBouton("Clôturer le tournoi");
				}
				recupererMatches(matcheList);
				this.vue.addAllMatchs(caseMatchList);
			}
		} catch (Exception e) {
			afficherErreur("Erreur lors de la récupération des matches");
		}
	}
	private void recupererMatches(List<Matche> matcheList) throws SQLException, MemeEquipeException, FausseDateException, IdNotSetException, GagnantNonChoisiException {
		caseMatchList = new ArrayList<>();
		for (Matche m : matcheList) {
			List<Partie> partieList = daoPartie.getPartieByMatche(m);
			m.setVainqueur(partieList.get(0).getVainqueur());
			CaseMatch caseMatche = convertMatchToCaseMatch(m);
			caseMatchList.add(caseMatche);
		}
	}
	private CaseMatch convertMatchToCaseMatch(Matche matche) {
		String dateMatche = matche.getDateDebutMatche().toString().substring(6);
		ImageIcon imageEquipe1 = new ImageIcon("assets/logo-equipes/" + matche.getEquipe1().getNom() + ".jpg");
		ImageIcon imageEquipe2 = new ImageIcon("assets/logo-equipes/" + matche.getEquipe2().getNom() + ".jpg");
		CaseMatch resultat = null;
		try {
			resultat = new CaseMatch(dateMatche, matche.getId(), imageEquipe1, matche.getEquipe1().getNom(), matche.getEquipe2().getNom(), imageEquipe2);
			Equipe vainqueur = matche.getVainqueur();

			if (vainqueur != null) {
				if (vainqueur.equals(matche.getEquipe1())) {
					resultat.setGagnant(1);
				} else {
					resultat.setGagnant(2);
				}
			}
		} catch (IdNotSetException e) {
			afficherErreur("Erreur lors de la récupération des matches");
		}
		return resultat;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vue.getBoutonAnnuler()) {
			new JFramePopup("Déconnexion", "Etes vous sur de vous déconnecter ?", () ->
					VueObserver.getInstance().notifyVue(Page.LOGIN)
			);
		} else if (e.getSource() == this.vue.getBoutonClosePoule() && this.vue.getBoutonClosePoule().getText().equals("Clôturer la poule")) {
			closePoule();
			this.vue.setTexteBouton("Clôturer le tournoi");
		} else if (e.getSource() == this.vue.getBoutonClosePoule() && this.vue.getBoutonClosePoule().getText().equals("Clôturer le tournoi")) {
			if (isAllMatcheClosed()) {
				new JFramePopup("Fin du tournoi", "Le tournoi est clos", () ->{
					tournoi.setEstEncours(false);
					VueObserver.getInstance().notifyVue(Page.LOGIN);
				}

				);
			} else {
				afficherErreur("Tout les matches n'ont pas de vainqueur");
			}
		}
	}

	private boolean isAllMatcheClosed() {
		for (CaseMatch m : caseMatchList) {
			if (m.getGagnant() == 0) {
				return false;
			}
		}
		return true;
	}

	private void updateMatche(List<CaseMatch> caseM) {
		this.vue.setMatchs(caseM);
		this.vue.revalidate();
	}

	private void closePoule() {
		List<Equipe> finale = new ArrayList<>();
		List<Equipe> petiteFinale = new ArrayList<>();
		try {
			Set<Equipe> classementTournoi = ModeleTournoi.getClassement(tournoi);
			List<List<Equipe>> phaseFinale = getPhaseFInale(classementTournoi);
			finale.addAll(phaseFinale.get(0));
			petiteFinale.addAll(phaseFinale.get(1));
		} catch (Exception e) {
			afficherErreur("Une erreur s'est produite lors de la cloturation");
		}
		if (isAllMatcheClosed()) {
			try {
				Matche matcheFinale = new Matche(1, tournoi.getFin(), Categorie.FINALE, finale.get(0), finale.get(1), this.tournoi);
				Partie partieFinale = new Partie(matcheFinale, 1);

				Matche matchePetiteFinale = new Matche(1, this.tournoi.getFin(), Categorie.PETITE_FINALE, petiteFinale.get(0), petiteFinale.get(1), this.tournoi);
				Partie partiePetiteFinale = new Partie(matchePetiteFinale, 1);

				daoMatche.add(matcheFinale);
				daoPartie.add(partieFinale);
				daoMatche.add(matchePetiteFinale);
				daoPartie.add(partiePetiteFinale);
				List<CaseMatch> currentMatchList = new ArrayList<>();

				currentMatchList.add(convertMatchToCaseMatch(matcheFinale));
				currentMatchList.add(convertMatchToCaseMatch(matchePetiteFinale));
				updateMatche(currentMatchList);
				this.caseMatchList = currentMatchList;
			} catch (Exception e) {
				afficherErreur("Une erreur s'est produite lors de la cloturation");
			}


		} else {
			afficherErreur("Tout les matches n'ont pas de vainqueur");
		}
	}

	private List<List<Equipe>> getPhaseFInale(Set<Equipe> equipes) {
		List<Equipe> qualifie = new ArrayList<>();
		for (Equipe e : equipes) {
			if (qualifie.size() < 4) {
				qualifie.add(e);
			} else {
				break;
			}
		}
		List<List<Equipe>> phaseFinale = new ArrayList<>();
		List<Equipe> finale = new ArrayList<>();
		finale.add(qualifie.get(0));
		finale.add(qualifie.get(1));

		List<Equipe> petiteFinale = new ArrayList<>();
		petiteFinale.add(qualifie.get(2));
		petiteFinale.add(qualifie.get(3));

		phaseFinale.add(finale);
		phaseFinale.add(petiteFinale);

		return phaseFinale;
	}
	private void afficherErreur(String message) {
		new JFramePopup("Erreur arbitre", message, () -> VueObserver.getInstance().notifyVue(Page.ARBITRE));
	}
}
