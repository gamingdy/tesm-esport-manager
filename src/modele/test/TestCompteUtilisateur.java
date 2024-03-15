package modele.test;

import modele.CompteUtilisateur;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCompteUtilisateur {
	private CompteUtilisateur compte;

	@Before
	public void setUp() {
		compte = new CompteUtilisateur("admin", "1234");
	}

	@Test
	public void getUsername() {
		assertEquals("admin", compte.getUsername());
	}

	@Test
	public void getMdp() {
		assertEquals("1234", compte.getHashMdp());
	}
}