package vue.admin.tournois.creation;

import vue.common.CustomColor;
import vue.common.MaFont;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class PopupCompteArbitre extends JFrame {

	private JTextField tf;

	public interface ActionHandler {
		void handleAction();
	}

	public PopupCompteArbitre(String title, ActionHandler actionHandler, String nomTournoi) {
		super(title);
		setType(Type.UTILITY);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(400, 200));

		ImageIcon icon = new ImageIcon(("assets/logo.png"));
		setIconImage(icon.getImage());

		JPanel panel = createPanel(actionHandler, nomTournoi);
		add(panel);

		pack();
		setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
		setVisible(true);
	}

	public JPanel createPanel(ActionHandler actionHandler, String nomTournoi) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(new Color(15, 3, 25));

		JTextArea label = new JTextArea("Voici le login  de l'arbitre : " + nomTournoi + System.lineSeparator()+"Saisissez le mot de passe :");
		label.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		label.setLineWrap(true);
		label.setWrapStyleWord(true);
		label.setOpaque(false);
		label.setEditable(false);
		label.setForeground(CustomColor.BLANC);
		label.setFont(MaFont.getFontTitre3());
		panel.add(label, BorderLayout.NORTH);

		tf = new JTextField(25);
		tf.setForeground(CustomColor.BLANC);
		tf.setBackground(CustomColor.BACKGROUND_MENU);
		tf.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		tf.setFont(MaFont.getFontTitre4());
		tf.setCaretColor(CustomColor.BLANC);

		panel.add(tf, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(new Color(15, 3, 25));
		JButton closeButton = new JButton("Annuler");
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.setFont(MaFont.getFontTitre4());
		closeButton.setForeground(CustomColor.BLANC);
		closeButton.setBackground(CustomColor.TRANSPARENT);
		closeButton.setOpaque(false);
		closeButton.setFocusable(false);
		closeButton.addActionListener(e -> dispose());
		buttonPanel.add(closeButton);

		JButton okButton = new JButton("Valider");
		okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		okButton.setFont(MaFont.getFontTitre4());
		okButton.setForeground(CustomColor.BLANC);
		okButton.setBackground(CustomColor.TRANSPARENT);
		okButton.setOpaque(false);
		okButton.setFocusable(false);
		okButton.addActionListener(e -> {
			if (actionHandler != null) {
				actionHandler.handleAction();
			}
			dispose();
		});
		buttonPanel.add(okButton);

		panel.add(buttonPanel, BorderLayout.SOUTH);

		return panel;
	}

	public String getSaisie() {
		return tf.getText();
	}
}

