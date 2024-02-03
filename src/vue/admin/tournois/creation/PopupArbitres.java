package vue.admin.tournois.creation;

import modele.Arbitre;
import vue.common.CustomColor;
import vue.common.CustomComboBox;
import vue.common.MaFont;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;


public class PopupArbitres extends JFrame {

	private CustomComboBox<Arbitre> c;

	public interface ActionHandler {
		void handleAction();
	}

	public PopupArbitres(String title, List<Arbitre> arbitres, ActionHandler actionHandler) {
		super(title);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(400, 200));

		ImageIcon icon = new ImageIcon(("assets/logo.png"));
		setIconImage(icon.getImage());

		JPanel panel = createPanel(actionHandler, arbitres);
		add(panel);

		pack();
		setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
		setVisible(true);
	}

	private JPanel createPanel(ActionHandler actionHandler, List<Arbitre> arbitres) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(new Color(15, 3, 25));

		JLabel label = new JLabel("Saisir un arbitre :");
		label.setForeground(Color.white);
		label.setHorizontalAlignment(SwingConstants.CENTER); // Centre le texte
		label.setFont(MaFont.getFontTitre3()); // Agrandir la police
		panel.add(label, BorderLayout.NORTH);


		DefaultComboBoxModel<Arbitre> model = new DefaultComboBoxModel<Arbitre>();
		arbitres.forEach(model::addElement);

		c = new CustomComboBox<Arbitre>(model);
		c.setRenderer(new ListCellRenderer<Arbitre>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends Arbitre> list, Arbitre value, int index,
														  boolean isSelected, boolean cellHasFocus) {
				JLabel panel;
				if (value != null) {
					panel = new JLabel(value.getNom() + " " + value.getPrenom());
				} else {
					panel = new JLabel("");
				}
				panel.setOpaque(true);
				panel.setForeground(CustomColor.BLANC);
				panel.setBackground(CustomColor.BACKGROUND_MAIN);
				panel.setFocusable(false);
				panel.setFont(MaFont.getFontTitre1());
				if (isSelected) {
					panel.setForeground(CustomColor.ROSE_CONTOURS);
				}
				return panel;
			}
		});
		panel.add(c, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(new Color(15, 3, 25));
		JButton closeButton = new JButton("Annuler");
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.setFont(MaFont.getFontTitre4());
		closeButton.setForeground(CustomColor.BLANC);
		closeButton.setBackground(CustomColor.BACKGROUND_MENU);
		closeButton.setOpaque(false);
		closeButton.setFocusable(false);
		closeButton.addActionListener(e -> dispose());
		buttonPanel.add(closeButton);

		JButton okButton = new JButton("Valider");
		okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		okButton.setFont(MaFont.getFontTitre4());
		okButton.setForeground(CustomColor.BLANC);
		okButton.setBackground(CustomColor.BACKGROUND_MAIN);
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

	public Arbitre getSaisie() {
		return (Arbitre) c.getSelectedItem();
	}
}
