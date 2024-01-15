package vue.common;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileChooser {

	public static BufferedImage createPopup(BufferedImage logo, JLabel labelLogo, String descriptionExtension, String extension) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter(descriptionExtension, extension));
		int returnVal = chooser.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			//recuperer le fichier choisi
			File fichier = chooser.getSelectedFile();
			try {
				//le transformer en image et la resize
				BufferedImage image = ImageIO.read(fichier);
				BufferedImage image_resized = resizeImage(image, labelLogo.getWidth(), labelLogo.getHeight());
				//transformer en icone pour pouvoir l'afficher

				ImageIcon imageIcon = new ImageIcon(image_resized);
				//passer l'image au controleur pour pouvoir la stoquer plus tard
				logo = image;
				//affichage
				labelLogo.setIcon(imageIcon);
				labelLogo.setText("");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}

		return logo;
	}

	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
		Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
		BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		return outputImage;
	}
}
