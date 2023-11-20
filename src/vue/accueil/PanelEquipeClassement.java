package vue.accueil;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import vue.Vue;
import vue.common.MaFont;

@SuppressWarnings("serial")
public class PanelEquipeClassement extends JPanel {
	public PanelEquipeClassement(int place, ImageIcon imageIcon, String nom, int points) {
		setBorder(new EmptyBorder(0, 20, 0, 0));
		setLayout(new GridLayout(1, 0, 0, 0));
		setOpaque(false);
		JLabel labelPlace = new JLabel(""+place);
		labelPlace.setForeground(Vue.BLANC);
		labelPlace.setOpaque(false);
		labelPlace.setFont(MaFont.getFontTitre3());
		add(labelPlace);
		
		JLabel labelImage = new JLabel();
		labelImage.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(50, 50, 0)));
		labelImage.setOpaque(false);
		add(labelImage);
		
		JLabel labelNom = new JLabel(nom);
		labelNom.setForeground(Vue.BLANC);
		labelNom.setOpaque(false);
		labelNom.setFont(MaFont.getFontTitre3());
		add(labelNom);
		
		JLabel labelPoints = new JLabel(""+points);
		labelPoints.setForeground(Vue.BLANC);
		labelPoints.setOpaque(false);
		labelPoints.setFont(MaFont.getFontTitre3());
		add(labelPoints);
		
		setOpaque(false);
		
	}

}
