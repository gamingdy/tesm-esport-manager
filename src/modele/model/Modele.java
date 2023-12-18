package modele;

import java.sql.Date;
import java.sql.SQLException;

import dao.Connexion;
import dao.DaoAppartenance;
import dao.DaoEquipe;
import dao.DaoInscription;
import dao.DaoMatche;
import dao.DaoNiveau;
import dao.DaoPartie;
import dao.DaoPoule;
import dao.DaoSaison;
import dao.DaoTournoi;
import exceptions.ExceptionPointsNegatifs;
import exceptions.FausseDateException;
import java.time.LocalDate;

public class Modele {
    private Connexion c;
    private DaoTournoi daoTournoi;
    private DaoNiveau daoNiveau;
    private DaoPoule daoPoule;
    private DaoEquipe daoEquipe;
    private DaoAppartenance daoAppartenance;
    private DaoMatche daoMatche;
    private DaoPartie daoPartie;
    private DaoSaison daoSaison;
    private DaoInscription daoInscription;

    public Modele() {
        c = Connexion.getConnexion();
        daoTournoi = new DaoTournoi(c);
        daoNiveau = new DaoNiveau(c);
        daoPoule = new DaoPoule(c);
        daoEquipe = new DaoEquipe(c);
        daoAppartenance = new DaoAppartenance(c);
        daoMatche = new DaoMatche(c);
        daoPartie = new DaoPartie(c);
        daoSaison = new DaoSaison(c);
        daoInscription = new DaoInscription(c);
    }

	public Integer getWorldRank(Equipe equipe) throws SQLException {
		Saison s = daoSaison.getLastSaison();
		
	}
    
}
