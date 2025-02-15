package init_bd;

import dao.Connexion;
import dao.DBGeneration;
import dao.DBSuppression;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ESporterManagerInitBDD {
	private static final Logger LOGGER = Logger.getLogger("Initialisation bd");

	public static void main(String[] args) {
		try {
			initDatabase();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public static void initDatabase() throws SQLException {
		try {
			Connexion c = Connexion.getConnexion();
			DBSuppression.deleteAllTable(c);
			DBGeneration.createAllTables(c);
			initData();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private static void initData(){
		InitNiveaux.initNiveaux();
		InitSaisons.initSaisons();
		InitArbitres.initArbitres();
		InitEquipes.initEquipes();
		InitTournois.initTournois();
		InitAppartenance.initAppartenance();
		InitMatches.initMatches();
		InitInscriptions.initInscriptions();
	}
}
