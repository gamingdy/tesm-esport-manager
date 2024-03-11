package vue.common;

import javax.swing.JTextField;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class PlaceholderTextField extends JTextField {

	private String placeholder;


	@Override
	protected void paintComponent(final Graphics pG) {
		super.paintComponent(pG);

		if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
			return;
		}

		final Graphics2D g = (Graphics2D) pG;
		g.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(getDisabledTextColor());
		g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
				.getMaxAscent() + getInsets().top + getHeight() / 2 - getFontMetrics(getFont()).getHeight() / 2);
	}

	public void setPlaceholder(final String s) {
		placeholder = s;
	}

}
