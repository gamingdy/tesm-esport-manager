package dao;

import java.sql.SQLException;

public class DBGeneration {

	private DBGeneration() {}

	public static void createAllTables(Connexion c) throws SQLException {
		DaoEquipe.createTable(c);
		DaoJoueur.createTable(c);
		DaoArbitre.createTable(c);
		DaoSaison.createTable(c);
		DaoNiveau.createTable(c);
		DaoTournoi.createTable(c);
		DaoMatche.createTable(c);
		DaoPartie.createTable(c);
		DaoPoule.createTable(c);
		DaoInscription.createTable(c);
		DaoSelection.createTable(c);
		DaoArbitrage.createTable(c);
		DaoAppartenance.createTable(c);
	}

}
