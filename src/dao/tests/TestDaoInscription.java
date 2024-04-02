package dao.tests;

import dao.FactoryDAO;
import modele.Equipe;
import modele.Inscription;
import modele.Saison;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestDaoInscription extends TestDao {

	private List<Equipe> e = new LinkedList<>();
	private List<Saison> s = new LinkedList<>();
	private List<Inscription> inscr = new LinkedList<>();

	@Before
	public void setup() throws Exception {
		e = FactoryDAO.getDaoEquipe(getC()).getAll();
		s = FactoryDAO.getDaoSaison(getC()).getAll();

		for (Saison sai : s) {
			for (int i = 0; i < 4; i++) {
				Inscription is = new Inscription(sai, e.get(i));
				is.setWorldRank(1000);
				inscr.add(is);
			}
		}
	}

	@Test
	public void testInsert() throws Exception {
		for (Inscription i : inscr) {
			FactoryDAO.getDaoInscription(getC()).add(i);
		}

		// Vérifier si les inscriptions ont bien été ajoutées
		assertEquals(inscr.size(), FactoryDAO.getDaoInscription(getC()).getAll().size());
	}

	@Test
	public void testDelete() throws Exception {
		FactoryDAO.getDaoInscription(getC()).delete(inscr.get(0).getSaison().getAnnee(), inscr.get(0).getEquipe().getNom());

		// Vérifier si l'inscription a bien été supprimée
		assertFalse(FactoryDAO.getDaoInscription(getC()).getById(inscr.get(0).getSaison().getAnnee(), inscr.get(0).getEquipe().getNom()).isPresent());
	}
}
