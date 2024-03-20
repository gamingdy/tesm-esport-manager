package dao;

import java.sql.SQLException;

public class DBReset {

	private static final Connexion c = Connexion.getConnexion();
	
	public static void main(String[] args) throws SQLException {
		DBSuppression.deleteAllTable(c);
		DBGeneration.createAllTables(c);
	}

}
