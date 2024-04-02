package InitBd;

import dao.Connexion;
import dao.DaoAppartenance;
import dao.DaoPoule;
import modele.Appartenance;
import modele.Equipe;
import modele.Poule;
import modele.Tournoi;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitPoules {

	private static final Logger LOGGER = Logger.getLogger("Initialisation des poules");

	public static void initPoules(Tournoi tournoi, List<Equipe> equipes) {
		try {
			DaoPoule daoPoule = new DaoPoule(Connexion.getConnexion());
			DaoAppartenance daoAppartenance = new DaoAppartenance(Connexion.getConnexion());

			Poule poule = new Poule(tournoi, 'A'); // Création de la poule

			daoPoule.add(poule); // Ajout de la poule à la base de données

			// Ajout des équipes à la poule
			for (Equipe equipe : equipes) {
				daoAppartenance.add(new Appartenance(equipe, poule));
			}

			LOGGER.info("Initialisation de la poule pour le tournoi " + tournoi.getNom() + " terminée avec succès.");
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Erreur lors de l'initialisation de la poule : " + e.getMessage(), e);
		}
	}
}
