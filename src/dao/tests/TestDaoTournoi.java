package dao.tests;

import dao.FactoryDAO;
import exceptions.FausseDateException;
import modele.CompteArbitre;
import modele.CustomDate;
import modele.Niveau;
import modele.Saison;
import modele.Tournoi;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.List;
import java.util.Optional;

public class TestDaoTournoi extends TestDao {

	private Saison saison;
	private Saison saison2;
	private Tournoi tournoi1;
	private Tournoi tournoi2;
	private Tournoi tournoi3;
	private Tournoi tournoi4;

	public TestDaoTournoi() throws Exception {
		super();

	}

	public void testInsert() throws Exception {
		FactoryDAO.getDaoTournoi(super.getC()).add(tournoi1);
		FactoryDAO.getDaoTournoi(super.getC()).add(tournoi2);
		FactoryDAO.getDaoTournoi(super.getC()).add(tournoi3);
		FactoryDAO.getDaoTournoi(super.getC()).add(tournoi4);
	}

	public void testDelete() throws Exception {
		FactoryDAO.getDaoTournoi(getC()).delete(tournoi1.getSaison().getAnnee(), tournoi1.getNom());
		FactoryDAO.getDaoTournoi(super.getC()).add(tournoi1);
	}

	public void testUpdate() throws Exception {
		Optional<Tournoi> b = FactoryDAO.getDaoTournoi(getC()).getById(tournoi1.getSaison().getAnnee(), tournoi1.getNom());
		b.get().setNiveau(Niveau.LOCAL);
		FactoryDAO.getDaoTournoi(getC()).update(b.get());
	}

	public void testGetCompteArbitreByTournoi() throws SQLException {
		CompteArbitre c = FactoryDAO.getDaoTournoi(getC()).getCompteArbitreByTournoi(2023, "zzzz");
	}

	public void testGetTournoiActuel() throws SQLException, FausseDateException {
		Optional<Tournoi> t = FactoryDAO.getDaoTournoi(getC()).getTournoiActuel();
	}

	public void testGetTournoiBySaison() throws SQLException, FausseDateException {
		List<Tournoi> t = FactoryDAO.getDaoTournoi(getC()).getTournoiBySaison(saison);
	}

	public void testGetTournoiBetweenDate() throws DateTimeException, Exception {
		List<Tournoi> t = FactoryDAO.getDaoTournoi(getC()).getTournoiBetweenDate(new CustomDate(2023, 12, 29), new CustomDate(2023, 12, 30));
	}


	public static void main(String[] args) throws Exception {
		TestDaoTournoi x = new TestDaoTournoi();
		x.setup();
		x.testInsert();
		x.testDelete();
		x.testUpdate();
		x.testGetCompteArbitreByTournoi();
		x.testGetTournoiActuel();
		x.testGetTournoiBySaison();
		x.testGetTournoiBetweenDate();

	}

	@Override
	public void setup() throws Exception {
		tournoi1 = new Tournoi(
				FactoryDAO.getDaoSaison(getC()).getById(2023).get(),
				"zzzz",
				new CustomDate(2023, 12, 14),
				new CustomDate(2023, 12, 30),
				Niveau.INTERNATIONAL_CLASSE,
				new CompteArbitre("username", "potdemasse"));

		tournoi2 = new Tournoi(
				FactoryDAO.getDaoSaison(getC()).getById(2023).get(),
				"wwww",
				new CustomDate(2023, 12, 1),
				new CustomDate(2023, 12, 13),
				Niveau.INTERNATIONAL_CLASSE,
				new CompteArbitre("username", "potdemasse"));
		tournoi3 = new Tournoi(
				FactoryDAO.getDaoSaison(getC()).getById(2022).get(),
				"zzzzz",
				new CustomDate(2022, 12, 14),
				new CustomDate(2022, 12, 30),
				Niveau.INTERNATIONAL_CLASSE,
				new CompteArbitre("username", "potdemasse"));

		tournoi4 = new Tournoi(
				FactoryDAO.getDaoSaison(getC()).getById(2022).get(),
				"wwwww",
				new CustomDate(2022, 12, 1),
				new CustomDate(2022, 12, 13),
				Niveau.INTERNATIONAL_CLASSE,
				new CompteArbitre("username", "potdemasse"));

	}
}
