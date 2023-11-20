package vue.main;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import vue.Vue;
import vue.common.JPanelWithBackground;
import vue.common.MaFont;

public class Main extends JFrame {


	private static final long serialVersionUID = 1L;
	private TitleBar topPanel;
	private MenuNavBar navbar;
	private JPanelWithBackground panelContenu;
	private ConteneurMain panelMain;

	private final int HEIGHT = 800;
	private final int WIDTH = 1300;


	/**
	 * Create the frame.
	 */
	public Main() {
		navbar = new MenuNavBar();
		topPanel = new TitleBar(this);


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		getContentPane().add(topPanel, BorderLayout.NORTH);

		panelContenu = null;

		try {
			panelContenu = new JPanelWithBackground("assets/background.jpg", WIDTH, HEIGHT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		panelContenu.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		getContentPane().add(panelContenu, BorderLayout.CENTER);

		//Création du jpanel principal avec le menu

		setMenu();

		setContenu("Accueil");
		new WindowResizer(this, panelContenu, HEIGHT, WIDTH);
	}

	public void setMenu() {
		GridBagLayout gbl_PanelContenu = new GridBagLayout();
		gbl_PanelContenu.columnWidths = new int[]{0};
		gbl_PanelContenu.rowHeights = new int[]{0};
		gbl_PanelContenu.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_PanelContenu.rowWeights = new double[]{Double.MIN_VALUE};
		panelContenu.setLayout(gbl_PanelContenu);

		JPanel panelMenu = new JPanel();
		GridBagLayout gbl_panelMenu = new GridBagLayout();
		panelMenu.setLayout(gbl_panelMenu);
		gbl_panelMenu.columnWidths = new int[]{0};
		gbl_panelMenu.rowHeights = new int[]{0};
		gbl_panelMenu.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panelMenu.rowWeights = new double[]{Double.MIN_VALUE};

		GridBagConstraints contraintesPanelMenu = new GridBagConstraints();
		panelMenu.setPreferredSize(new Dimension(0, Integer.MAX_VALUE));
		panelMenu.setBackground(Vue.BACKGROUND_MENU);
		panelMenu.setBorder(BorderFactory.createMatteBorder(3, 4, 4, 4, Vue.ROSE_CONTOURS));
		contraintesPanelMenu.fill = GridBagConstraints.HORIZONTAL;
		contraintesPanelMenu.weightx = 0.2;
		contraintesPanelMenu.gridx = 0;
		contraintesPanelMenu.gridy = 0;
		panelContenu.add(panelMenu, contraintesPanelMenu);

		JLabel labelMenu = new JLabel("Menu");
		labelMenu.setForeground(Vue.BLANC);
		labelMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Vue.ROSE_CONTOURS));
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


		panelMain = new ConteneurMain();
		panelContenu.add(panelMain, panelMain.getGridBagConstraints());
	}

	/**
	 * Change la partie contenu du main
	 *
	 * @param identifiant
	 */
	public void setContenu(String identifiant) {
		((CardLayout) panelMain.getLayout()).show(panelMain, identifiant);
	}
}
