package dao.tests;

import dao.FactoryDAO;
import init_bd.ESporterManagerInitBDD;
import modele.Saison;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class TestDaoSaison extends TestDao {

	private List<Saison> saisons = new LinkedList<>();

	@Before
	public void setup() throws Exception {
		ESporterManagerInitBDD.initDatabase();
		for (int i = 0; i < 9; i++) {
			saisons.add(new Saison(2014 + i));
		}
	}

	@Test
	public void testInsert() throws Exception {
		for (Saison s : saisons) {
			FactoryDAO.getDaoSaison(getC()).add(s);
		}
	}
}
