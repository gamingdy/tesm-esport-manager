package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

import vue.BoutonNavBar;
import vue.Page;
import vue.common.JFramePopup;
import vue.admin.VueAdmin;
import vue.admin.main.BoutonMenu;
import vue.admin.main.MenuNavBar;

enum ETAT {
	ACCUEIL, ARBITRES, EQUIPES
}

public class BoutonMenuControlleur implements ActionListener, MouseListener {
	private final VueAdmin vue;
	private ETAT etat;
	MenuNavBar navbar;
	private VueObserver obs;

	public BoutonMenuControlleur(MenuNavBar navbar, VueAdmin vue) {
		this.navbar = navbar;
		this.etat = ETAT.ACCUEIL;
		this.vue = vue;
		this.obs = VueObserver.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof BoutonMenu) {
			navbar.selectionner((BoutonMenu) e.getSource());
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof BoutonMenu) {
			navbar.selectionner((BoutonMenu) e.getSource());
			BoutonMenu boutonSelection = (BoutonMenu) e.getSource();
			if (Objects.equals(boutonSelection.getText(), BoutonNavBar.ARBITRES.getNom()) && etat != ETAT.ARBITRES) {
				etat = ETAT.ARBITRES;
				this.vue.setPage(Page.ARBITRES.getNom());
			} else if (Objects.equals(boutonSelection.getText(), BoutonNavBar.DECONNEXION.getNom())) {
				new JFramePopup("Déconnexion", "Etes vous sur de vous déconnecter ?", () -> VueObserver.getInstance().notifyVue("Login"));

			} else if ((Objects.equals(boutonSelection.getText(), BoutonNavBar.ACCUEIL.getNom())) && etat != ETAT.ACCUEIL) {
				etat = ETAT.ACCUEIL;
				this.vue.setPage(Page.ACCUEIL_ADMIN.getNom());
			} else if (Objects.equals(boutonSelection.getText(), BoutonNavBar.EQUIPES.getNom())) {
				this.vue.setPage(Page.EQUIPES.getNom());
				this.etat = ETAT.EQUIPES;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() instanceof BoutonMenu) {
			((BoutonMenu) e.getSource()).survoller();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() instanceof BoutonMenu) {
			((BoutonMenu) e.getSource()).finSurvoller();
		}
	}


	public void attach(VueObserver obs) {
		this.obs = obs;
	}
}
