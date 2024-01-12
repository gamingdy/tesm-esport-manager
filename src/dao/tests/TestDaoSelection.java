package dao.tests;

import java.util.LinkedList;
import java.util.List;

import dao.FactoryDAO;
import modele.Arbitre;
import modele.Saison;
import modele.Selection;

public class TestDaoSelection extends TestDao {

	private List<Saison> s = new LinkedList<>();
	private List<Arbitre> a = new LinkedList<>();
	private List<Selection> sel = new LinkedList<>();
	
	@Override
	public void setup() throws Exception {
		a = FactoryDAO.getDaoAbritre(getC()).getAll();
		s = FactoryDAO.getDaoSaison(getC()).getAll();
		for (Saison sa : s) {
			for(Arbitre ab : a) {
				sel.add(new Selection(ab,sa));
			}
		}
	}

	@Override
	public void testInsert() throws Exception {
		for(Selection select : sel) {
			FactoryDAO.getDaoSelection(getC()).add(select);
		}
		System.out.println(FactoryDAO.getDaoSelection(getC()).visualizeTable());

	}

	@Override
	public void testDelete() throws Exception {
		FactoryDAO.getDaoSelection(getC()).delete(sel.get(0).getArbitre().getNom(),sel.get(0).getArbitre().getPrenom(),sel.get(0).getArbitre().getNumeroTelephone(),sel.get(0).getSaison().getAnnee());
		System.out.println(FactoryDAO.getDaoSelection(getC()).visualizeTable());

	}

	@Override
	public void testUpdate() throws Exception {
		// TODO Auto-generated method stub

	}
	
	public void testGetArbitreBySaison() throws Exception {
		List<Arbitre> arr = FactoryDAO.getDaoSelection(getC()).getArbitreBySaison(sel.get(0).getSaison().getAnnee());
		System.out.println("____________________________");
		arr.stream().forEach(System.out::println);
	}
	
	public void testGetSaisonByArbitre() throws Exception {
		List<Saison> saisons = FactoryDAO.getDaoSelection(getC()).getSaisonByArbitre(sel.get(0).getArbitre().getNom(),sel.get(0).getArbitre().getPrenom(),sel.get(0).getArbitre().getNumeroTelephone());
		System.out.println("____________________________");
		saisons.stream().forEach(System.out::println);
	}

	public static void main(String[] args) throws Exception {
		TestDaoSelection x = new TestDaoSelection();
		
		x.setup();
		x.testInsert();
		x.testDelete();
		x.testGetArbitreBySaison();
		x.testGetSaisonByArbitre();

	}

}
