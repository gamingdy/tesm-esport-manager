package vue.admin.main;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import vue.admin.VueAdmin;
import vue.common.CustomColor;
import vue.common.JPanelWithBackground;
import vue.common.MaFont;
import vue.common.WindowResizer;

public class Main extends JPanel {


	private static final long serialVersionUID = 1L;
	private MenuNavBar navbar;
	private ConteneurMain panelMain;


	/**
	 * Create the frame.
	 */
	public Main() {
		navbar = new MenuNavBar();

		//Création du jpanel principal avec le menu
		setContent();
		setMenu();
	}
	
	public void setContent() {
		GridBagLayout gbl_PanelContenu = new GridBagLayout();
		gbl_PanelContenu.columnWidths = new int[]{0, 0};
		gbl_PanelContenu.rowHeights = new int[]{0};
		gbl_PanelContenu.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_PanelContenu.rowWeights = new double[]{Double.MIN_VALUE};
		setLayout(gbl_PanelContenu);

		panelMain = new ConteneurMain();
		panelMain.setPreferredSize(new Dimension(0,0));
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
		GridBagLayout gbl_panelMenu = new GridBagLayout();
		panelMenu.setPreferredSize(new Dimension(0,0));
		panelMenu.setLayout(gbl_panelMenu);
		gbl_panelMenu.columnWidths = new int[]{0};
		gbl_panelMenu.rowHeights = new int[]{0};
		gbl_panelMenu.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panelMenu.rowWeights = new double[]{Double.MIN_VALUE};

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
		labelMenu.setHorizontalAlignment(JLabel.CENTER);
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
	 * @param identifiant l'identifiant
	 */
	public void setPage(String identifiant) {
		panelMain.show(identifiant);
	}
}
