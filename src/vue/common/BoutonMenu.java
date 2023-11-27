package vue.common;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import vue.Vue;
import vue.common.MaFont;
import vue.main.MenuNavBar;

@SuppressWarnings("serial")
public class BoutonMenu extends JButton {

	GridBagConstraints contraintesBouton;

	public BoutonMenu(MenuNavBar menuNavBar, String name, int y, Object controlleur) {
		super(name);
		this.setFont(MaFont.getFontBoutonMenu());
		this.setForeground(CustomColor.BLANC);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, CustomColor.ROSE_CONTOURS), BorderFactory.createEmptyBorder(0, 0, 1, 0)));
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
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, CustomColor.ROSE_CONTOURS));
	}

	public void deselectionner() {
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, CustomColor.ROSE_CONTOURS), BorderFactory.createEmptyBorder(0, 0, 1, 0)));
	}

	public void survoller() {
		this.setForeground(CustomColor.ROSE_CONTOURS);
	}

	public void finSurvoller() {
		this.setForeground(CustomColor.BLANC);
	}
}
