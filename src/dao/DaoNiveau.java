package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modele.Arbitrage;
import modele.Niveau;

public class DaoNiveau implements Dao<Niveau,String>{

	private Connexion connexion;

	public DaoNiveau(Connexion connexion) {
		this.connexion = connexion;
	}

	/**
	 * Crée la table niveau
	 * @param connexion
	 * @throws SQLException
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Niveau("
				+"Libelle_Niveau VARCHAR(50),"
				+"Coefficient DECIMAL(2,1) NOT NULL,"
				+"PRIMARY KEY(Libelle_Niveau))";

		try(Statement createTable = connexion.getConnection().createStatement()){
			createTable.execute(createTableSql);
			System.out.println("Table 'Niveau' créée avec succès");
		}
	}

	/**
	 * Supprime la table niveau
	 * @param connexion
	 * @return
	 * @throws SQLException
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try(Statement deleteTable = connexion.getConnection().createStatement()){
			System.out.println("Table 'Niveau' supprimée avec succès");
			return deleteTable.execute("drop table Niveau");
		}
	}

	/**
	 * Renvoie tous les niveaux existants
	 */
	@Override
	public List<Niveau> getAll() throws Exception {
		try(Statement getAll = connexion.getConnection().createStatement()){
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Niveau");
			List<Niveau> sortie = new ArrayList<>();
			while(resultat.next()) {
				sortie.add(Niveau.valueOf(resultat.getString("Libelle_Niveau").toUpperCase()));
			}
			return sortie;
		}
	}

	/**
	 * Renvoie un niveau précis
	 * Les paramètres sont placés dans cet ordre : Libelle_Niveau (STRING)
	 */
	@Override
	public Optional<Niveau> getById(String... nom) throws Exception {
		try(PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Niveau WHERE Libelle_Niveau = ?")){
			getById.setString(1, nom[0]);
			ResultSet resultat = getById.executeQuery();
			Niveau niveau = null;
			if(resultat.next()) {
				niveau = Niveau.valueOf(resultat.getString("Libelle_Niveau"));
			
			}
			return Optional.ofNullable(niveau);
		}
	}
	
	/**
	 * Ajoute un niveau à la table Niveau à partir d'un objet Niveau
	 */
	@Override
	public boolean add(Niveau value) throws Exception {
		try(PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Niveau(Libelle_Niveau,Coefficient) values (?,?)")){
			add.setString(1, value.name());
			add.setFloat(2, value.getCoefficient());
			return add.execute();
		}
	}

	/**
	 * update un niveau à partir d'un objet niveau
	 */
	@Override
	public boolean update(Niveau value) throws Exception {
		try(PreparedStatement update = connexion.getConnection().prepareStatement(
				"UPDATE Niveau SET "
						+"Coefficient = ?"
						+"WHERE Libelle_Niveau = ?")){
			update.setFloat(1, value.getCoefficient());
			update.setString(2, value.getNom());
			return update.execute();
		}
	}

	/**
	 * supprime un niveau 
	 * Les paramètres sont placés dans cet ordre : Libelle_Niveau (STRING)
	 */
	@Override
	public boolean delete(String... value) throws Exception {
		try(PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Niveau where Libelle_Niveau = ?")){
			delete.setString(1,value[0]);
			return delete.execute();
		}
	}
	
	@Override
	public String visualizeTable() throws Exception {
		String s = "_______________Niveau_______________________" + "\n";
		List<Niveau> l = this.getAll();
		for(Niveau a : l) {
			s+=a.toString()+"\n";
		}
		s+="\n\n\n";
		return s;
	}
}

