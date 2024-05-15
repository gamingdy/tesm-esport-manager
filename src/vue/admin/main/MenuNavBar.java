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

		this.boutons = new HashMap<>();

		GridBagLayout gblThis = new GridBagLayout();
		this.setLayout(gblThis);
		gblThis.columnWidths = new int[]{0};
		gblThis.rowHeights = new int[]{0};
		gblThis.columnWeights = new double[]{Double.MIN_VALUE};
		gblThis.rowWeights = new double[]{Double.MIN_VALUE};

		gbc = new GridBagConstraints();
		this.setBackground(CustomColor.BACKGROUND_MENU);
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weighty = 0.9;
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;

		String accueil = BoutonNavBar.ACCUEIL.getNom();
		boutons.put(accueil, new BoutonMenu(accueil, 0));
		this.add(boutons.get(accueil), boutons.get(accueil).getContraintes());
		selectionner(boutons.get(accueil));

		String equipes = BoutonNavBar.EQUIPES.getNom();
		boutons.put(equipes, new BoutonMenu(equipes, 1));
		this.add(boutons.get(equipes), boutons.get(equipes).getContraintes());

		String tournois = BoutonNavBar.TOURNOIS.getNom();
		boutons.put(tournois, new BoutonMenu(tournois, 2));
		this.add(boutons.get(tournois), boutons.get(tournois).getContraintes());

		String arbitres = BoutonNavBar.ARBITRES.getNom();
		boutons.put(arbitres, new BoutonMenu(arbitres, 3));
		this.add(boutons.get(arbitres), boutons.get(arbitres).getContraintes());

		String saisons = BoutonNavBar.SAISON_PRECEDENTES.getNom();
		boutons.put(saisons, new BoutonMenu(saisons, 4));
		this.add(boutons.get(saisons), boutons.get(saisons).getContraintes());

		String deconnexion = BoutonNavBar.DECONNEXION.getNom();
		boutons.put(deconnexion, new BoutonMenu(deconnexion, 5));
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
	}

}
