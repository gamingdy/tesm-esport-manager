package dao;

import exceptions.FausseDateException;
import modele.Equipe;
import modele.Poule;
import modele.Tournoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoPoule extends SuperDao implements Dao<Poule, Object> {


	private final DaoTournoi daotournoi;

	public DaoPoule(Connexion connexion) {
        super(connexion);
        this.daotournoi = new DaoTournoi(connexion);

	}

	/**
	 * Crée la table Poule
	 *
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
		}
	}

	/**
	 * Supprime la table Poule
	 *
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			return deleteTable.execute("drop table Poule");
		}
	}

	/**
	 * Renvoie toutes les poules existantes
	 */
	@Override
	public List<Poule> getAll() throws SQLException, FausseDateException {
		try (Statement getAll = super.getConnexion().getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Poule");
			List<Poule> sortie = new ArrayList<>();
			return getPoules(resultat, sortie);
		}
	}

	/**
	 * Renvoie une poule précise
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER), Nom_tournoi (STRING), Libelle_poule (STRING)
	 */
	@Override
	public Optional<Poule> getById(Object... value) throws SQLException, FausseDateException {
		try (PreparedStatement getById = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Poule WHERE Annee = ? AND Nom_Tournoi = ? AND Libelle = ?")) {
			getById.setInt(1, (Integer) value[0]);
			getById.setString(2, (String) value[1]);
			getById.setString(3, (String) value[2]);
			ResultSet resultat = getById.executeQuery();
			if (resultat.next()) {
				Optional<Tournoi> tournoi = daotournoi.getById(resultat.getInt(super.getConstants().getAnnee()), resultat.getString(super.getConstants().getNomTournoi()));
				if (tournoi.isPresent()) {
					return Optional.of(new Poule(tournoi.get(), resultat.getString(super.getConstants().getLibelle()).charAt(0)));
				}
				return Optional.empty();
			}
			return Optional.empty();
		}
    }

	/**
	 * Ajoute une poule à la table poule à partir d'un objet poule
	 */
	@Override
	public boolean add(Poule value) throws SQLException {
		try (PreparedStatement add = super.getConnexion().getConnection().prepareStatement(
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
	public boolean update(Poule value) throws SQLException {
		return false;
	}

	/**
	 * Supprime une poule
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER), Nom_tournoi (STRING), Libelle (STRING)
	 */
	@Override
	public boolean delete(Object... value) throws SQLException {
		try (PreparedStatement delete = super.getConnexion().getConnection().prepareStatement(
				"DELETE FROM Poule where Annee = ? AND Nom_tournoi = ? AND Libelle = ?")) {
			delete.setInt(1, (Integer) value[0]);
			delete.setString(2, (String) value[1]);
			Character c = (Character) value[2];
			String libelle = c.toString();
			delete.setString(3, libelle);
			List<Equipe> equipes = FactoryDAO.getDaoAppartenance(super.getConnexion()).getEquipeByPoule(value[1], value[0], value[2]);
			for (Equipe e : equipes) {
				FactoryDAO.getDaoAppartenance(super.getConnexion()).delete(
						e.getNom(),
						value[0],
						value[1],
						value[2]
				);
			}
			return delete.execute();
		}
    }

	public List<Poule> getPouleByTournoi(Tournoi tournoi) throws SQLException, FausseDateException {
		try (PreparedStatement getPouleByTournoi = super.getConnexion().getConnection().prepareStatement("SELECT * "
				+ "FROM Poule "
				+ "WHERE Annee = ? "
				+ "AND Nom_tournoi = ?")) {
			getPouleByTournoi.setInt(1, tournoi.getSaison().getAnnee());
			getPouleByTournoi.setString(2, tournoi.getNom());
			ResultSet resultat = getPouleByTournoi.executeQuery();
			List<Poule> sortie = new ArrayList<>();
			return getPoules(resultat, sortie);
		}
    }

	private List<Poule> getPoules(ResultSet resultat, List<Poule> sortie) throws SQLException, FausseDateException {
		while (resultat.next()) {
			Optional<Tournoi> tournoiOptional = daotournoi.getById(resultat.getInt(super.getConstants().getAnnee()), resultat.getString(super.getConstants().getNomTournoi()));
			if (tournoiOptional.isPresent()) {
				sortie.add(new Poule(tournoiOptional.get(), resultat.getString(super.getConstants().getLibelle()).charAt(0)));
			}
		}
		return sortie;
	}

	@Override
	public String visualizeTable() throws SQLException, FausseDateException {
		StringBuilder s = new StringBuilder("_______________Poule_______________________" + "\n");
		List<Poule> l = this.getAll();
		for (Poule a : l) {
			s.append(a.toString()).append("\n");
		}
		s.append("\n\n\n");
		return s.toString();
	}
}

