package modele.test;

import static org.junit.Assert.*;


import exceptions.EquipeComplete;
import exceptions.ErreurJoueur;
import exceptions.idNotSet;
import modele.Country;
import modele.Equipe;
import org.junit.Before;
import org.junit.Test;


import modele.Joueur;

public class testJoueur {
	private Joueur j;
	private Equipe equipe1;

	@Before
	public void setUp() throws EquipeComplete, ErreurJoueur {
		equipe1 = new Equipe("Faze", Country.ALGERIA);
		j = new Joueur("Cricri", equipe1);
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
