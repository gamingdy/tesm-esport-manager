package dao.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dao.FactoryDAO;
import modele.Equipe;
import modele.Pays;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TestDaoEquipe extends TestDao {

	private Equipe equipe;
	private Equipe equipe2;
	private List<Equipe> ekip = new LinkedList<>();

	@Before
	public void setUp() throws Exception {
		// Initialisation des équipes pour les tests
		equipe = new Equipe("Bienveillance", Pays.REPUBLIQUE_DEMOCRATIQUE_DU_CONGO);
		equipe2 = new Equipe("Bonheur", Pays.REPUBLIQUE_DU_CONGO);
		for (int i = 0; i < 50; i++) {
			ekip.add(new Equipe(super.randomUsername("NomDequipeSuperCool"), Pays.values()[i]));
		}
	}

	@Test
	public void testInsert() throws Exception {
		// Test d'insertion des équipes
		FactoryDAO.getDaoEquipe(getC()).add(equipe);
		FactoryDAO.getDaoEquipe(getC()).add(equipe2);
		for (int i = 0; i < 50; i++) {
			FactoryDAO.getDaoEquipe(getC()).add(ekip.get(i));
		}
	}

	@Test
	public void testDelete() throws Exception {
		// Test de suppression d'une équipe
		FactoryDAO.getDaoEquipe(getC()).add(equipe);
		FactoryDAO.getDaoEquipe(getC()).delete(equipe.getNom());
	}

	@Test
	public void testUpdate() throws Exception {
		// Test de mise à jour d'une équipe
		Optional<Equipe> equipeToUpdate = FactoryDAO.getDaoEquipe(getC()).getById(equipe2.getNom());
		if (equipeToUpdate.isPresent()) {
			equipeToUpdate.get().setPays(Pays.FRANCE);
			FactoryDAO.getDaoEquipe(getC()).update(equipeToUpdate.get());
		}
	}
}
