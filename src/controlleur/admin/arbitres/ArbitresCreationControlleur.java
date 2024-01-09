package controlleur.admin.arbitres;

import controlleur.admin.tournois.TournoisObserver;
import dao.*;
import exceptions.FausseDateException;
import modele.Arbitrage;
import modele.Arbitre;
import modele.Saison;
import modele.Tournoi;
import vue.Page;
import vue.admin.arbitres.creation.PopupTournoi;
import vue.admin.arbitres.creation.VueAdminArbitresCreation;
import vue.admin.equipes.creation.VueAdminEquipesCreation;
import vue.admin.equipes.liste.VueAdminEquipesListe;
import vue.common.JFramePopup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArbitresCreationControlleur implements ActionListener, MouseListener {
	private VueAdminArbitresCreation vue;
	private DaoArbitre daoArbitre;
	private DaoTournoi daoTournoi;
	private DaoSaison daoSaison;
	private DaoArbitrage daoArbitrage;
	private List<Tournoi> listeTournoi;
	private Connexion c;
	private PopupTournoi popupTournoi;
	private int positionTournoi = 0;

	public ArbitresCreationControlleur(VueAdminArbitresCreation newVue) {
		this.vue = newVue;
		c = Connexion.getConnexion();
		daoArbitre = new DaoArbitre(c);
		daoTournoi = new DaoTournoi(c);
		daoSaison = new DaoSaison(c);
		daoArbitrage = new DaoArbitrage(c);
		listeTournoi = new ArrayList<>();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vue.getBoutonValider()) {
			String nomArbitre = vue.getTextfieldNom().trim();
			String prenomArbitre = vue.getTextfieldPrenom().trim();
			if (nomArbitre.isEmpty()) {
				new JFramePopup("Erreur", "Veuillez completer le nom de l'arbitre", () -> ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION));
			} else if (prenomArbitre.isEmpty()) {
				new JFramePopup("Erreur", "Veuillez completer le prénom de l'arbitre", () -> ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION));
			} else {
				Arbitre arbitre = null;
				try {
					arbitre = new Arbitre(nomArbitre, prenomArbitre);
				} catch (SQLException ex) {
					throw new RuntimeException(ex);
				}
				try {
					daoArbitre.add(arbitre);
					if(!listeTournoi.isEmpty()){
						addTournoisBdd(listeTournoi, arbitre);
					}
					new JFramePopup("Succès", "Arbitre ajouté", () -> ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_LISTE));
					resetChamps();
				} catch (Exception exceptionInsertion) {
					exceptionInsertion.printStackTrace();
					new JFramePopup("Erreur", "Erreur d'insertion", () -> ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION));
				}
			}

		}
		if (e.getSource() == vue.getBoutonAnnuler()) {
			ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_LISTE);
			resetChamps();
		}
	}

	public void resetChamps() {
		this.vue.clearField();
		this.listeTournoi.clear();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == vue.getBoutonAjoutTournois()) {
			try {
				Saison saison = daoSaison.getLastSaison();
				List<Tournoi> tournoi = daoTournoi.getTournoiBySaison(saison);
				this.popupTournoi = new PopupTournoi("Choisissez le tournoi attribué à l'arbitre", tournoi, () -> {
					ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION);
					this.addTournois(popupTournoi.getSaisie());
					this.listeTournoi.add(popupTournoi.getSaisie());
				});
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			} catch (FausseDateException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	public void addTournois(Tournoi tournoi) {
		String debut = tournoi.getDebut().toString().substring(6);
		String fin = tournoi.getFin().toString().substring(6);
		this.vue.addTournoi(tournoi.getNom(), debut, fin, this.positionTournoi);
	}

	public void addTournoisBdd(List<Tournoi> listeTournoi, Arbitre arbitre) {
		for (Tournoi tournoi : listeTournoi) {
			Arbitrage arbitrage = new Arbitrage(arbitre, tournoi);
			try {
				daoArbitrage.add(arbitrage);
			} catch (Exception e) {
				new JFramePopup("Erreur", "Erreur d'insertion d'arbitrage", () -> ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION));
			}

		}

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
