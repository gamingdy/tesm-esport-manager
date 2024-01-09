package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import exceptions.FausseDateException;
import modele.Appartenance;
import modele.Arbitre;
import modele.Categorie;
import modele.CompteArbitre;
import modele.CustomDate;
import modele.Equipe;
import modele.Matche;
import modele.Niveau;
import modele.Poule;
import modele.Saison;
import modele.Tournoi;

public class DaoTournoi implements Dao<Tournoi, Object> {

	private Connexion connexion;

	public DaoTournoi(Connexion connexion) {
		this.connexion = connexion;
	}

	/**
	 * Crée la table Tournoi
	 *
	 * @param connexion
	 * @throws SQLException
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Tournoi("
				+ "Annee INT,"
				+ "Nom_tournoi VARCHAR(50),"
				+ "Date_Debut DATE,"
				+ "Date_Fin DATE,"
				+ "Username_Compte_Arbitre VARCHAR(50),"
				+ "Mot_De_Passe_Compte_Arbitre VARCHAR(50),"
				+ "Libelle_Niveau VARCHAR(50) NOT NULL,"
				+ "PRIMARY KEY(Annee, Nom_tournoi),"
				+ "FOREIGN KEY(Annee) REFERENCES Saison(Annee),"
				+ "FOREIGN KEY(Libelle_Niveau) REFERENCES Niveau(Libelle_Niveau))";

		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
			System.out.println("Table 'Tournoi' créée avec succès");
		}
	}

	/**
	 * Supprime la table Tournoi
	 *
	 * @param connexion
	 * @return
	 * @throws SQLException
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement();) {
			System.out.println("Table 'Tournoi' supprimée avec succès");
			return deleteTable.execute("drop table Tournoi");
		}
	}

	/**
	 * <<<<<<< Updated upstream
	 *
	 * @return une liste de tous les tournois existants
	 */
	@Override
	public List<Tournoi> getAll() throws Exception {
		try (Statement getAll = connexion.getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Tournoi");
			List<Tournoi> sortie = new ArrayList<>();
			while (resultat.next()) {
				Tournoi tournoi = new Tournoi(
						new Saison(resultat.getInt("Annee")),
						resultat.getString("Nom_Tournoi"),
						new CustomDate(resultat.getTimestamp("Date_Debut")),
						new CustomDate(resultat.getTimestamp("Date_Fin")),
						Niveau.valueOf(resultat.getString("Libelle_Niveau")),
						new CompteArbitre(
								resultat.getString("Username_Compte_Arbitre"),
								resultat.getString("Mot_De_Passe_Compte_Arbitre"))
				);
				sortie.add(tournoi);
			}
			return sortie;
		}
	}

	/**
	 * @return un tournoi en particulier
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER) , Nom_Tournoi (STRING)
	 */
	@Override
	public Optional<Tournoi> getById(Object... id) throws Exception {
		try (PreparedStatement getById = connexion.getConnection().prepareStatement("SELECT * FROM Tournoi WHERE Annee = ? AND Nom_Tournoi = ?")) {
			getById.setInt(1, (Integer) id[0]);
			getById.setString(2, (String) id[1]);
			ResultSet resultat = getById.executeQuery();
			Tournoi tournoi = null;
			if (resultat.next()) {
				tournoi = new Tournoi(
						new Saison(resultat.getInt("Annee")),
						resultat.getString("Nom_Tournoi"),
						new CustomDate(resultat.getTimestamp("Date_Debut")),
						new CustomDate(resultat.getTimestamp("Date_Fin")),
						Niveau.valueOf(resultat.getString("Libelle_Niveau").toUpperCase()),
						new CompteArbitre(
								resultat.getString("Username_Compte_Arbitre"),
								resultat.getString("Mot_De_Passe_Compte_Arbitre"))
				);
				
			}
			return Optional.ofNullable(tournoi);
		}
	}

	/**
	 * Ajoute un tournoi à la table tournoi à partir d'un objet tournoi
	 */
	@Override
	public boolean add(Tournoi value) throws Exception {
		try (PreparedStatement add = connexion.getConnection().prepareStatement(
				"INSERT INTO Tournoi(Annee,Nom_Tournoi,Date_Debut,Date_Fin,Username_Compte_Arbitre,Mot_De_Passe_Compte_Arbitre,Libelle_Niveau) values (?,?,?,?,?,?,?)")) {
			add.setInt(1, value.getSaison().getAnnee());
			add.setString(2, value.getNom());
			add.setTimestamp(3, value.getDebut().toSQL());
			add.setTimestamp(4, value.getFin().toSQL());
			add.setString(5, value.getCompteArbitre().getUsername());
			add.setString(6, value.getCompteArbitre().getHashMdp());
			add.setString(7, value.getNiveau().name());

			return add.execute();
		}
	}

