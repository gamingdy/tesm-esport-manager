package vue.common;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;

@SuppressWarnings("serial")
public class JPanelWithBackground extends JPanel {

	private transient Image backgroundImage;
	private transient Image scaled;

	// Some code to initialize the background image.
	// Here, we use the constructor to load the image. This
	// can vary depending on the use case of the panel.
	public JPanelWithBackground(String fileName, int hauteur, int largeur) {
		ImageIcon icon = new ImageIcon(fileName);
		backgroundImage = icon.getImage().getScaledInstance(largeur, hauteur, 0);
		scaled = backgroundImage;
	}

	//Pour la resize

	public void updateBackgroundSize(int width, int height) {
		scaled = backgroundImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		paintComponent(this.getGraphics());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw the background image.
		g.drawImage(scaled, 0, 0, this);
	}

}
