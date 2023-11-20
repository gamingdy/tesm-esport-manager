package modele;


public class Joueur implements Comparable<Joueur> {
	private String pseudo;


	public Joueur(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int compareTo(Joueur j) {
		return j.getPseudo().compareTo(this.getPseudo());
	}

	@Override
	public int hashCode() {
		return this.pseudo.hashCode();
	}


}
