package vue.accueil;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import vue.Vue;
import vue.common.CustomColor;
import vue.common.MaFont;

import java.awt.GridBagLayout;
import java.awt.BorderLayout;

public class PanelNomTournoi extends JPanel {
	public PanelNomTournoi(String nomDuTournoi) {
		setBorder(null);
		setLayout(new BorderLayout(0, 0));

		JLabel labelNewLabel = new JLabel(nomDuTournoi);

		add(labelNewLabel);
		labelNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		labelNewLabel.setForeground(CustomColor.BLANC);
		labelNewLabel.setFont(MaFont.getFontTitre3());
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0), BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 1))
				, BorderFactory.createEmptyBorder(3, 5, 3, 5)));
	}

}
