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
import modele.Poule;
import modele.Tournoi;

public class DaoPoule implements Dao<Poule,Object>{
	
	private Connexion connexion;
	
	public DaoPoule(Connexion connexion) {
		this.connexion = connexion;
		
	}

	@Override
	public void createTable() throws SQLException {
		String createTableSql = "CREATE TABLE Poule ("
				+ "Annee SMALLINT,"
				+ "Nom_Tournoi VARCHAR(50),"
				+ "Libelle CHAR(1),"
				+ "PRIMARY KEY(Annee, Nom_Tournoi, Libelle),"
				+ "FOREIGN KEY(Annee, Nom_Tournoi) REFERENCES Tournoi(Annee, Nom_Tournoi)";

		Statement createTable;
		
		createTable = connexion.getConnexion().createStatement();
		createTable.execute(createTableSql);
        System.out.println("Table 'Poule' créée avec succès");
	}

	@Override
	public boolean dropTable() throws SQLException {
		Statement deleteTable;
		deleteTable = connexion.getConnexion().createStatement();
		return deleteTable.execute("drop table Poule");
	}

	@Override
	public List<Poule> getAll() throws Exception {
		Statement getAll = connexion.getConnexion().createStatement();
		ResultSet resultat = getAll.executeQuery("SELECT * FROM Poule");
		List<Poule> sortie = new ArrayList<>();
		while(resultat.next()) {
			Object[] idTournoi = {resultat.getString("Nom_tounoi"),resultat.getShort("Annee")};
			Poule poule = new Poule(
					idTournoi,
					resultat.getString("Libellé"));
			sortie.add(poule);
		}
		return sortie;
	}

	@Override
	public Poule getById(Object... nom) throws Exception {
		PreparedStatement getById = connexion.getConnexion().prepareStatement("SELECT * FROM Poule WHERE Annee = ? AND Nom_Tournoi = ? AND Libellé = ?");
		getById.setShort(1, (Short)nom[0]);
		getById.setString(2, (String)nom[1]);
		getById.setString(3, (String)nom[2]);
		ResultSet resultat = getById.executeQuery();
		Object[] idTournoi = {resultat.getString("Nom_tounoi"),resultat.getShort("Annee")};
		Poule poule = new Poule(
				idTournoi,
				resultat.getString("Libellé"));
		return poule;
	}

	@Override
	public boolean add(Poule value) throws Exception {
		PreparedStatement add = connexion.getConnexion().prepareStatement(
				"INSERT INTO Poule(Annee,Nom_tournoi,Libellé) values (?,?,?)");
		add.setShort(1, value.getAnneeTournoi());
		add.setString(2, value.getNomTournoi());
		add.setString(3, value.getLibelle());
		return add.execute();
	}

	@Override
	public boolean update(Poule value) throws Exception {
		return false;
	}

	@Override
	public boolean delete(Object... value) throws Exception {
		PreparedStatement delete = connexion.getConnexion().prepareStatement(
				"DELETE FROM Poule where Annee = ? AND Nom_tournoi = ? AND Libellé = ?");
		delete.setShort(1,(Short)value[0]);
		delete.setString(2,(String)value[1]);
		delete.setString(3,(String)value[2]);
		return delete.execute();
	}
}

