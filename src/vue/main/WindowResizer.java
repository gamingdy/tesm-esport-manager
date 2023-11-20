package vue.main;

import vue.common.JPanelWithBackground;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;


enum SIDE {
	LEFT, RIGHT, BOTTOM, NONE
}

public class WindowResizer {
	private int currentHeight;
	private int currentWidth;
	private final Main mainWindow;
	private SIDE side;
	private boolean isResizing;
	private Point originalPosition;
	private final JPanelWithBackground background;

	public WindowResizer(Main mainWindow, JPanelWithBackground background, int height, int width) {
		this.mainWindow = mainWindow;
		this.background = background;
		this.currentHeight = height;
		this.currentWidth = width;
		this.side = SIDE.NONE;
		this.isResizing = false;
		mouseMotion();
		mouseEvent();
	}

	private void findBorder(Point p) {
		int BORDERSIZE = 5;
		if (p.x <= BORDERSIZE) {
			this.side = SIDE.LEFT;
		} else if (currentWidth - BORDERSIZE <= p.x && p.x <= currentWidth) {
			this.side = SIDE.RIGHT;
		} else if (currentHeight - BORDERSIZE <= p.y && p.y <= currentHeight) {
			this.side = SIDE.BOTTOM;
		} else {
			this.side = SIDE.NONE;
		}
	}

	private void resize(int width, int height) {
		this.mainWindow.setSize(currentWidth + width, currentHeight + height);
	}


	private void updateSize() {
		this.currentHeight = this.mainWindow.getHeight();
		this.currentWidth = this.mainWindow.getWidth();
	}

	private Point mouseLocationOnScreen(MouseEvent event) {
		return event.getLocationOnScreen();
	}

	private Point mouseLocationOnApp(MouseEvent event) {
		return event.getPoint();
	}


	private void mouseMotion() {
		this.mainWindow.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
				Point p = mouseLocationOnApp(evt);
				if (!isResizing) {
					originalPosition = mouseLocationOnScreen(evt);
				}
				if (side != SIDE.NONE) {
					isResizing = true;
					Point pOnScreen = mouseLocationOnScreen(evt);
					Point originalPos = mainWindow.getLocationOnScreen();
					if (side == SIDE.LEFT) {
						int newWidth = originalPosition.x - pOnScreen.x;
						mainWindow.setLocation(pOnScreen.x, originalPos.y);
						resize(newWidth, 0);
					} else if (side == SIDE.RIGHT) {
						int newWidth = pOnScreen.x - originalPosition.x;
						resize(newWidth, 0);
					} else if (side == SIDE.BOTTOM) {
						int newHeight = pOnScreen.y - originalPosition.y;
						resize(0, newHeight);
					}
				}
			}

			public void mouseMoved(MouseEvent e) {
				Point p = mouseLocationOnApp(e);
				if (!isOnBorder(p)) {
					mainWindow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
	}


	private void mouseEvent() {
		this.mainWindow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (isResizing) {
					return;
				}
				Point p = mouseLocationOnApp(e);
				if (p.x == 0) {
					mainWindow.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
					side = SIDE.LEFT;
				} else if (p.x == currentWidth - 1) {
					mainWindow.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
					side = SIDE.RIGHT;
				} else if (p.y == currentHeight - 1) {
					mainWindow.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
					side = SIDE.BOTTOM;
				} else {
					side = SIDE.NONE;
					mainWindow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}

			public void mouseReleased(MouseEvent e) {
				isResizing = false;
				updateSize();
			}

		});


	}
}
