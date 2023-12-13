package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modele.Poule;

public class DaoPoule implements Dao<Poule, Object> {

	private Connexion connexion;
	private DaoTournoi daotournoi;

	public DaoPoule(Connexion connexion) {
		this.connexion = connexion;
		this.daotournoi = new DaoTournoi(connexion);

	}

	/**
	 * Crée la table Poule
	 *
	 * @param connexion
	 * @throws SQLException
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Poule ("
				+ "Annee INT,"
				+ "Nom_Tournoi VARCHAR(50),"
				+ "Libelle VARCHAR(1),"
				+ "PRIMARY KEY(Annee, Nom_Tournoi, Libelle),"
				+ "FOREIGN KEY(Annee, Nom_Tournoi) REFERENCES Tournoi(Annee, Nom_Tournoi))";

		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
			System.out.println("Table 'Poule' créée avec succès");
		}
	}

	/**
	 * Supprime la table Poule
	 *
	 * @param connexion
	 * @return
	 * @throws SQLException
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			System.out.println("Table 'Poule' supprimée avec succès");
			return deleteTable.execute("drop table Poule");
		}
	}

	/**
	 * Renvoie toutes les poules existantes
	 */
	@Override
	public List<Poule> getAll() throws Exception {
		try (Statement getAll = connexion.getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Poule");
			List<Poule> sortie = new ArrayList<>();
			while (resultat.next()) {
				Poule poule = new Poule(
						daotournoi.getById(resultat.getString("Nom_tournoi"), resultat.getInt("Annee")).get(),
						resultat.getString("Libelle").charAt(0));
				sortie.add(poule);
			}
			return sortie;
		}
	}

	/**
	 * Renvoie une poule précise
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER), Nom_tournoi (STRING), Libelle_poule (STRING)
	 */
	@Override
	public Optional<Poule> getById(Object... value) throws Exception {
		try (PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Poule WHERE Annee = ? AND Nom_Tournoi = ? AND Libelle = ?")) {
			getById.setInt(1, (Integer) value[0]);
			getById.setString(2, (String) value[1]);
			getById.setString(3, (String) value[2]);
			ResultSet resultat = getById.executeQuery();
			Poule poule = null;
			if (resultat.next()) {
				poule = new Poule(
						daotournoi.getById(resultat.getString("Nom_tournoi"), resultat.getInt("Annee")).get(),
						resultat.getString("Libelle").charAt(0));
				
			}
			return Optional.ofNullable(poule);
		}
	}

	/**
	 * Ajoute une poule à la table poule à partir d'un objet poule
	 * 
	 */
	@Override
	public boolean add(Poule value) throws Exception {
		try (PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Poule(Annee,Nom_tournoi,Libelle) values (?,?,?)")) {

			add.setInt(1, value.getTournoi().getSaison().getAnnee());
			add.setString(2, value.getTournoi().getNom());
			add.setString(3, value.getLibelle().toString());
			return add.execute();
		}
	}

	/**
	 * ne pas utiliser
	 */
	@Override
	public boolean update(Poule value) throws Exception {
		return false;
	}

	/**
	 * Supprime une poule
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER), Nom_tournoi (STRING), Libelle (STRING)
	 * 
	 */
	@Override
	public boolean delete(Object... value) throws Exception {
		try (PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Poule where Annee = ? AND Nom_tournoi = ? AND Libelle = ?")) {
			delete.setInt(1, (Integer) value[0]);
			delete.setString(2, (String) value[1]);
			delete.setString(3, (String) value[2]);
			return delete.execute();
		}
	}
}

