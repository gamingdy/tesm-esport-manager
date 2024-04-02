package initBd;

import dao.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.*;
import modele.*;

public class ESporterManagerInitBDD {
	private static final Logger LOGGER = Logger.getLogger("Initialisation bd");
	private static Tournoi tournoi2023;
	private static Saison saison2023;
	private static Poule poule2023;

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
		DaoMatche daoMatche = new DaoMatche(c);
		DaoNiveau daoNiveau = new DaoNiveau(c);
		DaoSaison daoSaison = new DaoSaison(c);
		DaoTournoi daoTournoi = new DaoTournoi(c);
		DaoEquipe daoEquipe = new DaoEquipe(c);
		DaoInscription daoInscription = new DaoInscription(c);
		DaoArbitre daoArbitre = new DaoArbitre(c);
		DaoPoule daoPoule= new DaoPoule(c);
		DaoPartie daoPartie=new DaoPartie(c);
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
		try {
			initPoints2023(daoTournoi, daoAppartenance,daoMatche,daoPartie, equipes);
		}catch (SQLException | MemeEquipeException | ExceptionPointsNegatifs e){
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private static void initNiveaux(DaoNiveau daoNiveau) throws SQLException {
		for (Niveau niveau : Niveau.values()) {
			daoNiveau.add(niveau);
		}
	}

	private static void initSaisons(DaoSaison daoSaison) throws SQLException {
		Saison saison = new Saison(2024);
		saison2023 = new Saison(2023);
		daoSaison.add(saison);
		daoSaison.add(saison2023);
	}

	private static void initTournois(DaoTournoi daoTournoi, DaoArbitre daoArbitre, DaoSaison daoSaison) throws SQLException {
		// Exemple d'ajout de tournois
		Tournoi tournoi = createTournoi("RLCS", 2024, 12, 1, 2024, 12, 30, Niveau.LOCAL, daoArbitre, daoSaison);
		Tournoi tournoi2 = createTournoi("RLRS", 2024, 1, 9, 2024, 1, 30, Niveau.LOCAL, daoArbitre, daoSaison);
		tournoi2023= createTournoi("RLRS", 2023, 1, 1, 2023, 1, 30, Niveau.LOCAL, daoArbitre, daoSaison);
		// Ajouter d'autres tournois si nécessaire

		daoTournoi.add(tournoi);
		daoTournoi.add(tournoi2);
		daoTournoi.add(tournoi2023);
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
	private static void initPoints2023(DaoTournoi daoTournoi,DaoAppartenance daoAppartenance, DaoMatche daoMatch,DaoPartie daoPartie, List<Equipe> equipes) throws SQLException, FausseDateException, MemeEquipeException, ExceptionPointsNegatifs {
		jouerMatchsDansPoule(daoMatch, equipes,daoPartie);
		afficherResultats(daoMatch, daoAppartenance);
	}

	private static void jouerMatchsDansPoule(DaoMatche daoMatch, List<Equipe> equipesPoule,DaoPartie daoPartie) throws SQLException, FausseDateException {
		for (int i = 0; i < equipesPoule.size(); i++) {
			for (int j = i + 1; j < equipesPoule.size(); j++) {
				Matche match = new Matche(1, new CustomDate(2023, 1, 1), Categorie.POULE, equipesPoule.get(i), equipesPoule.get(j),tournoi2023);
				daoMatch.add(match);
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
	}

	private static void afficherResultats(DaoMatche daoMatch,DaoAppartenance daoAppartenance) throws SQLException, MemeEquipeException, FausseDateException, ExceptionPointsNegatifs {
			List<Matche> matchsPoule = daoMatch.getMatchByTournoi(saison2023.getAnnee(), tournoi2023.getNom());
			Set<Equipe> set=ModeleSaison.getClassement(saison2023);
			for (Matche match : matchsPoule) {
				LOGGER.info(match.getEquipe1().getNom()+" - "+ match.getEquipe2().getNom() + " ,VAINQUEUR : " + match.getVainqueur());
			}
			for(Equipe equipe : set){
				LOGGER.info(equipe.getNom() + " ,POINTS : " + equipe.getPoint());
			}
	}



}
