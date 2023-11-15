package vue.accueil;

import javax.swing.JPanel;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class PanelEquipeClassement extends JPanel {
	public PanelEquipeClassement(String place, String cheminImage, String nom, String points) {
		setBorder(new EmptyBorder(0, 20, 0, 0));
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel labelPlace = new JLabel(place);
		add(labelPlace);
		
		JLabel labelImage = new JLabel();
		labelImage.setIcon(new ImageIcon(cheminImage));
		add(labelImage);
		
		JLabel lblNewLabel_2 = new JLabel(nom);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(points);
		add(lblNewLabel_3);
	}

}
