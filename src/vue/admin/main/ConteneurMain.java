package vue.admin.main;

import java.awt.CardLayout;
import javax.swing.*;

@SuppressWarnings("serial")
public class ConteneurMain extends JPanel {

	private CardLayout cardLayout;

	public ConteneurMain() {
		this.setOpaque(false);

		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
	}

	/**
	 * Choisit la page à afficher
	 *
	 * @param identifiant de la page à afficher
	 */
	public void show(String identifiant) {
		cardLayout.show(this, identifiant);
	}
}
