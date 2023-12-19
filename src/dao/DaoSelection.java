package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modele.Arbitrage;
import modele.Arbitre;
import modele.Saison;
import modele.Selection;

public class DaoSelection implements Dao<Selection,Object>{

	private Connexion connexion;
	private DaoArbitre daoarbitre;
	private DaoSaison daosaison;

	public DaoSelection(Connexion connexion) {
		this.connexion = connexion;
		this.daoarbitre = new DaoArbitre(connexion);
		this.daosaison = new DaoSaison(connexion);
	}

	/**
	 * Crée la table d'association Selection qui fait la liaison entre les arbitres et la saison
	 * @param connexion
	 * @throws SQLException
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Selection("
				+ "Id_Arbitre INT,"
				+ "Annee INT,"
				+ "PRIMARY KEY(Id_Arbitre, Annee),"
				+ "FOREIGN KEY(Id_Arbitre) REFERENCES Arbitre(Id_Arbitre),"
				+ "FOREIGN KEY(Annee) REFERENCES Saison(Annee))";

		try(Statement createTable = connexion.getConnection().createStatement()){
			createTable.execute(createTableSql);
			System.out.println("Table 'Selection' créée avec succès");
		}

	}

	/**
	 * Supprime la table d'association Selection
	 * @param connexion
	 * @return
	 * @throws SQLException
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try(Statement deleteTable = connexion.getConnection().createStatement()){
			System.out.println("Table 'Selection' supprimée avec succès");
			return deleteTable.execute("drop table Selection");
		}
	}

	/**
	 * Renvoie toutes les associations d'arbitres et de saisons 
	 */
	@Override
	public List<Selection> getAll() throws Exception {
		try(Statement getAll = connexion.getConnection().createStatement()){
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Selection");
			List<Selection> sortie = new ArrayList<>();
			Selection selection = null;
			while(resultat.next()) {
				selection = new Selection(
						daoarbitre.getById(resultat.getInt("Id_Arbitre")).get(),
						daosaison.getById(resultat.getInt("Annee")).get());
				sortie.add(selection);
			}
			return sortie;
		}
	}

	/**
	 * Renvoie une association d'une saison et d'un arbitre
	 * Les paramètres sont placés dans cet ordre : Id_Arbitre (INTEGER), Annee (INTEGER) 
	 */
	@Override
	public Optional<Selection> getById(Object... id) throws Exception {
		try(PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Selection WHERE Id_Arbitre = ? AND Annee = ?")){
			getById.setInt(1, (Integer)id[0]);
			getById.setInt(1, (Integer)id[1]);
			ResultSet resultat = getById.executeQuery();
			Selection selection = null;
			if(resultat.next()) {
				selection = new Selection(
						daoarbitre.getById(resultat.getInt("Id_Arbitre")).get(),
						daosaison.getById(resultat.getInt("Annee")).get());
				
			}
			return Optional.ofNullable(selection);
		}
	}

	/**
	 * Permet à partir d'un objet Selection, de lier une saison à un arbitre
	 */
	@Override
	public boolean add(Selection value) throws Exception {
		try(PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Selection(Id_Arbitre,Annee) values (?,?)")) {
			add.setInt(1, value.getArbitre().getId());
			add.setInt(2, value.getSaison().getAnnee());
			return add.execute();
		}
	}
	/**
	 * Ne pas utiliser
	 */
	@Override
	public boolean update(Selection value) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Supprime à partir de la clé primaire, l'association d'un arbitre et d'une saison
	 * Les paramètres sont placés dans cet ordre : Id_Arbitre (INTEGER), Annee (INTEGER) 
	 */
	@Override
	public boolean delete(Object... value) throws Exception {
		try(PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Selection where Id_Arbitre = ? AND Annee = ?")){
			delete.setInt(1,(Integer)value[0]);
			delete.setInt(2,(Integer)value[1]);
			return delete.execute();
		}
	}
	
	/**
	 * Renvoie tous les arbitres d'une saison
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER)
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List<Arbitre> getArbitreBySaison(Object...  value) throws Exception {
		try(PreparedStatement getArbitreBySaison = connexion.getConnection().prepareStatement(
				"SELECT * FROM Selection WHERE Annee = ?")) {
			getArbitreBySaison.setInt(1, (Integer)value[0]);
			ResultSet resultat = getArbitreBySaison.executeQuery();
			List<Arbitre> sortie = new ArrayList<>();
			while(resultat.next()) {
				sortie.add(daoarbitre.getById(resultat.getInt("Id_Arbitre")).get());
			}
			return sortie;
		}
	}
	

	/**
	 * Renvoie tous les saison d'un arbitre
	 * Les paramètres sont placés dans cet ordre : Id_Arbitre (INTEGER)
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List<Saison> getSaisonByArbitre(Object...  value) throws Exception {
		try(PreparedStatement getSaisonByArbitre = connexion.getConnection().prepareStatement(
				"SELECT * FROM Selection WHERE Id_Arbitre = ?")) {
			getSaisonByArbitre.setInt(1, (Integer)value[0]);
			ResultSet resultat = getSaisonByArbitre.executeQuery();
			List<Saison> sortie = new ArrayList<>();
			while(resultat.next()) {
				sortie.add(daosaison.getById(resultat.getInt("Annee")).get());
			}
			return sortie;
		}
	}
	
	@Override
	public String visualizeTable() throws Exception {
		String s = "_______________Selection_______________________" + "\n";
		List<Selection> l = this.getAll();
		for(Selection a : l) {
			s+=a.toString()+"\n";
		}
		s+="\n\n\n";
		return s;
	}

}
