package controlleur.admin.historique;

import dao.*;
import modele.CustomDate;
import modele.Equipe;
import modele.Inscription;
import modele.Matche;
import modele.Tournoi;
import vue.Vue;
import vue.admin.historique.VueAdminHistorique;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

public class HistoriqueControlleur implements ItemListener, ListSelectionListener {
	private VueAdminHistorique vue;
	private DaoSaison daoSaison;
	private DaoInscription daoInscription;
	private DaoMatche daoMatche;
	private DaoTournoi daoTournoi;
	private DaoEquipe daoEquipe;
	private DaoAppartenance daoAppartenance;
	private List<Integer> saisonListAnnees;
	private List<Matche> matcheList;
	private List<Equipe> equipeList;
	private List<Tournoi> tournoiList;
	private Optional<Equipe> equipeChoisie;
	private Optional<Tournoi> tournoiChoisi;
	private Integer anneeChoisie;

	public HistoriqueControlleur(VueAdminHistorique newVue) {
		this.vue = newVue;
		Connexion c = Connexion.getConnexion();
		this.daoSaison = new DaoSaison(c);
		this.daoInscription = new DaoInscription(c);
		this.daoMatche = new DaoMatche(c);
		this.daoTournoi = new DaoTournoi(c);
		this.daoEquipe=new DaoEquipe(c);
		this.daoAppartenance = new DaoAppartenance(c);
		try {
			saisonListAnnees = daoSaison.getAll().stream().map(saison -> saison.getAnnee()).collect(Collectors.toList());
			anneeChoisie = CustomDate.now().getAnnee();
			//saisonListAnnees.remove(anneeSaisonActuelle);
			DefaultComboBoxModel<Integer> comboBoxModel = this.vue.getModelSaisons();
			for (Integer a : saisonListAnnees) {
				comboBoxModel.addElement(a);
			}
			Integer saisonAnnee = saisonListAnnees.get(0);
			updateEquipe(saisonAnnee);
			updateMatches(equipeList.get(0),Optional.empty());
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
			if (table.getRowCount() > 0) {
				for (int i = table.getRowCount() - 1; i > -1; i--) {
					table.removeRow(i);
				}
			}
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

	private void updateMatches(Equipe equipe,Optional<Tournoi> tournoi) {
		try {
			/*if(!tournoi.isPresent()) {

			}else{*/
				/*matcheList = daoMatche.getMatchByTournoi();*/
				matcheList = daoMatche.getMatchByEquipe(equipe);
				DefaultTableModel tableMatches = this.vue.getModelMatch();
				if (tableMatches.getRowCount() > 0) {
					for (int i = tableMatches.getRowCount() - 1; i > -1; i--) {
						tableMatches.removeRow(i);
					}
				}
				List<Object[]> lignes = constructObjectArrayMatch(matcheList);
				for (Object[] ligne : lignes) {
					tableMatches.addRow(ligne);
				}
			//}
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
			if (tableTournois.getRowCount() > 0) {
				for (int i = tableTournois.getRowCount() - 1; i > -1; i--) {
					tableTournois.removeRow(i);
				}
			}
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

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JTable tableEquipe = this.vue.getTableEquipes();
		JTable tableTournoi = this.vue.getTableTournois();

		if (e.getValueIsAdjusting()) {
			if (tableEquipe.getSelectedRow() > -1 && e.getSource() == tableEquipe.getSelectionModel()) {
				VueAdminHistorique.CaseEquipe caseObjet = (VueAdminHistorique.CaseEquipe) tableEquipe.getValueAt(tableEquipe.getSelectedRow(), 1);
				try {
					equipeChoisie = daoEquipe.getById(caseObjet.getNom());
					if (equipeChoisie.isPresent()) {
						updateMatches(equipeChoisie.get(),Optional.empty());
						updateTournoi(equipeChoisie.get(), anneeChoisie);
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}

			if (tableTournoi.getSelectedRow() > -1 && e.getSource() == tableTournoi.getSelectionModel()) {
				if (equipeChoisie.isPresent()) {
					String nomTournoi =(String) tableTournoi.getValueAt(tableTournoi.getSelectedRow(), 0);
					try {
						tournoiChoisi=daoTournoi.getById(anneeChoisie,nomTournoi);
						if(tournoiChoisi.isPresent()){

						}
					} catch (Exception ex) {
						throw new RuntimeException(ex);
					}
				}
			}
		}
	}

}
