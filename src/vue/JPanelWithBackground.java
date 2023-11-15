package vue;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class JPanelWithBackground extends JPanel {

	private Image backgroundImage;
	private Image scaled;

	// Some code to initialize the background image.
	// Here, we use the constructor to load the image. This
	// can vary depending on the use case of the panel.
	public JPanelWithBackground(String fileName) throws IOException {
		ImageIcon icon = new ImageIcon(fileName);
		backgroundImage = icon.getImage().getScaledInstance(1300, 800, 0);
	}

	//Pour la resize
	@Override
	  public void invalidate() {
	    super.invalidate();
	    int width = getWidth();
	    int height = getHeight();

	    if (width > 0 && height > 0) {
	      scaled = backgroundImage.getScaledInstance(getWidth(), getHeight(),
	          Image.SCALE_FAST);
	    }
	  }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		

		// Draw the background image.
		g.drawImage(scaled, 0, 0, this);
	}

}
