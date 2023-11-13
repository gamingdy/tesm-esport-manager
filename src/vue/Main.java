package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Main extends JFrame{
	

	private static final long serialVersionUID = 1L;
	private Font font;
	private JPanel contentPane;
	private BoutonMenu boutonAccueil;
	private BoutonMenu boutonEquipes;
	private BoutonMenu boutonTournois;
	private BoutonMenu boutonArbitres;
	private BoutonMenu boutonSaisons;
	private BoutonMenu boutonDeconnexion;

	
	private void setFont() {
		this.font = null;
		try {
			File font_file = new File("assets/ChakraPetch-Regular.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, font_file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Font getFont() {
		if (this.font == null) {
			this.setFont();
		}
		return this.font;
	}
	
	@SuppressWarnings("serial")
	public class JPanelWithBackground extends JPanel {

		  private Image backgroundImage;

		  // Some code to initialize the background image.
		  // Here, we use the constructor to load the image. This
		  // can vary depending on the use case of the panel.
		  public JPanelWithBackground(String fileName) throws IOException {
			  ClassLoader cl= this.getClass().getClassLoader();
			  URL imageURL   = cl.getResource("background.jpg");
			  ImageIcon icon = new ImageIcon(imageURL);
			  backgroundImage = icon.getImage().getScaledInstance(1300, 800, DO_NOTHING_ON_CLOSE);
		  }

		  public void paintComponent(Graphics g) {
		    super.paintComponent(g);

		    // Draw the background image.
		    g.drawImage(backgroundImage, 0, 0, this);
		  }
		}

	/**
	 * Create the frame.
	 */
	public Main() {
		setFont();
		setIconImage(Toolkit.getDefaultToolkit().getImage("\\assets\\logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		try {
			contentPane = new JPanelWithBackground("background.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		contentPane.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		
		
		//Création du contentPane principal avec 
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0};
		gbl_contentPane.rowHeights = new int[]{0};
		gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panelMenu = new JPanel();
		GridBagLayout gbl_panelMenu = new GridBagLayout();
		panelMenu.setLayout(gbl_panelMenu);
		gbl_panelMenu.columnWidths = new int[]{0};
		gbl_panelMenu.rowHeights = new int[]{0};
		gbl_panelMenu.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panelMenu.rowWeights = new double[]{Double.MIN_VALUE};
		
		GridBagConstraints contraintesPanelMenu = new GridBagConstraints();
		panelMenu.setPreferredSize(new Dimension(0,Integer.MAX_VALUE));
		panelMenu.setBackground(Color.black);
		panelMenu.setBorder(BorderFactory.createLineBorder(Color.red,4));
		contraintesPanelMenu.fill = GridBagConstraints.HORIZONTAL;
		contraintesPanelMenu.weightx = 0.2;
		contraintesPanelMenu.gridx = 0;
		contraintesPanelMenu.gridy = 0;
		contentPane.add(panelMenu,contraintesPanelMenu);
		
		JPanel panelMain = new JPanel();
		GridBagConstraints contraintesPanelMain = new GridBagConstraints();
		panelMain.setPreferredSize(new Dimension(0,Integer.MAX_VALUE));
		panelMain.setOpaque(false);
		contraintesPanelMain.fill = GridBagConstraints.HORIZONTAL;
		contraintesPanelMain.weightx = 0.8;
		contraintesPanelMain.gridx = 1;
		contraintesPanelMain.gridy = 0;
		contentPane.add(panelMain,contraintesPanelMain);
		
		JLabel labelMenu = new JLabel("Menu");
		labelMenu.setForeground(Color.white);
		labelMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.red));
		labelMenu.setFont(font.deriveFont(54F));
		labelMenu.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints contraintesLabelMenu = new GridBagConstraints();
		contraintesLabelMenu.gridx = 0;
		contraintesLabelMenu.gridy = 0;
		contraintesLabelMenu.weighty = 0.1;
		contraintesLabelMenu.weightx = 1;
		contraintesLabelMenu.fill = GridBagConstraints.BOTH;
		panelMenu.add(labelMenu, contraintesLabelMenu);
		

		JPanel panelNavbar = new JPanel();
		GridBagLayout gbl_panelNavbar = new GridBagLayout();
		panelNavbar.setLayout(gbl_panelNavbar);
		gbl_panelNavbar.columnWidths = new int[]{0};
		gbl_panelNavbar.rowHeights = new int[]{0};
		gbl_panelNavbar.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panelNavbar.rowWeights = new double[]{Double.MIN_VALUE};
		
		GridBagConstraints contraintesPanelNavbar = new GridBagConstraints();
		panelNavbar.setBackground(Color.black);
		contraintesPanelNavbar.fill = GridBagConstraints.VERTICAL;
		contraintesPanelNavbar.weighty = 0.9;
		contraintesPanelNavbar.weightx = 1;
		contraintesPanelNavbar.gridx = 0;
		contraintesPanelNavbar.gridy = 1;
		panelMenu.add(panelNavbar,contraintesPanelNavbar);
		
		boutonAccueil = new BoutonMenu(this,"Accueil",0);
		panelNavbar.add(boutonAccueil,boutonAccueil.getContraintes());
		
		boutonEquipes = new BoutonMenu(this,"Equipes",1);
		panelNavbar.add(boutonEquipes,boutonEquipes.getContraintes());
		
		boutonTournois = new BoutonMenu(this,"Tournois",2);
		panelNavbar.add(boutonTournois,boutonTournois.getContraintes());
		
		boutonArbitres = new BoutonMenu(this,"Arbitres",3);
		panelNavbar.add(boutonArbitres,boutonArbitres.getContraintes());
		
		boutonSaisons = new BoutonMenu(this,"Saisons précédentes",4);
		panelNavbar.add(boutonSaisons,boutonSaisons.getContraintes());
		
		boutonDeconnexion = new BoutonMenu(this,"Déconnexion",5);
		boutonDeconnexion.setBorder(BorderFactory.createEmptyBorder());
		panelNavbar.add(boutonDeconnexion,boutonDeconnexion.getContraintes());
	}

}
