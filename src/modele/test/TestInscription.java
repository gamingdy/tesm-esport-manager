package modele.test;

import modele.Inscription;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestInscription {
	private Inscription inscription;
	private String nom;
	private int annee;

	@Before
	public void setUp() throws Exception {
		nom = "Nom";
		annee = 2022;
		inscription = new Inscription(annee, nom);
	}

	@Test
	public void testSetNom() {
		inscription.setNom("Nom2");
		assertEquals("Nom2", inscription.getNom());
	}

	@Test
	public void testSetAnnee() {
		inscription.setAnnee(2023);
		assertEquals((Integer) 2023, inscription.getAnnee());
	}
}