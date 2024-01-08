package dao.tests;

import dao.Connexion;
import dao.DBReset;
import dao.FactoryDAO;

public class GenerationDataTest {
	
	public static void main(String[] args) throws Exception {
		//DBReset.main(args);
		TestDaoEquipe e = new TestDaoEquipe();
		TestDaoArbitre a = new TestDaoArbitre();
		TestDaoJoueur j = new TestDaoJoueur();
		TestDaoNiveau n = new TestDaoNiveau();
		TestDaoTournoi t = new TestDaoTournoi();
		TestDaoSaison s = new TestDaoSaison();
		TestDaoMatche m = new TestDaoMatche();
		TestDaoPartie pa = new TestDaoPartie();
		TestDaoPoule po = new TestDaoPoule();
		
		
		
		/*
		e.setup();
		e.testInsert();
		
		j.setup();
		j.testInsert();
		
		a.setup();
		a.testInsert();
		
		s.setup();
		s.testInsert();
		
		n.setup();
		n.testInsert();
		
		t.setup();
		t.testInsert();
		
		po.setup();
		po.testInsert();
		*/
		m.setup();
		m.testInsert();
		
		pa.setup();
		pa.testInsert();
		
		
		
		FactoryDAO.getDaoPartie(Connexion.getConnexion()).visualizeTable();
		
	}
	

}
