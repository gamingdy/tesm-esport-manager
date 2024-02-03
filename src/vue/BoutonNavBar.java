package vue;

public enum BoutonNavBar {
	ACCUEIL("Accueil"),
	ARBITRES("Arbitres"),
	TOURNOIS("Tournois"),
	SAISON_PRECEDENTES("Saisons précédentes"),
	DECONNEXION("Déconnexion"),
	EQUIPES("Equipes");

	private String nom;

	BoutonNavBar(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

}

