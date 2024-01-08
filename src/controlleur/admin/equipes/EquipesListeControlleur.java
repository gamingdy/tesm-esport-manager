package controlleur.admin.equipes;

import controlleur.ControlleurObserver;
import dao.*;
import modele.*;
import vue.Page;
import vue.admin.equipes.liste.CaseEquipe;
import vue.admin.equipes.liste.VueAdminEquipesListe;
import vue.common.JFramePopup;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EquipesListeControlleur implements ActionListener, ControlleurObserver {
	private VueAdminEquipesListe vue;
	private DaoEquipe daoEquipe;
	private DaoJoueur daoJoueur;
	private DaoSaison daoSaison;
	private Saison saison;
	private DaoInscription daoInscription;
	private DaoAppartenance daoAppartenance;
	private List<CaseEquipe> listeCase;
	private List<Equipe> listeEquipe;

	public EquipesListeControlleur(VueAdminEquipesListe newVue) {
		this.vue = newVue;
		Connexion c = Connexion.getConnexion();
		this.daoEquipe = new DaoEquipe(c);
		this.daoJoueur = new DaoJoueur(c);
		this.daoInscription = new DaoInscription(c);
		this.daoSaison = new DaoSaison(c);
		this.daoAppartenance = new DaoAppartenance(c);
		this.update();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vue.getBoutonAjouter()) {
			EquipesObserver.getInstance().notifyVue(Page.EQUIPES_CREATION);
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
				try {
					Image img = ImageIO.read(new File("assets/logo-equipes/" + e.getNom() + ".jpg"));
					ImageIcon icon = new ImageIcon(img);
					Image imgPays = ImageIO.read(new File("assets/country-flags/png100px/" + e.getPays().getCode() + ".png"));
					ImageIcon iconPays = new ImageIcon(imgPays);
					resultat.add(new CaseEquipe(e.getNom(), listeJoueurs, icon, iconPays));
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			} catch (Exception sql) {

			}

		}
		return resultat;
	}

	public List<CaseEquipe> filtreSaison() throws Exception {
		saison = daoSaison.getLastSaison();
		List<Equipe> listeEquipe = daoInscription.getEquipeBySaison(saison);
		return convertListToCase(listeEquipe);
	}

	public void supprimerEquipeDefinitivement(Equipe equipe) throws Exception {
		try {
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
			new JFramePopup("Suppression echoué", "L'equipe  ne peut pas etre supprimée", () -> EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE));
		}
	}

	public void supprimerEquipeSaison(Equipe equipe) {
		try {
			if (isEquipeDansTournoiSaisonActuelle(equipe)) {
				new JFramePopup("Erreur", "L'equipe est inscrite dans un tournoi", () -> EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE));
			} else {
				if (isEquipeInscriteSaisonActuelle(equipe)) {
					saison = daoSaison.getLastSaison();
					daoInscription.delete(saison.getAnnee(), equipe.getNom());
				}

			}
		} catch (Exception e) {

		}

	}

	private boolean isEquipeInscriteSaisonActuelle(Equipe equipe) throws Exception {
		saison = daoSaison.getLastSaison();
		List<Saison> listeSaison = daoInscription.getSaisonByEquipe(equipe);
		return listeSaison.contains(saison);
	}

	private boolean isEquipeDansTournoiSaisonActuelle(Equipe equipe) throws SQLException, Exception {
		saison = daoSaison.getLastSaison();
		Inscription inscription = new Inscription(saison, equipe);
		List<Tournoi> listeTournoisJoue = daoAppartenance.getTournoiByEquipeForSaison(inscription);
		return !listeTournoisJoue.isEmpty();
	}

	@Override
	public void update() {
		try {
			List<Equipe> liste = daoEquipe.getAll();

			if (this.listeCase == null) {
				this.listeEquipe = liste;
				this.listeCase = convertListToCase(this.listeEquipe);
				this.vue.addAll(this.listeCase);
			} else {
				List<Equipe> differences = liste.stream()
						.filter(element -> !this.listeEquipe.contains(element))
						.collect(Collectors.toList());
				List<CaseEquipe> differencesCase = convertListToCase(differences);
				this.listeCase.addAll(differencesCase);
				this.listeEquipe.addAll(differences);
				this.vue.addAll(differencesCase);
			}

		} catch (Exception e) {
		}
	}
}
