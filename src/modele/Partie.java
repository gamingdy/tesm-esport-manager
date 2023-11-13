package modele;

public class Partie {

	private int id;
	private int numeroPartie;
	private String equipeGagnante;
	private Matche matche;


	public Partie(int id, byte numeroPartie, String nom, Matche matche) {
		this.id = id;
		this.numeroPartie = numeroPartie;
		this.equipeGagnante = null;
		this.matche = matche;
	}

	public int getId() {
		return id;
	}


	public int getNumeroPartie() {
		return numeroPartie;
	}

	public void setNumeroPartie(int numeroPartie) {
		this.numeroPartie = numeroPartie;
	}

	public String getEquipeGagnante() {
		return equipeGagnante;
	}

	public void setEquipeGagnante(String equipeGagnante) {
		this.equipeGagnante = equipeGagnante;
	}

	public Matche getmatche() {
		return matche;
	}

	public void setmatche(Matche matche) {
		this.matche = matche;
	}


}
