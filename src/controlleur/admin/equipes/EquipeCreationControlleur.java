package controlleur.admin.equipes;

import controlleur.VueObserver;
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
import java.util.Objects;

public class EquipeCreationControlleur implements ActionListener, ItemListener, MouseListener {
	private final VueAdminEquipesCreation vue;
	private final DaoEquipe daoEquipe;
	private DaoJoueur daoJoueur;
	private BufferedImage logo;
	private ImageIcon drapeauDeBase = new ImageIcon("assets/country-flags/earth.png");
	private static Connexion c;
	private PopupPseudo popupPseudo;
	private int nbJoueurs = 0;

	public EquipeCreationControlleur(VueAdminEquipesCreation newVue) {
		this.vue = newVue;
		c = Connexion.getConnexion();
		daoEquipe = new DaoEquipe(c);
		DaoSaison daoSaison = new DaoSaison(c);
		daoJoueur = new DaoJoueur(c);
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
			//if ((nomEquipe.isEmpty()) || (logo == null) || (champPaysEquipe == null)) {
			if (nomEquipe.isEmpty()) {
				new JFramePopup("Erreur", "Nom de l'equipe est vide", () -> VueObserver.getInstance().notifyVue(Page.EQUIPES));
			} else if (logo == null) {
				new JFramePopup("Erreur", "Le logo de l'equipe est obligatoire", () -> VueObserver.getInstance().notifyVue(Page.EQUIPES));
			} else if (champPaysEquipe == null) {
				new JFramePopup("Erreur", "Veuillez choisir le pays de l'equipe", () -> VueObserver.getInstance().notifyVue(Page.EQUIPES));
			} else if (this.nbJoueurs < 5) {
				new JFramePopup("Erreur", "Pas assez de joueurs dans l'equipe", () -> VueObserver.getInstance().notifyVue(Page.EQUIPES));
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
			if (!equipeComplete()) {
				this.popupPseudo = new PopupPseudo("Veuillez entrer le pseudo du Joueur", () -> {
					EquipesObserver.getInstance().notifyVue(Page.EQUIPES_CREATION);
					this.addJoueur();
				});
			} else {
				new JFramePopup("Erreur", "Equipe déjà complète", () -> VueObserver.getInstance().notifyVue(Page.EQUIPES));
			}

		}
		if (e.getSource() == vue.getLabelLogo()) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif"));
			int returnVal = chooser.showOpenDialog(this.vue);
			
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

	public void addJoueur() {
		this.vue.setJoueur(this.popupPseudo.getSaisie(), this.nbJoueurs);
		this.nbJoueurs++;
	}

	public boolean equipeComplete() {
		return this.nbJoueurs == 5;
	}


	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
		BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		return outputImage;
	}

	public void initEquipe(Equipe equipe) {
		daoJoueur = new DaoJoueur(c);
		Object[] liste = this.vue.getJoueurs();
		try {
			for (Object pseudo : liste) {
				daoJoueur.add(new Joueur(pseudo.toString(), equipe));
			}
		} catch (Exception e) {

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
}
