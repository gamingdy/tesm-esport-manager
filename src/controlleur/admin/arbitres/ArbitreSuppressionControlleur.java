package controlleur.admin.arbitres;

import controlleur.admin.tournois.TournoisObserver;
import dao.Connexion;
import dao.DaoArbitre;
import dao.DaoTournoi;
import modele.Arbitre;
import modele.CustomDate;
import modele.Tournoi;
import vue.Page;
import vue.admin.arbitres.liste.CaseArbitre;
import vue.admin.tournois.liste.CaseTournoi;
import vue.common.JFramePopup;

import java.awt.event.MouseAdapter;
import java.util.Optional;

public class ArbitreSuppressionControlleur extends MouseAdapter {
	private CaseArbitre caseArbitre;
	private DaoArbitre daoArbitre;

	public ArbitreSuppressionControlleur(CaseArbitre newCase) {
		this.caseArbitre = newCase;
		Connexion c = Connexion.getConnexion();
		this.daoArbitre=new DaoArbitre(c);
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		new JFramePopup("Suppression de Tournois","Etes vous sûr de supprimer ce tournoi ?",()->
				supprimerArbitre()
		);
	}
	public void supprimerArbitre() {
		try {
			Optional<Arbitre> arbitre = daoArbitre.getById( caseArbitre.getNom(),caseArbitre.getPrenom());
			if(arbitre.isPresent()){

				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
