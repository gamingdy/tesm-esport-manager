package vue;

public enum Page {
	LOGIN("Login"),
	ARBITRES("Arbitre"),
	TOURNOIS("Tournois"),
	TOURNOIS_CREATION("Tournoi Creation"),
	TOURNOIS_LISTE("Tournois Liste"),
	EQUIPES("Equipes"),
	EQUIPES_CREATION("Equipes Creation"),
	EQUIPES_LISTE("Equipes Liste"),
	EQUIPES_DETAILS("Equipes Details"),


	SAISON_PRECEDENTES("Saisons precedentes"),
	ACCUEIL_ADMIN("Admin-Accueil"),
	ARBITRES_CREATION("Arbitres Creation"),
	ARBITRES_LISTE("Arbitres liste"),
	
	ARBITRE("Arbitre");

	
	private String nom;

	Page(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
}
