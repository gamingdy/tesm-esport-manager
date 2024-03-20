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

import static dao.DaoEquipe.getEquipes;
import static dao.DaoSaison.getSaisons;

public class DaoInscription extends SuperDao implements Dao<Inscription, Object> {


	private final DaoEquipe daoequipe;
	private final DaoSaison daosaison;

	public DaoInscription(Connexion connexion) {
        super(connexion);
		this.daoequipe = new DaoEquipe(connexion);
		this.daosaison = new DaoSaison(connexion);
	}

	/**
	 * Crée la table d'association Inscription qui lie les équipes et les saisons
	 *
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
		}

	}

	/**
	 * supprime la table Inscription
	 *
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			return deleteTable.execute("drop table Inscription");
		}
	}

	/**
	 * Renvoie toutes les associations d'équipes et de saisons existantes
	 */
	@Override
	public List<Inscription> getAll() throws SQLException {
		try (Statement getAll = super.getConnexion().getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Inscription");
			List<Inscription> sortie = new ArrayList<>();
			while (resultat.next()) {
				Optional<Saison> saison = daosaison.getById(resultat.getInt(super.getConstants().getAnnee()));
				Optional<Equipe> equipe = daoequipe.getById(resultat.getString(super.getConstants().getNomEquipe()));
				if (saison.isPresent() && equipe.isPresent()) {
					sortie.add(new Inscription(saison.get(), resultat.getInt("World_Rank"), equipe.get()));
				}
			}
			return sortie;
		}
	}

	/**
	 * Renvoie une association précise
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER), Nom_Equipe (STRING)
	 */
	@Override
	public Optional<Inscription> getById(Object... id) throws SQLException {
		try (PreparedStatement getById = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Inscription WHERE Annee = ? AND Nom_Equipe = ?")) {
			getById.setInt(1, (Integer) id[0]);
			getById.setString(2, (String) id[1]);
			ResultSet resultat = getById.executeQuery();
			if (resultat.next()) {
				Optional<Saison> saison = daosaison.getById(resultat.getInt(super.getConstants().getAnnee()));
				Optional<Equipe> equipe = daoequipe.getById(resultat.getString(super.getConstants().getNomEquipe()));
				if (saison.isPresent() && equipe.isPresent()) {
					return Optional.of(new Inscription(saison.get(), resultat.getInt(super.getConstants().getWorldRank()), equipe.get()));
				}
				return Optional.empty();

			}
			return Optional.empty();
		}
	}

	/**
	 * Ajoute une association à partir d'un objet Inscription
	 */
	@Override
	public boolean add(Inscription value) throws SQLException {
		try (PreparedStatement add = super.getConnexion().getConnection().prepareStatement(
				"INSERT INTO Inscription(Annee,World_Rank,Nom_Equipe) values (?,?,?)")) {
			add.setInt(1, value.getSaison().getAnnee());
			add.setInt(2, value.getWorldRank());
			add.setString(3, value.getEquipe().getNom());
			return add.execute();
		}
	}

	/**
	 * Update de wolrd rank d'une équipe dans une saison à partir d'un objet Inscription
	 */
	@Override
	public boolean update(Inscription value) throws SQLException {
		try (PreparedStatement update = super.getConnexion().getConnection().prepareStatement(
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
	public boolean delete(Object... value) throws SQLException {
		try (PreparedStatement delete = super.getConnexion().getConnection().prepareStatement(
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
	 */
	public List<Equipe> getEquipeBySaison(Object... value) throws SQLException {
		try (PreparedStatement getEquipeBySaison = super.getConnexion().getConnection().prepareStatement(
				"SELECT * FROM Inscription WHERE Annee = ?")) {
			getEquipeBySaison.setInt(1, (Integer) value[0]);
			return getEquipes(getEquipeBySaison, daoequipe);
		}
	}


	/**
	 * Renvoie tous les saisons d'une equipe
	 * Les paramètres sont placés dans cet ordre : Nom_Equipe (STRING)
	 */
	public List<Saison> getSaisonByEquipe(Object... value) throws SQLException {
		try (PreparedStatement getSaisonByEquipe = super.getConnexion().getConnection().prepareStatement(
				"SELECT * FROM Inscription WHERE Nom_Equipe = ?")) {
			getSaisonByEquipe.setString(1, (String) value[0]);
			ResultSet resultat = getSaisonByEquipe.executeQuery();
			return getSaisons(resultat,daosaison);
		}
	}

	@Override
	public String visualizeTable() throws SQLException {
		StringBuilder s = new StringBuilder("_______________Inscription_______________________" + "\n");
		List<Inscription> l = this.getAll();
		for (Inscription a : l) {
			s.append(a.toString()).append("\n");
		}
		s.append("\n\n\n");
		return s.toString();
	}
}