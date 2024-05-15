package modele.test;

import exceptions.FausseDateException;
import modele.CompteArbitre;
import modele.CustomDate;
import modele.Niveau;
import modele.Saison;
import modele.Tournoi;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestTournoi {
	private Tournoi tournoi;
	private CompteArbitre compteArbitre;

	@Before
	public void setUp() throws FausseDateException {
		Saison saison = new Saison(2023);
		CustomDate debut = new CustomDate(2023, 10, 20);
		CustomDate fin = new CustomDate(2023, 10, 25);
		compteArbitre = new CompteArbitre("adminRLCS", "dsqd");
		tournoi = new Tournoi(saison, "RLCS", debut, fin, Niveau.INTERNATIONAL, compteArbitre);
	}

	@Test
	public void setSaison() {
		tournoi.setSaison(new Saison(2022));
		assertEquals(2022, tournoi.getSaison().getAnnee());
	}

	@Test
	public void setNom() {
		tournoi.setNom("KCTOURNAMENT");
		assertEquals("KCTOURNAMENT", tournoi.getNom());
	}

	@Test
	public void setDebut() {
		tournoi.setDebut(new CustomDate(2022, 10, 13));
		assertEquals(new CustomDate(2022, 10, 13), tournoi.getDebut());
	}

	@Test
	public void setFin() {
		tournoi.setFin(new CustomDate(2022, 10, 25));
		assertEquals(new CustomDate(2022, 10, 25), tournoi.getFin());
	}

	@Test
	public void setNiveau() {
		tournoi.setNiveau(Niveau.INTERNATIONAL);
		assertEquals(Niveau.INTERNATIONAL, tournoi.getNiveau());
	}

	@Test(expected = FausseDateException.class)
	public void testTournoiAvantSaison() throws FausseDateException {
		CustomDate debutAvantSaison = new CustomDate(2022, 10, 20);
		CustomDate finAvantSaison = new CustomDate(2022, 10, 25);
		Saison saison = new Saison(2023);
		new Tournoi(saison, "RLCS", debutAvantSaison, finAvantSaison, Niveau.INTERNATIONAL, new CompteArbitre("adminRLCS", "dsqd"));
	}

	@Test(expected = FausseDateException.class)
	public void testTournoiApresSaison() throws FausseDateException {
		CustomDate debutApresSaison = new CustomDate(2022, 10, 20);
		CustomDate finApresSaison = new CustomDate(2022, 10, 25);
		Saison saison = new Saison(2021);
		new Tournoi(saison, "RLCS", debutApresSaison, finApresSaison, Niveau.INTERNATIONAL, new CompteArbitre("adminRLCS", "dsqd"));
	}

	@Test
	public void testNotEncours() {
		assertFalse(tournoi.isEstEncours());
	}

	@Test
	public void testEncours() {
		tournoi.setEstEncours(true);
		assertTrue(tournoi.isEstEncours());
	}

	@Test
	public void testCompteArbitre() {
		assertEquals(compteArbitre, tournoi.getCompteArbitre());
	}
}