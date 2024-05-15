package modele.test;

import modele.Poule;
import modele.Tournoi;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPoule {
	private Poule poule;
	private Tournoi tournoi;

	@Before
	public void setUp() {
		poule = new Poule(tournoi, 'A');
	}

	@Test
	public void getTournoi() {
		assertEquals(tournoi, poule.getTournoi());
	}

	@Test
	public void getLibelle() {
		assertEquals((Character) 'A', poule.getLibelle());
	}


}