	/**
	 * Update les valeurs d'un tournoi à partir d'un objet tournoi
	 */
	@Override
	public boolean update(Tournoi value) throws Exception {
		try (PreparedStatement update = connexion.getConnection().prepareStatement(
				"UPDATE Tournoi SET "
						+ "Date_Debut = ?, "
						+ "Date_Fin = ?, "
						+ "Username_Compte_Arbitre = ?, "
						+ "Mot_De_Passe_Compte_Arbitre = ?, "
						+ "Libelle_Niveau = ? "
						+ "WHERE Annee = ? AND Nom_Tournoi = ?")) {
			update.setInt(6, value.getSaison().getAnnee());
			update.setString(7, value.getNom());
			update.setTimestamp(1, value.getDebut().toSQL());
			update.setTimestamp(2, value.getFin().toSQL());
			update.setString(3, value.getCompteArbitre().getUsername());
			update.setString(4, value.getCompteArbitre().getHashMdp());
			update.setString(5, value.getNiveau().name());

			return update.execute();
		}
	}

	/**
	 * Supprime un tournoi de la table tournoi à partir de sa clé primaire
	 * <<<<<<< Updated upstream
	 * Les paramètres sont placés dans cet ordre : Annee (INTEGER) , Nom_Tournoi (STRING)
	 * <p>
	 * =======
	 * Les paramètres sont placés dans cet ordre : annee du tournoi , nom du tournoi
	 * >>>>>>> Stashed changes
	 */
	@Override
	public boolean delete(Object... value) throws Exception {
		try (PreparedStatement delete = connexion.getConnection().prepareStatement(
				"DELETE FROM Tournoi WHERE Annee = ? AND Nom_Tournoi = ?")) {
			delete.setInt(1, (Integer) value[0]);
			delete.setString(2, (String) value[1]);
			List<Matche> matches = FactoryDAO.getDaoMatche(connexion).getMatchByTournoi(value[0],value[1]);
			List<Poule> poules = FactoryDAO.getDaoPoule(connexion).getPouleByTournoi(FactoryDAO.getDaoTournoi(connexion).getById(value[0],value[1]).get());
			List<Arbitre> arbitres = FactoryDAO.getDaoArbitrage(connexion).getArbitreByTournoi(value[1],value[0]);
			for(Matche m : matches) {
				FactoryDAO.getDaoMatche(connexion).delete(m.getId());
			}
			for(Poule p : poules) {
				FactoryDAO.getDaoPoule(connexion).delete(p.getTournoi().getSaison().getAnnee(),p.getTournoi().getNom(),p.getLibelle());
			}
			for(Arbitre a : arbitres) {
				FactoryDAO.getDaoArbitrage(connexion).delete(a.getId(),value[0],value[1]);
			}
			return delete.execute();
		}
	}

	/**
	 * @param value
	 * @return le compte abritre associé à un tournoi
	 * <<<<<<< Updated upstream
	 * @throws SQLException Les paramètres sont placés dans cet ordre : Annee (INTEGER) , Nom_Tournoi (STRING)
	 *                      =======
	 * @throws SQLException Les paramètres sont placés dans cet ordre : annee du tournoi , nom du tournoi
	 *                      >>>>>>> Stashed changes
	 */
	public CompteArbitre getCompteArbitreByTournoi(Object... value) throws SQLException {
		try (PreparedStatement getCompteArbitreByTournoi = connexion.getConnection().prepareStatement(
				"SELECT Username_Compte_Arbitre,Mot_De_Passe_Compte_Arbitre FROM Tournoi WHERE Annee = ? AND Nom_Tournoi = ?")) {
			getCompteArbitreByTournoi.setInt(1, (Integer) value[0]);
			getCompteArbitreByTournoi.setString(2, (String) value[1]);
			ResultSet resultat = getCompteArbitreByTournoi.executeQuery();
			resultat.next();
			return new CompteArbitre(resultat.getString("Username_Compte_Arbitre"), resultat.getString("Mot_De_Passe_Compte_Arbitre"));
		}
	}

