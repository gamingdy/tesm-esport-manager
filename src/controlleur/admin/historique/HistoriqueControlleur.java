package controlleur.admin.historique;

import dao.Connexion;
import dao.DaoAppartenance;
import dao.DaoInscription;
import dao.DaoMatche;
import dao.DaoSaison;
import dao.DaoTournoi;
import modele.CustomDate;
import modele.Equipe;
import modele.Inscription;
import modele.Matche;
import modele.Tournoi;
import vue.Vue;
import vue.admin.historique.VueAdminHistorique;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HistoriqueControlleur implements ItemListener {
	private VueAdminHistorique vue;
	private DaoSaison daoSaison;
	private DaoInscription daoInscription;
	private DaoMatche daoMatche;
	private DaoTournoi daoTournoi;
	private DaoAppartenance daoAppartenance;
	private List<Integer> saisonListAnnees;
	private List<Matche> matcheList;
	private List<Equipe> equipeList;
	private List<Tournoi> tournoiList;

	public HistoriqueControlleur(VueAdminHistorique newVue) {
		this.vue = newVue;
		Connexion c = Connexion.getConnexion();
		this.daoSaison = new DaoSaison(c);
		this.daoInscription = new DaoInscription(c);
		this.daoMatche = new DaoMatche(c);
		this.daoTournoi = new DaoTournoi(c);
		this.daoAppartenance = new DaoAppartenance(c);
		try {
			saisonListAnnees = daoSaison.getAll().stream().map(saison -> saison.getAnnee()).collect(Collectors.toList());
			Integer anneeSaisonActuelle = CustomDate.now().getAnnee();
			//saisonListAnnees.remove(anneeSaisonActuelle);
			DefaultComboBoxModel<Integer> comboBoxModel = this.vue.getModelSaisons();
			for (Integer a : saisonListAnnees) {
				comboBoxModel.addElement(a);
			}
			Integer saisonAnnee = saisonListAnnees.get(0);
			updateEquipe(saisonAnnee);
			updateMatches(equipeList.get(0));
			updateTournoi(equipeList.get(0), saisonAnnee);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			Integer saisonAnnee = (Integer) e.getItem();
			try {
				updateEquipe(saisonAnnee);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	private VueAdminHistorique.CaseEquipe constructCaseEquipe(Equipe e) {
		try {
			Image img = ImageIO.read(new File("assets/logo-equipes/" + e.getNom() + ".jpg"));
			ImageIcon icon = new ImageIcon(img);
			ImageIcon iconResized = Vue.resize(icon, 70, 70);
			VueAdminHistorique.CaseEquipe caseEquipe = new VueAdminHistorique.CaseEquipe(iconResized, e.getNom(), false);
			return caseEquipe;
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	private void updateEquipe(Integer saison) {
		try {
			equipeList = daoInscription.getEquipeBySaison(saison);
			DefaultTableModel table = this.vue.getModelEquipes();
			List<Object[]> liste = constructObjectArrayEquipe(equipeList);
			for (Object[] ligne : liste) {
				table.addRow(ligne);
			}
		} catch (Exception e) {

		}

	}

	private List<Object[]> constructObjectArrayEquipe(List<Equipe> listEquipe) {
		List<Object[]> resultat = new ArrayList<>();
		for (Equipe e : listEquipe) {
			VueAdminHistorique.CaseEquipe caseEquipe = constructCaseEquipe(e);
			Object[] ligne = new Object[]{0, caseEquipe, 0};
			resultat.add(ligne);
		}

		return resultat;
	}

	private List<Object[]> constructObjectArrayMatch(List<Matche> matcheList) {
		List<Object[]> resultat = new ArrayList<>();
		for (Matche m : matcheList) {
			Equipe equipe1 = m.getEquipe1();
			Equipe equipe2 = m.getEquipe2();
			VueAdminHistorique.CaseEquipe case1 = constructCaseEquipe(equipe1);
			VueAdminHistorique.CaseEquipe case2 = constructCaseEquipe(equipe2);
			CustomDate dateMatche = m.getDateDebutMatche();
			Object[] ligne = new Object[]{dateMatche.toString().substring(6), case1, "0 - 0", case2};
			resultat.add(ligne);
		}
		return resultat;
	}

	private void updateMatches(Equipe equipe) {
		try {
			matcheList = daoMatche.getMatchByEquipe(equipe);
			DefaultTableModel tableMatches = this.vue.getModelMatch();
			List<Object[]> lignes = constructObjectArrayMatch(matcheList);
			for (Object[] ligne : lignes) {
				tableMatches.addRow(ligne);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateTournoi(Equipe equipe, Integer annee) {
		try {
			Optional<Inscription> inscription = daoInscription.getById(annee, equipe.getNom());
			if (!inscription.isPresent()) {
				return;
			}
			tournoiList = daoAppartenance.getTournoiByEquipeForSaison(inscription.get());
			DefaultTableModel tableTournois = this.vue.getModelTournois();
			List<Object[]> lignes = constructArrayFromTournoi(tournoiList);
			for (Object[] ligne : lignes) {
				tableTournois.addRow(ligne);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Object[]> constructArrayFromTournoi(List<Tournoi> listeTournois) {
		List<Object[]> resultat = new ArrayList<>();
		for (Tournoi t : listeTournois) {
			CustomDate dateDebut = t.getDebut();
			Object[] ligne = new Object[]{t.getNom(), dateDebut.toString().substring(6), "0"};
			resultat.add(ligne);
		}
		return resultat;
	}
}
