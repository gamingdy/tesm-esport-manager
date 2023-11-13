package modele;

public class Partie {

	private int id;
	private byte numeroPartie;
	private String equipeGagnante;
	private Matche matche;
	
	public Partie(int id, byte numeroPartie, String equipeGagnante, Matche matche) {
		this.id = id;
		this.numeroPartie = numeroPartie;
		this.equipeGagnante = equipeGagnante;
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
