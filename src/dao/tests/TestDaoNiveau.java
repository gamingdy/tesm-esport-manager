package dao.tests;

import dao.FactoryDAO;
import modele.Niveau;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestDaoNiveau extends TestDao {

	private Niveau[] niveaux;

	@Before
	public void setup() throws Exception {
		niveaux = Niveau.values();
	}


	@Test
	public void testDelete() throws Exception {
		FactoryDAO.getDaoNiveau(getC()).delete(Niveau.INTERNATIONAL.name());
		FactoryDAO.getDaoNiveau(getC()).add(Niveau.INTERNATIONAL);

		// Vérifier si le niveau a bien été supprimé et ajouté de nouveau
		assertFalse(FactoryDAO.getDaoNiveau(getC()).getById(Niveau.INTERNATIONAL.name()).isPresent());
		assertTrue(FactoryDAO.getDaoNiveau(getC()).getById(Niveau.INTERNATIONAL.name()).isPresent());
	}

}
