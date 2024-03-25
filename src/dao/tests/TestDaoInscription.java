package dao.tests;

import dao.FactoryDAO;
import modele.Equipe;
import modele.Inscription;
import modele.Saison;

import java.util.LinkedList;
import java.util.List;

public class TestDaoInscription extends TestDao {

	private List<Equipe> e = new LinkedList<>();
	private List<Saison> s = new LinkedList<>();
	private List<Inscription> inscr = new LinkedList<>();

	@Override
	public void setup() throws Exception {
		e = FactoryDAO.getDaoEquipe(getC()).getAll();
		s = FactoryDAO.getDaoSaison(getC()).getAll();

		for (Saison sai : s) {
			for (int i = 0; i < 4; i++) {
				Inscription is = new Inscription(sai, e.get(i));
				is.setWorldRank(1000);
				inscr.add(is);
			}
		}

	}

	@Override
	public void testInsert() throws Exception {
		for (Inscription i : inscr) {
			FactoryDAO.getDaoInscription(getC()).add(i);
		}

	}

	@Override
	public void testDelete() throws Exception {
		FactoryDAO.getDaoInscription(getC()).delete(inscr.get(0).getSaison().getAnnee(), inscr.get(0).getEquipe().getNom());

	}

	@Override
	public void testUpdate() throws Exception {
		inscr.get(1).setWorldRank(5);
		FactoryDAO.getDaoInscription(getC()).update(inscr.get(1));
	}

	public void testGetEquipeBySaison() throws Exception {
		List<Equipe> eq = FactoryDAO.getDaoInscription(getC()).getEquipeBySaison(inscr.get(1).getSaison().getAnnee());
	}

	public void testGetSaisonByEquipe() throws Exception {
		List<Saison> sais = FactoryDAO.getDaoInscription(getC()).getSaisonByEquipe(inscr.get(1).getEquipe().getNom());
	}

	public static void main(String[] args) throws Exception {
		TestDaoInscription x = new TestDaoInscription();

		x.setup();
		x.testInsert();
		x.testDelete();
		x.testUpdate();
		x.testGetEquipeBySaison();
		x.testGetSaisonByEquipe();


	}

}
