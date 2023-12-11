package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exceptions.FausseDateException;
import modele.CompteArbitre;
import modele.CustomDate;
import modele.Niveau;
import modele.Saison;
import modele.Tournoi;

public class DaoTournoi implements Dao<Tournoi, Object> {

	private Connexion connexion;

	public DaoTournoi(Connexion connexion) {
		this.connexion = connexion;
	}

	/**
	 * Crée la table Tournoi
	 *
	 * @param connexion
	 * @throws SQLException
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Tournoi("
				+ "Annee INT,"
				+ "Nom_tournoi VARCHAR(50),"
				+ "Date_Debut DATE,"
				+ "Date_Fin DATE,"
				+ "username VARCHAR(50),"
				+ "mdp VARCHAR(50),"
				+ "Libelle_Niveau VARCHAR(50) NOT NULL,"
				+ "PRIMARY KEY(Annee, Nom_tournoi),"
				+ "FOREIGN KEY(Annee) REFERENCES Saison(Annee),"
				+ "FOREIGN KEY(Libelle_Niveau) REFERENCES Niveau(Libelle_Niveau))";

		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
			System.out.println("Table 'Tournoi' créée avec succès");
		}
	}

	/**
	 * Supprime la table Tournoi
	 *
	 * @param connexion
	 * @return
	 * @throws SQLException
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement();) {
			System.out.println("Table 'Tournoi' supprimée avec succès");
			return deleteTable.execute("drop table Tournoi");
		}
	}

	/**
	 * <<<<<<< Updated upstream
	 *
	 * @return une liste de tous les tournois existants
	 */
	@Override
	public List<Tournoi> getAll() throws Exception {
		try (Statement getAll = connexion.getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Tournoi");
			List<Tournoi> sortie = new ArrayList<>();
			while (resultat.next()) {
				Tournoi tournoi = new Tournoi(
						new Saison(resultat.getInt("Annee")),
						resultat.getString("Nom_Tournoi"),
						new CustomDate(resultat.getTimestamp("Date_Debut")),
						new CustomDate(resultat.getTimestamp("Date_Fin")),
						Niveau.valueOf(resultat.getString("Libelle_Niveau").toUpperCase()),
						new CompteArbitre(
								resultat.getString("username"),
								resultat.getString("mdp"))
				);
				sortie.add(tournoi);
			}
			return sortie;
		}
	}

	/**
	 * @return un tournoi en particulier
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER) , Nom_Tournoi (STRING)
	 */
	@Override
	public Tournoi getById(Object... id) throws Exception {
		try (PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Tournoi WHERE Nom_Tournoi = ? AND Annee = ?")) {
			getById.setString(1, (String) id[0]);
			getById.setInt(2, (Integer) id[1]);
			ResultSet resultat = getById.executeQuery();
			if (resultat.next()) {
				Tournoi tournoi = new Tournoi(
						new Saison(resultat.getInt("Annee")),
						resultat.getString("Nom_Tournoi"),
						new CustomDate(resultat.getTimestamp("Date_Debut")),
						new CustomDate(resultat.getTimestamp("Date_Fin")),
						Niveau.search(resultat.getString("Libelle_Niveau")),
						new CompteArbitre(
								resultat.getString("username"),
								resultat.getString("mdp"))
				);
				return tournoi;
			}
			throw new Exception("Tournoi non trouvé");
		}
	}

	/**
	 * Ajoute un tournoi à la table tournoi à partir d'un objet tournoi
	 */
	@Override
	public boolean add(Tournoi value) throws Exception {
		try (PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Tournoi(Annee,Nom_Tournoi,Date_Debut,Date_Fin,username,mdp,Libelle_Niveau) values (?,?,?,?,?,?,?)")) {
			add.setInt(1, value.getSaison().getAnnee());
			add.setString(2, value.getNom());
			add.setTimestamp(3, value.getDebut().toSQL());
			add.setTimestamp(4, value.getFin().toSQL());
			add.setString(5, value.getCompteArbitre().getUsername());
			add.setString(6, value.getCompteArbitre().getMdp());
			add.setString(7, value.getNiveau().getNom());

			return add.execute();
		}
	}

	/**
	 * Update les valeurs d'un tournoi à partir d'un objet tournoi
	 */
	@Override
	public boolean update(Tournoi value) throws Exception {
		try (PreparedStatement update = connexion.getConnection().prepareStatement(
				"UPDATE Tournoi SET"
						+ "Date_Debut = ?,"
						+ "Date_Fin = ?,"
						+ "username = ?,"
						+ "mdp = ?,"
						+ "Libelle_Niveau = ?"
						+ "WHERE Annee = ? AND Nom_Tournoi = ?")) {
			update.setInt(6, value.getSaison().getAnnee());
			update.setString(7, value.getNom());
			update.setTimestamp(1, value.getDebut().toSQL());
			update.setTimestamp(2, value.getFin().toSQL());
			update.setString(3, value.getCompteArbitre().getUsername());
			update.setString(4, value.getCompteArbitre().getMdp());
			update.setString(5, value.getNiveau().getNom());

			return update.execute();
		}
	}

