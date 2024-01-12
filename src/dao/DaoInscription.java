package dao;

import modele.Equipe;
import modele.Inscription;
import modele.Saison;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoInscription implements Dao<Inscription, Object> {

	private Connexion connexion;
	private DaoEquipe daoequipe;
	private DaoSaison daosaison;

	public DaoInscription(Connexion connexion) {
		this.connexion = connexion;
		this.daoequipe = new DaoEquipe(connexion);
		this.daosaison = new DaoSaison(connexion);
	}

	/**
	 * Crée la table d'association Inscription qui lie les équipes et les saisons
	 *
	 * @param connexion
	 * @throws SQLException
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Inscription("
				+ "Nom_Equipe VARCHAR(50),"
				+ "Annee INT,"
				+ "World_Rank INT,"
				+ "PRIMARY KEY(Nom_Equipe, Annee),"
				+ "FOREIGN KEY(Nom_Equipe) REFERENCES Equipe(Nom_Equipe),"
				+ "FOREIGN KEY(Annee) REFERENCES Saison(Annee))";


		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
			System.out.println("Table 'Inscription' créée avec succès");
		}

	}

	/**
	 * supprime la table Inscription
	 *
	 * @param connexion
	 * @return
	 * @throws SQLException
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			System.out.println("Table 'Inscription' supprimée avec succès");
			return deleteTable.execute("drop table Inscription");
		}
	}

	/**
	 * Renvoie toutes les associations d'équipes et de saisons existantes
	 */
	@Override
	public List<Inscription> getAll() throws Exception {
		try (Statement getAll = connexion.getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Inscription");
			List<Inscription> sortie = new ArrayList<>();
			while (resultat.next()) {
				Inscription inscription = new Inscription(
						daosaison.getById(resultat.getInt("Annee")).get(),
						resultat.getInt("World_Rank"),
						daoequipe.getById(resultat.getString("Nom_Equipe")).get());
				sortie.add(inscription);
			}
			return sortie;
		}
	}

	/**
	 * renvoie une association précise
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER), Nom_Equipe (STRING)
	 */
	@Override
	public Optional<Inscription> getById(Object... id) throws Exception {
		try (PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Inscription WHERE Annee = ? AND Nom_Equipe = ?")) {
			getById.setInt(1, (Integer) id[0]);
			getById.setString(2, (String) id[1]);
			ResultSet resultat = getById.executeQuery();
			Inscription inscription = null;
			if (resultat.next()) {
				inscription = new Inscription(
						daosaison.getById(resultat.getInt("Annee")).get(),
						resultat.getInt("World_Rank"),
						daoequipe.getById(resultat.getString("Nom_Equipe")).get());

			}
			return Optional.ofNullable(inscription);
		}
	}

	/**
	 * Ajoute une association à partir d'un objet Inscription
	 */
	@Override
	public boolean add(Inscription value) throws Exception {
		try (PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Inscription(Annee,World_Rank,Nom_Equipe) values (?,?,?)")) {
			add.setInt(1, value.getSaison().getAnnee());
			add.setInt(2, value.getWorldRank());
			add.setString(3, value.getEquipe().getNom());
			return add.execute();
		}
	}

	/**
	 * update de wolrd rank d'une équipe dans une saison à partir d'un objet Inscription
	 */
	@Override
	public boolean update(Inscription value) throws Exception {
		try (PreparedStatement update = connexion.getConnection().prepareStatement(
				"UPDATE Inscription SET "
						+ "World_Rank = ? "
						+ "WHERE Nom_Equipe = ? AND Annee = ?")) {
			update.setInt(1, value.getWorldRank());
			update.setString(2, value.getEquipe().getNom());
			update.setInt(3, value.getSaison().getAnnee());
			return update.execute();
		}
	}

	/**
	 * Supprime une association d'une annee et d'une equipe
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER), Nom_Equipe (STRING)
	 */
	@Override
	public boolean delete(Object... value) throws Exception {
		try (PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Inscription where Annee = ? AND Nom_Equipe = ?")) {
			delete.setInt(1, (Integer) value[0]);
			delete.setString(2, (String) value[1]);
			return delete.execute();
		}
	}

	/**
	 * Renvoie toutes les equipes d'une saison
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER)
	 *
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List<Equipe> getEquipeBySaison(Object... value) throws Exception {
		try (PreparedStatement getEquipeBySaison = connexion.getConnection().prepareStatement(
				"SELECT * FROM Inscription WHERE Annee = ?")) {
			getEquipeBySaison.setInt(1, (Integer) value[0]);
			ResultSet resultat = getEquipeBySaison.executeQuery();
			List<Equipe> sortie = new ArrayList<>();
			while (resultat.next()) {
				sortie.add(daoequipe.getById(resultat.getString("Nom_Equipe")).get());
			}
			return sortie;
		}
	}


	/**
	 * Renvoie tous les saison d'une equipe
	 * Les paramètres sont placés dans cet ordre : Nom_Equipe (STRING)
	 *
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List<Saison> getSaisonByEquipe(Object... value) throws Exception {
		try (PreparedStatement getSaisonByEquipe = connexion.getConnection().prepareStatement(
				"SELECT * FROM Inscription WHERE Nom_Equipe = ?")) {
			getSaisonByEquipe.setString(1, (String) value[0]);
			ResultSet resultat = getSaisonByEquipe.executeQuery();
			List<Saison> sortie = new ArrayList<>();
			while (resultat.next()) {
				sortie.add(daosaison.getById(resultat.getInt("Annee")).get());
			}
			return sortie;
		}
	}

	@Override
	public String visualizeTable() throws Exception {
		String s = "_______________Inscription_______________________" + "\n";
		List<Inscription> l = this.getAll();
		for (Inscription a : l) {
			s += a.toString() + "\n";
		}
		s += "\n\n\n";
		return s;
	}
}