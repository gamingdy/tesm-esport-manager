package vue.main;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import vue.common.JPanelWithBackground;
import vue.common.MaFont;

public class Main extends JFrame {


	private static final long serialVersionUID = 1L;
	private TitleBar topPanel;
	private MenuNavBar navbar;
	private Point compCoords;
	private JPanelWithBackground panelContenu;
	private ConteneurMain panelMain;
	
	private void setCustomTitleBar() {
		// Set title bar to custom title bar
		setUndecorated(true);
		topPanel = new TitleBar(this, compCoords);
		final JButton minimize = new JButton("-");
		final JButton exit = new JButton("X");

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		minimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});

		compCoords = null;


		topPanel.add(minimize);
		Color color = new Color(25, 11, 52);
		topPanel.setBackground(color);
		topPanel.add(exit);
		topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.black));
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		navbar = new MenuNavBar();
		setIconImage(new ImageIcon("assets/logo.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		setLocationRelativeTo(null);
		setCustomTitleBar();
		getContentPane().add(topPanel, BorderLayout.NORTH);

		panelContenu = null;

		try {
			panelContenu = new JPanelWithBackground("assets/background.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		panelContenu.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		getContentPane().add(panelContenu, BorderLayout.CENTER);

		//Création du jpanel principal avec le menu

		setMenu();
		
		setContenu("Accueil");
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
		panelMenu.setBackground(Color.black);
		panelMenu.setBorder(BorderFactory.createMatteBorder(3, 4, 4, 4, Color.red));
		contraintesPanelMenu.fill = GridBagConstraints.HORIZONTAL;
		contraintesPanelMenu.weightx = 0.2;
		contraintesPanelMenu.gridx = 0;
		contraintesPanelMenu.gridy = 0;
		panelContenu.add(panelMenu, contraintesPanelMenu);

		JLabel labelMenu = new JLabel("Menu");
		labelMenu.setForeground(Color.white);
		labelMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.red));
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
	 * @param identifiant 
	 */
	public void setContenu(String identifiant) {
		((CardLayout) panelMain.getLayout()).show(panelMain, identifiant);
	}
}
