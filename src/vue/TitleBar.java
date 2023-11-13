package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TitleBar extends JPanel {

	private Point coordsWin;

	public TitleBar(JFrame mainWindow, Point coords) {
		super((LayoutManager) new FlowLayout(FlowLayout.RIGHT));
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
