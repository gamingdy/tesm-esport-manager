package dao.tests;

import dao.FactoryDAO;
import exceptions.EquipeCompleteException;
import exceptions.JoueurException;
import modele.Joueur;

import java.util.List;
import java.util.Optional;

public class TestDaoJoueur extends TestDao {

	private String nom = "Everam";
	private Joueur j1;
	private Joueur j2;
	private Joueur j3;
	private Joueur j4;
	private Joueur j5;


	public TestDaoJoueur() throws EquipeCompleteException, JoueurException, Exception {
		super();

	}

	public void testInsert() throws Exception {
		FactoryDAO.getDaoJoueur(super.getC()).add(j1);
		FactoryDAO.getDaoJoueur(super.getC()).add(j2);
		FactoryDAO.getDaoJoueur(super.getC()).add(j3);
		FactoryDAO.getDaoJoueur(super.getC()).add(j4);
		FactoryDAO.getDaoJoueur(super.getC()).add(j5);
	}

	public void testDelete() throws Exception {
		FactoryDAO.getDaoJoueur(getC()).delete(4);
	}

	public void testUpdate() throws Exception {
		Optional<Joueur> b = FactoryDAO.getDaoJoueur(getC()).getById(3);
		b.get().setPseudo("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		b.get().setEquipe(FactoryDAO.getDaoEquipe(getC()).getById("Bienveillance").get());
		FactoryDAO.getDaoJoueur(getC()).update(b.get());
	}

	public void testGetJoueurParEquipe() throws Exception {
		List<Joueur> bonheur = FactoryDAO.getDaoJoueur(getC()).getJoueurParEquipe("Bonheur");
		List<Joueur> bienveillance = FactoryDAO.getDaoJoueur(getC()).getJoueurParEquipe("Bienveillance");
	}

	public static void main(String[] args) throws Exception {
		TestDaoJoueur x = new TestDaoJoueur();
		x.setup();
		x.testInsert();
		x.testDelete();
		x.testUpdate();
		x.testGetJoueurParEquipe();

	}

	public void setup() throws Exception {
		j1 = new Joueur(super.randomUsername(nom), FactoryDAO.getDaoEquipe(getC()).getById("Bonheur").get());
		j2 = new Joueur(super.randomUsername(nom), FactoryDAO.getDaoEquipe(getC()).getById("Bonheur").get());
		j3 = new Joueur(super.randomUsername(nom), FactoryDAO.getDaoEquipe(getC()).getById("Bonheur").get());
		j4 = new Joueur(super.randomUsername(nom), FactoryDAO.getDaoEquipe(getC()).getById("Bonheur").get());
		j5 = new Joueur(super.randomUsername(nom), FactoryDAO.getDaoEquipe(getC()).getById("Bonheur").get());

	}
}
