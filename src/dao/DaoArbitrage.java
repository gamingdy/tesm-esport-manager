package dao;

import exceptions.FausseDateException;
import modele.Arbitrage;
import modele.Arbitre;
import modele.Tournoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dao.DaoArbitre.getArbitres;
import static dao.DaoTournoi.getTournois;

public class DaoArbitrage extends SuperDao implements Dao<Arbitrage, Object> {


	private final DaoArbitre daoarbitre;
	private final DaoTournoi daotournoi;


	public DaoArbitrage(Connexion connexion) {
        super(connexion);
		this.daoarbitre = new DaoArbitre(connexion);
		this.daotournoi = new DaoTournoi(connexion);
	}

	/**
	 * Crée la table d'association arbitrage qui fait le lien entre les tournois et les arbitres
	 *
	 *
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Arbitrage("
				+ "Annee INT,"
				+ "Nom_tournoi VARCHAR(50),"
				+ "Nom VARCHAR(50), "
				+ "Prenom VARCHAR(50), "
				+ "Telephone VARCHAR(50),"
				+ "PRIMARY KEY(Annee, Nom_tournoi, Nom,Prenom,Telephone),"
				+ "FOREIGN KEY(Annee, Nom_tournoi) REFERENCES Tournoi(Annee, Nom_tournoi),"
				+ "FOREIGN KEY(Nom,Prenom,Telephone) REFERENCES Arbitre(Nom,Prenom,Telephone))";

		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
		}
	}

	/**
	 * Supprime la table arbitrage
	 *
	 *
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			return deleteTable.execute("drop table Arbitrage");
		}
	}

	/**
	 * Renvoie toutes les associations de tournois et d'arbitres existantes
	 */
	@Override
	public List<Arbitrage> getAll() throws SQLException,FausseDateException {
		try (Statement getAll = super.getConnexion().getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Arbitrage");
			List<Arbitrage> sortie = new ArrayList<>();
			while (resultat.next()) {
				Optional<Arbitre> arbitre = createOptionalArbitre(resultat);
				Optional<Tournoi> tournoi = createOptionalTournoi(resultat);
				if (arbitre.isPresent() && tournoi.isPresent()) {
					sortie.add(new Arbitrage(arbitre.get(),tournoi.get()));
				}
			}
			return sortie;
		}
	}

	private Optional<Arbitre> createOptionalArbitre(ResultSet resultat) throws SQLException {
		return daoarbitre.getById(resultat.getString(super.getConstants().getNom()), resultat.getString(super.getConstants().getPrenom()), resultat.getString(super.getConstants().getTelephone()));
	}
	private Optional<Tournoi> createOptionalTournoi(ResultSet resultat) throws SQLException, FausseDateException {
		return 	daotournoi.getById(resultat.getInt(super.getConstants().getAnnee()), resultat.getString(super.getConstants().getNomTournoi()));

	}

	/**
	 * Renvoie une association précise d'un tournoi et d'un arbitre
	 * Les paramètres sont placés dans cet ordre : Nom (STRING), Prenom (STRING), Telephone (STRING), Annee (INTEGER), Nom_tournoi (STRING)
	 */
	@Override
	public Optional<Arbitrage> getById(Object... id) throws SQLException,FausseDateException  {
		try (PreparedStatement getById = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Arbitrage WHERE Nom = ? AND Prenom = ? AND Telephone = ? AND Annee = ? AND Nom_tournoi = ?")) {
			bindParam(getById, id);
			ResultSet resultat = getById.executeQuery();
			if (resultat.next()) {
				Optional<Arbitre> arbitre = createOptionalArbitre(resultat);
				Optional<Tournoi> tournoi = createOptionalTournoi(resultat);
				if (arbitre.isPresent() && tournoi.isPresent()) {
					return Optional.of(new Arbitrage(arbitre.get(),tournoi.get()));
				}
				return Optional.empty();
			}
			return Optional.empty();
		}
    }

	private void bindParam(PreparedStatement getById, Object[] id) throws SQLException {
		getById.setString(1, (String) id[0]);
		getById.setString(2, (String) id[1]);
		getById.setString(3, (String) id[2]);
		getById.setInt(4, (Integer) id[3]);
		getById.setString(5, (String) id[4]);
	}

	/**
	 * Ajoute une association d'un tournoi et d'un arbitre à partir d'un objet association
	 */
	@Override
	public boolean add(Arbitrage value) throws SQLException {
		try (PreparedStatement add = super.getConnexion().getConnection().prepareStatement(
				"INSERT INTO Arbitrage(Nom,Prenom,Telephone,Annee,Nom_Tournoi) values (?,?,?,?,?)")) {
			add.setString(1, value.getArbitre().getNom());
			add.setString(2, value.getArbitre().getPrenom());
			add.setString(3, value.getArbitre().getNumeroTelephone());
			add.setInt(4, value.getTournoi().getSaison().getAnnee());
			add.setString(5, value.getTournoi().getNom());
			return add.execute();
		}
	}

	/**
	 * Ne pas utiliser
	 */
	@Override
	public boolean update(Arbitrage value) throws SQLException {
		return false;
	}

	/**
	 * Supprime une association d'un tournoi et d'un arbitre
	 * Les paramètres sont placés dans cet ordre : Nom Arbitre (STRING), Prenom Arbitre (STRING), Telephone (STRING), Annee (INTEGER), Nom_tournoi (STRING)
	 */
	@Override
	public boolean delete(Object... value) throws SQLException {
		try (PreparedStatement delete = super.getConnexion().getConnection().prepareStatement(
				"DELETE FROM Arbitrage where Nom = ? AND Prenom = ?  AND Telephone = ? AND Annee = ? AND Nom_tournoi = ?")) {
			bindParam(delete, value);

			return delete.execute();
		}
	}

	/**
	 * Renvoie la liste des arbitres pour un tournoi
	 * Les paramètres sont placés dans cet ordre : Nom_tournoi (STRING), Annee (INTEGER)
	 *
	 *
	 */
	public List<Arbitre> getArbitreByTournoi(Object... value) throws SQLException {
		try (PreparedStatement getArbitreByTournoi = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Arbitrage where Nom_Tournoi = ? AND Annee = ?")) {
			getArbitreByTournoi.setString(1, (String) value[0]);
			getArbitreByTournoi.setInt(2, (Integer) value[1]);
			return getArbitres(getArbitreByTournoi, daoarbitre);
		}
	}

	/**
	 * Renvoie tous les tournois pour un arbitre
	 * Les paramètres sont placés dans cet ordre : Nom (STRING), Prenom (STRING), Telephone (STRING)
	 *
	 */
	public List<Tournoi> getTournoiByArbitre(Object... value) throws SQLException,FausseDateException {
		try (PreparedStatement getTournoiByArbitre = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Arbitrage where Nom = ? AND Prenom = ?  AND Telephone = ?")) {
			getTournoiByArbitre.setString(1, (String) value[0]);
			getTournoiByArbitre.setString(2, (String) value[1]);
			getTournoiByArbitre.setString(3, (String) value[2]);
			ResultSet resultat = getTournoiByArbitre.executeQuery();
			return getTournois(resultat, daotournoi, super.getConstants());
		}
	}



	@Override
	public String visualizeTable() throws SQLException, FausseDateException {
		StringBuilder s = new StringBuilder("_______________Arbitrage_______________________" + "\n");
		List<Arbitrage> l = this.getAll();
		for (Arbitrage a : l) {
			s.append(a.toString()).append("\n");
		}
		s.append("\n\n\n");
		return s.toString();
	}


}

