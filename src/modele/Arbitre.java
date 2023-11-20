package modele;

public class Arbitre {

	private int id;
	private String nom;
	private String prenom;

	public Arbitre(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	public void setId(int newid) {
		this.id = newid;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public String toString() {
		return "(" + this.getId() + ")" + this.getNom() + " " + this.getPrenom();
	}


}
