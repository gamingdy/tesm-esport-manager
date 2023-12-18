package dao;

public class FactoryDAO {

	public static DaoAppartenance getDaoAppartenance(Connexion c) {
		return new DaoAppartenance(c);
	}
	
	public static DaoArbitrage getDaoArbitrage(Connexion c) {
		return new DaoArbitrage(c);
	}
	
	public static DaoArbitre getDaoAbritre(Connexion c) {
		return new DaoArbitre(c);
	}
	
	public static DaoEquipe getDaoEquipe(Connexion c) {
		return new DaoEquipe(c);
	}
	
	public static DaoInscription getDaoInscription(Connexion c) {
		return new DaoInscription(c);
	}
	
	public static DaoJoueur getDaoJoueur(Connexion c) {
		return new DaoJoueur(c);
	}
	
	public static DaoMatche getDaoMatche(Connexion c) {
		return new DaoMatche(c);
	}
	
	public static DaoNiveau getDaoNiveau(Connexion c) {
		return new DaoNiveau(c);
	}
	
	public static DaoPartie getDaoPartie(Connexion c) {
		return new DaoPartie(c);
	}
	
	public static DaoPoule getDaoPoule(Connexion c) {
		return new DaoPoule(c);
	}
	
	public static DaoSaison getDaoSaison(Connexion c) {
		return new DaoSaison(c);
	}
	
	public static DaoSelection getDaoSelection(Connexion c) {
		return new DaoSelection(c);
	}
	
	public static DaoTournoi getDaoTournoi(Connexion c) {
		return new DaoTournoi(c);
	}
}
