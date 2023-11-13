package modele.test;

import org.junit.Before;
import org.junit.Test;
import modele.*;

import java.sql.Date;

import static org.junit.Assert.*;

public class testMatche {
	Matche m;
	Equipe e1 = new Equipe("Faze");
	Equipe e2 = new Equipe("KC");

	Saison s = new Saison((short) 2022);
	Tournoi tournoi = new Tournoi(s, "RLCS", new Date(2022, 01, 1), new Date(2022, 12, 20), Niveau.INTERNATIONAL);

	@Before
	public void setUp() throws Exception {

		m = new Matche(1, (byte) 1, new Date(2022, 12, 2), Categorie.DEMI_FINALE, e1, e2, tournoi);
	}

	@Test
	public void getId() {
		assertEquals(1, m.getId());
	}

	@Test
	public void getNombreMaxParties() {
		assertEquals((byte) 1, m.getNombreMaxParties());
	}

	@Test
	public void setNombreMaxParties() {
		m.setNombreMaxParties((byte) 2);
		assertEquals((byte) 2, m.getNombreMaxParties());
	}

	@Test
	public void getDateDebutMatche() {
		assertEquals(new Date(2022, 12, 2), m.getDateDebutMatche());
	}

	@Test
	public void setDateDebutMatche() {
		m.setDateDebutMatche(new Date(2022, 12, 3));
		assertEquals(new Date(2022, 12, 3), m.getDateDebutMatche());
	}


	@Test
	public void getLibelle() {
		assertEquals(Categorie.DEMI_FINALE, m.getLibelle());
	}

	@Test
	public void setLibelle() {
		m.setLibelle(Categorie.FINALE);
		assertEquals(Categorie.FINALE, m.getLibelle());
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
	}

	@Test
	public void getTournoi() {
	}

	@Test
	public void setTournoi() {
	}
}