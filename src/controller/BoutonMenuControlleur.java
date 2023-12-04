package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vue.Vue;
import vue.admin.main.BoutonMenu;
import vue.admin.main.MenuNavBar;

enum ETAT{
	ACCUEIL,ARBITRES
}
public class BoutonMenuControlleur implements ActionListener, MouseListener {
	private Vue vue;
	private ETAT etat;
	MenuNavBar navbar;

	public BoutonMenuControlleur(MenuNavBar navbar) {
		this.navbar = navbar;
		this.etat=ETAT.ACCUEIL;
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
			if(boutonSelectionné.getText()=="Arbitres"){
				etat=ETAT.ARBITRES;
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

}
