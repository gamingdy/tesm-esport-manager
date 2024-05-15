package dao;

import modele.Arbitre;
import modele.Saison;
import modele.Selection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static dao.DaoArbitre.getArbitres;
import static dao.DaoSaison.getSaisons;

public class DaoSelection extends SuperDao implements Dao<Selection, Object> {
	
	private final DaoArbitre daoarbitre;
	private final DaoSaison daosaison;

	public DaoSelection(Connexion connexion) {
        super(connexion);
		this.daoarbitre = new DaoArbitre(connexion);
		this.daosaison = new DaoSaison(connexion);
	}

	/**
	 * Crée la table d'association Selection qui fait la liaison entre les arbitres et la saison
	 *
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Selection("
				+ "Nom VARCHAR(50), "
				+ "Prenom VARCHAR(50), "
				+ "Telephone VARCHAR(50), "
				+ "Annee INT, "
				+ "PRIMARY KEY(Nom, Prenom, Telephone, Annee), "
				+ "FOREIGN KEY(Nom,Prenom,Telephone) REFERENCES Arbitre(Nom,Prenom,Telephone), "
				+ "FOREIGN KEY(Annee) REFERENCES Saison(Annee))";

		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
		}

	}

	/**
	 * Supprime la table d'association Selection
	 *
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			return deleteTable.execute("drop table Selection");
		}
	}

	/**
	 * Renvoie toutes les associations d'arbitres et de saisons
	 */
	@Override
	public List<Selection> getAll() throws SQLException {
		try (Statement getAll = super.getConnexion().getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Selection");
			List<Selection> sortie = new ArrayList<>();
			while (resultat.next()) {
				Optional<Arbitre> arbitre = daoarbitre.getById(resultat.getString(super.getConstants().getNom()), resultat.getString(super.getConstants().getPrenom()), resultat.getString(super.getConstants().getTelephone()));
				Optional<Saison> saison = daosaison.getById(resultat.getInt(super.getConstants().getAnnee()));
				if (arbitre.isPresent() && saison.isPresent()) {
					sortie.add(new Selection(arbitre.get(),saison.get()));
				}
			}
			return sortie;
		}
	}

	/**
	 * Renvoie une association d'une saison et d'un arbitre
	 * Les paramètres sont placés dans cet ordre : Nom (STRING), Prenom (STRING), Telephone (INTEGER), Annee (INTEGER)
	 */
	@Override
	public Optional<Selection> getById(Object... id) throws SQLException {
		try (PreparedStatement getById = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Selection WHERE Nom = ? AND Prenom = ? AND Telephone = ? AND Annee = ?")) {
			getById.setString(1, (String) id[0]);
			getById.setString(2, (String) id[1]);
			getById.setInt(3, (Integer) id[2]);
			getById.setInt(4, (Integer) id[3]);
			ResultSet resultat = getById.executeQuery();
			if (resultat.next()) {
				Optional<Arbitre> arbitre = daoarbitre.getById(resultat.getString(super.getConstants().getNom()), resultat.getString(super.getConstants().getPrenom()), resultat.getString(super.getConstants().getTelephone()));
				Optional<Saison> saison = daosaison.getById(resultat.getInt(super.getConstants().getAnnee()));
				if (arbitre.isPresent() && saison.isPresent()) {
					return Optional.of(new Selection(arbitre.get(),saison.get()));
				}
				return  Optional.empty();
			}
			return Optional.empty();
		}
	}

	/**
	 * Permet à partir d'un objet Selection, de lier une saison à un arbitre
	 */
	@Override
	public boolean add(Selection value) throws SQLException {
		try (PreparedStatement add = super.getConnexion().getConnection().prepareStatement(
				"INSERT INTO Selection(Nom,Prenom,Telephone,Annee) values (?,?,?,?)")) {
			add.setString(1, value.getArbitre().getNom());
			add.setString(2, value.getArbitre().getPrenom());
			add.setString(3, value.getArbitre().getNumeroTelephone());
			add.setInt(4, value.getSaison().getAnnee());
			return add.execute();
		}
	}

	/**
	 * Ne pas utiliser
	 */
	@Override
	public boolean update(Selection value) throws SQLException {
		return false;
	}

	/**
	 * Supprime à partir de la clé primaire, l'association d'un arbitre et d'une saison
	 * Les paramètres sont placés dans cet ordre : Nom (STRING), Prenom (STRING), Telephone (INTEGER), Annee (INTEGER)
	 */
	@Override
	public boolean delete(Object... value) throws SQLException {
		try (PreparedStatement delete = super.getConnexion().getConnection().prepareStatement(
				"DELETE FROM Selection where Nom = ? AND Prenom = ? AND Telephone = ? AND Annee = ?")) {
			delete.setString(1, (String) value[0]);
			delete.setString(2, (String) value[1]);
			delete.setString(3, (String) value[2]);
			delete.setInt(4, (Integer) value[3]);
			return delete.execute();
		}
	}

	/**
	 * Renvoie tous les arbitres d'une saison
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER)
	 *
	 */
	public List<Arbitre> getArbitreBySaison(Object... value) throws SQLException {
		try (PreparedStatement getArbitreBySaison = super.getConnexion().getConnection().prepareStatement(
				"SELECT * FROM Selection WHERE Annee = ?")) {
			getArbitreBySaison.setInt(1, (Integer) value[0]);
			return getArbitres(getArbitreBySaison, daoarbitre);
		}
	}


	/**
	 * Renvoie tous les saisons d'un arbitre
	 * Les paramètres sont placés dans cet ordre : Nom Arbitre (STRING) , Prenom Arbitre (STRING) , Telephone (STRING)
	 *
	 */
	public List<Saison> getSaisonByArbitre(Object... value) throws SQLException {
		try (PreparedStatement getSaisonByArbitre = super.getConnexion().getConnection().prepareStatement(
				"SELECT * FROM Selection WHERE Nom = ? AND Prenom = ? AND Telephone = ?")) {
			getSaisonByArbitre.setString(1, (String) value[0]);
			getSaisonByArbitre.setString(2, (String) value[1]);
			getSaisonByArbitre.setString(3, (String) value[2]);
			ResultSet resultat = getSaisonByArbitre.executeQuery();
			return getSaisons(resultat,daosaison);
		}
	}



	@Override
	public String visualizeTable() throws SQLException {
		StringBuilder s = new StringBuilder("_______________Selection_______________________" + "\n");
		List<Selection> l = this.getAll();
		for (Selection a : l) {
			s.append(a.toString()).append("\n");
		}
		s.append("\n\n\n");
		return s.toString();
	}

}
