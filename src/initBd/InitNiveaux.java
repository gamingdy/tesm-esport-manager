package initBd;

import dao.Connexion;
import dao.DaoNiveau;
import modele.Niveau;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitNiveaux {

	private static final Logger LOGGER = Logger.getLogger("Initialisation des niveaux");

	public static void initNiveaux() {
		try {
			DaoNiveau daoNiveau = new DaoNiveau(Connexion.getConnexion());
			for (Niveau niveau : Niveau.values()) {
				daoNiveau.add(niveau);
			}
			LOGGER.info("Initialisation des niveaux terminée avec succès.");
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Erreur lors de l'initialisation des niveaux : " + e.getMessage(), e);
		}
	}
}
