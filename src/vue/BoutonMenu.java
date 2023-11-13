package vue;

import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class BoutonMenu extends JButton {
	
	GridBagConstraints contraintesBouton;
	
	public BoutonMenu(Main main,String name,int y) {
		super(name);
		this.setFont(main.getFont().deriveFont(20F));
		this.setForeground(Color.white);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.red));
		this.setFocusable(false);
		this.contraintesBouton = new GridBagConstraints();
		this.contraintesBouton.fill = GridBagConstraints.BOTH;
		this.contraintesBouton.weighty = 1F/6F;
		this.contraintesBouton.weightx = 1;
		this.contraintesBouton.gridx = 0;
		this.contraintesBouton.gridy = y;
	}
	
	public GridBagConstraints getContraintes() {
		return this.contraintesBouton;
	}

}
