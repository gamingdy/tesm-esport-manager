package dao;


import exceptions.FausseDateException;
import exceptions.IdNotSetException;
import exceptions.MemeEquipeException;
import modele.Niveau;
import modele.Tournoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoNiveau extends SuperDao implements Dao<Niveau, String> {

	public DaoNiveau(Connexion connexion) {
        super(connexion);
	}

	/**
	 * Crée la table niveau
	 *
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Niveau("
				+ "Libelle_Niveau VARCHAR(50),"
				+ "Coefficient DECIMAL(2,1) NOT NULL,"
				+ "PRIMARY KEY(Libelle_Niveau))";

		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
		}
	}

	/**
	 * Supprime la table niveau
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			return deleteTable.execute("drop table Niveau");
		}
	}

	/**
	 * Renvoie tous les niveaux existants
	 */
	@Override
	public List<Niveau> getAll() throws SQLException {
		try (Statement getAll = super.getConnexion().getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Niveau");
			List<Niveau> sortie = new ArrayList<>();
			while (resultat.next()) {
				sortie.add(Niveau.valueOf(resultat.getString(super.getConstants().getLibelleN()).toUpperCase()));
			}
			return sortie;
		}
	}

	/**
	 * Renvoie un niveau précis
	 * Les paramètres sont placés dans cet ordre : Libelle_Niveau (STRING)
	 */
	@Override
	public Optional<Niveau> getById(String... nom) throws SQLException {
		try (PreparedStatement getById = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Niveau WHERE Libelle_Niveau = ?")) {
			getById.setString(1, nom[0]);
			ResultSet resultat = getById.executeQuery();
			if (resultat.next()) {
				return Optional.of(Niveau.valueOf(resultat.getString(super.getConstants().getLibelleN())));

			}
			return Optional.empty();
		}
	}

	/**
	 * Ajoute un niveau à la table Niveau à partir d'un objet Niveau
	 */
	@Override
	public boolean add(Niveau value) throws SQLException {
		try (PreparedStatement add = super.getConnexion().getConnection().prepareStatement(
				"INSERT INTO Niveau(Libelle_Niveau,Coefficient) values (?,?)")) {
			add.setString(1, value.name());
			add.setFloat(2, value.getCoefficient());
			return add.execute();
		}
	}

	/**
	 * Ne pas utiliser
	 */
	@Override
	public boolean update(Niveau value) throws SQLException {
		return false;
	}

	/**
	 * supprime un niveau
	 * Les paramètres sont placés dans cet ordre : Libelle_Niveau (STRING)
	 */
	@Override
	public boolean delete(String... value) throws SQLException, MemeEquipeException, FausseDateException, IdNotSetException {
		try (PreparedStatement delete = super.getConnexion().getConnection().prepareStatement(
				"DELETE FROM Niveau where Libelle_Niveau = ?")) {
			delete.setString(1, value[0]);
			Optional<Niveau> niveau = FactoryDAO.getDaoNiveau(super.getConnexion()).getById(value[0]);
			if (niveau.isPresent()) {
				List<Tournoi> tournois = FactoryDAO.getDaoTournoi(super.getConnexion()).getTournoiByNiveau(niveau.get());
				for (Tournoi t : tournois) {
					FactoryDAO.getDaoTournoi(super.getConnexion()).delete(t.getSaison().getAnnee(), t.getNom());
				}
			}
			return delete.execute();
		}
    }

	@Override
	public String visualizeTable() throws SQLException {
		StringBuilder s = new StringBuilder("_______________Niveau_______________________" + "\n");
		List<Niveau> l = this.getAll();
		for (Niveau a : l) {
			s.append(a.toString()).append("\n");
		}
		s.append("\n\n\n");
		return s.toString();
	}
}

