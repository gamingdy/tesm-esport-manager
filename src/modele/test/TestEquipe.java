package modele.test;

import exceptions.EquipeCompleteException;
import exceptions.EquipeVideException;
import exceptions.ExceptionPointsNegatifs;
import exceptions.JoueurNonPresentException;
import modele.Equipe;
import modele.Joueur;
import modele.Pays;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class TestEquipe {
	private Equipe equipe1;
	private Equipe equipe2;
	private Joueur j;
	private Joueur j2;

	@Before
	public void setUp() throws EquipeCompleteException {
		equipe1 = new Equipe("Faze", Pays.ALGERIE);
		equipe2 = new Equipe("Patate", Pays.ALGERIE);
		j2 = new Joueur("Soso1", equipe1);
	}


	@Test
	public void testAjoutMemeJoueur() throws EquipeCompleteException {
		j = new Joueur("Cricri", equipe1);
		equipe1.addJoueur(j);
		Joueur j1 = new Joueur("Cricri", equipe1);
		equipe1.addJoueur(j1);
		assertEquals(2, equipe1.getNombreJoueurs());
	}

	@Test
	public void testgetCountry() {
		assertEquals(Pays.ALGERIE, equipe1.getPays());
	}

	@Test
	public void testsetCountry() {
		equipe1.setPays(Pays.POLOGNE);
		assertEquals(Pays.POLOGNE, equipe1.getPays());
	}

	@Test
	public void testCountryName() {
		assertEquals("Pologne", Pays.POLOGNE.getNom());
	}

	@Test
	public void testCountryCode() {
		assertEquals("pl", Pays.POLOGNE.getCode());
	}


	@Test
	public void testAjout5joueurs() throws EquipeCompleteException {
		new Joueur("Cricri", equipe1);
		new Joueur("Sosososo4", equipe1);
		new Joueur("Sosososo3", equipe1);
		new Joueur("Sosososo5", equipe1);
		assertEquals(5, equipe1.getNombreJoueurs());
	}

	@Test(expected = EquipeCompleteException.class)
	public void testAjout6joueursException() throws EquipeCompleteException {
		j = new Joueur("Cricri", equipe1);
		new Joueur("Sosososo4", equipe1);
		new Joueur("exception", equipe1);
		new Joueur("Sosososo3", equipe1);
		new Joueur("Sosososo5", equipe1);
	}

	@Test(expected = JoueurNonPresentException.class)
	public void testgetJoueurInexistant() throws EquipeCompleteException, JoueurNonPresentException, EquipeVideException {
		equipe1.getJoueur(new Joueur("klklk", equipe2));
	}

	@Test(expected = EquipeVideException.class)
	public void testgetJoueurEquipeVide() throws JoueurNonPresentException, EquipeVideException {
		equipe2.getJoueur(j2);
	}

	@Test
	public void testSupprimerJoueur() throws JoueurNonPresentException, EquipeVideException {
		assertEquals(1, equipe1.getNombreJoueurs());
		equipe1.deleteJoueur(j2);
		assertEquals(0, equipe1.getNombreJoueurs());
	}

	@Test(expected = EquipeVideException.class)
	public void testSupprimerJoueurEquipeVide() throws JoueurNonPresentException, EquipeVideException {
		equipe2.deleteJoueur(j);
	}

	@Test
	public void testGetJoueurNormal() throws JoueurNonPresentException, EquipeVideException, EquipeCompleteException {
		j = new Joueur("Cricri", equipe1);
		assertEquals(j, equipe1.getJoueur(j));
	}

	@Test
	public void testGetNom() {
		assertEquals("Faze", equipe1.getNom());
	}


	@Test(expected = ExceptionPointsNegatifs.class)
	public void testSetPointNegatif() throws ExceptionPointsNegatifs {
		equipe1.setPoint((float) -1.0);
	}

	@Test
	public void testSetGetPoint() throws ExceptionPointsNegatifs {
		equipe1.setPoint(10.0f);
		assertEquals(10.0f, equipe1.getPoint(), 0.01);
	}

	@Test
	public void testReturnGetEquipe() throws EquipeCompleteException {
		j = new Joueur("Cricri", equipe1);
		Set<Joueur> set = new TreeSet<>();
		set.add(j);
		equipe2.addJoueur(j);
		assertEquals(set, equipe2.getEquipe());
	}

	@Test(expected = JoueurNonPresentException.class)
	public void testDeleteJoueur() throws EquipeCompleteException, JoueurNonPresentException, EquipeVideException {
		j = new Joueur("fjkdfj", equipe2);
		equipe1.deleteJoueur(j);
	}

}
