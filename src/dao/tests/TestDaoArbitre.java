package dao.tests;

import dao.FactoryDAO;
import modele.Arbitre;

import java.sql.SQLException;
import java.util.Optional;

public class TestDaoArbitre extends TestDao {

	private Arbitre a;
	private Arbitre b;
	private Arbitre c;

	public TestDaoArbitre() throws SQLException {
		super();

	}

	public void testInsert() throws Exception {
		FactoryDAO.getDaoAbritre(super.getC()).add(a);
		FactoryDAO.getDaoAbritre(super.getC()).add(b);
		FactoryDAO.getDaoAbritre(super.getC()).add(c);
	}

	public void testDelete() throws Exception {
		FactoryDAO.getDaoAbritre(getC()).delete("Brando", "Titouan", 0606060606);
		FactoryDAO.getDaoAbritre(super.getC()).add(a);
	}

	public void testUpdate() throws Exception {
		Optional<Arbitre> b = FactoryDAO.getDaoAbritre(getC()).getById("Brando", "Titouan", 0606060606);
		b.get().setNom(randomUsername("Pelletier"));
		b.get().setPrenom(randomUsername("Alex"));
		FactoryDAO.getDaoAbritre(getC()).update(b.get());
	}

	public static void main(String[] args) throws Exception {
		TestDaoArbitre x = new TestDaoArbitre();
		x.setup();
		x.testInsert();
		x.testDelete();
		x.testUpdate();

	}

	public void setup() throws SQLException {
		a = new Arbitre("Brando", "Titouan", "0606060606");
		b = new Arbitre("Pelletier", "Alex", "0230423423");
		c = new Arbitre("AAAAAAAAA", "bbbbbbbbbbbbb", "0345546546");
	}
}
