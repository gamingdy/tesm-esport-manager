package dao;

import modele.Arbitre;
import modele.Saison;
import modele.Tournoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoArbitre implements Dao<Arbitre, Object> {

	private Connexion connexion;

	public DaoArbitre(Connexion connexion) {
		this.connexion = connexion;
	}

	/**
	 * Crée la table Arbitre
	 *
	 * @param connexion
	 * @throws SQLException
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Arbitre("
				+ "Nom VARCHAR(50),"
				+ "Prenom VARCHAR(50),"
				+ "Telephone VARCHAR(50),"
				+ "PRIMARY KEY(Nom,Prenom,Telephone))";

		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
			System.out.println("Table 'Arbitre' créée avec succès");
		}
	}

	/**
	 * Supprime la table arbitre
	 *
	 * @param connexion
	 * @return
	 * @throws SQLException
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			System.out.println("Table 'Arbitre' supprimée avec succès");
			return deleteTable.execute("drop table Arbitre");
		}
	}

	/**
	 * renvoie tous les arbitres existants
	 */
	@Override
	public List<Arbitre> getAll() throws Exception {
		try (Statement getAll = connexion.getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Arbitre");
			List<Arbitre> sortie = new ArrayList<>();
			while (resultat.next()) {
				Arbitre arbitre = new Arbitre(
						resultat.getString("Nom"),
						resultat.getString("Prenom"),
						resultat.getString("Telephone"));
				sortie.add(arbitre);
			}
			return sortie;
		}
	}

	/**
	 * renvoie un arbitre précis
	 * Les paramètres sont placés dans cet ordre : Nom Arbitre (STRING) , Prenom Arbitre (STRING) , Telephone (STRING)
	 */
	@Override
	public Optional<Arbitre> getById(Object... id) throws Exception {
		try (PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Arbitre WHERE Nom = ? AND Prenom = ? AND Telephone = ? ")) {
			getById.setString(1, (String) id[0]);
			getById.setString(2, (String) id[1]);
			getById.setString(3, (String) id[2]);
			ResultSet resultat = getById.executeQuery();
			Arbitre arbitre = null;
			if (resultat.next()) {
				arbitre = new Arbitre(
						resultat.getString("Nom"),
						resultat.getString("Prenom"),
						resultat.getString("Telephone"));
			}
			return Optional.ofNullable(arbitre);

		}
	}

	/**
	 * Ajoute un arbitre à la table arbitre à partir d'un objet arbitre
	 */
	@Override
	public boolean add(Arbitre value) throws Exception {
		try (PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Arbitre(Nom,Prenom,Telephone) values (?,?,?)")) {
			add.setString(1, value.getNom());
			add.setString(2, value.getPrenom());
			add.setString(3, value.getNumeroTelephone());
			return add.execute();
		}
	}

	/**
	 * Ne pas utiliser
	 */
	@Override
	public boolean update(Arbitre value) throws Exception {
		return false;
	}

	/**
	 * Supprime un arbitre de la table arbitre
	 * Les paramètres sont placés dans cet ordre : Nom Arbitre (STRING) , Prenom Arbitre (STRING) , Telephone (STRING)
	 */
	@Override
	public boolean delete(Object... value) throws Exception {
		try (PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Arbitre WHERE Nom = ? AND Prenom = ? AND Telephone = ? ")) {
			delete.setString(1, (String) value[0]);
			delete.setString(2, (String) value[1]);
			delete.setString(3, (String) value[2]);
			List<Saison> saisons = FactoryDAO.getDaoSelection(connexion).getSaisonByArbitre(value[0], value[1], value[2]);
			List<Tournoi> tournois = FactoryDAO.getDaoArbitrage(connexion).getTournoiByArbitre(value[0], value[1], value[2]);
			for (Saison s : saisons) {
				FactoryDAO.getDaoSelection(connexion).delete(value[0], value[1], value[2], s.getAnnee());
			}
			for (Tournoi t : tournois) {
				FactoryDAO.getDaoArbitrage(connexion).delete(value[0], value[1], value[2], t.getSaison().getAnnee(), t.getNom());
			}
			return delete.execute();
		}
	}

	@Override
	public String visualizeTable() throws Exception {
		String s = "_______________Arbitre_______________________" + "\n";
		List<Arbitre> l = this.getAll();
		for (Arbitre a : l) {
			s += a.toString() + "\n";
		}
		s += "\n\n\n";
		return s;
	}

}
