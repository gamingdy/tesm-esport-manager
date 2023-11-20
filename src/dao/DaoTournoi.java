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
import modele.Saison;
import modele.Tournoi;

public class DaoTournoi implements Dao<Tournoi,Object>{

	private Connexion connexion;
	
	public DaoTournoi(Connexion connexion) {
		this.connexion = connexion;
	}

	@Override
	public void createTable() throws SQLException {
		String createTableSql = "CREATE TABLE Tournoi("
				   +"Annee SMALLINT,"
				   +"Nom_tournoi VARCHAR(50),"
				   +"Date_Début DATETIME,"
				   +"Date_Fin DATETIME,"
				   +"username VARCHAR(50),"
				   +"mdp VARCHAR(50),"
				   +"Libelle_Niveau VARCHAR(50) NOT NULL,"
				   +"PRIMARY KEY(Annee, Nom_tournoi),"
				   +"FOREIGN KEY(Annee) REFERENCES Saison(Annee),"
				   +"FOREIGN KEY(Libelle_Niveau) REFERENCES Niveau(Libelle_Niveau)";

		Statement createTable;
		
		createTable = connexion.getConnexion().createStatement();
		createTable.execute(createTableSql);
        System.out.println("Table 'Tournoi' créée avec succès");
	}

	@Override
	public boolean dropTable() throws SQLException {
		Statement deleteTable;
		deleteTable = connexion.getConnexion().createStatement();
		return deleteTable.execute("drop table Tournoi");
	}

	@Override
	public List<Tournoi> getAll() throws Exception {
		Statement getAll = connexion.getConnexion().createStatement();
		ResultSet resultat = getAll.executeQuery("SELECT * FROM Tournoi");
		List<Tournoi> sortie = new ArrayList<>();
		while(resultat.next()) {
			Tournoi tournoi = new Tournoi(
					new Saison(resultat.getShort("Annee")),
					resultat.getString("Nom_Tournoi"),
					resultat.getDate("Date_Début"),
					resultat.getDate("Date_Fin"),
					Niveau.valueOf(resultat.getString("Libelle_Niveau")),
					resultat.getString("username"),
					resultat.getString("mdp"));
			sortie.add(tournoi);
		}
		return sortie;
	}

	@Override
	public Tournoi getById(Object... id) throws Exception {
		PreparedStatement getById = connexion.getConnexion().prepareStatement("SELECT * FROM Tournoi WHERE Nom_Tournoi = ? AND Annee = ?");
		getById.setString(1, (String)id[0]);
		getById.setShort(2, (Short)id[1]);
		ResultSet resultat = getById.executeQuery();
		Tournoi tournoi = new Tournoi(
				new Saison(resultat.getShort("Annee")),
				resultat.getString("Nom_Tournoi"),
				resultat.getDate("Date_Début"),
				resultat.getDate("Date_Fin"),
				Niveau.valueOf(resultat.getString("Libelle_Niveau")),
				resultat.getString("username"),
				resultat.getString("mdp"));
		return tournoi;
	}

	@Override
	public boolean add(Tournoi value) throws Exception {
		PreparedStatement add = connexion.getConnexion().prepareStatement(
				"INSERT INTO Tournoi(Annee,Nom_Tournoi,Date_Début,Date_Fin,username,mdp,Libelle_Niveau) values (?,?,?,?,?,?,?)");
		add.setShort(1, value.getAnnee().getAnnee());
		add.setString(2, value.getNom());
		add.setDate(3, value.getDebut());
		add.setDate(4, value.getFin());
		add.setString(5, value.getUsernameArbitre());
		add.setString(6, value.getMdpArbitre());
		add.setString(7, value.getNiveau().getNom());
		
		return add.execute();
	}

	@Override
	public boolean update(Tournoi value) throws Exception {
		PreparedStatement add = connexion.getConnexion().prepareStatement(
				"UPDATE Tournoi SET"
				+"Date_Début = ?"
				+"Date_Fin = ?" 
				+"username = ?"
				+"mdp = ?"
				+"Libelle_Niveau = ?"
				+"WHERE Annee = ? AND Nom_Tournoi = ?");
		add.setShort(6, value.getAnnee().getAnnee());
		add.setString(7, value.getNom());
		add.setDate(1, value.getDebut());
		add.setDate(2, value.getFin());
		add.setString(3, value.getUsernameArbitre());
		add.setString(4, value.getMdpArbitre());
		add.setString(5, value.getNiveau().getNom());
		
		return add.execute();
	}

	@Override
	public boolean delete(Object... value) throws Exception {
		PreparedStatement delete = connexion.getConnexion().prepareStatement(
				"DELETE FROM Tournoi WHERE Annee = ? AND Nom_Tournoi = ?");
		delete.setShort(1,(Short)value[0]);
		delete.setString(1,(String)value[1]);
		return delete.execute();
	}
}
