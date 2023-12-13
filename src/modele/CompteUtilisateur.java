package modele;

import java.util.Objects;

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

	public String getMdp() {
		return mdp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mdp, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompteUtilisateur other = (CompteUtilisateur) obj;
		return Objects.equals(mdp, other.mdp) && Objects.equals(username, other.username);
	}
}
