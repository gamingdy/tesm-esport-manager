package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vue.Vue;
import vue.admin.VueAdmin;
import vue.admin.main.BoutonMenu;
import vue.admin.main.MenuNavBar;

import javax.swing.*;

import static javax.swing.JOptionPane.YES_NO_OPTION;

enum ETAT{
	ACCUEIL,ARBITRES
}
public class BoutonMenuControlleur implements ActionListener, MouseListener {
	private VueAdmin vue;
	private ETAT etat;
	MenuNavBar navbar;
	private VueObserver obs;

	public BoutonMenuControlleur(MenuNavBar navbar, VueAdmin vue) {
		this.navbar = navbar;
		this.etat=ETAT.ACCUEIL;
		this.vue=vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof BoutonMenu) {
			navbar.selectionner((BoutonMenu) e.getSource());}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof BoutonMenu) {
			navbar.selectionner((BoutonMenu) e.getSource());
			BoutonMenu boutonSelectionné=(BoutonMenu) e.getSource();
			System.out.println("patata");
			if(boutonSelectionné.getText()=="Arbitres"){
				System.out.println("arbitre");
				etat=ETAT.ARBITRES;
			}
			if (boutonSelectionné.getText() == "Déconnexion") {
				System.out.println("Deconnexion");
				int a = JOptionPane.showConfirmDialog(vue, "Etes vous sure de vous deconnecter?", "Deconnexion", YES_NO_OPTION);
				if (a == 0) {
					obs.notifyVue("Login");
				}
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
	ETAT getEtat(){
		return this.etat;
	}
}
