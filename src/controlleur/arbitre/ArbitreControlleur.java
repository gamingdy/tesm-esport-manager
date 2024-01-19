package controlleur.arbitre;

import controlleur.VueObserver;
import dao.Connexion;
import dao.DaoMatche;
import dao.DaoPartie;
import dao.DaoTournoi;
import exceptions.FausseDateException;
import exceptions.IdNotSetException;
import exceptions.MemeEquipeException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ArbitreControlleur implements ActionListener {
	private VueArbitrePoule vue;
	private List<CaseMatch> caseMatchList;
	private DaoTournoi daoTournoi;
	private Optional<Tournoi> tournoiActuel;
	private DaoMatche daoMatche;
	private DaoPartie daoPartie;

	public ArbitreControlleur(VueArbitrePoule vueArbitre) {
		this.vue = vueArbitre;
		Connexion c = Connexion.getConnexion();
		this.daoMatche = new DaoMatche(c);
		this.daoPartie = new DaoPartie(c);
		this.daoTournoi = new DaoTournoi(c);

		try {
			tournoiActuel = daoTournoi.getTournoiActuel();

			if (tournoiActuel.isPresent()) {
				List<Matche> matcheList = daoMatche.getMatchByTournoi(tournoiActuel.get().getDebut().getAnnee(), tournoiActuel.get().getNom());
				this.vue.setTitre("Tournoi " +tournoiActuel.get().getNom() + " "+tournoiActuel.get().getDebut().getAnnee());
				if (matcheList.stream().anyMatch(m -> m.getCategorie() != Categorie.POULE)) {
					
					caseMatchList = new ArrayList<>();
					for (Matche m : matcheList) {
						if (m.getCategorie() != Categorie.POULE) {
							List<Partie> partieList = daoPartie.getPartieByMatche(m);
							m.setVainqueur(partieList.get(0).getVainqueur());
							CaseMatch caseMatche = convertMatchToCaseMatch(m);
							caseMatchList.add(caseMatche);
						}
					}
				}
				else {
					caseMatchList = new ArrayList<>();
					for (Matche m : matcheList) {
						List<Partie> partieList = daoPartie.getPartieByMatche(m);
						m.setVainqueur(partieList.get(0).getVainqueur());
						CaseMatch caseMatche = convertMatchToCaseMatch(m);
						caseMatchList.add(caseMatche);
					}
				}
				this.vue.addAllMatchs(caseMatchList);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return resultat;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vue.getBoutonAnnuler()) {
			new JFramePopup("Déconnexion", "Etes vous sur de vous déconnecter ?", () -> {
				VueObserver.getInstance().notifyVue(Page.LOGIN);
			});
		} else if (e.getSource() == this.vue.getBoutonClosePoule()) {
			closePoule();
		}
	}

	private void initPhaseFinale() {
		//to do
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
			Set<Equipe> classementTournoi = ModeleTournoi.getClassement(tournoiActuel.get());
			List<List<Equipe>> phaseFinale = getPhaseFInale(classementTournoi);
			finale.addAll(phaseFinale.get(0));
			petiteFinale.addAll(phaseFinale.get(1));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (isAllMatcheClosed()) {
			try {
				System.out.println("ui");
				Matche matcheFinale = new Matche(1, this.tournoiActuel.get().getFin(), Categorie.FINALE, finale.get(0), finale.get(1), this.tournoiActuel.get());
				Partie partieFinale = new Partie(matcheFinale, 1);

				Matche matchePetiteFinale = new Matche(1, this.tournoiActuel.get().getFin(), Categorie.PETITE_FINALE, petiteFinale.get(0), petiteFinale.get(1), this.tournoiActuel.get());
				Partie partiePetiteFinale = new Partie(matchePetiteFinale, 1);

				daoMatche.add(matcheFinale);
				daoPartie.add(partieFinale);
				daoMatche.add(matchePetiteFinale);
				daoPartie.add(partiePetiteFinale);
				List<CaseMatch> caseMatchList = new ArrayList<>();
				caseMatchList.add(convertMatchToCaseMatch(matcheFinale));
				caseMatchList.add(convertMatchToCaseMatch(matchePetiteFinale));
				updateMatche(caseMatchList);
			} catch (FausseDateException | MemeEquipeException e) {
				throw new RuntimeException(e);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}


		} else {
			new JFramePopup("Erreur de cloture", "Tout les matches n'ont pas de vainqueur", () -> {
				VueObserver.getInstance().notifyVue(Page.ARBITRE);
			});
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
}
