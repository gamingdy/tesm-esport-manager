package vue.admin.main;

import vue.Page;
import vue.common.CustomColor;
import vue.common.MaFont;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Main extends JPanel {


	private static final long serialVersionUID = 1L;
	private MenuNavBar navbar;
	private ConteneurMain panelMain;


	/**
	 * Create the frame.
	 */
	public Main(MenuNavBar navbar) {
		this.navbar = navbar;

		//Création du jpanel principal avec le menu
		setContent();
		setMenu();
	}

	public void setContent() {
		GridBagLayout gblPanelContenu = new GridBagLayout();
		gblPanelContenu.columnWidths = new int[]{0, 0};
		gblPanelContenu.rowHeights = new int[]{0};
		gblPanelContenu.columnWeights = new double[]{Double.MIN_VALUE};
		gblPanelContenu.rowWeights = new double[]{Double.MIN_VALUE};
		setLayout(gblPanelContenu);

		panelMain = new ConteneurMain();
		panelMain.setPreferredSize(new Dimension(0, 0));
		panelMain.setOpaque(false);
		GridBagConstraints gbcPanelContenu = new GridBagConstraints();
		gbcPanelContenu.fill = GridBagConstraints.BOTH;
		gbcPanelContenu.weightx = 0.8;
		gbcPanelContenu.gridx = 1;
		gbcPanelContenu.gridy = 0;
		add(panelMain, gbcPanelContenu);
	}

	public void setMenu() {
		JPanel panelMenu = new JPanel();
		GridBagLayout gblPanelMenu = new GridBagLayout();
		panelMenu.setPreferredSize(new Dimension(0, 0));
		panelMenu.setLayout(gblPanelMenu);
		gblPanelMenu.columnWidths = new int[]{0};
		gblPanelMenu.rowHeights = new int[]{0};
		gblPanelMenu.columnWeights = new double[]{Double.MIN_VALUE};
		gblPanelMenu.rowWeights = new double[]{Double.MIN_VALUE};

		GridBagConstraints contraintesPanelMenu = new GridBagConstraints();
		panelMenu.setPreferredSize(new Dimension(0, Integer.MAX_VALUE));
		contraintesPanelMenu.weightx = 0.2;
		contraintesPanelMenu.fill = GridBagConstraints.HORIZONTAL;
		panelMenu.setBackground(CustomColor.BACKGROUND_MENU);
		panelMenu.setBorder(BorderFactory.createMatteBorder(3, 4, 4, 4, CustomColor.ROSE_CONTOURS));
		contraintesPanelMenu.gridx = 0;
		contraintesPanelMenu.gridy = 0;
		add(panelMenu, contraintesPanelMenu);

		JLabel labelMenu = new JLabel("Menu");
		labelMenu.setForeground(CustomColor.BLANC);
		labelMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, CustomColor.ROSE_CONTOURS));
		labelMenu.setFont(MaFont.getFontMenu());
		labelMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		GridBagConstraints contraintesLabelMenu = new GridBagConstraints();
		contraintesLabelMenu.gridx = 0;
		contraintesLabelMenu.gridy = 0;
		contraintesLabelMenu.weighty = 0.1;
		contraintesLabelMenu.weightx = 1;
		contraintesLabelMenu.fill = GridBagConstraints.BOTH;
		panelMenu.add(labelMenu, contraintesLabelMenu);


		panelMenu.add(navbar, navbar.getGBC());
	}

	/**
	 * Change la page de contenue du main
	 *
	 * @param equipes l'identifiant
	 */
	public void setPage(Page equipes) {
		panelMain.show(equipes);
	}

	/**
	 * Ajoute une page dans le conteneur du contenu
	 *
	 * @param c  le composant à ajouter
	 * @param id son identifiant
	 */
	public void addPage(JComponent c, Page id) {
		panelMain.add(c, id.name());
	}
}
