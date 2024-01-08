package vue.common;

import vue.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TitleBar extends JPanel {

	private Point coordsWin;
	final ButtonTitleBar minimize;
	final ButtonTitleBar exit;
	final ButtonTitleBar enlarge;
	private JLabel title;
	private static TitleBar instance;


	private TitleBar(Vue vue) {
		super(new BorderLayout());

		vue.setUndecorated(true);
		Color titleBarColor = new Color(25, 11, 52);

		minimize = new ButtonTitleBar("Minimiser", titleBarColor);
		enlarge = new ButtonTitleBar("Agrandir", titleBarColor);
		exit = new ButtonTitleBar("Fermer", titleBarColor);

		exit.addActionListener(e -> System.exit(0));
		exit.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		minimize.addActionListener(e -> vue.setState(JFrame.ICONIFIED));
		minimize.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		enlarge.addActionListener(e -> {
			if (vue.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
				vue.setExtendedState(JFrame.NORMAL);
				enlarge.updateIcon("Agrandir");
				vue.updateBackgroundSize();
			} else {
				vue.setExtendedState(JFrame.MAXIMIZED_BOTH);
				enlarge.updateIcon("Reduire");
				vue.updateBackgroundSize();
			}
		});
		enlarge.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		JPanel titleBarButton = new JPanel(new GridLayout(1, 3));
		titleBarButton.setBackground(titleBarColor);
		titleBarButton.add(minimize);
		titleBarButton.add(enlarge);
		titleBarButton.add(exit);


		JPanel titleBarIcon = new JPanel(new BorderLayout());
		titleBarIcon.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		titleBarIcon.setBackground(titleBarColor);
		ImageIcon logo = new ImageIcon("assets/logo.png");
		Image newimg = logo.getImage().getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		titleBarIcon.add(new JLabel(new ImageIcon(newimg)), BorderLayout.CENTER);

		title = new JLabel("", SwingConstants.CENTER);
		title.setForeground(Color.white);
		title.setFont(MaFont.getFontTitre1());
		title.setBorder(BorderFactory.createEmptyBorder(0, 135, 0, 0));

		this.setBackground(titleBarColor);
		this.add(titleBarButton, BorderLayout.EAST);
		this.add(titleBarIcon, BorderLayout.WEST);
		this.add(title, BorderLayout.CENTER);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.black));


		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				coordsWin = null;
			}

			public void mousePressed(MouseEvent e) {
				coordsWin = e.getPoint();
			}
		});

		addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
				vue.setLocation(currCoords.x - coordsWin.x, currCoords.y - coordsWin.y);
			}
		});
	}

	public static synchronized TitleBar getInstance() {
		return instance;
	}

	public static synchronized TitleBar getInstance(Vue vue) {
		if (instance == null) {
			instance = new TitleBar(vue);
		}
		return instance;
	}

	public void setTitle(String title) {
		this.title.setText(title);
	}

}
