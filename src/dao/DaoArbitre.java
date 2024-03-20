package dao;

import exceptions.FausseDateException;
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

public class DaoArbitre extends SuperDao implements Dao<Arbitre, Object> {

	

	public DaoArbitre(Connexion connexion) {
        super(connexion);
	}

	/**
	 * Crée la table Arbitre
	 *
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Arbitre("
				+ "Nom VARCHAR(50),"
				+ "Prenom VARCHAR(50),"
				+ "Telephone VARCHAR(50),"
				+ "PRIMARY KEY(Nom,Prenom,Telephone))";

		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
		}
	}

	/**
	 * Supprime la table arbitre
	 *
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			return deleteTable.execute("drop table Arbitre");
		}
	}

	/**
	 * Renvoie tous les arbitres existants
	 */
	@Override
	public List<Arbitre> getAll() throws SQLException {
		try (Statement getAll = super.getConnexion().getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Arbitre");
			List<Arbitre> sortie = new ArrayList<>();
			while (resultat.next()) {
				sortie.add(createArbitre(resultat));
			}
			return sortie;
		}
	}

	private Arbitre createArbitre(ResultSet resultat) throws SQLException {
		return new Arbitre(
				resultat.getString(super.getConstants().getNom()),
				resultat.getString(super.getConstants().getPrenom()),
				resultat.getString(super.getConstants().getTelephone()));
	}

	/**
	 * renvoie un arbitre précis
	 * Les paramètres sont placés dans cet ordre : Nom Arbitre (STRING) , Prenom Arbitre (STRING) , Telephone (STRING)
	 */
	@Override
	public Optional<Arbitre> getById(Object... id) throws SQLException {
		try (PreparedStatement getById = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Arbitre WHERE Nom = ? AND Prenom = ? AND Telephone = ? ")) {
			getById.setString(1, (String) id[0]);
			getById.setString(2, (String) id[1]);
			getById.setString(3, (String) id[2]);
			ResultSet resultat = getById.executeQuery();
			if (resultat.next()) {
				return Optional.of(createArbitre(resultat));
			}
			return Optional.empty();
		}
	}
	
	public Optional<Arbitre> getArbitreByTelephone(String numero) throws SQLException {
		try(PreparedStatement getById = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Arbitre WHERE Telephone = ? ")){
			getById.setString(1, numero);
			ResultSet resultat = getById.executeQuery();
			if (resultat.next()) {
				return Optional.of(createArbitre(resultat));
			}
			return Optional.empty();
		}
	}
	
	/**
	 * Ajoute un arbitre à la table arbitre à partir d'un objet arbitre
	 */
	@Override
	public boolean add(Arbitre value) throws SQLException {
		try (PreparedStatement add = super.getConnexion().getConnection().prepareStatement(
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
	public boolean update(Arbitre value) throws SQLException {
		return false;
	}

	/**
	 * Supprime un arbitre de la table arbitre
	 * Les paramètres sont placés dans cet ordre : Nom Arbitre (STRING) , Prenom Arbitre (STRING) , Telephone (STRING)
	 */
	@Override
	public boolean delete(Object... value) throws SQLException, FausseDateException {
		try (PreparedStatement delete = super.getConnexion().getConnection().prepareStatement(
				"DELETE FROM Arbitre WHERE Nom = ? AND Prenom = ? AND Telephone = ? ")) {
			delete.setString(1, (String) value[0]);
			delete.setString(2, (String) value[1]);
			delete.setString(3, (String) value[2]);
			List<Saison> saisons = FactoryDAO.getDaoSelection(super.getConnexion()).getSaisonByArbitre(value[0], value[1], value[2]);
			List<Tournoi> tournois = FactoryDAO.getDaoArbitrage(super.getConnexion()).getTournoiByArbitre(value[0], value[1], value[2]);
			for (Saison s : saisons) {
				FactoryDAO.getDaoSelection(super.getConnexion()).delete(value[0], value[1], value[2], s.getAnnee());
			}
			for (Tournoi t : tournois) {
				FactoryDAO.getDaoArbitrage(super.getConnexion()).delete(value[0], value[1], value[2], t.getSaison().getAnnee(), t.getNom());
			}
			return delete.execute();
		}
    }

	static List<Arbitre> getArbitres(PreparedStatement getArbitreByTournoi, DaoArbitre daoarbitre) throws SQLException {
		ResultSet resultat = getArbitreByTournoi.executeQuery();
		List<Arbitre> sortie = new ArrayList<>();
		while (resultat.next()) {
			Optional<Arbitre> arbitre = daoarbitre.getById(resultat.getString("Nom"), resultat.getString("Prenom"), resultat.getString("Telephone"));
			arbitre.ifPresent(sortie::add);
		}
		return sortie;
	}

	@Override
	public String visualizeTable() throws SQLException {
		StringBuilder s = new StringBuilder("_______________Arbitre_______________________" + "\n");
		List<Arbitre> l = this.getAll();
		for (Arbitre a : l) {
			s.append(a.toString()).append("\n");
		}
		s.append("\n\n\n");
		return s.toString();
	}

}
