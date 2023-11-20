package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List; 

import modele.Joueur;
import modele.Saison;

public class DaoSaison implements Dao<Saison,Integer>{
	
	private Connexion connexion;
	
	public DaoSaison(Connexion connexion) {
		this.connexion = connexion;
	}

	@Override
	public void createTable() throws SQLException {
		String createTableSql = "CREATE TABLE Saison("
				   +"Annee INT,"
				   +"PRIMARY KEY(Annee)";
		Statement createTable;
		
		createTable = connexion.getConnexion().createStatement();
		createTable.execute(createTableSql);
        System.out.println("Table 'Saison' créée avec succès");
	}

	@Override
	public boolean dropTable() throws SQLException {
		Statement deleteTable;
		deleteTable = connexion.getConnexion().createStatement();
		return deleteTable.execute("drop table Saison");
	}

	@Override
	public List<Saison> getAll() throws Exception {
		Statement getAll = connexion.getConnexion().createStatement();
		ResultSet resultat = getAll.executeQuery("SELECT * FROM Saison");
		List<Saison> sortie = new ArrayList<>();
		while(resultat.next()) {
			Saison saison = new Saison(
					resultat.getInt("Annee"));
			sortie.add(saison);
		}
		return sortie;
	}

	@Override
	public Saison getById(Integer... id) throws Exception {
		PreparedStatement getById = connexion.getConnexion().prepareStatement("SELECT * FROM Saison WHERE Annee = ?");
		getById.setInt(1, id[0]);
		ResultSet resultat = getById.executeQuery();
		Saison saison = new Saison(
				resultat.getInt("Annee"));
		return saison;
	}

	@Override
	public boolean add(Saison value) throws Exception {
		PreparedStatement add = connexion.getConnexion().prepareStatement(
				"INSERT INTO Saison(Annee) values (?)");
		add.setInt(1, value.getAnnee());
		return add.execute();
	}

	@Override
	public boolean update(Saison value) throws Exception {
		return false;		
	}

	@Override
	public boolean delete(Integer... value) throws Exception {
		PreparedStatement delete = connexion.getConnexion().prepareStatement(
				"DELETE FROM Saison where Annee = ?");
		delete.setInt(1,value[0]);
		return delete.execute();
	}
	
}
