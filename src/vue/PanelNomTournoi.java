package vue;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;

public class PanelNomTournoi extends JPanel {
	public PanelNomTournoi(String nomDuTournoi) {
		setBorder(null);
				setLayout(new BorderLayout(0, 0));
				
				JLabel lblNewLabel = new JLabel(nomDuTournoi);
				add(lblNewLabel);
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				
				setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red,1),BorderFactory.createEmptyBorder(3,0,3,0)));
	}

}
