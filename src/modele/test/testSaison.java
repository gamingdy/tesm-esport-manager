package modele.test;

import exceptions.EquipeInexistante;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import modele.*;

public class testSaison {
	private Saison s1;
	private Equipe e1;
	private Equipe e2;
	private Arbitre a1;
	private Arbitre a2;

	@Before
	public void setUp() throws Exception {
		s1 = new Saison(2022);
		e1 = new Equipe("Faze", Country.ALGERIA);
		e2 = new Equipe("KC", Country.PALAU);
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

	@Test
	public void addArbitre() {
		s1.addArbitre(a1);
		assertEquals(1, s1.getArbitres().size());
	}

	@Test
	public void deleteArbitre() {
		s1.addArbitre(a1);
		s1.addArbitre(a2);
		s1.deleteArbitre(a2);
		assertEquals(1, s1.getArbitres().size());
	}

	@Test
	public void getEquipes() {
		s1.addEquipe(e1);
		assertEquals(1, s1.getEquipes().size());

	}

	@Test
	public void testToString() {
		s1.addEquipe(e1);
		assertEquals("Faze : 1000" + System.lineSeparator(), s1.toString());
	}

	@Test
	public void testDeleteEquipe() throws EquipeInexistante {
		s1.addEquipe(e1);
		s1.deleteEquipe(e1);
		assertEquals(0, s1.getEquipes().size());
	}

	@Test(expected = EquipeInexistante.class)
	public void testDeleteEquipeEquipeInexistante() throws EquipeInexistante {
		s1.addEquipe(e1);
		s1.deleteEquipe(e2);
	}

}