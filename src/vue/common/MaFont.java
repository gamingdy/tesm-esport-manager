package vue.common;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class MaFont{

	private static Font maFont;
	private static HashMap<String,Font> mesFonts = new HashMap<String,Font>();
	
	private static Font getFont(String param) {
		if (maFont == null) {
			File font_file = new File("assets/ChakraPetch-Regular.ttf");
			try {
				maFont = Font.createFont(Font.TRUETYPE_FONT, font_file);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return mesFonts.get(param);
	}

	public static Font getFontMenu() {
		Font laFont = getFont("Menu");
		if (laFont == null) {
			laFont = maFont.deriveFont(54F);
			mesFonts.put("Menu", laFont);
			System.out.println(mesFonts);
		}
		return laFont;
	}
	
	public static Font getFontBoutonMenu() {
		Font laFont = getFont("BoutonMenu");
		if (laFont == null) {
			laFont = maFont.deriveFont(20F);
			mesFonts.put("BoutonMenu", laFont);
			System.out.println(mesFonts);
		}
		return laFont;
	}
	
	public static Font getFontTitre1() {
		Font laFont = getFont("Titre");
		if (laFont == null) {
			laFont = maFont.deriveFont(24F);
			mesFonts.put("Titre",laFont);
		}
		return laFont;
	}
	
	public static Font getFontTitre2() {
		Font laFont = getFont("Titre");
		if (laFont == null) {
			laFont = maFont.deriveFont(20F);
			mesFonts.put("Titre",laFont);
		}
		return laFont;
	}
	
	public static Font getFontTitre3() {
		Font laFont = getFont("Titre");
		if (laFont == null) {
			laFont = maFont.deriveFont(20F);
			mesFonts.put("Titre",laFont);
		}
		return laFont;
	}

	public static Font getFontTitre4() {
		Font laFont = getFont("Titre");
		if (laFont == null) {
			laFont = maFont.deriveFont(16F);
			mesFonts.put("Titre",laFont);
		}
		return laFont;
	}
	
	public static Font getFontTitre5() {
		Font laFont = getFont("Titre");
		if (laFont == null) {
			laFont = maFont.deriveFont(12F);
			mesFonts.put("Titre",laFont);
		}
		return laFont;
	}
	
	public static Font getFontTitre6() {
		Font laFont = getFont("Titre");
		if (laFont == null) {
			laFont = maFont.deriveFont(8F);
			mesFonts.put("Titre",laFont);
		}
		return laFont;
	}
	
	public static Font getFontGrandCorps() {
		Font laFont = getFont("Corps");
		if (laFont == null) {
			laFont = maFont.deriveFont(16F);
			mesFonts.put("Corps", laFont);
		}
		return laFont;
	}
	
	public static Font getFontPetitCorps() {
		Font laFont = getFont("Corps");
		if (laFont == null) {
			laFont = maFont.deriveFont(12F);
			mesFonts.put("Corps", laFont);
		}
		return laFont;
	}
}
