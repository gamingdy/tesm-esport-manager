package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.Arbitrage;
import modele.Arbitre;
import modele.Inscription;
import modele.Selection;

public class DaoArbitrage implements Dao<Arbitrage,Object>{

	private Connexion connexion;
	private DaoArbitre daoarbitre;
	private DaoTournoi daotournoi;
	
	public DaoArbitrage(Connexion connexion) {
		this.connexion = connexion;
		this.daoarbitre=new DaoArbitre(connexion);
		this.daotournoi= new DaoTournoi(connexion);
	}
	
	@Override
	public void createTable() throws SQLException {
		String createTableSql = "CREATE TABLE Arbitrage("
				   +"Annee INT,"
				   +"Nom_tournoi VARCHAR(50),"
				   +"Id_arbitre INT,"
				   +"PRIMARY KEY(Annee, Nom_tournoi, Id_arbitre),"
				   +"FOREIGN KEY(Annee, Nom_tournoi) REFERENCES Tournoi(Annee, Nom_tournoi),"
				   +"FOREIGN KEY(Id_arbitre) REFERENCES Arbitre(Id_arbitre)";

		
		Statement createTable;
		
		createTable = connexion.getConnexion().createStatement();
		createTable.execute(createTableSql);
		System.out.println("Table 'Arbitrage' créée avec succès");
		
	}

	@Override
	public boolean dropTable() throws SQLException {
		Statement deleteTable;
		deleteTable = connexion.getConnexion().createStatement();
		return deleteTable.execute("drop table Selection");
	}

	@Override
	public List<Arbitrage> getAll() throws Exception {
		Statement getAll = connexion.getConnexion().createStatement();
		ResultSet resultat = getAll.executeQuery("SELECT * FROM Arbitrage");
		List<Arbitrage> sortie = new ArrayList<>();
		while(resultat.next()) {
			Arbitrage arbitrage = new Arbitrage(
					daoarbitre.getById(resultat.getInt("Id_Arbitre")),
					daotournoi.getById(
					resultat.getInt("Annee"),
					resultat.getString("Nom_Tournoi")));
			sortie.add(arbitrage);
		}
		return sortie;
	}

	@Override
	public Arbitrage getById(Object... id) throws Exception {
		PreparedStatement getById = connexion.getConnexion().prepareStatement("SELECT * FROM Arbitrage WHERE Id_Arbitre = ? AND Annee = ? AND Nom_tournoi = ?");
		getById.setInt(1, (Integer)id[0]);
		getById.setInt(2, (Integer)id[1]);
		getById.setString(3, (String) id[2]);
		ResultSet resultat = getById.executeQuery();
		Arbitrage arbitrage = new Arbitrage(
				daoarbitre.getById(resultat.getInt("Id_Arbitre")),
				daotournoi.getById(
				resultat.getInt("Annee"),
				resultat.getString("Nom_Tournoi")));
		return arbitrage;
	}

	@Override
	public boolean add(Arbitrage value) throws Exception {
		PreparedStatement add = connexion.getConnexion().prepareStatement(
				"INSERT INTO Arbitrage(Id_Arbitre,Annee,Nom_Tournoi) values (?,?,?)");
		add.setInt(1, value.getArbitre().getId());
		add.setInt(2, value.getTournoi().getSaison().getAnnee());
		add.setString(3, value.getTournoi().getNom());;
		return add.execute();
	}

	@Override
	public boolean update(Arbitrage value) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object... value) throws Exception {
		PreparedStatement delete = connexion.getConnexion().prepareStatement(
				"DELETE FROM Arbitrage where Id_Arbitre = ? AND Annee = ? AND Nom_tournoi = ?");
		delete.setInt(1,(Integer)value[0]);
		delete.setInt(2,(Integer)value[1]);
		delete.setString(3, (String) value[2]);
		return delete.execute();
	}
	
	public List<Arbitre> getArbitreByTournoi(Object... value) throws Exception {
		PreparedStatement getAll = connexion.getConnexion().prepareStatement("SELECT * FROM Arbitrage where Nom_Tournoi = ? AND Annee = ?");
		getAll.setString(1, (String)value[0]);
		getAll.setInt(2, (Integer)value[1]);
		ResultSet resultat = getAll.executeQuery();
		List<Arbitre> sortie = new ArrayList<>();
		while(resultat.next()) {
			Arbitre arbitre = daoarbitre.getById(resultat.getInt("Id_Arbitre"));
			sortie.add(arbitre);
		}
		return sortie;
	}

}

