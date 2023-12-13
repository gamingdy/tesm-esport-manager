package modele.test;

import static org.junit.Assert.*;


import exceptions.*;
import modele.Pays;
import modele.Equipe;
import org.junit.Before;
import org.junit.Test;


import modele.Joueur;

import java.util.HashSet;
import java.util.Set;

public class TestJoueur {
	private Joueur j;
	private Equipe equipe1;

	@Before
	public void setUp() throws EquipeCompleteException, JoueurException {
		equipe1 = new Equipe("Faze", Pays.ALGERIE);
		j = new Joueur("Cricri", equipe1);
	}

	@Test
	public void testSetPseudo() {
		j.setPseudo("Cricri2");
		assertEquals("Cricri2", j.getPseudo());
	}

	@Test
	public void testSetID() throws IdNotSetException {
		j.setId(5);
		assertEquals((Integer) 5, j.getId());
	}

	@Test
	public void testHashcode() {
		Set<Joueur> joueurs = new HashSet<>();
		joueurs.add(j);
		joueurs.add(j);
	}

	@Test(expected = IdNotSetException.class)
	public void testIdnotSet() throws IdNotSetException {
		j.getId();
	}


	@Test
	public void testGetEquipe() throws JoueurNonPresentException, EquipeVideException, EquipeCompleteException, JoueurException {
		assertEquals(equipe1, j.getEquipe());
	}

	@Test
	public void testGetNomEquipe() {
		assertEquals(equipe1.getNom(), j.getNomEquipe());
	}

	@Test
	public void testSameJoueur() throws EquipeCompleteException, JoueurException {
		Joueur j2 = new Joueur("Cricri", equipe1);
		assertEquals(j, j2);
	}

	@Test
	public void testJoueurDifferent() throws EquipeCompleteException, JoueurException {
		Joueur j2 = new Joueur("Cricri2", equipe1);
		assertNotEquals(j, j2);
	}

	@Test
	public void testEqualsPasMemeType() {
		assertNotEquals(j, 5);
	}
}
