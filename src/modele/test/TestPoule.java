package modele.test;

import exceptions.ExceptionDate;
import exceptions.FausseDateException;
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
	public void setUp() throws ExceptionDate, FausseDateException {
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


}