package modele.test;

import modele.Niveau;
import org.junit.Before;
import org.junit.Test;


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
}
