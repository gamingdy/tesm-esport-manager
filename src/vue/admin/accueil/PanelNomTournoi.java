package vue.admin.accueil;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vue.common.CustomColor;
import vue.common.MaFont;

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
