package modele.test;

import static org.junit.Assert.*;

import exceptions.PointsNegatifs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.EquipeComplete;
import exceptions.EquipeVide;
import exceptions.JoueurNonPresent;
import modele.Equipe;
import modele.Joueur;

public class testJoueur {
	Joueur j;

	@Before
	public void setUp() {
		j = new Joueur(1, "Cricri");
	}

	@Test
	public void testSetPseudo() {
		j.setPseudo("Cricri2");
		assertEquals("Cricri2", j.getPseudo());
	}
}
