package initBd;

import dao.*;
import modele.*;
import exceptions.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitMatches {

	private static final Logger LOGGER = Logger.getLogger("Initialisation des matches");

	public static void initMatches() {
		try {
			Connexion connexion = Connexion.getConnexion();
			DaoMatche daoMatche = new DaoMatche(connexion);
			DaoPartie daoPartie = new DaoPartie(connexion);
			DaoAppartenance daoAppartenance = new DaoAppartenance(connexion);
			DaoTournoi daoTournoi = new DaoTournoi(connexion);

			List<Tournoi> tournois = daoTournoi.getAll();

			for (Tournoi tournoi : tournois) {
				LOGGER.info("Initialisation des matches pour le tournoi : " + tournoi.getNom());
				List<Equipe> equipes = daoAppartenance.getEquipeByTournoi(tournoi.getNom(), tournoi.getSaison().getAnnee());
				LOGGER.info("Nombre d'équipes pour le tournoi " + tournoi.getNom() + " : " + equipes.size());
				// Jouer des matchs dans les poules
				jouerMatchsDansPoule(daoMatche, equipes, daoPartie, tournoi);
			}

			LOGGER.info("Initialisation des matches terminée avec succès.");
		} catch (SQLException | FausseDateException e) {
			LOGGER.log(Level.SEVERE, "Erreur lors de l'initialisation des matches : " + e.getMessage(), e);
		}
	}

	private static void jouerMatchsDansPoule(DaoMatche daoMatch, List<Equipe> equipesPoule,DaoPartie daoPartie,Tournoi tournoi) throws SQLException, FausseDateException {
		for (int i = 0; i < equipesPoule.size(); i++) {
			LOGGER.info("Initialisation des matchs pour l'équipe " + equipesPoule.get(i).getNom());
			for (int j = i + 1; j < equipesPoule.size(); j++) {
				LOGGER.info("Initialisation du match " + (i + 1) + " vs " + (j + 1));
				CustomDate date = tournoi.getDebut();
				Matche match = new Matche(1, date, Categorie.POULE, equipesPoule.get(i), equipesPoule.get(j),tournoi);
				try {
					daoMatch.add(match);
				}catch (SQLException e){
					LOGGER.severe("ERREUR"+e.getMessage());
				}
				Partie partie=new Partie(match);
				try{
					daoPartie.add(partie);
					partie.setVainqueur(equipesPoule.get(1));
					daoPartie.update(partie);
					match.setVainqueur(equipesPoule.get(1));
					daoMatch.update(match);
				}catch (IdNotSetException | GagnantNonChoisiException e ){
					LOGGER.severe(e.getMessage());
				}

			}
		}
		try {
			List<Matche> matchs = daoMatch.getMatchByTournoi(tournoi.getSaison().getAnnee(), tournoi.getNom());
			LOGGER.info("Nombre de matchs joués pour le tournoi " + tournoi.getNom() + " : " + matchs.size());
		} catch (SQLException | MemeEquipeException e) {
			LOGGER.severe(e.getMessage());
		}
	}

	}


