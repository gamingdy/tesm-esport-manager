package dao;

import exceptions.FausseDateException;
import exceptions.IdNotSetException;
import exceptions.MemeEquipeException;
import modele.Arbitre;
import modele.Equipe;
import modele.Saison;
import modele.Tournoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoSaison extends SuperDao implements Dao<Saison, Integer> {



	public DaoSaison(Connexion connexion) {
        super(connexion);

	}

	/**
	 * Crée la table Saison
	 *
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Saison("
				+ "Annee INT,"
				+ "PRIMARY KEY(Annee))";
		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
		}
	}

	/**
	 * Supprime la table saison
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			return deleteTable.execute("drop table Saison");
		}
	}

	/**
	 * Renvoie la liste de toutes les saisons (années)
	 */
	@Override
	public List<Saison> getAll() throws SQLException {
		try (Statement getAll = super.getConnexion().getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Saison");
			List<Saison> sortie = new ArrayList<>();
			while (resultat.next()) {
				sortie.add(new Saison(resultat.getInt(super.getConstants().getAnnee())));
			}
			return sortie;
		}
	}

	/**
	 * Renvoie une année pécise
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER)
	 */
	@Override
	public Optional<Saison> getById(Integer... id) throws SQLException {
		try (PreparedStatement getById = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Saison WHERE Annee = ?")) {
			getById.setInt(1, id[0]);
			ResultSet resultat = getById.executeQuery();
			if (resultat.next()) {
				return Optional.of(new Saison(resultat.getInt(super.getConstants().getAnnee())));
			}
			return Optional.empty();
		}
	}

	/**
	 * Ajoute une saison à la table saison à partir d'un objet saison
	 */
	@Override
	public boolean add(Saison value) throws SQLException {
		try (PreparedStatement add = super.getConnexion().getConnection().prepareStatement(
				"INSERT INTO Saison(Annee) values (?)")) {
			add.setInt(1, value.getAnnee());
			return add.execute();
		}
	}

	/**
	 * Ne pas utiliser
	 */
	@Override
	public boolean update(Saison value) throws SQLException {
		return false;
	}

	/**
	 * supprime une saison de la table saison
	 */
	@Override
	public boolean delete(Integer... value) throws SQLException, FausseDateException, MemeEquipeException,IdNotSetException {
		try (PreparedStatement delete = super.getConnexion().getConnection().prepareStatement(
				"DELETE FROM Saison where Annee = ?")) {
			delete.setInt(1, value[0]);
			Optional<Saison> saison = FactoryDAO.getDaoSaison(super.getConnexion()).getById(value[0]);
			if (saison.isPresent()) {
				List<Tournoi> tournois = FactoryDAO.getDaoTournoi(super.getConnexion()).getTournoiBySaison(saison.get());
				List<Equipe> equipes = FactoryDAO.getDaoInscription(super.getConnexion()).getEquipeBySaison(value[0]);
				List<Arbitre> arbitres = FactoryDAO.getDaoSelection(super.getConnexion()).getArbitreBySaison(saison.get());
				for (Tournoi t : tournois) {
					FactoryDAO.getDaoTournoi(super.getConnexion()).delete(t.getSaison().getAnnee(), t.getNom());
				}
				for (Equipe e : equipes) {
					FactoryDAO.getDaoInscription(super.getConnexion()).delete(value[0], e.getNom());
				}
				for (Arbitre a : arbitres) {
					FactoryDAO.getDaoSelection(super.getConnexion()).delete(a.getNom(), a.getPrenom(), a.getNumeroTelephone(), value[0]);
				}
			}
			return delete.execute();
		}
    }

	/**
	 * Renvoie la dernière saison ajoutée de la table
	 *
	 */
	public Saison getLastSaison() throws SQLException {
		try (PreparedStatement lastInsert = super.getConnexion().getConnection().prepareStatement(
				"SELECT Annee "
						+ "FROM Saison "
						+ "ORDER BY Annee DESC "
						+ "FETCH FIRST 1 ROW ONLY")) {
			ResultSet resultat = lastInsert.executeQuery();
			if (resultat.next()) {
				return new Saison(resultat.getInt(super.getConstants().getAnnee()));
			}
			return null;
		}
	}

	static List<Saison> getSaisons(ResultSet resultat, DaoSaison daoSaison) throws SQLException {
		List<Saison> sortie = new ArrayList<>();
		while (resultat.next()) {
			Optional<Saison> saison = daoSaison.getById(resultat.getInt("Annee"));
			saison.ifPresent(sortie::add);
		}
		return sortie;
	}

	@Override
	public String visualizeTable() throws SQLException {
		StringBuilder s = new StringBuilder("_______________Saison_______________________" + "\n");
		List<Saison> l = this.getAll();
		for (Saison a : l) {
			s.append(a.toString()).append("\n");
		}
		s.append("\n\n\n");
		return s.toString();
	}

}
