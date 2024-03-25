package dao.tests;

import dao.FactoryDAO;
import modele.Niveau;

public class TestDaoNiveau extends TestDao {

	private Niveau[] niveaux;

	public TestDaoNiveau() {
		super();

	}

	@Override
	public void testInsert() throws Exception {
		for (Niveau n : niveaux) {
			FactoryDAO.getDaoNiveau(getC()).add(n);
		}
	}

	@Override
	public void testDelete() throws Exception {
		FactoryDAO.getDaoNiveau(getC()).delete(Niveau.INTERNATIONAL.name());
		FactoryDAO.getDaoNiveau(getC()).add(Niveau.INTERNATIONAL);
	}

	@Override
	public void testUpdate() throws Exception {

	}

	public static void main(String[] args) throws Exception {
		TestDaoNiveau x = new TestDaoNiveau();
		x.setup();
		x.testInsert();
		x.testDelete();
	}

	@Override
	public void setup() throws Exception {
		niveaux = Niveau.values();

	}

}
