package modele.test;

import exceptions.ErreurDate;
import exceptions.FausseDate;
import org.junit.Before;
import org.junit.Test;
import modele.*;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class testMatche {
	Matche m;
	Equipe e1 = new Equipe("Faze", Country.PALAU);
	Equipe e2 = new Equipe("KC", Country.ALGERIA);
	CustomDate d1;
	CustomDate d2;

	Saison s = new Saison(2022);
	Tournoi tournoi;

	{
		try {
			d2 = new CustomDate(2022, 11, 13);
			d1 = new CustomDate(2022, 10, 10);
			;

			tournoi = new Tournoi(s, "RLCS", d1, d2, Niveau.INTERNATIONAL);
		} catch (FausseDate e) {
			throw new RuntimeException(e);
		} catch (ErreurDate e) {
			throw new RuntimeException(e);
		}
	}


	@Before
	public void setUp() throws Exception {

		m = new Matche(1, 1, d1, Categorie.DEMI_FINALE, e1, e2, tournoi);
	}

	@Test
	public void getId() {
		System.out.println(d1.toString());
		assertEquals(1, m.getId());
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