package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vue.common.JFramePopup;
import vue.Vue;
import vue.admin.VueAdmin;
import vue.admin.main.BoutonMenu;
import vue.admin.main.MenuNavBar;

import javax.swing.*;

import static javax.swing.JOptionPane.YES_NO_OPTION;

enum ETAT {
	ACCUEIL, ARBITRES
}

public class BoutonMenuControlleur implements ActionListener, MouseListener {
	private VueAdmin vue;
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
			BoutonMenu boutonSelectionné = (BoutonMenu) e.getSource();
			if (boutonSelectionné.getText() == "Arbitres") {
				etat = ETAT.ARBITRES;
				this.vue.setPage("Arbitres");
			}
			if (boutonSelectionné.getText() == "Déconnexion") {
				System.out.println("Deconnexion");
				JFramePopup popup = new JFramePopup("Deconnexion", "Etes vous sur de vous deconnecter ?");
				popup.setVisible(true);
				/*if (a == 0) {

				}*/
			}
			if ((boutonSelectionné.getText() == "Accueil") && etat != ETAT.ACCUEIL) {
				System.out.println("Accueil");
				etat = ETAT.ACCUEIL;
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
