package modele.test;

import exceptions.ErreurDate;
import exceptions.FausseDate;
import modele.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPoule {
	private Poule poule;
	private Tournoi tournoi;
	private Character libelle;
	private Equipe e1;
	private Equipe e2;
	private CustomDate debut;
	private CustomDate fin;

	@Before
	public void setUp() throws ErreurDate, FausseDate {
		debut = new CustomDate(2022, 10, 10);
		fin = new CustomDate(2022, 10, 22);

		poule = new Poule(tournoi, 'A');
	}

	@Test
	public void getTournoi() {
		assertEquals(tournoi, poule.getTournoi());
	}

	@Test
	public void getLibelle() {
		assertEquals((Character) 'A', poule.getLibelle());
	}

	@Test
	public void setLibelle() {
	}

	@Test
	public void getPoint() {
	}

	@Test
	public void ajouterPoint() {
	}

	@Test
	public void enleverPoint() {
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
}