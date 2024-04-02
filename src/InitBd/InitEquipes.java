package InitBd;

import dao.Connexion;
import dao.DaoEquipe;
import modele.Equipe;
import modele.Pays;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitEquipes {

	private static final Logger LOGGER = Logger.getLogger("Initialisation des équipes");

	public static void initEquipes() {
		try {
			DaoEquipe daoEquipe = new DaoEquipe(Connexion.getConnexion());

			// Création et ajout des équipes
			daoEquipe.add(new Equipe("terros", Pays.FRANCE));
			daoEquipe.add(new Equipe("lion-rouge", Pays.FRANCE));
			daoEquipe.add(new Equipe("shark", Pays.FRANCE));
			daoEquipe.add(new Equipe("goule", Pays.FRANCE));
			daoEquipe.add(new Equipe("dragon", Pays.FRANCE));
			daoEquipe.add(new Equipe("aigle", Pays.FRANCE));
			daoEquipe.add(new Equipe("bear", Pays.FRANCE));
			daoEquipe.add(new Equipe("chevarcher", Pays.FRANCE));
			daoEquipe.add(new Equipe("loup", Pays.FRANCE));
			daoEquipe.add(new Equipe("mort", Pays.FRANCE));

			LOGGER.info("Initialisation des équipes terminée avec succès.");
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Erreur lors de l'initialisation des équipes : " + e.getMessage(), e);
		}
	}
}
