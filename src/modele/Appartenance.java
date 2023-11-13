package modele;

public class Appartenance {
	
	private String nomEquipe;
	private String libellePoule;
	
	public Appartenance(String nomEquipe, String libellePoule) {
		this.nomEquipe = nomEquipe;
		this.libellePoule = libellePoule;
	}
	public String getNomEquipe() {
		return nomEquipe;
	}
	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}
	public String getLibellePoule() {
		return libellePoule;
	}
	public void setLibellePoule(String libellePoule) {
		this.libellePoule = libellePoule;
	}
	
	

}
