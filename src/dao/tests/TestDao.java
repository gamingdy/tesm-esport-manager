package dao.tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import dao.Connexion;

public abstract class TestDao {

	private Connexion c;
	
	public TestDao() {
		c = Connexion.getConnexion();
	}

	public Connexion getC() {
		return c;
	}
	
	protected String randomUsername(String name) {
		String str = name;
		List<String> characters = Arrays.asList(str.split(""));
		Collections.shuffle(characters);
		String afterShuffle = "";
		for (String character : characters) {
			afterShuffle += character;
		}
		return afterShuffle;
	}
	
	public abstract void setup() throws Exception;
	public abstract void testInsert() throws Exception;
	public abstract void testDelete() throws Exception;
	public abstract void testUpdate() throws Exception;
}
