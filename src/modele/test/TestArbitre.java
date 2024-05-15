package modele.test;

import modele.Arbitre;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestArbitre {
	private Arbitre a;

	@Before
	public void setUp() {
		a = new Arbitre("Brando", "Titouan", "124567890");
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


	@Test
	public void testEquals() {
		assertNotEquals(null, a);
	}

	@Test
	public void testEqualsnotSameClass() {
		assertNotEquals(a, 1);

	}

	@Test
	public void testToString() {
		assertEquals("Arbitre [nom=" + a.getNom() + ", prenom=" + a.getPrenom() + ", numeroTelephone=" + a.getNumeroTelephone() + "]", a.toString());
	}

	@Test
	public void testEqualsNormal() {
		Arbitre a2 = a;
		assertEquals(a, a2);
	}

	@Test
	public void testCompareTo() {
		Arbitre a2 = a;
		assertEquals(0, a.compareTo(a2));
	}

}