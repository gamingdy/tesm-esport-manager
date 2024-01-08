package modele.model;

import java.util.LinkedList;
import java.util.List;

import dao.Connexion;
import dao.DaoAppartenance;
import dao.DaoEquipe;
import dao.DaoInscription;
import dao.DaoTournoi;
import dao.FactoryDAO;
import modele.Inscription;

public class ModeleEquipe {

	private final Connexion c;
	
	public ModeleEquipe() {
		c = Connexion .getConnexion();
	}
	
}
