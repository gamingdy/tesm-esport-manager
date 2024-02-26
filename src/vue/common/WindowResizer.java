package vue.common;

import vue.Vue;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


enum SIDE {
	LEFT, RIGHT, BOTTOM, NONE
}

public class WindowResizer {
	private int currentHeight;
	private int currentWidth;
	private final Vue mainWindow;
	private SIDE side;
	private boolean isResizing;
	private Point originalPosition;
	private final int BORDERSIZE = 10;
	private final int MINIMUMHEIGHT = 600;
	private final int MINIMUMWIDTH = 1100;

	public WindowResizer(Vue vue, int height, int width) {
		this.mainWindow = vue;
		this.currentHeight = height;
		this.currentWidth = width;
		this.side = SIDE.NONE;
		this.isResizing = false;
		mouseMotion();
		mouseEvent();
	}

	private void findBorder(Point p) {
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

	private boolean isMinimumSize(int width, int height) {
		return height + this.currentHeight >= MINIMUMHEIGHT && width + this.currentWidth >= MINIMUMWIDTH;
	}

	private void resize(int width, int height) {
		this.mainWindow.setSize(currentWidth + width, currentHeight + height);
	}


	private void updateSize() {
		this.currentHeight = this.mainWindow.getHeight();
		this.currentWidth = this.mainWindow.getWidth();
		this.mainWindow.updateBackgroundSize();
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

				if (!isResizing) {
					originalPosition = mouseLocationOnScreen(evt);
				}
				if (side != SIDE.NONE) {
					isResizing = true;
					Point pOnScreen = mouseLocationOnScreen(evt);
					Point originalPos = mainWindow.getLocationOnScreen();
					if (side == SIDE.LEFT) {
						int newWidth = originalPosition.x - pOnScreen.x;
						if (!isMinimumSize(newWidth, 0)) {
							return;
						}
						mainWindow.setLocation(pOnScreen.x, originalPos.y);
						resize(newWidth, 0);
					} else if (side == SIDE.RIGHT) {
						int newWidth = pOnScreen.x - originalPosition.x;
						if (!isMinimumSize(newWidth, 0)) {
							return;
						}
						resize(newWidth, 0);
					} else if (side == SIDE.BOTTOM) {
						int newHeight = pOnScreen.y - originalPosition.y;
						if (!isMinimumSize(0, newHeight)) {
							return;
						}
						resize(0, newHeight);
					}
				}
			}

			public void mouseMoved(MouseEvent e) {
				Point p = mouseLocationOnApp(e);
				findBorder(p);

				if (side == SIDE.NONE) {
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
				findBorder(p);
				if (side == SIDE.LEFT) {
					mainWindow.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
					side = SIDE.LEFT;
				} else if (side == SIDE.RIGHT) {
					mainWindow.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
					side = SIDE.RIGHT;
				} else if (side == SIDE.BOTTOM) {
					mainWindow.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
					side = SIDE.BOTTOM;
				} else {
					side = SIDE.NONE;
					mainWindow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (isResizing) {
					updateSize();
				}
				isResizing = false;
			}

		});


	}
}
