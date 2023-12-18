package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modele.Appartenance;
import modele.Arbitrage;
import modele.Arbitre;
import modele.Tournoi;

public class DaoArbitrage implements Dao<Arbitrage,Object>{

	private Connexion connexion;
	private DaoArbitre daoarbitre;
	private DaoTournoi daotournoi;

	public DaoArbitrage(Connexion connexion) {
		this.connexion = connexion;
		this.daoarbitre=new DaoArbitre(connexion);
		this.daotournoi= new DaoTournoi(connexion);
	}

	/**
	 * Crée la table d'association arbitrage qui fait le lien entre les tournois et les arbitres
	 * @param connexion
	 * @throws SQLException
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Arbitrage("
				+ "Annee INT,"
				+ "Nom_tournoi VARCHAR(50),"
				+ "Id_arbitre INT,"
				+ "PRIMARY KEY(Annee, Nom_tournoi, Id_arbitre),"
				+ "FOREIGN KEY(Annee, Nom_tournoi) REFERENCES Tournoi(Annee, Nom_tournoi),"
				+ "FOREIGN KEY(Id_arbitre) REFERENCES Arbitre(Id_arbitre))";

		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
			System.out.println("Table 'Arbitrage' créée avec succès");
		}
	}

	/**
	 * Supprime la table arbitrage
	 * @param connexion
	 * @return
	 * @throws SQLException
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try(Statement deleteTable = connexion.getConnection().createStatement()){
			System.out.println("Table 'Arbitrage' supprimée avec succès");
			return deleteTable.execute("drop table Arbitrage");
		}
	}

	/**
	 * Renvoie toutes les associations de tournois et d'abitres existantes 
	 */
	@Override
	public List<Arbitrage> getAll() throws Exception {
		try(Statement getAll = connexion.getConnection().createStatement()){
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Arbitrage");
			List<Arbitrage> sortie = new ArrayList<>();
			while(resultat.next()) {
				Arbitrage arbitrage = new Arbitrage(
						daoarbitre.getById(resultat.getInt("Id_Arbitre")).get(),
						daotournoi.getById(
								resultat.getString("Nom_Tournoi"),
								resultat.getInt("Annee")).get());
				sortie.add(arbitrage);
			}
			return sortie;
		}
	}
	
	/**
	 * Renvoie une association précise d'un tournoi et d'un arbitre
	 * Les paramètres sont placés dans cet ordre : Id_Arbitre (INTEGER), Annee (INTEGER), Nom_tournoi (STRING)
	 */
	@Override
	public Optional<Arbitrage> getById(Object... id) throws Exception {
		try(PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Arbitrage WHERE Id_Arbitre = ? AND Annee = ? AND Nom_tournoi = ?")){
			getById.setInt(1, (Integer)id[0]);
			getById.setInt(2, (Integer)id[1]);
			getById.setString(3, (String) id[2]);
			ResultSet resultat = getById.executeQuery();
			Arbitrage arbitrage = null;
			if (resultat.next()) {
				arbitrage = new Arbitrage(
						daoarbitre.getById(resultat.getInt("Id_Arbitre")).get(),
						daotournoi.getById(
								resultat.getInt("Annee"),
								resultat.getString("Nom_Tournoi")).get());
				
			}
			return Optional.ofNullable(arbitrage);
		}
	}

	/**
	 * Ajoute une association d'un tournoi et d'un arbitre à partir d'un objet association
	 */
	@Override
	public boolean add(Arbitrage value) throws Exception {
		try(PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Arbitrage(Id_Arbitre,Annee,Nom_Tournoi) values (?,?,?)")){
			add.setInt(1, value.getArbitre().getId());
			add.setInt(2, value.getTournoi().getSaison().getAnnee());
			add.setString(3, value.getTournoi().getNom());;
			return add.execute();
		}
	}

	/**
	 * ne pas utiliser
	 */
	@Override
	public boolean update(Arbitrage value) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Supprime une association d'un tournoi et d'un arbitre
	 * Les paramètres sont placés dans cet ordre : Id_Arbitre (INTEGER), Annee (INTEGER), Nom_tournoi (STRING)
	 */
	@Override
	public boolean delete(Object... value) throws Exception {
		try(PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Arbitrage where Id_Arbitre = ? AND Annee = ? AND Nom_tournoi = ?")){
			delete.setInt(1,(Integer)value[0]);
			delete.setInt(2,(Integer)value[1]);
			delete.setString(3, (String) value[2]);
			return delete.execute();
		}
	}

	/**
	 * Renvoie la liste des arbitre pour un tournoi
	 * Les paramètres sont placés dans cet ordre : Nom_tournoi (STRING), Annee (INTEGER) 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List<Arbitre> getArbitreByTournoi(Object... value) throws Exception {
		try(PreparedStatement getArbitreByTournoi = connexion.getConnection().prepareStatement("SELECT * FROM Arbitrage where Nom_Tournoi = ? AND Annee = ?")){
			getArbitreByTournoi.setString(1, (String)value[0]);
			getArbitreByTournoi.setInt(2, (Integer)value[1]);
			ResultSet resultat = getArbitreByTournoi.executeQuery();
			List<Arbitre> sortie = new ArrayList<>();
			while(resultat.next()) {
				sortie.add(daoarbitre.getById(resultat.getInt("Id_Arbitre")).get());
			}
			return sortie;
		}
	}
	
	/**
	 * Renvoie tous les tournois pour un arbitre
	 * Les paramètres sont placés dans cet ordre : Id_Arbitre (INTEGER)
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List<Tournoi> getTournoiByArbitre(Object... value) throws Exception {
		try(PreparedStatement getTournoiByArbitre = connexion.getConnection().prepareStatement("SELECT * FROM Arbitrage where Id_Arbitre = ?")){
			getTournoiByArbitre.setInt(1, (Integer)value[1]);
			ResultSet resultat = getTournoiByArbitre.executeQuery();
			List<Tournoi> sortie = new ArrayList<>();
			while(resultat.next()) {
				sortie.add(daotournoi.getById(resultat.getString("Nom_tournoi"),resultat.getInt("Annee")).get());
			}
			return sortie;
		}
	}
	
	@Override
	public String visualizeTable() throws Exception {
		String s = "_______________Arbitrage_______________________" + "\n";
		List<Arbitrage> l = this.getAll();
		for(Arbitrage a : l) {
			s+=a.toString()+"\n";
		}
		s+="\n\n\n";
		return s;
	}

}

