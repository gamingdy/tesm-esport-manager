package dao.tests;

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

	public TestDaoEquipe() {
		super();


	}

	public void testInsert() throws Exception {
		FactoryDAO.getDaoEquipe(getC()).add(equipe);
		FactoryDAO.getDaoEquipe(getC()).add(equipe2);
		for (int i = 0; i < 50; i++) {
			FactoryDAO.getDaoEquipe(getC()).add(ekip.get(i));
		}

	}

	public void testDelete() throws Exception {
		FactoryDAO.getDaoEquipe(getC()).delete(equipe.getNom());
		FactoryDAO.getDaoEquipe(getC()).add(equipe);
	}

	public void testUpdate() throws Exception {
		Optional<Equipe> b = FactoryDAO.getDaoEquipe(getC()).getById(equipe2.getNom());
		b.get().setPays(Pays.FRANCE);
		FactoryDAO.getDaoEquipe(getC()).update(b.get());
	}

	public static void main(String[] args) throws Exception {
		TestDaoEquipe x = new TestDaoEquipe();
		x.setup();
		x.testInsert();
		x.testDelete();
		x.testUpdate();

	}

	@Override
	public void setup() throws Exception {
		equipe = new Equipe("Bienveillance", Pays.REPUBLIQUE_DEMOCRATIQUE_DU_CONGO);
		equipe2 = new Equipe("Bonheur", Pays.REPUBLIQUE_DU_CONGO);
		for (int i = 0; i < 50; i++) {
			ekip.add(new Equipe(super.randomUsername("NomDequipeSuperCool"), Pays.values()[i]));
		}

	}


}
