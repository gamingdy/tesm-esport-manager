package controlleur.admin.historique;

import dao.Connexion;
import dao.DaoInscription;
import dao.DaoSaison;
import modele.CustomDate;
import modele.Equipe;
import vue.Vue;
import vue.admin.historique.VueAdminHistorique;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HistoriqueControlleur implements ItemListener {
	private VueAdminHistorique vue;
	private DaoSaison daoSaison;
	private DaoInscription daoInscription;
	private List<Integer> saisonListAnnees;
	private List<Equipe> equipeList;

	public HistoriqueControlleur(VueAdminHistorique newVue) {
		this.vue = newVue;
		Connexion c = Connexion.getConnexion();
		this.daoSaison = new DaoSaison(c);
		this.daoInscription = new DaoInscription(c);
		try {
			saisonListAnnees = daoSaison.getAll().stream().map(saison -> saison.getAnnee()).collect(Collectors.toList());
			Integer anneeSaisonActuelle = CustomDate.now().getAnnee();
			//saisonListAnnees.remove(anneeSaisonActuelle);
			DefaultComboBoxModel<Integer> comboBoxModel = this.vue.getModelSaisons();
			for (Integer a : saisonListAnnees) {
				comboBoxModel.addElement(a);
			}
			Integer saisonAnnee = saisonListAnnees.get(0);
			equipeList = daoInscription.getEquipeBySaison(saisonAnnee);
			System.out.println(equipeList.size());
			DefaultTableModel table = this.vue.getModelEquipes();
			List<Object[]> liste = constructObjectArray(equipeList);
			for (Object[] ligne : liste) {
				table.addRow(ligne);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println("j'ai cliqué");
		if (e.getStateChange() == ItemEvent.SELECTED) {
			Integer saisonAnnee = (Integer) e.getItem();
			try {
				equipeList = daoInscription.getEquipeBySaison(saisonAnnee);
				System.out.println(equipeList.size());
				DefaultTableModel table = this.vue.getModelEquipes();
				List<Object[]> liste = constructObjectArray(equipeList);
				for (Object[] ligne : liste) {
					table.addRow(ligne);
				}
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

	private List<Object[]> constructObjectArray(List<Equipe> listEquipe) {
		List<Object[]> resultat = new ArrayList<>();
		for (Equipe e : listEquipe) {
			VueAdminHistorique.CaseEquipe caseEquipe = constructCaseEquipe(e);
			Object[] ligne = new Object[]{0, caseEquipe, 0};
			resultat.add(ligne);
		}

		return resultat;
	}
}
