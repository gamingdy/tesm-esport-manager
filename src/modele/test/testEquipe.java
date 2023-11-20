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
	private Equipe equipe1;
	private Joueur j;
	private Equipe equipe2;
	private Joueur j2;
	private Joueur j3;
	private Joueur j4;
	private Joueur j5;
	private Joueur j6;

	@Before
	public void setUp() throws EquipeComplete {
		equipe1 = new Equipe("Faze", Country.ALGERIA);

		j2 = new Joueur("Soso1", equipe1);
		j3 = new Joueur("SoSo2", equipe1);
		j4 = new Joueur("Sososo3", equipe1);
		j5 = new Joueur("Sosososo4", equipe1);
		equipe2 = new Equipe("Patate", Country.ALGERIA);
	}


	@Test
	public void testAjoutMemeJoueur() throws EquipeComplete {
		j = new Joueur("Cricri", equipe1);
		j = new Joueur("Cricri", equipe1);
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
	public void testCountry() {
		assertEquals("Palau", Country.PALAU.getCountry());
	}


	@Test
	public void testAjout5joueurs() throws EquipeComplete {
		j = new Joueur("Cricri", equipe1);
		assertEquals(5, equipe1.getNombreJoueurs());
	}

	@Test(expected = EquipeComplete.class)
	public void testAjout6joueursException() throws EquipeComplete {
		j6 = new Joueur("exception", equipe1);
	}

	@Test(expected = JoueurNonPresent.class)
	public void testgetJoueurInexistant() throws JoueurNonPresent, EquipeVide, EquipeComplete {
		equipe1.getJoueur(new Joueur("klklk", equipe2));
	}

	@Test(expected = EquipeVide.class)
	public void testgetJoueurEquipeVide() throws JoueurNonPresent, EquipeVide, EquipeComplete {
		equipe1.getJoueur(j2);
	}

	@Test
	public void testSupprimerJoueur() throws JoueurNonPresent, EquipeVide, EquipeComplete {
		equipe1.deleteJoueur(j2);
		assertEquals(3, equipe1.getNombreJoueurs());
	}

	@Test(expected = EquipeVide.class)
	public void testSupprimerJoueurEquipeVide() throws JoueurNonPresent, EquipeVide {
		equipe2.deleteJoueur(j);
	}

	@Test(expected = JoueurNonPresent.class)
	public void testSupprimerJoueurPasDanslequipe() throws JoueurNonPresent, EquipeVide, EquipeComplete {
		equipe1.addJoueur(j);
		equipe1.deleteJoueur(j2);
	}

	@Test
	public void testgetJoueurNormal() throws JoueurNonPresent, EquipeVide, EquipeComplete {
		assertEquals(j, equipe1.getJoueur(j2));
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
