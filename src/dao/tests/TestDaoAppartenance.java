package dao.tests;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dao.FactoryDAO;
import modele.Appartenance;
import modele.Equipe;
import modele.Inscription;
import modele.Poule;
import modele.Tournoi;

public class TestDaoAppartenance extends TestDao {

	private List<Equipe> e = new LinkedList<>();
	private List<Poule> p = new LinkedList<>();
	private List<Tournoi> t = new LinkedList<>();
	private List<Appartenance> a = new LinkedList<>();
	
	public TestDaoAppartenance() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setup() throws Exception {
		e = FactoryDAO.getDaoEquipe(getC()).getAll();
		t = FactoryDAO.getDaoTournoi(getC()).getAll();
		Iterator<Tournoi> itt = t.iterator();
		while(itt.hasNext()) {
			this.p = FactoryDAO.getDaoPoule(getC()).getPouleByTournoi(itt.next());
			for(int i = 0 , y = 0 ; i<e.size() ; i++) {
				Appartenance app = new Appartenance(e.get(i),p.get(y));
				a.add(app);
				if (Math.floorMod(i, 8)==7) {
					y++;
				}
			}
		}
		
	}

	@Override
	public void testInsert() throws Exception {
		for(Appartenance ap : a) {
			FactoryDAO.getDaoAppartenance(getC()).add(ap);
		}
		System.out.println(FactoryDAO.getDaoAppartenance(getC()).visualizeTable());
	}

	@Override
	public void testDelete() throws Exception {
		Appartenance app = FactoryDAO.getDaoAppartenance(getC()).getById(a.get(0).getEquipe().getNom(),a.get(0).getPoule().getTournoi().getSaison().getAnnee(),a.get(0).getPoule().getTournoi().getNom(),a.get(0).getPoule().getLibelle()).get();
		FactoryDAO.getDaoAppartenance(getC()).delete(app.getEquipe().getNom(),app.getPoule().getTournoi().getSaison().getAnnee(),app.getPoule().getTournoi().getNom(),app.getPoule().getLibelle());
		System.out.println(FactoryDAO.getDaoAppartenance(getC()).visualizeTable());
	}

	@Override
	public void testUpdate() throws Exception {
		

	}
	
	public void testGetEquipeByPoule() throws Exception {
		List<Equipe> eq = FactoryDAO.getDaoAppartenance(getC()).getEquipeByPoule(a.get(0).getPoule().getTournoi().getNom(),a.get(0).getPoule().getTournoi().getSaison().getAnnee(),a.get(0).getPoule().getLibelle());
		eq.stream().forEach(System.out::println);
	}
	
	public void testGetEquipeByTournoi() throws Exception {
		List<Equipe> eq = FactoryDAO.getDaoAppartenance(getC()).getEquipeByTournoi(a.get(0).getPoule().getTournoi().getNom(),a.get(0).getPoule().getTournoi().getSaison().getAnnee());
		eq.stream().forEach(System.out::println);
	}
	
	public void testGetPouleByEquipe() throws Exception {
		List<Poule> po = FactoryDAO.getDaoAppartenance(getC()).getPouleByEquipe(a.get(0).getEquipe().getNom(),a.get(0).getPoule().getTournoi().getNom(),a.get(0).getPoule().getTournoi().getSaison().getAnnee());
		po.stream().forEach(System.out::println);
	}
	
	public void testGetTournoiByEquipe() throws Exception {
		List<Tournoi> po = FactoryDAO.getDaoAppartenance(getC()).getTournoiByEquipe(a.get(0).getEquipe().getNom());
		po.stream().forEach(System.out::println);
	}
	
	public void testGetTournoiByEquipeForSaison() throws Exception {
		List<Tournoi> to = FactoryDAO.getDaoAppartenance(getC()).getTournoiByEquipeForSaison(new Inscription(a.get(0).getPoule().getTournoi().getSaison(),a.get(0).getEquipe()));
		to.stream().forEach(System.out::println);
	}
	
	public static void main(String[] args) throws Exception {
		TestDaoAppartenance x = new TestDaoAppartenance();
		
		x.setup();
		//x.testInsert();
		//x.testDelete();
		System.out.println("____________________________");
		x.testGetEquipeByPoule();
		System.out.println("____________________________");
		x.testGetEquipeByTournoi();
		System.out.println("____________________________");
		x.testGetPouleByEquipe();
		System.out.println("____________________________");
		x.testGetTournoiByEquipe();
		System.out.println("____________________________");
		x.testGetTournoiByEquipeForSaison();
		
		
	}

}
