package vue.login;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class VueLogin extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	public VueLogin() {
		setLayout(new BorderLayout(0, 0));
		setOpaque(false);
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Identifiant");
		lblNewLabel.setBounds(154, 105, 66, 13);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(154, 128, 96, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mot de passe");
		lblNewLabel_1.setBounds(153, 157, 67, 13);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(154, 180, 96, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel labelTitreConnexion = new JLabel("Bienvenue sur ESporterManager");
		labelTitreConnexion.setBounds(135, 37, 147, 26);
		panel.add(labelTitreConnexion);
		
		JButton btnNewButton = new JButton("Connexion");
		btnNewButton.setBounds(154, 223, 96, 21);
		panel.add(btnNewButton);
	}
}
