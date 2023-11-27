package dao;

import java.sql.SQLException;

public class DBSuppression {

	public static void main(String[] args) throws SQLException {
		Connexion c = Connexion.getConnexion();
		DaoAppartenance.dropTable(c);
		DaoArbitrage.dropTable(c);
		DaoSelection.dropTable(c);
		DaoInscription.dropTable(c);
		DaoPoule.dropTable(c);
		DaoPartie.dropTable(c);
		DaoMatche.dropTable(c);
		DaoTournoi.dropTable(c);
		DaoNiveau.dropTable(c);
		DaoSaison.dropTable(c);
		DaoArbitre.dropTable(c);
		DaoJoueur.dropTable(c);
		DaoEquipe.dropTable(c);
		System.out.println("Toutes les tables ont été correctement supprimées");
		c.stop();

	}

}
