package dao;

import exceptions.FausseDateException;
import modele.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static dao.DaoEquipe.getEquipes;
import static dao.DaoTournoi.getTournois;


public class DaoAppartenance extends SuperDao implements Dao<Appartenance, Object>  {
	
	private final DaoPoule daopoule;
	private final DaoEquipe daoequipe;
	private final DaoTournoi daotournoi;

	public DaoAppartenance(Connexion connexion) {
        super(connexion);
		this.daopoule = new DaoPoule(connexion);
		this.daoequipe = new DaoEquipe(connexion);
		this.daotournoi = new DaoTournoi(connexion);
	}

	/**
	 * Crée la table d'association appartenance qui associe les équipes et les poules
	 *
	 *
     */


	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql =
				"CREATE TABLE Appartenance("
						+ "Annee INT,"
						+ "Nom_Equipe VARCHAR(50),"
						+ "Nom_Tournoi VARCHAR(50),"
						+ "Libelle CHAR(1),"
						+ "PRIMARY KEY(Annee, Nom_tournoi, Nom_Equipe, Libelle),"
						+ "FOREIGN KEY(Annee, Nom_tournoi) REFERENCES Tournoi(Annee, Nom_tournoi),"
						+ "FOREIGN KEY(Nom_Equipe) REFERENCES Equipe(Nom_Equipe))";


		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
		}

	}

	/**
	 * Supprime la table Appartenance
	 *
     */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			return deleteTable.execute("drop table Appartenance");
		}
	}

	/**
	 * Renvoie toutes les associations de poules et d'équipes existantes
	 */
	@Override
	public List<Appartenance> getAll() throws SQLException, FausseDateException {
		try (Statement getAll = super.getConnexion().getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Appartenance");
			List<Appartenance> sortie = new ArrayList<>();
			generateListAppartenance(resultat, sortie);
			return sortie;
		}
    }

	private void generateListAppartenance(ResultSet resultat, List<Appartenance> sortie)
			throws SQLException, FausseDateException {
		while (resultat.next()) {
			Optional<Equipe> equipe = createOptionalEquipe(resultat);
			Optional<Poule> poule = createOptionalPoule(resultat);
			if (equipe.isPresent() && poule.isPresent()) {
				sortie.add(new Appartenance(equipe.get(), poule.get()));
			}
		}
	}

	private Optional<Equipe> createOptionalEquipe(ResultSet resultat) throws SQLException {
		return daoequipe.getById(resultat.getString(super.getConstants().getNomEquipe()));
	}

	private Optional<Poule> createOptionalPoule(ResultSet resultat) throws SQLException, FausseDateException {
		return daopoule.getById(resultat.getInt(super.getConstants().getAnnee()), resultat.getString(super.getConstants().getNomTournoi()), resultat.getString(super.getConstants().getLibelle()));
	}

	/**
	 * Renvoie une association précise
	 * Les paramètres sont placés dans cet ordre : Nom_Equipe (STRING), Annee (INTEGER) , Nom_tournoi (STRING) , Libelle (STRING)
	 */
	@Override
	public Optional<Appartenance> getById(Object... id) throws SQLException, FausseDateException {
		try (PreparedStatement getById = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Appartenance WHERE Nom_Equipe = ? AND Annee = ? AND Nom_tournoi = ? AND Libelle = ?")) {
			bindParam(getById, id);
			ResultSet resultat = getById.executeQuery();
			Appartenance appartenance = findAppartenance(resultat);
			return Optional.ofNullable(appartenance);
		}
    }

	private void bindParam(PreparedStatement getById, Object[] id) throws SQLException {
		getById.setString(1, (String) id[0]);
		getById.setInt(2, (Integer) id[1]);
		getById.setString(3, (String) id[2]);
		Character c = (Character)id[3];
		String libelle = c.toString();
		getById.setString(4, libelle);
	}

	private Appartenance findAppartenance(ResultSet resultat)
			throws SQLException, FausseDateException {
		Optional<Equipe> equipe = createOptionalEquipe(resultat);
		Optional<Poule> poule = createOptionalPoule(resultat);
		if (equipe.isPresent() && poule.isPresent()) {
			return new Appartenance(equipe.get(), poule.get());
		}
		return null;
	}

	/**
	 * Ajoute une association d'une équipe et d'une poule à partir d'un objet Appartenance
	 */
	@Override
	public boolean add(Appartenance value) throws SQLException {
		try (PreparedStatement add = super.getConnexion().getConnection().prepareStatement(
				"INSERT INTO Appartenance(Nom_Equipe,Annee,Nom_Tournoi,Libelle) values (?,?,?,?)")) {
			add.setString(1, value.getEquipe().getNom());
			add.setInt(2, value.getPoule().getTournoi().getSaison().getAnnee());
			add.setString(3, value.getPoule().getTournoi().getNom());
			add.setString(4, value.getPoule().getLibelle().toString());
			return add.execute();
		}
	}

	/**
	 * Ne pas utiliser
	 */
	@Override
	public boolean update(Appartenance value) throws SQLException {
		return false;
	}

	/**
	 * Supprime une association d'une équipe et d'une poule
	 * Les paramètres sont placés dans cet ordre : Nom_Equipe (STRING), Annee (INTEGER), Nom_tournoi (STRING), Libelle (STRING)
	 */
	@Override
	public boolean delete(Object... value) throws SQLException {
		try (PreparedStatement delete = super.getConnexion().getConnection().prepareStatement(
				"DELETE FROM Appartenance where Nom_Equipe = ? AND Annee = ? AND Nom_tournoi = ? AND Libelle = ?")) {
			bindParam(delete, value);

			return delete.execute();
		}
	}

	/**
	 * Renvoie toutes les équipes d'une poule pour une poule donnée
	 *
	 *
     */
	public List<Equipe> getEquipeByPoule(Object... value) throws SQLException {
		try(PreparedStatement getEquipeByPoule = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Appartenance where Nom_Tournoi = ? AND Annee = ? AND Libelle = ?")){
			getEquipeByPoule.setString(1,(String)value[0]);
			getEquipeByPoule.setInt(2,(Integer)value[1]);
			Character c = (Character)value[2];
			String libelle = c.toString();
			getEquipeByPoule.setString(3, libelle);
			return bindParam(getEquipeByPoule);
		}
	}

	private List<Equipe> bindParam(PreparedStatement getEquipeByPoule) throws SQLException {
		return getEquipes(getEquipeByPoule, daoequipe);
	}

	/**
	 * Renvoie toutes les équipes d'un tournoi
	 * Les paramètres sont placés dans cet ordre : Nom_tournoi (STRING), Annee (INTEGER)
	 *
	 *
     */
	public List<Equipe> getEquipeByTournoi(Object... value) throws SQLException {
		try (PreparedStatement getAll = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Appartenance where Nom_Tournoi = ? AND Annee = ?")) {
			getAll.setString(1, (String) value[0]);
			getAll.setInt(2, (Integer) value[1]);
			return bindParam(getAll);
		}
	}

	/**
	 * Renvoie toutes les poules pour une équipe 
	 * Les paramètres sont placés dans cet ordre : Nom_Equipe (STRING), 
	 *
     */
	public List<Poule> getPouleByEquipe(Object... value) throws SQLException, FausseDateException {
		try(PreparedStatement getEquipeByPoule = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Appartenance where Nom_Equipe = ?")){
			getEquipeByPoule.setString(1, (String)value[0]);
			ResultSet resultat = getEquipeByPoule.executeQuery();
			List<Poule> sortie = new ArrayList<>();
			while (resultat.next()) {
				Optional<Poule> poule = createOptionalPoule(resultat);
                poule.ifPresent(sortie::add);
			}
			return sortie;
		}
    }

	/**
	 * Renvoie tous les tournois pour une équipe
	 * Les paramètres sont placés dans cet ordre : Nom_Equipe (STRING)
	 *
	 *
	 *
     */
	public List<Tournoi> getTournoiByEquipe(Object... value) throws SQLException, FausseDateException {
		try(PreparedStatement getTournoiByEquipe = super.getConnexion().getConnection().prepareStatement("SELECT DISTINCT * FROM Appartenance where Nom_Equipe = ?")){
			getTournoiByEquipe.setString(1, (String)value[0]);
			ResultSet resultat = getTournoiByEquipe.executeQuery();
			return getTournois(resultat, daotournoi, super.getConstants());
		}
    }



	/**
	 * À partir d'un objet inscription (association d'une équipe et d'une saison, ici la saison précédente)
	 * renvoie la liste de tous les tournois où l'équipe était présente
	 *
	 *
	 *
     */
	public List<Tournoi> getTournoiByEquipeForSaison(Inscription inscription) throws SQLException, FausseDateException {
		try(PreparedStatement getTournoiByEquipeForSaison = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Appartenance where Nom_Equipe = ? AND Annee = ?")){
			getTournoiByEquipeForSaison.setString(1, inscription.getEquipe().getNom());
			getTournoiByEquipeForSaison.setInt(2, inscription.getSaison().getAnnee());
			ResultSet resultat = getTournoiByEquipeForSaison.executeQuery();
			return getTournois(resultat, daotournoi, super.getConstants());
		}
	}

	@Override
	public String visualizeTable() throws SQLException, FausseDateException {
		StringBuilder s = new StringBuilder("_______________Appartenance_______________________" + "\n");
		List<Appartenance> l = this.getAll();
		for (Appartenance a : l) {
			s.append(a.toString()).append("\n");
		}
		s.append("\n\n\n");
		return s.toString();
	}


}