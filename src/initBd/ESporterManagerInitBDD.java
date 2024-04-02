package initBd;

import dao.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.FausseDateException;
import modele.*;

public class ESporterManagerInitBDD {
	private static final Logger LOGGER = Logger.getLogger("Initialisation bd");

	public static void main(String[] args) {
		try {
			initDatabase();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public static void initDatabase() throws SQLException {
		Connexion c = Connexion.getConnexion();
		try {
			DBSuppression.deleteAllTable(c);
			DBGeneration.createAllTables(c);
			initData(c);
		}catch(SQLException | FausseDateException e){
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private static void initData(Connexion c) throws SQLException, FausseDateException {
		DaoNiveau daoNiveau = new DaoNiveau(c);
		DaoSaison daoSaison = new DaoSaison(c);
		DaoTournoi daoTournoi = new DaoTournoi(c);
		DaoEquipe daoEquipe = new DaoEquipe(c);
		DaoInscription daoInscription = new DaoInscription(c);
		DaoArbitre daoArbitre = new DaoArbitre(c);
		DaoPoule daoPoule= new DaoPoule(c);
		DaoAppartenance daoAppartenance = new DaoAppartenance(c);

		initNiveaux(daoNiveau);
		initSaisons(daoSaison);
		initTournois(daoTournoi, daoArbitre, daoSaison);
		initEquipes(daoEquipe, 10); // Modifier le nombre d'équipes si nécessaire
		List<Saison> saisons = daoSaison.getAll();
		List<Equipe> equipes = daoEquipe.getAll();
		initInscriptions(daoInscription, saisons, equipes);
		List<Tournoi> tournois = daoTournoi.getAll(); // Obtenez tous les tournois pour initialiser les poules
		initPoules(daoPoule, daoAppartenance, tournois, equipes);
	}

	private static void initNiveaux(DaoNiveau daoNiveau) throws SQLException {
		for (Niveau niveau : Niveau.values()) {
			daoNiveau.add(niveau);
		}
	}

	private static void initSaisons(DaoSaison daoSaison) throws SQLException {
		Saison saison = new Saison(2024);
		Saison saison2 = new Saison(2023);
		daoSaison.add(saison);
		daoSaison.add(saison2);
	}

	private static void initTournois(DaoTournoi daoTournoi, DaoArbitre daoArbitre, DaoSaison daoSaison) throws SQLException {
		// Exemple d'ajout de tournois
		Tournoi tournoi = createTournoi("RLCS", 2024, 12, 1, 2024, 12, 30, Niveau.LOCAL, daoArbitre, daoSaison);
		Tournoi tournoi2 = createTournoi("RLRS", 2024, 1, 9, 2024, 1, 30, Niveau.LOCAL, daoArbitre, daoSaison);
		// Ajouter d'autres tournois si nécessaire

		daoTournoi.add(tournoi);
		daoTournoi.add(tournoi2);
		// Ajouter d'autres tournois si nécessaire
	}

	private static Tournoi createTournoi(String nom, int debutAnnee, int debutMois, int debutJour, int finAnnee, int finMois, int finJour, Niveau niveau, DaoArbitre daoArbitre, DaoSaison daoSaison) {
		try{
			Optional<Saison> OptionalSaison = daoSaison.getById(debutAnnee);
			if(OptionalSaison.isPresent()){
				Saison saison = OptionalSaison.get();
				CustomDate debutTournoi = new CustomDate(debutAnnee, debutMois, debutJour);
				CustomDate fin = new CustomDate(finAnnee, finMois, finJour);
				CompteArbitre compteArbitre = new CompteArbitre("arbitre", "rlcs");
				return new Tournoi(saison, nom, debutTournoi, fin, niveau, compteArbitre);
			}
		}catch(SQLException|FausseDateException e){
			LOGGER.log(Level.SEVERE, e.getMessage(), e);

		}
		return null;
	}

	private static void initEquipes(DaoEquipe daoEquipe, int nombreEquipes) throws SQLException {
		Equipe equipe = new Equipe("terros", Pays.FRANCE);
		Equipe equipe1 = new Equipe("lion-rouge", Pays.FRANCE);
		Equipe equipe2 = new Equipe("shark", Pays.FRANCE);
		Equipe equipe3 = new Equipe("goule", Pays.FRANCE);
		Equipe equipe4 = new Equipe("dragon", Pays.FRANCE);
		Equipe equipe5 = new Equipe("aigle", Pays.FRANCE);
		Equipe equipe6 = new Equipe("bear", Pays.FRANCE);
		Equipe equipe7 = new Equipe("chevarcher", Pays.FRANCE);
		Equipe equipe8 = new Equipe("loup", Pays.FRANCE);
		Equipe equipe9 = new Equipe("mort", Pays.FRANCE);
		daoEquipe.add(equipe);
		daoEquipe.add(equipe1);
		daoEquipe.add(equipe2);
		daoEquipe.add(equipe3);
		daoEquipe.add(equipe4);
		daoEquipe.add(equipe5);
		daoEquipe.add(equipe6);
		daoEquipe.add(equipe7);
		daoEquipe.add(equipe8);
		daoEquipe.add(equipe9);
	}

	private static void initInscriptions(DaoInscription daoInscription, List<Saison> saisons, List<Equipe> equipes) throws SQLException {
		for (Saison saison : saisons) {
			for (Equipe equipe : equipes) {
				Inscription inscription = new Inscription(saison, equipe);
				daoInscription.add(inscription);
			}
		}
	}
	private static void initPoules(DaoPoule daoPoule, DaoAppartenance daoAppartenance, List<Tournoi> tournois, List<Equipe> equipes) throws SQLException {
		for (Tournoi tournoi : tournois) {
			char lettre = 'A';
			int equipesParPoule = equipes.size() / 4; // Nombre d'équipes à ajouter à chaque poule
			int indiceDebut = 0; // Indice de début pour la sélection des équipes

			for (int i = 0; i < 4; i++) { // Vous pouvez ajuster le nombre de poules ici
				Poule poule = new Poule(tournoi, lettre);
				daoPoule.add(poule);

				// Sélectionnez les équipes pour cette poule
				int indiceFin = Math.min(indiceDebut + equipesParPoule, equipes.size()); // Évitez l'IndexOutOfBoundsException
				List<Equipe> equipesPoule = equipes.subList(indiceDebut, indiceFin);

				// Ajoutez chaque équipe à la poule
				for (Equipe equipe : equipesPoule) {
					Appartenance appartenance = new Appartenance(equipe, poule);
					daoAppartenance.add(appartenance);
				}

				lettre++;
				indiceDebut = indiceFin; // Mettez à jour l'indice de début pour la prochaine poule
			}
		}
	}


}
