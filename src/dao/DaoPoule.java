package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modele.Poule;

public class DaoPoule implements Dao<Poule,Object>{

	private Connexion connexion;
	private DaoTournoi daotournoi;

	public DaoPoule(Connexion connexion) {
		this.connexion = connexion;
		this.daotournoi= new DaoTournoi(connexion);

	}

	@Override
	public void createTable() throws SQLException {
		String createTableSql = "CREATE TABLE Poule ("
				+ "Annee INT,"
				+ "Nom_Tournoi VARCHAR(50),"
				+ "Libelle VARCHAR(1),"
				+ "PRIMARY KEY(Annee, Nom_Tournoi, Libelle),"
				+ "FOREIGN KEY(Annee, Nom_Tournoi) REFERENCES Tournoi(Annee, Nom_Tournoi)";

		try(Statement createTable = connexion.getConnection().createStatement()){
			createTable.execute(createTableSql);
			System.out.println("Table 'Poule' créée avec succès");
		}
	}

	@Override
	public boolean dropTable() throws SQLException {
		try(Statement deleteTable = connexion.getConnection().createStatement()){
			return deleteTable.execute("drop table Poule");
		}
	}

	@Override
	public List<Poule> getAll() throws Exception {
		try(Statement getAll = connexion.getConnection().createStatement()){
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Poule");
			List<Poule> sortie = new ArrayList<>();
			while(resultat.next()) {
				Poule poule = new Poule(
						daotournoi.getById(resultat.getString("Nom_tounoi"),resultat.getShort("Annee")),
						resultat.getString("Libellé").charAt(0));
				sortie.add(poule);
			}
			return sortie;
		}
	}

	@Override
	public Poule getById(Object... value) throws Exception {
		try(PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Poule WHERE Annee = ? AND Nom_Tournoi = ? AND Libellé = ?")){
			getById.setInt(1, (Integer)value[0]);
			getById.setString(2, (String)value[1]);
			getById.setString(3, (String)value[2]);
			ResultSet resultat = getById.executeQuery();
			Poule poule = new Poule(
					daotournoi.getById(resultat.getString("Nom_tounoi"),resultat.getShort("Annee")),
					resultat.getString("Libellé").charAt(0));
			return poule;
		}
	}

	@Override
	public boolean add(Poule value) throws Exception {
		try(PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Poule(Annee,Nom_tournoi,Libellé) values (?,?,?)")){
			add.setInt(1, value.getTournoi().getSaison().getAnnee());
			add.setString(2, value.getTournoi().getNom());
			add.setString(3, value.getLibelle().toString());
			return add.execute();
		}
	}

	@Override
	public boolean update(Poule value) throws Exception {
		return false;
	}

	@Override
	public boolean delete(Object... value) throws Exception {
		try(PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Poule where Annee = ? AND Nom_tournoi = ? AND Libellé = ?")){
			delete.setInt(1,(Integer)value[0]);
			delete.setString(2,(String)value[1]);
			delete.setString(3,(String)value[2]);
			return delete.execute();
		}
	}
}

