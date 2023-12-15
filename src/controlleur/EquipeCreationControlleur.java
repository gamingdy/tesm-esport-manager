package controlleur;

import dao.Connexion;
import dao.DaoEquipe;
import dao.DaoJoueur;
import dao.DaoSaison;
import modele.Pays;
import modele.Equipe;

import vue.admin.equipes.creation.VueAdminEquipesCreation;
import vue.common.JFramePopup;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

public class EquipeCreationControlleur implements ActionListener, ControlleurObserver, ItemListener {
	private final VueAdminEquipesCreation vue;
	private final DaoEquipe daoEquipe;

	public EquipeCreationControlleur(VueAdminEquipesCreation newVue) {
		this.vue = newVue;
		Connexion c = Connexion.getConnexion();
		daoEquipe = new DaoEquipe(c);
		DaoSaison daoSaison = new DaoSaison(c);
		DaoJoueur daoJoueur = new DaoJoueur(c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bouton = (JButton) e.getSource();
		if (Objects.equals(bouton.getText(), "Ajouter")) {
			String nomEquipe = vue.getNomEquipe();
			Pays champPaysEquipe = Pays.trouverPaysParNom(vue.getChampPaysEquipe());
			if (nomEquipe == "") {
				new JFramePopup("Erreur", "Un des champs est vide", () -> VueObserver.getInstance().notifyVue("Equipe"));
			} else if (EquipeDejaExistante(nomEquipe)) {
				new JFramePopup("Erreur", "L'equipe existe deja", () -> VueObserver.getInstance().notifyVue("Equipe"));
				this.vue.clearField();
			} else {
				Equipe equipeInserer = new Equipe(nomEquipe, champPaysEquipe);
				try {
					daoEquipe.add(equipeInserer);
					new JFramePopup("Succès", "L'équipe est insérée", () -> VueObserver.getInstance().notifyVue("Equipe"));
					this.vue.clearField();

				} catch (Exception ex) {
					new JFramePopup("Erreur", "Erreur d'insertion", () -> VueObserver.getInstance().notifyVue("Equipe"));
					throw new RuntimeException(ex);
				}
			}
		}
	}

	private boolean EquipeDejaExistante(String nomEquipe) {
		try {
			Equipe equipe = daoEquipe.getById(nomEquipe);
			if (equipe != null) {
				return true;
			}
		} catch (Exception ignored) {

		}
		return false;
	}

	@Override
	public void update() {

	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			Pays item = (Pays) e.getItem();
			vue.setDrapeau(item.getCode());
		}
	}
}
