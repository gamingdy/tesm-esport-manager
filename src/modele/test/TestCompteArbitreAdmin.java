package modele.test;

import exceptions.FausseDateException;
import modele.CompteAdmin;
import modele.CompteArbitre;
import modele.CustomDate;
import modele.Niveau;
import modele.Saison;
import modele.Tournoi;
import org.junit.Before;
import org.junit.Test;

public class TestCompteArbitreAdmin {

	@Before
	public void setUp() throws FausseDateException {
		CustomDate d1 = new CustomDate(2022, 11, 13);
		CustomDate d2 = new CustomDate(2022, 10, 10);
		Saison s = new Saison(2022);
		CompteArbitre compteArbitre = new CompteArbitre("arbitre1", "123");
		new Tournoi(s, "RLCS", d1, d2, Niveau.INTERNATIONAL, compteArbitre);

	}


	@Test
	public void testCompteAdmin() {
		new CompteAdmin("admin", "123fd4");
	}
}