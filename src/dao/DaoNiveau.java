package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.EquipeComplete;
import modele.Equipe;
import modele.Joueur;
import modele.Niveau;

public class DaoNiveau implements Dao<Niveau,String>{
	
	private Connexion connexion;
	
	public DaoNiveau(Connexion connexion) {
		this.connexion = connexion;
	}

	@Override
	public void createTable() throws SQLException {
		String createTableSql = "CREATE TABLE Niveau("
				   +"Libelle_Niveau VARCHAR(50),"
				   +"Coefficient DECIMAL(2,1) NOT NULL,"
				   +"PRIMARY KEY(Libelle_Niveau)";

		Statement createTable;
		
		createTable = connexion.getConnexion().createStatement();
		createTable.execute(createTableSql);
        System.out.println("Table 'Niveau' créée avec succès");
	}

	@Override
	public boolean dropTable() throws SQLException {
		Statement deleteTable;
		deleteTable = connexion.getConnexion().createStatement();
		return deleteTable.execute("drop table Niveau");
	}

	@Override
	public List<Niveau> getAll() throws Exception {
		Statement getAll = connexion.getConnexion().createStatement();
		ResultSet resultat = getAll.executeQuery("SELECT * FROM Niveau");
		List<Niveau> sortie = new ArrayList<>();
		while(resultat.next()) {
			sortie.add(Niveau.valueOf(resultat.getString("Libelle_Niveau")));
		}
		return sortie;
	}

	@Override
	public Niveau getById(String... nom) throws Exception {
		PreparedStatement getById = connexion.getConnexion().prepareStatement("SELECT * FROM Niveau WHERE Libelle_Niveau = ?");
		getById.setString(1, nom[0]);
		ResultSet resultat = getById.executeQuery();
		Niveau niveau = Niveau.valueOf(resultat.getString("Nom_Equipe"));
		return niveau;
	}

	@Override
	public boolean add(Niveau value) throws Exception {
		PreparedStatement add = connexion.getConnexion().prepareStatement(
				"INSERT INTO Niveau(Libelle_Niveau,Coefficient) values (?,?)");
		add.setString(1, value.getNom());
		add.setFloat(2, value.getCoefficient());
		return add.execute();
	}

	@Override
	public boolean update(Niveau value) throws Exception {
		PreparedStatement update = connexion.getConnexion().prepareStatement(
				"UPDATE Niveau SET "
				+"Coefficient = ? "
				+"WHERE Libelle_Niveau = ?");
		update.setFloat(1, value.getCoefficient());
		update.setString(2, value.getNom());
		return update.execute();
	}

	@Override
	public boolean delete(String... value) throws Exception {
		PreparedStatement delete = connexion.getConnexion().prepareStatement(
				"DELETE FROM Niveau where Libelle_Niveau = ?");
		delete.setString(1,value[0]);
		return delete.execute();
	}
}
