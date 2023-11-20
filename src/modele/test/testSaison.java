package modele.test;

import exceptions.EquipeInexistante;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import modele.*;

public class testSaison {
	private Saison s1;
	private Arbitre a1;
	private Arbitre a2;

	@Before
	public void setUp() throws Exception {
		s1 = new Saison(2022);
		a1 = new Arbitre("Gikapa", "Christian");
		a1.setId(42);
		a2 = new Arbitre("Brando", "Titouan");
		a2.setId(2);
	}

	@Test
	public void getAnnee() {
		assertEquals(2022, s1.getAnnee());
	}

	@Test
	public void setAnnee() {
		s1.setAnnee(2023);
		assertEquals(2023, s1.getAnnee());
	}

}