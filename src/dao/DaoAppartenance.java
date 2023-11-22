package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modele.Appartenance;
import modele.Equipe;


public class DaoAppartenance implements Dao<Appartenance,Object>{

	private Connexion connexion;
	private DaoPoule daopoule;
	private DaoEquipe daoequipe;

	public DaoAppartenance(Connexion connexion) {
		this.connexion = connexion;
		this.daopoule=new DaoPoule(connexion);
		this.daoequipe= new DaoEquipe(connexion);
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


		try(Statement createTable = connexion.getConnection().createStatement()){
			createTable.execute(createTableSql);
			System.out.println("Table 'Arbitrage' créée avec succès");
		}

	}

	@Override
	public boolean dropTable() throws SQLException {
		try(Statement deleteTable = connexion.getConnection().createStatement()){
			return deleteTable.execute("drop table Selection");
		}
	}

	@Override
	public List<Appartenance> getAll() throws Exception {
		try(Statement getAll = connexion.getConnection().createStatement()){
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Appartenance");
			List<Appartenance> sortie = new ArrayList<>();
			while(resultat.next()) {
				Appartenance appartenance = new Appartenance(
						daoequipe.getById(resultat.getString("Nom_Equipe")),
						daopoule.getById(resultat.getInt("Annee"),resultat.getString("Nom_tournoi"),resultat.getString("Libellé")));
				sortie.add(appartenance);
			}
			return sortie;
		}
	}

	@Override
	public Appartenance getById(Object... id) throws Exception {
		try(PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Appartenance WHERE Nom_Equipe = ? AND Annee = ? AND Nom_tournoi = ? AND Libellé = ?")){
			getById.setString(1, (String)id[0]);
			getById.setInt(2, (Integer)id[1]);
			getById.setString(3, (String) id[2]);
			getById.setString(3, (String)id[2]);

			ResultSet resultat = getById.executeQuery();
			Appartenance appartenance = new Appartenance(
					daoequipe.getById(resultat.getString("Nom_Equipe")),
					daopoule.getById(resultat.getInt("Annee"),resultat.getString("Nom_tournoi"),resultat.getString("Libellé")));

			return appartenance;
		}
	}

	@Override
	public boolean add(Appartenance value) throws Exception {
		try(PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Arbitrage(Nom_Equipe,Annee,Nom_Tournoi,Libellé) values (?,?,?)")){
			add.setString(1, value.getEquipe().getNom());
			add.setInt(2, value.getPoule().getTournoi().getSaison().getAnnee());
			add.setString(3, value.getPoule().getTournoi().getNom());
			add.setString(4, value.getPoule().getLibelle().toString());
			return add.execute();
		}
	}

	@Override
	public boolean update(Appartenance value) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object... value) throws Exception {
		try(PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Arbitrage where Nom_Equipe = ? AND Annee = ? AND Nom_tournoi = ? AND Libellé = ?")){
			delete.setString(1,(String)value[0]);
			delete.setInt(2,(Integer)value[1]);
			delete.setString(3, (String) value[2]);
			delete.setString(4, (String)value[3]);
			return delete.execute();
		}
	}

	public List<Equipe> getEquipeByPoule(Object... value) throws Exception {
		try(PreparedStatement getAll = connexion.getConnection().prepareStatement("SELECT * FROM Appartenance where Nom_Tournoi = ? AND Annee = ? AND Libelle = ?")){
			getAll.setString(1, (String)value[0]);
			getAll.setInt(2, (Integer)value[1]);
			getAll.setString(3, (String)value[2]);
			ResultSet resultat = getAll.executeQuery();
			List<Equipe> sortie = new ArrayList<>();
			while(resultat.next()) {
				Equipe equipe = daoequipe.getById(resultat.getString("Nom_Equipe"));
				sortie.add(equipe);
			}
			return sortie;
		}
	}

	public List<Equipe> getEquipeByTournoi(Object... value) throws Exception {
		try(PreparedStatement getAll = connexion.getConnection().prepareStatement("SELECT * FROM Appartenance where Nom_Tournoi = ? AND Annee = ?")){
			getAll.setString(1, (String)value[0]);
			getAll.setInt(2, (Integer)value[1]);
			ResultSet resultat = getAll.executeQuery();
			List<Equipe> sortie = new ArrayList<>();
			while(resultat.next()) {
				Equipe equipe = daoequipe.getById(resultat.getString("Nom_Equipe"));
				sortie.add(equipe);
			}
			return sortie;
		}
	}

}