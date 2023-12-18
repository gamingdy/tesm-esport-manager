package controlleur.login;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import controlleur.VueObserver;
import dao.Connexion;
import dao.DaoNiveau;
import dao.DaoSaison;
import dao.DaoTournoi;
import exceptions.FausseDateException;
import modele.*;
import vue.Page;
import vue.login.VueLogin;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LoginControlleur implements ActionListener, DocumentListener, KeyListener {
	private VueLogin vue;
	private CompteAdmin admin = CompteAdmin.compteAdmin;
	private CompteArbitre arbitre;
	private String champLogin;
	private String champMotDePasse;

	private VueObserver obs;

	public LoginControlleur(VueLogin newVue) throws Exception {
		this.vue = newVue;

		Connexion c = Connexion.getConnexion();

		DaoTournoi daoTournoi = new DaoTournoi(c);
		Tournoi tournoi = daoTournoi.getTournoiActuel().get();

		arbitre = daoTournoi.getCompteArbitreByTournoi(tournoi.getSaison().getAnnee(), tournoi.getNom());
	}

	public void attach(VueObserver obs) {
		this.obs = obs;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bouton = (JButton) e.getSource();
		if (bouton.isEnabled()) {
			champLogin = vue.getIdentifiant();
			champMotDePasse = vue.getMotDePasse();

			if (bouton.getText() == "Connexion") {
				if (!champMotDePasse.isEmpty() && !champLogin.isEmpty()) {
					CompteUtilisateur compteActuel = compteAdminOuUtilisateur(champLogin, champMotDePasse);
					if (compteActuel == null) {
						JOptionPane.showMessageDialog(vue, "Identifiant ou mot de passe incorrect");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException ex) {
							throw new RuntimeException(ex);
						}
					} else {
						if (compteActuel instanceof CompteArbitre) {
							JOptionPane.showMessageDialog(vue, "Bienvenue en tant qu'Arbitre");
						}
						if (compteActuel == this.admin) {
							obs.notifyVue(Page.ACCUEIL_ADMIN.getNom());
							this.vue.clearField();
						}
					}
				}
			}
		}
	}

	public CompteUtilisateur compteAdminOuUtilisateur(String login, String mdp) {
		if (login.equals(admin.getUsername()) && mdp.equals(admin.getMdp())) {
			return this.admin;
		}
		if (login.equals(arbitre.getUsername()) && mdp.equals(arbitre.getMdp())) {
			return this.arbitre;
		}
		return null;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		if (vue.getIdentifiant().length() == 0 || vue.getMotDePasse().length() == 0) {
			vue.setBoutonActif(false);
		} else {
			vue.setBoutonActif(true);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		if (vue.getIdentifiant().length() == 0 || vue.getMotDePasse().length() == 0) {
			vue.setBoutonActif(false);
		} else {
			vue.setBoutonActif(true);
		}

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == '\n') {
			vue.clicSurBoutonConnexion();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}