package controlleur.admin.arbitres;

import controlleur.ControlleurObserver;
import controlleur.admin.equipes.EquipesObserver;
import dao.*;
import modele.Arbitre;
import modele.Equipe;
import modele.Saison;
import modele.Tournoi;
import vue.Page;
import vue.admin.arbitres.liste.CaseArbitre;
import vue.admin.arbitres.liste.VueAdminArbitresListe;
import vue.admin.equipes.liste.CaseEquipe;
import vue.common.JFramePopup;

import javax.naming.ldap.Control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArbitresListeControlleur implements ControlleurObserver, ActionListener {
	private VueAdminArbitresListe vue;
	private DaoArbitre daoArbitre;
	private DaoArbitrage daoArbitrage;
	private DaoSaison daoSaison;
	private Connexion c;
	private List<Arbitre> arbitreList;
	private List<CaseArbitre> listeCase;

	public ArbitresListeControlleur(VueAdminArbitresListe newVue) {
		this.vue = newVue;
		c = Connexion.getConnexion();
		daoArbitre = new DaoArbitre(c);
		daoArbitrage = new DaoArbitrage(c);
		daoSaison = new DaoSaison(c);
		this.update();
	}

	public void update() {
		try {
			List<Arbitre> liste = daoArbitre.getAll();

			if (this.listeCase == null) {
				this.arbitreList = liste;
				this.listeCase = convertToCaseArbitre(this.arbitreList);
				this.vue.addAll(this.listeCase);
			} else {
				List<Arbitre> differences = liste.stream()
						.filter(element -> !this.arbitreList.contains(element))
						.collect(Collectors.toList());
				List<CaseArbitre> differencesCase = convertToCaseArbitre(differences);
				this.listeCase.addAll(differencesCase);
				this.arbitreList.addAll(differences);
				this.vue.addAll(differencesCase);
			}

		} catch (Exception e) {
		}

	}

	public List<CaseArbitre> convertToCaseArbitre(List<Arbitre> liste) {
		List<CaseArbitre> listeRes = new ArrayList<>();
		for (Arbitre arbitre : liste) {
			listeRes.add(new CaseArbitre(arbitre.getNom(), arbitre.getPrenom()));
		}
		return listeRes;
	}

	public void supprimerArbitre(Arbitre arbitre) {
		try {
			if (isArbitreArbitrageSaisonActuelle(arbitre)) {
				new JFramePopup("Erreur", "L'arbitre est attribué aux tournois dans la saison actuelle", () -> EquipesObserver.getInstance().notifyVue(Page.ARBITRES_LISTE));
			} else {
				/*daoArbitre.delete(arbitre.getId());*/
			}
		} catch (Exception e) {

		}
	}

	private boolean isArbitreArbitrageSaisonActuelle(Arbitre arbitre) throws Exception {
		Saison saison = daoSaison.getLastSaison();
		List<Tournoi> tournois = daoArbitrage.getTournoiByArbitre();
		for (Tournoi t : tournois) {
			if (t.getSaison() == saison) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vue.getBoutonAjouter()) {
			ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION);
		}
	}
}
