package dao;

import java.sql.SQLException;

public class DBGeneration {

	public static void main(String[] args) throws SQLException {
		Connexion c = Connexion.getConnexion();
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
		
		System.out.println("Toutes les tables ont été correctement créées");
		c.stop();
	}

}
