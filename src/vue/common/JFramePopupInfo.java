package vue.common;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class JFramePopupInfo extends JFrame {

	public interface ActionHandler {
		void handleAction();
	}

	public JFramePopupInfo(String title, String message) {
		super(title);
		setType(Type.UTILITY);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(400, 200));

		ImageIcon icon = new ImageIcon(("assets/logo.png"));
		setIconImage(icon.getImage());

		JPanel panel = createPanel(message);
		add(panel);

		pack();
		setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
		setVisible(true);
	}

	private JPanel createPanel(String message) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(new Color(15, 3, 25));

		JLabel label = new JLabel(message);
		label.setForeground(Color.white);
		label.setHorizontalAlignment(SwingConstants.CENTER); // Centre le texte
		label.setFont(MaFont.getFontTitre3()); // Agrandir la police

		panel.add(label, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(new Color(15, 3, 25));

		JButton closeButton = new JButton("OK");
		closeButton.setFont(MaFont.getFontTitre4());
		closeButton.setForeground(CustomColor.BLANC);
		closeButton.setBackground(CustomColor.TRANSPARENT);
		closeButton.setOpaque(false);
		closeButton.setFocusable(false);
		closeButton.addActionListener(e -> dispose());
		buttonPanel.add(closeButton);

		panel.add(buttonPanel, BorderLayout.SOUTH);

		return panel;
	}
}
