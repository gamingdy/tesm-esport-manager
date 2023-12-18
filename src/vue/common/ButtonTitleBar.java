package vue.common;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ButtonTitleBar extends JButton {

	private Color hoverColor;

	public ButtonTitleBar(String name, Color bgColor) {
		super();
		updateIcon(name);

		setBackground(bgColor);
		setHorizontalAlignment(SwingConstants.CENTER);
		setPreferredSize(new Dimension(60, 40));
		setForeground(Color.white);
		setFocusPainted(false);

		if (name.equals("Fermer")) {
			hoverColor = new Color(168, 6, 6);
		} else {
			hoverColor = new Color(52, 22, 98);
		}

		this.getModel().addChangeListener(e -> {
			ButtonModel model = (ButtonModel) e.getSource();
			if (model.isRollover()) {
				setBackground(hoverColor);
			} else {
				setBackground(bgColor);
			}
		});
	}

	public void updateIcon(String name) {
		setIcon(new ImageIcon("assets/bouton" + name + ".png"));
	}

}
