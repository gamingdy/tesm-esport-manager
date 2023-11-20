package modele.test;

import static org.junit.Assert.*;


import exceptions.idNotSet;
import org.junit.Before;
import org.junit.Test;


import modele.Joueur;

public class testJoueur {
	Joueur j;

	@Before
	public void setUp() {
		j = new Joueur("Cricri", "Faze");
	}

	@Test
	public void testSetPseudo() {
		j.setPseudo("Cricri2");
		assertEquals("Cricri2", j.getPseudo());
	}

	@Test
	public void testSetID() throws idNotSet {
		j.setId(5);
		assertEquals((Integer) 5, j.getId());
	}

	@Test(expected = idNotSet.class)
	public void testIdnotSet() throws idNotSet {
		j.getId();
	}

	@Test
	public void testgetNomEquipe() {
		assertEquals("Faze", j.getNomEquipe());
	}
}
