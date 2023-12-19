package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modele.Arbitrage;
import modele.Joueur;

public class DaoJoueur implements Dao<Joueur,Integer>{

	private Connexion connexion;
	private DaoEquipe daoequipe;

	public DaoJoueur(Connexion connexion) {
		this.connexion = connexion;
		this.daoequipe = new DaoEquipe(connexion);
	}

	/**
	 * Crée la table joueur
	 * @param connexion
	 * @throws SQLException
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Joueur("
				+"Id_Joueur INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
				+"Pseudo VARCHAR(50),"
				+"Nom_Equipe VARCHAR(50) NOT NULL,"
				+"PRIMARY KEY(Id_Joueur),"
				+"FOREIGN KEY(Nom_Equipe) REFERENCES Equipe(Nom_Equipe))";

		try(Statement createTable= connexion.getConnection().createStatement()){
			createTable.execute(createTableSql);
			System.out.println("Table 'Joueur' créée avec succès");
		}
	}

	/**
	 * supprime la table joueur
	 * @param connexion
	 * @return
	 * @throws SQLException
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try(Statement deleteTable = connexion.getConnection().createStatement()){
			System.out.println("Table 'Joueur' supprimée avec succès");
			return deleteTable.execute("drop table Joueur");
		}
	}

	/**
	 * Renvoie tous les joueurs existants
	 */
	@Override
	public List<Joueur> getAll() throws Exception {
		try(Statement getAll = connexion.getConnection().createStatement()){
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Joueur");
			List<Joueur> sortie = new ArrayList<>();
			while(resultat.next()) {
				Joueur joueur = new Joueur(
						resultat.getString("Pseudo"),
						daoequipe.getById(resultat.getString("Nom_Equipe")).get());
				joueur.setId(resultat.getInt("Id_Joueur"));
				sortie.add(joueur);
			}
			return sortie;
		}
	}

	/**
	 * Renvoie un joueur précis
	 * Les paramètres sont placés dans cet ordre : Id_Joueur (INTEGER)
	 */
	@Override
	public Optional<Joueur> getById(Integer... id) throws Exception {
		try(PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Joueur WHERE Id_Joueur = ?")){
			getById.setInt(1, id[0]);
			ResultSet resultat = getById.executeQuery();
			Joueur joueur = null;
			if(resultat.next()) {
			joueur = new Joueur(
					resultat.getString("Pseudo"),
					daoequipe.getById(resultat.getString("Nom_Equipe")).get());
			joueur.setId(resultat.getInt("Id_Joueur"));
			
			}
			return Optional.ofNullable(joueur);
		}
	}

	/**
	 * Ajoute un joueur à la table joueur à partir d'un objet joueur 
	 */
	@Override
	public boolean add(Joueur value) throws Exception {
		try(PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Joueur(Pseudo,Nom_Equipe) values (?,?)")){
			add.setString(1, value.getPseudo());
			add.setString(2, value.getNomEquipe());
			return add.execute();
		}
	}

	/**
	 * update une ligen de la table joueur à partir d'un objet joueur
	 */
	@Override
	public boolean update(Joueur value) throws Exception {
		try(PreparedStatement update = connexion.getConnection().prepareStatement(
				"UPDATE Joueur SET "
						+"Pseudo = ?, "
						+"Nom_Equipe = ? "
						+"WHERE Id_Joueur = ?")) {
			update.setString(1, value.getPseudo());
			update.setString(2, value.getNomEquipe());
			update.setInt(3, value.getId());
			return update.execute();
		}		
	}

	/**
	 * supprime un joueur 
	 * Les paramètres sont placés dans cet ordre : Id_Joueur (INTEGER)
	 */
	@Override
	public boolean delete(Integer... value) throws Exception {
		try(PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Joueur where Id_Joueur = ?")){
			delete.setInt(1,value[0]);
			return delete.execute();
		}
	}

	/**
	 * Renvoie tous les joueurs d'une équipe en passant en paramètre le nom de l'équipe
	 * @param nom
	 * @return
	 * @throws Exception
	 */
	public List<Joueur> getJoueurParEquipe(String nom) throws Exception {
		try(PreparedStatement getAll = connexion.getConnection().prepareStatement("SELECT * FROM Joueur WHERE Nom_Equipe = ?")){
			getAll.setString(1, nom);
			ResultSet resultat = getAll.executeQuery();
			List<Joueur> sortie = new ArrayList<>();
			while(resultat.next()) {
				Joueur joueur = new Joueur(
						resultat.getString("Pseudo"),
						daoequipe.getById(resultat.getString("Nom_Equipe")).get());
				joueur.setId(resultat.getInt("Id_Joueur"));
				sortie.add(joueur);
			}
			return sortie;
		}
	}
	
	@Override
	public String visualizeTable() throws Exception {
		String s = "_______________Joueur_______________________" + "\n";
		List<Joueur> l = this.getAll();
		for(Joueur a : l) {
			s+=a.toString()+"\n";
		}
		s+="\n\n\n";
		return s;
	}
}