	/**
	 * @return Le tournoi actuel s'il existe sinon un optional null
	 * @throws SQLException
	 * @throws FausseDateException Vérifie la date système, si cette date est comprise entre la date de début et de fin du dernier tournoi actuel, alors cela renvoie le dernier tournoi sinon renvoie un optional null
	 */
	public Optional<Tournoi> getTournoiActuel() throws SQLException, FausseDateException {
		CustomDate c = new CustomDate(Timestamp.from(Instant.now()));
		try (PreparedStatement getCompteArbitreByTournoi = connexion.getConnection().prepareStatement(
				"SELECT * FROM Tournoi WHERE ? BETWEEN Date_Debut AND Date_Fin ")) {
			getCompteArbitreByTournoi.setTimestamp(1, c.toSQL());
			ResultSet resultat = getCompteArbitreByTournoi.executeQuery();
			Tournoi tournoi = null;
			if (resultat.next()) {
				tournoi = new Tournoi(
						new Saison(resultat.getInt("Annee")),
						resultat.getString("Nom_Tournoi"),
						new CustomDate(resultat.getTimestamp("Date_Debut")),
						new CustomDate(resultat.getTimestamp("Date_Fin")),
						Niveau.valueOf(resultat.getString("Libelle_Niveau").toUpperCase()),
						new CompteArbitre(
								resultat.getString("Username_Compte_Arbitre"),
								resultat.getString("Mot_De_Passe_Compte_Arbitre"))
				);
			}
			return Optional.ofNullable(tournoi);
		}
	}

	/**
	 * Renvoie la liste de tournoi en fonction d'une année précise
	 *
	 * @param annee
	 * @return
	 * @throws SQLException
	 * @throws FausseDateException
	 */

	public List<Tournoi> getTournoiBySaison(Saison saison) throws SQLException, FausseDateException {
		try (PreparedStatement getTournoiBySaison = connexion.getConnection().prepareStatement(
				"Select * From Tournoi Where Annee = ?")) {
			getTournoiBySaison.setInt(1, saison.getAnnee());
			ResultSet resultat = getTournoiBySaison.executeQuery();
			List<Tournoi> sortie = new ArrayList<>();
			while (resultat.next()) {
				sortie.add(
						new Tournoi(
								new Saison(resultat.getInt("Annee")),
								resultat.getString("Nom_Tournoi"),
								new CustomDate(resultat.getTimestamp("Date_Debut")),
								new CustomDate(resultat.getTimestamp("Date_Fin")),
								Niveau.valueOf(resultat.getString("Libelle_Niveau").toUpperCase()),
								new CompteArbitre(
										resultat.getString("Username_Compte_Arbitre"),
										resultat.getString("Mot_De_Passe_Compte_Arbitre"))
						));
			}
			return sortie;
		}
	}
	
	public List<Tournoi> getTournoiByNiveau(Niveau niveau) throws FausseDateException, SQLException {
		try (PreparedStatement getTournoiByNiveau = connexion.getConnection().prepareStatement(
				"SELECT *"
				+ "FROM Tournoi"
				+ "WHERE Libelle_Niveau = ?")) {
			getTournoiByNiveau.setString(1, niveau.name());
			ResultSet resultat = getTournoiByNiveau.executeQuery();
			List<Tournoi> sortie = new LinkedList<>();
			while(resultat.next()) {
				sortie.add(new Tournoi(
						new Saison(resultat.getInt("Annee")),
						resultat.getString("Nom_Tournoi"),
						new CustomDate(resultat.getTimestamp("Date_Debut")),
						new CustomDate(resultat.getTimestamp("Date_Fin")),
						Niveau.valueOf(resultat.getString("Libelle_Niveau").toUpperCase()),
						new CompteArbitre(
								resultat.getString("Username_Compte_Arbitre"),
								resultat.getString("Mot_De_Passe_Compte_Arbitre"))
					)
				);
				
			}
			return sortie;
		}
	}
	
