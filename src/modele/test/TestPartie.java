package modele.test;

import exceptions.FausseDateException;
import exceptions.GagnantNonChoisiException;
import exceptions.MemeEquipeException;
import modele.Categorie;
import modele.CompteArbitre;
import modele.CustomDate;
import modele.Equipe;
import modele.Matche;
import modele.Niveau;
import modele.Partie;
import modele.Pays;
import modele.Saison;
import modele.Tournoi;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPartie {
	private Partie partie;
	private Matche matche;
	private Equipe equipe1;
	private Equipe equipe2;

	@Before
	public void setUp() throws FausseDateException, MemeEquipeException {
		CustomDate debut = new CustomDate(2022, 12, 5);
		CustomDate finTournoi = new CustomDate(2022, 12, 20);
		equipe1 = new Equipe("Faze", Pays.ALGERIE);
		equipe2 = new Equipe("KC", Pays.ALGERIE);
		Saison saison = new Saison(2022);
		Tournoi tournoi = new Tournoi(saison, "RLCS", debut, finTournoi, Niveau.INTERNATIONAL, new CompteArbitre("arbitre0", "1234"));
		matche = new Matche(2, debut, Categorie.DEMI_FINALE, equipe1, equipe2, tournoi);
		partie = new Partie(matche);
	}

	@Test
	public void setNumeroPartie() {
		partie.setNumeroPartie(1);
		assertEquals(1, partie.getNumeroPartie());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setNumeroPartieNegatif() {
		partie.setNumeroPartie(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurPointsNegatifs() {
		partie = new Partie(matche, -10);
	}

	@Test
	public void constructeurAvecNumeroPartie() {
		partie = new Partie(matche, 5);
	}

	@Test
	public void testSetVainqueur1() throws GagnantNonChoisiException {
		partie.setVainqueur(equipe1);
		assertEquals(equipe1, partie.getVainqueur());
	}

	@Test
	public void testSetVainqueur2() throws GagnantNonChoisiException {
		partie.setVainqueur(equipe2);
		assertEquals(equipe2, partie.getVainqueur());
	}

	@Test(expected = GagnantNonChoisiException.class)
	public void testErreurSetVainqueur() throws GagnantNonChoisiException {
		partie.getVainqueur();
	}

	@Test
	public void testGetMatche() {
		assertEquals(matche, partie.getMatche());
	}

}