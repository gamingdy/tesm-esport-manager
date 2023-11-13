package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.Inscription;
import modele.Selection;

public class DaoSelection implements Dao<Selection,Object>{

	private Connexion connexion;
	
	public DaoSelection(Connexion connexion) {
		this.connexion = connexion;
	}
	
	@Override
	public void createTable() throws SQLException {
		String createTableSql = "CREATE TABLE Selection("
				  + "Id_Arbitre INT,"
				  + "Annee SMALLINT,"
				  + "PRIMARY KEY(Id_Arbitre, Annee),"
				  + "FOREIGN KEY(Id_Arbitre) REFERENCES Arbitre(Id_Arbitre),"
				  + "FOREIGN KEY(Annee) REFERENCES Saison(Annee)";
		
		Statement createTable;
		
		createTable = connexion.getConnexion().createStatement();
		createTable.execute(createTableSql);
		System.out.println("Table 'Selection' créée avec succès");
		
	}

	@Override
	public boolean dropTable() throws SQLException {
		Statement deleteTable;
		deleteTable = connexion.getConnexion().createStatement();
		return deleteTable.execute("drop table Selection");
	}

	@Override
	public List<Selection> getAll() throws Exception {
		Statement getAll = connexion.getConnexion().createStatement();
		ResultSet resultat = getAll.executeQuery("SELECT * FROM Selection");
		List<Selection> sortie = new ArrayList<>();
		while(resultat.next()) {
			Selection inscription = new Selection(
					resultat.getInt("Id_Arbitre"),
					resultat.getShort("Annee"));
			sortie.add(inscription);
		}
		return sortie;
	}

	@Override
	public Selection getById(Object... id) throws Exception {
		PreparedStatement getById = connexion.getConnexion().prepareStatement("SELECT * FROM Selection WHERE Id_Arbitre = ? AND Annee = ?");
		getById.setInt(1, (Integer)id[0]);
		getById.setShort(1, (Short)id[1]);
		ResultSet resultat = getById.executeQuery();
		Selection selection = new Selection(
				resultat.getInt("Id_Arbitre"),
				resultat.getShort("Annee"));
		return selection;
	}

	@Override
	public boolean add(Selection value) throws Exception {
		PreparedStatement add = connexion.getConnexion().prepareStatement(
				"INSERT INTO Inscription(Id_Arbitre,Annee) values (?,?)");
		add.setInt(1, value.getIdArbitre());
		add.setShort(2, value.getAnnee());
		return add.execute();
	}

	@Override
	public boolean update(Selection value) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object... value) throws Exception {
		PreparedStatement delete = connexion.getConnexion().prepareStatement(
				"DELETE FROM Selection where Id_Arbitre = ? AND Annee = ?");
		delete.setInt(1,(Integer)value[0]);
		delete.setShort(2,(Short)value[1]);
		return delete.execute();
	}

}
