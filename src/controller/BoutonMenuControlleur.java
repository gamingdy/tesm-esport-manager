package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.BoutonMenu;
import vue.MenuNavBar;

public class BoutonMenuControlleur implements ActionListener {

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

}
