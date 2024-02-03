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

public class JFramePopupEquipe extends JFrame {

	public interface ActionHandler {
		void handleAction();
	}

	public JFramePopupEquipe(String title, String message, ActionHandler ajouterHandler, ActionHandler saisonHandler) {
		super(title);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(600, 200));

		ImageIcon icon = new ImageIcon(("assets/logo.png"));
		setIconImage(icon.getImage());

		JPanel panel = createPanel(message, ajouterHandler, saisonHandler);
		add(panel);

		pack();
		setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
		setVisible(true);
	}

	private JPanel createPanel(String message, ActionHandler ajouterHandler, ActionHandler saisonHandler) {
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

		JButton okButton = new JButton("Créer");
		okButton.setFont(MaFont.getFontTitre4());
		okButton.setForeground(CustomColor.BLANC);
		okButton.setBackground(CustomColor.TRANSPARENT);
		okButton.setOpaque(false);
		okButton.setFocusable(false);
		okButton.addActionListener(e -> {
			if (ajouterHandler != null) {
				ajouterHandler.handleAction();
			}
			dispose();
		});
		buttonPanel.add(okButton);

		JButton addToSeasonButton = new JButton("Ajouter à la saison");
		addToSeasonButton.setFont(MaFont.getFontTitre4());
		addToSeasonButton.setForeground(CustomColor.BLANC);
		addToSeasonButton.setBackground(CustomColor.TRANSPARENT);
		addToSeasonButton.setOpaque(false);
		addToSeasonButton.setFocusable(false);
		addToSeasonButton.addActionListener(e -> {
			if (saisonHandler != null) {
				saisonHandler.handleAction();
			}
			dispose();
		});
		buttonPanel.add(addToSeasonButton);

		panel.add(buttonPanel, BorderLayout.SOUTH);

		return panel;
	}
}