package controlleur.admin.equipes;

import controlleur.ControlleurObserver;
import dao.*;
import modele.Equipe;
import modele.Joueur;
import modele.Saison;
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
	private List<CaseEquipe> listeCase;
	private List<Equipe> listeEquipe;

	public EquipesListeControlleur(VueAdminEquipesListe newVue) {
		this.vue = newVue;
		Connexion c = Connexion.getConnexion();
		this.daoEquipe = new DaoEquipe(c);
		this.daoJoueur = new DaoJoueur(c);
		this.daoInscription = new DaoInscription(c);
		this.daoSaison = new DaoSaison(c);
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

	public void supprimerEquipe(Equipe equipe) throws Exception {
		try {
			daoEquipe.delete(equipe.getNom());
			new JFramePopup("Suppression effectuée", "L'equipe est supprimée", () -> EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE));
			this.update();
		} catch (Exception e) {
			new JFramePopup("Suppression echoué", "L'equipe  ne peut pas etre supprimée", () -> EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE));
		}
	}

	@Override
	public void update() {
		try {
			List<Equipe> liste = daoEquipe.getAll();

			if (this.listeCase == null) {
				this.listeEquipe = liste;
				this.listeCase = convertListToCase(this.listeEquipe);
				System.out.println(this.listeCase.size());
				this.vue.addAll(this.listeCase);
			} else {
				List<Equipe> differences = liste.stream()
						.filter(element -> !this.listeEquipe.contains(element))
						.collect(Collectors.toList());
				List<CaseEquipe> differencesCase = convertListToCase(differences);

				this.vue.addAll(differencesCase);
				this.listeCase.addAll(differencesCase);
			}

		} catch (Exception e) {
		}
	}
}
