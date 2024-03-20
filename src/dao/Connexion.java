package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

	private Connection connection;
	private static Connexion connexion;

	private Connexion() {
		String dirProjetJava = System.getProperty("user.dir");
		System.setProperty("derby.system.home", dirProjetJava + "/TESM");
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			String urlConnexion = "jdbc:derby:TESM;create=true";
			connection = DriverManager.getConnection(urlConnexion);
		} catch (SQLException e) {
			e.fillInStackTrace();
		}
	}

	public static synchronized Connexion getConnexion() {
		if (connexion == null) {
			connexion = new Connexion();
		}
		return connexion;
	}

	public Connection getConnection() {
		return this.connection;
	}


}
