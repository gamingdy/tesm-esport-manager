package dao.tests;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import dao.FactoryDAO;
import modele.Arbitre;

public class TestDaoArbitre extends TestDao{
	
	private Arbitre a;

	public TestDaoArbitre() throws SQLException {
		super();
		a = new Arbitre("Brando","Titouan");
	}
	
	public void testInsert() throws Exception {
		FactoryDAO.getDaoAbritre(super.getC()).add(a);
		System.out.println(FactoryDAO.getDaoAbritre(getC()).visualizeTable());
	}
	
	public void testDelete() throws Exception {
		FactoryDAO.getDaoAbritre(getC()).delete(FactoryDAO.getDaoAbritre(getC()).getLastId());
		System.out.println(FactoryDAO.getDaoAbritre(getC()).visualizeTable());
		FactoryDAO.getDaoAbritre(super.getC()).add(a);
	}
	
	public void testUpdate() throws Exception {
		Optional<Arbitre> b = FactoryDAO.getDaoAbritre(getC()).getById(FactoryDAO.getDaoAbritre(getC()).getLastId());
		b.get().setNom(randomUsername("Pelletier"));
		b.get().setPrenom(randomUsername("Alex"));
		FactoryDAO.getDaoAbritre(getC()).update(b.get());
		System.out.println(FactoryDAO.getDaoAbritre(getC()).visualizeTable());
	}
	
	public static void main(String[] args) throws Exception {
		TestDaoArbitre x = new TestDaoArbitre();
		x.testInsert();
		x.testDelete();
		x.testUpdate();
		
	}
}
