package controlleur.admin.arbitres;

import controlleur.ControlleurObserver;
import dao.Connexion;
import dao.Dao;
import dao.DaoArbitre;
import modele.Arbitre;
import vue.Page;
import vue.admin.arbitres.liste.CaseArbitre;
import vue.admin.arbitres.liste.VueAdminArbitresListe;

import javax.naming.ldap.Control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ArbitresListeControlleur implements ControlleurObserver, ActionListener {
	private VueAdminArbitresListe vue;
	private DaoArbitre daoArbitre;
	private Connexion c;

	public ArbitresListeControlleur(VueAdminArbitresListe newVue) {
		this.vue = newVue;
		c = Connexion.getConnexion();
		daoArbitre = new DaoArbitre(c);
		this.update();
	}

	public void update() {
		try {
			List<Arbitre> arbitreList = daoArbitre.getAll();
			List<CaseArbitre> listeCase = convertToCaseArbitre(arbitreList);
			this.vue.addAll(listeCase);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public List<CaseArbitre> convertToCaseArbitre(List<Arbitre> liste) {
		List<CaseArbitre> listeRes = new ArrayList<>();
		for (Arbitre arbitre : liste) {
			listeRes.add(new CaseArbitre(arbitre.getNom(), arbitre.getPrenom()));
		}
		return listeRes;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vue.getBoutonAjouter()) {
			ArbitresObserver.getInstance().notifyVue(Page.ARBITRES_CREATION);
		}
	}
}
