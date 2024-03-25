package controlleur.arbitre;

import dao.Connexion;
import dao.DaoMatche;
import dao.DaoPartie;
import exceptions.IdNotSetException;
import modele.Matche;
import modele.Partie;
import vue.arbitre.CaseMatch;
import vue.common.JFramePopup;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

public class CaseMatcheControlleur extends MouseAdapter {

	private CaseMatch caseMatch;
	private boolean isLeft;
	private DaoPartie daoPartie;
	private DaoMatche daoMatche;

	public CaseMatcheControlleur(CaseMatch caseMatch, boolean isLeft) {
		this.caseMatch = caseMatch;
		this.isLeft = isLeft;
		Connexion c = Connexion.getConnexion();
		this.daoPartie = new DaoPartie(c);
		this.daoMatche = new DaoMatche(c);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Matche matche = this.getMatche();
		Partie partie = null;
		if (matche == null) {
			return;
		}
		try {
			partie = this.getPartie(matche.getId());
		} catch (IdNotSetException ex) {
			afficherErreur("Erreur de récupération de partie");
		}
		if (partie == null) {
			return;
		}

		if (isLeft) {
			partie.setVainqueur(matche.getEquipe1());
			this.setVainqueurEquipe1Affichage();
		} else {
			partie.setVainqueur(matche.getEquipe2());
			this.setVainqueurEquipe2Affichage();
		}

		try {
			this.daoPartie.update(partie);
		} catch (Exception ex) {
			afficherErreur("Erreur SQL de mise à jour de partie");
		}
	}

	public void setVainqueurEquipe1Affichage() {
		this.caseMatch.setGagnant(1);
	}

	public void setVainqueurEquipe2Affichage() {
		this.caseMatch.setGagnant(2);
	}

	private Matche getMatche() {
		try {
			Optional<Matche> matche1 = daoMatche.getById(this.caseMatch.getIdMatche());
			if (matche1.isPresent()) {
				return matche1.get();
			}
		} catch (Exception e) {
			afficherErreur("Erreur SQL de recuperation de matche");
		}
		return null;
	}

	private Partie getPartie(int idMatche) {
		try {
			Optional<Partie> partie = daoPartie.getById(idMatche, 1);
			if (partie.isPresent()) {
				return partie.get();
			}
		} catch (Exception e) {
			afficherErreur("Erreur SQL");
		}
		return null;
	}
	private void afficherErreur(String message) {
		new JFramePopup("Erreur de case match", message, () -> {});
	}
}
