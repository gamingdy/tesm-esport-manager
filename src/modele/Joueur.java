package modele;

public class Joueur {
	
	private int id;
	private String pseudo;
	private String nom;
	
	public Joueur(int id, String pseudo, String nom) {
		this.id = id;
		this.pseudo = pseudo;
		this.nom = nom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

}
