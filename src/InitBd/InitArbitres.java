package InitBd;

import dao.Connexion;
import dao.DaoArbitre;
import modele.Arbitre;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitArbitres {

	private static final Logger LOGGER = Logger.getLogger("Initialisation des arbitres");

	public static void initArbitres() {
		try {
			DaoArbitre daoArbitre = new DaoArbitre(Connexion.getConnexion());

			// Création des arbitres
			Arbitre arbitre1 = new Arbitre("arbitre1", "arbitre1", "1234567891");
			Arbitre arbitre2 = new Arbitre("arbitre2", "arbitre2", "1234567892");

			// Ajout des arbitres à la base de données
			daoArbitre.add(arbitre1);
			daoArbitre.add(arbitre2);

			LOGGER.info("Initialisation des arbitres terminée avec succès.");
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Erreur lors de l'initialisation des arbitres : " + e.getMessage(), e);
		}
	}
}
