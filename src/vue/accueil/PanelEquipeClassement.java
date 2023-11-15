package vue.accueil;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class PanelEquipeClassement extends JPanel {
	public PanelEquipeClassement() {
		setBorder(new EmptyBorder(0, 20, 0, 0));
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("New label");
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		add(lblNewLabel_3);
	}

}
