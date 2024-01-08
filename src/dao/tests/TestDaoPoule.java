package dao.tests;

import java.util.LinkedList;
import java.util.List;

import dao.FactoryDAO;
import modele.Poule;
import modele.Tournoi;

public class TestDaoPoule extends TestDao {

	private List<Poule> poules = new LinkedList<>();
	private Tournoi tournoi;
	
	public TestDaoPoule() throws Exception {
		super();
		
		
	}
	
	@Override
	public void testInsert() throws Exception {
		for(Poule p : poules) {
			FactoryDAO.getDaoPoule(getC()).add(p);
		}
		System.out.println(FactoryDAO.getDaoPoule(getC()).visualizeTable());
		
		
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
		this.tournoi = FactoryDAO.getDaoTournoi(getC()).getById(2023,"zzzz").get();
		for(int i=0; i<8; i++) {
			poules.add(new Poule(tournoi,(char)(65+i)));
		}
		
	}

}
