package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modele.Partie;

public class DaoPartie implements Dao<Partie,Integer>{

	private Connexion connexion;
	private DaoMatche daomatche;


	public DaoPartie(Connexion connexion) {
		this.connexion = connexion;
		this.daomatche = new DaoMatche(connexion);

	}

	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Partie("
				+ "Id_Partie INT NOT NULL,"
				+ "Id_Match INT NOT NULL,"
				+ "Nom_Equipe VARCHAR(50),"
				+ "PRIMARY KEY(Id_Partie,Id_Match),"
				+ "FOREIGN KEY(Id_Match) REFERENCES Matche(Id_Match),"
				+ "FOREIGN KEY(Nom_Equipe) REFERENCES Equipe(Nom_Equipe))";

		try(Statement createTable = connexion.getConnection().createStatement()){
			createTable.execute(createTableSql);
			System.out.println("Table 'Partie' créée avec succès");
		}
	}

	public static boolean dropTable(Connexion connexion) throws SQLException {
		try(Statement deleteTable = connexion.getConnection().createStatement()){
			System.out.println("Table 'Partie' créée avec succès");
			return deleteTable.execute("drop table Partie");
		}
	}

	@Override
	public List<Partie> getAll() throws Exception {
		try(Statement getAll = connexion.getConnection().createStatement()){
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Partie");
			List<Partie> sortie = new ArrayList<>();
			while(resultat.next()) {
				Partie partie = new Partie(
						resultat.getString("Nom_Equipe"),
						daomatche.getById(resultat.getInt("Id_Match")));
				partie.setNumeroPartie(resultat.getInt("Id_Partie"));
				sortie.add(partie);
			}
			return sortie;
		}
	}

	@Override
	public Partie getById(Integer... id) throws Exception {
		try(PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Partie WHERE Id_Match = ? AND Numero_Partie = ?")){
			getById.setInt(1, id[0]);
			getById.setInt(2, id[1]);
			ResultSet resultat = getById.executeQuery();
			if(resultat.next()) {
				Partie partie = new Partie(
						resultat.getString("Nom_Equipe"),
						daomatche.getById(resultat.getInt("Id_Match")));
				partie.setNumeroPartie(resultat.getInt("Id_Partie"));
				return partie;
			}
			throw new Exception("Partie non trouvée");
		}
	}

	@Override
	public boolean add(Partie value) throws Exception {
		try(PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Partie(Id_Match,Nom_Equipe) values (?)")){
			add.setInt(1, value.getMatche().getId());

			return add.execute();
		}
	}

	@Override
	public boolean update(Partie value) throws Exception {
		try(PreparedStatement update = connexion.getConnection().prepareStatement(
				"UPDATE Partie SET "
						+ "Nom_Equipe = ?"
						+ "Id_Match = ?"
						+ "WHERE Id_Partie = ?")){
			update.setString(1, value.getVainqueur().getNom());
			update.setInt(2, value.getMatche().getId());
			update.setInt(3, value.getNumeroPartie());
			return update.execute();
		}
	}

	@Override
	public boolean delete(Integer... value) throws Exception {
		try(PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Partie where Id_Partie = ?")){
			delete.setInt(1,value[0]);
			return delete.execute();
		}
	}
}


