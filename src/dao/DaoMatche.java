package dao;

import exceptions.FausseDateException;
import exceptions.IdNotSetException;
import exceptions.MemeEquipeException;
import modele.Categorie;
import modele.CustomDate;
import modele.Equipe;
import modele.Inscription;
import modele.Matche;
import modele.Partie;
import modele.Saison;
import modele.Tournoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DaoMatche extends SuperDao implements Dao<Matche, Integer> {

	
	private final DaoTournoi daotournoi;
	private final DaoEquipe daoequipe;

	public DaoMatche(Connexion connexion) {
        super(connexion);
		this.daotournoi = new DaoTournoi(connexion);
		this.daoequipe = new DaoEquipe(connexion);
	}

	/**
	 * Crée la table Matche
	 *
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Matche("
				+ "Id_Match INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ "categorie VARCHAR(50),"
				+ "Nombres_Parties_Max INT,"
				+ "Date_Matche_Debut DATE,"
				+ "Nom_Equipe1 VARCHAR(50) NOT NULL,"
				+ "Nom_Equipe2 VARCHAR(50) NOT NULL,"
				+ "Annee INT NOT NULL,"
				+ "Nom_tournoi VARCHAR(50) NOT NULL,"
				+ "PRIMARY KEY(Id_Match),"
				+ "FOREIGN KEY(Nom_Equipe1) REFERENCES Equipe(Nom_Equipe),"
				+ "FOREIGN KEY(Nom_Equipe2) REFERENCES Equipe(Nom_Equipe),"
				+ "FOREIGN KEY(Annee, Nom_tournoi) REFERENCES Tournoi(Annee, Nom_tournoi))";


		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
		}
	}

	/**
	 * Supprime la table Matche
	 *
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			return deleteTable.execute("drop table Matche");
		}
	}

	/**
	 * Renvoie la liste de tous les matchs existants
	 */
	@Override
	public List<Matche> getAll() throws SQLException, MemeEquipeException, FausseDateException {
		try (Statement getAll = super.getConnexion().getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Matche");
			List<Matche> sortie = new ArrayList<>();
			generateListMatche(resultat, sortie);
			return sortie;
		}
	}

	/**
	 * renvoie un match précis
	 * Les paramètres sont placés dans cet ordre : Id_Match (INTEGER)
	 */
	@Override
	public Optional<Matche> getById(Integer... id) throws SQLException, MemeEquipeException, FausseDateException {
		try (PreparedStatement getById = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Matche WHERE Id_Match = ?")) {
			getById.setInt(1, id[0]);
			ResultSet resultat = getById.executeQuery();
			Matche matche = findMatche(resultat);
			return Optional.ofNullable(matche);
		}
    }

	/**
	 * Ajoute un match à la table match à partir d'un objet match
	 */
	@Override
	public boolean add(Matche value) throws SQLException {
		try (PreparedStatement add = super.getConnexion().getConnection().prepareStatement(
				"INSERT INTO Matche("
						+ "categorie,"
						+ "Nombres_Parties_Max,"
						+ "Date_Matche_Debut,"
						+ "Nom_Equipe1,"
						+ "Nom_Equipe2,"
						+ "Annee,"
						+ "Nom_Tournoi) values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
			add.setString(1, value.getCategorie().name());
			add.setInt(2, value.getNombreMaxParties());
			add.setTimestamp(3, value.getDateDebutMatche().toSQL());
			add.setString(4, value.getEquipe1().getNom());
			add.setString(5, value.getEquipe2().getNom());
			add.setInt(6, value.getTournoi().getSaison().getAnnee());
			add.setString(7, value.getTournoi().getNom());
			boolean execute = add.execute();
			value.setId(getLastId());
			return execute;
		}
	}


	/**
	 * Update une ligne de la table match à partir d'un objet match
	 */
	@Override
	public boolean update(Matche value) throws SQLException, IdNotSetException {
		try (PreparedStatement update = super.getConnexion().getConnection().prepareStatement(
				"UPDATE Matche SET "
						+ "categorie = ?,"
						+ "Nombres_Parties_Max = ?,"
						+ "Date_Matche_Debut = ?,"
						+ "Nom_Equipe1 = ?,"
						+ "Nom_Equipe2 = ?"
						+ "WHERE Id_Match = ?")) {
			update.setString(1, value.getCategorie().name());
			update.setInt(2, value.getNombreMaxParties());
			update.setTimestamp(3, value.getDateDebutMatche().toSQL());
			update.setString(4, value.getEquipe1().getNom());
			update.setString(5, value.getEquipe2().getNom());
			update.setInt(6, value.getId());
			return update.execute();
		}
    }

	/**
	 * Supprime un match de la table match
	 * Les paramètres sont placés dans cet ordre : Id_Match (INTEGER)
	 */
	@Override
	public boolean delete(Integer... value) throws SQLException, MemeEquipeException, FausseDateException, IdNotSetException {
		try (PreparedStatement delete = super.getConnexion().getConnection().prepareStatement(
				"DELETE FROM Matche where Id_Match = ?")) {
			delete.setInt(1, value[0]);
			Optional<Matche> matche = FactoryDAO.getDaoMatche(super.getConnexion()).getById(value[0]);
			if (matche.isPresent()) {
				List<Partie> parties = FactoryDAO.getDaoPartie(super.getConnexion()).getPartieByMatche(matche.get());
				for (Partie p : parties) {
					FactoryDAO.getDaoPartie(super.getConnexion()).delete(value[0], p.getNumeroPartie());
				}
			}
			return delete.execute();
		}
    }

	/**
	 * Récupère tous les matchs d'un tournoi
	 * Les paramètres sont placés de cette manière : Saison (INTEGER), Nom_tournoi (STRING)
	 *
	 */
	public List<Matche> getMatchByTournoi(Object... value) throws SQLException, MemeEquipeException, FausseDateException{
		try (PreparedStatement getMatchByTournoi = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Matche WHERE Annee = ? AND Nom_Tournoi = ?")) {
			getMatchByTournoi.setInt(1, (Integer) value[0]);
			getMatchByTournoi.setString(2, (String) value[1]);
			ResultSet resultat = getMatchByTournoi.executeQuery();
			List<Matche> sortie = new ArrayList<>();
			generateListMatche(resultat, sortie);
			return sortie;
		}
    }

	public List<Matche> getMatchesByTournoiFromCategorie(Tournoi tournoi, Categorie categorie) throws FausseDateException, MemeEquipeException, SQLException{
		try (PreparedStatement getMatchesFromCategorie = super.getConnexion().getConnection().prepareStatement(
				"SELECT * "
						+ "FROM Matche "
						+ "WHERE categorie = ? "
						+ "AND Annee = ? "
						+ "AND Nom_tournoi = ?")) {
			getMatchesFromCategorie.setString(1, categorie.name());
			getMatchesFromCategorie.setInt(2, tournoi.getSaison().getAnnee());
			getMatchesFromCategorie.setString(3, tournoi.getNom());
			ResultSet resultat = getMatchesFromCategorie.executeQuery();
			List<Matche> sortie = new ArrayList<>();
			generateListMatche(resultat, sortie);
			return sortie;
		}
	}

	public List<Matche> getMatchByEquipe(Equipe equipe) throws FausseDateException, MemeEquipeException, SQLException {
		try (PreparedStatement getMatchByEquipe = super.getConnexion().getConnection().prepareStatement(
				"SELECT * "
						+ "FROM Matche "
						+ "WHERE Nom_Equipe1 = ? "
						+ "OR Nom_Equipe2 = ?")) {
			getMatchByEquipe.setString(1, equipe.getNom());
			getMatchByEquipe.setString(2, equipe.getNom());
			ResultSet resultat = getMatchByEquipe.executeQuery();
			List<Matche> sortie = new LinkedList<>();
			generateListMatche(resultat, sortie);
			return sortie;
		}
	}

	public List<Matche> getMatchByEquipeForTournoi(Equipe equipe, Tournoi tournoi) throws FausseDateException, MemeEquipeException, SQLException {
		try (PreparedStatement getMatchByEquipeForTournoi = super.getConnexion().getConnection().prepareStatement(
				"SELECT * "
						+ "FROM Matche "
						+ "WHERE (Nom_Equipe1 = ? "
						+ "OR Nom_Equipe2 = ?) "
						+ "AND Nom_tournoi = ? "
						+ "AND Annee = ? ")) {
			getMatchByEquipeForTournoi.setString(1, equipe.getNom());
			getMatchByEquipeForTournoi.setString(2, equipe.getNom());
			getMatchByEquipeForTournoi.setString(3, tournoi.getNom());
			getMatchByEquipeForTournoi.setInt(4, tournoi.getSaison().getAnnee());
			ResultSet resultat = getMatchByEquipeForTournoi.executeQuery();
			List<Matche> sortie = new LinkedList<>();
			generateListMatche(resultat, sortie);
			return sortie;
		}
	}

	public List<Matche> getMatchBySaison(Saison saison) throws FausseDateException, MemeEquipeException, SQLException{
		try (PreparedStatement getMatchByEquipe = super.getConnexion().getConnection().prepareStatement(
				"SELECT * "
						+ "FROM Matche "
						+ "WHERE Annee = ? ")) {
			getMatchByEquipe.setInt(1, saison.getAnnee());
			ResultSet resultat = getMatchByEquipe.executeQuery();
			List<Matche> sortie = new LinkedList<>();
			generateListMatche(resultat, sortie);
			return sortie;
		}
	}

	public List<Matche> getMatchByEquipeForSaison(Inscription inscription) throws FausseDateException, MemeEquipeException, SQLException{
		try (PreparedStatement getMatchByEquipeForSaison = super.getConnexion().getConnection().prepareStatement(
				"SELECT * "
						+ "FROM Matche "
						+ "WHERE Annee = ? "
						+ "AND (Nom_Equipe1 = ? "
						+ "OR Nom_Equipe2 = ?)")) {
			getMatchByEquipeForSaison.setInt(1, inscription.getSaison().getAnnee());
			getMatchByEquipeForSaison.setString(2, inscription.getEquipe().getNom());
			getMatchByEquipeForSaison.setString(3, inscription.getEquipe().getNom());
			ResultSet resultat = getMatchByEquipeForSaison.executeQuery();
			List<Matche> sortie = new LinkedList<>();
			generateListMatche(resultat, sortie);
			return sortie;
		}
	}

	public List<Matche> getTenLastMatch() throws FausseDateException, MemeEquipeException, SQLException {
		try (PreparedStatement getTenLastMatch = super.getConnexion().getConnection().prepareStatement(
				"SELECT * "
				+ "FROM Matche "
				+ "WHERE Date_Matche_Debut < CURRENT_DATE "
				+ "ORDER BY Date_Matche_Debut DESC "
				+ "fetch first 10 rows only")) {
			List<Matche> sortie = new LinkedList<>();
			ResultSet resultat = getTenLastMatch.executeQuery();
			generateListMatche(resultat, sortie);
			return sortie;
		}
	}


	@Override
	public String visualizeTable() throws SQLException, MemeEquipeException, FausseDateException {
		StringBuilder s = new StringBuilder("_______________Matche_______________________" + "\n");
		List<Matche> l = this.getAll();
		for (Matche a : l) {
			s.append(a.toString()).append("\n");
		}
		s.append("\n\n\n");
		return s.toString();
	}

	public Integer getLastId() throws SQLException {
		try (PreparedStatement getLastId = super.getConnexion().getConnection().prepareStatement(
				"SELECT Id_Match "
						+ "FROM Matche "
						+ "ORDER BY Id_Match DESC "
						+ "FETCH FIRST 1 ROW ONLY")) {
			ResultSet resultat = getLastId.executeQuery();
			if (resultat.next()) {
				return resultat.getInt(super.getConstants().getIdMatch());
			}
			return null;
		}
	}

	private void generateListMatche(ResultSet resultat, List<Matche> sortie)
			throws FausseDateException, MemeEquipeException, SQLException {
		while (resultat.next()) {
			Optional<Equipe> equipe1 = createOptionalEquipe(resultat);
			Optional<Equipe> equipe2 = createOptionalEquipe2(resultat);
			Optional<Tournoi> tournoi = createOptionalTournoi(resultat);
			if (equipe1.isPresent() && equipe2.isPresent() && tournoi.isPresent()) {
				Matche matche = new Matche(
						resultat.getInt(super.getConstants().getNbParties()),
						new CustomDate(resultat.getTimestamp(super.getConstants().getDateDebut())),
						Categorie.valueOf(resultat.getString(super.getConstants().getCategorie())),
						equipe1.get(),
						equipe2.get(),
						tournoi.get()
				);
				matche.setId(resultat.getInt(super.getConstants().getIdMatch()));
				sortie.add(matche);
			}
		}
	}

	private Matche findMatche(ResultSet resultat)
			throws FausseDateException, MemeEquipeException, SQLException {
		if (resultat.next()) {
			Optional<Equipe> equipe1 = createOptionalEquipe(resultat);
			Optional<Equipe> equipe2 = createOptionalEquipe2(resultat);
			Optional<Tournoi> tournoi = createOptionalTournoi(resultat);
			if (equipe1.isPresent() && equipe2.isPresent() && tournoi.isPresent()) {
				Matche matche = new Matche(
						resultat.getInt(super.getConstants().getNbParties()),
						new CustomDate(resultat.getTimestamp(super.getConstants().getDateDebut())),
						Categorie.valueOf(resultat.getString(super.getConstants().getCategorie())),
						equipe1.get(),
						equipe2.get(),
						tournoi.get());
				matche.setId(resultat.getInt(super.getConstants().getIdMatch()));
				return matche;

			}
			return null;
		}
		return null;
	}

	private Optional<Tournoi> createOptionalTournoi(ResultSet resultat) throws SQLException, FausseDateException {
		return daotournoi.getById(resultat.getInt(super.getConstants().getAnnee()), resultat.getString(super.getConstants().getNomTournoi()));
	}

	private Optional<Equipe> createOptionalEquipe2(ResultSet resultat) throws SQLException {
		return daoequipe.getById(resultat.getString(super.getConstants().getEquipe2()));
	}

	private Optional<Equipe> createOptionalEquipe(ResultSet resultat) throws SQLException {
		return daoequipe.getById(resultat.getString(super.getConstants().getEquipe1()));
	}
}

