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
	public void setUp() throws EquipeCompleteException, JoueurException {
		equipe1 = new Equipe("Faze", Pays.ALGERIE);
		equipe2 = new Equipe("Patate", Pays.ALGERIE);
		j2 = new Joueur("Soso1", equipe1);
		j3 = new Joueur("SoSo2", equipe1);
		j4 = new Joueur("Sososo3", equipe1);
	}


	@Test
	public void testAjoutMemeJoueur() throws EquipeCompleteException, JoueurException {
		j = new Joueur("Cricri", equipe1);
		equipe1.addJoueur(j);
		assertEquals(4, equipe1.getNombreJoueurs());
	}

	@Test
	public void testgetCountry() {
		assertEquals(Country.ALGERIE, equipe1.getPays());
	}

	@Test
	public void testsetCountry() {
		equipe1.setPays(Country.POLOGNE);
		assertEquals(Country.POLOGNE, equipe1.getPays());
	}

	@Test
	public void testCountryName() {
		assertEquals("Pologne", Country.POLOGNE.getNom());
	}

	@Test
	public void testCountryCode() {
		assertEquals("pl", Country.POLOGNE.getCode());
	}


	@Test
	public void testAjout5joueurs() throws EquipeCompleteException, JoueurException {
		j = new Joueur("Cricri", equipe1);

		j5 = new Joueur("Sosososo4", equipe1);
		assertEquals(5, equipe1.getNombreJoueurs());
	}

	@Test(expected = EquipeCompleteException.class)
	public void testAjout6joueursException() throws EquipeCompleteException, JoueurException {
		j = new Joueur("Cricri", equipe1);
		j5 = new Joueur("Sosososo4", equipe1);
		j6 = new Joueur("exception", equipe1);
	}

	@Test(expected = JoueurNonPresentException.class)
	public void testgetJoueurInexistant() throws JoueurNonPresentException, EquipeVideException, EquipeCompleteException, JoueurException {
		equipe1.getJoueur(new Joueur("klklk", equipe2));
	}

	@Test(expected = EquipeVideException.class)
	public void testgetJoueurEquipeVide() throws JoueurNonPresentException, EquipeVideException, EquipeCompleteException {
		equipe2.getJoueur(j2);
	}

	@Test
	public void testSupprimerJoueur() throws JoueurNonPresentException, EquipeVideException, EquipeCompleteException {
		equipe1.deleteJoueur(j2);
		assertEquals(2, equipe1.getNombreJoueurs());
	}

	@Test(expected = EquipeVideException.class)
	public void testSupprimerJoueurEquipeVide() throws JoueurNonPresentException, EquipeVideException {
		equipe2.deleteJoueur(j);
	}

	@Test
	public void testgetJoueurNormal() throws JoueurNonPresentException, EquipeVideException, EquipeCompleteException, JoueurException {
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

	@Test(expected = ExceptionPointsNegatifs.class)
	public void testSetPointNegatif() throws ExceptionPointsNegatifs {
		equipe1.setPoint(-1);
	}

	@Test
	public void testSetGetPoint() throws ExceptionPointsNegatifs {
		equipe1.setPoint(10);
		assertEquals(10, equipe1.getPoint());
	}

	@Test
	public void testReturnGetEquipe() throws EquipeCompleteException, JoueurException {
		j = new Joueur("Cricri", equipe1);
		Set<Joueur> set = new TreeSet<Joueur>();
		set.add(j);
		equipe2.addJoueur(j);
		assertEquals(set, equipe2.getEquipe());
	}

	@Test(expected = JoueurNonPresentException.class)
	public void testDeleteJoueur() throws EquipeCompleteException, JoueurException, JoueurNonPresentException, EquipeVideException {
		j = new Joueur("fjkdfj", equipe2);
		equipe1.deleteJoueur(j);
	}

}
