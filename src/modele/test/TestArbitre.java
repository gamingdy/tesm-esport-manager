package modele.test;

import exceptions.IdNotSetException;
import org.junit.Before;
import org.junit.Test;
import modele.Arbitre;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class TestArbitre {
	private Arbitre a;

	@Before
	public void setUp() throws Exception {
		a = new Arbitre("Brando", "Titouan");
	}

	@Test(expected = IdNotSetException.class)
	public void getIdNotSet() throws IdNotSetException {
		a.getId();
	}

	@Test
	public void getId() throws IdNotSetException {
		a.setId(5);
		assertEquals((Integer) 5, a.getId());
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
	public void testtoString() throws IdNotSetException {
		a.setId(6);
		assertEquals("(6)Brando Titouan", "(" + a.getId() + ")" + a.getNom() + " " + a.getPrenom());
	}

	@Test
	public void testHashcodeArbitre() {
		a.setId(1);
		Set<Arbitre> set = new HashSet<Arbitre>();
		set.add(a);
		set.add(a);
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
		assertEquals(a.getNom() + " " + a.getPrenom(), a.toString());
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

	@Test
	public void testCompareToPasPareil() {
		Arbitre a2 = new Arbitre("A", "A");
		assertEquals(1, a.compareTo(a2));
	}
}