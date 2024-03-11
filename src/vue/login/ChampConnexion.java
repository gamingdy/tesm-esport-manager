package vue.login;

import controlleur.login.LoginControlleur;
import vue.common.CustomColor;
import vue.common.MaFont;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

@SuppressWarnings("serial")
public class ChampConnexion extends JPanel {
	private JTextField textField;

	public ChampConnexion(String libelle, boolean isPassword, LoginControlleur controleur) {
		setOpaque(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel labelLibelle = new JLabel(libelle);
		labelLibelle.setVerticalAlignment(SwingConstants.BOTTOM);
		labelLibelle.setFont(MaFont.getFontLabelConnexion());
		labelLibelle.setForeground(CustomColor.BLANC);
		GridBagConstraints gbcLabelLibelle = new GridBagConstraints();
		gbcLabelLibelle.fill = GridBagConstraints.HORIZONTAL;
		gbcLabelLibelle.insets = new Insets(0, 0, 5, 0);
		gbcLabelLibelle.gridx = 0;
		gbcLabelLibelle.gridy = 0;
		add(labelLibelle, gbcLabelLibelle);

		if (isPassword) {
			textField = new JPasswordField();
		} else {
			textField = new JTextField();
		}

		textField.setFont(textField.getFont().deriveFont((float) MaFont.getFontLabelConnexion().getSize()));
		textField.getDocument().addDocumentListener(controleur);
		textField.addKeyListener(controleur);
		GridBagConstraints gbcTextField = new GridBagConstraints();
		gbcTextField.fill = GridBagConstraints.HORIZONTAL;
		gbcTextField.gridx = 0;
		gbcTextField.gridy = 1;
		add(textField, gbcTextField);
	}

	public String getContenu() {
		return textField.getText();
	}


	public void clear() {
		textField.setText("");
	}
}
