package vue.admin.main;

import controlleur.AdminControlleur;
import vue.BoutonNavBar;
import vue.common.CustomColor;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;

@SuppressWarnings("serial")
public class MenuNavBar extends JPanel {

	HashMap<String, BoutonMenu> boutons;
	GridBagConstraints gbc;

	public MenuNavBar() {
		super();


		this.boutons = new HashMap<String, BoutonMenu>();

		GridBagLayout gbl_this = new GridBagLayout();
		this.setLayout(gbl_this);
		gbl_this.columnWidths = new int[]{0};
		gbl_this.rowHeights = new int[]{0};
		gbl_this.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_this.rowWeights = new double[]{Double.MIN_VALUE};

		gbc = new GridBagConstraints();
		this.setBackground(CustomColor.BACKGROUND_MENU);
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weighty = 0.9;
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;

		String accueil = BoutonNavBar.ACCUEIL.getNom();
		boutons.put(accueil, new BoutonMenu(this, accueil, 0));
		this.add(boutons.get(accueil), boutons.get(accueil).getContraintes());
		selectionner(boutons.get(accueil));

		String equipes = BoutonNavBar.EQUIPES.getNom();
		boutons.put(equipes, new BoutonMenu(this, equipes, 1));
		this.add(boutons.get(equipes), boutons.get(equipes).getContraintes());

		String tournois = BoutonNavBar.TOURNOIS.getNom();
		boutons.put(tournois, new BoutonMenu(this, tournois, 2));
		this.add(boutons.get(tournois), boutons.get(tournois).getContraintes());

		String arbitres = BoutonNavBar.ARBITRES.getNom();
		boutons.put(arbitres, new BoutonMenu(this, arbitres, 3));
		this.add(boutons.get(arbitres), boutons.get(arbitres).getContraintes());

		String saisons = BoutonNavBar.SAISON_PRECEDENTES.getNom();
		boutons.put(saisons, new BoutonMenu(this, saisons, 4));
		this.add(boutons.get(saisons), boutons.get(saisons).getContraintes());

		String deconnexion = BoutonNavBar.DECONNEXION.getNom();
		boutons.put(deconnexion, new BoutonMenu(this, deconnexion, 5));
		boutons.get(deconnexion).setBorder(BorderFactory.createEmptyBorder());
		this.add(boutons.get(deconnexion), boutons.get(deconnexion).getContraintes());
	}

	public void addButtonControlleur(AdminControlleur controlleur) {
		for (BoutonMenu bouton : boutons.values()) {
			bouton.setControlleur(controlleur);
		}
	}

	public GridBagConstraints getGBC() {
		return this.gbc;
	}

	public void selectionner(BoutonMenu bouton) {
		boutons.entrySet().stream()
				.forEach(e -> {
					if (e.getKey() == bouton.getText() && e.getKey() != BoutonNavBar.DECONNEXION.getNom()) {
						e.getValue().selectionner();
					} else if (e.getKey() != BoutonNavBar.DECONNEXION.getNom()) {
						e.getValue().deselectionner();
					}
				});
		;
	}

}
