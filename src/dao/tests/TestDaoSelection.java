package dao.tests;

import dao.FactoryDAO;
import modele.Arbitre;
import modele.Saison;
import modele.Selection;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class TestDaoSelection extends TestDao {

	private List<Saison> saisons = new LinkedList<>();
	private List<Arbitre> arbitres = new LinkedList<>();
	private List<Selection> selections = new LinkedList<>();

	@Before
	public void setup() throws Exception {
		arbitres = FactoryDAO.getDaoAbritre(getC()).getAll();
		saisons = FactoryDAO.getDaoSaison(getC()).getAll();
		for (Saison saison : saisons) {
			for (Arbitre arbitre : arbitres) {
				selections.add(new Selection(arbitre, saison));
			}
		}
	}

	@Test
	public void testInsert() throws Exception {
		for (Selection selection : selections) {
			FactoryDAO.getDaoSelection(getC()).add(selection);
		}
	}

	@Test
	public void testDelete() throws Exception {
		FactoryDAO.getDaoSelection(getC()).delete(
				selections.get(0).getArbitre().getNom(),
				selections.get(0).getArbitre().getPrenom(),
				selections.get(0).getArbitre().getNumeroTelephone(),
				selections.get(0).getSaison().getAnnee());
	}

	// Méthode testUpdate() à implémenter si nécessaire

	@Test
	public void testGetArbitreBySaison() throws Exception {
		List<Arbitre> arbitresBySaison = FactoryDAO.getDaoSelection(getC()).getArbitreBySaison(selections.get(0).getSaison().getAnnee());
		arbitresBySaison.forEach(System.out::println);
	}

	@Test
	public void testGetSaisonByArbitre() throws Exception {
		List<Saison> saisonsByArbitre = FactoryDAO.getDaoSelection(getC()).getSaisonByArbitre(
				selections.get(0).getArbitre().getNom(),
				selections.get(0).getArbitre().getPrenom(),
				selections.get(0).getArbitre().getNumeroTelephone());
		saisonsByArbitre.forEach(System.out::println);
	}
}
