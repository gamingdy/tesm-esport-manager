package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

private Connection connexion;
	
	public Connexion() {
		String dirProjetJava = System.getProperty("user.dir");		
		System.setProperty("derby.system.home", dirProjetJava+"/TESM");	
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			String urlConnexion = "jdbc:derby:TESM;create=true";			
			connexion = DriverManager.getConnection(urlConnexion);
			System.out.println("Connexion OK");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			connexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnexion() {
		return connexion;
	}
}
