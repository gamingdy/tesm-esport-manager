package controlleur;

import dao.Connexion;
import dao.DaoEquipe;
import dao.DaoJoueur;
import dao.DaoSaison;
import modele.Pays;
import modele.Equipe;

import vue.admin.equipes.creation.VueAdminEquipesCreation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class EquipeControlleur implements MouseListener, ControlleurObserver {
	private VueAdminEquipesCreation vue;
	private DaoEquipe daoEquipe;
	private DaoSaison daoSaison;
	private DaoJoueur daoJoueur;
	private String champNomEquipe;
	private Pays champPaysEquipe;
	private String codeImage;

	public EquipeControlleur(VueAdminEquipesCreation newVue) {
		this.vue = newVue;
		Connexion c = Connexion.getConnexion();
		daoEquipe = new DaoEquipe(c);
		daoSaison = new DaoSaison(c);
		daoJoueur = new DaoJoueur(c);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("ja cliqué");
		if (e.getSource() == vue.getComboboxPays()) {
			System.out.println("Pays changé");
			vue.setDrapeau(Pays.valueOf(vue.getChampPaysEquipe().toUpperCase()).getCode());
		} else {
			JButton bouton = (JButton) e.getSource();
			if (Objects.equals(bouton.getText(), "Ajout")) {
				System.out.println("Bouton Valider cliqué");
				champNomEquipe = vue.getChampNomEquipe();
				champPaysEquipe = Pays.valueOf(vue.getChampPaysEquipe());
				if (champNomEquipe == "" || champPaysEquipe.getNom() == "") {
					JOptionPane.showMessageDialog(vue, "Un des champs est vide");
				} else if (EquipeDejaExistante(champNomEquipe)) {
					JOptionPane.showMessageDialog(vue, "L'equipe existe deja");
					this.vue.clearField();
				} else {
					Equipe equipeInserer = new Equipe(champNomEquipe, champPaysEquipe);
					try {
						daoEquipe.add(equipeInserer);
					} catch (Exception ex) {
						throw new RuntimeException(ex);
					}
				}
			}
		}

	}

	private boolean EquipeDejaExistante(String nomEquipe) {
		try {
			Equipe equipe = daoEquipe.getById(nomEquipe);
		} catch (Exception e) {

		}
		return true;
	}

	@Override
	public void update() {

	}


	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
