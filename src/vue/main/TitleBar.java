package vue.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TitleBar extends JPanel {

	private Point coordsWin;

	public TitleBar(JFrame mainWindow, Point coords) {
		super((LayoutManager) new FlowLayout(FlowLayout.RIGHT));

		mainWindow.setUndecorated(true);

		final JButton minimize = new JButton("-");
		final JButton exit = new JButton("X");

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		minimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.setState(JFrame.ICONIFIED);
			}
		});


		this.add(minimize);
		Color color = new Color(25, 11, 52);
		this.setBackground(color);
		this.add(exit);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.black));


		this.coordsWin = coords;
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
