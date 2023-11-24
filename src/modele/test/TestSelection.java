package modele.test;

import modele.Selection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSelection {
	private Selection selection;
	private int idArbitre;
	private int annee;
	@Before
	public void setUp() throws Exception {
		idArbitre=0;
		annee=2022;
		selection=new Selection(idArbitre,annee);
	}

	@Test
	public void getIdArbitre() {
		assertEquals(0,selection.getIdArbitre());
	}

	@Test
	public void setIdArbitre() {
		selection.setIdArbitre(5);
		assertEquals(5,selection.getIdArbitre());
	}

	@Test
	public void getAnnee() {
		assertEquals(annee,selection.getAnnee());
	}

	@Test
	public void setAnnee() {
		selection.setAnnee(2023);
		assertEquals(2023,selection.getAnnee());
	}
}