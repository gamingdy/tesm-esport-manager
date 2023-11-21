package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modele.Arbitre;

public class DaoArbitre implements Dao<Arbitre,Integer> {

private Connexion connexion;
	
	public DaoArbitre(Connexion connexion) {
		this.connexion = connexion;
	}

	@Override
	public void createTable() throws SQLException {
		String createTableSql = "CREATE TABLE Arbitre("
				   +"Id_Arbitre INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
				   +"Nom VARCHAR(50),"
				   +"Prénom VARCHAR(50),"
				   +"PRIMARY KEY(Id_Arbitre)";

		Statement createTable;
		
		createTable = connexion.getConnexion().createStatement();
		createTable.execute(createTableSql);
        System.out.println("Table 'Arbitre' créée avec succès");
	}

	@Override
	public boolean dropTable() throws SQLException {
		Statement deleteTable;
		deleteTable = connexion.getConnexion().createStatement();
		return deleteTable.execute("drop table Arbitre");
	}

	@Override
	public List<Arbitre> getAll() throws Exception {
		Statement getAll = connexion.getConnexion().createStatement();
		ResultSet resultat = getAll.executeQuery("SELECT * FROM Arbitre");
		List<Arbitre> sortie = new ArrayList<>();
		while(resultat.next()) {
			Arbitre arbitre = new Arbitre(
					resultat.getString("Nom"),
					resultat.getString("Prénom"));
			arbitre.setId(resultat.getInt("Id_Arbitre"));
			sortie.add(arbitre);
		}
		return sortie;
	}

	@Override
	public Arbitre getById(Integer... id) throws Exception {
		PreparedStatement getById = connexion.getConnexion().prepareStatement("SELECT * FROM Saison WHERE Annee = ?");
		getById.setInt(1, id[0]);
		ResultSet resultat = getById.executeQuery();
		Arbitre arbitre = new Arbitre(
				resultat.getString("Nom"),
				resultat.getString("Prénom"));
		arbitre.setId(resultat.getInt("Id_Arbitre"));
		return arbitre;
	}

	@Override
	public boolean add(Arbitre value) throws Exception {
		PreparedStatement add = connexion.getConnexion().prepareStatement(
				"INSERT INTO Arbitre(Nom,Prenom) values (?,?)");
		add.setString(1, value.getNom());
		add.setString(2, value.getPrenom());
		return add.execute();
	}

	@Override
	public boolean update(Arbitre value) throws Exception {
		PreparedStatement update = connexion.getConnexion().prepareStatement(
				"UPDATE Arbitre SET "
				+"Nom = ? "
				+"Prénom = ? "
				+"WHERE Id_Arbitre = ?");
		update.setString(1, value.getNom());
		update.setString(2, value.getPrenom());
		update.setInt(3, value.getId());
		return update.execute();		
	}

	@Override
	public boolean delete(Integer... value) throws Exception {
		PreparedStatement delete = connexion.getConnexion().prepareStatement(
				"DELETE FROM Arbitre where Id_Arbitre = ?");
		delete.setInt(1,value[0]);
		return delete.execute();
	}

}
