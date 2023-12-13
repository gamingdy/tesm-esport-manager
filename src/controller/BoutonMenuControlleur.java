package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vue.Page;
import vue.common.JFramePopup;
import vue.admin.VueAdmin;
import vue.admin.main.BoutonMenu;
import vue.admin.main.MenuNavBar;

enum ETAT {
	ACCUEIL, ARBITRES
}

public class BoutonMenuControlleur implements ActionListener, MouseListener {
	private VueAdmin vue;
	private ETAT etat;
	MenuNavBar navbar;
	private VueObserver obs;

	private AccueilControlleur accueil;

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
			BoutonMenu boutonSelectionné = (BoutonMenu) e.getSource();
			if (boutonSelectionné.getText() == "Arbitres") {
				etat = ETAT.ARBITRES;
				this.vue.setPage(Page.ARBITRES.getNom());
			} else if (boutonSelectionné.getText() == "Déconnexion") {
				new JFramePopup("Deconnexion", "Etes vous sur de vous deconnecter ?");

			} else if ((boutonSelectionné.getText() == "Accueil") && etat != ETAT.ACCUEIL) {
				etat = ETAT.ACCUEIL;
				this.vue.setPage(Page.ACCUEIL_ADMIN.getNom());
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

	ETAT getEtat() {
		return this.etat;
	}

	public void attach(VueObserver obs) {
		this.obs = obs;
	}
}
