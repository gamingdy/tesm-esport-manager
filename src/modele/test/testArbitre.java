package modele.test;

import exceptions.IdNotSet;
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

	@Test(expected = IdNotSet.class)
	public void getIdNotSet() throws IdNotSet {
		a.getId();
	}

	@Test
	public void getId() throws IdNotSet {
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
	public void testtoString() throws IdNotSet {
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
		assertNotEquals(1, a);
	}

	@Test
	public void testToString() {
		assertEquals( a.getNom() + " " + a.getPrenom(),a.toString());
	}
}