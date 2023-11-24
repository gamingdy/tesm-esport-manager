package modele.test;

import static org.junit.Assert.*;


import exceptions.*;
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
	@Test(expected = ErreurJoueur.class)
	public void testSetEquipe() throws EquipeComplete, ErreurJoueur {
		Equipe equipe2=new Equipe("a",Country.PALAU);
		j.setEquipe(equipe2);
	}
	@Test
	public void testSetEquipeNormal() throws JoueurNonPresent, EquipeVide, EquipeComplete, ErreurJoueur {
		Joueur j2=new Joueur("UnJoueur");
		j2.setEquipe(equipe1);
		assertEquals(j2,equipe1.getJoueur(j2));
	}
	@Test
	public void testGetEquipe() throws JoueurNonPresent, EquipeVide, EquipeComplete, ErreurJoueur {
		assertEquals(equipe1,j.getEquipe());
	}
	@Test
	public void testHashcode() throws EquipeComplete, ErreurJoueur {
		Joueur j2 = new Joueur("Cricri", equipe1);
		assertNotEquals(j,j2);
	}
}
