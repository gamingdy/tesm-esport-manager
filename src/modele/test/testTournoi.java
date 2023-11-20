package modele.test;

import exceptions.ErreurDate;
import exceptions.FausseDate;
import modele.*;
import org.junit.Test;
import exceptions.idNotSet;
import org.junit.Before;
import modele.Arbitre;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.*;

public class testTournoi {
	private Tournoi tournoi;
	private Saison saison;
	private CustomDate debut;
	private CustomDate fin;
	private Niveau niveau;
	private Equipe e1;
	private Equipe e2;
	private Matche m1;

	@Before
	public void setUp() throws ErreurDate, FausseDate {
		saison = new Saison(2023);
		debut = new CustomDate(2023, 10, 20);
		fin = new CustomDate(2023, 10, 25);
		tournoi = new Tournoi(saison, "RLCS", debut, fin, niveau);
	}

	@Test
	public void setSaison() {
		tournoi.setSaison(new Saison(2022));
		assertEquals(2022, tournoi.getSaison());
	}

	@Test
	public void setNom() {
		tournoi.setNom("KCTOURNAMENT");
		assertEquals("KCTOURNAMENT", tournoi.getNom());
	}

	@Test
	public void setDebut() throws ErreurDate {
		tournoi.setDebut(new CustomDate(2022, 10, 13));
		assertEquals(new CustomDate(2022, 10, 13), tournoi.getDebut());
	}

	@Test
	public void setFin() throws ErreurDate {
		tournoi.setFin(new CustomDate(2022, 10, 25));
		assertEquals(new CustomDate(2022, 10, 25), tournoi.getFin());
	}

	@Test
	public void setNiveau() {
		tournoi.setNiveau(Niveau.INTERNATIONAL);
		assertEquals(Niveau.INTERNATIONAL, tournoi.getNiveau());
	}

	@Test
	public void addEquipe() {
		
	}

	@Test
	public void removeEquipe() {
	}

	@Test
	public void setEquipes() {
	}

	@Test
	public void addMatche() {
	}

	@Test
	public void removeMatche() {
	}

	@Test
	public void setMatches() {
	}
}