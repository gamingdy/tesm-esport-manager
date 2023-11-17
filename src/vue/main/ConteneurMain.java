package vue.main;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListModel;

import vue.accueil.PageAccueil;

@SuppressWarnings("serial")
public class ConteneurMain extends JPanel {

	private GridBagConstraints gbc;
	
	public ConteneurMain() {
		this.gbc = new GridBagConstraints();
		gbc.gridx = 1;
		
		gbc = new GridBagConstraints();
		this.setPreferredSize(new Dimension(0, Integer.MAX_VALUE));
		this.setOpaque(false);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.8;
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		CardLayout cl_main = new CardLayout();
		this.setLayout(cl_main);

		this.add(new PageAccueil(),"Accueil");
		cl_main.show(this,"Accueil");
	}
	
	public GridBagConstraints getGridBagConstraints() {
		return this.gbc;
	}
}
