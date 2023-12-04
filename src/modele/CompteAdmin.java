package modele;

public class CompteAdmin extends CompteUtilisateur {
	public static final CompteAdmin compteAdmin=new CompteAdmin("admin","root");
	public CompteAdmin(String username, String mdp) {
		super(username, mdp);
	}
}
