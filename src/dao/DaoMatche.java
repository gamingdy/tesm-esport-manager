package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modele.Categorie;
import modele.CustomDate;
import modele.Matche;

public class DaoMatche implements Dao<Matche,Integer>{

	private Connexion connexion;
	private DaoTournoi daotournoi;
	private DaoEquipe daoequipe;

	public DaoMatche(Connexion connexion) {
		this.connexion = connexion;
		this.daotournoi = new DaoTournoi(connexion);
		this.daoequipe = new DaoEquipe(connexion);
	}

	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Matche("
				+"Id_Match INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+"categorie VARCHAR(50),"
				+"Nombres_Parties_Max INT,"
				+"Date_Matche_Debut DATE,"
				+"Nom_Equipe1 VARCHAR(50) NOT NULL,"
				+"Nom_Equipe2 VARCHAR(50) NOT NULL,"
				+"Annee INT NOT NULL,"
				+"Nom_tournoi VARCHAR(50) NOT NULL,"
				+"PRIMARY KEY(Id_Match),"
				+"FOREIGN KEY(Nom_Equipe1) REFERENCES Equipe(Nom_Equipe),"
				+"FOREIGN KEY(Nom_Equipe2) REFERENCES Equipe(Nom_Equipe),"
				+"FOREIGN KEY(Annee, Nom_tournoi) REFERENCES Tournoi(Annee, Nom_tournoi))";


		try(Statement createTable= connexion.getConnection().createStatement()){
			createTable.execute(createTableSql);
			System.out.println("Table 'Matche' créée avec succès");
		}
	}

	public static boolean dropTable(Connexion connexion) throws SQLException {
		try(Statement deleteTable= connexion.getConnection().createStatement()){
			System.out.println("Table 'Matche' créée avec succès");
			return deleteTable.execute("drop table Matche");
		}
	}

	@Override
	public List<Matche> getAll() throws Exception {
		try(Statement getAll = connexion.getConnection().createStatement()){
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Matche");
			List<Matche> sortie = new ArrayList<>();
			while(resultat.next()) {
				Matche matche = new Matche(
						resultat.getInt("Nombres_Parties_Max"),
						new CustomDate(resultat.getTimestamp("Date_Matche_Debut")),
						Categorie.valueOf(resultat.getString("categorie")),
						daoequipe.getById(resultat.getString("Nom_Equipe1")),
						daoequipe.getById(resultat.getString("Nom_Equipe2")),
						daotournoi.getById(resultat.getString("Nom_tournoi"),resultat.getInt("Annee")));
				matche.setId(resultat.getInt("Id_Match"));
				sortie.add(matche);
			}
			return sortie;
		}
	}

	@Override
	public Matche getById(Integer... id) throws Exception {
		try(PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Matche WHERE Id_Match = ?")){
			getById.setInt(1, id[0]);
			ResultSet resultat = getById.executeQuery();
			if(resultat.next()) {
				Matche matche = new Matche(
						resultat.getInt("Nombres_Parties_Max"),
						new CustomDate(resultat.getTimestamp("Date_Matche_Debut")),
						Categorie.valueOf(resultat.getString("categorie")),
						daoequipe.getById(resultat.getString("Nom_Equipe1")),
						daoequipe.getById(resultat.getString("Nom_Equipe2")),
						daotournoi.getById(resultat.getString("Nom_tournoi"),resultat.getInt("Annee")));
				matche.setId(resultat.getInt("Id_Match"));
				return matche;
			}
			throw new Exception("Matche non trouvé");
		}
	}

	@Override
	public boolean add(Matche value) throws Exception {
		try(PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Matche("
						+ "categorie,"
						+ "Nombres_Parties_Max,"
						+ "Date_Matche_Debut"
						+ "Nom_Equipe1"
						+ "Nom_Equipe2"
						+ "Annee"
						+ "Nom_Tournoi) values (?,?,?,?,?,?,?)")){
			add.setString(1, value.getLibelle().name());
			add.setInt(2, value.getNombreMaxParties());
			add.setTimestamp(3, value.getDateDebutMatche().toSQL());
			add.setString(4, value.getEquipe1().getNom());
			add.setString(5, value.getEquipe2().getNom());
			add.setInt(6, value.getTournoi().getSaison().getAnnee());
			add.setString(7, value.getTournoi().getNom());
			return add.execute();
		}
	}

	@Override
	public boolean update(Matche value) throws Exception {
		try(PreparedStatement update = connexion.getConnection().prepareStatement(
				"UPDATE Matche SET "
						+ "categorie = ?"
						+ "Nombres_Parties_Max = ?"
						+ "Date_Matche_Debut = ?"
						+ "Date_Matche_Fin = ?"
						+ "Nom_Equipe1 = ?"
						+ "Nom_Equipe2 = ?"
						+ "WHERE Id_Match = ?")){
			update.setString(1, value.getLibelle().name());
			update.setInt(2, value.getNombreMaxParties());
			update.setTimestamp(3, value.getDateDebutMatche().toSQL());
			update.setString(4, value.getEquipe1().getNom());
			update.setString(5, value.getEquipe2().getNom());
			update.setString(6, value.getTournoi().getNom());
			update.setInt(7, value.getId());
			return update.execute();
		}
	}

	@Override
	public boolean delete(Integer... value) throws Exception {
		try(PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Matche where Id_Matche = ?")){
			delete.setInt(1,value[0]);
			return delete.execute();
		}
	}

	public List<Matche> getMatchByTournoi(Object... value) throws Exception {
		try(PreparedStatement getMatchByTournoi = connexion.getConnection().prepareStatement("SELECT * FROM Matche WHERE Annee = ? AND Nom_Tournoi = ?")){
			getMatchByTournoi.setShort(1, (Short)value[0]);
			getMatchByTournoi.setString(2, (String)value[1]);
			ResultSet resultat = getMatchByTournoi.executeQuery();
			List<Matche> sortie = new ArrayList<>();
			while(resultat.next()) {
				Matche matche = new Matche(
						resultat.getInt("Nombres_Parties_Max"),
						new CustomDate(resultat.getTimestamp("Date_Matche_Debut")),
						Categorie.valueOf(resultat.getString("categorie")),
						daoequipe.getById(resultat.getString("Nom_Equipe1")),
						daoequipe.getById(resultat.getString("Nom_Equipe2")),
						daotournoi.getById(resultat.getInt("Annee"),resultat.getString("Nom_tournoi")));
				matche.setId(resultat.getInt("Id_Match"));
				sortie.add(matche);
			}
			return sortie;
		}
	}
}

