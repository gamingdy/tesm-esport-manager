package vue;

public enum Page {
	LOGIN("Login"),
	ARBITRES("Arbitres"),
	TOURNOIS("Tournois"),
	EQUIPES("Equipes"),
	SAISON_PRECEDENTES("Saisons precedentes"),
	ACCUEIL("Accueil"),
	ACCUEILL_ADMIN("Accueil admin");

	private String nom;

	Page(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
}
