package modele.test;

import exceptions.FausseDateException;
import exceptions.IdNotSetException;
import exceptions.MemeEquipeException;
import org.junit.Before;
import org.junit.Test;

import modele.Categorie;
import modele.CompteArbitre;
import modele.CustomDate;
import modele.Equipe;
import modele.Matche;
import modele.Niveau;
import modele.Pays;
import modele.Saison;
import modele.Tournoi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestMatche {
	private Matche m;
	private Equipe e1 = new Equipe("Faze", Pays.POLOGNE);
	private Equipe e2 = new Equipe("KC", Pays.ALGERIE);
	private CustomDate d1;
	private CustomDate d2;
	private Tournoi tournoi;
	private Saison saison;

	@Before
	public void setUp() throws Exception {
		d1 = new CustomDate(2022, 10, 10);
		d2 = new CustomDate(2022, 10, 30);
		saison = new Saison(2022);
		tournoi = new Tournoi(saison, "RLCS", d1, d2, Niveau.INTERNATIONAL, new CompteArbitre("cricri", "1234"));
		m = new Matche(1, d1, Categorie.DEMI_FINALE, e1, e2, tournoi);
	}

	@Test(expected = MemeEquipeException.class)
	public void testConstructeurExceptionMemeEquipe() throws MemeEquipeException, FausseDateException {
		m = new Matche(1, d1, Categorie.DEMI_FINALE, e1, e1, tournoi);
	}

	@Test(expected = FausseDateException.class)
	public void testContructeurExceptionFausseDate() throws MemeEquipeException, FausseDateException {
		d1 = new CustomDate(2022, 9, 10);
		m = new Matche(1, d1, Categorie.DEMI_FINALE, e1, e2, tournoi);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructeurExceptionNull() throws MemeEquipeException, FausseDateException {
		m = new Matche(1, d1, Categorie.DEMI_FINALE, e1, null, tournoi);
	}

	@Test
	public void getId() throws IdNotSetException {
		m.setId(1);
		assertEquals((Integer) 1, m.getId());
	}

	@Test(expected = IdNotSetException.class)
	public void getIdNotSet() throws IdNotSetException {
		assertEquals((Integer) 1, m.getId());
	}

	@Test
	public void getNombreMaxParties() {
		assertEquals(1, m.getNombreMaxParties());
	}

	@Test
	public void setNombreMaxParties() {
		m.setNombreMaxParties(2);
		assertEquals(2, m.getNombreMaxParties());
	}


	@Test
	public void setDateDebutMatche() {
		m.setDateDebutMatche(d2);
		assertEquals(d2, m.getDateDebutMatche());
	}


	@Test
	public void getLibelle() {
		assertEquals(Categorie.DEMI_FINALE, m.getCategorie());
	}

	@Test
	public void setLibelle() {
		m.setCategorie(Categorie.FINALE);
		assertEquals(Categorie.FINALE, m.getCategorie());
	}

	@Test
	public void getEquipe1() {
		assertEquals(e1, m.getEquipe1());
	}

	@Test
	public void setEquipe1() {
		m.setEquipe1(e2);

	}

	@Test
	public void getEquipe2() {
		assertEquals(e2, m.getEquipe2());
	}

	@Test
	public void setEquipe2() {
		m.setEquipe2(e1);
	}

	@Test
	public void getTournoi() {
		assertEquals(tournoi, m.getTournoi());
	}

	@Test
	public void getSaison() {
		assertEquals(saison, m.getSaison());
	}


	@Test
	public void setVainqueur() {
		m.setVainqueur(e1);
		assertEquals(e1, m.getVainqueur());
	}

	@Test
	public void setVainqueurNull() {
		assertNull(m.getVainqueur());
	}
}