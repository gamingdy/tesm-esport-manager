package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modele.Saison;

public class DaoSaison implements Dao<Saison, Integer> {

	private Connexion connexion;

	public DaoSaison(Connexion connexion) {
		this.connexion = connexion;
	}

	/**
	 * Crée la table Saison
	 *
	 * @param connexion
	 * @throws SQLException
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Saison("
				+ "Annee INT,"
				+ "PRIMARY KEY(Annee))";
		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
			System.out.println("Table 'Saison' créée avec succès");
		}
	}

	/**
	 * Supprime la table saison
	 *
	 * @param connexion
	 * @return
	 * @throws SQLException
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			System.out.println("Table 'Saison' supprimée avec succès");
			return deleteTable.execute("drop table Saison");
		}
	}

	/**
	 * Renvoie la liste de toutes les saisons (années)
	 */
	@Override
	public List<Saison> getAll() throws Exception {
		try (Statement getAll = connexion.getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Saison");
			List<Saison> sortie = new ArrayList<>();
			while (resultat.next()) {
				Saison saison = new Saison(
						resultat.getInt("Annee"));
				sortie.add(saison);
			}
			return sortie;
		}
	}

	/**
	 * Renvoie une année pécise
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER)
	 */
	@Override
	public Saison getById(Integer... id) throws Exception {
		try (PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Saison WHERE Annee = ?")) {
			getById.setInt(1, id[0]);
			ResultSet resultat = getById.executeQuery();
			if (resultat.next()) {
				Saison saison = new Saison(
						resultat.getInt("Annee"));
				return saison;
			}
			throw new Exception("Saison non trouvée");
		}
	}

	/**
	 * Ajoute une saison à la table saison à partir d'un objet saison
	 */
	@Override
	public boolean add(Saison value) throws Exception {
		try (PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Saison(Annee) values (?)")) {
			add.setInt(1, value.getAnnee());
			return add.execute();
		}
	}

	/**
	 * ne pas utiliser
	 */
	@Override
	public boolean update(Saison value) throws Exception {
		return false;
	}

	/**
	 * supprime une saison de la table saison
	 */
	@Override
	public boolean delete(Integer... value) throws Exception {
		try (PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Saison where Annee = ?")) {
			delete.setInt(1, value[0]);
			return delete.execute();
		}
	}

	/**
	 * Renvoie la dernière saison ajoutée de la table
	 *
	 * @return
	 * @throws SQLException
	 */
	public Saison getLastSaison() throws SQLException {
		try (PreparedStatement lastInsert = connexion.getConnection().prepareStatement(
				"SELECT Annee "
						+ "FROM Saison "
						+ "ORDER BY Annee DESC "
						+ "FETCH FIRST 1 ROW ONLY")) {
			ResultSet resultat = lastInsert.executeQuery();
			Saison saison = null;
			if (resultat.next()) {
				saison = new Saison(resultat.getInt("Annee"));
			}
			return saison;
		}
	}

}
