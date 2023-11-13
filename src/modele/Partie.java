package modele;

public class Partie {

	private int id;
	private byte numeroPartie;
	private String nom;
	private Matche matche;
	
	public Partie(int id, byte numeroPartie, String nom, Matche matche) {
		this.id = id;
		this.numeroPartie = numeroPartie;
		this.nom = nom;
		this.matche = matche;
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

	public Matche getmatche() {
		return matche;
	}

	public void setmatche(Matche matche) {
		this.matche = matche;
	}
	
	
	
}
