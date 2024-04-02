package InitBd;

import dao.Connexion;
import dao.DaoAppartenance;
import dao.DaoEquipe;
import modele.Equipe;
import modele.Tournoi;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class InitAppartenance {
	private static Logger LOGGER = Logger.getLogger("Initialisation des appartenance");
	private static final DaoAppartenance daoAppartenance = new DaoAppartenance(Connexion.getConnexion());
	private static final DaoEquipe daoEquipes = new DaoEquipe(Connexion.getConnexion());
	public static void initAppartenance() {
		// Initialisation des tournois...
		Tournoi tournoi1=InitTournois.getTournoi1();
		Tournoi tournoi2=InitTournois.getTournoi2();
		Tournoi tournoi2023=InitTournois.getTournoi2023();
		// Initialisation des poules pour chaque tournoi
		initPoulesPourTournoi(tournoi1);
		initPoulesPourTournoi(tournoi2);
		initPoulesPourTournoi(tournoi2023);
	}

	private static void initPoulesPourTournoi(Tournoi tournoi) {
		try{
		// Récupérer les équipes participant au tournoi
		List<Equipe> equipes = daoEquipes.getAll();

		// Initialiser les poules pour le tournoi avec les équipes récupérées
		InitPoules.initPoules(tournoi, equipes);
		}catch (SQLException e){
			LOGGER.severe("Erreur lors de l'initialisation des poules pour le tournoi "+tournoi.getNom()+" : "+e.getMessage());
		}
	}
}
