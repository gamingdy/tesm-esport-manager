package controlleur.admin.equipes;

import dao.Connexion;
import dao.DaoEquipe;
import dao.DaoInscription;
import dao.DaoJoueur;
import modele.Equipe;
import modele.Joueur;
import modele.Saison;
import vue.admin.equipes.details.VueAdminEquipesDetails;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EquipeModificationControlleur implements ActionListener {
	private VueAdminEquipesDetails vue;
	private DaoEquipe daoEquipe;
	private DaoJoueur daoJoueur;
	private DaoInscription daoInscription;

	public EquipeModificationControlleur(VueAdminEquipesDetails vue) {
		this.vue = vue;
		Connexion connexion = Connexion.getConnexion();
		this.daoEquipe = new DaoEquipe(connexion);
		this.daoJoueur = new DaoJoueur(connexion);
		this.daoInscription = new DaoInscription(connexion);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public void init(String nomEquipe, boolean editing) {
		try {
			Optional<Equipe> find_equipe = this.daoEquipe.getById(nomEquipe);
			if (!find_equipe.isPresent()) {
				throw new RuntimeException("L'équipe n'existe pas");
			}
			List<Joueur> joueurs = this.daoJoueur.getJoueurParEquipe(nomEquipe);
			List<String> liste_joueurs = joueurs.stream().map(Joueur::getPseudo).collect(Collectors.toList());
			Equipe equipe = find_equipe.get();

			BufferedImage img = ImageIO.read(new File("assets/logo-equipes/" + equipe.getNom() + ".jpg"));
			
			ImageIcon logo = new ImageIcon(resizeImage(img,this.vue.getLabelLogo().getWidth(), this.vue.getLabelLogo().getHeight()));

			List<Saison> saisons = this.daoInscription.getSaisonByEquipe(equipe.getNom());
			System.out.println(saisons.size());
			List<Integer> lst_saison = saisons.stream().map(Saison::getAnnee).collect(Collectors.toList());

			this.vue.setNom(equipe.getNom());
			this.vue.setPays(equipe.getPays());
			this.vue.setJoueurs(liste_joueurs);
			this.vue.setLogo(logo);
			this.vue.setSaisons(lst_saison);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
		BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		return outputImage;
	}
}
