package modele.test;

import exceptions.ErreurDate;
import exceptions.FausseDate;
import modele.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class testCompteArbitreAdmin {
	private CompteArbitre compteArbitre;
	private Arbitre a;
	private Tournoi tournoi;
	private Saison s;

	@Before
	public void setUp() throws Exception {
		a = new Arbitre("Michel", "Jean");
		CustomDate d1 = new CustomDate(2022, 11, 13);
		CustomDate d2 = new CustomDate(2022, 10, 10);
		s = new Saison(2022);
		compteArbitre = new CompteArbitre("arbitre1", "123");
		tournoi = new Tournoi(s, "RLCS", d1, d2, Niveau.INTERNATIONAL, compteArbitre);

	}


	@Test
	public void testCompteAdmin() {
		CompteAdmin ca = new CompteAdmin("admin", "123fd4");
	}
}