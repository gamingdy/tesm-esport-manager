package modele;

public class CompteUtilisateur {

	private String username;
	private String mdp;
	
	public CompteUtilisateur(String username, String mdp) {
		this.username = username;
		this.mdp = mdp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
	
	
}
