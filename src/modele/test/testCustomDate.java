package modele.test;

import exceptions.ErreurDate;
import exceptions.FausseDate;
import org.junit.Before;
import org.junit.Test;
import modele.*;

import java.sql.Date;

import static org.junit.Assert.*;

public class testCustomDate {
	private CustomDate d1;
	private CustomDate d2;

	private CustomDate d3;

	@Before
	public void setUp() throws Exception {

		d1 = new CustomDate(2022, 10, 11);
		d2 = new CustomDate(2022, 10, 11);
		d3 = new CustomDate(2022, 10, 13);
	}

	@Test
	public void estAvantDateSimilaire() {
		assertFalse(d1.estAvant(d2));
	}

	@Test
	public void estAvant() {
		assertTrue(d1.estAvant(d3));
	}

	@Test
	public void estApres() {
		assertTrue(d3.estApres(d1));
	}

	@Test
	public void estApresFalse() {
		assertFalse(d1.estApres(d3));
	}

	@Test
	public void testtoSql() {
		assertTrue(d1.toSQL() instanceof Date);
	}

	@Test
	public void setgetAnnee() {
		d1.setAnnee(2023);
		assertEquals(2023, d1.getAnnee());
	}

	@Test
	public void setgetMois() throws ErreurDate {
		d1.setMois(12);
		assertEquals(12, d1.getMois());
	}

	@Test
	public void setgetJour() throws ErreurDate {
		d1.setJour(20);
		assertEquals(20, d1.getJour());
	}

	@Test
	public void testEquals() {
		assertEquals(d1, d2);
	}

	@Test
	public void testEqualsFaux() {
		assertNotEquals(d1, d3);
	}

	@Test
	public void testEqualsNull() {
		assertNotEquals(d1, null);
	}

	@Test
	public void testEqualsNotSameClass() {
		assertNotEquals(d1, 1);
	}

	@Test
	public void testCompareto() {
		assertEquals(0, d1.compareTo(d2));
	}

	@Test
	public void testConstructeurDate() {
		Date date_sql = new Date(2022 - 1901, 12, 1);
		CustomDate c = new CustomDate(date_sql);
		assertEquals(2022, c.getAnnee());
	}

	@Test
	public void testsetGetHeure() throws ErreurDate {
		d1.setHeure(20);
		assertEquals(20, d1.getHeure());
	}

	@Test(expected = ErreurDate.class)
	public void testsetHeureErreur() throws ErreurDate {
		d1.setHeure(25);
	}

	@Test
	public void testsetGetMin() throws ErreurDate {
		d1.setMinute(20);
		assertEquals(20, d1.getMinute());
	}

	@Test(expected = ErreurDate.class)
	public void testsetMinErreur() throws ErreurDate {
		d1.setMinute(70);
	}

	@Test(expected = ErreurDate.class)
	public void testsetMoisErreur() throws ErreurDate {
		d1.setMois(70);
	}

	@Test(expected = ErreurDate.class)
	public void testsetJourErreurNegatif() throws ErreurDate {
		d1.setJour(-1);
	}

	@Test(expected = ErreurDate.class)
	public void testsetJourErreurSup30() throws ErreurDate {
		d1.setJour(40);
	}


	@Test(expected = ErreurDate.class)
	public void testsetJourErreur30() throws ErreurDate {
		d1.setMois(4);
		d1.setJour(31);
	}

	@Test(expected = ErreurDate.class)
	public void testsetJourErreurFevr() throws ErreurDate {
		d1.setMois(2);
		d1.setJour(31);
	}

	@Test(expected = ErreurDate.class)
	public void testsetJourErreurfevr() throws ErreurDate {
		d1.setAnnee(2023);
		d1.setMois(2);
		d1.setJour(29);
	}

	@Test
	public void testConstructeurDateHeureMin() throws ErreurDate {
		CustomDate date = new CustomDate(2022, 10, 1, 2, 1);
	}

	@Test(expected = ErreurDate.class)
	public void testConstructeurDateHeureMinErreur() throws ErreurDate {
		CustomDate date = new CustomDate(2022, 10, 1, 25, 1);
	}

	@Test(expected = ErreurDate.class)
	public void testConstructeurDateHeureMinErreurMin() throws ErreurDate {
		CustomDate date = new CustomDate(2022, 10, 1, 20, -2);
	}

	@Test
	public void testsetJourErreurAnneeBisextile() throws ErreurDate {
		d1.setAnnee(2024);
		d1.setMois(2);
		d1.setJour(29);
	}


}
