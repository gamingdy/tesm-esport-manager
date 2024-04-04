package init_bd;

import dao.Connexion;
import dao.DaoSaison;
import modele.Saison;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitSaisons {

	private static final Logger LOGGER = Logger.getLogger("Initialisation des saisons");

	public static void initSaisons() {
		try {
			DaoSaison daoSaison = new DaoSaison(Connexion.getConnexion());
			Saison saison2024 = new Saison(2024);
			Saison saison2023 = new Saison(2023);
			daoSaison.add(saison2024);
			daoSaison.add(saison2023);
			LOGGER.info("Initialisation des saisons terminée avec succès.");
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Erreur lors de l'initialisation des saisons : " + e.getMessage(), e);
		}
	}
}
