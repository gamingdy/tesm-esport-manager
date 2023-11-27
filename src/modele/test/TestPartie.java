package modele.test;

import exceptions.GagnantNonChoisi;
import modele.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPartie {
	private Partie partie;
	private int numeroPartie;
	private Matche matche;
	private CustomDate debut;
	private CustomDate finTournoi;
	private Equipe equipe1;
	private Equipe equipe2;
	private Tournoi tournoi;
	private Saison saison;
	@Before
	public void setUp() throws Exception {
		debut=new CustomDate(2022,12,5);
		finTournoi=new CustomDate(2022,12,20);
		equipe1=new Equipe("Faze",Country.ALGERIE);
		equipe2=new Equipe("KC",Country.ALGERIE);
		saison=new Saison(2022);
		tournoi=new Tournoi(saison,"RLCS",debut,finTournoi,Niveau.INTERNATIONAL,new CompteArbitre("arbitre0","1234"));
		matche=new Matche(2,debut,Categorie.DEMI_FINALE,equipe1,equipe2,tournoi);
		partie=new Partie("RLCS aout",matche);
	}

	@Test
	public void setNumeroPartie() {
		partie.setNumeroPartie(1);
		assertEquals(1,partie.getNumeroPartie());
	}
	@Test(expected = IllegalArgumentException.class)
	public void setNumeroPartieNegatif() {
		partie.setNumeroPartie(-1);
		assertEquals(1,partie.getNumeroPartie());
	}
	@Test(expected = IllegalArgumentException.class)
	public void constructeurPointsNegatifs(){
		partie=new Partie("RLCS aout",matche,-10);
	}
	@Test
	public void constructeurAvecNumeroPartie(){
		partie=new Partie("RLCS aout",matche,5);
	}
	@Test
	public void testSetVainqueur1() throws GagnantNonChoisi {
		partie.setVainqueur(equipe1);
		assertEquals(equipe1,partie.getVainqueur());
	}
	@Test
	public void testSetVainqueur2() throws GagnantNonChoisi{
		partie.setVainqueur(equipe2);
		assertEquals(equipe2,partie.getVainqueur());
	}
	@Test (expected = GagnantNonChoisi.class)
	public void testErreurSetVainqueur() throws  GagnantNonChoisi{
		partie.getVainqueur();
	}
	@Test
	public void testGetMatche(){
		assertEquals(matche,partie.getMatche());
	}

}