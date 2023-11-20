package modele.test;

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
		s1=new Saison(2022);
		e1=new Equipe("Faze",Country.ALGERIA);
		e2=new Equipe("KC",Country.PALAU);
		a1=new Arbitre("Christian","Gikapa");
		a2=new Arbitre()
	}

	@Test
	public void getAnnee() {
	}

	@Test
	public void setAnnee() {
	}

	@Test
	public void addArbitre() {
	}

	@Test
	public void deleteArbitre() {
	}

	@Test
	public void getArbitre() {
	}

	@Test
	public void addEquipe() {
	}

	@Test
	public void deleteEquipe() {
	}

	@Test
	public void getEquipes() {
	}

	@Test
	public void testToString() {
	}

	@Test
	public void testSetAnnee() {
	}

	@Test
	public void testAddArbitre() {
	}

	@Test
	public void testDeleteArbitre() {
	}

	@Test
	public void getArbitres() {
	}

	@Test
	public void testAddEquipe() {
	}

	@Test
	public void testDeleteEquipe() {
	}

	@Test
	public void testGetEquipes() {
	}

	@Test
	public void testToString1() {
	}
}