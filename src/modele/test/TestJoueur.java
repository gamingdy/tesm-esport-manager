package modele.test;

import static org.junit.Assert.*;


import exceptions.*;
import modele.Country;
import modele.Equipe;
import org.junit.Before;
import org.junit.Test;


import modele.Joueur;

public class TestJoueur {
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
	public void testSetID() throws IdNotSet {
		j.setId(5);
		assertEquals((Integer) 5, j.getId());
	}

	@Test(expected = IdNotSet.class)
	public void testIdnotSet() throws IdNotSet {
		j.getId();
	}


	@Test
	public void testGetEquipe() throws JoueurNonPresent, EquipeVide, EquipeComplete, ErreurJoueur {
		assertEquals(equipe1,j.getEquipe());
	}

	@Test
	public void testGetNomEquipe(){
		assertEquals(equipe1.getNom(), j.getNomEquipe());
	}

	@Test
	public void testSameJoueur() throws EquipeComplete, ErreurJoueur {
		Joueur j2 = new Joueur("Cricri", equipe1);
		assertEquals(j,j2);
	}

	@Test
	public void testJoueurDifferent() throws EquipeComplete, ErreurJoueur {
		Joueur j2 = new Joueur("Cricri2", equipe1);
		assertNotEquals(j,j2);
	}
	@Test
	public void testEqualsPasMemeType(){
		assertNotEquals(j,5);
	}
}
