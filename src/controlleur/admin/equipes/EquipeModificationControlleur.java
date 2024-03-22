package controlleur.admin.equipes;

import dao.Connexion;
import dao.DaoEquipe;
import dao.DaoInscription;
import dao.DaoJoueur;
import dao.DaoSaison;
import modele.CustomDate;
import modele.Equipe;
import modele.Inscription;
import modele.Joueur;
import modele.Pays;
import modele.Saison;
import vue.Page;
import vue.admin.equipes.details.VueAdminEquipesDetails;
import vue.admin.equipes.liste.CaseEquipe;
import vue.common.CustomComboBox;
import vue.common.FileChooser;
import vue.common.JFramePopup;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class EquipeModificationControlleur implements ActionListener, MouseListener, ItemListener {
	private VueAdminEquipesDetails vue;
	private DaoEquipe daoEquipe;
	private DaoJoueur daoJoueur;
	private DaoInscription daoInscription;
	private DaoSaison daoSaison;
	private boolean editing;
	private boolean logoChanged;
	private boolean saisonDefined;
	private BufferedImage logo;
	private CaseEquipe caseEquipe;

	public EquipeModificationControlleur(VueAdminEquipesDetails vue) {
		this.vue = vue;
		Connexion connexion = Connexion.getConnexion();
		this.daoEquipe = new DaoEquipe(connexion);
		this.daoJoueur = new DaoJoueur(connexion);
		this.daoInscription = new DaoInscription(connexion);
		this.daoSaison = new DaoSaison(connexion);
		this.logoChanged = false;
		this.saisonDefined = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vue.getBoutonAnnuler()) {
			EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE);
			this.reset();
		} else if (e.getSource() == this.vue.getBoutonValider() && this.vue.getBoutonValider().getText().equals("Valider")) {
			passerEnNonEditing();
			String nomPays = Objects.requireNonNull(this.vue.getComboboxPays().getSelectedItem()).toString();
			Pays pays = Pays.trouverPaysParNom(nomPays);
			Equipe equipe = new Equipe(this.vue.getChampNom().getText(), pays);
			addEquipe(equipe);
			if (this.saisonDefined) {
				addEquipeSaison(equipe);
			}
			updateCase(equipe);
			EquipesObserver.getInstance().notifyVue(Page.EQUIPES_LISTE);

		} else if (e.getSource() == this.vue.getBoutonValider() && this.vue.getBoutonValider().getText().equals("Modifier")) {
			passerEnEditing();
		}
	}

	public void reset() {
		this.logoChanged = false;
		this.logo = null;
		this.saisonDefined = false;
	}

	private void updateCase(Equipe equipe) {
		try {
			Image img = ImageIO.read(new File("assets/logo-equipes/" + equipe.getNom() + ".jpg"));
			ImageIcon icon = new ImageIcon(img);
			Image imgPays = ImageIO.read(new File("assets/country-flags/png100px/" + equipe.getPays().getCode() + ".png"));
			ImageIcon iconPays = new ImageIcon(imgPays);
			this.caseEquipe.setLogo(icon);
			this.caseEquipe.setPays(iconPays);
			this.caseEquipe.updatePanel();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void init(CaseEquipe caseEquipe, boolean newEditing) {
		this.editing = newEditing;
		String nomEquipe = caseEquipe.getNom();
		this.caseEquipe = caseEquipe;

		try {
			Optional<Equipe> findEquipe = this.daoEquipe.getById(nomEquipe);
			if (!findEquipe.isPresent()) {
				throw new RuntimeException("L'équipe n'existe pas");
			}
			List<Joueur> joueurs = this.daoJoueur.getJoueurParEquipe(nomEquipe);
			List<String> listeJoueurs = joueurs.stream().map(Joueur::getPseudo).collect(Collectors.toList());
			Equipe equipe = findEquipe.get();

			BufferedImage img = ImageIO.read(new File("assets/logo-equipes/" + equipe.getNom() + ".jpg"));

			ImageIcon defaultLogo = new ImageIcon(resizeImage(img, this.vue.getLabelLogo().getWidth(), this.vue.getLabelLogo().getHeight()));

			List<Saison> saisons = this.daoInscription.getSaisonByEquipe(equipe.getNom());
			if (!saisons.isEmpty()) {
				this.saisonDefined = true;
			}
			List<Integer> listSaison = saisons.stream().map(Saison::getAnnee).collect(Collectors.toList());

			this.vue.setNom(equipe.getNom());
			this.vue.setPays(equipe.getPays());
			this.vue.setJoueurs(listeJoueurs);
			this.vue.setLogo(defaultLogo);
			this.vue.getChampWorldRank().setEditable(false);
			this.vue.getChampNom().setEditable(false);
			if (editing) {
				passerEnEditing();
			} else {
				passerEnNonEditing();
			}
			this.vue.setSaisons(listSaison);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void passerEnEditing() {
		this.setEditing(true);
		this.vue.getLabelLogo().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.vue.getBoutonValider().setText("Valider");
	}

	private void passerEnNonEditing() {
		this.setEditing(false);
		this.vue.getLabelLogo().setCursor(Cursor.getDefaultCursor());
		this.vue.getBoutonValider().setText("Modifier");
	}

	private void setEditing(boolean editing) {
		this.editing = editing;
		this.vue.getbtnAjoutSaisons().setVisible(editing && !this.saisonDefined);

		CustomComboBox<Pays> ref = this.vue.getComboboxPays();
		ref.setActif(editing);
	}

	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
		Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
		BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		return outputImage;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// default implementation ignored
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this.vue.getLabelLogo() && this.editing) {
			JLabel labelLogo = this.vue.getLabelLogo();
			try {
				this.logo = FileChooser.createPopup(this.logo, labelLogo, "JPG Images", "jpg");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (this.logo != null) {
				this.logoChanged = true;
			}

		} else if (e.getSource() == this.vue.getbtnAjoutSaisons()) {
			this.saisonDefined = true;
			this.vue.addSaison(CustomDate.now().getAnnee());
			this.vue.getbtnAjoutSaisons().setVisible(false);
		}
	}

	public void addEquipe(Equipe equipeInserer) {
		try {
			this.daoEquipe.update(equipeInserer);
			if (!this.logoChanged) {
				return;
			}
			String filename = "assets/logo-equipes/" + equipeInserer.getNom() + ".jpg";
			File outputfile = new File(filename);
			if (outputfile.exists()) {
				outputfile.delete();
				outputfile = new File(filename);
			}
			ImageIO.write(this.logo, "jpg", outputfile);
		} catch (Exception e) {
			new JFramePopup("Erreur", "Erreur d'insertion", () -> EquipesObserver.getInstance().notifyVue(Page.EQUIPES_CREATION));
		}
	}

	public void addEquipeSaison(Equipe equipeInserer) {
		try {
			addEquipe(equipeInserer);
			Saison saison = daoSaison.getLastSaison();
			Inscription inscription = new Inscription(saison, equipeInserer);
			daoInscription.add(inscription);
		} catch (SQLException e) {
			new JFramePopup("Erreur", "Erreur d'insertion", () -> {
			});
		} catch (Exception e) {
			new JFramePopup("Erreur", "Erreur d'insertion dans la saison", () -> {
			});
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// default implementation ignored
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// default implementation ignored
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// default implementation ignored
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// default implementation ignored
	}
}
