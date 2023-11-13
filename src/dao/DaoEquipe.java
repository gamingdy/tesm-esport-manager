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

public class DaoEquipe implements Dao<Equipe,String>{
	
	private Connexion connexion;
	private DaoJoueur daojoueur;
	
	public DaoEquipe(Connexion connexion) {
		this.connexion = connexion;
		this.daojoueur = new DaoJoueur(this.connexion);
	}

	@Override
	public void createTable() throws SQLException {
		String createTableSql = "CREATE TABLE Equipe (" +
                "Nom_Equipe VARCHAR(50)" +
                "Point INT " +
                "PRIMARY KEY (nom))";
		Statement createTable;
		
		createTable = connexion.getConnexion().createStatement();
		createTable.execute(createTableSql);
        System.out.println("Table 'Equipe' créée avec succès");
	}

	@Override
	public boolean dropTable() throws SQLException {
		Statement deleteTable;
		deleteTable = connexion.getConnexion().createStatement();
		return deleteTable.execute("drop table Equipe");
	}

	@Override
	public List<Equipe> getAll() throws Exception {
		Statement getAll = connexion.getConnexion().createStatement();
		ResultSet resultat = getAll.executeQuery("SELECT * FROM Equipe");
		List<Equipe> sortie = new ArrayList<>();
		while(resultat.next()) {
			List<Joueur> joueurs = daojoueur.getJoueurParEquipe(resultat.getString("Nom_Equipe"));
			Equipe equipe = new Equipe(resultat.getString("Nom_Equipe"));
			joueurs.stream().forEach(joueur -> {
				try {
					equipe.addJoueur(joueur);
				} catch (EquipeComplete e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			equipe.setPoint(resultat.getInt("Point"));
			sortie.add(equipe);
		}
		return sortie;
	}

	@Override
	public Equipe getById(String... nom) throws Exception {
		PreparedStatement getById = connexion.getConnexion().prepareStatement("SELECT * FROM Equipe WHERE Nom_Equipe = ?");
		getById.setString(1, nom[0]);
		ResultSet resultat = getById.executeQuery();
		List<Joueur> joueurs = daojoueur.getJoueurParEquipe(resultat.getString("Nom_Equipe"));
		Equipe equipe = new Equipe(resultat.getString("Nom_Equipe"));
		joueurs.stream().forEach(joueur -> {
			try {
				equipe.addJoueur(joueur);
			} catch (EquipeComplete e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		equipe.setPoint(resultat.getInt("Point"));
		return equipe;
	}

	@Override
	public boolean add(Equipe value) throws Exception {
		PreparedStatement add = connexion.getConnexion().prepareStatement(
				"INSERT INTO Equipe(Nom_Equipe,Points) values (?,?)");
		add.setString(1, value.getNom());
		add.setInt(2, value.getPoint());
		return add.execute();
	}

	@Override
	public boolean update(Equipe value) throws Exception {
		PreparedStatement update = connexion.getConnexion().prepareStatement(
				"UPDATE Equipe SET "
				+"Point = ? "
				+"WHERE Nom_Equipe = ?");
		update.setInt(1, value.getPoint());
		update.setString(2, value.getNom());
		return update.execute();
	}

	@Override
	public boolean delete(String... value) throws Exception {
		PreparedStatement delete = connexion.getConnexion().prepareStatement(
				"DELETE FROM Equipe where Nom_Equipe = ?");
		delete.setString(1,value[0]);
		return delete.execute();
	}

	

}
