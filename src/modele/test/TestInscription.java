package modele.test;

import modele.Equipe;
import modele.Inscription;
import modele.Pays;
import modele.Saison;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class TestInscription {

	private Inscription inscription1;
	private Inscription inscription2;
	private Inscription inscription3;

	@Before
	public void setUp() {
		Saison saison1 = new Saison(2023);
		Equipe equipe1 = new Equipe("TeamA", Pays.PAYS_BAS);

		Saison saison2 = new Saison(2023);
		Equipe equipe2 = new Equipe("TeamA", Pays.POLOGNE);

		inscription1 = new Inscription(saison1, equipe1);
		inscription2 = new Inscription(saison2, 1500, equipe2);
		inscription3 = new Inscription(saison1, equipe1);
	}

	@Test
	public void testDefaultConstructor() {
		assertNotNull(inscription1);
		assertEquals("TeamA", inscription1.getEquipe().getNom());
		assertEquals(2023, inscription1.getSaison().getAnnee());
		assertEquals(Integer.valueOf(1000), inscription1.getWorldRank());
	}

	@Test
	public void testParameterizedConstructor() {
		assertNotNull(inscription2);
		assertEquals("TeamA", inscription2.getEquipe().getNom());
		assertEquals(2023, inscription2.getSaison().getAnnee());
		assertEquals(Integer.valueOf(1500), inscription2.getWorldRank());
	}

	@Test
	public void testGettersAndSetters() {
		Saison newSaison = new Saison(2024);
		Equipe newEquipe = new Equipe("TeamB", Pays.POLOGNE);

		inscription1.setSaison(newSaison);
		inscription1.setEquipe(newEquipe);
		inscription1.setWorldRank(1200);

		assertEquals(newSaison, inscription1.getSaison());
		assertEquals(newEquipe, inscription1.getEquipe());
		assertEquals(Integer.valueOf(1200), inscription1.getWorldRank());
	}

	@Test
	public void testEquals() {
		assertEquals(inscription1, inscription3);
		assertNotEquals(inscription1, inscription2);
		assertNotEquals(inscription2, inscription3);
	}
}
