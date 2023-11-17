package vue.accueil;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import vue.Vue;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;

public class PanelNomTournoi extends JPanel {
	public PanelNomTournoi(String nomDuTournoi) {
		setBorder(null);
				setLayout(new BorderLayout(0, 0));
				
				JLabel labelNewLabel = new JLabel(nomDuTournoi);
				
				add(labelNewLabel);
				labelNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				labelNewLabel.setForeground(Vue.BLANC);
				setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red,1),BorderFactory.createEmptyBorder(3,0,3,0)));
	}

}
