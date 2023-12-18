package dao.tests;

import dao.Connexion;

public class TestDao {

	private Connexion c;
	
	public TestDao() {
		c = Connexion.getConnexion();
	}

	public Connexion getC() {
		return c;
	}
}
