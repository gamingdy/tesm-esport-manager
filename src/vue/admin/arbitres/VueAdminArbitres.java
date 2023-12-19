package vue.admin.arbitres;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controlleur.VueObserver;
import vue.common.CustomColor;
import vue.common.MaFont;

public class VueAdminArbitres extends JPanel {
	public VueAdminArbitres() {
		setOpaque(false);
		JLabel labelTitreClassement = new JLabel("Classement des équipes année précédente");
		labelTitreClassement.setFont(MaFont.getFontTitre1());
		labelTitreClassement.setForeground(CustomColor.BLANC);
		add(labelTitreClassement);
	}

	public void attachObserver(VueObserver observer) {
	}
}
