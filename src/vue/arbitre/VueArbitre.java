package vue.arbitre;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import vue.common.CustomColor;

import java.awt.GridBagLayout;
import java.awt.Insets;

@SuppressWarnings("serial")
public abstract class VueArbitre extends JPanel {
	
	protected JLabel titre;
	protected JPanel main;
	protected JButton bouton;

	public VueArbitre() {
		GridBagLayout gbl = new GridBagLayout();
		gbl.rowWeights = new double[] {0.1,0.75,0.15};
		setLayout(gbl);

		GridBagConstraints gbcTitre = new GridBagConstraints();
		gbcTitre.fill = GridBagConstraints.NONE;
		titre = new JLabel("Titre par défaut");
		add(titre, gbcTitre);
		
		initMain();
		GridBagConstraints gbcMain = new GridBagConstraints();
		gbcMain.insets = new Insets(0,50,0,50);
		gbcMain.gridy = 1;
		gbcMain.fill = GridBagConstraints.BOTH;
		add(main, gbcMain);
	}
	
	protected void initMain() {
		main = new JPanel();
		main.setBackground(CustomColor.BACKGROUND_MAIN);
		main.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3));
		
	}
}
