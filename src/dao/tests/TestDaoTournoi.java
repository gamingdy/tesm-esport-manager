package dao.tests;

import dao.Connexion;
import dao.FactoryDAO;
import exceptions.FausseDateException;
import init_bd.ESporterManagerInitBDD;
import modele.CompteArbitre;
import modele.CustomDate;
import modele.Niveau;
import modele.Saison;
import modele.Tournoi;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class TestDaoTournoi {

	private Saison saison;
	private Tournoi tournoi1;
	private Tournoi tournoi2;
	private Tournoi tournoi3;
	private Tournoi tournoi4;

	@Before
	public void setUp() throws Exception {
		initDatabase();
		saison= FactoryDAO.getDaoSaison(Connexion.getConnexion()).getById(2024).get();
		initData();
	}

	@Test
	public void testInsert() throws Exception {
		assertTrue(FactoryDAO.getDaoTournoi(Connexion.getConnexion()).add(tournoi1));
		assertTrue(FactoryDAO.getDaoTournoi(Connexion.getConnexion()).add(tournoi2));
		assertTrue(FactoryDAO.getDaoTournoi(Connexion.getConnexion()).add(tournoi3));
		assertTrue(FactoryDAO.getDaoTournoi(Connexion.getConnexion()).add(tournoi4));
	}

	@Test
	public void testDelete() throws Exception {
		assertTrue(FactoryDAO.getDaoTournoi(Connexion.getConnexion()).add(tournoi1));
		assertTrue(FactoryDAO.getDaoTournoi(Connexion.getConnexion()).delete(tournoi1.getSaison().getAnnee(), tournoi1.getNom()));
	}

	@Test
	public void testUpdate() throws Exception {
		assertTrue(FactoryDAO.getDaoTournoi(Connexion.getConnexion()).add(tournoi1));
		Optional<Tournoi> b = FactoryDAO.getDaoTournoi(Connexion.getConnexion()).getById(tournoi1.getSaison().getAnnee(), tournoi1.getNom());
		assertTrue(b.isPresent());
		b.get().setNiveau(Niveau.LOCAL);
		assertTrue(FactoryDAO.getDaoTournoi(Connexion.getConnexion()).update(b.get()));
	}

	@Test
	public void testGetCompteArbitreByTournoi() throws SQLException {
		CompteArbitre c = FactoryDAO.getDaoTournoi(Connexion.getConnexion()).getCompteArbitreByTournoi(2024, "zzzz");
		assertNotNull(c);
	}

	@Test
	public void testGetTournoiActuel() throws SQLException, FausseDateException {
		Optional<Tournoi> t = FactoryDAO.getDaoTournoi(Connexion.getConnexion()).getTournoiActuel();
		assertFalse(t.isPresent());
	}

	@Test
	public void testGetTournoiBySaison() throws SQLException, FausseDateException {
		List<Tournoi> t = FactoryDAO.getDaoTournoi(Connexion.getConnexion()).getTournoiBySaison(saison);
		assertTrue(t.size() > 0);
	}

	@Test
	public void testGetTournoiBetweenDate() throws DateTimeException, Exception {
		List<Tournoi> t = FactoryDAO.getDaoTournoi(Connexion.getConnexion()).getTournoiBetweenDate(new CustomDate(2024, 12, 29), new CustomDate(2024, 12, 30));
		assertTrue(t.size() > 0);
	}

	private void initDatabase() throws Exception {
		// Initialisation de la base de données
		ESporterManagerInitBDD.initDatabase();
	}


	private void initData() throws Exception {
		// Initialisation des données pour les tests
		tournoi1 = new Tournoi(
				FactoryDAO.getDaoSaison(Connexion.getConnexion()).getById(2024).get(),
				"zzzz",
				new CustomDate(2024, 12, 14),
				new CustomDate(2024, 12, 30),
				Niveau.INTERNATIONAL_CLASSE,
				new CompteArbitre("username", "potdemasse"));

		tournoi2 = new Tournoi(
				FactoryDAO.getDaoSaison(Connexion.getConnexion()).getById(2024).get(),
				"wwww",
				new CustomDate(2024, 12, 1),
				new CustomDate(2024, 12, 13),
				Niveau.INTERNATIONAL_CLASSE,
				new CompteArbitre("username", "potdemasse"));
		tournoi3 = new Tournoi(
				FactoryDAO.getDaoSaison(Connexion.getConnexion()).getById(2024).get(),
				"zzzzz",
				new CustomDate(2024, 12, 14),
				new CustomDate(2024, 12, 30),
				Niveau.INTERNATIONAL_CLASSE,
				new CompteArbitre("username", "potdemasse"));

		tournoi4 = new Tournoi(
				FactoryDAO.getDaoSaison(Connexion.getConnexion()).getById(2024).get(),
				"wwwww",
				new CustomDate(2024, 12, 1),
				new CustomDate(2024, 12, 13),
				Niveau.INTERNATIONAL_CLASSE,
				new CompteArbitre("username", "potdemasse"));

	}
}
