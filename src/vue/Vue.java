package vue;

import vue.main.Main;

import java.awt.Color;

public class Vue {

	public static final Color BACKGROUND_MAIN = new Color(15, 3, 25,196);
	public static final Color BACKGROUND_MENU = new Color(20, 6, 40);
	public static final Color ROSE_CONTOURS = new Color(188, 19, 254);
	public static final Color BLANC = new Color(243, 229, 245);
	public static final Color TRANSPARENT = new Color(0,0,0,0);

	public static void main(String[] args) {
		Main main = new Main();
		main.setVisible(true);
	}
}
