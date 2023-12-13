package modele.test;

import exceptions.EquipeInexistante;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import modele.*;

import java.util.HashSet;
import java.util.Set;

public class TestSaison {
	private Tournoi tournoi;
	private Saison s1;
	private Arbitre a1;
	private Arbitre a2;
	private CustomDate debut;
	private CustomDate fin;
	private CompteArbitre compteArbitre;
	private Equipe equipe;

	@Before
	public void setUp() throws Exception {
		s1 = new Saison(2022);
		a1 = new Arbitre("Gikapa", "Christian");
		a1.setId(42);
		a2 = new Arbitre("Brando", "Titouan");
		a2.setId(2);
		debut = new CustomDate(2022, 10, 20);
		fin = new CustomDate(2022, 10, 25);
		compteArbitre = new CompteArbitre("adminRLCS", "dsqd");
		tournoi = new Tournoi(s1, "RLCS", debut, fin, Niveau.INTERNATIONAL, compteArbitre);
		equipe = new Equipe("FAZE", Pays.POLOGNE);
	}

	@Test
	public void getAnnee() {
		assertEquals(2022, s1.getAnnee());
	}

	@Test
	public void setAnnee() {
		s1.setAnnee(2023);
		assertEquals(2023, s1.getAnnee());
	}

	@Test
	public void testAddTournoi() {
		s1.addTournoi(tournoi);
	}

	@Test
	public void testGetTournoi() {
		s1.addTournoi(tournoi);
		assertEquals(tournoi, s1.getTournoi("RLCS"));
	}

	@Test
	public void testRemoveTournoi() {
		s1.addTournoi(tournoi);
		s1.deleteTournoi(tournoi);
	}

	@Test
	public void testGetTournois() {
		Set<Tournoi> set = new HashSet<Tournoi>();
		assertEquals(set, s1.getTournois());
	}

	@Test
	public void testAddRemoveArbitre() {
		s1.addArbitre(a1);
		s1.deleteArbitre(a1);
	}

	@Test
	public void testSetArbitres() {
		s1.addArbitre(a1);
		Set<Arbitre> set = new HashSet<>();
		set.add(a1);
		assertEquals(set, s1.getArbitres());
	}

	@Test
	public void testSetEquipes() {
		s1.addEquipe(equipe);
		Set<Equipe> set = new HashSet<>();
		set.add(equipe);
		assertEquals(set, s1.getEquipes());
	}

	@Test
	public void testToString() {
		s1.addEquipe(equipe);
		assertEquals("FAZE : 1000" + System.lineSeparator(), s1.toString());
	}

	@Test
	public void testDeleteEquipe() throws EquipeInexistante {
		s1.addEquipe(equipe);
		s1.deleteEquipe(equipe);
	}

	@Test(expected = EquipeInexistanteException.class)
	public void testDeleteEquipeException() throws EquipeInexistanteException {
		s1.deleteEquipe(equipe);
	}
}