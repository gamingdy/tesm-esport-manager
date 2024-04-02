package dao.tests;

import dao.Connexion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class TestDao {
	private static final Logger LOGGER = Logger.getLogger(TestDao.class.getName());

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
}
