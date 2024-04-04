package InitBd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.Connexion;
import dao.DaoInscription;
import dao.DaoSaison;
import exceptions.ExceptionPointsNegatifs;
import exceptions.FausseDateException;
import exceptions.MemeEquipeException;
import dao.DaoEquipe;
import modele.Equipe;
import modele.Inscription;
import modele.ModeleSaison;
import modele.Saison;
import modele.Tournoi;

public class InitInscriptions {
	private static Logger LOGGER = Logger.getLogger("Initialisation des appartenance");
	private static final DaoInscription daoInscription = new DaoInscription(Connexion.getConnexion());
	private static final DaoEquipe daoEquipes = new DaoEquipe(Connexion.getConnexion());
	private static final DaoSaison daoSaison = new DaoSaison(Connexion.getConnexion());
	public static void initInscriptions() {
		try {
			Iterable<Saison> saisons = daoSaison.getAll();
			Iterable<Equipe> equipes = daoEquipes.getAll();
			for (Saison s: saisons) {
				try {
					Set<Equipe> classement = ModeleSaison.getClassement(s);
					for (Equipe e : equipes) {
						Inscription i = new Inscription(s,e);
						i.setWorldRank((new ArrayList<>(classement)).indexOf(e)+1);
						daoInscription.add(i);
					}
				} catch (MemeEquipeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FausseDateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ExceptionPointsNegatifs e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			System.out.println(daoInscription.getAll());

		}

		catch (SQLException e1) {
			LOGGER.log(Level.SEVERE, "Erreur lors de l'initialisation de l'inscription : " + e1.getMessage(), e1);
		}
	}
}
