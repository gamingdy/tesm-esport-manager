package dao.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dao.FactoryDAO;
import modele.Arbitre;

public class TestDaoArbitre extends TestDao{

	private Arbitre a;
	private Arbitre b;
	private Arbitre c;

	@Before
	public void setUp() throws Exception {
		// Initialisation des arbitres pour les tests
		a = new Arbitre("Brando", "Titouan", "0606060606");
		b = new Arbitre("Pelletier", "Alex", "0230423423");
		c = new Arbitre("AAAAAAAAA", "bbbbbbbbbbbbb", "0345546546");
	}

	@Test
	public void testInsert() throws Exception {
		// Test d'insertion des arbitres
		FactoryDAO.getDaoAbritre(getC()).add(a);
		FactoryDAO.getDaoAbritre(getC()).add(b);
		FactoryDAO.getDaoAbritre(getC()).add(c);
	}

	@Test
	public void testDelete() throws Exception {
		// Test de suppression d'un arbitre
		FactoryDAO.getDaoAbritre(getC()).add(a);
		FactoryDAO.getDaoAbritre(getC()).delete("Brando", "Titouan", "0606060606");
	}

	@Test
	public void testUpdate() throws Exception {
		// Test de mise à jour d'un arbitre
		Arbitre arbitreToUpdate = FactoryDAO.getDaoAbritre(getC()).getById("Brando", "Titouan", "0606060606").orElse(null);
		if (arbitreToUpdate != null) {
			arbitreToUpdate.setNom("NewNom");
			arbitreToUpdate.setPrenom("NewPrenom");
			FactoryDAO.getDaoAbritre(getC()).update(arbitreToUpdate);
		}
	}
}
