package dao.tests;

import java.util.LinkedList;
import java.util.List;

import dao.FactoryDAO;
import modele.Saison;

public class TestDaoSaison extends TestDao {
	
	private List<Saison> saisons = new LinkedList<>();


	public TestDaoSaison() {
		super();
		
	}

	@Override
	public void testInsert() throws Exception {
		for(Saison s : saisons) {
			FactoryDAO.getDaoSaison(getC()).add(s);
		}
		System.out.println(FactoryDAO.getDaoSaison(getC()).visualizeTable());
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
		TestDaoSaison x = new TestDaoSaison();
		x.setup();
		x.testInsert();

	}

	@Override
	public void setup() throws Exception {
		for(int i = 0; i<10 ; i++ ) {
			saisons.add(new Saison(2014+i));
		}
		
	}

}
