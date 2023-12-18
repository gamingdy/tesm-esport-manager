package controlleur.admin.equipes;

import controlleur.ControlleurObserver;
import dao.Connexion;
import dao.DaoEquipe;
import dao.DaoJoueur;
import modele.Equipe;
import modele.Joueur;
import vue.Page;
import vue.admin.equipes.VueAdminEquipes;
import vue.admin.equipes.liste.CaseEquipe;
import vue.admin.equipes.liste.VueAdminEquipesListe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EquipesListeControlleur implements ActionListener, ControlleurObserver {
	private VueAdminEquipesListe vue;
	private VueAdminEquipes vuePrincipale;
	private DaoEquipe daoEquipe;
	private DaoJoueur daoJoueur;

	public EquipesListeControlleur(VueAdminEquipesListe newVue) {
		this.vue = newVue;
		Connexion c = Connexion.getConnexion();
		this.daoEquipe = new DaoEquipe(c);
		this.daoJoueur = new DaoJoueur(c);
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

	@Override
	public void update() {
		try {
			List<Equipe> liste = daoEquipe.getAll();
			List<CaseEquipe> listeCase = convertListToCase(liste);
			this.vue.setListEquipes(listeCase);
		} catch (Exception e) {
		}
	}
}
