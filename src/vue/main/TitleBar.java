package vue.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TitleBar extends JPanel {

	private Point coordsWin;
	final ButtonTitleBar minimize;
	final ButtonTitleBar exit;

	public TitleBar(JFrame mainWindow) {
		super(new BorderLayout());

		mainWindow.setUndecorated(true);
		Color titleBarColor = new Color(25, 11, 52);

		minimize = new ButtonTitleBar("Reduire", titleBarColor);
		exit = new ButtonTitleBar("Fermer", titleBarColor);

		exit.addActionListener(e -> System.exit(0));
		exit.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		minimize.addActionListener(e -> mainWindow.setState(JFrame.ICONIFIED));
		minimize.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		JPanel titleBarButton = new JPanel(new GridLayout(1, 2));
		titleBarButton.setBackground(titleBarColor);
		titleBarButton.add(minimize);
		titleBarButton.add(exit);

		JPanel titleBarIcon = new JPanel(new BorderLayout());
		titleBarIcon.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		titleBarIcon.setBackground(titleBarColor);
		ImageIcon logo = new ImageIcon("assets/logo.png");
		Image newimg = logo.getImage().getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		titleBarIcon.add(new JLabel(new ImageIcon(newimg)), BorderLayout.CENTER);

		this.setBackground(titleBarColor);
		this.add(titleBarButton, BorderLayout.EAST);
		this.add(titleBarIcon, BorderLayout.WEST);
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
				mainWindow.setLocation(currCoords.x - coordsWin.x, currCoords.y - coordsWin.y);
			}
		});
	}

}
