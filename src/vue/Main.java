package vue;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class Main extends JFrame {


	private static final long serialVersionUID = 1L;
	private Font font;
	private JPanel contentPane;
	private MenuNavBar navbar;


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
			ImageIcon icon = new ImageIcon(fileName);
			backgroundImage = icon.getImage().getScaledInstance(1300, 800, DO_NOTHING_ON_CLOSE);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// Draw the background image.
			g.drawImage(backgroundImage, 0, 0, this);
		}
	}

	private void setCustomTitleBar() {
		// Set title bar to custom title bar
		setUndecorated(true);
		topPanel = new JPanel((LayoutManager) new FlowLayout(FlowLayout.RIGHT));
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
		topPanel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				compCoords = null;
			}

			public void mousePressed(MouseEvent e) {
				compCoords = e.getPoint();
			}

			public void mouseExited(MouseEvent e) {
				topPanel.setBackground(Color.black);
			}

			public void mouseEntered(MouseEvent e) {
				topPanel.setBackground(Color.red);
			}

			public void mouseClicked(MouseEvent e) {
			}
		});
		topPanel.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
			}

			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
				setLocation(currCoords.x - compCoords.x, currCoords.y - compCoords.y);
			}
		});
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
		setFont();
		setIconImage(new ImageIcon("assets/logo.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		setLocationRelativeTo(null);
		setCustomTitleBar();
		getContentPane().add(topPanel, BorderLayout.NORTH);

		JPanel PanelContenu = new JPanel();

		try {
			PanelContenu = new JPanelWithBackground("assets/background.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		PanelContenu.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		getContentPane().add(PanelContenu, BorderLayout.CENTER);

		//Création du contentPane principal avec 

		GridBagLayout gbl_PanelContenu = new GridBagLayout();
		gbl_PanelContenu.columnWidths = new int[]{0};
		gbl_PanelContenu.rowHeights = new int[]{0};
		gbl_PanelContenu.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_PanelContenu.rowWeights = new double[]{Double.MIN_VALUE};
		PanelContenu.setLayout(gbl_PanelContenu);

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
		panelMenu.setBorder(BorderFactory.createMatteBorder(3,4,4,4,Color.red));
		contraintesPanelMenu.fill = GridBagConstraints.HORIZONTAL;
		contraintesPanelMenu.weightx = 0.2;
		contraintesPanelMenu.gridx = 0;
		contraintesPanelMenu.gridy = 0;
		PanelContenu.add(panelMenu, contraintesPanelMenu);

		JPanel panelMain = new JPanel();
		GridBagConstraints contraintesPanelMain = new GridBagConstraints();
		panelMain.setPreferredSize(new Dimension(0, Integer.MAX_VALUE));
		panelMain.setOpaque(false);
		contraintesPanelMain.fill = GridBagConstraints.HORIZONTAL;
		contraintesPanelMain.weightx = 0.8;
		contraintesPanelMain.gridx = 1;
		contraintesPanelMain.gridy = 0;
		PanelContenu.add(panelMain, contraintesPanelMain);

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


		panelMenu.add(navbar, navbar.getGBC());
		
	}

}
