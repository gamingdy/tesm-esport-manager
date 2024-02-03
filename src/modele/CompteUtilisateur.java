package modele;

import java.util.Objects;

public class CompteUtilisateur {

	private String username;
	private String hashMdp;

	public CompteUtilisateur(String username, String hashMdp) {
		this.username = username;
		this.hashMdp = hashMdp;
	}

	public String getUsername() {
		return username;
	}

	public String getHashMdp() {
		return hashMdp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hashMdp, username);
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
		return Objects.equals(hashMdp, other.hashMdp) && Objects.equals(username, other.username);
	}
}
