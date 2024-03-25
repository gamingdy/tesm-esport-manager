package dao.tests;

import dao.FactoryDAO;
import modele.Matche;
import modele.Partie;

import java.util.LinkedList;
import java.util.List;

public class TestDaoPartie extends TestDao {

	private List<Partie> parties = new LinkedList<>();
	private List<Matche> matches = new LinkedList<>();

	public TestDaoPartie() throws Exception {
		super();

	}

	@Override
	public void testInsert() throws Exception {
		for (int i = 0; i < parties.size(); i++) {
			FactoryDAO.getDaoPartie(getC()).add(parties.get(i));
		}
}

	@Override
	public void testDelete() throws Exception {
		FactoryDAO.getDaoPartie(getC()).delete(0, 3);
		FactoryDAO.getDaoPartie(getC()).delete(0, 4);
	}

	@Override
	public void testUpdate() throws Exception {
		Partie partie = FactoryDAO.getDaoPartie(getC()).getById(0, 0).get();
		partie.setVainqueur(partie.getMatche().getEquipe1());
		FactoryDAO.getDaoPartie(getC()).update(partie);
	}

	public void testGetPartiesByMatch() throws Exception {
		Matche match = FactoryDAO.getDaoMatche(getC()).getById(10).get();
		List<Partie> p = FactoryDAO.getDaoPartie(getC()).getPartieByMatche(match);
	}

	public static void main(String[] args) throws Exception {
		TestDaoPartie x = new TestDaoPartie();
		x.setup();
		//x.testInsert();
		x.testDelete();
		x.testUpdate();
		x.testGetPartiesByMatch();

	}

	@Override
	public void setup() throws Exception {
		matches = FactoryDAO.getDaoMatche(getC()).getAll();
		for (int i = 0; i < matches.size() * 3; i++) {
			parties.add(new Partie(matches.get(i / 3), Math.floorMod(i, 3)));
		}

	}

}
