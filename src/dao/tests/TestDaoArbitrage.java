package dao.tests;

import initBd.ESporterManagerInitBDD;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import dao.FactoryDAO;
import modele.Arbitrage;
import modele.Arbitre;
import modele.Tournoi;
import java.util.LinkedList;
import java.util.List;

public class TestDaoArbitrage extends TestDao{

	private List<Arbitre> a = new LinkedList<>();
	private List<Tournoi> t = new LinkedList<>();
	private List<Arbitrage> ab = new LinkedList<>();

	@Before
	public void setUp() throws Exception {

		ESporterManagerInitBDD.initDatabase();
		// Initialisation des données avant chaque test
		t = FactoryDAO.getDaoTournoi(getC()).getAll();
		a = FactoryDAO.getDaoAbritre(getC()).getAll();
		for (Tournoi tr : t) {
			for (Arbitre arb : a) {
				ab.add(new Arbitrage(arb, tr));
			}
		}
	}

	@After
	public void tearDown() {
		// Nettoyage des données après chaque test
		ab.clear();
		t.clear();
		a.clear();
	}

	@Test
	public void testInsertAndDelete() throws Exception {
		// Test d'insertion
		for (Arbitrage arb : ab) {
			FactoryDAO.getDaoArbitrage(getC()).add(arb);
		}

		// Test de suppression
		FactoryDAO.getDaoArbitrage(getC()).delete(ab.get(0).getArbitre().getNom(), ab.get(0).getArbitre().getPrenom(), ab.get(0).getArbitre().getNumeroTelephone(), ab.get(0).getTournoi().getSaison().getAnnee(), ab.get(0).getTournoi().getNom());
	}

	@Test
	public void testGetArbitreByTournoi() throws Exception {
		// Test de récupération des arbitres par tournoi
		List<Arbitre> ar = FactoryDAO.getDaoArbitrage(getC()).getArbitreByTournoi(ab.get(0).getTournoi().getNom(), ab.get(0).getTournoi().getSaison().getAnnee());
		assertFalse(ar.isEmpty()); // Vérifie que la liste n'est pas vide
	}

	@Test
	public void testGetTournoiByArbitre() throws Exception {
		// Test de récupération des tournois par arbitre
		List<Tournoi> ar = FactoryDAO.getDaoArbitrage(getC()).getTournoiByArbitre(ab.get(0).getArbitre().getNom(), ab.get(0).getArbitre().getPrenom(), ab.get(0).getArbitre().getNumeroTelephone());
		assertFalse(ar.isEmpty()); // Vérifie que la liste n'est pas vide
	}
}
