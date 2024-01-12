package modele.test;

import modele.Arbitrage;
import modele.Arbitre;
import modele.CompteArbitre;
import modele.CustomDate;
import modele.Niveau;
import modele.Saison;
import modele.Tournoi;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestArbitrage {
	private Tournoi tournoi;
	private CustomDate debut;
	private CustomDate fin;
	private Arbitre arbitre;
	private Arbitrage arbitrage;

	@Before
	public void setUp() throws Exception {
		debut = new CustomDate(2022, 10, 10);
		fin = new CustomDate(2022, 10, 22);
		tournoi = new Tournoi(new Saison(2022), "RLCS", debut, fin, Niveau.INTERNATIONAL, new CompteArbitre("1234", "1234"));
		arbitre = new Arbitre("Armand", "Simon","123456790");
		arbitrage = new Arbitrage(arbitre, tournoi);
	}

	@Test
	public void setGetArbitre() {
		assertEquals(arbitre, arbitrage.getArbitre());
	}

	@Test
	public void setGetTournoi() {
		assertEquals(tournoi, arbitrage.getTournoi());
	}
}