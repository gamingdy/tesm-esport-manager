package modele;

public class Arbitre {

	private final int id;
	private String nom;
	private String prenom;

	public Arbitre(int id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
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
	public int hashCode(){
		return this.toString().hashCode();
	}
	
	@Override
	public String toString(){
		return "("+this.getId()+")"+this.getNom()+" "+this.getPrenom();
	}


}
