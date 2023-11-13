package modele.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import modele.Arbitre;

import static org.junit.Assert.*;

public class testArbitre {
	Arbitre a;

	@Before
	public void setUp() throws Exception {
		a = new Arbitre(5, "Brando", "Titouan");
	}

	@Test
	public void getId() {
		assertEquals(5, a.getId());
	}

	@Test
	public void getNom() {
		assertEquals("Brando", a.getNom());
	}

	@Test
	public void setNom() {
		a.setNom("Branda");
		assertEquals("Branda", a.getNom());
	}

	@Test
	public void getPrenom() {
		assertEquals("Titouan", a.getPrenom());
	}

	@Test
	public void setPrenom() {
		a.setPrenom("T");
		assertEquals("T", a.getPrenom());
	}
}