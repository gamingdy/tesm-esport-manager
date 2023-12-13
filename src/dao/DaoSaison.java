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

	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Saison("
				+ "Annee INT,"
				+ "PRIMARY KEY(Annee))";
		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
			System.out.println("Table 'Saison' créée avec succès");
		}
	}

	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			System.out.println("Table 'Saison' créée avec succès");
			return deleteTable.execute("drop table Saison");
		}
	}

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

	@Override
	public boolean add(Saison value) throws Exception {
		try {
			System.out.println("patate");
			System.out.println(connexion);
			Connection add = connexion.getConnection();
			System.out.println("poti");
			PreparedStatement t = add.prepareStatement("INSERT INTO Saison(Annee) values (?)");

			t.setInt(1, value.getAnnee());
			return t.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Saison value) throws Exception {
		return false;
	}

	@Override
	public boolean delete(Integer... value) throws Exception {
		try (PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Saison where Annee = ?")) {
			delete.setInt(1, value[0]);
			return delete.execute();
		}
	}

}
