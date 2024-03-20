package dao;

import exceptions.EquipeCompleteException;
import exceptions.IdNotSetException;
import exceptions.JoueurException;
import modele.Equipe;
import modele.Joueur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoJoueur extends SuperDao implements Dao<Joueur, Integer> {

	private final DaoEquipe daoequipe;

	public DaoJoueur(Connexion connexion) {
        super(connexion);
		this.daoequipe = new DaoEquipe(connexion);
	}

	/**
	 * Crée la table joueur
	 */
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Joueur("
				+ "Id_Joueur INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
				+ "Pseudo VARCHAR(50),"
				+ "Nom_Equipe VARCHAR(50) NOT NULL,"
				+ "PRIMARY KEY(Id_Joueur),"
				+ "FOREIGN KEY(Nom_Equipe) REFERENCES Equipe(Nom_Equipe))";

		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
		}
	}

	/**
	 * Supprime la table joueur
	 *
	 */
	public static boolean dropTable(Connexion connexion) throws SQLException {
		try (Statement deleteTable = connexion.getConnection().createStatement()) {
			return deleteTable.execute("drop table Joueur");
		}
	}

	/**
	 * Renvoie tous les joueurs existants
	 */
	@Override
	public List<Joueur> getAll() throws JoueurException, EquipeCompleteException, SQLException {
		try (Statement getAll = super.getConnexion().getConnection().createStatement()) {
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Joueur");
			return getJoueurs(resultat);
		}
    }

	private List<Joueur> getJoueurs(ResultSet resultat) throws SQLException, EquipeCompleteException, JoueurException {
		List<Joueur> sortie = new ArrayList<>();
		while (resultat.next()) {
			Optional<Equipe> equipe = daoequipe.getById(resultat.getString(super.getConstants().getNomEquipe()));
			if (equipe.isPresent()) {
				Joueur joueur = new Joueur(resultat.getString(super.getConstants().getPseudo()), equipe.get());
				joueur.setId(resultat.getInt(super.getConstants().getIdJoueur()));
				sortie.add(joueur);
			}
		}
		return sortie;
	}

	/**
	 * Renvoie un joueur précis
	 * Les paramètres sont placés dans cet ordre : Id_Joueur (INTEGER)
	 */
	@Override
	public Optional<Joueur> getById(Integer... id) throws JoueurException, EquipeCompleteException, SQLException {
		try (PreparedStatement getById = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Joueur WHERE Id_Joueur = ?")) {
			getById.setInt(1, id[0]);
			ResultSet resultat = getById.executeQuery();
			if (resultat.next()) {
				Optional<Equipe> equipe = daoequipe.getById(resultat.getString(super.getConstants().getNomEquipe()));
				if (equipe.isPresent()) {
					Joueur joueur = new Joueur(resultat.getString(super.getConstants().getPseudo()), equipe.get());
					joueur.setId(resultat.getInt(super.getConstants().getIdJoueur()));
					return Optional.of(joueur);
				}
				return Optional.empty();

			}
			return Optional.empty();
		}
	}

	/**
	 * Ajoute un joueur à la table joueur à partir d'un objet joueur
	 */
	@Override
	public boolean add(Joueur value) throws SQLException {
		try (PreparedStatement add = super.getConnexion().getConnection().prepareStatement(
				"INSERT INTO Joueur(Pseudo,Nom_Equipe) values (?,?)")) {
			add.setString(1, value.getPseudo());
			add.setString(2, value.getNomEquipe());
			return add.execute();
		}
	}

	/**
	 * Update une ligne de la table joueur à partir d'un objet joueur
	 */
	@Override
	public boolean update(Joueur value) throws SQLException, IdNotSetException {
		try (PreparedStatement update = super.getConnexion().getConnection().prepareStatement(
				"UPDATE Joueur SET "
						+ "Pseudo = ?, "
						+ "Nom_Equipe = ? "
						+ "WHERE Id_Joueur = ?")) {
			update.setString(1, value.getPseudo());
			update.setString(2, value.getNomEquipe());
			update.setInt(3, value.getId());
			return update.execute();
		}
	}

	/**
	 * Supprime un joueur
	 * Les paramètres sont placés dans cet ordre : Id_Joueur (INTEGER)
	 */
	@Override
	public boolean delete(Integer... value) throws SQLException {
		try (PreparedStatement delete = super.getConnexion().getConnection().prepareStatement(
				"DELETE FROM Joueur where Id_Joueur = ?")) {
			delete.setInt(1, value[0]);
			return delete.execute();
		}
	}

	/**
	 * Renvoie tous les joueurs d'une équipe en passant en paramètre le nom de l'équipe
	 *
	 */
	public List<Joueur> getJoueurParEquipe(String nom) throws JoueurException, EquipeCompleteException, SQLException {
		try (PreparedStatement getAll = super.getConnexion().getConnection().prepareStatement("SELECT * FROM Joueur WHERE Nom_Equipe = ?")) {
			getAll.setString(1, nom);
			ResultSet resultat = getAll.executeQuery();
			return getJoueurs(resultat);
		}
	}

	@Override
	public String visualizeTable() throws JoueurException, EquipeCompleteException, SQLException {
		StringBuilder s = new StringBuilder("_______________Joueur_______________________" + "\n");
		List<Joueur> l = this.getAll();
		for (Joueur a : l) {
			s.append(a.toString()).append("\n");
		}
		s.append("\n\n\n");
		return s.toString();
	}
}
