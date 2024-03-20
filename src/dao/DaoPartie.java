package dao;

import exceptions.FausseDateException;
import exceptions.GagnantNonChoisiException;
import exceptions.IdNotSetException;
import exceptions.MemeEquipeException;
import modele.Equipe;
import modele.Matche;
import modele.Partie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DaoPartie extends SuperDao implements Dao<Partie, Integer> {


	private final DaoMatche daomatche;


	public DaoPartie(Connexion connexion) {
        super(connexion);
        this.daomatche = new DaoMatche(connexion);

	}

	/**
	 * Crée la table partie
	 *
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Partie("
				+ "Id_Partie INT NOT NULL,"
				+ "Id_Match INT NOT NULL,"
				+ "Nom_Equipe VARCHAR(50),"
				+ "PRIMARY KEY(Id_Partie,Id_Match),"
				+ "FOREIGN KEY(Id_Match) REFERENCES Matche(Id_Match),"
				+ "FOREIGN KEY(Nom_Equipe) REFERENCES Equipe(Nom_Equipe))";

		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
		}
	}

	/**
	 * Supprime la table poule
	 *
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			return deleteTable.execute("drop table Partie");
		}
	}

	/**
	 * Renvoie toutes les parties existantes
	 */
	@Override
	public List<Partie> getAll() throws SQLException, MemeEquipeException, FausseDateException {
		try (Statement getAll = super.getConnexion().getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Partie");
			List<Partie> sortie = new ArrayList<>();
			return getParties(resultat, sortie);
		}
	}

	/**
	 * Renvoie une partie précise
	 * Les paramètres sont placés dans cet ordre : Id_Match (INTEGER), Numero_Partie (INTEGER)
	 */
	@Override
	public Optional<Partie> getById(Integer... id) throws SQLException, MemeEquipeException, FausseDateException {
		try (PreparedStatement getById = super.getConnexion().getConnection().prepareStatement("SELECT * "
				+ "FROM Partie "
				+ "WHERE Id_Match = ? "
				+ "AND Id_Partie = ?")) {
			getById.setInt(1, id[0]);
			getById.setInt(2, id[1]);
			ResultSet resultat = getById.executeQuery();
			if (resultat.next()) {
				Optional<Matche> matche = daomatche.getById(resultat.getInt(super.getConstants().getIdMatch()));
				if (matche.isPresent()) {
					Partie partie = createPartie(matche.get(), resultat);
					return Optional.of(partie);
				}
				return Optional.empty();
			}
			return Optional.empty();
		}
    }

	private Partie createPartie(Matche matche, ResultSet resultat) throws SQLException {
		Partie partie = new Partie(
				matche,
				resultat.getInt(super.getConstants().getIdPartie()));
		Optional<Equipe> vainqueur = FactoryDAO.getDaoEquipe(Connexion.getConnexion()).getById(resultat.getString(super.getConstants().getNomEquipe()));
		vainqueur.ifPresent(partie::setVainqueur);
		return partie;
	}

	/**
	 * Ajoute une partie à la table partie à partir d'un objet partie
	 */
	@Override
	public boolean add(Partie value) throws SQLException, IdNotSetException  {
		try (PreparedStatement add = super.getConnexion().getConnection().prepareStatement(
				"INSERT INTO Partie(Id_Match,Id_Partie) values (?,?)")) {
			add.setInt(1, value.getMatche().getId());
			add.setInt(2, value.getNumeroPartie());

			return add.execute();
		}
    }

	/**
	 * Met à jour une ligne de la table partie à partir d'un objet Partie
	 */
	@Override
	public boolean update(Partie value) throws SQLException, GagnantNonChoisiException, IdNotSetException{
		try (PreparedStatement update = super.getConnexion().getConnection().prepareStatement(
				"UPDATE Partie SET "
						+ "Nom_Equipe = ? "
						+ "WHERE Id_Partie = ? "
						+ "AND Id_Match = ?")) {
			update.setString(1, value.getVainqueur().getNom());
			update.setInt(3, value.getMatche().getId());
			update.setInt(2, value.getNumeroPartie());
			return update.execute();
		}
    }

	/**
	 * Supprime une partie de la table partie
	 * Les paramètres sont placés dans cet ordre : Id_Match (INTEGER), Numero_Partie (INTEGER)
	 */
	@Override
	public boolean delete(Integer... value) throws SQLException {
		try (PreparedStatement delete = super.getConnexion().getConnection().prepareStatement(
				"DELETE FROM Partie where Id_Match = ? AND Id_Partie = ?")) {
			delete.setInt(1, value[0]);
			delete.setInt(2, value[1]);
			return delete.execute();
		}
	}

	public List<Partie> getPartieByMatche(Matche matche) throws IllegalArgumentException, SQLException, MemeEquipeException, FausseDateException, IdNotSetException  {
		try (PreparedStatement getPartieByMatche = super.getConnexion().getConnection().prepareStatement(
				"SELECT * "
						+ "FROM Partie "
						+ "WHERE Id_Match = ?")) {
			getPartieByMatche.setInt(1, matche.getId());
			ResultSet resultat = getPartieByMatche.executeQuery();
			List<Partie> sortie = new LinkedList<>();
			return getParties(resultat, sortie);
		}
    }

	private List<Partie> getParties(ResultSet resultat, List<Partie> sortie) throws SQLException, MemeEquipeException, FausseDateException {
		while (resultat.next()) {
			Optional<Matche> matche = daomatche.getById(resultat.getInt(super.getConstants().getIdMatch()));
			if (matche.isPresent()) {
				Partie partie = createPartie(matche.get(), resultat);
				sortie.add(partie);
			}
		}
		return sortie;
	}

	@Override
	public String visualizeTable() throws SQLException, MemeEquipeException, FausseDateException {
		StringBuilder s = new StringBuilder("_______________Partie_______________________" + "\n");
		List<Partie> l = this.getAll();
		for (Partie a : l) {
			s.append(a.toString()).append("\n");
		}
		s.append("\n\n\n");
		return s.toString();
	}
}


