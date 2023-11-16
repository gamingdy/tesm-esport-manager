package vue.accueil;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class PanelEquipeClassement extends JPanel {
	public PanelEquipeClassement(String place, String cheminImage, String nom, String points) {
		setBorder(new EmptyBorder(0, 20, 0, 0));
		setLayout(new GridLayout(1, 0, 0, 0));
		setOpaque(false);
		JLabel labelPlace = new JLabel(place);
		labelPlace.setForeground(Color.white);
		labelPlace.setOpaque(false);
		add(labelPlace);
		
		JLabel labelImage = new JLabel();
		labelImage.setIcon(new ImageIcon(new ImageIcon(cheminImage).getImage().getScaledInstance(50, 50, 0)));
		labelImage.setOpaque(false);
		add(labelImage);
		
		JLabel labelNom = new JLabel(nom);
		labelNom.setForeground(Color.white);
		labelNom.setOpaque(false);
		add(labelNom);
		
		JLabel labelPoints = new JLabel(points);
		labelPoints.setForeground(Color.white);
		labelPoints.setOpaque(false);
		add(labelPoints);
		
		setOpaque(false);
		
	}

}
