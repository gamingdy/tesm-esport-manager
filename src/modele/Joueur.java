package modele;


public class Joueur implements Comparable<Joueur>{
	

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

	public  int compareTo(Joueur j){
		if (j.getId()==this.getId()){
			return 0;
		}else{
			return j.getPseudo().compareTo(this.getPseudo());
		}
	}


}
