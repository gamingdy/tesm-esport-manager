package initBd;

import dao.*;
import exceptions.FausseDateException;
import modele.*;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitTournois {
	private static Tournoi tournoi1;
	private static Tournoi tournoi2;
	private static Tournoi tournoi2023;

	private static final Logger LOGGER = Logger.getLogger("Initialisation des tournois");

	public static void initTournois() {
		try {
			Connexion connexion = Connexion.getConnexion();
			DaoArbitre daoArbitre = new DaoArbitre(connexion);
			DaoSaison daoSaison = new DaoSaison(connexion);
			DaoTournoi daoTournoi = new DaoTournoi(connexion);
			DaoArbitrage daoArbitrage = new DaoArbitrage(connexion);

			// Récupération de la saison
			Optional<Saison> saison2023 = daoSaison.getById(2023);
			Optional<Saison> saison2024 = daoSaison.getById(2024);

			// Récupération des arbitres
			Arbitre arbitre1 = daoArbitre.getAll().get(0);
			Arbitre arbitre2 = daoArbitre.getAll().get(1);
			if(saison2023.isPresent()&&saison2024.isPresent()) {
				// Création des tournois
				tournoi1 = createTournoi("RLCS", 2024, 12, 1, 2024, 12, 30, Niveau.LOCAL, saison2024.get(), daoTournoi, daoArbitrage, arbitre1);
				tournoi2 = createTournoi("RLRS", 2024, 1, 9, 2024, 1, 30, Niveau.LOCAL, saison2024.get(), daoTournoi, daoArbitrage, arbitre2);
				tournoi2023=createTournoi("RLRS", 2023, 1, 1, 2023, 1, 30, Niveau.LOCAL, saison2023.get(), daoTournoi, daoArbitrage, arbitre1);
				LOGGER.info("Initialisation des tournois terminée avec succès.");
			} else {
				LOGGER.log(Level.SEVERE, "Erreur lors de l'initialisation des tournois : la saison 2023 n'existe pas.");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Erreur lors de l'initialisation des tournois : " + e.getMessage(), e);
		}
	}

	private static Tournoi createTournoi(String nom, int debutAnnee, int debutMois, int debutJour, int finAnnee, int finMois, int finJour, Niveau niveau, Saison saison, DaoTournoi daoTournoi, DaoArbitrage daoArbitrage, Arbitre arbitre) throws SQLException {
		try {
			CustomDate debutTournoi = new CustomDate(debutAnnee, debutMois, debutJour);
			CustomDate fin = new CustomDate(finAnnee, finMois, finJour);
			CompteArbitre compteArbitre = new CompteArbitre(arbitre.getNom(), "rlcs");
			Tournoi tournoi = new Tournoi(saison, nom, debutTournoi, fin, niveau, compteArbitre);
			daoTournoi.add(tournoi);
			daoArbitrage.add(new Arbitrage(arbitre, tournoi));
			return tournoi; // Retourne le tournoi créé
		} catch (FausseDateException e) {
			throw new SQLException(e.getMessage());
		}
	}
	public static Tournoi getTournoi2023(){
		return tournoi2023;
	}

	public static Tournoi getTournoi1(){
		return tournoi1;
	}

	public static Tournoi getTournoi2(){
		return tournoi2;
	}

}