	public List<Tournoi> getTournoiBetweenDate(CustomDate dateAvant, CustomDate dateApres) throws Exception {
		try(PreparedStatement getTournoiBetweenDate = connexion.getConnection().prepareStatement(
				"SELECT * "
				+ "FROM Tournoi "
				+ "WHERE (Date_Debut BETWEEN ? AND ?) "
				+ "OR (Date_Fin BETWEEN ? AND ?)"
				+ "OR (Date_Debut > ? AND Date_Fin < ?)")) {
			getTournoiBetweenDate.setTimestamp(1, dateAvant.toSQL());
			getTournoiBetweenDate.setTimestamp(2, dateApres.toSQL());
			getTournoiBetweenDate.setTimestamp(3, dateAvant.toSQL());
			getTournoiBetweenDate.setTimestamp(4, dateApres.toSQL());
			getTournoiBetweenDate.setTimestamp(5, dateAvant.toSQL());
			getTournoiBetweenDate.setTimestamp(6, dateApres.toSQL());
			ResultSet resultat = getTournoiBetweenDate.executeQuery();
			List<Tournoi> sortie = new ArrayList<>();
			Tournoi tournoi = null;
			while(resultat.next()) {
				tournoi = new Tournoi(
						new Saison(resultat.getInt("Annee")),
						resultat.getString("Nom_Tournoi"),
						new CustomDate(resultat.getTimestamp("Date_Debut")),
						new CustomDate(resultat.getTimestamp("Date_Fin")),
						Niveau.valueOf(resultat.getString("Libelle_Niveau").toUpperCase()),
						new CompteArbitre(
								resultat.getString("Username_Compte_Arbitre"),
								resultat.getString("Mot_De_Passe_Compte_Arbitre"))
				);
				sortie.add(tournoi);
			}
			return sortie;
		}
	}
	
	
	/**
	 * Récoupère tous les matchs d'une poule à partir d'un objet poule
	 * @param poule
	 * @return
	 * @throws Exception
	 */
	public List<Matche> getMatchesByPoule(Poule poule) throws Exception {
		//Création des DAO utilisées
		DaoAppartenance daoappartenance = new DaoAppartenance(connexion);
		DaoMatche daomatche = new DaoMatche(connexion);
		
		//Récupérations des équipes de la poule
		List<Equipe> equipes = daoappartenance.getEquipeByPoule(poule.getTournoi().getNom(),poule.getTournoi().getSaison().getAnnee(),poule.getLibelle());
		
		//Récupération des matchs du tournoi duquel appartient la poule si ce sont des matchs de catégorie "poule"
		List<Matche> matches = daomatche.getMatchesByTournoiFromCategorie(poule.getTournoi(), Categorie.POULE);
		
		//Création de la liste de sortie
		List<Matche> sortie = new ArrayList<>();
		
		//Boucle qui vérifie que si l'équipe que l'on observe est référencée comme Equipe 1 ou Equipe 2 du match que l'on observe, alors ce match fait partie de la poule
		for(Equipe e : equipes) {
			for(Matche m : matches) {
				if (m.getEquipe1().equals(e) || m.getEquipe2().equals(e)) {
					sortie.add(m);
				}
			}
		}
		
		//On renvoie la liste des matchs de la poule passée en paramètre
		return sortie;
	}
	
	/**
	 * Récoupère tous les matchs d'une poule à partir d'un objet poule
	 * @param poule
	 * @return
	 * @throws Exception
	 */
	public List<Matche> getMatchesByEquipe(Appartenance a) throws Exception {
		
		//Récupération des matchs du tournoi duquel appartient la poule si ce sont des matchs de catégorie "poule"
		List<Matche> matches = FactoryDAO.getDaoMatche(connexion).getMatchesByTournoiFromCategorie(a.getPoule().getTournoi(), Categorie.POULE);
		
		//Création de la liste de sortie
		List<Matche> sortie = new ArrayList<>();
		
		//Boucle qui vérifie que si l'équipe que l'on observe est référencée comme Equipe 1 ou Equipe 2 du match que l'on observe, alors ce match fait partie de la poule
		for(Matche m : matches) {
			if (m.getEquipe1().equals(a.getEquipe()) || m.getEquipe2().equals(a.getEquipe())) {
				sortie.add(m);
			}
		}
		
		//On renvoie la liste des matchs de la poule passée en paramètre
		return sortie;
	}
	
	@Override
	public String visualizeTable() throws Exception {
		String s = "_______________Tournoi_______________________" + "\n";
		List<Tournoi> l = this.getAll();
		for(Tournoi a : l) {
			s+=a.toString()+"\n";
		}
		s+="\n\n\n";
		return s;
	}

}
