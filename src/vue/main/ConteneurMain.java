package vue.main;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListModel;

import vue.accueil.PageAccueil;
import vue.accueil.LigneEquipe;
import vue.accueil.LigneTournoi;
import vue.accueil.LigneMatche
;

@SuppressWarnings("serial")
public class ConteneurMain extends JPanel {

	private GridBagConstraints gbc;
	private CardLayout cardLayout;
	
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
		
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		
		DefaultListModel<LigneEquipe> l = new DefaultListModel<LigneEquipe>();
		l.addElement(new LigneEquipe(1,new ImageIcon("assets/logo.png"), "Equipe", 69));
		l.addElement(new LigneEquipe(1,new ImageIcon("assets/logo.png"), "Equipe", 69));
		l.addElement(new LigneEquipe(1,new ImageIcon("assets/logo.png"), "Equipe", 69));
		l.addElement(new LigneEquipe(1,new ImageIcon("assets/logo.png"), "Equipe", 69));
		l.addElement(new LigneEquipe(1,new ImageIcon("assets/logo.png"), "Equipe", 69));

		this.add(new PageAccueil(l,new DefaultListModel<LigneTournoi>(),new DefaultListModel<LigneMatche>()),"Accueil");
		cardLayout.show(this,"Accueil");
	}
	
	public GridBagConstraints getGridBagConstraints() {
		return this.gbc;
	}
	
	public void show(String str) {
		cardLayout.show(this,str);
	}
}
