package controlleur.admin.equipes;

import controlleur.VueObserver;
import controlleur.admin.tournois.TournoisObserver;
import dao.Connexion;
import dao.DaoEquipe;
import dao.DaoJoueur;
import dao.DaoSaison;
import modele.Equipe;
import modele.Joueur;
import modele.Pays;
import vue.Page;
import vue.admin.equipes.creation.PopupPseudo;
import vue.admin.equipes.creation.VueAdminEquipesCreation;
import vue.common.JFramePopup;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class EquipeCreationControlleur implements ActionListener, ItemListener, MouseListener {
	private final VueAdminEquipesCreation vue;
	private final DaoEquipe daoEquipe;
	private BufferedImage logo;
	private ImageIcon drapeauDeBase = new ImageIcon("assets/country-flags/earth.png");
	private static Connexion c;

	public EquipeCreationControlleur(VueAdminEquipesCreation newVue) {
		this.vue = newVue;
		c = Connexion.getConnexion();
		daoEquipe = new DaoEquipe(c);
		DaoSaison daoSaison = new DaoSaison(c);
		DaoJoueur daoJoueur = new DaoJoueur(c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton bouton = (JButton) e.getSource();
		if (Objects.equals(bouton.getText(), "Ajouter")) {
			String nomEquipe = vue.getNomEquipe().trim();
			Pays champPaysEquipe;
			if (vue.getChampPaysEquipe() == null) {
				champPaysEquipe = null;
			} else {
				champPaysEquipe = Pays.trouverPaysParNom(vue.getChampPaysEquipe());
			}
			if ((nomEquipe.isEmpty()) || (logo == null) || (champPaysEquipe == null)) {
				if (nomEquipe.isEmpty()) {
					new JFramePopup("Erreur", "Nom de l'equipe est vide", () -> VueObserver.getInstance().notifyVue(Page.EQUIPES));
				} else if (logo == null) {
					new JFramePopup("Erreur", "Le logo de l'equipe est obligatoire", () -> VueObserver.getInstance().notifyVue(Page.EQUIPES));
				} else {
					new JFramePopup("Erreur", "Veuillez choisir le pays de l'equipe", () -> VueObserver.getInstance().notifyVue(Page.EQUIPES));
				}

			} else if (equipeDejaExistante(nomEquipe)) {
				new JFramePopup("Erreur", "L'equipe existe deja", () -> VueObserver.getInstance().notifyVue(Page.EQUIPES));
				this.logo = null;
				this.vue.clearField();
			} else {
				Equipe equipeInserer = new Equipe(nomEquipe, champPaysEquipe);

				try {
					daoEquipe.add(equipeInserer);
					initEquipe(equipeInserer);
					File outputfile = new File("assets/logo-equipes/" + nomEquipe + ".jpg");
					ImageIO.write(logo, "jpg", outputfile);
					new JFramePopup("Succès", "L'équipe est insérée", () -> VueObserver.getInstance().notifyVue(Page.EQUIPES));
					this.logo = null;
					this.vue.clearField();


				} catch (Exception ex) {
					this.logo = null;
					new JFramePopup("Erreur", "Erreur d'insertion", () -> VueObserver.getInstance().notifyVue(Page.EQUIPES));
					throw new RuntimeException(ex);
				}
			}

		} else if ((Objects.equals(bouton.getText(), "Annuler"))) {
			EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE);
			this.logo = null;
			this.vue.clearField();
		}
	}

	private boolean equipeDejaExistante(String nomEquipe) {
		try {
			Equipe equipe = daoEquipe.getById(nomEquipe).get();
			return equipe != null;
		} catch (Exception ignored) {
			return false;
		}

	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			Pays item = (Pays) e.getItem();
			vue.setDrapeau(item.getCode());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == vue.getbtnAjoutJoueurs()) {
			PopupPseudo s = new PopupPseudo("Veuillez entrer le pseudo du Joueur", "Pseudo du joueur", () -> EquipesObserver.getInstance().notifyVue(Page.EQUIPES_CREATION));
			if (s.IS_OK) {
				System.out.println("ui");
			} else {
				System.out.println("non");
			}

			//this.vue.setJoueur(resultat, 1);
		}
		if (e.getSource() == vue.getLabelLogo()) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif"));
			int returnVal = 0;
			try {
				// ...
				returnVal = chooser.showOpenDialog(this.vue);
				// ...
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				//recuperer le fichier choisi
				File fichier = chooser.getSelectedFile();
				try {
					//le transformer en image et la resize
					BufferedImage image = ImageIO.read(fichier);
					BufferedImage image_resized = resizeImage(image, this.vue.getLabelLogo().getWidth(), this.vue.getLabelLogo().getHeight());
					//transformer en icone pour pouvoir l'afficher

					ImageIcon imageIcon = new ImageIcon(image_resized);
					//passer l'image au controleur pour pouvoir la stoquer plus tard
					this.logo = image;
					//affichage
					this.vue.getLabelLogo().setIcon(imageIcon);
					this.vue.getLabelLogo().setText("");
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
		BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		return outputImage;
	}

	private static String randomUsername(String name) {
		List<String> characters = Arrays.asList(name.split(""));
		Collections.shuffle(characters);
		StringBuilder afterShuffle = new StringBuilder();
		for (String character : characters) {
			afterShuffle.append(character);
		}
		return afterShuffle.toString();
	}

	private static void initEquipe(Equipe equipe) {
		DaoJoueur daoJoueur = new DaoJoueur(c);
		String default_username = "patata";
		for (int i = 0; i < 5; i++) {
			default_username = randomUsername(default_username);
			try {
				Joueur j = new Joueur(default_username, equipe);
				daoJoueur.add(j);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
