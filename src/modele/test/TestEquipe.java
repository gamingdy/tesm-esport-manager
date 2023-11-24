package modele.test;

import static org.junit.Assert.*;

import exceptions.*;
import modele.Country;
import org.junit.Before;
import org.junit.Test;

import modele.Equipe;
import modele.Joueur;

import java.util.Set;
import java.util.TreeSet;

public class TestEquipe {
	private Equipe equipe1;
	private Equipe equipe2;
	private Joueur j;
	private Joueur j2;
	private Joueur j3;
	private Joueur j4;
	private Joueur j5;
	private Joueur j6;

	@Before
	public void setUp() throws EquipeComplete, ErreurJoueur {
		equipe1 = new Equipe("Faze", Country.ALGERIA);
		equipe2 = new Equipe("Patate", Country.ALGERIA);
		j2 = new Joueur("Soso1", equipe1);
		j3 = new Joueur("SoSo2", equipe1);
		j4 = new Joueur("Sososo3", equipe1);
	}


	@Test
	public void testAjoutMemeJoueur() throws EquipeComplete, ErreurJoueur {
		j = new Joueur("Cricri", equipe1);
		equipe1.addJoueur(j);
		assertEquals(4, equipe1.getNombreJoueurs());
	}

	@Test
	public void testgetCountry() {
		assertEquals(Country.ALGERIA, equipe1.getPays());
	}

	@Test
	public void testsetCountry() {
		equipe1.setPays(Country.PALAU);
		assertEquals(Country.PALAU, equipe1.getPays());
	}

	@Test
	public void testCountryName() {
		assertEquals("Palau", Country.PALAU.getName());
	}

	@Test
	public void testCountryCode() {
		assertEquals("pw", Country.PALAU.getCode());
	}


	@Test
	public void testAjout5joueurs() throws EquipeComplete, ErreurJoueur {
		j = new Joueur("Cricri", equipe1);

		j5 = new Joueur("Sosososo4", equipe1);
		assertEquals(5, equipe1.getNombreJoueurs());
	}

	@Test(expected = EquipeComplete.class)
	public void testAjout6joueursException() throws EquipeComplete, ErreurJoueur {
		j = new Joueur("Cricri", equipe1);
		j5 = new Joueur("Sosososo4", equipe1);
		j6 = new Joueur("exception", equipe1);
	}

	@Test(expected = JoueurNonPresent.class)
	public void testgetJoueurInexistant() throws JoueurNonPresent, EquipeVide, EquipeComplete, ErreurJoueur {
		equipe1.getJoueur(new Joueur("klklk", equipe2));
	}

	@Test(expected = EquipeVide.class)
	public void testgetJoueurEquipeVide() throws JoueurNonPresent, EquipeVide, EquipeComplete {
		equipe2.getJoueur(j2);
	}

	@Test
	public void testSupprimerJoueur() throws JoueurNonPresent, EquipeVide, EquipeComplete {
		equipe1.deleteJoueur(j2);
		assertEquals(2, equipe1.getNombreJoueurs());
	}

	@Test(expected = EquipeVide.class)
	public void testSupprimerJoueurEquipeVide() throws JoueurNonPresent, EquipeVide {
		equipe2.deleteJoueur(j);
	}

	@Test
	public void testgetJoueurNormal() throws JoueurNonPresent, EquipeVide, EquipeComplete, ErreurJoueur {
		j = new Joueur("Cricri", equipe1);
		assertEquals(j, equipe1.getJoueur(j));
	}

	@Test
	public void testgetNom() {
		assertEquals("Faze", equipe1.getNom());
	}

	@Test
	public void testSetNom() {
		equipe2.setNom("Faz2");
		assertEquals("Faz2", equipe2.getNom());
	}

	@Test(expected = PointsNegatifs.class)
	public void testSetPointNegatif() throws PointsNegatifs {
		equipe1.setPoint(-1);
	}

	@Test
	public void testSetGetPoint() throws PointsNegatifs {
		equipe1.setPoint(10);
		assertEquals(10, equipe1.getPoint());
	}

	@Test
	public void testReturnGetEquipe() throws EquipeComplete, ErreurJoueur {
		j = new Joueur("Cricri", equipe1);
		Set<Joueur> set = new TreeSet<Joueur>();
		set.add(j);
		equipe2.addJoueur(j);
		assertEquals(set, equipe2.getEquipe());
	}

	@Test(expected = JoueurNonPresent.class)
	public void testDeleteJoueur() throws EquipeComplete, ErreurJoueur, JoueurNonPresent, EquipeVide {
		j = new Joueur("fjkdfj", equipe2);
		equipe1.deleteJoueur(j);
	}

}
