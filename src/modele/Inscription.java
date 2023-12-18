package modele;

public class Inscription {
	
	private String nom;
	private Integer annee;
	
	public Inscription(int i, String nom) {
		this.nom = nom;
		this.annee = i;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	@Override
	public String toString() {
		return "Inscription [nom=" + nom + ", annee=" + annee + "]";
	}

}
