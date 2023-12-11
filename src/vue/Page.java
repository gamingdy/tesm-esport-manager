package vue;

public enum Page {
	LOGIN("Login"),
	ARBITRES("Arbitres"),
	TOURNOIS("Tournois"),
	EQUIPES("Equipes"),
	SAISON_PRECEDENTES("Saisons precedentes"),
	ACCUEIL("Accueil"),
	ACCUEIL_ADMIN("Admin");

	private String nom;

	Page(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
}
