package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vue.common.BoutonMenu;
import vue.main.MenuNavBar;

public class BoutonMenuControlleur implements ActionListener, MouseListener {

	MenuNavBar navbar;

	public BoutonMenuControlleur(MenuNavBar navbar) {
		this.navbar = navbar;
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
