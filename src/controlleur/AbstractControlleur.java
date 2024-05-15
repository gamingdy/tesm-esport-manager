package controlleur;

public abstract class AbstractControlleur {
	
	protected String recupererCheminIconeEquipe(String nomEquipe) {
		return "assets/logo-equipes/" + nomEquipe + ".jpg";
	}
}
