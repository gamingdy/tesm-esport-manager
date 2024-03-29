package dao.tests;

import dao.FactoryDAO;
import modele.Categorie;
import modele.CustomDate;
import modele.Equipe;
import modele.Matche;
import modele.Tournoi;

import java.util.LinkedList;
import java.util.List;

public class TestDaoMatche extends TestDao {

	private Tournoi t;
	private Tournoi t2;
	private List<Equipe> allEquipe = FactoryDAO.getDaoEquipe(getC()).getAll();
	private List<Matche> matche = new LinkedList<>();

	public TestDaoMatche() throws Exception {
		super();
	}

	public void testInsert() throws Exception {
		for (int i = 0; i < matche.size(); i++) {
			FactoryDAO.getDaoMatche(getC()).add(matche.get(i));
		}

	}

	public void testDelete() throws Exception {
		FactoryDAO.getDaoMatche(getC()).delete(FactoryDAO.getDaoMatche(getC()).getLastId());

	}


	public void testUpdate() throws Exception {
		Matche match = FactoryDAO.getDaoMatche(getC()).getById(FactoryDAO.getDaoMatche(getC()).getLastId()).get();
		match.setCategorie(Categorie.PETITE_FINALE);
		FactoryDAO.getDaoMatche(getC()).update(match);
	}

	public void testGetMatchByTournoi() throws Exception {
		List<Matche> m = FactoryDAO.getDaoMatche(getC()).getMatchByTournoi(2023, "zzzz");
	}

	public void testGetMatchByTournoiFromCategorie() throws Exception {
		List<Matche> m = FactoryDAO.getDaoMatche(getC()).getMatchesByTournoiFromCategorie(FactoryDAO.getDaoTournoi(getC()).getById(2023, "zzzz").get(), Categorie.PETITE_FINALE);
	}

	public void testGetMatchByEquipe() throws Exception {
		List<Equipe> e = FactoryDAO.getDaoEquipe(getC()).getAll();
		List<Matche> m = new LinkedList<>();
		for (Equipe eq : e) {
			FactoryDAO.getDaoMatche(getC()).getMatchByEquipe(eq).stream().forEach(x -> m.add(x));
		}
	}

	public void testGetMatchBySaison() throws Exception {
		List<Matche> m = FactoryDAO.getDaoMatche(getC()).getMatchBySaison(t.getSaison());
	}

	public void testGetMatchByEquipeForTournoi() throws Exception {
		List<Matche> m = FactoryDAO.getDaoMatche(getC()).getMatchByEquipeForTournoi(FactoryDAO.getDaoEquipe(getC()).getById("Bonheur").get(), t2);
	}


	public static void main(String[] args) throws Exception {
		TestDaoMatche x = new TestDaoMatche();
		x.setup();
		//x.testInsert();
		//x.testDelete();
		//x.testUpdate();
		x.testGetMatchByTournoi();
		x.testGetMatchByTournoiFromCategorie();
		x.testGetMatchBySaison();
		x.testGetMatchByEquipeForTournoi();
	}

	public void setup() throws Exception {
		t = FactoryDAO.getDaoTournoi(getC()).getById(2023, "wwww").get();
		t2 = FactoryDAO.getDaoTournoi(getC()).getById(2023, "zzzz").get();
		Matche match = null;
		for (int i = 0; i < allEquipe.size(); i += 2) {

			match = new Matche(
					3,
					new CustomDate(2023, 12, 12, Math.floorMod(i, 23), 0),
					Categorie.POULE,
					allEquipe.get(i),
					allEquipe.get(i + 1),
					t);
			match.setId(i / 2);

			match = new Matche(
					3,
					new CustomDate(2023, 12, 15, Math.floorMod(i, 23), 0),
					Categorie.POULE,
					allEquipe.get(i),
					allEquipe.get(i + 1),
					t2);
			match.setId(i / 2);


			matche.add(match);
		}

	}


}
