package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.Arbitrage;
import modele.Arbitre;

public class DaoArbitrage implements Dao<Arbitrage,Object>{

	private Connexion connexion;
	private DaoArbitre daoarbitre;
	private DaoTournoi daotournoi;

	public DaoArbitrage(Connexion connexion) {
		this.connexion = connexion;
		this.daoarbitre=new DaoArbitre(connexion);
		this.daotournoi= new DaoTournoi(connexion);
	}

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

	public static boolean dropTable(Connexion connexion) throws SQLException {
		try(Statement deleteTable = connexion.getConnection().createStatement()){
			System.out.println("Table 'Arbitrage' créée avec succès");
			return deleteTable.execute("drop table Arbitrage");
		}
	}

	@Override
	public List<Arbitrage> getAll() throws Exception {
		try(Statement getAll = connexion.getConnection().createStatement()){
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Arbitrage");
			List<Arbitrage> sortie = new ArrayList<>();
			while(resultat.next()) {
				Arbitrage arbitrage = new Arbitrage(
						daoarbitre.getById(resultat.getInt("Id_Arbitre")),
						daotournoi.getById(
								resultat.getString("Nom_Tournoi"),
								resultat.getInt("Annee")));
				sortie.add(arbitrage);
			}
			return sortie;
		}
	}

	@Override
	public Arbitrage getById(Object... id) throws Exception {
		try(PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Arbitrage WHERE Id_Arbitre = ? AND Annee = ? AND Nom_tournoi = ?")){
			getById.setInt(1, (Integer)id[0]);
			getById.setInt(2, (Integer)id[1]);
			getById.setString(3, (String) id[2]);
			ResultSet resultat = getById.executeQuery();
			if (resultat.next()) {
				Arbitrage arbitrage = new Arbitrage(
						daoarbitre.getById(resultat.getInt("Id_Arbitre")),
						daotournoi.getById(
								resultat.getInt("Annee"),
								resultat.getString("Nom_Tournoi")));
				return arbitrage;
			}
			throw new Exception("Ligne non trouvée");
		}
	}

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

	@Override
	public boolean update(Arbitrage value) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

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

	public List<Arbitre> getArbitreByTournoi(Object... value) throws Exception {
		try(PreparedStatement getAll = connexion.getConnection().prepareStatement("SELECT * FROM Arbitrage where Nom_Tournoi = ? AND Annee = ?")){
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

}

