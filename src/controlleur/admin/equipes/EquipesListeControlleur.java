package controlleur.admin.equipes;

import controlleur.ControlleurObserver;
import dao.Connexion;
import dao.DaoEquipe;
import dao.DaoInscription;
import dao.DaoJoueur;
import dao.DaoSaison;
import modele.Equipe;
import modele.Joueur;
import modele.Saison;
import vue.Page;
import vue.admin.equipes.liste.CaseEquipe;
import vue.admin.equipes.liste.VueAdminEquipesListe;
import vue.common.JFramePopup;
import vue.common.TitleBar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EquipesListeControlleur implements ActionListener, ControlleurObserver {
	private VueAdminEquipesListe vue;
	private DaoEquipe daoEquipe;
	private DaoJoueur daoJoueur;
	private DaoSaison daoSaison;
	private DaoInscription daoInscription;

	private enum Etat {SAISON_ACTUELLE, TOUTES}

	private Etat etat;
	private List<CaseEquipe> listeCase;
	private List<Equipe> listeEquipe;
	private List<CaseEquipe> listeCaseSaison;
	private List<Equipe> listeEquipeSaison;

	public EquipesListeControlleur(VueAdminEquipesListe newVue) {
		this.vue = newVue;
		Connexion c = Connexion.getConnexion();
		this.daoEquipe = new DaoEquipe(c);
		this.daoJoueur = new DaoJoueur(c);
		this.daoSaison = new DaoSaison(c);
		this.daoInscription = new DaoInscription(c);
		this.etat = Etat.TOUTES;
		this.listeEquipe = new ArrayList<>();
		this.listeCase = new ArrayList<>();
		this.listeCaseSaison = new ArrayList<>();
		this.listeEquipeSaison = new ArrayList<>();
		this.update();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vue.getBoutonAjouter()) {
			EquipesObserver.getInstance().notifyVue(Page.EQUIPES_CREATION);
		} else if (e.getSource() == vue.getBoutonSaison() && etat == Etat.TOUTES) {
			etat = Etat.SAISON_ACTUELLE;
			TitleBar.getInstance().setTitle("Equipes de la saison actuelle");
			this.update();
			this.vue.getBoutonSaison().setText("Toutes les équipes");
		} else if (e.getSource() == vue.getBoutonSaison() && etat == Etat.SAISON_ACTUELLE) {
			etat = Etat.TOUTES;
			TitleBar.getInstance().setTitle("Equipes");
			this.update();
			this.vue.getBoutonSaison().setText("Equipes de la saison");
		}
	}

	public List<CaseEquipe> convertListToCase(List<Equipe> liste) {
		List<CaseEquipe> resultat = new ArrayList<>();
		for (Equipe e : liste) {
			try {
				List<Joueur> joueurs = daoJoueur.getJoueurParEquipe(e.getNom());
				String[] listeJoueurs = new String[joueurs.size()];
				for (int i = 0; i < joueurs.size(); i++) {
					listeJoueurs[i] = joueurs.get(i).getPseudo();
				}
				creerCase(e, resultat, listeJoueurs);
			} catch (Exception sql) {
				afficherErreur("Erreur SQL lors de la récupération des joueurs de l'équipe " + e.getNom());
				}

		}
		return resultat;
	}

	private void creerCase(Equipe e, List<CaseEquipe> resultat, String[] listeJoueurs) {
		try {
			Image img = ImageIO.read(new File("assets/logo-equipes/" + e.getNom() + ".jpg"));
			ImageIcon icon = new ImageIcon(img);
			Image imgPays = ImageIO.read(new File("assets/country-flags/png100px/" + e.getPays().getCode() + ".png"));
			ImageIcon iconPays = new ImageIcon(imgPays);
			resultat.add(new CaseEquipe(e.getNom(), listeJoueurs, icon, iconPays));
		} catch (IOException ex) {
			afficherErreur("Erreur de lecture de fichier");
			}
	}

	private void afficherErreur(String message) {
		new JFramePopup("Erreur liste d'équipes", message, () -> EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE));
	}

	@Override
	public void update() {
		try {
			Saison saison = daoSaison.getLastSaison();
			List<Equipe> liste = new ArrayList<>();

			List<Equipe> listeEquipeSaisonDiff = new ArrayList<>();
			if (etat == Etat.TOUTES) {
				liste = daoEquipe.getAll();
				if (liste.size() < this.listeEquipe.size()) {
					List<Equipe> caseSupprimer = getDifference(this.listeEquipe, liste);
					supprimerEquipe(caseSupprimer);
				}
				listeEquipeSaisonDiff = getDifference(getEquipeSaion(saison.getAnnee()), this.listeEquipeSaison);
			} else {
				try {
					liste = getEquipeSaion(saison.getAnnee());
					listeEquipeSaisonDiff = getDifference(liste, this.listeEquipeSaison);
					if (liste.size() < this.listeEquipe.size()) {
						List<Equipe> caseSupprimer = getDifference(this.listeEquipe, liste);
						supprimerEquipe(caseSupprimer);
					}

				} catch (Exception e) {
					afficherErreur("Erreur SQL lors de la récupération des équipes de la saison actuelle");
				}
			}

			if (this.listeCase == null) {
				this.listeEquipe = liste;
				this.listeEquipeSaison.addAll(listeEquipeSaisonDiff);
				List<Equipe> listeEquipeHorsSaison = getDifference(this.listeEquipe, this.listeEquipeSaison);
				List<CaseEquipe> listeCaseHorsSaison = convertListToCase(listeEquipeHorsSaison);
				this.listeCaseSaison = convertListToCase(this.listeEquipeSaison);
				this.listeCase = new ArrayList<>();
				this.listeCase.addAll(this.listeCaseSaison);
				this.listeCase.addAll(listeCaseHorsSaison);
				this.vue.addAll(this.listeCase);
			} else if (etat == Etat.SAISON_ACTUELLE) {
				this.listeEquipe = liste;
				List<CaseEquipe> listeCaseDiff = convertListToCase(listeEquipeSaisonDiff);
				this.listeCaseSaison.addAll(listeCaseDiff);
				this.vue.resetGrille();
				this.vue.addAll(this.listeCaseSaison);
			} else {
				List<Equipe> differences = getDifference(liste, this.listeEquipe);
				List<Equipe> nouveauDansSaison = getDifference(listeEquipeSaisonDiff, this.listeEquipeSaison);
				List<Equipe> horsSaison = getDifference(differences, nouveauDansSaison);


				this.listeEquipeSaison.addAll(nouveauDansSaison);
				List<CaseEquipe> caseHorsSaison = convertListToCase(horsSaison);
				List<CaseEquipe> caseSaison = convertListToCase(nouveauDansSaison);
				List<CaseEquipe> nouvelleCase = new ArrayList<>(caseHorsSaison);
				if (!differences.isEmpty()) {
					nouvelleCase.addAll(caseSaison);
				}
				for (CaseEquipe c : nouvelleCase) {
					if (!this.listeCase.contains(c)) {
						this.listeCase.add(c);
					}
				}

				this.listeCaseSaison.addAll(caseSaison);
				this.listeEquipe.addAll(differences);

				this.vue.addAll(nouvelleCase);
			}

		} catch (Exception e) {
			afficherErreur("Erreur SQL lors de la récupération des équipes");
			}
	}

	private void supprimerEquipe(List<Equipe> listeEquipeSupprimer) {
		List<CaseEquipe> listeCaseSupprimer = new ArrayList<>();
		List<Equipe> listeEquipeSaisonSupprimer = new ArrayList<>();
		List<CaseEquipe> listeCaseSaisonSupprimer = new ArrayList<>();

		for (CaseEquipe e : this.listeCase) {
			for (Equipe eq : listeEquipeSupprimer) {
				if (e.getNom().equals(eq.getNom())) {
					if (this.listeCaseSaison.contains(e)) {
						listeCaseSaisonSupprimer.add(e);
						listeEquipeSaisonSupprimer.add(eq);
					}
					listeCaseSupprimer.add(e);
				}
			}
		}
		this.listeCase.removeAll(listeCaseSupprimer);
		this.listeEquipe.removeAll(listeEquipeSupprimer);
		this.listeCaseSaison.removeAll(listeCaseSaisonSupprimer);
		this.listeEquipeSaison.removeAll(listeEquipeSaisonSupprimer);

		this.vue.resetGrille();
		this.vue.revalidate();
		this.vue.addAll(this.listeCase);
	}


	private List<Equipe> getDifference(List<Equipe> liste1, List<Equipe> liste2) {
		List<Equipe> liste = new ArrayList<>();
		for (Equipe e : liste1) {
			if (!liste2.contains(e)) {
				liste.add(e);
			}
		}
		return liste;
	}

	private List<Equipe> getEquipeSaion(int annee) {
		List<Equipe> liste = new ArrayList<>();
		try {
			return daoInscription.getEquipeBySaison(annee);
		} catch (Exception e) {
			afficherErreur("Erreur SQL lors de la récupération des équipes de la saison actuelle");
		}
		return liste;
	}

}
