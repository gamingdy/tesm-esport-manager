package modele.test;

import modele.Niveau;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import modele.Arbitre;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class testNiveau {
	@Test
	public void getCoef() {
		assertEquals(1.0F, Niveau.LOCAL.getCoefficient(), 0.0);
	}

	@Test
	public void getNom() {
		assertEquals("Local", Niveau.LOCAL.getNom());
	}

	@Test
	public void getNiveau() {
		Niveau n = Niveau.LOCAL;
		assertEquals(Niveau.LOCAL, n.getNiveau("Local"));
	}

	@Test
	public void getNiveauNull() {
		Niveau n = Niveau.LOCAL;
		assertEquals(null, n.getNiveau("dfsf"));
	}
}
