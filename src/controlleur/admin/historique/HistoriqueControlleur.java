package controlleur.admin.historique;

import controlleur.AbstractControlleur;
import controlleur.VueObserver;
import dao.Connexion;
import dao.DaoAppartenance;
import dao.DaoEquipe;
import dao.DaoInscription;
import dao.DaoMatche;
import dao.DaoPartie;
import dao.DaoSaison;
import dao.DaoTournoi;
import exceptions.ExceptionPointsNegatifs;
import exceptions.FausseDateException;
import exceptions.GagnantNonChoisiException;
import exceptions.IdNotSetException;
import exceptions.MemeEquipeException;
import modele.CustomDate;
import modele.Equipe;
import modele.Inscription;
import modele.Matche;
import modele.ModeleSaison;
import modele.ModeleTournoi;
import modele.Partie;
import modele.Saison;
import modele.Tournoi;
import vue.Page;
import vue.Vue;
import vue.admin.historique.VueAdminHistorique;
import vue.common.JFramePopup;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class HistoriqueControlleur extends AbstractControlleur implements ItemListener, ListSelectionListener {
	private static final Logger LOGGER= Logger.getLogger(HistoriqueControlleur.class.getName());
	private VueAdminHistorique vue;
	private DaoSaison daoSaison;
	private DaoInscription daoInscription;
	private DaoMatche daoMatche;
	private DaoTournoi daoTournoi;
	private DaoPartie daoPartie;
	private DaoEquipe daoEquipe;
	private DaoAppartenance daoAppartenance;

	private List<Integer> saisonListAnnees;
	private Optional<Equipe> equipeChoisie;
	private Optional<Tournoi> tournoiChoisi;
	private Saison anneeChoisie;
	private Etat etat;

	private static final String ERREUR_SQL_EQUIPE_MESSAGE = "Erreur SQL lors de la récupération des équipes";
	private static final String ERREUR_SQL_MATCH_MESSAGE = "Erreur SQL lors de la récupération des matchs";

	private enum Etat {
		EQUIPES, TOURNOIS;
	}

	public HistoriqueControlleur(VueAdminHistorique newVue) {
		this.vue = newVue;
		Connexion c = Connexion.getConnexion();
		this.daoSaison = new DaoSaison(c);
		this.daoInscription = new DaoInscription(c);
		this.daoMatche = new DaoMatche(c);
		this.daoTournoi = new DaoTournoi(c);
		this.daoEquipe = new DaoEquipe(c);
		this.daoAppartenance = new DaoAppartenance(c);
		this.equipeChoisie = Optional.empty();
		this.daoPartie = new DaoPartie(c);
		try {
			saisonListAnnees = daoSaison.getAll().stream().map(Saison::getAnnee).collect(Collectors.toList());
			anneeChoisie = daoSaison.getLastSaison();
			tournoiChoisi = Optional.empty();

			DefaultComboBoxModel<Integer> comboBoxModel = this.vue.getModelSaisons();
			for (Integer a : saisonListAnnees) {
				comboBoxModel.addElement(a);
			}
			comboBoxModel.setSelectedItem(saisonListAnnees.get(saisonListAnnees.size() - 1));
			updateEquipe(anneeChoisie);
			updateMatches(Optional.empty(), Optional.empty());
			updateTournoi(Optional.empty(), anneeChoisie);

		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			afficherErreur(ERREUR_SQL_EQUIPE_MESSAGE);
			}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			try {
				tournoiChoisi = Optional.empty();
				Optional<Saison> optional = daoSaison.getById((Integer) e.getItem());
				if (!optional.isPresent()) {
					throw new SQLException("Pas de saisons dans la BD");
				}
				anneeChoisie = optional.get();
				updateEquipe(anneeChoisie);
				updateMatches(Optional.empty(), Optional.empty());
				updateTournoi(Optional.empty(), anneeChoisie);
			} catch (Exception ex) {
				LOGGER.severe(ex.getMessage());
				afficherErreur(ERREUR_SQL_EQUIPE_MESSAGE);
			}
		}
	}

	private VueAdminHistorique.CaseEquipe constructCaseEquipe(Equipe e) {
		try {
			Image img = ImageIO.read(new File(recupererCheminIconeEquipe(e.getNom())));
			ImageIcon icon = new ImageIcon(img);
			ImageIcon iconResized = Vue.resize(icon, 70, 70);
			return new VueAdminHistorique.CaseEquipe(iconResized, e.getNom());
		} catch (IOException ex) {
			LOGGER.severe(ex.getMessage());
			afficherErreur(ERREUR_SQL_EQUIPE_MESSAGE);
		}
		return null;
	}

	private void updateEquipe(Saison saison) {
		try {
			List<Equipe> equipeList = new ArrayList<>();
			if (tournoiChoisi.isPresent()) {
				equipeList = daoAppartenance.getEquipeByTournoi(tournoiChoisi.get().getNom(), saison.getAnnee());
			} else {
				Set<Equipe> classement = ModeleSaison.getClassement(anneeChoisie);
				if (!classement.isEmpty()) {
					equipeList = new ArrayList<>(classement);
				}
			}
			if (!equipeList.isEmpty()) {
				DefaultTableModel table = this.vue.getModelEquipes();

				List<Object[]> liste = constructObjectArrayEquipe(equipeList);
				if (table.getRowCount() > 0) {
					for (int i = table.getRowCount() - 1; i > -1; i--) {
						table.removeRow(i);
					}
				}
				for (Object[] ligne : liste) {
					table.addRow(ligne);
				}
			}
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			afficherErreur(ERREUR_SQL_EQUIPE_MESSAGE);}

	}

	private List<Object[]> constructObjectArrayEquipe(List<Equipe> listEquipe) {
		List<Object[]> resultat = new ArrayList<>();

		for (Equipe e : listEquipe) {
			try {
				Optional<Inscription> inscription = daoInscription.getById(anneeChoisie.getAnnee(), e.getNom());
				if (inscription.isPresent()) {
					VueAdminHistorique.CaseEquipe caseEquipe = constructCaseEquipe(e);
					Object[] ligne = new Object[]{inscription.get().getWorldRank(), caseEquipe, e.getPoint()};
					resultat.add(ligne);
				} else {
					VueAdminHistorique.CaseEquipe caseEquipe = constructCaseEquipe(e);
					Object[] ligne = new Object[]{1000, caseEquipe, 0.0};
					resultat.add(ligne);
				}

			} catch (Exception exc) {
				afficherErreur(ERREUR_SQL_EQUIPE_MESSAGE);}
		}

		return resultat;
	}

	private List<Object[]> constructObjectArrayMatch(List<Matche> matcheList) {
		List<Object[]> resultat = new ArrayList<>();
		for (Matche m : matcheList) {
			Equipe equipe1 = m.getEquipe1();
			Equipe equipe2 = m.getEquipe2();
			CustomDate dateMatche = m.getDateDebutMatche();
			try {
				List<Partie> partieList = daoPartie.getPartieByMatche(m);
				m.setVainqueur(partieList.get(0).getVainqueur());
			}catch (SQLException | MemeEquipeException | FausseDateException | IdNotSetException | GagnantNonChoisiException e){
				LOGGER.severe(e.getMessage());
			}
			Equipe winner = m.getVainqueur();
			int value1 = 0;
			int value2 = 0;
			if (winner != null) {
				if (winner.equals(equipe1)) {
					value1 = 1;
					value2 = 0;
				} else {
					value1 = 0;
					value2 = 1;
				}
			}
			Object[] ligne = new Object[]{dateMatche.toString().substring(6), equipe1.getNom(), value1 + " - " + value2, equipe2.getNom()};
			resultat.add(ligne);
		}
		return resultat;
	}

	private void updateMatches(Optional<Equipe> equipe, Optional<Tournoi> tournoi)  {
			List<Matche> matcheList= new ArrayList<>();
			if (!equipe.isPresent() && !tournoi.isPresent()) {
				try{
					matcheList = daoMatche.getMatchBySaison(anneeChoisie);
				} catch (SQLException | FausseDateException | MemeEquipeException e) {
					LOGGER.severe(e.getMessage());
					afficherErreur(ERREUR_SQL_MATCH_MESSAGE);
				}
			} else if (!equipe.isPresent()) {
				try {
					matcheList = daoMatche.getMatchByTournoi(anneeChoisie.getAnnee(), tournoi.get().getNom());
				} catch (SQLException | FausseDateException | MemeEquipeException ex) {
					LOGGER.severe(ex.getMessage());
					afficherErreur(ERREUR_SQL_MATCH_MESSAGE);
				}
			} else if (!tournoi.isPresent()) {
				try{
					matcheList = daoMatche.getMatchByEquipe(equipe.get());
				} catch (SQLException | FausseDateException | MemeEquipeException e) {
					LOGGER.severe(e.getMessage());
					afficherErreur(ERREUR_SQL_MATCH_MESSAGE);
				}
			} else {
				try {
					matcheList = daoMatche.getMatchByEquipeForTournoi(equipe.get(), tournoi.get());
				} catch (SQLException | FausseDateException | MemeEquipeException e) {
					LOGGER.severe(e.getMessage());
					afficherErreur(ERREUR_SQL_MATCH_MESSAGE);
				}
			}
			DefaultTableModel tableMatches = this.vue.getModelMatch();
			if (tableMatches.getRowCount() > 0) {
				for (int i = tableMatches.getRowCount() - 1; i > -1; i--) {
					tableMatches.removeRow(i);
				}
			}
			if(matcheList.isEmpty()){
				return;
			}
			try{
			List<Object[]> lignes = constructObjectArrayMatch(matcheList);
			for (Object[] ligne : lignes) {
				tableMatches.addRow(ligne);
			}
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			}
	}

	private void updateTournoi(Optional<Equipe> equipe, Saison annee) {
		try {
			Saison saison = annee;
			DefaultTableModel tableTournois = this.vue.getModelTournois();
			Optional<Inscription> inscription = Optional.empty();
			if (tableTournois.getRowCount() > 0) {
				for (int i = tableTournois.getRowCount() - 1; i > -1; i--) {
					tableTournois.removeRow(i);
				}
			}

			List<Object[]> lignes;
			if (!equipe.isPresent()) {
				List<Tournoi> tournoiList = daoTournoi.getTournoiBySaison(saison);
				lignes = constructArrayFromTournoi(tournoiList, inscription);
			} else {
				Equipe equipe2 = equipe.get();
				inscription = daoInscription.getById(annee.getAnnee(), equipe2.getNom());
				if (!inscription.isPresent()) {
					return;
				}
				List<Tournoi> tournoiList = daoAppartenance.getTournoiByEquipeForSaison(inscription.get());
				lignes = constructArrayFromTournoi(tournoiList, inscription);
			}
			for (Object[] ligne : lignes) {
				tableTournois.addRow(ligne);
			}
		} catch (Exception e) {
			afficherErreur("Erreur SQL lors de la récupération des tournois");}
	}

	private List<Object[]> constructArrayFromTournoi(List<Tournoi> listeTournois, Optional<Inscription> inscription) throws SQLException, MemeEquipeException, FausseDateException, IdNotSetException, GagnantNonChoisiException, ExceptionPointsNegatifs {
		List<Object[]> resultat = new ArrayList<>();

		if (inscription.isPresent()) {
			for (Tournoi t : listeTournois) {
				Set<Equipe> classementTournoi = ModeleTournoi.getClassement(t);
				Equipe e = inscription.get().getEquipe();
				Equipe selection = null;
				for (Equipe eq : classementTournoi) {
					if (eq.equals(e)) {
						selection = eq;
					}
				}
				CustomDate dateDebut = t.getDebut();
				Object[] ligne = new Object[]{t.getNom(), dateDebut.toString().substring(6), ModeleTournoi.getPointsSaison(selection, t)};
				resultat.add(ligne);
			}
			return resultat;
		} else {
			for (Tournoi t : listeTournois) {
				CustomDate dateDebut = t.getDebut();
				Object[] ligne = new Object[]{t.getNom(), dateDebut.toString().substring(6), 0};
				resultat.add(ligne);
			}
		}
		return resultat;
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		JTable tableEquipe = this.vue.getTableEquipes();
		JTable tableTournoi = this.vue.getTableTournois();

		if (!e.getValueIsAdjusting()) {
			return;
		}
		if (tableEquipe.getSelectedRow() > -1 && e.getSource() == tableEquipe.getSelectionModel()) {
			VueAdminHistorique.CaseEquipe caseObjet = (VueAdminHistorique.CaseEquipe) tableEquipe.getValueAt(tableEquipe.getSelectedRow(), 1);
			try {
				equipeChoisie = daoEquipe.getById(caseObjet.getNom());
				if (etat == null) {
					etat = Etat.EQUIPES;
				}

				if (equipeChoisie.isPresent()) {
					updateMatches(equipeChoisie, Optional.empty());
					if (etat == Etat.EQUIPES) {
						updateTournoi(equipeChoisie, anneeChoisie);
					}
				}
			} catch (Exception exception) {

				afficherErreur("Erreur SQL lors de la récupération des joueurs de l'équipe " + caseObjet.getNom());
			}
		}


		if (tableTournoi.getSelectedRow() > -1 && e.getSource() == tableTournoi.getSelectionModel()) {
			String nomTournoi = (String) tableTournoi.getValueAt(tableTournoi.getSelectedRow(), 0);
			try {
				tournoiChoisi = daoTournoi.getById(anneeChoisie.getAnnee(), nomTournoi);
				if (etat == null) {
					etat = Etat.TOURNOIS;
				}

					if (etat == Etat.TOURNOIS) {
						updateEquipe(anneeChoisie);
					}
					updateMatches(equipeChoisie, tournoiChoisi);
				} catch (Exception ex) {
					afficherErreur("Erreur SQL s'est produite, contactez l'administrateur");}
			}
		}

private void afficherErreur(String message) {
	new JFramePopup("Erreur historique", message, () -> VueObserver.getInstance().notifyVue(Page.SAISON_PRECEDENTES));
}
}
