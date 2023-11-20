package modele;

public class Partie {

	
	private int numeroPartie;
	private Equipe equipeGagnante;
	private Integer idMatche;
	
	public Partie(Equipe equipeGagnante, Integer idMatche) {
		this.equipeGagnante = equipeGagnante;
		this.idMatche = idMatche;
	}

	public int getNumeroPartie() {
		return numeroPartie;
	}

	public void setNumeroPartie(int numeroPartie) {
		this.numeroPartie = numeroPartie;
	}

	public Equipe getEquipeGagnante() {
		return equipeGagnante;
	}

	public void setEquipeGagnante(Equipe equipeGagnante) {
		this.equipeGagnante = equipeGagnante;
	}

	public Integer getIdMatche() {
		return idMatche;
	}

	public void setIdMatche(Integer idMatche) {
		this.idMatche = idMatche;
	}
}