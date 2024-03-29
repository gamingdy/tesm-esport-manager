package dao.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import dao.Connexion;
import initBd.ESporterManagerInitBDD;
import org.junit.Before;
import org.junit.Test;

import dao.FactoryDAO;
import modele.Appartenance;
import modele.Equipe;
import modele.Inscription;
import modele.Poule;
import modele.Tournoi;

public class TestDaoAppartenance extends TestDao{

	private List<Equipe> e;
	private List<Poule> p;
	private List<Tournoi> t;
	private List<Appartenance> a = new LinkedList<>();

	@Before
	public void setup() throws Exception {

		ESporterManagerInitBDD.initDatabase();
		e = FactoryDAO.getDaoEquipe(getC()).getAll();
		t = FactoryDAO.getDaoTournoi(getC()).getAll();
		for (Tournoi tournoi : t) {
			p = FactoryDAO.getDaoPoule(getC()).getPouleByTournoi(tournoi);
			if (!p.isEmpty()) {
				for (int i = 0, y = 0; i < e.size() - 1 && y < p.size(); i++) {
					Appartenance app = new Appartenance(e.get(i), p.get(y));
					a.add(app);
					if (Math.floorMod(i, 8) == 7) {
						y++;
					}
				}
			}
		}
	}

	@Test(expected = Exception.class)
	public void testInsert() throws Exception {
		for (Appartenance ap : a) {
			FactoryDAO.getDaoAppartenance(getC()).add(ap);
		}
	}

	@Test
	public void testDelete() throws Exception {
		Appartenance app = FactoryDAO.getDaoAppartenance(getC()).getById(a.get(0).getEquipe().getNom(), a.get(0).getPoule().getTournoi().getSaison().getAnnee(), a.get(0).getPoule().getTournoi().getNom(), a.get(0).getPoule().getLibelle()).get();
		FactoryDAO.getDaoAppartenance(getC()).delete(app.getEquipe().getNom(), app.getPoule().getTournoi().getSaison().getAnnee(), app.getPoule().getTournoi().getNom(), app.getPoule().getLibelle());
	}

	@Test
	public void testGetEquipeByPoule() throws Exception {
		List<Equipe> eq = FactoryDAO.getDaoAppartenance(getC()).getEquipeByPoule(a.get(0).getPoule().getTournoi().getNom(), a.get(0).getPoule().getTournoi().getSaison().getAnnee(), a.get(0).getPoule().getLibelle());
		assertNotNull(eq);
		assertFalse(eq.isEmpty());
	}

	@Test
	public void testGetEquipeByTournoi() throws Exception {
		List<Equipe> eq = FactoryDAO.getDaoAppartenance(getC()).getEquipeByTournoi(a.get(0).getPoule().getTournoi().getNom(), a.get(0).getPoule().getTournoi().getSaison().getAnnee());
		assertNotNull(eq);
		assertFalse(eq.isEmpty());
	}

	@Test
	public void testGetPouleByEquipe() throws Exception {
		List<Poule> po = FactoryDAO.getDaoAppartenance(getC()).getPouleByEquipe(a.get(0).getEquipe().getNom(), a.get(0).getPoule().getTournoi().getNom(), a.get(0).getPoule().getTournoi().getSaison().getAnnee());
		assertNotNull(po);
		assertFalse(po.isEmpty());
	}

	@Test
	public void testGetTournoiByEquipe() throws Exception {
		List<Tournoi> po = FactoryDAO.getDaoAppartenance(getC()).getTournoiByEquipe(a.get(0).getEquipe().getNom());
		assertNotNull(po);
		assertFalse(po.isEmpty());
	}

	@Test
	public void testGetTournoiByEquipeForSaison() throws Exception {
		List<Tournoi> to = FactoryDAO.getDaoAppartenance(getC()).getTournoiByEquipeForSaison(new Inscription(a.get(0).getPoule().getTournoi().getSaison(), a.get(0).getEquipe()));
		assertNotNull(to);
		assertFalse(to.isEmpty());
	}

}
