package vue.admin.arbitres;

import controller.BoutonMenuControlleur;
import controller.VueObserver;
import vue.common.CustomColor;
import vue.common.MaFont;

import javax.swing.*;

public class VueArbitres extends JPanel {
	public VueArbitres() {
		setOpaque(false);
		JLabel labelTitreClassement = new JLabel("Classement des équipes année précédente");
		labelTitreClassement.setFont(MaFont.getFontTitre1());
		labelTitreClassement.setForeground(CustomColor.BLANC);
		add(labelTitreClassement);
	}

	public void attachObserver(VueObserver observer) {
	}
}
