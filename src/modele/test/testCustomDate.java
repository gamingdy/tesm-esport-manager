package modele.test;

import modele.CustomDate;
import org.junit.Before;

import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDateTime;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestCustomDate {
	private CustomDate d1;
	private CustomDate d2;

	private CustomDate d3;

	@Before
	public void setUp() {

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
		assertTrue(d1.toSQL() instanceof Timestamp);
	}

	@Test
	public void setgetAnnee() {
		d1.setAnnee(2023);
		assertEquals(2023, d1.getAnnee());
	}

	@Test
	public void setgetMois() throws DateTimeException {
		d1.setMois(12);
		assertEquals(12, d1.getMois());
	}

	@Test
	public void setgetJour() throws DateTimeException {
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
		LocalDateTime d = LocalDateTime.of(2022, 10, 11, 0, 0);
		Timestamp date_sql = Timestamp.valueOf(d);
		CustomDate c = new CustomDate(date_sql);
		assertEquals(2022, c.getAnnee());
	}

	@Test
	public void testsetGetHeure() throws DateTimeException {
		d1.setHeure(20);
		assertEquals(20, d1.getHeure());
	}

	@Test(expected = DateTimeException.class)
	public void testSetHeureErreur() throws DateTimeException {
		d1.setHeure(25);
	}

	@Test
	public void testSetGetMin() throws DateTimeException {
		d1.setMinute(20);
		assertEquals(20, d1.getMinute());
	}

	@Test(expected = DateTimeException.class)
	public void testSetMinErreur() throws DateTimeException {
		d1.setMinute(70);
	}

	@Test(expected = DateTimeException.class)
	public void testSetMoisErreur() throws DateTimeException {
		d1.setMois(70);
	}

	@Test(expected = DateTimeException.class)
	public void testSetJourErreurNegatif() throws DateTimeException {
		d1.setJour(-1);
	}

	@Test(expected = DateTimeException.class)
	public void testSetJourErreurSup30() throws DateTimeException {
		d1.setJour(40);
	}


	@Test(expected = DateTimeException.class)
	public void testSetJourErreur30() throws DateTimeException {
		d1.setMois(4);
		d1.setJour(31);
	}

	@Test(expected = DateTimeException.class)
	public void testSetJourErreurFevr() throws DateTimeException {
		d1.setMois(2);
		d1.setJour(31);
	}

	@Test(expected = DateTimeException.class)
	public void testSetJourErreurfevr() throws DateTimeException {
		d1.setAnnee(2023);
		d1.setMois(2);
		d1.setJour(29);
	}

	@Test
	public void testConstructeurDateHeureMin() throws DateTimeException {
		CustomDate date = new CustomDate(2022, 10, 1, 2, 1);
	}

	@Test(expected = DateTimeException.class)
	public void testConstructeurDateHeureMinErreur() throws DateTimeException {
		CustomDate date = new CustomDate(2022, 10, 1, 25, 1);
	}

	@Test(expected = DateTimeException.class)
	public void testConstructeurDateHeureMinErreurMin() throws DateTimeException {
		CustomDate date = new CustomDate(2022, 10, 1, 20, -2);
	}

	@Test
	public void testSetJourErreurAnneeBisextile() throws DateTimeException {
		d1.setAnnee(2024);
		d1.setMois(2);
		d1.setJour(29);
	}


}
