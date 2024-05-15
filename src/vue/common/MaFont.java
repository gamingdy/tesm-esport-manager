package vue.common;

import java.awt.Font;
import java.io.File;
import java.util.HashMap;

public class MaFont {
	private MaFont() {
		// default implementation ignored
	}

	private static Font font;
	private static HashMap<String, Font> mesFonts = new HashMap<>();

	private static Font getFont(String param) {
		if (font == null) {
			File fontFile = new File("assets/ChakraPetch-Regular.ttf");
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
			} catch (Exception e) {
			}
		}
		return mesFonts.get(param);
	}

	public static Font getFontMenu() {
		Font laFont = getFont("Menu");
		if (laFont == null) {
			laFont = font.deriveFont(54F);
			mesFonts.put("Menu", laFont);
		}
		return laFont;
	}

	public static Font getFontBoutonMenu() {
		Font laFont = getFont("BoutonMenu");
		if (laFont == null) {
			laFont = font.deriveFont(20F);
			mesFonts.put("BoutonMenu", laFont);
		}
		return laFont;
	}

	public static Font getFontTitre1() {
		Font laFont = getFont("Titre1");
		if (laFont == null) {
			laFont = font.deriveFont(24F);
			mesFonts.put("Titre1", laFont);
		}
		return laFont;
	}

	public static Font getFontTitre2() {
		Font laFont = getFont("Titre2");
		if (laFont == null) {
			laFont = font.deriveFont(20F);
			mesFonts.put("Titre2", laFont);
		}
		return laFont;
	}

	public static Font getFontTitre3() {
		Font laFont = getFont("Titre3");
		if (laFont == null) {
			laFont = font.deriveFont(20F);
			mesFonts.put("Titre3", laFont);
		}
		return laFont;
	}

	public static Font getFontTitre4() {
		Font laFont = getFont("Titre4");
		if (laFont == null) {
			laFont = font.deriveFont(16F);
			mesFonts.put("Titre4", laFont);
		}
		return laFont;
	}

	public static Font getFontTitre5() {
		Font laFont = getFont("Titre5");
		if (laFont == null) {
			laFont = font.deriveFont(12F);
			mesFonts.put("Titre5", laFont);
		}
		return laFont;
	}

	public static Font getFontTitre6() {
		Font laFont = getFont("Titre6");
		if (laFont == null) {
			laFont = font.deriveFont(8F);
			mesFonts.put("Titre6", laFont);
		}
		return laFont;
	}

	public static Font getFontGrandCorps() {
		Font laFont = getFont("Corps");
		if (laFont == null) {
			laFont = font.deriveFont(16F);
			mesFonts.put("GrandCorps", laFont);
		}
		return laFont;
	}

	public static Font getFontPetitCorps() {
		Font laFont = getFont("Corps");
		if (laFont == null) {
			laFont = font.deriveFont(14F);
			mesFonts.put("PetitCorps", laFont);
		}
		return laFont;
	}

	public static Font getFontLabelConnexion() {
		Font laFont = getFont("labelConnexion");
		if (laFont == null) {
			laFont = font.deriveFont(30F);
			mesFonts.put("labelConnexion", laFont);
		}
		return laFont;
	}

	public static Font getFontTitreConnexion() {
		Font laFont = getFont("titreConnexion");
		if (laFont == null) {
			laFont = font.deriveFont(50F);
			mesFonts.put("titreConnexion", laFont);
		}
		return laFont;
	}
}
