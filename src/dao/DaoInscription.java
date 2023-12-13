package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modele.Inscription;

public class DaoInscription implements Dao<Inscription,Object>{

	private Connexion connexion;

	public DaoInscription(Connexion connexion) {
		this.connexion = connexion;
	}

	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Inscription("
				+ "Nom_Equipe VARCHAR(50),"
				+ "Annee INT,"
				+ "PRIMARY KEY(Nom_Equipe, Annee),"
				+ "FOREIGN KEY(Nom_Equipe) REFERENCES Equipe(Nom_Equipe),"
				+ "FOREIGN KEY(Annee) REFERENCES Saison(Annee))";


		try(Statement createTable= connexion.getConnection().createStatement()){
			createTable.execute(createTableSql);
			System.out.println("Table 'Inscription' créée avec succès");
		}

	}

	public static boolean dropTable(Connexion connexion) throws SQLException {
		try(Statement deleteTable= connexion.getConnection().createStatement()){
			System.out.println("Table 'Inscription' créée avec succès");
			return deleteTable.execute("drop table Inscription");
		}
	}

	@Override
	public List<Inscription> getAll() throws Exception {
		try(Statement getAll = connexion.getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Inscription");
			List<Inscription> sortie = new ArrayList<>();
			while(resultat.next()) {
				Inscription inscription = new Inscription(
						resultat.getInt("Annee"),
						resultat.getString("Nom_Equipe"));
				sortie.add(inscription);
			}
			return sortie;
		}
	}

	@Override
	public Inscription getById(Object... id) throws Exception {
		try(PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Inscription WHERE Annee = ? AND Nom_Equipe = ?")){
			getById.setShort(1, (Short)id[0]);
			getById.setString(1, (String)id[1]);
			ResultSet resultat = getById.executeQuery();
			if(resultat.next()) {
				Inscription inscription = new Inscription(
						resultat.getShort("Annee"),
						resultat.getString("Nom_Equipe"));
				return inscription;
			}
			throw new Exception("Ligne non trouvé");
		}
	}

	@Override
	public boolean add(Inscription value) throws Exception {
		try(PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Inscription(Annee,Nom_Equipe) values (?,?)")){
			add.setInt(1, value.getAnnee());
			add.setString(2, value.getNom());
			return add.execute();
		}
	}

	@Override
	public boolean update(Inscription value) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object... value) throws Exception {
		try(PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Saison where Annee = ? AND Nom_Equipe = ?")){
			delete.setShort(1,(Short)value[0]);
			delete.setString(2,(String)value[1]);
			return delete.execute();
		}
	}

}