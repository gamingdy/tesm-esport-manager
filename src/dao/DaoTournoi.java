package dao;

import exceptions.FausseDateException;
import exceptions.IdNotSetException;
import exceptions.MemeEquipeException;
import modele.Arbitre;
import modele.CompteArbitre;
import modele.CustomDate;
import modele.Matche;
import modele.Niveau;
import modele.Poule;
import modele.Saison;
import modele.Tournoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DaoTournoi extends SuperDao implements Dao<Tournoi, Object> {
	
	public DaoTournoi(Connexion connexion) {
        super(connexion);
	}

	/**
	 * Crée la table Tournoi
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Tournoi("
				+ "Annee INT,"
				+ "Nom_tournoi VARCHAR(50),"
				+ "Date_Debut DATE,"
				+ "Date_Fin DATE,"
				+ "Username_Compte_Arbitre VARCHAR(50),"
				+ "Mot_De_Passe_Compte_Arbitre VARCHAR(50),"
				+ "Libelle_Niveau VARCHAR(50) NOT NULL,"
				+ "PRIMARY KEY(Annee, Nom_tournoi),"
				+ "FOREIGN KEY(Annee) REFERENCES Saison(Annee),"
				+ "FOREIGN KEY(Libelle_Niveau) REFERENCES Niveau(Libelle_Niveau))";

		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
		}
	}

	/**
	 * Supprime la table Tournoi
	 *
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			return deleteTable.execute("drop table Tournoi");
		}
	}

	/**
	 * <<<<<<< Updated upstream
	 *
	 * @return une liste de tous les tournois existants
	 */
	@Override
	public List<Tournoi> getAll() throws SQLException, FausseDateException {
		try (Statement getAll = super.getConnexion().getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Tournoi");
			List<Tournoi> sortie = new ArrayList<>();
			while (resultat.next()) {
				sortie.add(createTournoi(resultat));
			}
			return sortie;
		}
	}

	private Tournoi createTournoi(ResultSet resultat) throws FausseDateException, SQLException {
        return new Tournoi(
				new Saison(resultat.getInt(super.getConstants().getAnnee())),
				resultat.getString(super.getConstants().getNomTournoi()),
				new CustomDate(resultat.getTimestamp(super.getConstants().getDd())),
				new CustomDate(resultat.getTimestamp(super.getConstants().getDf())),
				Niveau.valueOf(resultat.getString(super.getConstants().getLibelleN()).toUpperCase()),
				new CompteArbitre(
						resultat.getString(super.getConstants().getUsername()),
						resultat.getString(super.getConstants().getMdp()))
		);
	}

	/**
	 * @return un tournoi en particulier
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER), Nom_Tournoi (STRING)
	 */
	@Override
	public Optional<Tournoi> getById(Object... id) throws SQLException, FausseDateException{
		try (PreparedStatement getById = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Tournoi WHERE Annee = ? AND Nom_Tournoi = ?")) {
			getById.setInt(1, (Integer) id[0]);
			getById.setString(2, (String) id[1]);
			return getTournoi(getById);
		}
    }

	/**
	 * Ajoute un tournoi à la table tournoi à partir d'un objet tournoi
	 */
	@Override
	public boolean add(Tournoi value) throws SQLException {
		try (PreparedStatement add = super.getConnexion().getConnection().prepareStatement(
				"INSERT INTO Tournoi(Annee,Nom_Tournoi,Date_Debut,Date_Fin,Username_Compte_Arbitre,Mot_De_Passe_Compte_Arbitre,Libelle_Niveau) values (?,?,?,?,?,?,?)")) {
			add.setInt(1, value.getSaison().getAnnee());
			add.setString(2, value.getNom());
			add.setTimestamp(3, value.getDebut().toSQL());
			add.setTimestamp(4, value.getFin().toSQL());
			add.setString(5, value.getCompteArbitre().getUsername());
			add.setString(6, value.getCompteArbitre().getHashMdp());
			add.setString(7, value.getNiveau().name());

			return add.execute();
		}
	}

	static List<Tournoi> getTournois(ResultSet resultat, DaoTournoi daotournoi, Constants constants) throws SQLException, FausseDateException {
		List<Tournoi> sortie = new ArrayList<>();
		while (resultat.next()) {
			Optional<Tournoi> tournoi = daotournoi.getById(resultat.getInt(constants.getAnnee()), resultat.getString(constants.getNomTournoi()));
			tournoi.ifPresent(sortie::add);
		}
		return sortie;
	}

	/**
	 * Update les valeurs d'un tournoi à partir d'un objet tournoi
	 */
	@Override
	public boolean update(Tournoi value) throws SQLException {
		try (PreparedStatement update = super.getConnexion().getConnection().prepareStatement(
				"UPDATE Tournoi SET "
						+ "Date_Debut = ?, "
						+ "Date_Fin = ?, "
						+ "Username_Compte_Arbitre = ?, "
						+ "Mot_De_Passe_Compte_Arbitre = ?, "
						+ "Libelle_Niveau = ? "
						+ "WHERE Annee = ? AND Nom_Tournoi = ?")) {
			update.setInt(6, value.getSaison().getAnnee());
			update.setString(7, value.getNom());
			update.setTimestamp(1, value.getDebut().toSQL());
			update.setTimestamp(2, value.getFin().toSQL());
			update.setString(3, value.getCompteArbitre().getUsername());
			update.setString(4, value.getCompteArbitre().getHashMdp());
			update.setString(5, value.getNiveau().name());

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
	public boolean delete(Object... value) throws SQLException, MemeEquipeException, FausseDateException, IdNotSetException {
		try (PreparedStatement delete = super.getConnexion().getConnection().prepareStatement(
				"DELETE FROM Tournoi WHERE Annee = ? AND Nom_Tournoi = ?")) {
			delete.setInt(1, (Integer) value[0]);
			delete.setString(2, (String) value[1]);
			Optional<Tournoi> tournoi = FactoryDAO.getDaoTournoi(super.getConnexion()).getById(value[0], value[1]);
			if (tournoi.isPresent()) {
				List<Matche> matches = FactoryDAO.getDaoMatche(super.getConnexion()).getMatchByTournoi(value[0], value[1]);
				List<Poule> poules = FactoryDAO.getDaoPoule(super.getConnexion()).getPouleByTournoi(tournoi.get());
				List<Arbitre> arbitres = FactoryDAO.getDaoArbitrage(super.getConnexion()).getArbitreByTournoi(value[1], value[0]);
				for (Matche m : matches) {
					FactoryDAO.getDaoMatche(super.getConnexion()).delete(m.getId());
				}
				for (Poule p : poules) {
					FactoryDAO.getDaoPoule(super.getConnexion()).delete(p.getTournoi().getSaison().getAnnee(), p.getTournoi().getNom(), p.getLibelle());
				}
				for (Arbitre a : arbitres) {
					FactoryDAO.getDaoArbitrage(super.getConnexion()).delete(a.getNom(), a.getPrenom(), a.getNumeroTelephone(), value[0], value[1]);
				}
			}
			return delete.execute();
		}
    }

	/**
	 * @return le compte abritre associé à un tournoi
	 * <<<<<<< Updated upstream
	 * @throws SQLException Les paramètres sont placés dans cet ordre : Annee (INTEGER) , Nom_Tournoi (STRING)
	 *                      =======
	 * @throws SQLException Les paramètres sont placés dans cet ordre : annee du tournoi , nom du tournoi
	 *                      >>>>>>> Stashed changes
	 */
	public CompteArbitre getCompteArbitreByTournoi(Object... value) throws SQLException {
		try (PreparedStatement getCompteArbitreByTournoi = super.getConnexion().getConnection().prepareStatement(
				"SELECT Username_Compte_Arbitre,Mot_De_Passe_Compte_Arbitre FROM Tournoi WHERE Annee = ? AND Nom_Tournoi = ?")) {
			getCompteArbitreByTournoi.setInt(1, (Integer) value[0]);
			getCompteArbitreByTournoi.setString(2, (String) value[1]);
			ResultSet resultat = getCompteArbitreByTournoi.executeQuery();
			resultat.next();
			return new CompteArbitre(resultat.getString(super.getConstants().getUsername()), resultat.getString(super.getConstants().getMdp()));
		}
	}

	/**
	 * @return Le tournoi actuel s'il existe sinon un optional null
	 * @throws FausseDateException Vérifie la date système, si cette date est comprise entre la date de début et de fin du dernier tournoi actuel, alors cela renvoie le dernier tournoi sinon renvoie un optional null
	 */
	public Optional<Tournoi> getTournoiActuel() throws SQLException, FausseDateException {
		CustomDate c = new CustomDate(Timestamp.from(Instant.now()));
		try (PreparedStatement getCompteArbitreByTournoi = super.getConnexion().getConnection().prepareStatement(
				"SELECT * FROM Tournoi WHERE ? BETWEEN Date_Debut AND Date_Fin ")) {
			getCompteArbitreByTournoi.setTimestamp(1, c.toSQL());
			return getTournoi(getCompteArbitreByTournoi);
		}
	}

	private Optional<Tournoi> getTournoi(PreparedStatement getCompteArbitreByTournoi) throws SQLException, FausseDateException {
		ResultSet resultat = getCompteArbitreByTournoi.executeQuery();
		if (resultat.next()) {
			return Optional.of(createTournoi(resultat));
		}
		return Optional.empty();
	}

	/**
	 * Renvoie la liste de tournoi en fonction d'une année précise
	 *
	 */

	public List<Tournoi> getTournoiBySaison(Saison saison) throws SQLException, FausseDateException {
		try (PreparedStatement getTournoiBySaison = super.getConnexion().getConnection().prepareStatement(
				"Select * From Tournoi Where Annee = ?")) {
			getTournoiBySaison.setInt(1, saison.getAnnee());
			ResultSet resultat = getTournoiBySaison.executeQuery();
			List<Tournoi> sortie = new ArrayList<>();
			return getTournois(resultat, sortie);
		}
	}

	private List<Tournoi> getTournois(ResultSet resultat, List<Tournoi> sortie) throws SQLException, FausseDateException {
		while (resultat.next()) {
			sortie.add(createTournoi(resultat));
		}
		return sortie;
	}

	public List<Tournoi> getTournoiByNiveau(Niveau niveau) throws FausseDateException, SQLException {
		try (PreparedStatement getTournoiByNiveau = super.getConnexion().getConnection().prepareStatement(
				"SELECT *"
						+ "FROM Tournoi"
						+ "WHERE Libelle_Niveau = ?")) {
			getTournoiByNiveau.setString(1, niveau.name());
			ResultSet resultat = getTournoiByNiveau.executeQuery();
			List<Tournoi> sortie = new LinkedList<>();
			return getTournois(resultat, sortie);
		}
	}

	public List<Tournoi> getTournoiBetweenDate(CustomDate dateAvant, CustomDate dateApres) throws SQLException, FausseDateException {
		try (PreparedStatement getTournoiBetweenDate = super.getConnexion().getConnection().prepareStatement(
				"SELECT * "
						+ "FROM Tournoi "
						+ "WHERE (Date_Debut BETWEEN ? AND ?) "
						+ "OR (Date_Fin BETWEEN ? AND ?)"
						+ "OR (Date_Debut > ? AND Date_Fin < ?)")) {
			getTournoiBetweenDate.setTimestamp(1, dateAvant.toSQL());
			getTournoiBetweenDate.setTimestamp(2, dateApres.toSQL());
			getTournoiBetweenDate.setTimestamp(3, dateAvant.toSQL());
			getTournoiBetweenDate.setTimestamp(4, dateApres.toSQL());
			getTournoiBetweenDate.setTimestamp(5, dateAvant.toSQL());
			getTournoiBetweenDate.setTimestamp(6, dateApres.toSQL());
			ResultSet resultat = getTournoiBetweenDate.executeQuery();
			List<Tournoi> sortie = new ArrayList<>();
			return getTournois(resultat, sortie);
		}
    }


	@Override
	public String visualizeTable() throws SQLException, FausseDateException {
		StringBuilder s = new StringBuilder("_______________Tournoi_______________________" + "\n");
		List<Tournoi> l = this.getAll();
		for (Tournoi a : l) {
			s.append(a.toString()).append("\n");
		}
		s.append("\n\n\n");
		return s.toString();
	}

}
