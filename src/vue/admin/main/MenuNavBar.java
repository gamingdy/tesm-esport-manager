package vue.admin.main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controlleur.BoutonMenuControlleur;
import vue.common.CustomColor;

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

		boutons.put("Accueil", new BoutonMenu(this, "Accueil", 0));
		this.add(boutons.get("Accueil"), boutons.get("Accueil").getContraintes());
		selectionner(boutons.get("Accueil"));

		boutons.put("Equipes", new BoutonMenu(this, "Equipes", 1));
		this.add(boutons.get("Equipes"), boutons.get("Equipes").getContraintes());

		boutons.put("Tournois", new BoutonMenu(this, "Tournois", 2));
		this.add(boutons.get("Tournois"), boutons.get("Tournois").getContraintes());

		boutons.put("Arbitres", new BoutonMenu(this, "Arbitres", 3));
		this.add(boutons.get("Arbitres"), boutons.get("Arbitres").getContraintes());

		boutons.put("Saisons précédentes", new BoutonMenu(this, "Saisons précédentes", 4));
		this.add(boutons.get("Saisons précédentes"), boutons.get("Saisons précédentes").getContraintes());

		boutons.put("Déconnexion", new BoutonMenu(this, "Déconnexion", 5));
		boutons.get("Déconnexion").setBorder(BorderFactory.createEmptyBorder());
		this.add(boutons.get("Déconnexion"), boutons.get("Déconnexion").getContraintes());
	}

	public void addButtonControlleur(Object controlleur){
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
					if (e.getKey() == bouton.getText()) {
						e.getValue().selectionner();
					} else if (e.getKey() != "Déconnexion") {
						e.getValue().deselectionner();
					}
				});
		;
	}

}
