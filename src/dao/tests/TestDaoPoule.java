package dao.tests;

import dao.FactoryDAO;
import modele.Poule;
import modele.Tournoi;

import java.util.LinkedList;
import java.util.List;

public class TestDaoPoule extends TestDao {

	private List<Poule> poules = new LinkedList<>();
	private List<Tournoi> tournois = new LinkedList<>();

	public TestDaoPoule() throws Exception {
		super();


	}

	@Override
	public void testInsert() throws Exception {
		for (Poule p : poules) {
			FactoryDAO.getDaoPoule(getC()).add(p);
		}

	}

	@Override
	public void testDelete() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void testUpdate() throws Exception {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws Exception {
		TestDaoPoule x = new TestDaoPoule();
		x.setup();
		x.testInsert();
	}

	@Override
	public void setup() throws Exception {
		this.tournois = FactoryDAO.getDaoTournoi(getC()).getAll();
		for (Tournoi t : tournois) {
			for (int i = 0; i < 8; i++) {
				poules.add(new Poule(t, (char) (65 + i)));
			}
		}


	}

}
