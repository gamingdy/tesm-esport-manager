package dao;

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

public class DaoPartie implements Dao<Partie, Integer> {

	private Connexion connexion;
	private DaoMatche daomatche;


	public DaoPartie(Connexion connexion) {
		this.connexion = connexion;
		this.daomatche = new DaoMatche(connexion);

	}

	/**
	 * Crée la table partie
	 *
	 * @param connexion
	 * @throws SQLException
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
			System.out.println("Table 'Partie' créée avec succès");
		}
	}

	/**
	 * Supprime la table poule
	 *
	 * @param connexion
	 * @return
	 * @throws SQLException
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			System.out.println("Table 'Partie' supprimée avec succès");
			return deleteTable.execute("drop table Partie");
		}
	}

	/**
	 * Renvoie toutes les parties existantes
	 */
	@Override
	public List<Partie> getAll() throws Exception {
		try (Statement getAll = connexion.getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Partie");
			List<Partie> sortie = new ArrayList<>();
			while (resultat.next()) {
				Partie partie = new Partie(
						daomatche.getById(resultat.getInt("Id_Match")).get(),
						resultat.getInt("Id_Partie"));
				Optional<Equipe> vainqueur = FactoryDAO.getDaoEquipe(Connexion.getConnexion()).getById(resultat.getString("Nom_Equipe"));
				if (vainqueur.isPresent()) {
					partie.setVainqueur(vainqueur.get());
				}
				sortie.add(partie);
			}
			return sortie;
		}
	}

	/**
	 * Renvoie une partie précise
	 * Les paramètres sont placés dans cet ordre : Id_Match (INTEGER), Numero_Partie (INTEGER)
	 */
	@Override
	public Optional<Partie> getById(Integer... id) throws Exception {
		try (PreparedStatement getById = connexion.getConnection().prepareStatement(""
				+ "SELECT * "
				+ "FROM Partie "
				+ "WHERE Id_Match = ? "
				+ "AND Id_Partie = ?")) {
			getById.setInt(1, id[0]);
			getById.setInt(2, id[1]);
			ResultSet resultat = getById.executeQuery();
			Partie partie = null;
			if (resultat.next()) {
				partie = new Partie(
						daomatche.getById(resultat.getInt("Id_Match")).get(),
						resultat.getInt("Id_Partie")
				);
				Optional<Equipe> vainqueur = FactoryDAO.getDaoEquipe(Connexion.getConnexion()).getById(resultat.getString("Nom_Equipe"));
				if (vainqueur.isPresent()) {
					partie.setVainqueur(vainqueur.get());
				}
			}
			return Optional.ofNullable(partie);
		}
	}

	/**
	 * Ajoute une partie à la table partie à partir d'un objet partie
	 */
	@Override
	public boolean add(Partie value) throws Exception {
		try (PreparedStatement add = connexion.getConnection().prepareStatement(
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
	public boolean update(Partie value) throws Exception {
		try (PreparedStatement update = connexion.getConnection().prepareStatement(
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
	public boolean delete(Integer... value) throws Exception {
		try (PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Partie where Id_Match = ? AND Id_Partie = ?")) {
			delete.setInt(1, value[0]);
			delete.setInt(2, value[1]);
			return delete.execute();
		}
	}

	public List<Partie> getPartieByMatche(Matche matche) throws IllegalArgumentException, SQLException, Exception {
		try (PreparedStatement getPartieByMatche = connexion.getConnection().prepareStatement(
				"SELECT * "
						+ "FROM Partie "
						+ "WHERE Id_Match = ?")) {
			getPartieByMatche.setInt(1, matche.getId());
			ResultSet resultat = getPartieByMatche.executeQuery();
			List<Partie> sortie = new LinkedList<>();
			while (resultat.next()) {
				Partie partie = new Partie(
						FactoryDAO.getDaoMatche(connexion).getById(resultat.getInt("Id_Match")).get(),
						resultat.getInt("Id_Partie"));
				Optional<Equipe> vainqueur = FactoryDAO.getDaoEquipe(Connexion.getConnexion()).getById(resultat.getString("Nom_Equipe"));
				if (vainqueur.isPresent()) {
					partie.setVainqueur(vainqueur.get());
				}
				sortie.add(partie);
			}
			return sortie;
		}
	}

	@Override
	public String visualizeTable() throws Exception {
		String s = "_______________Partie_______________________" + "\n";
		List<Partie> l = this.getAll();
		for (Partie a : l) {
			s += a.toString() + "\n";
		}
		s += "\n\n\n";
		return s;
	}
}


