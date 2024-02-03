package dao;

import exceptions.FausseDateException;
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

public class DaoMatche implements Dao<Matche, Integer> {

	private Connexion connexion;
	private DaoTournoi daotournoi;
	private DaoEquipe daoequipe;

	public DaoMatche(Connexion connexion) {
		this.connexion = connexion;
		this.daotournoi = new DaoTournoi(connexion);
		this.daoequipe = new DaoEquipe(connexion);
	}

	/**
	 * Crée la table Matche
	 *
	 * @param connexion
	 * @throws SQLException
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
			System.out.println("Table 'Matche' créée avec succès");
		}
	}

	/**
	 * Supprime la table Matche
	 *
	 * @param connexion
	 * @return
	 * @throws SQLException
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			System.out.println("Table 'Matche' suprimée avec succès");
			return deleteTable.execute("drop table Matche");
		}
	}

	/**
	 * Renvoie la liste de tous les matchs existants
	 */
	@Override
	public List<Matche> getAll() throws Exception {
		try (Statement getAll = connexion.getConnection().createStatement()) {
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
	public Optional<Matche> getById(Integer... id) throws Exception {
		try (PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Matche WHERE Id_Match = ?")) {
			getById.setInt(1, id[0]);
			ResultSet resultat = getById.executeQuery();
			Matche matche = null;
			matche = findMatche(resultat, matche);
			return Optional.ofNullable(matche);
		}
	}

	/**
	 * Ajoute un match à la table match à partir d'un objet match
	 */
	@Override
	public boolean add(Matche value) throws Exception {
		try (PreparedStatement add = connexion.getConnection().prepareStatement(
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
	public boolean update(Matche value) throws Exception {
		try (PreparedStatement update = connexion.getConnection().prepareStatement(
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
	 * supprime un match de la table match
	 * Les paramètres sont placés dans cet ordre : Id_Match (INTEGER)
	 */
	@Override
	public boolean delete(Integer... value) throws Exception {
		try (PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Matche where Id_Match = ?")) {
			delete.setInt(1, value[0]);
			List<Partie> parties = FactoryDAO.getDaoPartie(connexion).getPartieByMatche(FactoryDAO.getDaoMatche(connexion).getById(value[0]).get());
			for (Partie p : parties) {
				FactoryDAO.getDaoPartie(connexion).delete(value[0], p.getNumeroPartie());
			}
			return delete.execute();
		}
	}

	/**
	 * Récupère tous les matchs d'un tournoi
	 * Les paramètres sont placés de cette manières : Saison (INTEGER), Nom_tournoi (STRING)
	 *
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List<Matche> getMatchByTournoi(Object... value) throws Exception {
		try (PreparedStatement getMatchByTournoi = connexion.getConnection().prepareStatement("SELECT * FROM Matche WHERE Annee = ? AND Nom_Tournoi = ?")) {
			getMatchByTournoi.setInt(1, (Integer) value[0]);
			getMatchByTournoi.setString(2, (String) value[1]);
			ResultSet resultat = getMatchByTournoi.executeQuery();
			List<Matche> sortie = new ArrayList<>();
			generateListMatche(resultat, sortie);
			return sortie;
		}
	}

	public List<Matche> getMatchesByTournoiFromCategorie(Tournoi tournoi, Categorie categorie) throws FausseDateException, MemeEquipeException, SQLException, Exception {
		try (PreparedStatement getMatchesFromCategorie = connexion.getConnection().prepareStatement(
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

	public List<Matche> getMatchByEquipe(Equipe equipe) throws FausseDateException, MemeEquipeException, SQLException, Exception {
		try (PreparedStatement getMatchByEquipe = connexion.getConnection().prepareStatement(
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

	public List<Matche> getMatchByEquipeForTournoi(Equipe equipe, Tournoi tournoi) throws FausseDateException, MemeEquipeException, SQLException, Exception {
		try (PreparedStatement getMatchByEquipeForTournoi = connexion.getConnection().prepareStatement(
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

	public List<Matche> getMatchBySaison(Saison saison) throws FausseDateException, MemeEquipeException, Exception {
		try (PreparedStatement getMatchByEquipe = connexion.getConnection().prepareStatement(
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

	public List<Matche> getMatchByEquipeForSaison(Inscription inscription) throws FausseDateException, MemeEquipeException, Exception {
		try (PreparedStatement getMatchByEquipeForSaison = connexion.getConnection().prepareStatement(
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

	public List<Matche> getTenLastMatch() throws FausseDateException, MemeEquipeException, Exception {
		try (PreparedStatement getTenLastMatch = connexion.getConnection().prepareStatement(""
				+ "SELECT * "
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
	public String visualizeTable() throws Exception {
		String s = "_______________Matche_______________________" + "\n";
		List<Matche> l = this.getAll();
		for (Matche a : l) {
			s += a.toString() + "\n";
		}
		s += "\n\n\n";
		return s;
	}

	public Integer getLastId() throws SQLException {
		try (PreparedStatement getLastId = connexion.getConnection().prepareStatement(
				"SELECT Id_Match "
						+ "FROM Matche "
						+ "ORDER BY Id_Match DESC "
						+ "FETCH FIRST 1 ROW ONLY")) {
			ResultSet resultat = getLastId.executeQuery();
			Integer sortie = null;
			if (resultat.next()) {
				sortie = resultat.getInt("Id_Match");
			}
			return sortie;
		}
	}

	private void generateListMatche(ResultSet resultat, List<Matche> sortie)
			throws SQLException, FausseDateException, MemeEquipeException, Exception {
		while (resultat.next()) {
			Matche matche = new Matche(
					resultat.getInt("Nombres_Parties_Max"),
					new CustomDate(resultat.getTimestamp("Date_Matche_Debut")),
					Categorie.valueOf(resultat.getString("categorie")),
					daoequipe.getById(resultat.getString("Nom_Equipe1")).get(),
					daoequipe.getById(resultat.getString("Nom_Equipe2")).get(),
					daotournoi.getById(resultat.getInt("Annee"), resultat.getString("Nom_tournoi")).get());
			matche.setId(resultat.getInt("Id_Match"));
			sortie.add(matche);
		}
	}

	private Matche findMatche(ResultSet resultat, Matche matche)
			throws SQLException, FausseDateException, MemeEquipeException, Exception {
		if (resultat.next()) {
			matche = new Matche(
					resultat.getInt("Nombres_Parties_Max"),
					new CustomDate(resultat.getTimestamp("Date_Matche_Debut")),
					Categorie.valueOf(resultat.getString("categorie")),
					daoequipe.getById(resultat.getString("Nom_Equipe1")).get(),
					daoequipe.getById(resultat.getString("Nom_Equipe2")).get(),
					daotournoi.getById(resultat.getInt("Annee"), resultat.getString("Nom_tournoi")).get());
			matche.setId(resultat.getInt("Id_Match"));
		}
		return matche;
	}
}

