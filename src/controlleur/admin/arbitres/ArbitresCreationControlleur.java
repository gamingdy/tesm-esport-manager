package controlleur.admin.arbitres;

import dao.Connexion;
import dao.DaoArbitrage;
import dao.DaoArbitre;
import dao.DaoSaison;
import dao.DaoTournoi;
import modele.Arbitrage;
import modele.Arbitre;
import modele.CustomDate;
import modele.Saison;
import modele.Tournoi;
import vue.Page;
import vue.admin.arbitres.creation.PopupTournoi;
import vue.admin.arbitres.creation.VueAdminArbitresCreation;
import vue.common.JFramePopup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	private static final String ERREUR_SQL = "Erreur SQL";

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
			validerCreationArbitre();
		} else if (e.getSource() == vue.getBoutonAnnuler()) {
			annulerCreationArbitre();
		}
	}

	private void validerCreationArbitre() {
		//recuperer des champs
		String nomArbitre = vue.getTextfieldNom().trim();
		String prenomArbitre = vue.getTextfieldPrenom().trim();
		String telephone = vue.getTextTelephone().trim();

		if (champsArbitreNonValides(nomArbitre, prenomArbitre, telephone)) {
			return;
		}

		Arbitre arbitre = new Arbitre(nomArbitre, prenomArbitre, telephone);

		if (isArbitreDejaExistant(arbitre)) {
			afficherErreur("Ce numéro de telephone est deja utilisé");
			resetChamps();
			return;
		}

		try {
			daoArbitre.add(arbitre);
			if (!listeTournoiChoisi.isEmpty()) {
				addTournoisBdd(listeTournoiChoisi, arbitre);
			}
			afficherSucces("Arbitre ajouté");
			resetChamps();
		} catch (Exception exceptionInsertion) {
			afficherErreur("Erreur d'insertion");
		}
	}

	private void annulerCreationArbitre() {
		ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_LISTE);
		resetChamps();
	}

	private boolean champsArbitreNonValides(String nomArbitre, String prenomArbitre, String telephone) {
		if (nomArbitre.isEmpty()) {
			afficherErreur("Veuillez compléter le nom de l'arbitre");
			return true;
		}
		if (prenomArbitre.isEmpty()) {
			afficherErreur("Veuillez compléter le prénom de l'arbitre");
			return true;
		}
		if (telephone.isEmpty()) {
			afficherErreur("Le téléphone est obligatoire");
			return true;
		}
		if (telephone.length() != 10) {
			afficherErreur("Le téléphone doit contenir 10 chiffres, actuellement il y en a " + telephone.length());
			return true;
		}
		if (!isDigit(telephone)) {
			afficherErreur("Le téléphone ne doit pas contenir des caractères");
			return true;
		}
		return false;
	}

	private void afficherErreur(String message) {
		new JFramePopup("Erreur création arbitre", message, () -> ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION));
	}

	private void afficherSucces(String message) {
		new JFramePopup("Succès", message, () -> ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_LISTE));
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
			afficherErreur(ERREUR_SQL);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == vue.getBoutonAjoutTournois()) {
			if (listeTournoiComboBox.isEmpty()) {
				afficherErreur("Il n'y a plus de tournois disponibles");
				} else {
				try {

					this.popupTournoi = new PopupTournoi("Choisissez le tournoi attribué à l'arbitre", listeTournoiComboBox, () -> {
						this.addTournois(popupTournoi.getSaisie());

						this.listeTournoiChoisi.add(popupTournoi.getSaisie());
						this.listeTournoiComboBox.remove(popupTournoi.getSaisie());

						ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION);
					});
				} catch (Exception ex) {
					afficherErreur(ERREUR_SQL);
				}
			}
		}
	}

	public void addTournois(Tournoi tournoi) {
		String debut = tournoi.getDebut().toString().substring(6);
		String fin = tournoi.getFin().toString().substring(6);
		this.vue.addTournoi(tournoi.getNom(), debut, fin, this.positionTournoi);
	}

	private boolean isDigit(String telephone) {
		for (int i = 0; i < telephone.length(); i++) {
			if (!Character.isDigit(telephone.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private boolean isArbitreDejaExistant(Arbitre arbitre) {
		try {
			Optional<Arbitre> arbitreOptional = daoArbitre.getArbitreByTelephone(arbitre.getNumeroTelephone());
			return arbitreOptional.isPresent();
		} catch (Exception e) {
			afficherErreur(ERREUR_SQL);}
		return false;
	}

	public void addTournoisBdd(List<Tournoi> listeTournoi, Arbitre arbitre) {
		for (Tournoi tournoi : listeTournoi) {
			Arbitrage arbitrage = new Arbitrage(arbitre, tournoi);
			try {
				daoArbitrage.add(arbitrage);
			} catch (Exception e) {
				afficherErreur("Erreur d'insertion d'arbitrage");
				}

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// default implementation ignored
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// default implementation ignored
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// default implementation ignored
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// default implementation ignored
	}
}
