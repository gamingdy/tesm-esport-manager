package vue;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class BoutonMenu extends JButton {

	GridBagConstraints contraintesBouton;
	
	public BoutonMenu(MenuNavBar menuNavBar,String name,int y, Object controlleur) {
		super(name);
		this.setFont(menuNavBar.getFont().deriveFont(20F));
		this.setForeground(Color.white);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.red),BorderFactory.createEmptyBorder(0, 0, 1, 0)));
		this.setFocusable(false);
		this.contraintesBouton = new GridBagConstraints();
		this.contraintesBouton.fill = GridBagConstraints.BOTH;
		this.contraintesBouton.weighty = 1F / 6F;
		this.contraintesBouton.weightx = 1;
		this.contraintesBouton.gridx = 0;
		this.contraintesBouton.gridy = y;
		this.addActionListener((ActionListener) controlleur);
		this.addMouseListener((MouseListener) controlleur);
	}

	public GridBagConstraints getContraintes() {
		return this.contraintesBouton;
	}

	public void selectionner() {
		this.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.red));
	}
	
	public void deselectionner() {
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.red),BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black)));
	}
	
	public void survoller() {
		this.setForeground(Color.red);
	}
	
	public void finSurvoller() {
		this.setForeground(Color.white);
	}
}
