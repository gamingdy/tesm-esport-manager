package vue.admin.accueil;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import vue.admin.VueAdmin;
import vue.common.CustomColor;
import vue.common.MaFont;

@SuppressWarnings("serial")
public class PanelEquipeClassement extends JPanel {
	public PanelEquipeClassement(int place, ImageIcon imageIcon, String nom, int points) {

		setLayout(new GridLayout(1, 0, 0, 0));
		setBackground(CustomColor.BACKGROUND_MAIN);
		setOpaque(true);
		JLabel labelPlace = new JLabel("" + place);
		labelPlace.setForeground(CustomColor.BLANC);
		labelPlace.setFont(MaFont.getFontTitre3());
		add(labelPlace);

		JLabel labelImage = new JLabel();
		labelImage.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(50, 50, 0)));

		add(labelImage);

		JLabel labelNom = new JLabel(nom);
		labelNom.setForeground(CustomColor.BLANC);
		labelNom.setFont(MaFont.getFontTitre3());
		add(labelNom);

		JLabel labelPoints = new JLabel("" + points);
		labelPoints.setForeground(CustomColor.BLANC);
		labelPoints.setFont(MaFont.getFontTitre3());
		add(labelPoints);


	}

}
