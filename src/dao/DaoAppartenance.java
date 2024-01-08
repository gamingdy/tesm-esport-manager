package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modele.Appartenance;
import modele.Equipe;
import modele.Inscription;
import modele.Poule;
import modele.Tournoi;


public class DaoAppartenance implements Dao<Appartenance,Object>{

	private Connexion connexion;
	private DaoPoule daopoule;
	private DaoEquipe daoequipe;
	private DaoTournoi daotournoi;

	public DaoAppartenance(Connexion connexion) {
		this.connexion = connexion;
		this.daopoule= new DaoPoule(connexion);
		this.daoequipe= new DaoEquipe(connexion);
		this.daotournoi = new DaoTournoi(connexion);
	}

	/**
	 * Crée la table d'association apparenance qui associe les équipes et les poules
	 * @param connexion
	 * @throws SQLException
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = 
				"CREATE TABLE Appartenance("
						+"Annee INT,"
						+"Nom_Equipe VARCHAR(50),"
						+"Nom_Tournoi VARCHAR(50),"
						+"Libelle CHAR(1),"
						+"PRIMARY KEY(Annee, Nom_tournoi, Nom_Equipe, Libelle),"
						+"FOREIGN KEY(Annee, Nom_tournoi) REFERENCES Tournoi(Annee, Nom_tournoi),"
						+"FOREIGN KEY(Nom_Equipe) REFERENCES Equipe(Nom_Equipe))";


		try(Statement createTable = connexion.getConnection().createStatement()){
			createTable.execute(createTableSql);
			System.out.println("Table 'Appartenance' créée avec succès");
		}

	}

	/**
	 * Supprime la table Appartenance
	 * @param connexion
	 * @return
	 * @throws SQLException
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try(Statement deleteTable = connexion.getConnection().createStatement()){
			System.out.println("Table 'Appartenance' supprimée avec succès");
			return deleteTable.execute("drop table Appartenance");
		}
	}

	/**
	 * Renvoie toutes les associations de poules et d'équipes existantes
	 */
	@Override
	public List<Appartenance> getAll() throws Exception {
		try(Statement getAll = connexion.getConnection().createStatement()){
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Appartenance");
			List<Appartenance> sortie = new ArrayList<>();
			generateListAppartenance(resultat, sortie);
			return sortie;
		}
	}

	private void generateListAppartenance(ResultSet resultat, List<Appartenance> sortie)
			throws SQLException, Exception {
		while(resultat.next()) {
			Appartenance appartenance = new Appartenance(
					daoequipe.getById(resultat.getString("Nom_Equipe")).get(),
					daopoule.getById(resultat.getInt("Annee"),resultat.getString("Nom_tournoi"),resultat.getString("Libelle")).get());
			sortie.add(appartenance);
		}
	}

	/**
	 * Renvoie une association précise
	 * Les paramètres sont placés dans cet ordre : Nom_Equipe (STRING) , Annee (INTEGER) , Nom_tournoi (STRING) , Libelle (STRING)
	 */
	@Override
	public Optional<Appartenance> getById(Object... id) throws Exception {
		try(PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Appartenance WHERE Nom_Equipe = ? AND Annee = ? AND Nom_tournoi = ? AND Libelle = ?")){
			getById.setString(1, (String)id[0]);
			getById.setInt(2, (Integer)id[1]);
			getById.setString(3, (String) id[2]);
			getById.setString(3, (String)id[2]);

			ResultSet resultat = getById.executeQuery();
			Appartenance appartenance = null;
			appartenance = findAppartenance(resultat, appartenance);
			return Optional.ofNullable(appartenance);
		}
	}

	private Appartenance findAppartenance(ResultSet resultat, Appartenance appartenance)
			throws SQLException, Exception {
		if (resultat.next()) {
			appartenance = new Appartenance(
					daoequipe.getById(resultat.getString("Nom_Equipe")).get(),
					daopoule.getById(resultat.getInt("Annee"),resultat.getString("Nom_tournoi"),resultat.getString("Libelle")).get());
		}
		return appartenance;
	}

	/**
	 * Ajoute une association d'une équipe et d'une poule à partir d'un objet Appartenance
	 */
	@Override
	public boolean add(Appartenance value) throws Exception {
		try(PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Appartenance(Nom_Equipe,Annee,Nom_Tournoi,Libelle) values (?,?,?,?)")){
			add.setString(1, value.getEquipe().getNom());
			add.setInt(2, value.getPoule().getTournoi().getSaison().getAnnee());
			add.setString(3, value.getPoule().getTournoi().getNom());
			add.setString(4, value.getPoule().getLibelle().toString());
			return add.execute();
		}
	}

	/**
	 * ne pas utiliser
	 */
	@Override
	public boolean update(Appartenance value) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * supprime une association d'une équipe et d'une poule
	 * Les paramètres sont placés dans cet ordre : Nom_Equipe (STRING) , Annee (INTEGER) , Nom_tournoi (STRING) , Libelle (STRING) 
	 *  
	 */
	@Override
	public boolean delete(Object... value) throws Exception {
		try(PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Appartenance where Nom_Equipe = ? AND Annee = ? AND Nom_tournoi = ? AND Libelle = ?")){
			delete.setString(1,(String)value[0]);
			delete.setInt(2,(Integer)value[1]);
			delete.setString(3, (String) value[2]);
			delete.setString(4, (String)value[3]);
			
			return delete.execute();
		}
	}

