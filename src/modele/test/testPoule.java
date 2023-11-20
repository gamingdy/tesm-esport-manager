package modele.test;

import exceptions.ErreurDate;
import modele.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class testPoule {
	private Poule poule;
	private Tournoi tournoi;
	private Character libelle;
	private Equipe e1;
	private Equipe e2;
	private CustomDate debut;

	@Before
	public void setUp() throws ErreurDate {
		debut = new CustomDate(2022, 10, 10);

	}

	@Test
	public void getTournoi() {
	}

	@Test
	public void getLibelle() {
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