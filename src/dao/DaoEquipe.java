package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modele.Equipe;
import modele.Country;

public class DaoEquipe implements Dao<Equipe,String>{

	private Connexion connexion;

	public DaoEquipe(Connexion connexion) {
		this.connexion = connexion;
	}

	/**
	 * Crée la table equipe
	 * @param connexion
	 * @throws SQLException
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Equipe (" +
				"Nom_Equipe VARCHAR(50)," +
				"Pays_Equipe VARCHAR(50)," +
				"World_rank INT," +
				"PRIMARY KEY (Nom_Equipe))";
		try(Statement createTable = connexion.getConnection().createStatement()){
			createTable.execute(createTableSql);
			System.out.println("Table 'Equipe' créée avec succès");
		}
	}

	/**
	 * Supprime la table Equipe
	 * @param connexion
	 * @return
	 * @throws SQLException
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try(Statement deleteTable= connexion.getConnection().createStatement()){
			System.out.println("Table 'Equipe' supprimée avec succès");
			return deleteTable.execute("drop table Equipe");
		}
	}

	/**
	 * Renvoie toutes les équipes existantes 
	 */
	@Override
	public List<Equipe> getAll() throws Exception {
		try(Statement getAll = connexion.getConnection().createStatement()){
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Equipe");
			List<Equipe> sortie = new ArrayList<>();
			while(resultat.next()) {
				Equipe equipe = new Equipe(
						resultat.getString("Nom_Equipe"),
						Country.valueOf(resultat.getString("Pays_Equipe")));
				equipe.setPoint(resultat.getInt("World_rank"));
				sortie.add(equipe);
			}
			return sortie;
		}
	}

	/**
	 * Renvoie une équipe précise
	 * Les paramètres sont placés dans cet ordre : Nom_Equipe (STRING)
	 */
	@Override
	public Equipe getById(String... nom) throws Exception {
		try(PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Equipe WHERE Nom_Equipe = ?")){
			getById.setString(1, nom[0]);
			ResultSet resultat = getById.executeQuery();
			if (resultat.next()) {
				Equipe equipe = new Equipe(
						resultat.getString("Nom_Equipe"),
						Country.valueOf(resultat.getString("Pays_Equipe")));
				equipe.setPoint(resultat.getInt("World_rank"));
				return equipe;
			}
			throw new Exception("Equipe non trouvée");
		}
	}

	/**
	 * Ajoute une équipe à la table équipe à partir d'un objet équipe
	 */
	@Override
	public boolean add(Equipe value) throws Exception {
		try(PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Equipe(Nom_Equipe,World_rank,Pays_Equipe) values (?,?,?)")){
			add.setString(1, value.getNom());
			add.setInt(2, value.getPoint());
			add.setString(3, value.getPays().name());
			return add.execute();
		}
	}

	/**
	 * update une équipe à partir d'un objet équipe
	 */
	@Override
	public boolean update(Equipe value) throws Exception {
		try(PreparedStatement update = connexion.getConnection().prepareStatement(
				"UPDATE Equipe SET "
						+"World_rank = ?, "
						+"Pays_Equipe = ?"
						+"WHERE Nom_Equipe = ?")) {
			update.setInt(1, value.getPoint());
			update.setString(2, value.getPays().getNom());
			update.setString(3, value.getNom());
			return update.execute();
		}
	}

	/**
	 * Supprime une équipe de la table equipe
	 * Les paramètres sont placés dans cet ordre : Nom_Equipe (STRING)
	 */
	@Override
	public boolean delete(String... value) throws Exception {
		try(PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Equipe where Nom_Equipe = ?")){
			delete.setString(1,value[0]);
			return delete.execute();
		}
	}
}
