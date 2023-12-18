package vue.admin.main;

import vue.Page;

import javax.swing.*;
import java.awt.*;

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
	 * @param equipes de la page à afficher
	 */
	public void show(Page equipes) {
		cardLayout.show(this, equipes.name());
	}

	public void addPage(Component page, Page id) {
		add(page, id.name());
	}
}
