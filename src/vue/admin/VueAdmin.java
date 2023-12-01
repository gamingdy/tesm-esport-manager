package vue.admin;

import vue.admin.main.Main;
import vue.common.JPanelWithBackground;
import vue.common.TitleBar;
import vue.common.WindowResizer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VueAdmin extends JPanel{
	
	private Main main;
	
	public VueAdmin() {
		setLayout(new BorderLayout());
		main = new Main();
		main.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		main.setOpaque(false);
		add(main, BorderLayout.CENTER);
		setPage("Accueil");
	}


	/**
	 * Change la page de contenue du main
	 *
	 * @param identifiant l'identifiant
	 */
	public void setPage(String identifiant) {
		main.setPage(identifiant);
	}
	
	
}
