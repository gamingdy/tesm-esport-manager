package modele.test;

import static org.junit.Assert.*;

import exceptions.PointsNegatifs;
import modele.Country;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.EquipeComplete;
import exceptions.EquipeVide;
import exceptions.JoueurNonPresent;
import modele.Equipe;
import modele.Joueur;

import java.util.Set;
import java.util.TreeSet;

public class testEquipe {
	Equipe equipe1;
	Joueur j;
	Equipe equipe2;
	Joueur j2 = new Joueur("Soso1", "Faze");
	Joueur j3 = new Joueur("SoSo2", "Faze");
	Joueur j4 = new Joueur("Sososo3", "Faze");
	Joueur j5 = new Joueur("Sosososo4", "Faze");
	Joueur j6 = new Joueur("Simon", "Faze");

	@Before
	public void setUp() {
		equipe1 = new Equipe("Faze", Country.ALGERIA);
		j = new Joueur("Cricri", "Faze");
		equipe2 = new Equipe("Patate", Country.ALGERIA);
	}

	@After
	public void tearDown() {
		equipe1 = null;
		equipe2 = null;
	}

	@Test
	public void testAjoutJoueur() throws EquipeComplete {
		equipe1.addJoueur(j);
		assertEquals(1, equipe1.getNombreJoueurs());
	}

	@Test
	public void testAjoutMemeJoueur() throws EquipeComplete {
		equipe1.addJoueur(j);
		equipe1.addJoueur(j);
		assertEquals(1, equipe1.getNombreJoueurs());
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
	public void testCountry() {
		assertEquals("Palau", Country.PALAU.getCountry());
	}

	@Test
	public void testAjoutJoueurDifferent() throws EquipeComplete {
		Joueur j2 = new Joueur("Soso", "Faze");
		equipe1.addJoueur(j);
		equipe1.addJoueur(j2);
		assertEquals(2, equipe1.getNombreJoueurs());
	}

	@Test
	public void testAjout5joueurs() throws EquipeComplete {
		equipe1.addJoueur(j);
		equipe1.addJoueur(j2);
		equipe1.addJoueur(j3);
		equipe1.addJoueur(j4);
		equipe1.addJoueur(j5);
		assertEquals(5, equipe1.getNombreJoueurs());
	}

	@Test(expected = EquipeComplete.class)
	public void testAjout6joueursException() throws EquipeComplete {
		equipe1.addJoueur(j);
		equipe1.addJoueur(j2);
		equipe1.addJoueur(j3);
		equipe1.addJoueur(j4);
		equipe1.addJoueur(j5);
		equipe1.addJoueur(j6);
	}

	@Test
	public void testNombreJoueursEquipe() {
		assertEquals(0, equipe1.getNombreJoueurs());
	}

	@Test(expected = JoueurNonPresent.class)
	public void testgetJoueurInexistant() throws JoueurNonPresent, EquipeVide, EquipeComplete {
		equipe1.addJoueur(j);
		equipe1.getJoueur(new Joueur("klklk", "Faze"));
	}

	@Test(expected = EquipeVide.class)
	public void testgetJoueurEquipeVide() throws JoueurNonPresent, EquipeVide, EquipeComplete {
		equipe1.getJoueur(j);
	}

	@Test
	public void testSupprimerJoueur() throws JoueurNonPresent, EquipeVide, EquipeComplete {
		equipe1.addJoueur(j);
		equipe1.deleteJoueur(j);
		assertEquals(0, equipe1.getNombreJoueurs());
	}

	@Test(expected = EquipeVide.class)
	public void testSupprimerJoueurEquipeVide() throws JoueurNonPresent, EquipeVide {
		equipe1.deleteJoueur(j);
	}

	@Test(expected = JoueurNonPresent.class)
	public void testSupprimerJoueurPasDanslequipe() throws JoueurNonPresent, EquipeVide, EquipeComplete {
		equipe1.addJoueur(j);
		equipe1.deleteJoueur(j2);
	}

	@Test
	public void testgetJoueurNormal() throws JoueurNonPresent, EquipeVide, EquipeComplete {
		equipe1.addJoueur(j);
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
	public void testReturnGetEquipe() throws EquipeComplete {
		Set<Joueur> set = new TreeSet<Joueur>();
		set.add(j);
		equipe1.addJoueur(j);
		assertEquals(set, equipe1.getEquipe());
	}

}
