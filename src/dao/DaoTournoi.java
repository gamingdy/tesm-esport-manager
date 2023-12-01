package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modele.CompteArbitre;
import modele.CustomDate;
import modele.Niveau;
import modele.Saison;
import modele.Tournoi;

public class DaoTournoi implements Dao<Tournoi,Object>{

	private Connexion connexion;

	public DaoTournoi(Connexion connexion) {
		this.connexion = connexion;
	}

	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Tournoi("
				+"Annee INT,"
				+"Nom_tournoi VARCHAR(50),"
				+"Date_Début DATE,"
				+"Date_Fin DATE,"
				+"username VARCHAR(50),"
				+"mdp VARCHAR(50),"
				+"Libelle_Niveau VARCHAR(50) NOT NULL,"
				+"PRIMARY KEY(Annee, Nom_tournoi),"
				+"FOREIGN KEY(Annee) REFERENCES Saison(Annee),"
				+"FOREIGN KEY(Libelle_Niveau) REFERENCES Niveau(Libelle_Niveau))";

		try(Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
			System.out.println("Table 'Tournoi' créée avec succès");
		}
	}


	public static boolean dropTable(Connexion connexion) throws SQLException {
		try(Statement deleteTable = connexion.getConnection().createStatement();){
			System.out.println("Table 'Tournoi' créée avec succès");
			return deleteTable.execute("drop table Tournoi");
		}
	}

	@Override
	public List<Tournoi> getAll() throws Exception {
		try(Statement getAll = connexion.getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Tournoi");
			List<Tournoi> sortie = new ArrayList<>();
			while(resultat.next()) {
				Tournoi tournoi = new Tournoi(
						new Saison(resultat.getInt("Annee")),
						resultat.getString("Nom_Tournoi"),
						new CustomDate(resultat.getTimestamp("Date_Début")),
						new CustomDate(resultat.getTimestamp("Date_Fin")),
						Niveau.valueOf(resultat.getString("Libelle_Niveau")),
						new CompteArbitre(
								resultat.getString("username"),
								resultat.getString("mdp"))
						);
				sortie.add(tournoi);
			}
			return sortie;
		}
	}

	@Override
	public Tournoi getById(Object... id) throws Exception {
		try (PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Tournoi WHERE Nom_Tournoi = ? AND Annee = ?")){
			getById.setString(1, (String)id[0]);
			getById.setInt(2, (Integer)id[1]);
			ResultSet resultat = getById.executeQuery();
			if(resultat.next()) {
				Tournoi tournoi = new Tournoi(
						new Saison(resultat.getInt("Annee")),
						resultat.getString("Nom_Tournoi"),
						new CustomDate(resultat.getTimestamp("Date_Début")),
						new CustomDate(resultat.getTimestamp("Date_Fin")),
						Niveau.search(resultat.getString("Libelle_Niveau")),
						new CompteArbitre(
								resultat.getString("username"),
								resultat.getString("mdp"))
						);
				return tournoi;
			}
			throw new Exception("Tournoi non trouvé");
		}
	}

	@Override
	public boolean add(Tournoi value) throws Exception {
		try(PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Tournoi(Annee,Nom_Tournoi,Date_Début,Date_Fin,username,mdp,Libelle_Niveau) values (?,?,?,?,?,?,?)")) {
			add.setInt(1, value.getSaison().getAnnee());
			add.setString(2, value.getNom());
			add.setTimestamp(3, value.getDebut().toSQL());
			add.setTimestamp(4, value.getFin().toSQL());
			add.setString(5, value.getCompteArbitre().getUsername());
			add.setString(6, value.getCompteArbitre().getMdp());
			add.setString(7, value.getNiveau().getNom());

			return add.execute();
		}
	}

	@Override
	public boolean update(Tournoi value) throws Exception {
		try (PreparedStatement update = connexion.getConnection().prepareStatement(
				"UPDATE Tournoi SET"
						+"Date_Début = ?"
						+"Date_Fin = ?" 
						+"username = ?"
						+"mdp = ?"
						+"Libelle_Niveau = ?"
						+"WHERE Annee = ? AND Nom_Tournoi = ?")) {
			update.setInt(6, value.getSaison().getAnnee());
			update.setString(7, value.getNom());
			update.setTimestamp(1, value.getDebut().toSQL());
			update.setTimestamp(2, value.getFin().toSQL());
			update.setString(3, value.getCompteArbitre().getUsername());
			update.setString(4, value.getCompteArbitre().getMdp());
			update.setString(5, value.getNiveau().getNom());

			return update.execute();
		}
	}

	@Override
	public boolean delete(Object... value) throws Exception {
		try (PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Tournoi WHERE Annee = ? AND Nom_Tournoi = ?")) {
			delete.setInt(1,(Integer)value[0]);
			delete.setString(2,(String)value[1]);
			return delete.execute();
		}
	}
}
