package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.EquipeComplete;
import modele.Categorie;
import modele.Equipe;
import modele.Joueur;
import modele.Matche;

public class DaoMatche implements Dao<Matche,Integer>{
	
	private Connexion connexion;
	
	public DaoMatche(Connexion connexion) {
		this.connexion = connexion;
	}

	@Override
	public void createTable() throws SQLException {
		String createTableSql = "CREATE TABLE Matche("
						   +"Id_Match INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
						   +"categorie VARCHAR(50),"
						   +"Nombres_Parties_Max BYTE,"
						   +"Date_Matche_Debut DATETIME,"
						   +"Date_Matche_Fin DATETIME,"
						   +"Nom_Equipe1 VARCHAR(50) NOT NULL,"
						   +"Nom_Equipe2 VARCHAR(50) NOT NULL,"
						   +"Annee SMALLINT NOT NULL,"
						   +"Nom_tournoi VARCHAR(50) NOT NULL,"
						   +"PRIMARY KEY(Id_Match),"
						   +"FOREIGN KEY(Nom_Equipe1) REFERENCES Equipe(Nom_Equipe),"
						   +"FOREIGN KEY(Nom_Equipe2) REFERENCES Equipe(Nom_Equipe),"
						   +"FOREIGN KEY(Annee, Nom_tournoi) REFERENCES Tournoi(Annee, Nom_tournoi)";


		Statement createTable;
		
		createTable = connexion.getConnexion().createStatement();
		createTable.execute(createTableSql);
        System.out.println("Table 'Matche' créée avec succès");
	}

	@Override
	public boolean dropTable() throws SQLException {
		Statement deleteTable;
		deleteTable = connexion.getConnexion().createStatement();
		return deleteTable.execute("drop table Matche");
	}

	@Override
	public List<Matche> getAll() throws Exception {
		Statement getAll = connexion.getConnexion().createStatement();
		ResultSet resultat = getAll.executeQuery("SELECT * FROM Matche");
		List<Matche> sortie = new ArrayList<>();
		while(resultat.next()) {
			Object[] idTournoi = {resultat.getShort("Annee"),resultat.getString("Nom_tournoi")};
			Matche matche = new Matche(
					resultat.getByte("Nombres_Parties_Max"),
					resultat.getDate("Date_Matche_Debut"),
					resultat.getDate("Date_Matche_Fin"),
					Categorie.valueOf(resultat.getString("categorie")),
					new Equipe(resultat.getString("Nom_Equipe1")),
					new Equipe(resultat.getString("Nom_Equipe2")),
					idTournoi);
			matche.setId(resultat.getInt("Id_Match"));
			sortie.add(matche);
		}
		return sortie;
	}

	@Override
	public Matche getById(Integer... id) throws Exception {
		PreparedStatement getById = connexion.getConnexion().prepareStatement("SELECT * FROM Matche WHERE Id_Match = ?");
		getById.setInt(1, id[0]);
		ResultSet resultat = getById.executeQuery();
		Object[] idTournoi = {resultat.getShort("Annee"),resultat.getString("Nom_tournoi")};
		Matche matche = new Matche(
				resultat.getByte("Nombres_Parties_Max"),
				resultat.getDate("Date_Matche_Debut"),
				resultat.getDate("Date_Matche_Fin"),
				Categorie.valueOf(resultat.getString("categorie")),
				new Equipe(resultat.getString("Nom_Equipe1")),
				new Equipe(resultat.getString("Nom_Equipe2")),
				idTournoi);
		matche.setId(resultat.getInt("Id_Match"));
		return matche;
	}

	@Override
	public boolean add(Matche value) throws Exception {
		PreparedStatement add = connexion.getConnexion().prepareStatement(
				"INSERT INTO Matche("
				+ "categorie,"
				+ "Nombres_Parties_Max,"
				+ "Date_Matche_Debut"
				+ "Date_Matche_Fin"
				+ "Nom_Equipe1"
				+ "Nom_Equipe2"
				+ "Annee"
				+ "Nom_Tournoi) values (?,?,?,?,?,?,?,?)");
		add.setString(1, value.getLibelle().name());
		add.setByte(2, value.getNombreMaxParties());
		add.setDate(3, value.getDateDebutMatche());
		add.setDate(4, value.getDateFinMatche());
		add.setString(5, value.getEquipe1().getNom());
		add.setString(6, value.getEquipe2().getNom());
		add.setShort(7, value.getAnneeTournoi());
		add.setString(8, value.getNomTournoi());
		return add.execute();
	}

	@Override
	public boolean update(Matche value) throws Exception {
		PreparedStatement update = connexion.getConnexion().prepareStatement(
				"UPDATE Matche SET "
				+ "categorie = ?"
				+ "Nombres_Parties_Max = ?"
				+ "Date_Matche_Debut = ?"
				+ "Date_Matche_Fin = ?"
				+ "Nom_Equipe1 = ?"
				+ "Nom_Equipe2 = ?"
				+ "WHERE Id_Match = ?");
		update.setString(1, value.getLibelle().name());
		update.setByte(2, value.getNombreMaxParties());
		update.setDate(3, value.getDateDebutMatche());
		update.setDate(4, value.getDateFinMatche());
		update.setString(5, value.getEquipe1().getNom());
		update.setString(6, value.getEquipe2().getNom());
		update.setInt(7, value.getId());
		return update.execute();
	}

	@Override
	public boolean delete(Integer... value) throws Exception {
		PreparedStatement delete = connexion.getConnexion().prepareStatement(
				"DELETE FROM Matche where Id_Matche = ?");
		delete.setInt(1,value[0]);
		return delete.execute();
	}
}

