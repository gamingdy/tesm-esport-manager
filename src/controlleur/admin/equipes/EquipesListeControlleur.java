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
import vue.common.TitleBar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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

	private enum Etat {SAISON_ACTUELLE, TOUTES}

	;
	private Etat etat;
	private List<CaseEquipe> listeCase;
	private List<Equipe> listeEquipe;

	public EquipesListeControlleur(VueAdminEquipesListe newVue) {
		this.vue = newVue;
		Connexion c = Connexion.getConnexion();
		this.daoEquipe = new DaoEquipe(c);
		this.daoJoueur = new DaoJoueur(c);
		this.daoSaison = new DaoSaison(c);
		this.daoInscription = new DaoInscription(c);
		this.etat = Etat.TOUTES;
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


	@Override
	public void update() {
		try {
			saison = daoSaison.getLastSaison();
			List<Equipe> liste = new ArrayList<>();
			if (etat == Etat.TOUTES) {
				liste = daoEquipe.getAll();
			} else {

				try {
					liste = daoInscription.getEquipeBySaison(saison.getAnnee());
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			if (this.listeCase == null && etat == Etat.SAISON_ACTUELLE) {
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
