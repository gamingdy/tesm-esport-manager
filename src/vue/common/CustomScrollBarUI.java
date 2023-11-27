package vue.common;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

import vue.Vue;

public class CustomScrollBarUI extends BasicScrollBarUI {
	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
		g.setColor(CustomColor.ROSE_CONTOURS);
		g.fillRect(r.x, r.y, r.width - 1, r.height);
	}

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
		g.setColor(CustomColor.BACKGROUND_MAIN);
		g.fillRect(r.x, r.y, r.width, r.height);
		g.setColor(CustomColor.ROSE_CONTOURS);
		g.drawRect(r.x, r.y, r.width - 1, r.height - 1);
	}

	@Override
	protected JButton createIncreaseButton(int orientation) {
		JButton jb = new JButton();
		jb.setPreferredSize(new Dimension(0, 0));
		jb.setVisible(false);
		return jb;
	}

	@Override
	protected JButton createDecreaseButton(int orientation) {
		JButton jb = new JButton();
		jb.setVisible(false);
		jb.setPreferredSize(new Dimension(0, 0));
		return jb;
	}
}
