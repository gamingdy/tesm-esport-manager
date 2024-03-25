package controlleur.login;

import controlleur.VueObserver;
import dao.Connexion;
import dao.DaoTournoi;
import modele.CompteAdmin;
import modele.CompteArbitre;
import modele.CompteUtilisateur;
import modele.Tournoi;
import vue.Page;
import vue.common.JFramePopup;
import vue.login.VueLogin;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;

public class LoginControlleur implements ActionListener, DocumentListener, KeyListener {
	private VueLogin vue;
	private CompteAdmin admin = CompteAdmin.compteAdmin;
	private CompteArbitre arbitre = null;
	private VueObserver obs;

	public LoginControlleur(VueLogin newVue) {
		this.vue = newVue;

		Connexion c = Connexion.getConnexion();

		DaoTournoi daoTournoi = new DaoTournoi(c);
		try {

			Optional<Tournoi> tournoi = daoTournoi.getTournoiActuel();
			if(tournoi.isPresent()){
				arbitre = daoTournoi.getCompteArbitreByTournoi(tournoi.get().getSaison().getAnnee(), tournoi.get().getNom());
			}
		} catch (Exception e) {
			new JFramePopup("Erreur sql", "Une erreur sql s'est produite, contactez l'administrateur", () ->
					VueObserver.getInstance().notifyVue(Page.LOGIN)
			);
		}
	}

	public void attach(VueObserver obs) {
		this.obs = obs;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bouton = (JButton) e.getSource();
		String champLogin = vue.getIdentifiant().trim();
		String champMotDePasse = vue.getMotDePasse().trim();

		if (bouton.getText() == "Connexion") {
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

					obs.notifyVue(Page.ARBITRE);
					this.vue.clearField();

				}
				if (compteActuel == this.admin) {
					obs.notifyVue(Page.ACCUEIL_ADMIN);
					this.vue.clearField();
				}
			}
		}
	}

	public CompteUtilisateur compteAdminOuUtilisateur(String login, String mdp) {

		if (login.equals(admin.getUsername()) && mdp.equals(admin.getHashMdp())) {
			return this.admin;
		}
		if (this.arbitre != null && login.equals(arbitre.getUsername()) && mdp.equals(arbitre.getHashMdp())) {
			return this.arbitre;
		}

		return null;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		vue.setBoutonActif(!vue.getIdentifiant().isEmpty() && !vue.getMotDePasse().isEmpty());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		vue.setBoutonActif(!vue.getIdentifiant().isEmpty() && !vue.getMotDePasse().isEmpty());

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// default implementation ignored
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == '\n') {
			vue.clicSurBoutonConnexion();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// default implementation ignored
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// default implementation ignored
	}
}
