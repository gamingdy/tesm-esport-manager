package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import modele.Equipe;
import modele.Joueur;
import modele.Matche;
import modele.Partie;
import modele.Pays;
import modele.Poule;
import modele.Saison;
import modele.Tournoi;

public class DaoEquipe implements Dao<Equipe,String>{

	private Connexion connexion;

	public DaoEquipe(Connexion connexion) {
		this.connexion = connexion;
	}

	/**
	 * Crée la table equipe
	 * @param connexion
	 * @throws SQLException
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Equipe (" +
				"Nom_Equipe VARCHAR(50)," +
				"Pays_Equipe VARCHAR(50)," +
				"PRIMARY KEY (Nom_Equipe))";
		try(Statement createTable = connexion.getConnection().createStatement()){
			createTable.execute(createTableSql);
			System.out.println("Table 'Equipe' créée avec succès");
		}
	}

	/**
	 * Supprime la table Equipe
	 * @param connexion
	 * @return
	 * @throws SQLException
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try(Statement deleteTable= connexion.getConnection().createStatement()){
			System.out.println("Table 'Equipe' supprimée avec succès");
			return deleteTable.execute("drop table Equipe");
		}
	}

	/**
	 * Renvoie toutes les équipes existantes 
	 */
	@Override
	public List<Equipe> getAll() throws Exception {
		try(Statement getAll = connexion.getConnection().createStatement()){
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Equipe");
			List<Equipe> sortie = new ArrayList<>();
			while(resultat.next()) {
				Equipe equipe = new Equipe(
						resultat.getString("Nom_Equipe"),
						Pays.valueOf(resultat.getString("Pays_Equipe")));
				sortie.add(equipe);
			}
			return sortie;
		}
	}

	/**
	 * Renvoie une équipe précise
	 * Les paramètres sont placés dans cet ordre : Nom_Equipe (STRING)
	 */
	@Override
	public Optional<Equipe> getById(String... nom) throws Exception {
		try(PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Equipe WHERE Nom_Equipe = ?")){
			getById.setString(1, nom[0]);
			ResultSet resultat = getById.executeQuery();
			Equipe equipe = null;
			if (resultat.next()) {
				equipe = new Equipe(
						resultat.getString("Nom_Equipe"),
						Pays.valueOf(resultat.getString("Pays_Equipe")));
				
			}
			return Optional.ofNullable(equipe);
		}
	}

	/**
	 * Ajoute une équipe à la table équipe à partir d'un objet équipe
	 */
	@Override
	public boolean add(Equipe value) throws Exception {
		try(PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Equipe(Nom_Equipe,Pays_Equipe) values (?,?)")){
			add.setString(1, value.getNom());
			add.setString(2, value.getPays().name());
			return add.execute();
		}
	}

	/**
	 * update une équipe à partir d'un objet équipe
	 */
	@Override
	public boolean update(Equipe value) throws Exception {
		try(PreparedStatement update = connexion.getConnection().prepareStatement(
				"UPDATE Equipe SET "
						+"Pays_Equipe = ?"
						+"WHERE Nom_Equipe = ?")) {
			update.setString(1, value.getPays().name());
			update.setString(2, value.getNom());
			return update.execute();
		}
	}

	/**
	 * Supprime une équipe de la table equipe
	 * Les paramètres sont placés dans cet ordre : Nom_Equipe (STRING)
	 */
	@Override
	public boolean delete(String... value) throws Exception {
		try(PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Equipe where Nom_Equipe = ?")){
			delete.setString(1,value[0]);
			List<Joueur> joueurs = FactoryDAO.getDaoJoueur(connexion).getJoueurParEquipe(value[0]);
			List<Saison> saisons = FactoryDAO.getDaoInscription(connexion).getSaisonByEquipe(value[0]);
			List<Tournoi> tournois = FactoryDAO.getDaoAppartenance(connexion).getTournoiByEquipe(value[0]);
			List<Poule> poules = new LinkedList<>();
			List<Matche> matches = FactoryDAO.getDaoMatche(connexion).getMatchByEquipe(FactoryDAO.getDaoEquipe(connexion).getById(value[0]).get());
			for(Tournoi t : tournois) {
				for(Poule p : FactoryDAO.getDaoAppartenance(connexion).getPouleByEquipe(value[0],t.getNom(),t.getSaison().getAnnee())) {
					poules.add(p);
				}
			}
			for(Joueur j : joueurs) {
				FactoryDAO.getDaoJoueur(connexion).delete(j.getId());
			}
			for(Saison s : saisons) {
				FactoryDAO.getDaoInscription(connexion).delete(s.getAnnee(),value[0]);
			}
			for(Poule p : poules) {
				FactoryDAO.getDaoAppartenance(connexion).delete(value[0],p.getTournoi().getSaison().getAnnee(),p.getTournoi().getNom(),p.getLibelle());
			}
			for(Matche m : matches) {
				FactoryDAO.getDaoMatche(connexion).delete(m.getId());
			}
			 
			return delete.execute();
		}
	}
	
	@Override
	public String visualizeTable() throws Exception {
		String s = "_______________Equipe_______________________" + "\n";
		List<Equipe> l = this.getAll();
		for(Equipe a : l) {
			s+=a.toString()+"\n";
		}
		s+="\n\n\n";
		return s;
	}
}
