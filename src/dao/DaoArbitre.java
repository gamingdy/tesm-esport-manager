package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modele.Arbitre;
import modele.Saison;

public class DaoArbitre implements Dao<Arbitre,Integer> {

	private Connexion connexion;

	public DaoArbitre(Connexion connexion) {
		this.connexion = connexion;
	}

	/**
	 * Crée la table Arbitre
	 * @param connexion
	 * @throws SQLException
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Arbitre("
				+"Id_Arbitre INT NOT NULL, "
				+"Nom VARCHAR(50),"
				+"Prenom VARCHAR(50),"
				+"PRIMARY KEY(Id_Arbitre))";

		try(Statement createTable= connexion.getConnection().createStatement()){
			createTable.execute(createTableSql);
			System.out.println("Table 'Arbitre' créée avec succès");
		}
	}

	/**
	 * Supprime la table arbitre
	 * @param connexion
	 * @return
	 * @throws SQLException
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try(Statement deleteTable = connexion.getConnection().createStatement()){
			System.out.println("Table 'Arbitre' supprimée avec succès");
			return deleteTable.execute("drop table Arbitre");
		}
	}

	/**
	 * renvoie tous les arbitres existants
	 */
	@Override
	public List<Arbitre> getAll() throws Exception {
		try(Statement getAll = connexion.getConnection().createStatement()){
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Arbitre");
			List<Arbitre> sortie = new ArrayList<>();
			while(resultat.next()) {
				Arbitre arbitre = new Arbitre(
						resultat.getString("Nom"),
						resultat.getString("Prenom"));
				arbitre.setId(resultat.getInt("Id_Arbitre"));
				sortie.add(arbitre);
			}
			return sortie;
		}
	}

	/**
	 * renvoie un arbitre précis
	 * Les paramètres sont placés dans cet ordre : Id_Arbitre (INTEGER)
	 */
	@Override
	public Optional<Arbitre> getById(Integer... id) throws Exception {
		try(PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Arbitre WHERE Id_Arbitre = ?")){
			getById.setInt(1, id[0]);
			ResultSet resultat = getById.executeQuery();
			Arbitre arbitre = null;
			if (resultat.next()) {
				arbitre = new Arbitre(
						resultat.getString("Nom"),
						resultat.getString("Prenom"));
				arbitre.setId(resultat.getInt("Id_Arbitre"));
				
			}
			return Optional.ofNullable(arbitre);
			
		}
	}
	
	/**
	 * Ajoute un arbitre à la table arbitre à partir d'un objet arbitre
	 */
	@Override
	public boolean add(Arbitre value) throws Exception {
		try(PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Arbitre(Id_Arbitre,Nom,Prenom) values (?,?,?)")){
			add.setInt(1, value.getId());
			add.setString(2, value.getNom());
			add.setString(3, value.getPrenom());
			return add.execute();
		}
	}

	/**
	 * update un arbitre à partir d'un objet arbitre
	 */
	@Override
	public boolean update(Arbitre value) throws Exception {
		try(PreparedStatement update = connexion.getConnection().prepareStatement(
				"UPDATE Arbitre SET "
						+"Nom = ? , "
						+"Prenom = ? "
						+"WHERE Id_Arbitre = ?")){
			update.setString(1, value.getNom());
			update.setString(2, value.getPrenom());
			update.setInt(3, value.getId());
			return update.execute();
		}		
	}

	/**
	 * Supprime un arbitre de la table arbitre
	 * Les paramètres sont placés dans cet ordre : Id_Arbitre (INTEGER)
	 */
	@Override
	public boolean delete(Integer... value) throws Exception {
		try(PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Arbitre WHERE Id_Arbitre = ?")){
			delete.setInt(1,value[0]);
			return delete.execute();
		}
	}
	
	/**
	 * Renvoie le dernier id d'arbitre ajouté à la table
	 * @return
	 * @throws SQLException
	 */
	public Integer getLastId() throws SQLException {
		try(PreparedStatement getLastId = connexion.getConnection().prepareStatement(
				"SELECT Id_Arbitre "
               + "FROM Arbitre "
               + "ORDER BY Id_Arbitre DESC "
               + "FETCH FIRST 1 ROW ONLY")) {
			ResultSet resultat = getLastId.executeQuery();
			Integer sortie = null;
			if (resultat.next()) {
				sortie = resultat.getInt("Id_Arbitre");
			}
			return sortie;
		}
	}

}
