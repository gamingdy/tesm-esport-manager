package modele;

public class Joueur {

	private int id;
	private String pseudo;


	public Joueur(int id, String pseudo) {
		this.id = id;
		this.pseudo = pseudo;
	}

	public int getId() {
		return id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


}