	/**
	 * Renvoie toutes les équipes d'une poule pour un tournoi donné
	 * Les paramètres sont placés dans cet ordre : Nom_tournoi (STRING), Annee (INTEGER) , Libelle (STRING)
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List<Equipe> getEquipeByPoule(Object... value) throws Exception {
		try(PreparedStatement getAll = connexion.getConnection().prepareStatement("SELECT * FROM Appartenance where Nom_Tournoi = ? AND Annee = ? AND Libelle = ?")){
			getAll.setString(1, (String)value[0]);
			getAll.setInt(2, (Integer)value[1]);
			getAll.setString(3, (String)value[2]);
			ResultSet resultat = getAll.executeQuery();
			List<Equipe> sortie = new ArrayList<>();
			while(resultat.next()) {
				Equipe equipe = daoequipe.getById(resultat.getString("Nom_Equipe")).get();
				sortie.add(equipe);
			}
			return sortie;
		}
	}

	/**
	 * Renvoie toutes les équipes d'un tournoi
	 * Les paramètres sont placés dans cet ordre : Nom_tournoi (STRING), Annee (INTEGER)
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List<Equipe> getEquipeByTournoi(Object... value) throws Exception {
		try(PreparedStatement getAll = connexion.getConnection().prepareStatement("SELECT * FROM Appartenance where Nom_Tournoi = ? AND Annee = ?")){
			getAll.setString(1, (String)value[0]);
			getAll.setInt(2, (Integer)value[1]);
			ResultSet resultat = getAll.executeQuery();
			List<Equipe> sortie = new ArrayList<>();
			while(resultat.next()) {
				Equipe equipe = daoequipe.getById(resultat.getString("Nom_Equipe")).get();
				sortie.add(equipe);
			}
			return sortie;
		}
	}
	
	/**
	 * Renvoie toutes les poules pour une équipe pour un tournoi donné
	 * Les paramètres sont placés dans cet ordre : Nom_Equipe (STRING), Nom_tournoi (STRING) , Annee (INTEGER)
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List<Poule> getPouleByEquipe(Object... value) throws Exception {
		try(PreparedStatement getEquipeByPoule = connexion.getConnection().prepareStatement("SELECT * FROM Appartenance where Nom_Equipe = ? AND Nom_Tournoi = ? AND Annee = ?")){
			getEquipeByPoule.setString(1, (String)value[0]);
			getEquipeByPoule.setString(2, (String)value[1]);
			getEquipeByPoule.setInt(3, (Integer)value[2]);
			ResultSet resultat = getEquipeByPoule.executeQuery();
			List<Poule> sortie = new ArrayList<>();
			while(resultat.next()) {
				sortie.add(daopoule.getById(resultat.getInt("Annee"),resultat.getString("Nom_tournoi"),resultat.getString("Libelle")).get());
			}
			return sortie;
		}
	}

	/**
	 * Renvoie toutes les tournoi pour une équipe 
	 * Les paramètres sont placés dans cet ordre : Nom_Equipe (STRING)
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List<Tournoi> getTournoiByEquipe(Object... value) throws Exception {
		try(PreparedStatement getTournoiByEquipe = connexion.getConnection().prepareStatement("SELECT * FROM Appartenance where Nom_Equipe = ?")){
			getTournoiByEquipe.setString(1, (String)value[0]);
			ResultSet resultat = getTournoiByEquipe.executeQuery();
			List<Tournoi> sortie = new ArrayList<>();
			while(resultat.next()) {
				sortie.add(daotournoi.getById(resultat.getString("Nom_tournoi"),resultat.getInt("Annee")).get());
			}
			return sortie;
		}
	}
	
	/**
	 * A partir d'un objet inscription (association d'une équipe et d'une saison, ici la saison précédente)
	 * renvoie la liste de tous les tournois où l'équipe était présente
	 * @param inscription
	 * @return
	 * @throws Exception
	 */
	public List<Tournoi> getTournoiByEquipeForSaison(Inscription inscription) throws Exception {
		try(PreparedStatement getTournoiByEquipeForSaison = connexion.getConnection().prepareStatement("SELECT * FROM Appartenance where Nom_Equipe = ? AND Annee = ?")){
			getTournoiByEquipeForSaison.setString(1, inscription.getEquipe().getNom());
			getTournoiByEquipeForSaison.setInt(2, inscription.getSaison().getAnnee());
			ResultSet resultat = getTournoiByEquipeForSaison.executeQuery();
			List<Tournoi> sortie = new ArrayList<>();
			while(resultat.next()) {
				sortie.add(daotournoi.getById(resultat.getInt("Annee"),resultat.getString("Nom_tournoi")).get());
			}
			return sortie;
		}
	}

	@Override
	public String visualizeTable() throws Exception {
		String s = "_______________Appartenance_______________________" + "\n";
		List<Appartenance> l = this.getAll();
		for(Appartenance a : l) {
			s+=a.toString()+"\n";
		}
		s+="\n\n\n";
		return s;
	}
	
	
}