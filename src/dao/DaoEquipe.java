package dao;

import modele.Equipe;
import modele.Joueur;
import modele.Matche;
import modele.Pays;
import modele.Poule;
import modele.Saison;
import modele.Tournoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DaoEquipe extends SuperDao implements Dao<Equipe, String> {



	public DaoEquipe(Connexion connexion) {
        super(connexion);

	}

	/**
	 * Crée la table equipe
	 *
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Equipe (" +
				"Nom_Equipe VARCHAR(50)," +
				"Pays_Equipe VARCHAR(50)," +
				"PRIMARY KEY (Nom_Equipe))";
		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
		}
	}

	/**
	 * Supprime la table Equipe
	 *
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			return deleteTable.execute("drop table Equipe");
		}
	}

	/**
	 * Renvoie toutes les équipes existantes
	 */
	@Override
	public List<Equipe> getAll() throws SQLException {
		try (Statement getAll = super.getConnexion().getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Equipe");
			List<Equipe> sortie = new ArrayList<>();
			while (resultat.next()) {
				sortie.add(createEquipe(resultat));
			}
			return sortie;
		}
	}

	private Equipe createEquipe(ResultSet resultat) throws SQLException {
		return new Equipe(
				resultat.getString(super.getConstants().getNomEquipe()),
				Pays.valueOf(resultat.getString(super.getConstants().getPaysEquipe())));
	}

	/**
	 * Renvoie une équipe précise
	 * Les paramètres sont placés dans cet ordre : Nom_Equipe (STRING)
	 */
	@Override
	public Optional<Equipe> getById(String... nom) throws SQLException {
		try (PreparedStatement getById = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Equipe WHERE Nom_Equipe = ?")) {
			getById.setString(1, nom[0]);
			ResultSet resultat = getById.executeQuery();
			if (resultat.next()) {
				return Optional.of(createEquipe(resultat));
			}
			return Optional.empty();
		}
	}

	/**
	 * Ajoute une équipe à la table équipe à partir d'un objet équipe
	 */
	@Override
	public boolean add(Equipe value) throws SQLException {
		try (PreparedStatement add = super.getConnexion().getConnection().prepareStatement(
				"INSERT INTO Equipe(Nom_Equipe,Pays_Equipe) values (?,?)")) {
			add.setString(1, value.getNom());
			add.setString(2, value.getPays().name());
			return add.execute();
		}
	}

	/**
	 * update une équipe à partir d'un objet équipe
	 */
	@Override
	public boolean update(Equipe value) throws SQLException {
		try (PreparedStatement update = super.getConnexion().getConnection().prepareStatement(
				"UPDATE Equipe SET "
						+ "Pays_Equipe = ?"
						+ "WHERE Nom_Equipe = ?")) {
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
		try (PreparedStatement delete = super.getConnexion().getConnection().prepareStatement(
				"DELETE FROM Equipe where Nom_Equipe = ?")) {
			delete.setString(1, value[0]);
			List<Joueur> joueurs = FactoryDAO.getDaoJoueur(super.getConnexion()).getJoueurParEquipe(value[0]);
			List<Saison> saisons = FactoryDAO.getDaoInscription(super.getConnexion()).getSaisonByEquipe(value[0]);
			List<Tournoi> tournois = FactoryDAO.getDaoAppartenance(super.getConnexion()).getTournoiByEquipe(value[0]);
			List<Poule> poules = new LinkedList<>();
			Optional<Equipe> equipe = FactoryDAO.getDaoEquipe(super.getConnexion()).getById(value[0]);
			if (equipe.isPresent()) {
				List<Matche> matches = FactoryDAO.getDaoMatche(super.getConnexion()).getMatchByEquipe(equipe.get());
				for (Tournoi t : tournois) {
					poules.addAll(FactoryDAO.getDaoAppartenance(super.getConnexion()).getPouleByEquipe(value[0], t.getNom(), t.getSaison().getAnnee()));
				}
				for (Joueur j : joueurs) {
					FactoryDAO.getDaoJoueur(super.getConnexion()).delete(j.getId());
				}
				for (Saison s : saisons) {
					FactoryDAO.getDaoInscription(super.getConnexion()).delete(s.getAnnee(), value[0]);
				}
				for (Poule p : poules) {
					FactoryDAO.getDaoAppartenance(super.getConnexion()).delete(value[0], p.getTournoi().getSaison().getAnnee(), p.getTournoi().getNom(), p.getLibelle());
				}
				for (Matche m : matches) {
					FactoryDAO.getDaoMatche(super.getConnexion()).delete(m.getId());
				}
				return delete.execute();
			} else {
				return false;
			}

    	}
	}

	static List<Equipe> getEquipes(PreparedStatement getEquipeByPoule, DaoEquipe daoequipe) throws SQLException {
		ResultSet resultat = getEquipeByPoule.executeQuery();
		List<Equipe> sortie = new ArrayList<>();
		while (resultat.next()) {
			Optional<Equipe> equipe = daoequipe.getById(resultat.getString("Nom_Equipe"));
			equipe.ifPresent(sortie::add);
		}
		return sortie;
	}

	@Override
	public String visualizeTable() throws SQLException {
		StringBuilder s = new StringBuilder("_______________Equipe_______________________" + "\n");
		List<Equipe> l = this.getAll();
		for (Equipe a : l) {
			s.append(a.toString()).append("\n");
		}
		s.append("\n\n\n");
		return s.toString();
	}
}
