package vue.login;

import javax.swing.*;

import java.awt.GridBagLayout;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import controlleur.LoginControlleur;
import vue.common.CustomColor;
import vue.common.MaFont;

@SuppressWarnings("serial")
public class ChampConnexion extends JPanel {
	private JTextField textField;

	public ChampConnexion(String libellé, boolean isPassword, LoginControlleur controleur) {
		setOpaque(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel labelLibellé = new JLabel(libellé);
		labelLibellé.setVerticalAlignment(SwingConstants.BOTTOM);
		labelLibellé.setFont(MaFont.getFontLabelConnexion());
		labelLibellé.setForeground(CustomColor.BLANC);
		GridBagConstraints gbcLabelLibellé = new GridBagConstraints();
		gbcLabelLibellé.fill = GridBagConstraints.HORIZONTAL;
		gbcLabelLibellé.insets = new Insets(0, 0, 5, 0);
		gbcLabelLibellé.gridx = 0;
		gbcLabelLibellé.gridy = 0;
		add(labelLibellé, gbcLabelLibellé);

		if (isPassword) {
			textField = new JPasswordField();
		} else {
			textField = new JTextField();
		}

		textField.setFont(textField.getFont().deriveFont((float) MaFont.getFontLabelConnexion().getSize()));
		textField.getDocument().addDocumentListener(controleur);
		textField.addKeyListener(controleur);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
	}

	public String getContenu() {
		return textField.getText();
	}


	public void clear() {
		textField.setText("");
	}
}
