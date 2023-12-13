package modele.test;

import exceptions.ErreurDate;
import exceptions.FausseDate;
import exceptions.MemeEquipe;
import modele.*;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class TestTournoi {
	private Tournoi tournoi;
	private Saison saison;
	private CustomDate debut;
	private CustomDate fin;
	private CompteArbitre compteArbitre;

	@Before
	public void setUp() throws ErreurDate, FausseDate, MemeEquipe {
		saison = new Saison(2023);
		debut = new CustomDate(2023, 10, 20);
		fin = new CustomDate(2023, 10, 25);
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

	@Test(expected = FausseDate.class)
	public void testTournoiAvantSaison() throws FausseDate {
		CustomDate debut = new CustomDate(2022, 10, 20);
		CustomDate fin = new CustomDate(2022, 10, 25);
		Saison saison = new Saison(2023);
		Tournoi tournoi = new Tournoi(saison, "RLCS", debut, fin, Niveau.INTERNATIONAL, new CompteArbitre("adminRLCS", "dsqd"));
	}

	@Test(expected = FausseDate.class)
	public void testTournoiApresSaison() throws FausseDate {
		CustomDate debut = new CustomDate(2022, 10, 20);
		CustomDate fin = new CustomDate(2022, 10, 25);
		Saison saison = new Saison(2021);
		Tournoi tournoi = new Tournoi(saison, "RLCS", debut, fin, Niveau.INTERNATIONAL, new CompteArbitre("adminRLCS", "dsqd"));
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