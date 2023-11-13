package modele;

public class Selection {
	
	private int idArbitre;
	private short annee;
	
	public Selection(int idArbitre, short annee) {
		this.idArbitre = idArbitre;
		this.annee = annee;
	}

	public int getIdArbitre() {
		return idArbitre;
	}

	public void setIdArbitre(int idArbitre) {
		this.idArbitre = idArbitre;
	}

	public short getAnnee() {
		return annee;
	}

	public void setAnnee(short annee) {
		this.annee = annee;
	}

}
