package modele;

public class Partie {

	private int id;
	private byte numeroPartie;
	private String nom;
	private Tournoi tournoi;
	
	public Partie(int id, byte numeroPartie, String nom, Tournoi tournoi) {
		this.id = id;
		this.numeroPartie = numeroPartie;
		this.nom = nom;
		this.tournoi = tournoi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getNumeroPartie() {
		return numeroPartie;
	}

	public void setNumeroPartie(byte numeroPartie) {
		this.numeroPartie = numeroPartie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Tournoi getTournoi() {
		return tournoi;
	}

	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
	}
	
	
	
}