	/**
	 * Supprime un tournoi de la table tournoi à partir de sa clé primaire
	 * <<<<<<< Updated upstream
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER) , Nom_Tournoi (STRING)
	 * <p>
	 * =======
	 * Les paramètres sont placés dans cet ordre : annee du tournoi , nom du tournoi
	 * >>>>>>> Stashed changes
	 */
	@Override
	public boolean delete(Object... value) throws Exception {
		try (PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Tournoi WHERE Annee = ? AND Nom_Tournoi = ?")) {
			delete.setInt(1, (Integer) value[0]);
			delete.setString(2, (String) value[1]);
			return delete.execute();
		}
	}

	/**
	 * @param value
	 * @return le compte abritre associé à un tournoi
	 * <<<<<<< Updated upstream
	 * @throws SQLException Les paramètres sont placés dans cet ordre : Annee (INTEGER) , Nom_Tournoi (STRING)
	 *                      =======
	 * @throws SQLException Les paramètres sont placés dans cet ordre : annee du tournoi , nom du tournoi
	 *                      >>>>>>> Stashed changes
	 */
	public CompteArbitre getCompteArbitreByTournoi(Object... value) throws SQLException {
		try (PreparedStatement getCompteArbitreByTournoi = connexion.getConnection().prepareStatement(
				"SELECT username,mdp FROM Tournoi WHERE Annee = ? AND Nom_Tournoi = ?")) {
			getCompteArbitreByTournoi.setInt(1, (Integer) value[0]);
			getCompteArbitreByTournoi.setString(2, (String) value[1]);
			ResultSet resultat = getCompteArbitreByTournoi.executeQuery();
			resultat.next();
			return new CompteArbitre(resultat.getString("username"), resultat.getString("mdp"));
		}
	}

	/**
	 * @return Le tournoi actuel s'il existe sinon un optional null
	 * @throws SQLException
	 * @throws FausseDateException Vérifie la date système, si cette date est comprise entre la date de début et de fin du dernier tournoi actuel, alors cela renvoie le dernier tournoi sinon renvoie un optional null
	 */
	public Optional<Tournoi> getTournoiActuel() throws SQLException, FausseDateException {
		CustomDate c = new CustomDate(Timestamp.from(Instant.now()));
		try (PreparedStatement getCompteArbitreByTournoi = connexion.getConnection().prepareStatement(
				"SELECT * FROM Tournoi WHERE ? BETWEEN Date_Debut AND Date_Fin ")) {
			getCompteArbitreByTournoi.setTimestamp(1, c.toSQL());
			ResultSet resultat = getCompteArbitreByTournoi.executeQuery();
			resultat.next();
			Tournoi tournoi = new Tournoi(
					new Saison(resultat.getInt("Annee")),
					resultat.getString("Nom_Tournoi"),
					new CustomDate(resultat.getTimestamp("Date_Debut")),
					new CustomDate(resultat.getTimestamp("Date_Fin")),
					Niveau.search(resultat.getString("Libelle_Niveau")),
					new CompteArbitre(
							resultat.getString("username"),
							resultat.getString("mdp"))
			);
			return Optional.ofNullable(tournoi);
		}
	}
	
	/**
	 * Renvoie la liste de tournoi en fonction d'une année précise
	 * @param annee
	 * @return
	 * @throws SQLException
	 * @throws FausseDateException
	 */
	
	public List<Tournoi> getTournoiBySaison(Saison saison) throws SQLException, FausseDateException {
		try(PreparedStatement getTournoiBySaison = connexion.getConnection().prepareStatement(
				"Select * From Tournoi Where Annee = ?")) {
			getTournoiBySaison.setInt(1, saison.getAnnee());
			ResultSet resultat = getTournoiBySaison.executeQuery();
			List<Tournoi> sortie = new ArrayList<>();
			while (resultat.next()) {
				sortie.add(
						new Tournoi(
								new Saison(resultat.getInt("Annee")),
								resultat.getString("Nom_Tournoi"),
								new CustomDate(resultat.getTimestamp("Date_Debut")),
								new CustomDate(resultat.getTimestamp("Date_Fin")),
								Niveau.search(resultat.getString("Libelle_Niveau")),
								new CompteArbitre(
										resultat.getString("username"),
										resultat.getString("mdp"))
								));
			}
			return sortie;
		}
	}

}
