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

public class JFramePopupSuppressionEquipe extends JFrame {

	public interface ActionHandler {
		void handleAction();
	}

	public JFramePopupSuppressionEquipe(String title, String message, ActionHandler supprimerSaison, ActionHandler supprimerDefinitivement) {
		super(title);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(600, 200));

		ImageIcon icon = new ImageIcon(("assets/logo.png"));
		setIconImage(icon.getImage());

		JPanel panel = createPanel(message, supprimerSaison, supprimerDefinitivement);
		add(panel);

		pack();
		setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
		setVisible(true);
	}

	private JPanel createPanel(String message, ActionHandler supprimerSaison, ActionHandler supprimerDefinitivemnt) {
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

		JButton closeButton = new JButton("Annuler");
		closeButton.setFont(MaFont.getFontTitre4());
		closeButton.setForeground(CustomColor.BLANC);
		closeButton.setBackground(CustomColor.TRANSPARENT);
		closeButton.setOpaque(false);
		closeButton.setFocusable(false);
		closeButton.addActionListener(e -> dispose());
		buttonPanel.add(closeButton);

		JButton okButton = new JButton("Supprimer de la saison");
		okButton.setFont(MaFont.getFontTitre4());
		okButton.setForeground(CustomColor.BLANC);
		okButton.setBackground(CustomColor.TRANSPARENT);
		okButton.setOpaque(false);
		okButton.setFocusable(false);
		okButton.addActionListener(e -> {
			if (supprimerSaison != null) {
				supprimerSaison.handleAction();
			}
			dispose();
		});
		buttonPanel.add(okButton);

		JButton addToSeasonButton = new JButton("Supprimer définitivement");
		addToSeasonButton.setFont(MaFont.getFontTitre4());
		addToSeasonButton.setForeground(CustomColor.BLANC);
		addToSeasonButton.setBackground(CustomColor.TRANSPARENT);
		addToSeasonButton.setOpaque(false);
		addToSeasonButton.setFocusable(false);
		addToSeasonButton.addActionListener(e -> {
			if (supprimerDefinitivemnt != null) {
				supprimerDefinitivemnt.handleAction();
			}
			dispose();
		});
		buttonPanel.add(addToSeasonButton);

		panel.add(buttonPanel, BorderLayout.SOUTH);

		return panel;
	}
}