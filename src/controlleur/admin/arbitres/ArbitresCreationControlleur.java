package controlleur.admin.arbitres;

import controlleur.admin.tournois.TournoisObserver;
import dao.*;
import exceptions.FausseDateException;
import modele.*;
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
	private List<Tournoi> listeTournoiComboBox;
	private List<Tournoi> listeTournoiChoisi;
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
		listeTournoiChoisi = new ArrayList<>();
		resetComboBoxTournois();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vue.getBoutonValider()) {
			String nomArbitre = vue.getTextfieldNom().trim();
			String prenomArbitre = vue.getTextfieldPrenom().trim();
			String telephone = vue.getTextTelephone().trim();
			if (nomArbitre.isEmpty()) {
				new JFramePopup("Erreur", "Veuillez completer le nom de l'arbitre", () -> ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION));
			} else if (prenomArbitre.isEmpty()) {
				new JFramePopup("Erreur", "Veuillez completer le prénom de l'arbitre", () -> ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION));
			} else if (telephone.isEmpty()) {
				new JFramePopup("Erreur", "Le telephone est obligatoire", () -> ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION));
			} else if (telephone.length() != 10) {
				new JFramePopup("Erreur", "Le telephone doit contenir 10 chiffres, actuellement il y en a " + telephone.length(), () -> ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION));
			} else if (!isDigit(telephone)) {
				new JFramePopup("Erreur", "Le telephone ne doit pas contenir des caractères", () -> ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION));
			} else {
				Arbitre arbitre = new Arbitre(nomArbitre, prenomArbitre, Integer.parseInt(telephone));
				;
				try {
					daoArbitre.add(arbitre);
					if (!listeTournoiChoisi.isEmpty()) {
						addTournoisBdd(listeTournoiChoisi, arbitre);
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
		this.listeTournoiChoisi.clear();
		resetComboBoxTournois();
	}

	public void resetComboBoxTournois() {
		try {
			Saison saison = daoSaison.getLastSaison();
			CustomDate finSaison = new CustomDate(saison.getAnnee(), 12, 31);
			this.listeTournoiComboBox = daoTournoi.getTournoiBetweenDate(CustomDate.now(), finSaison);
			if (listeTournoiComboBox.get(0).isEstEncours()) {
				listeTournoiComboBox.remove(0);
			}
		} catch (Exception e) {

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == vue.getBoutonAjoutTournois()) {
			try {

				this.popupTournoi = new PopupTournoi("Choisissez le tournoi attribué à l'arbitre", listeTournoiComboBox, () -> {
					this.addTournois(popupTournoi.getSaisie());

					this.listeTournoiChoisi.add(popupTournoi.getSaisie());
					this.listeTournoiComboBox.remove(popupTournoi.getSaisie());
					if (listeTournoiComboBox.isEmpty()) {
						this.vue.getBoutonAjoutTournois().setVisible(false);
					}
					ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION);
				});
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	public void addTournois(Tournoi tournoi) {
		String debut = tournoi.getDebut().toString().substring(6);
		String fin = tournoi.getFin().toString().substring(6);
		this.vue.addTournoi(tournoi.getNom(), debut, fin, this.positionTournoi);
	}

	private boolean isDigit(String telephone) {
		try {
			Integer.parseInt(telephone);
			return true;
		} catch (NumberFormatException nb) {
			return false;
		}
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
