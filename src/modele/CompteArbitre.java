package modele;

public class CompteArbitre extends CompteUtilisateur{

	public CompteArbitre(String username, String mdp) {
		super(username, mdp);
	}

	@Override
	public String toString() {
		return "CompteArbitre [getUsername()=" + getUsername() + ", getHashMdp()=" + getHashMdp() +"]";
	}
}
